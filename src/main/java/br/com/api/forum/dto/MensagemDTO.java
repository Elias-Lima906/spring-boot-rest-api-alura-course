package br.com.api.forum.dto;

public class MensagemDTO {

	private String mensagem;

	public MensagemDTO(String mensagem) {
		this.mensagem = mensagem;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

}
