package com.lucasricardo1.cardschallenge;

import com.lucasricardo1.cardschallenge.client.ShuffleCardsClient;
import com.lucasricardo1.cardschallenge.dto.response.ShuffleCardsResponse;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class CardsChallengeApplication implements CommandLineRunner {

	private final ShuffleCardsClient shuffleCardsClient;
	public CardsChallengeApplication(ShuffleCardsClient shuffleCardsClient) {
		this.shuffleCardsClient = shuffleCardsClient;
	}

	public static void main(String[] args) {
		SpringApplication.run(CardsChallengeApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		ShuffleCardsResponse shuffleCards = this.shuffleCardsClient.shuffleCards();
		System.out.println(shuffleCards);
	}

}
