package ru.hhschool.segment.resource;

import ru.hhschool.segment.service.QuestionService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
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
  @Path(value = "/{layerId}")
  @Produces(MediaType.APPLICATION_JSON)
  public Response getQuestionDtoListWithAnswers(@PathParam("layerId") Long id) {
    return Response.ok(questionService.getSetQuestionDtoOfLayerAndParentsWithAnswers(id)).build();
  }

  @GET
  @Path(value = "/info/{layerId}/{questionId}")
  @Produces(MediaType.APPLICATION_JSON)
  public Response getQuestionDtoInfoWithAnswers(@PathParam("layerId") Long layerId,
                                                @PathParam("questionId") Long questionId) {
    return Response.ok().build();
  }
}
