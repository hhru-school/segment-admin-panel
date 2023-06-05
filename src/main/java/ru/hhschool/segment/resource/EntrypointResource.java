package ru.hhschool.segment.resource;

import java.util.List;
import java.util.Optional;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.springframework.web.bind.annotation.RequestBody;
import ru.hhschool.segment.exception.HttpBadRequestException;
import ru.hhschool.segment.exception.HttpNotFoundException;
import ru.hhschool.segment.model.dto.EntrypointCreateDto;
import ru.hhschool.segment.model.dto.EntrypointDto;
import ru.hhschool.segment.model.dto.ErrorDto;
import ru.hhschool.segment.service.EntrypointService;

@Path("/entrypoints")
public class EntrypointResource {
  private final EntrypointService entrypointService;

  @Inject
  public EntrypointResource(EntrypointService entrypointService) {
    this.entrypointService = entrypointService;
  }

  @GET
  @Path(value = "/")
  @Produces(MediaType.APPLICATION_JSON)
  public Response getAllEntrypoint() {
    List<EntrypointDto> entrypointList = entrypointService.getAllEntrypoint();
    if (entrypointList.isEmpty()) {
      return Response.status(Response.Status.NO_CONTENT).build();
    }
    return Response.ok(entrypointList).build();
  }

  @GET
  @Path(value = "/{entrypointId}")
  @Produces(MediaType.APPLICATION_JSON)
  public Response getEntrypointById(@PathParam("entrypointId") Long entrypointId) {
    Optional<EntrypointDto> entrypointDto = entrypointService.getEntrypointById(entrypointId);
    return Response.ok(entrypointDto.orElseThrow(() -> new HttpNotFoundException("entrypointID not found."))).build();
  }

  @POST
  @Path("/")
  @Produces(MediaType.APPLICATION_JSON)
  public Response addEntrypoint(@RequestBody EntrypointCreateDto entrypointCreateDto) {
    if (entrypointCreateDto == null) {
      throw new HttpBadRequestException("Отсутствует необходимый параметр");
    }
    Optional<EntrypointDto> entrypointDto = entrypointService.add(entrypointCreateDto);
    if (entrypointDto.isPresent()) {
      return Response.ok(entrypointDto.get()).build();
    }
    return Response.status(Response.Status.BAD_REQUEST).entity(new ErrorDto("Не удалось создать.")).build();
  }

}