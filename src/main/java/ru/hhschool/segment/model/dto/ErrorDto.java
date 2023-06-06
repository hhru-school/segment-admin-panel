package ru.hhschool.segment.model.dto;

public class ErrorDto {

  private int responseCode;
  private final String message;

  public ErrorDto(int responseCode, String message) {
    this.responseCode = responseCode;
    this.message = message;
  }

  public ErrorDto(String message) {
    this.message = message;
  }

  public int getResponseCode() {
    return responseCode;
  }

  public String getMessage() {
    return message;
  }
}
