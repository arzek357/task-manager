package com.vtb.idrteam.taskmanager.services;

import com.vtb.idrteam.taskmanager.entities.Furniture;
import com.vtb.idrteam.taskmanager.repositories.FurnitureRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class FurnitureService {
    private FurnitureRepository furnitureRepository;

    public List<Furniture> getAllFurnitures(){
        return furnitureRepository.findAll();
    }

    public Optional<Furniture> getById(Long id){
        return furnitureRepository.findById(id);
    }

    public void deleteById(Long id){
        furnitureRepository.deleteById(id);
    }

    public Furniture saveOrUpdate(Furniture furniture){
        return furnitureRepository.save(furniture);
    }


}
