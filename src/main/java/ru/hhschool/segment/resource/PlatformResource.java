package ru.hhschool.segment.resource;

import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import ru.hhschool.segment.model.dto.platform.PlatformVersionListDto;
import ru.hhschool.segment.service.PlatformService;

@Path("/platforms")
public class PlatformResource {
  private final PlatformService platformService;

  @Inject
  public PlatformResource(PlatformService platformService) {
    this.platformService = platformService;
  }

  @GET
  @Path("/")
  @Produces(MediaType.APPLICATION_JSON)
  public Response getAllPlatforms() {
    List<PlatformVersionListDto> platformVersionListDtoList = platformService.getAll();
    if (!platformVersionListDtoList.isEmpty()) {
      return Response.ok(platformVersionListDtoList).build();
    }
    return Response.status(Response.Status.NO_CONTENT).build();
  }

}
