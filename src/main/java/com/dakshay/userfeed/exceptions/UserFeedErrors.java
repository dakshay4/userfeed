  package com.dakshay.userfeed.exceptions;

  import lombok.Getter;

  import java.io.Serializable;

  @Getter
  public enum UserFeedErrors implements CentralError, Serializable {

    USER_NOT_FOUND(ErrorType.RUNTIME,"user.not.found" ),
    POST_NOT_FOUND(ErrorType.RUNTIME,"post.not.found" ),
    COMMENT_NOT_FOUND(ErrorType.RUNTIME,"comment.not.found" ),
    INVALID_REQUEST(ErrorType.RUNTIME,"invalid.request" );
    private final ErrorType errorType;
    private final String messageKey;

    UserFeedErrors(ErrorType errorType, String messageKey) {
      this.errorType = errorType;
      this.messageKey = messageKey;
    }

  }
