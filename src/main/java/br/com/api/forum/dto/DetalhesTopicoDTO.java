package br.com.api.forum.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import br.com.api.forum.modelo.StatusTopico;
import br.com.api.forum.modelo.Topico;

public class DetalhesTopicoDTO {

	private Long id;
	private String titulo;
	private String mensagem;
	private LocalDateTime data;
	private String nomeAutor;
	private StatusTopico status;
	private List<RespostaDTO> respostas;

	public DetalhesTopicoDTO(Topico topico) {

		this.id = topico.getId();
		this.titulo = topico.getTitulo();
		this.mensagem = topico.getMensagem();
		this.data = topico.getDataCriacao();
		this.nomeAutor = topico.getAutor().getNome();
		this.status = topico.getStatus();
		this.respostas = this.populaListaRespostasDTO(topico);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public LocalDateTime getData() {
		return data;
	}

	public void setData(LocalDateTime data) {
		this.data = data;
	}

	public String getNomeAutor() {
		return nomeAutor;
	}

	public void setNomeAutor(String nomeAutor) {
		this.nomeAutor = nomeAutor;
	}

	public StatusTopico getStatus() {
		return status;
	}

	public void setStatus(StatusTopico status) {
		this.status = status;
	}

	public List<RespostaDTO> getRespostas() {
		return respostas;
	}

	public void setRespostas(List<RespostaDTO> respostas) {
		this.respostas = respostas;
	}

	private List<RespostaDTO> populaListaRespostasDTO(Topico topico) {
		
		this.respostas = new ArrayList<RespostaDTO>();

		return topico.getRespostas().stream().map(RespostaDTO::new).collect(Collectors.toList());
	}
}
