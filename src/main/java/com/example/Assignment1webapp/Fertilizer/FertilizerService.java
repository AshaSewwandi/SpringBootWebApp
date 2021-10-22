package com.example.Assignment1webapp.Fertilizer;

import com.example.Assignment1webapp.User.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FertilizerService {
    @Autowired
    private FertilizerRepository repo;

    public List<Fertilizer> listAll(){
        return (List<Fertilizer>) repo.findAll();
    }

    public void save(Fertilizer fertilizer) {
        repo.save(fertilizer);
    }

    public Fertilizer get(Integer id) throws FertilizerNotFoundException {
        Optional<Fertilizer> result = repo.findById(id);
        if(result.isPresent()){
            return result.get();
        }
        throw new FertilizerNotFoundException("Could not find any fertilizers with ID " +id);
    }

    public void delete(Integer id) throws FertilizerNotFoundException{
        Long count = repo.countById(id);
        if(count == null || count == 0){
            throw new FertilizerNotFoundException("Could not find any fertilizer with ID " +id);
        }
        repo.deleteById(id);
    }
}
