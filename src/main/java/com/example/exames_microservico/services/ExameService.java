package com.example.exames_microservico.services;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.exames_microservico.models.ExameModel;
import com.example.exames_microservico.repositories.ExameRepository;

@Service
public class ExameService {

  final ExameRepository exameRepository;
  
  public ExameService(ExameRepository exameRepository) {
	  this.exameRepository =  exameRepository;
  }  

  @Transactional
  public ExameModel save(ExameModel ExameModel) {
      return exameRepository.save(ExameModel);
  }

  public boolean existsByNome(String Nome) {
      return exameRepository.existsByNome(Nome);
  }

  public Page<ExameModel> findAll(Pageable pageable) {
      return exameRepository.findAll(pageable);
  }

  public Optional<ExameModel> findBycd_exame(UUID cd_exame) {
      return exameRepository.findById(cd_exame);
  }

  @Transactional
  public void delete(ExameModel ExameModel) {
      exameRepository.delete(ExameModel);
  }
  
  
}
