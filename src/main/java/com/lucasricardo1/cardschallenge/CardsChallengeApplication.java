package com.lucasricardo1.cardschallenge;

import com.lucasricardo1.cardschallenge.db.entities.ShuffleEntity;
import com.lucasricardo1.cardschallenge.dto.ShuffleDTO;
import com.lucasricardo1.cardschallenge.services.CardsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
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
public class CardsChallengeApplication implements CommandLineRunner {

	@Autowired
	private CardsService cardsService;

	public static void main(String[] args) {
		SpringApplication.run(CardsChallengeApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		ShuffleDTO shuffleDTO = cardsService.shuffleDeck();
		cardsService.saveDecks(shuffleDTO.getDeckId());
		cardsService.pickWinner();
	}

}
