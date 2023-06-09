package ru.hhschool.segment.util;

import ru.hhschool.segment.exception.HttpBadRequestException;

public class ExceptionMessageExtract {
  public static void extractStackErrors(Exception err) {
    StringBuilder lastMessage = new StringBuilder(err.getMessage());
    Throwable cause = err.getCause();
    while (cause != null) {
      lastMessage.append(" \n").append(cause.getMessage());
      cause = cause.getCause();
    }
    throw new HttpBadRequestException(lastMessage.toString());
  }
}
