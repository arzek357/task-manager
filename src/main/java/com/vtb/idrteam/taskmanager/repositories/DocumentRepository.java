package com.vtb.idrteam.taskmanager.repositories;

import com.vtb.idrteam.taskmanager.entities.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Long> {
}
