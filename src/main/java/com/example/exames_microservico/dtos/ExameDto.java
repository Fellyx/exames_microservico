package com.example.exames_microservico.dtos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class ExameDto {
    
    @NotBlank    
    @Size(max = 25)
    private String nome;
    @NotBlank
    @Size(max = 250)
    private String descricao;
    
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

    
}
