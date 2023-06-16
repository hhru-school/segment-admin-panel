package ru.hhschool.segment.model.dto.createlayer.validate;

import com.fasterxml.jackson.annotation.JsonInclude;
import ru.hhschool.segment.model.dto.createlayer.validate.enums.ErrorType;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ValidateResultDto<T> {
  private String error;
  private ErrorType errorType;
  private T result;

  public ValidateResultDto(){};

  public ValidateResultDto(String error, ErrorType errorType, T result) {
    this.error = error;
    this.result = result;
  }

  public String getError() {
    return error;
  }

  public void setError(String error) {
    this.error = error;
  }

  public ErrorType getErrorType() {
    return errorType;
  }

  public void setErrorType(ErrorType errorType) {
    this.errorType = errorType;
  }

  public T getResult() {
    return result;
  }

  public void setResult(T result) {
    this.result = result;
  }
}
