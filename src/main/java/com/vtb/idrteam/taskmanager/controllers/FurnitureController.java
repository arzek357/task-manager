package com.vtb.idrteam.taskmanager.controllers;

import com.vtb.idrteam.taskmanager.entities.Furniture;
import com.vtb.idrteam.taskmanager.services.FurnitureService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/furniture")
public class FurnitureController {
    private FurnitureService furnitureService;

    @GetMapping
    public List<Furniture> getAllFurnitures(){
        return  furnitureService.getAllFurnitures();
    }

    @GetMapping("/{id}")
    public Furniture getById(@PathVariable Long id){
        return furnitureService.getById(id)
                .orElseThrow(() -> new RuntimeException("No furniture with id = " + id));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        furnitureService.deleteById(id);
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Furniture create(@RequestBody Furniture furniture){
        return furnitureService.saveOrUpdate(furniture);
    }


}

