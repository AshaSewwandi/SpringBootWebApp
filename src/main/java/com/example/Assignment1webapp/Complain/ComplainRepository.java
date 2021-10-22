package com.example.Assignment1webapp.Complain;

import org.springframework.data.repository.CrudRepository;

public interface ComplainRepository extends CrudRepository<Complain, Integer> {
    public Long countById(Integer id);
}
