package com.cm.vitor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.cm.vitor.modelo.Campo;

public class CampoTeste {

	Campo campo = new Campo (3, 3);
	
	@Test
	void teste() {
		Campo vizinho = new Campo (4, 4);
	
		boolean result = campo.adicionarVizinho(vizinho);
		
		assertTrue(result);
	}
	
	@Test
	void teste2() {
		campo.alternarMarcacao();
		
		boolean result = campo.isMarcado();
		
		assertTrue(result);
	}
	
	@Test
	void teste3() {
		campo.alternarMarcacao();
		
		campo.alternarMarcacao();
		
		boolean result = campo.isMarcado();
		
		assertFalse(result);
	}
	
	@Test
	void teste4() {
		campo.minar();
		
		assertTrue(campo.isMinado());
	}
	
	@Test
	void teste5() {
		campo.minar();
		
		campo.alternarMarcacao();
		
		assertTrue(campo.isMarcado());
		
		assertFalse(campo.isAberto());
		
		assertTrue(campo.objetivoSucesso());
	}
	
	@Test
	void teste6() {
		
		campo.abrir();
		
		assertFalse(campo.isMarcado());
		
		assertTrue(campo.isAberto());
		
		assertFalse(campo.isMinado()); 
	
		assertTrue(campo.objetivoSucesso());	
	}
	
	@Test
	void teste7() {
		
	Campo campo1 = new Campo(3,3);
	
	Campo campo2 = new Campo(2,3);
	
	Campo campo3 = new Campo(4,4);
	
	campo1.adicionarVizinho(campo2);
	
	campo1.adicionarVizinho(campo3);
	
	campo2.adicionarVizinho(campo3);
	
	campo2.adicionarVizinho(campo3);
	
	campo3.adicionarVizinho(campo1);
	
	campo3.adicionarVizinho(campo2);
	
	campo1.minar();
	
	campo2.minar();
	
	campo3.minar();
	
	int teste = campo1.bombasPerto();
	
	assertEquals(2, teste);
	}
	
	
	
}
