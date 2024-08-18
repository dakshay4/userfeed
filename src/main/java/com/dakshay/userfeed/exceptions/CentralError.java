package com.dakshay.userfeed.exceptions;

import java.io.Serializable;

public interface CentralError extends Serializable {

  static final long serialVersionUID = 751832173588781223L;

  public ErrorType getErrorType();

  public String getMessageKey();
}
