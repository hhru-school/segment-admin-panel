package ru.hhschool.segment.exception;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import ru.hhschool.segment.model.dto.ErrorDto;

public class HttpNotFoundException extends WebApplicationException {
  private static final Response.Status responseStatus = Response.Status.NOT_FOUND;

  public HttpNotFoundException(String message) {
    super(
        message,
        Response.status(responseStatus).entity(
            new ErrorDto(responseStatus.getStatusCode(), message)
        ).build()
    );
  }
}
