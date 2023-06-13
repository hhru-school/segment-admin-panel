package ru.hhschool.segment.model.dto.createlayer.validate;

import java.util.List;

public class ValidateResultDto<T> {
  private String error;
  private List<T> results;

  public ValidateResultDto(){};

  public ValidateResultDto(String error, List<T> results) {
    this.error = error;
    this.results = results;
  }

  public String getError() {
    return error;
  }

  public void setError(String error) {
    this.error = error;
  }

  public List<T> getResults() {
    return results;
  }

  public void setResults(List<T> results) {
    this.results = results;
  }
}
