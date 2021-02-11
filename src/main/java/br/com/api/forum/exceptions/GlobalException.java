package br.com.api.forum.exceptions;

public class GlobalException extends Exception {

    private static final long serialVersionUID = 1L;

    private String mensagemErro;

    public GlobalException(String mensagemErro) {
        this.mensagemErro = mensagemErro;
    }

    public String getMensagemErro() {
        return mensagemErro;
    }

    public void setMensagemErro(String mensagemErro) {
        this.mensagemErro = mensagemErro;
    }

}
