/*
 *JAVALICORE
 *AUTHOR: ALBENOR ARAÚJO FILHO 
 *
 * Essa classe é uma estrutura responsável por armazenar o 
 * nome do caso de teste associado ao nome da implementação
 * 
 * */


package br.javali.core.framework;

public class Associacao {
 private String testeNome;
 private String classeNome;
 
 public Associacao(String classeNome, String testeNome){
	 this.testeNome = testeNome;
	 this.classeNome = classeNome;
 }
 
public String getTesteNome() {
	return testeNome;
}
public void setTesteNome(String testeNome) {
	this.testeNome = testeNome;
}
public String getClasseNome() {
	return classeNome;
}
public void setClasseNome(String classeNome) {
	this.classeNome = classeNome;
}
 
 
}
