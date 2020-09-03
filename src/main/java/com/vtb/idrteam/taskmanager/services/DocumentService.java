package com.vtb.idrteam.taskmanager.services;

import com.vtb.idrteam.taskmanager.entities.Document;
import com.vtb.idrteam.taskmanager.repositories.DocumentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class DocumentService {
    private DocumentRepository documentRepository;

    public List<Document> getAllDocuments(){
        return documentRepository.findAll();
    }

    public Optional<Document> getById(Long id){
        return documentRepository.findById(id);
    }

    public Document saveOrUpdate(Document document){
        return documentRepository.save(document);
    }

    public void deleteById(Long id){
        documentRepository.deleteById(id);
    }

    public boolean existsById(Long id) {
        return documentRepository.existsById(id);
    }
}
