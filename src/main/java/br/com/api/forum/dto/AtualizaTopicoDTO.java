package br.com.api.forum.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

public class AtualizaTopicoDTO {

	@NotEmpty(message = "O Campo TITULO não pode estar vazio!")
	@NotBlank(message = "O Campo TITULO não pode estar vazio!")
	@Length(min = 5)
	private String titulo;

	@NotEmpty(message = "O Campo MENSAGEM não pode estar vazio!")
	@NotBlank(message = "O Campo MENSAGEM não pode estar vazio!")
	@Length(min = 10)
	private String mensagem;

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

}
