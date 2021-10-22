package com.example.Assignment1webapp;

import com.example.Assignment1webapp.Complain.Complain;
import com.example.Assignment1webapp.Complain.ComplainRepository;
import com.example.Assignment1webapp.User.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class ComplainRepositoryTests {
    @Autowired
    private ComplainRepository repo;

    @Test
    public void testAddNewComplain(){
        Complain complain = new Complain();
        complain.setName("Deshan Perera");
        complain.setMobileNo("0778909678");
        complain.setDescription("I want the tea plants and water");

        Complain saveComplain = repo.save(complain);
    }

    @Test
    public void testListAll(){
        Iterable<Complain> complains = repo.findAll();

        for (Complain complain: complains) {
            System.out.println(complain);
        }
    }

    @Test
    public void testUpdate(){
        Integer complainId = 1;
        Optional<Complain> optionalComplain = repo.findById(complainId);
        Complain complain = optionalComplain.get();
        complain.setName("Wanigasekara");
        repo.save(complain);

        Complain updatedComplain = repo.findById(complainId).get();
    }

    @Test
    public void testGet(){
        Integer complainId = 1;
        Optional<Complain> optionalComplain = repo.findById(complainId);
        System.out.println(optionalComplain.get());
    }

    @Test
    public void testDelete(){
        Integer complainId = 1;
        repo.deleteById(complainId);
        Optional<Complain> optionalComplain = repo.findById(complainId);
    }
}
