package com.dakshay.userfeed.exceptions;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@ControllerAdvice
@RequiredArgsConstructor
public class CentralExceptionHandler {

  private final Logger logger = LoggerFactory.getLogger(getClass());

  private final MessageSource messageSource;

  @ExceptionHandler(CustomException.class)
  public ResponseEntity<ErrorHttpResponse> handleCustomException(CustomException ex, HttpServletRequest request) {
    Locale locale = Locale.ENGLISH;
    String localizedMessage = messageSource.getMessage(ex.getCentralError().getMessageKey(), ex.getArgs(), locale);
    localizedMessage = replaceUnreplacedPlaceholders(localizedMessage);
    HttpStatus statusCode = ex.getCentralError().getErrorType().getStatusCode();
    ErrorHttpResponse errorResponse = new ErrorHttpResponse(ex.getCentralError().getErrorType().name(), localizedMessage, System.currentTimeMillis(),
        request.getRequestURI(), ex.getCause()!=null ? ex.getCause().getMessage() : "");
    logger.error("{}", ex);
    return new ResponseEntity<>(errorResponse, statusCode);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorHttpResponse> handleException(Exception ex, HttpServletRequest request) {
    String cause = ex.getCause()!=null ? ex.getCause().getMessage() : null;
    ErrorHttpResponse errorResponse = new ErrorHttpResponse("Error", ex.getMessage(), System.currentTimeMillis(),
        request.getRequestURI(), cause);
    logger.error("Exception Handler -- {}", ex);

    return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  private String replaceUnreplacedPlaceholders(String message) {
    // Regular expression to match placeholders like {0}, {1}, etc.
    String regex = "\\{\\d+\\}";
    Pattern pattern = Pattern.compile(regex);
    Matcher matcher = pattern.matcher(message);

    // Replace all matches with "_"
    return matcher.replaceAll("_");
  }
}
