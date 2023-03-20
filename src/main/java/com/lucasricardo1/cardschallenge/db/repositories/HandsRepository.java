package com.lucasricardo1.cardschallenge.db.repositories;

import com.lucasricardo1.cardschallenge.db.entities.HandsEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HandsRepository extends CrudRepository<HandsEntity, Long> {

    List<HandsEntity> findByPlayer(String player);
}
