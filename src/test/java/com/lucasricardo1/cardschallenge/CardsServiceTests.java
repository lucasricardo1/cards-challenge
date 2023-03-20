package com.lucasricardo1.cardschallenge;

import com.lucasricardo1.cardschallenge.client.DrawCardsClient;
import com.lucasricardo1.cardschallenge.client.ShuffleCardsClient;
import com.lucasricardo1.cardschallenge.db.entities.HandsEntity;
import com.lucasricardo1.cardschallenge.db.entities.ShuffleEntity;
import com.lucasricardo1.cardschallenge.db.repositories.HandsRepository;
import com.lucasricardo1.cardschallenge.db.repositories.ShuffleRepository;
import com.lucasricardo1.cardschallenge.dto.ShuffleDTO;
import com.lucasricardo1.cardschallenge.dto.response.CardsResponse;
import com.lucasricardo1.cardschallenge.dto.response.DrawCardsResponse;
import com.lucasricardo1.cardschallenge.dto.response.DrawHandsResponse;
import com.lucasricardo1.cardschallenge.dto.response.ShuffleCardsResponse;
import com.lucasricardo1.cardschallenge.services.CardsService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.util.*;

@SpringBootTest
public class CardsServiceTests {

    @Mock
    private HandsRepository handsRepository;

    @Mock
    private ShuffleRepository shuffleRepository;

    @Mock
    DrawCardsClient drawCardsClient;

    @Mock
    ShuffleCardsClient shuffleCardsClient;

    @InjectMocks
    private CardsService cardsService;


    ShuffleCardsResponse shuffleCardsResponse =
            new ShuffleCardsResponse(true, "id", true, 52);
    ShuffleEntity shuffleEntity = new ShuffleEntity(true, "id", true, 52);
    ShuffleDTO shuffleDTO = new ShuffleDTO(true, "id", true, 52);

    List<CardsResponse> cards = new ArrayList<>();
    List<HandsEntity> handsEntities = new ArrayList<>();
    DrawCardsResponse drawCardsResponse = new DrawCardsResponse(true, "id", cards,32);

    @Test
    public void shuffleDeck(){
        Mockito.when(shuffleCardsClient.shuffleCards()).thenReturn(shuffleCardsResponse);
        Mockito.when(shuffleRepository.save(shuffleEntity)).thenReturn(shuffleEntity);

        Assertions.assertEquals(cardsService.shuffleDeck(), shuffleDTO);
    }

    @Test
    public void saveDecks(){
        Mockito.when(shuffleRepository.findById(shuffleDTO.getDeckId())).thenReturn(Optional.of(shuffleEntity));

        cards.add(new CardsResponse("1", "1", "1"));
        cards.add(new CardsResponse("1", "1", "1"));
        cards.add(new CardsResponse("1", "1", "1"));
        cards.add(new CardsResponse("1", "1", "1"));
        cards.add(new CardsResponse("1", "1", "1"));
        cards.add(new CardsResponse("1", "1", "1"));
        cards.add(new CardsResponse("1", "1", "1"));
        cards.add(new CardsResponse("1", "1", "1"));
        cards.add(new CardsResponse("1", "1", "1"));
        cards.add(new CardsResponse("1", "1", "1"));
        cards.add(new CardsResponse("1", "1", "1"));
        cards.add(new CardsResponse("1", "1", "1"));
        cards.add(new CardsResponse("1", "1", "1"));
        cards.add(new CardsResponse("1", "1", "1"));
        cards.add(new CardsResponse("1", "1", "1"));
        cards.add(new CardsResponse("1", "1", "1"));
        cards.add(new CardsResponse("1", "1", "1"));
        cards.add(new CardsResponse("1", "1", "1"));
        cards.add(new CardsResponse("1", "1", "1"));
        cards.add(new CardsResponse("1", "1", "1"));

        Mockito.when(drawCardsClient.drawCards(shuffleEntity.getDeckId(), 20)).thenReturn(drawCardsResponse);

        int index = 0;

        for (int i = 0; i < 4; i++) {

            handsEntities.add(index, new HandsEntity("Player " + (i + 1), "id", "1"));
            index++;

            handsEntities.add(index, new HandsEntity("Player " + (i + 1), "id", "1"));
            index++;

            handsEntities.add(index, new HandsEntity("Player " + (i + 1), "id", "1"));
            index++;

            handsEntities.add(index, new HandsEntity("Player " + (i + 1), "id", "1"));
            index++;

            handsEntities.add(index, new HandsEntity("Player " + (i + 1), "id", "1"));
            index++;


        }

        handsRepository.saveAll(handsEntities);

        Assertions.assertEquals(cardsService.saveDecks("id"), "Decks saved!");

    }

