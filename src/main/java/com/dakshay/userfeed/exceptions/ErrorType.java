package com.dakshay.userfeed.exceptions;

import org.springframework.http.HttpStatus;

public enum ErrorType {

  VALIDATION(HttpStatus.INTERNAL_SERVER_ERROR), AUTHENTICATION(HttpStatus.UNAUTHORIZED), GATEWAY_TIMEOUT(
      HttpStatus.GATEWAY_TIMEOUT), RUNTIME(HttpStatus.INTERNAL_SERVER_ERROR), DATABASE_FAILURE(
          HttpStatus.INTERNAL_SERVER_ERROR), INTERNAL_SERVICE_CALL(HttpStatus.INTERNAL_SERVER_ERROR);

  private HttpStatus statusCode;

  ErrorType(HttpStatus statusCode) {
    this.statusCode = statusCode;
  }

  public HttpStatus getStatusCode() {
    return statusCode;
  }
}
