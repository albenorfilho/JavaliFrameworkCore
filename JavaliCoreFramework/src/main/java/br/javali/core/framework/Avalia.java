/*
 *JAVALICORE
 *AUTHOR: ALBENOR ARAÚJO FILHO 
 * 
 * Classe responsável por fazer a execução do framework de 
 * avaliação automática 
 * 
 * */

package br.javali.core.framework;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.List;



public class Avalia {
//Esse método deve receber o caminho para o jar de teste, um caminho para o jar a ser testado, e uma lista de associacao que mapeia qual teste testa qual implementacao
//O resultado desse método é do tipo ResultadoAvalicao, uma estrutura que guarda a nota calculada e mensagens que dizem qual método deu erro.	
public static ResultadoAvaliacao run(String testepath, String implementacaopath, List<Associacao> associacoes) throws ClassNotFoundException, NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException, IOException {
		
		double valor = 0;
		String message = "Métodos que deram defeito: ";
    	/*Aqui fazemos a criação dos classloaders, responsáveis por carregar nossas classes
    	 * mesmo que tenham o mesmo nome, sem dar conflitos.*/
		URLClassLoader loader = (URLClassLoader)ClassLoader.getSystemClassLoader();
    	MyClassLoader l = new MyClassLoader(loader.getURLs());
    	
    	//Abaixo iremos adicionar nossos jars dentro do classpath do projeto
    	l.addURL(new URL("file://"+implementacaopath)); 
    	l.addURL(new URL("file://"+testepath)); 
    	
    	//O loop abaixo é necessário para que sejam executados todos os casos de teste que o professor criou
    	for(int i = 0; i< associacoes.size(); i++){
	    	
    		//Abaixo fazemos o carregamento da classe implementada e seu respectivo caso de teste
	    	Class<?> implementacao = l.loadClass(associacoes.get(i).getClasseNome());
	    	Class<?> teste = l.loadClass(associacoes.get(i).getTesteNome());
	    	
	    	//Aqui faremos a instanciação da classe e do caso de teste para a mesma
	    	Object objImpl = implementacao.newInstance();
	    	Object objTest = teste.newInstance();
    	
	    	//O loop abaixo é necessário para executar todos os métodos escritos dentro do teste
	    	for (Method metodo : teste.getDeclaredMethods()) {
	    		Method my_method = teste.getDeclaredMethod(metodo.getName(), Object.class);
	  		    //Quando o método é executado, será retornado um valor de pontuação no formato double
	    		double notaDoMetodo = (Double) my_method.invoke(objTest, objImpl);
	  		    //Aqui somamos o valor recebido ao valor total
	    		valor += notaDoMetodo;
	  		    //O if abaixo é para deixar mais visual para o aluno o nome do método que deu errado.
	    		if(notaDoMetodo==0){
	  		    	String old = metodo.getName();
	  		    	String nomeMetodo = old.replace("test", "");
	  		    	message += nomeMetodo + " ";
	  		    }
	  		  
	  		    
	    	}
    	}
    	//Caso a nota seja 10, significa que não existem classes defeituosas.
    	if(valor==10){
    		message = "Parabéns! O seu projeto não contém nenhum erro!";
    	}
    	//Criamos a instancia de resultado e setamos a nota e mensagens para mandar no retorno
    	ResultadoAvaliacao resultado = new ResultadoAvaliacao();
    	resultado.setNota(valor);
    	resultado.setMensagem(message);
    	loader.close();
    	l.close();
    	return resultado;

	}

}