    @Test
    public void pickWinner_drawResult(){
        List<HandsEntity> handsEntities = new ArrayList<>();
        handsEntities.add(new HandsEntity("Player 1", "id", "1"));
        handsEntities.add(new HandsEntity("Player 1", "id", "1"));
        handsEntities.add(new HandsEntity("Player 1", "id", "1"));
        handsEntities.add(new HandsEntity("Player 1", "id", "1"));
        handsEntities.add(new HandsEntity("Player 1", "id", "1"));

        Mockito.when(handsRepository.findByPlayer("Player 1")).thenReturn(handsEntities);

        handsEntities = new ArrayList<>();
        handsEntities.add(new HandsEntity("Player 2", "id", "1"));
        handsEntities.add(new HandsEntity("Player 2", "id", "1"));
        handsEntities.add(new HandsEntity("Player 2", "id", "1"));
        handsEntities.add(new HandsEntity("Player 2", "id", "1"));
        handsEntities.add(new HandsEntity("Player 2", "id", "1"));

        Mockito.when(handsRepository.findByPlayer("Player 2")).thenReturn(handsEntities);

        handsEntities = new ArrayList<>();
        handsEntities.add(new HandsEntity("Player 3", "id", "1"));
        handsEntities.add(new HandsEntity("Player 3", "id", "1"));
        handsEntities.add(new HandsEntity("Player 3", "id", "1"));
        handsEntities.add(new HandsEntity("Player 3", "id", "1"));
        handsEntities.add(new HandsEntity("Player 3", "id", "1"));

        Mockito.when(handsRepository.findByPlayer("Player 3")).thenReturn(handsEntities);
        handsEntities = new ArrayList<>();
        handsEntities.add(new HandsEntity("Player 4", "id", "1"));
        handsEntities.add(new HandsEntity("Player 4", "id", "1"));
        handsEntities.add(new HandsEntity("Player 4", "id", "1"));
        handsEntities.add(new HandsEntity("Player 4", "id", "1"));
        handsEntities.add(new HandsEntity("Player 4", "id", "1"));

        Mockito.when(handsRepository.findByPlayer("Player 4")).thenReturn(handsEntities);

        List<DrawHandsResponse> drawHandsResponses = new ArrayList<>();
        List<Long> values = new ArrayList<>();
        values.add(1L);
        values.add(1L);
        values.add(1L);
        values.add(1L);
        values.add(1L);
        drawHandsResponses.add(new DrawHandsResponse("Player 1", values));
        drawHandsResponses.add(new DrawHandsResponse("Player 2", values));
        drawHandsResponses.add(new DrawHandsResponse("Player 3", values));
        drawHandsResponses.add(new DrawHandsResponse("Player 4", values));

        Assertions.assertEquals(cardsService.pickWinner(), drawHandsResponses.get(0).getNome() + " = " +
                Arrays.toString(drawHandsResponses.get(0).getValues().toArray()) + "\n" +
                drawHandsResponses.get(1).getNome() + " = " +
                Arrays.toString(drawHandsResponses.get(1).getValues().toArray()) + "\n" +
                drawHandsResponses.get(2).getNome() + " = " +
                Arrays.toString(drawHandsResponses.get(2).getValues().toArray()) + "\n" +
                drawHandsResponses.get(3).getNome() + " = " +
                Arrays.toString(drawHandsResponses.get(3).getValues().toArray()));

    }

    @Test
    public void pickWinner_onePlayerWinnerResult(){
        List<HandsEntity> handsEntities = new ArrayList<>();
        handsEntities.add(new HandsEntity("Player 1", "id", "5"));
        handsEntities.add(new HandsEntity("Player 1", "id", "5"));
        handsEntities.add(new HandsEntity("Player 1", "id", "5"));
        handsEntities.add(new HandsEntity("Player 1", "id", "5"));
        handsEntities.add(new HandsEntity("Player 1", "id", "5"));

        Mockito.when(handsRepository.findByPlayer("Player 1")).thenReturn(handsEntities);

        handsEntities = new ArrayList<>();
        handsEntities.add(new HandsEntity("Player 2", "id", "1"));
        handsEntities.add(new HandsEntity("Player 2", "id", "1"));
        handsEntities.add(new HandsEntity("Player 2", "id", "1"));
        handsEntities.add(new HandsEntity("Player 2", "id", "1"));
        handsEntities.add(new HandsEntity("Player 2", "id", "1"));

        Mockito.when(handsRepository.findByPlayer("Player 2")).thenReturn(handsEntities);

        handsEntities = new ArrayList<>();
        handsEntities.add(new HandsEntity("Player 3", "id", "1"));
        handsEntities.add(new HandsEntity("Player 3", "id", "1"));
        handsEntities.add(new HandsEntity("Player 3", "id", "1"));
        handsEntities.add(new HandsEntity("Player 3", "id", "1"));
        handsEntities.add(new HandsEntity("Player 3", "id", "1"));

        Mockito.when(handsRepository.findByPlayer("Player 3")).thenReturn(handsEntities);
        handsEntities = new ArrayList<>();
        handsEntities.add(new HandsEntity("Player 4", "id", "1"));
        handsEntities.add(new HandsEntity("Player 4", "id", "1"));
        handsEntities.add(new HandsEntity("Player 4", "id", "1"));
        handsEntities.add(new HandsEntity("Player 4", "id", "1"));
        handsEntities.add(new HandsEntity("Player 4", "id", "1"));

        Mockito.when(handsRepository.findByPlayer("Player 4")).thenReturn(handsEntities);

        List<DrawHandsResponse> drawHandsResponses = new ArrayList<>();
        List<Long> values = new ArrayList<>();
        values.add(1L);
        values.add(1L);
        values.add(1L);
        values.add(1L);
        values.add(1L);
        drawHandsResponses.add(new DrawHandsResponse("Player 1", values));
        drawHandsResponses.add(new DrawHandsResponse("Player 2", values));
        drawHandsResponses.add(new DrawHandsResponse("Player 3", values));
        drawHandsResponses.add(new DrawHandsResponse("Player 4", values));

        Assertions.assertEquals(cardsService.pickWinner(), "Vencedor é o Player 1 com 25 pontos" );

    }
}
