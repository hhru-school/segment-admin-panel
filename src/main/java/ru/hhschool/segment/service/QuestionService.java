package ru.hhschool.segment.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import javax.inject.Inject;
import javax.transaction.Transactional;
import org.eclipse.jetty.util.StringUtil;
import ru.hhschool.segment.dao.abstracts.AnswerDao;
import ru.hhschool.segment.dao.abstracts.QuestionDao;
import ru.hhschool.segment.exception.HttpBadRequestException;
import ru.hhschool.segment.mapper.AnswerMapper;
import ru.hhschool.segment.mapper.question.QuestionMapper;
import ru.hhschool.segment.mapper.question.QuestionSatusMapper;
import ru.hhschool.segment.model.dto.question.AnswerCreateDto;
import ru.hhschool.segment.model.dto.question.QuestionCreateDto;
import ru.hhschool.segment.model.dto.questioninfopage.AnswerDtoForQuestionsInfo;
import ru.hhschool.segment.model.dto.questioninfopage.QuestionDtoForQuestionsInfo;
import ru.hhschool.segment.model.entity.Answer;
import ru.hhschool.segment.model.entity.Question;
import ru.hhschool.segment.model.enums.AnswersNumberType;

public class QuestionService {
  private final QuestionDao questionDao;
  private final AnswerService answerService;
  private final QuestionFilterService questionFilterService;
  private final AnswerDao answerDao;
  private static final int MAX_DEPTH_OF_TREE = 3;

  @Inject
  public QuestionService(QuestionDao questionDao, AnswerService answerService, QuestionFilterService questionFilterService, AnswerDao answerDao) {
    this.questionDao = questionDao;
    this.answerService = answerService;
    this.questionFilterService = questionFilterService;
    this.answerDao = answerDao;
  }

  @Transactional
  public List<QuestionDtoForQuestionsInfo> getAllQuestionDtoListForQuestionsInfo(String searchQuery, List<String> questionTypeStringList) {
    List<QuestionDtoForQuestionsInfo> questionDtoForQuestionsInfoList = new ArrayList<>();
    List<Question> questionList;
    if (questionTypeStringList == null || questionTypeStringList.size() == 0) {
      questionList = questionDao.findAll();
    } else {
      questionList = questionDao.findAllByType(QuestionSatusMapper.toTypeList(questionTypeStringList));
    }
    List<Question> finalQuestionList = questionList;
    questionList.forEach(question -> {
      List<AnswerDtoForQuestionsInfo> answerDtoList = answerService.getAllAnswerDtoListByListId(
          question.getPossibleAnswers(),
          finalQuestionList,
          MAX_DEPTH_OF_TREE
      );
      QuestionDtoForQuestionsInfo questionDto = QuestionMapper.questionToDto(question, answerDtoList);
      questionDtoForQuestionsInfoList.add(questionDto);
    });
    if (searchQuery == null || searchQuery.equals("")) {
      return questionDtoForQuestionsInfoList;
    }
    return questionFilterService.filterQuestionDtoListByString(searchQuery, questionDtoForQuestionsInfoList);
  }

  @Transactional
  public QuestionDtoForQuestionsInfo getQuestionDtoForQuestionInfo(Long questionId) {
    List<Question> questionList = questionDao.findAll();
    Optional<Question> question = questionList.stream()
        .filter(question1 -> Objects.equals(question1.getId(), questionId))
        .findFirst();
    if (question.isEmpty()) {
      throw new HttpBadRequestException("Вопроса с таким Id  не существует");
    }
    List<AnswerDtoForQuestionsInfo> answerDtoList = answerService.getAllAnswerDtoListByListId(question.get().getPossibleAnswers(), questionList, 3);
    return QuestionMapper.questionToDto(question.get(), answerDtoList);
  }

  @Transactional
  public QuestionDtoForQuestionsInfo add(QuestionCreateDto questionCreateDto) {
    Question mainQuestion = getOrCreateQuestion(questionCreateDto, 1);

    return getQuestionDtoForQuestionInfo(mainQuestion.getId());
  }

  /**
   * Делаем небольшую валидацию и создаем либо находим необходимый нам вопрос.
   * Если есть открытые ответы, проходим их в рекурсии через getOrCreateAnswer.
   */
  private Question getOrCreateQuestion(QuestionCreateDto questionCreateDto, int countDepth) {
    if (countDepth++ > MAX_DEPTH_OF_TREE) {
      throw new HttpBadRequestException(
          String.format("Превышена максимальная вложенность MAX_DEPTH_OF_TREE = %d", MAX_DEPTH_OF_TREE)
      );
    }
    if (StringUtil.isBlank(questionCreateDto.getTitle())) {
      throw new HttpBadRequestException("Название не может быть пустым.");
    }

    boolean isTypeWithAnswer = questionCreateDto.getAnswerType() == AnswersNumberType.MULTI_SELECT
        || questionCreateDto.getAnswerType() == AnswersNumberType.SINGLE_CHOICE;
    boolean isAnswerArrayIsEmpty = questionCreateDto.getPossibleAnswers() == null
        || questionCreateDto.getPossibleAnswers().isEmpty();

    if (isTypeWithAnswer && isAnswerArrayIsEmpty) {
      throw new HttpBadRequestException("Не заданны ответы.");
    }

    List<Long> possibleAnswerIdList = new ArrayList<>();
    if (isTypeWithAnswer) {
      for (AnswerCreateDto answerDto : questionCreateDto.getPossibleAnswers()) {
        Answer answer = getOrCreateAnswer(answerDto, countDepth);
        possibleAnswerIdList.add(answer.getId());
      }
    }

    Question question;
    if (questionCreateDto.getId() == null) {
      question = QuestionMapper.fromDto(questionCreateDto, possibleAnswerIdList);
      questionDao.persist(question);
    } else {
      question = questionDao.findById(questionCreateDto.getId())
          .orElseThrow(() -> new HttpBadRequestException("Указанное поле/вопрос не найден."));
    }
    return question;
  }

  /**
   * Делаем небольшую валидацию и создаем либо находим необходимый нам ответ.
   * В рекурсии создаем вопросы и ответы к ним.
   */
  private Answer getOrCreateAnswer(AnswerCreateDto answerCreateDto, int countDepth) {
    if (StringUtil.isBlank(answerCreateDto.getTitle())) {
      throw new HttpBadRequestException("Название не может быть пустым.");
    }
    if (StringUtil.isBlank(answerCreateDto.getPositiveTitle())) {
      throw new HttpBadRequestException("Утвердительная форма не может быть пустой.");
    }
    List<Long> openQuestionIdList = new ArrayList<>();

    if (answerCreateDto.getOpenQuestions() != null && answerCreateDto.getOpenQuestions().size() > 0) {
      for (QuestionCreateDto openQuestionDto : answerCreateDto.getOpenQuestions()) {
        Question openQuestion = getOrCreateQuestion(openQuestionDto, countDepth);
        openQuestionIdList.add(openQuestion.getId());
      }
    }

    Answer answer;
    if (answerCreateDto.getId() == null) {
      answer = AnswerMapper.fromDto(answerCreateDto, openQuestionIdList);
      answerDao.persist(answer);
    } else {
      answer = answerDao.findById(answerCreateDto.getId())
          .orElseThrow(() -> new HttpBadRequestException("Указанный ответ не найден."));
    }
    return answer;
  }

}
