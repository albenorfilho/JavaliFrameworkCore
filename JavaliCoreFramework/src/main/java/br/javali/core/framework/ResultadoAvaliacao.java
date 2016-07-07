/*
 *JAVALICORE
 *AUTHOR: ALBENOR ARAÚJO FILHO 
 *
 * Essa classe é uma estrutura responsável por armazenar a nota calculada ao final  
 * da execução, bem como eventuais mensagens sobre métodos defeituosos.
 * 
 * */

package br.javali.core.framework;


public class ResultadoAvaliacao {
	private String mensagem;
	private double nota;
	
	
	public String getMensagem() {
		return mensagem;
	}
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	public double getNota() {
		return nota;
	}
	public void setNota(double nota) {
		this.nota = nota;
	}

	
	
}
