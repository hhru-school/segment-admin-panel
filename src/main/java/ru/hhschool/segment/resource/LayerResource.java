package ru.hhschool.segment.resource;

import java.util.List;
import java.util.Optional;
import javax.inject.Inject;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.PATCH;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import ru.hhschool.segment.model.dto.LayerDto;
import ru.hhschool.segment.model.dto.basicinfo.LayerBasicInfoDto;
import ru.hhschool.segment.model.dto.layer.LayerDtoForList;
import ru.hhschool.segment.model.dto.createlayer.CreateLayerSegmentDto;
import ru.hhschool.segment.model.dto.viewsegments.layerview.LayerSegmentsDto;
import ru.hhschool.segment.model.dto.viewsegments.layerview.SegmentSelectedDto;
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
  public Response getLayerGroupList() {
    List<LayerDto> layerDtoList = layerService.getLayerGroupList();
    if (layerDtoList.isEmpty()) {
      return Response.status(Response.Status.NO_CONTENT).build();
    }
    return Response.ok(layerDtoList).build();
  }

  @GET
  @Path(value = "/list")
  @Produces(MediaType.APPLICATION_JSON)
  public Response getLayerFilteredList(@QueryParam("state") List<String> layerStringStateTypes) {
    List<LayerDtoForList> layerDtoList = layerService.getAll(layerStringStateTypes);
    if (layerDtoList.isEmpty()) {
      return Response.status(Response.Status.NO_CONTENT).build();
    }
    return Response.ok(layerDtoList).build();
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
  public Response getSegmentViewDtoListForAllLayerSegmentsPage(
      @PathParam("layerId") Long layerId,
      @QueryParam("searchQuery") @DefaultValue("") String searchQuery
  ) {
    Optional<LayerSegmentsDto> layerSegmentsDto = segmentService.getSegmentViewDtoListForSegmentsInLayerPage(layerId, searchQuery);
    if (layerSegmentsDto.isPresent()) {
      return Response.ok(layerSegmentsDto.get()).build();
    }
    return Response.status(Response.Status.NO_CONTENT).build();
  }

  @GET
  @Path("/{layerId}/segments/{segmentId}")
  @Produces(MediaType.APPLICATION_JSON)
  public Response getSegmentViewDtoListForViewSegmentPage(@PathParam("layerId") Long layerId, @PathParam("segmentId") Long segmentId){
    Optional<SegmentSelectedDto> segmentSelectedDto = segmentService.getSegmentSelectedDto(layerId, segmentId);
    if (segmentSelectedDto.isPresent()){
      return Response.ok(segmentSelectedDto).build();
    }
    return Response.status(Response.Status.NOT_FOUND).build();
  }

  @GET
  @Path("/{layerId}/segments/{segmentId}/details")
  @Produces(MediaType.APPLICATION_JSON)
  public Response getCreateLayerSegmentDto(@PathParam("layerId") Long layerId, @PathParam("segmentId") Long segmentId){
    Optional<CreateLayerSegmentDto> createLayerSegmentDto = segmentService.getCreateLayerSegmentDto(layerId, segmentId);
    if (createLayerSegmentDto.isPresent()){
      return Response.ok(createLayerSegmentDto).build();
    }
    return Response.status(Response.Status.NOT_FOUND).build();
  }

  @PATCH
  @Path("/{layerId}/setArchive/")
  @Produces(MediaType.APPLICATION_JSON)
  public Response setLayerStateToArchive(@PathParam(value = "layerId") Long layerId) {
    layerService.setLayerStateToArchive(layerId);
    return Response.ok("Статус успешно изменен.").build();
  }

  @GET
  @Path(value = "/{layerId}/merge")
  @Produces(MediaType.APPLICATION_JSON)
  public Response joinLayer(@PathParam(value = "layerId") Long layerId) {
    return Response.ok("TEST").build();
  }

}