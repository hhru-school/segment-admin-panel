package ru.hhschool.segment.resource;

import ru.hhschool.segment.model.dto.viewsegments.SegmentSelectedDto;
import ru.hhschool.segment.model.dto.viewsegments.SegmentViewDto;
import ru.hhschool.segment.service.SegmentService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;

@Path("/segments")
public class SegmentResource {
  private final SegmentService segmentService;

  @Inject
  public SegmentResource(SegmentService segmentService) {
    this.segmentService = segmentService;
  }

  @GET
  @Path("/{layerId}")
  @Produces(MediaType.APPLICATION_JSON)
  public Response getSegmentViewDtoListForAllSegmentsPage(@PathParam("layerId") Long layerId){
    List<SegmentViewDto> segmentViewDtoList = segmentService.getSegmentViewDtoListForAllSegmentsPage(layerId);
    if (!segmentViewDtoList.isEmpty()){
      return Response.ok(segmentViewDtoList).build();
    }
    return Response.status(Response.Status.NO_CONTENT).build();
  }

  @GET
  @Path("/{layerId}/{segmentId}")
  @Produces(MediaType.APPLICATION_JSON)
  public Response getSegmentViewDtoListForViewSegmentPage(@PathParam("layerId") Long layerId, @PathParam("segmentId") Long segmentId){
    Optional<SegmentSelectedDto> segmentSelectedDto = segmentService.getSegmentSelectedDto(layerId, segmentId);
    if (segmentSelectedDto.isPresent()){
      return Response.ok(segmentSelectedDto).build();
    }
    return Response.status(Response.Status.NO_CONTENT).build();
  }
}
