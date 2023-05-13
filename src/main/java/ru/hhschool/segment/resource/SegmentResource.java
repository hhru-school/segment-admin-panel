package ru.hhschool.segment.resource;

import ru.hhschool.segment.service.SegmentService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/segments")
public class SegmentResource {
  private final SegmentService segmentService;

  @Inject
  public SegmentResource(SegmentService segmentService) {
    this.segmentService = segmentService;
  }

  @GET
  @Path("/")
  @Produces(MediaType.APPLICATION_JSON)
  public Response getSegmentViewDtoListForAllSegmentsPage(@QueryParam("layerId") Long layerId){
//    List<SegmentViewDto> segmentViewDtoList = segmentService.getSegmentViewDtoListForAllSegmentsPage(layerId);
//    if (!segmentViewDtoList.isEmpty()){
//      return Response.ok(segmentViewDtoList).build();
//    }
//    return Response.status(Response.Status.NO_CONTENT).build();
    return Response.ok("TEST").build();
  }

  @GET
  @Path("/details")
  @Produces(MediaType.APPLICATION_JSON)
  public Response getSegmentViewDtoListForViewSegmentPage(@QueryParam("layerId") Long layerId, @QueryParam("segmentId") Long segmentId){
//    Optional<SegmentSelectedDto> segmentSelectedDto = segmentService.getSegmentSelectedDto(layerId, segmentId);
//    if (segmentSelectedDto.isPresent()){
//      return Response.ok(segmentSelectedDto).build();
//    }
//    return Response.status(Response.Status.NO_CONTENT).build();
    return Response.ok("TEST").build();
  }
}
