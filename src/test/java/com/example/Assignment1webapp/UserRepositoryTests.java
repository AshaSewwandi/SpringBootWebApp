package com.example.Assignment1webapp;

import com.example.Assignment1webapp.User.User;
import com.example.Assignment1webapp.User.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class UserRepositoryTests {
    @Autowired
    private UserRepository repo;

    @Test
    public void testAddNew(){
        User user = new User();
        user.setEmail("ravishana@gmail.com");
        user.setFirstName("Ravishana");
        user.setLastName("Chathuranga");

        User savedUser = repo.save(user);

        //Assertions.assertThat(savedUser).isNotNull();
    }

    @Test
    public void testListAll(){
        Iterable<User> users = repo.findAll();

        for (User user: users) {
            System.out.println(user);
        }
    }

    @Test
    public void testUpdate(){
        Integer userId = 2;
        Optional<User> optionalUser = repo.findById(userId);
        User user = optionalUser.get();
        user.setFirstName("Asha");
        repo.save(user);

        User updateduser = repo.findById(userId).get();

    }

    @Test
    public void testGet(){
        Integer userId = 2;
        Optional<User> optionalUser = repo.findById(userId);
        System.out.println(optionalUser.get());
    }

    @Test
    public void testDelete(){
        Integer userId = 2;
        repo.deleteById(userId);
        Optional<User> optionalUser = repo.findById(userId);
    }


}
