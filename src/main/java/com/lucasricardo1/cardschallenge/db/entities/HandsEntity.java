package com.lucasricardo1.cardschallenge.db.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "TB_HANDS", schema = "cards")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HandsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY
    )
    @Column(name = "ID")
    private Long id;

    @Column(name = "PLAYER")
    private String player;

    @Column(name = "DECK_ID")
    private String deckId;

    @Column(name = "CARD")
    private String card;

    public HandsEntity(String player, String deckId, String card) {
        this.player = player;
        this.deckId = deckId;
        this.card = card;
    }
}
