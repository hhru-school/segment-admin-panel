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
import ru.hhschool.segment.HttpBadRequestException;
import ru.hhschool.segment.model.dto.screen.ScreenDto;
import ru.hhschool.segment.model.dto.screen.ScreenPlatformDto;
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
  public Response getAllScreens() {
    List<ScreenDto> screenDtoList = screenService.getAll();
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
    List<ScreenPlatformDto> screenPlatformList = screenService.getAllPlatforms();
    if (!screenPlatformList.isEmpty()) {
      return Response.ok(screenPlatformList).build();
    }
    return Response.status(Response.Status.NO_CONTENT).build();
  }

}
