package com.lucasricardo1.cardschallenge.services;

import com.lucasricardo1.cardschallenge.client.DrawCardsClient;
import com.lucasricardo1.cardschallenge.client.ShuffleCardsClient;
import com.lucasricardo1.cardschallenge.dto.ShuffleDTO;
import com.lucasricardo1.cardschallenge.dto.response.DrawCardsResponse;
import com.lucasricardo1.cardschallenge.dto.response.DrawHandsResponse;
import com.lucasricardo1.cardschallenge.dto.response.ShuffleCardsResponse;
import com.lucasricardo1.cardschallenge.db.entities.HandsEntity;
import com.lucasricardo1.cardschallenge.db.entities.ShuffleEntity;
import com.lucasricardo1.cardschallenge.db.repositories.HandsRepository;
import com.lucasricardo1.cardschallenge.db.repositories.ShuffleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CardsService {
    @Autowired
    private HandsRepository handsRepository;

    @Autowired
    private ShuffleRepository shuffleRepository;

    @Autowired
    DrawCardsClient drawCardsClient;

    @Autowired
    ShuffleCardsClient shuffleCardsClient;

    public CardsService() {
    }

    public ShuffleDTO shuffleDeck() {
        ShuffleCardsResponse shuffleCardsResponse = shuffleCardsClient.shuffleCards();

        ShuffleEntity shuffleEntity = new ShuffleEntity(
                shuffleCardsResponse.isSuccess(),
                shuffleCardsResponse.getDeck_id(),
                shuffleCardsResponse.isShuffled(),
                shuffleCardsResponse.getRemaining());

        ShuffleEntity entitySaved = shuffleRepository.save(shuffleEntity);

        return ShuffleDTO.builder()
                .shuffle(entitySaved.isShuffle())
                .deckId(entitySaved.getDeckId())
                .remaining(shuffleEntity.getRemaining())
                .success(shuffleEntity.isSuccess())
                .build();
    }

    public String saveDecks(String deckId) {
        Optional<ShuffleEntity> shuffleEntity = shuffleRepository.findById(deckId);

        if (shuffleEntity.isEmpty())
            return "Don't have an Deck to this Deck ID";

        DrawCardsResponse drawCardsResponse = drawCardsClient.drawCards(shuffleEntity.get().getDeckId(), 20);

        List<HandsEntity> handsEntities = new ArrayList<>();
        int index = 0;

        for (int i = 0; i < 4; i++) {

            handsEntities.add(index, new HandsEntity("Player " + (i + 1),
                    shuffleEntity.get().getDeckId(),
                    drawCardsResponse.getCards().get(index).getValue()));
            index++;

            handsEntities.add(index, new HandsEntity("Player " + (i + 1),
                    shuffleEntity.get().getDeckId(),
                    drawCardsResponse.getCards().get(index).getValue()));
            index++;

            handsEntities.add(index, new HandsEntity("Player " + (i + 1),
                    shuffleEntity.get().getDeckId(),
                    drawCardsResponse.getCards().get(index).getValue()));
            index++;

            handsEntities.add(index, new HandsEntity("Player " + (i + 1),
                    shuffleEntity.get().getDeckId(),
                    drawCardsResponse.getCards().get(index).getValue()));
            index++;

            handsEntities.add(index, new HandsEntity("Player " + (i + 1),
                    shuffleEntity.get().getDeckId(),
                    drawCardsResponse.getCards().get(index).getValue()));
            index++;


        }

        handsRepository.saveAll(handsEntities);
        return "Decks saved!";
    }

    public String pickWinner() {
        TreeMap<String, Long> map = new TreeMap<>();
        List<DrawHandsResponse> drawHandsResponses = new ArrayList<>();

        for (int i = 1; i <= 4; i++) {
            Long value = 0L;
            List<HandsEntity> handsEntities = handsRepository.findByPlayer("Player " + i);

            value += getCardValue(handsEntities.get(0).getCard());
            value += getCardValue(handsEntities.get(1).getCard());
            value += getCardValue(handsEntities.get(2).getCard());
            value += getCardValue(handsEntities.get(3).getCard());
            value += getCardValue(handsEntities.get(4).getCard());

            List<Long> values = new ArrayList<>();
            values.add(getCardValue(handsEntities.get(0).getCard()));
            values.add(getCardValue(handsEntities.get(1).getCard()));
            values.add(getCardValue(handsEntities.get(2).getCard()));
            values.add(getCardValue(handsEntities.get(3).getCard()));
            values.add(getCardValue(handsEntities.get(4).getCard()));


            drawHandsResponses.add(new DrawHandsResponse("Player " + i, values));

            map.put("Player " + i, value);
        }



        List finalValues = new ArrayList(map.entrySet());
        sortMap(finalValues);

        if (((Map.Entry) finalValues.get(2)).getValue().equals(((Map.Entry) finalValues.get(3)).getValue())) {

            return drawHandsResponses.get(0).getNome() + " = " +
                    Arrays.toString(drawHandsResponses.get(0).getValues().toArray()) + "\n" +
                    drawHandsResponses.get(1).getNome() + " = " +
                    Arrays.toString(drawHandsResponses.get(1).getValues().toArray()) + "\n" +
                    drawHandsResponses.get(2).getNome() + " = " +
                    Arrays.toString(drawHandsResponses.get(2).getValues().toArray()) + "\n" +
                    drawHandsResponses.get(3).getNome() + " = " +
                    Arrays.toString(drawHandsResponses.get(3).getValues().toArray());
        } else {
            return "Vencedor é o " + ((Map.Entry<String, Long>) finalValues.get(3)).getKey();
        }


    }

    private static void sortMap(List finalValues) {
        Collections.sort(finalValues, new Comparator<Map.Entry<String, Long>>() {
            @Override
            public int compare(Map.Entry<String, Long> e1, Map.Entry<String, Long> e2) {
                return e1.getValue().compareTo(e2.getValue());
            }
        });
    }

    private Long getCardValue(String value){
        if(value.equals("ACE"))
            return 1L;
        if(value.equals("KING"))
            return 13L;
        if(value.equals("QUEEN"))
            return 12L;
        if(value.equals("JACK"))
            return 11L;

        return Long.parseLong(value);
    }

}
