package com.cm.vitor.visao;

import java.util.Scanner;

import com.cm.vitor.excecao.ExplosaoException;
import com.cm.vitor.modelo.Tabuleiro;

public class TabuleiroConsole {

	private Tabuleiro tabuleiro;
	private Scanner entrada = new Scanner(System.in);
	
	public TabuleiroConsole(Tabuleiro tabuleiro) {
		this.tabuleiro = tabuleiro;
		
		logicaJogo();
	}

	private void logicaJogo() {
		
		while (!tabuleiro.objetivoSucesso()) {
			try {
				System.out.println(tabuleiro);
				System.out.print("Digite a linha: ");
				int x = entrada.nextInt() - 1;
				System.out.print("Digite a coluna: ");
				int y = entrada.nextInt() - 1;
				System.out.println("\n1 - Abrir\n2 - Alternar marcação");
				int opc = entrada.nextInt();
				System.out.println("\n");
				if (opc == 1) {
					tabuleiro.abrir(x, y);
				}
				else if (opc == 2) {
					tabuleiro.alternarMarcacao(x, y);
				}
				if (tabuleiro.objetivoSucesso()) {
					System.out.println(tabuleiro);
					System.out.println("VOCÊ VENCEU!");
					entrada.close();
				}
			} catch (ExplosaoException e) {
				System.out.println(tabuleiro);
				System.out.println("PERDEU!");
				System.exit(0);
			}
		}

	}

}
