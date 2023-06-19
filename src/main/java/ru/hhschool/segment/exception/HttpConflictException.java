package ru.hhschool.segment.exception;

import ru.hhschool.segment.model.dto.merge.MergeResponseDto;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

public class HttpConflictException extends WebApplicationException {
  private static final Response.Status responseStatus = Response.Status.CONFLICT;

  public HttpConflictException(String message, MergeResponseDto mergeResponseDto) {
    super(
        message,
        Response.status(responseStatus).entity(
            mergeResponseDto
        ).build()
    );
  }
}
