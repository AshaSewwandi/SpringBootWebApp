package com.example.Assignment1webapp.Complain;

import com.example.Assignment1webapp.User.User;
import com.example.Assignment1webapp.User.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ComplainService {
    @Autowired
    private ComplainRepository repo;

    public List<Complain> listAll(){
        return (List<Complain>) repo.findAll();
    }

    public void save(Complain complain) {
        repo.save(complain);
    }

    public Complain get(Integer id) throws ComplainNotFoundException {
        Optional<Complain> result = repo.findById(id);
        if(result.isPresent()){
            return result.get();
        }
        throw new ComplainNotFoundException("Could not find any complain with ID " +id);
    }

    public void delete(Integer id) throws ComplainNotFoundException {
        Long count = repo.countById(id);
        if(count == null || count == 0){
            throw new ComplainNotFoundException("Could not find any complain with ID " +id);
        }
        repo.deleteById(id);
    }
}
