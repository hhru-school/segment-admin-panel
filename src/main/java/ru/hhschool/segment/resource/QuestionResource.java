package ru.hhschool.segment.resource;

import ru.hhschool.segment.model.dto.questioninfopage.QuestionDtoForQuestionsInfo;
import ru.hhschool.segment.service.QuestionService;

import javax.inject.Inject;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/questions")
public class QuestionResource {
  private final QuestionService questionService;

  @Inject
  public QuestionResource(QuestionService questionService) {
    this.questionService = questionService;
  }

  @GET
  @Path(value = "/")
  @Produces(MediaType.APPLICATION_JSON)
  public Response getQuestionDtoListWithAnswers(@QueryParam("searchQuery") @DefaultValue("") String searchQuery,
                                                @QueryParam("type") List<String> questionTypeStringList) {
    List<QuestionDtoForQuestionsInfo> questionDtoList = questionService.getAllQuestionDtoListForQuestionsInfo(searchQuery, questionTypeStringList);
    if (!questionDtoList.isEmpty()) {
      return Response.ok(questionDtoList).build();
    }
    return Response.status(Response.Status.NO_CONTENT).build();
  }

  @GET
  @Path(value = "/{id}")
  @Produces(MediaType.APPLICATION_JSON)
  public Response getQuestionDtoInfoWithAnswers(@PathParam("id") Long questionId) {
    return Response.ok(questionService.getQuestionDtoForQuestionInfo(questionId)).build();
  }
}
