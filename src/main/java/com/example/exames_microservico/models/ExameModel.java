package com.example.exames_microservico.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "TB_EXAME")
public class ExameModel implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID cd_exame;
	@Column(nullable = false, unique = true, length = 10)
	private String nome;
	@Column(nullable = false, unique = true, length = 250)
	private String descricao;
	
	
	public UUID getCd_exame() {
		return cd_exame;
	}
	
	public void setCd_exame(UUID cd_exame) {
		this.cd_exame = cd_exame;
	}
	
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
