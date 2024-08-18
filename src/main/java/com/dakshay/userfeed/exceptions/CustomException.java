package com.dakshay.userfeed.exceptions;

import lombok.Getter;

import java.io.Serial;
import java.io.Serializable;

@Getter
public class CustomException extends RuntimeException implements Serializable{

  @Serial
  private static final long serialVersionUID = 3882501414944821995L;

  private final transient CentralError centralError;
  private final transient Object[] args;

  public CustomException(CentralError error) {
    super();
    this.centralError = error;
    this.args = null;
  }

  public CustomException(CentralError centralError, Throwable cause) {
    super(centralError.getMessageKey(), cause);
    this.centralError = centralError;
    this.args = null;
  }

  public CustomException(CentralError centralError, Object[] args, Throwable cause) {
    super(centralError.getMessageKey(), cause);
    this.centralError = centralError;
    this.args = args;
  }

  public CustomException(CentralError centralError, Object ... args) {
    super(centralError.getMessageKey());
    this.centralError = centralError;
    this.args = args;
  }

  public CustomException(CentralError centralError, Throwable cause, Object ... args) {
    super(centralError.getMessageKey(), cause);
    this.centralError = centralError;
    this.args = args;
  }

}
