package ru.hhschool.segment.resource;

import java.util.Optional;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import ru.hhschool.segment.model.dto.change.LayerChangeDto;
import ru.hhschool.segment.service.LayerService;

@Path("/layers")
public class LayerResource {
  private final LayerService layerService;

  @Inject
  public LayerResource(LayerService layerService) {
    this.layerService = layerService;
  }

  @GET
  @Path(value = "/changes/{layerId}")
  @Produces(MediaType.APPLICATION_JSON)
  public Response getLayerChanges(@PathParam(value = "layerId") Long layerId) {
    Optional<LayerChangeDto> layerChanges = layerService.getLayerChanges(layerId);

    if (layerChanges.isPresent()) {
      return Response.ok(layerChanges.get()).build();
    }

    return Response.status(Response.Status.BAD_REQUEST).build();
  }
}
