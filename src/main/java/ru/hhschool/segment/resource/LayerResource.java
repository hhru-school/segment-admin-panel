package ru.hhschool.segment.resource;

import java.util.List;
import java.util.Optional;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import ru.hhschool.segment.model.dto.LayerDto;
import ru.hhschool.segment.model.dto.basicInfo.LayerBasicInfoDto;
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
  @Path(value = "/")
  @Produces(MediaType.APPLICATION_JSON)
  public Response getLayerDtoListForMainPage() {
    List<LayerDto> layerDtos = layerService.getLayerDtoListForMainPage();
    if (layerDtos.isEmpty()) {
      return Response.status(Response.Status.NO_CONTENT).build();
    }
    return Response.ok(layerDtos).build();
  }

  @GET
  @Path(value = "/{layerId}")
  @Produces(MediaType.APPLICATION_JSON)
  public Response getLayerDtoListForBasicInfoPage(@PathParam("layerId") Long id) {
    Optional<LayerBasicInfoDto> layerBasicInfoDto = layerService.getLayerDtoForBasicInfoPage(id);
    if (layerBasicInfoDto.isPresent()) {
      return Response.ok(layerBasicInfoDto).build();
    }
    return Response.status(Response.Status.NOT_FOUND).build();
  }

  @GET
  @Path(value = "/{layerId}/changes")
  @Produces(MediaType.APPLICATION_JSON)
  public Response getLayerChanges(@PathParam(value = "layerId") Long layerId) {
    Optional<LayerChangeDto> layerChanges = layerService.getLayerChanges(layerId);

    if (layerChanges.isPresent()) {
      return Response.ok(layerChanges.get()).build();
    }
    return Response.status(Response.Status.NOT_FOUND).build();
  }

  @GET
  @Path(value = "/{layerId}/merge")
  @Produces(MediaType.APPLICATION_JSON)
  public Response joinLayer(@PathParam(value = "layerId") Long layerId) {
    try {
      Optional<LayerChangeDto> layerChanges = layerService.mergeLayerWithParent(layerId);

      if (layerChanges.isPresent() && layerChanges.get().isConflict()) {
        return Response
            .status(Response.Status.CONFLICT)
            .entity(layerChanges.get())
            .build();
      }
      return Response.ok(layerChanges.get()).build();

    } catch (
        NotFoundException e) {
      return Response
          .status(Response.Status.NOT_FOUND)
          .entity(e.getMessage())
          .build();

    } catch (
        IllegalStateException e) {
      return Response.status(Response.Status.METHOD_NOT_ALLOWED)
          .entity(e.getMessage())
          .build();
    }

  }

}