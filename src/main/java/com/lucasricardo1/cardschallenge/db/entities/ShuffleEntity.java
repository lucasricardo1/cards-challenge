package com.lucasricardo1.cardschallenge.db.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "TB_SHUFFLE", schema = "cards")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShuffleEntity {

    @Column(name = "SUCCESS")
    private boolean success;

    @Id
    @Column(name = "DECK_ID")
    private String deckId;

    @Column(name = "SHUFFLE")
    private boolean shuffle;

    @Column(name = "REMAINING")
    private int remaining;

}
