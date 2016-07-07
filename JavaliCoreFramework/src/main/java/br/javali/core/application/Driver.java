package br.javali.core.application;


import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import br.javali.core.framework.Associacao;
import br.javali.core.framework.Avalia;
import br.javali.core.framework.ResultadoAvaliacao;


public class Driver {

	public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException, IOException {
		String caminhoTeste = "/javali/implementacao.jar";
		String caminhoImpl = "/javali/teste2.jar";
		ArrayList<Associacao> associacoes = new ArrayList<Associacao>();
		associacoes.add(new Associacao("javaliproject.implementacoes.CalculadoraImpl", "javaliproject.testes.TesteCalculadora"));
		associacoes.add(new Associacao("javaliproject.implementacoes.PerfilImpl", "javaliproject.testes.TestePerfil"));
		ResultadoAvaliacao result = Avalia.run(caminhoTeste, caminhoImpl, associacoes);
		System.out.println("A nota foi: "+result.getNota());
		System.out.println(result.getMensagem());
	}

}
