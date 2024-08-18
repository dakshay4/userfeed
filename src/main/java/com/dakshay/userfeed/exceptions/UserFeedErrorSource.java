package com.dakshay.userfeed.exceptions;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

@Configuration
public class UserFeedErrorSource extends ResourceBundleMessageSource {

  public UserFeedErrorSource() {
    setBasename(getBaseName());
    setDefaultEncoding("UTF-8");
  }

  private static final String SERVICE_BASE_NAME = "userFeed";

  private String getBaseName() {
    return "error_" + SERVICE_BASE_NAME;
  }

}
