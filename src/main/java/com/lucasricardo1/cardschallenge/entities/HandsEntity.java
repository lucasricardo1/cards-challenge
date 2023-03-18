package com.lucasricardo1.cardschallenge.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "TB_HANDS", schema = "cards")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HandsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "PLAYER")
    private String player;

    @Column(name = "DECK_ID")
    private String deckId;

    @Column(name = "CARD")
    private String card;
}
