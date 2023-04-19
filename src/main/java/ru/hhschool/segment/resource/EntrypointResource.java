package ru.hhschool.segment.resource;

import java.util.Set;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import ru.hhschool.segment.model.dto.EntrypointDto;
import ru.hhschool.segment.service.EntrypointService;

@Path("/entrypoints")
public class EntrypointResource {
  private final EntrypointService entrypointService;

  @Inject
  public EntrypointResource(EntrypointService entrypointService) {
    this.entrypointService = entrypointService;
  }

  @GET
  @Path(value = "/{layerId}")
  @Produces(MediaType.APPLICATION_JSON)
  public Response getAllEntrypointByLayerId(@PathParam("layerId") Long id) {
    Set<EntrypointDto> entrypointList = entrypointService.getAllEntrypointByLayerId(id);
    return Response.ok(entrypointList).build();
  }


}