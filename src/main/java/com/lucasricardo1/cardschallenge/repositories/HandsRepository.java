package com.lucasricardo1.cardschallenge.repositories;

import com.lucasricardo1.cardschallenge.entities.HandsEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HandsRepository extends CrudRepository<HandsEntity, Integer> {

    List<HandsEntity> findByPlayer(String player);
}
