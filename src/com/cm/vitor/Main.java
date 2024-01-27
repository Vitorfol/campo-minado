package com.cm.vitor;

import java.util.Scanner;

import com.cm.vitor.modelo.Tabuleiro;
import com.cm.vitor.visao.TabuleiroConsole;

public class Main {

	public static void main(String[] args) {

		Tabuleiro jogo = null;

		System.out.println("1 - Fácil\n2 - Médio\n3 - Difícil");

		Scanner opc = new Scanner(System.in);

		int opcao = opc.nextInt();

		switch (opcao) {

		case 1:
			jogo = new Tabuleiro(6, 6, 5);
			break;

		case 2:
			jogo = new Tabuleiro(8, 8, 10);
			break;

		case 3:
			jogo = new Tabuleiro(10, 10, 15);
			break;

		default:
			jogo = new Tabuleiro(6, 6, 5);

		}

		new TabuleiroConsole(jogo);

		opc.close();
		
	}

}
