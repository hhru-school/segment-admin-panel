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
import ru.hhschool.segment.exception.HttpBadRequestException;
import ru.hhschool.segment.model.dto.ErrorDto;
import ru.hhschool.segment.model.dto.screen.ScreenCreateDto;
import ru.hhschool.segment.model.dto.screen.ScreenDto;
import ru.hhschool.segment.model.dto.screen.ScreenPlatformVersionDto;
import ru.hhschool.segment.service.ScreenService;

@Path("/screens")
public class ScreenResource {
  private final ScreenService screenService;

  @Inject
  public ScreenResource(ScreenService screenService) {
    this.screenService = screenService;
  }

  @GET
  @Path("/")
  @Produces(MediaType.APPLICATION_JSON)
  public Response getAllScreens(
      @QueryParam("androidId") Long androidId,
      @QueryParam("iosId") Long iosId,
      @QueryParam("web") @DefaultValue("false") boolean webSelect
  ) {
    List<ScreenDto> screenDtoList = screenService.getAll(androidId, iosId, webSelect);
    if (!screenDtoList.isEmpty()) {
      return Response.ok(screenDtoList).build();
    }
    return Response.status(Response.Status.NO_CONTENT).build();
  }

  @GET
  @Path("/{screenId}")
  @Produces(MediaType.APPLICATION_JSON)
  public Response getSegmentViewDtoListForViewSegmentPage(@PathParam(value = "screenId") Long screenId) {
    if (screenId == null) {
      throw new HttpBadRequestException("Отсутствует необходимый параметр");
    }

    Optional<ScreenDto> screenDto = screenService.getScreenById(screenId);
    if (screenDto.isPresent()) {
      return Response.ok(screenDto.get()).build();
    }
    return Response.status(Response.Status.NOT_FOUND).build();
  }

  @GET
  @Path("/platforms")
  @Produces(MediaType.APPLICATION_JSON)
  public Response getAllPlatformOfScreens() {
    List<ScreenPlatformVersionDto> screenPlatformList = screenService.getAllPlatforms();
    if (!screenPlatformList.isEmpty()) {
      return Response.ok(screenPlatformList).build();
    }
    return Response.status(Response.Status.NO_CONTENT).build();
  }

  @POST
  @Path("/")
  @Produces(MediaType.APPLICATION_JSON)
  public Response addScreen(@RequestBody ScreenCreateDto screenCreateDto) {
    if (screenCreateDto == null) {
      throw new HttpBadRequestException("Отсутствует необходимый параметр");
    }
    Optional<ScreenDto> segmentDto = screenService.add(screenCreateDto);
    if (segmentDto.isPresent()) {
      return Response.ok(segmentDto.get()).build();
    }
    return Response.status(Response.Status.BAD_REQUEST).entity(new ErrorDto("Не удалось создать.")).build();
  }
}
