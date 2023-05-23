package ru.hhschool.segment.resource;

import java.util.List;
import java.util.Optional;
import javax.inject.Inject;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.springframework.web.bind.annotation.RequestBody;
import ru.hhschool.segment.HttpBadRequestException;
import ru.hhschool.segment.model.dto.ErrorDto;
import ru.hhschool.segment.model.dto.segment.SegmentCreateDto;
import ru.hhschool.segment.model.dto.segment.SegmentDto;
import ru.hhschool.segment.service.SegmentService;

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
  public Response getAllSegments(@QueryParam("searchQuery") @DefaultValue("") String searchQuery) {
    List<SegmentDto> segmentViewDtoList = segmentService.getAll(searchQuery);
    if (!segmentViewDtoList.isEmpty()) {
      return Response.ok(segmentViewDtoList).build();
    }
    return Response.status(Response.Status.NO_CONTENT).build();
  }

  @POST
  @Path("/")
  @Produces(MediaType.APPLICATION_JSON)
  public Response addSegments(@RequestBody Optional<SegmentCreateDto> segmentCreateDtoOptional) {
    SegmentCreateDto segmentCreateDto = segmentCreateDtoOptional.orElseThrow(
        () -> new HttpBadRequestException("Отсутствует необходимый параметр")
    );

    Optional<SegmentDto> segmentDto = segmentService.add(segmentCreateDto);
    if (segmentDto.isPresent()) {
      return Response.ok(segmentDto.get()).build();
    }
    return Response.status(Response.Status.BAD_REQUEST).entity(new ErrorDto("Не удалось создать.")).build();
  }

  @GET
  @Path("/{segmentId}")
  @Produces(MediaType.APPLICATION_JSON)
  public Response getSegmentById(@PathParam("segmentId") Optional<Long> segmentIdOptional) {
    long segmentId = segmentIdOptional.orElseThrow(
        () -> new HttpBadRequestException("Отсутствует необходимый параметр")
    );

    Optional<SegmentDto> segmentDto = segmentService.getById(segmentId);
    if (segmentDto.isPresent()) {
      return Response.ok(segmentDto.get()).build();
    }

    return Response.status(Response.Status.NOT_FOUND).build();
  }

  @GET
  @Path("/details")
  @Produces(MediaType.APPLICATION_JSON)
  public Response getSegmentViewDtoListForViewSegmentPage(@QueryParam("layerId") Long layerId, @QueryParam("segmentId") Long segmentId) {
//    Optional<SegmentSelectedDto> segmentSelectedDto = segmentService.getSegmentSelectedDto(layerId, segmentId);
//    if (segmentSelectedDto.isPresent()){
//      return Response.ok(segmentSelectedDto).build();
//    }
//    return Response.status(Response.Status.NO_CONTENT).build();
    return Response.ok("TEST").build();
  }
}
