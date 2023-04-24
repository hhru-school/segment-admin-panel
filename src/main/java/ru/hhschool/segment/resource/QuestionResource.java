package ru.hhschool.segment.resource;

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
  public Response getQuestionDtoListWithAnswers(@QueryParam("layerId") Long layerId,
                                                @QueryParam("searchString") @DefaultValue("") String searchString) {
    return Response.ok(questionService.getSetQuestionDtoOfLayerAndParentsWithAnswers(layerId, searchString)).build();
  }

  @GET
  @Path(value = "/detail")
  @Produces(MediaType.APPLICATION_JSON)
  public Response getQuestionDtoInfoWithAnswers(@QueryParam("layerId") Long layerId,
                                                @QueryParam("questionId") Long questionId) {
    return Response.ok(questionService.сreateQuestionDtoWithAnswersAndStatus(layerId, questionId)).build();
  }
}
