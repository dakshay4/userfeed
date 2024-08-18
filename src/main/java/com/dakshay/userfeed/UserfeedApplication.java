package com.dakshay.userfeed;

import com.dakshay.userfeed.exceptions.UserFeedErrorSource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class UserfeedApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserfeedApplication.class, args);
	}


	@Bean
	public MessageSource messageSource() {
		return new UserFeedErrorSource();
	}

}
