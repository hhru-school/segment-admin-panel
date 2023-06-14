package ru.hhschool.segment.model.dto.createlayer.validate;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ValidateResultDto<T> {
  private String error;
  private T result;

  public ValidateResultDto(){};

  public ValidateResultDto(String error, T result) {
    this.error = error;
    this.result = result;
  }

  public String getError() {
    return error;
  }

  public void setError(String error) {
    this.error = error;
  }

  public T getResult() {
    return result;
  }

  public void setResult(T result) {
    this.result = result;
  }
}
