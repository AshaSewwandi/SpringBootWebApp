package com.example.Assignment1webapp;

import com.example.Assignment1webapp.Complain.Complain;
import com.example.Assignment1webapp.Fertilizer.Fertilizer;
import com.example.Assignment1webapp.Fertilizer.FertilizerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class FertilizerRepositoryTests {
    @Autowired
    private FertilizerRepository repo;

    @Test
    public void testAddNewFertilizer(){
        Fertilizer fertilizer = new Fertilizer();
        fertilizer.setName("Helicks");
        fertilizer.setPrice(3990);
        fertilizer.setQuantity(750);

        Fertilizer saveFertilizer = repo.save(fertilizer);
    }

    @Test
    public void testListAll(){
        Iterable<Fertilizer> fertilizers = repo.findAll();

        for(Fertilizer fertilizer: fertilizers){
            System.out.println(fertilizer);
        }
    }

    @Test
    public void testUpdate(){
        Integer fertilizerId = 4;
        Optional<Fertilizer> optionalFertilizer = repo.findById(fertilizerId);
        Fertilizer fertilizer = optionalFertilizer.get();
        fertilizer.setQuantity(300);
        repo.save(fertilizer);

        Fertilizer updatedFertilizer = repo.findById(fertilizerId).get();
    }

    @Test
    public void testGet(){
        Integer fertilizerId = 4;
        Optional<Fertilizer> optionalFertilizer = repo.findById(fertilizerId);
        System.out.println(optionalFertilizer.get());
    }

    @Test
    public void testDelete(){
        Integer fertilizerId = 4;
        repo.deleteById(fertilizerId);
        Optional<Fertilizer> optionalFertilizer = repo.findById(fertilizerId);
    }
}
