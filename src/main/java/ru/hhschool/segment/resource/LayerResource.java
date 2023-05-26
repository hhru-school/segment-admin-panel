package ru.hhschool.segment.resource;

import java.util.List;
import java.util.Optional;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import ru.hhschool.segment.model.dto.basicinfo.LayerBasicInfoDto;
import ru.hhschool.segment.model.dto.viewsegments.LayerSegmentsDto;
import ru.hhschool.segment.model.dto.viewsegments.SegmentViewDto;
import ru.hhschool.segment.service.LayerService;
import ru.hhschool.segment.service.SegmentService;

@Path("/layers")
public class LayerResource {
  private final LayerService layerService;
  private final SegmentService segmentService;

  @Inject
  public LayerResource(LayerService layerService, SegmentService segmentService) {
    this.layerService = layerService;
    this.segmentService = segmentService;
  }

  @GET
  @Path(value = "/")
  @Produces(MediaType.APPLICATION_JSON)
  public Response getLayerDtoListForMainPage() {
    return Response.ok(layerService.getLayerDtoListForMainPage()).build();
  }

  @GET
  @Path(value = "/{layerId}")
  @Produces(MediaType.APPLICATION_JSON)
  public Response getLayerDtoListForBasicInfoPage(@PathParam("layerId") Long layerId) {
    Optional<LayerBasicInfoDto> layerBasicInfoDto = layerService.getLayerDtoForBasicInfoPage(layerId);
    if (layerBasicInfoDto.isPresent()) {
      return Response.ok(layerBasicInfoDto).build();
    }
    return Response.status(Response.Status.NOT_FOUND).build();
  }

  @GET
  @Path("/{layerId}/segments")
  @Produces(MediaType.APPLICATION_JSON)
  public Response getSegmentViewDtoListForAllLayerSegmentsPage(@PathParam("layerId") Long layerId){
    Optional<LayerSegmentsDto> layerSegmentsDto = segmentService.getSegmentViewDtoListForSegmentsInLayerPage(layerId);
    if (layerSegmentsDto.isPresent()){
      return Response.ok(layerSegmentsDto.get()).build();
    }
    return Response.status(Response.Status.NO_CONTENT).build();
  }

  @GET
  @Path(value = "/{layerId}/merge")
  @Produces(MediaType.APPLICATION_JSON)
  public Response joinLayer(@PathParam(value = "layerId") Long layerId) {
//    try {
//      Optional<LayerChangeDto> layerChanges = layerService.mergeLayerWithParent(layerId);
//
//      if (layerChanges.isPresent() && layerChanges.get().isConflict()) {
//        return Response
//            .status(Response.Status.CONFLICT)
//            .entity(layerChanges.get())
//            .build();
//      }
//      return Response.ok(layerChanges.get()).build();
//
//    } catch (
//        NotFoundException e) {
//      return Response
//          .status(Response.Status.NOT_FOUND)
//          .entity(e.getMessage())
//          .build();
//
//    } catch (
//        IllegalStateException e) {
//      return Response.status(Response.Status.METHOD_NOT_ALLOWED)
//          .entity(e.getMessage())
//          .build();
//    }
    return Response.ok("TEST").build();

  }

}