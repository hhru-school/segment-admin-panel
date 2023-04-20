package ru.hhschool.segment.resource;

import ru.hhschool.segment.model.dto.EntrypointDto;
import ru.hhschool.segment.model.dto.entrypointinfo.EntrypointWitchQuestionStatusDto;
import ru.hhschool.segment.service.EntrypointService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Optional;
import java.util.Set;

@Path("/entrypoints")
public class EntrypointResource {
  private final EntrypointService entrypointService;

  @Inject
  public EntrypointResource(EntrypointService entrypointService) {
    this.entrypointService = entrypointService;
  }

  @GET
  @Path(value = "/layer/{layerId}")
  @Produces(MediaType.APPLICATION_JSON)
  public Response getAllEntrypointByLayerId(@PathParam("layerId") Long layerId) {
    Set<EntrypointDto> entrypointList = entrypointService.getAllEntrypointByLayerId(layerId);

    return Response.ok(entrypointList).build();
  }

  @GET
  @Path(value = "/{entrypointId}/layer/{layerId}")
  @Produces(MediaType.APPLICATION_JSON)
  public Response getEntrypointByIdWithQuestionStatus(@PathParam("entrypointId") Long entrypointId, @PathParam("layerId") Long layerId) {
    Optional<EntrypointWitchQuestionStatusDto> entrypointWithQuestionStatus = entrypointService.getEntrypointByIdWithQuestionStatus(
        entrypointId,
        layerId
    );

    if (entrypointWithQuestionStatus.isPresent()) {
      return Response.ok(entrypointWithQuestionStatus).build();
    }

    return Response.status(Response.Status.NOT_FOUND).build();
  }

}