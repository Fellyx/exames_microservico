package com.example.exames_microservico.services;

import javax.transaction.Transactional;

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

  public Optional<ExameModel> findById(UUID id) {
      return exameRepository.findById(id);
  }

  @Transactional
  public void delete(ExameModel ExameModel) {
      exameRepository.delete(ExameModel);
  }
  
  
}
