package com.example.Assignment1webapp.Fertilizer;

import org.springframework.data.repository.CrudRepository;

public interface FertilizerRepository extends CrudRepository<Fertilizer, Integer> {
    Long countById(Integer id);
}
