package com.vtb.idrteam.taskmanager.controllers;

import com.vtb.idrteam.taskmanager.entities.Document;
import com.vtb.idrteam.taskmanager.services.DocumentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/document")
public class DocumentController {
    private DocumentService documentService;

    @GetMapping
    public List<Document> getAllDocuments(){
        return documentService.getAllDocuments();
    }

    @GetMapping("/{id}")
    public Document getById(@PathVariable Long id){
        return documentService.getById(id).orElseThrow(() -> new RuntimeException("Document with id=" + id + " not found"));
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Document createNewDocument(@RequestBody Document document) {
        if (document.getId() != null) {
            document.setId(null);
        }
        return documentService.saveOrUpdate(document);
    }

    @PutMapping(consumes = "application/json", produces = "application/json")
    public Document modifyBook(@RequestBody Document document) {
        if (!documentService.existsById(document.getId())) {
            throw new RuntimeException("Document with id: " + document.getId() + " doesnt exists");
        }
        return documentService.saveOrUpdate(document);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        documentService.deleteById(id);
    }
}
