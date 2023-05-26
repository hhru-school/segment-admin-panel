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
import ru.hhschool.segment.model.dto.RoleDto;
import ru.hhschool.segment.service.RoleService;

@Path("/roles")
public class RoleResource {
  private final RoleService roleService;

  @Inject
  public RoleResource(RoleService roleService) {
    this.roleService = roleService;
  }

  @GET
  @Path("/")
  @Produces(MediaType.APPLICATION_JSON)
  public Response getAllRoles() {
    List<RoleDto> roleDtoList = roleService.getAll();
    return Response.ok(roleDtoList).build();
  }

  @GET
  @Path("/{roleId}")
  @Produces(MediaType.APPLICATION_JSON)
  public Response getRoleById(@PathParam(value = "roleId") Long roleId) {
    if (roleId == null) {
      throw new HttpBadRequestException("Отсутствует необходимый параметр");
    }

    Optional<RoleDto> roleDto = roleService.getRoleById(roleId);
    if (roleDto.isPresent()) {
      return Response.ok(roleDto.get()).build();
    }

    return Response.status(Response.Status.NOT_FOUND).build();
  }
}
