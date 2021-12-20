package com.example.Like_and_Unlike_feature;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LikeAndUnlikeFeatureApplication {
	private static final Logger LOGGER= LoggerFactory.getLogger(LikeAndUnlikeFeatureApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(LikeAndUnlikeFeatureApplication.class, args);
//		LOGGER.info("Message Logged at INFO level");
//		LOGGER.error("Message logged at ERROR level");
//		LOGGER.warn("Message logged at WARN level");
//		LOGGER.debug("Message logged at DEBUG level");

	}

}
