package com.example.exames_microservico.controllers;

import com.example.exames_microservico.dtos.ExameDto;
import com.example.exames_microservico.models.ExameModel;
import com.example.exames_microservico.services.ExameService;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/exame")
public class ExameController {
    final ExameService exameService;

    public ExameController (ExameService exameService) {
        this.exameService = exameService;
    }

    @PostMapping
    public ResponseEntity<Object> saveExame(@RequestBody @Valid ExameDto exameDto){
        if(exameService.existsByNome(exameDto.getNome())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflito: Nome Do Exame já em uso");
        }        
        var exameModel = new ExameModel();
        BeanUtils.copyProperties(exameDto, exameModel);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(exameService.save(exameModel));
    }

    @GetMapping
    public ResponseEntity<Page<ExameModel>> getAllExames(@PageableDefault(page = 0, size = 10, sort = "cd_exame", direction = Sort.Direction.ASC) Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(exameService.findAll(pageable));
    }

    @GetMapping("/{cd_exame}")
    public ResponseEntity<Object> getOneExame(@PathVariable(value = "cd_exame") UUID cd_exame){
        Optional<ExameModel> exameModelOptional = exameService.findBycd_exame(cd_exame);
        if (!exameModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Exame Nao Encontrado.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(exameModelOptional.get());
    }

    @DeleteMapping("/{cd_exame}")
    public ResponseEntity<Object> deleteExame(@PathVariable(value = "cd_exame") UUID cd_exame){
        Optional<ExameModel> exameModelOptional = exameService.findBycd_exame(cd_exame);
        if (!exameModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Exame não encontrado.");
        }
        exameService.delete(exameModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Exame Deletado com Sucesso.");
    }

    @PutMapping("/{cd_exame}")
    public ResponseEntity<Object> updateExame(@PathVariable(value = "cd_exame") UUID cd_exame,
                                                    @RequestBody @Valid ExameDto exameDto){
        Optional<ExameModel> exameModelOptional = exameService.findBycd_exame(cd_exame);
        if (!exameModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Exame não encontrado.");
        }
        var exameModel = new ExameModel();
        BeanUtils.copyProperties(exameDto, exameModel);
        exameModel.setCd_exame(exameModelOptional.getCd_exame());     
        return ResponseEntity.status(HttpStatus.OK).body(exameService.save(exameModel));
    } 
}
