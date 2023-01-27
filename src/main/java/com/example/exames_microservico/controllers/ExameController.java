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
    public ResponseEntity<Object> saveExame(@RequestBody @Valid Examedto exameDto){
        if(ExameService.existsByNome(exameDto.getNome())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflito: Nome Do Exame já em uso");
        }        
        var exameModel = new ExameModel();
        BeanUtils.copyProperties(exameDto, exameModel);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(ExameService.save(exameModel));
    }

    @GetMapping
    public ResponseEntity<Page<ExameModel>> getAllExames(@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(ExameService.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneExame(@PathVariable(value = "id") UUID id){
        Optional<ExameModel> exameModelOptional = ExameService.findById(id);
        if (!exameModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Exame Nao Encontrado.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(exameModelOptional.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteExame(@PathVariable(value = "id") UUID id){
        Optional<ExameModel> exameModelOptional = ExameService.findById(id);
        if (!exameModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Exame não encontrado.");
        }
        ExameService.delete(exameModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Exame Deletado com Sucesso.");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateExame(@PathVariable(value = "id") UUID id,
                                                    @RequestBody @Valid Examedto exameDto){
        Optional<ExameModel> exameModelOptional = ExameService.findById(id);
        if (!exameModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Exame não encontrado.");
        }
        var exameModel = new ExameModel();
        BeanUtils.copyProperties(exameDto, exameModel);
        exameModel.setId(exameModelOptional.get().getId());
        exameModel.setRegistrationDate(exameModelOptional.get().getRegistrationDate());
        return ResponseEntity.status(HttpStatus.OK).body(ExameService.save(exameModel));
    } 
}
