package com.lucasricardo1.cardschallenge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableFeignClients
@EnableScheduling
@ComponentScan(basePackages = {"com.lucasricardo1.cardschallenge"})
@EnableAutoConfiguration
@SpringBootApplication
public class CardsChallengeApplication {

	public static void main(String[] args) {
		SpringApplication.run(CardsChallengeApplication.class, args);
	}

}
