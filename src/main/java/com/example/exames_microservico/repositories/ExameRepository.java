package com.example.exames_microservico.repositories;

import com.example.exames_microservico.models.ExameModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ExameRepository extends JpaRepository<ExameModel, UUID> {

    boolean existsByNome(String nome);

}
