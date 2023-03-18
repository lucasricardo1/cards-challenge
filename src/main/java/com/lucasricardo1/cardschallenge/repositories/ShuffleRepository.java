package com.lucasricardo1.cardschallenge.repositories;

import com.lucasricardo1.cardschallenge.entities.ShuffleEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShuffleRepository extends CrudRepository<ShuffleEntity, String> {
}
