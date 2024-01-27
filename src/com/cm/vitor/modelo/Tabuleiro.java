package com.cm.vitor.modelo;

import java.util.ArrayList;
import java.util.List;

import com.cm.vitor.excecao.ExplosaoException;

public class Tabuleiro {

	List<Campo> tabuleiro = new ArrayList<Campo>();

	private int linhas;
	private int colunas;
	private int minas;
	
	public Tabuleiro(int linhas, int colunas, int minas) {
		this.linhas = linhas;
		this.colunas = colunas;
		this.minas = minas;
		
		gerarCampos();
		relacionarVizinhos();
		sortearMinas();
	}

	private void gerarCampos() {
		
		for (int i = 0; i < linhas; i++) {
			for (int j = 0; j < colunas; j++) {
				tabuleiro.add(new Campo(i, j));
			}
		}
		
	}

	private void relacionarVizinhos() {
	
		for (Campo itr1 : tabuleiro) {
			for (Campo itr2 : tabuleiro) {
				itr1.adicionarVizinho(itr2);
			}
		}
		
	}

	private void sortearMinas() {
		long minasArmadas = 0;

		do {
			int indice = (int) (Math.random() * tabuleiro.size());
			tabuleiro.get(indice).minar();
			minasArmadas = (int) tabuleiro.stream().filter(c -> c.isMinado()).count();
		} while (minasArmadas < minas);
		
	}
	
	public boolean objetivoSucesso() {
		return tabuleiro.stream().allMatch(c-> c.objetivoSucesso());
	}
	
	public void reiniciarJogo() {
		tabuleiro.stream().forEach(c->c.reiniciarCasas());
		sortearMinas();
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < colunas; i++) {
			sb.append("   ");
			sb.append(i+1);
			sb.append("");
		}
		sb.append("\n");
		int aux = 0;
		for (int i = 0; i < linhas; i++) {
			sb.append(i+1);
			sb.append(" ");
			for (int j = 0; j < colunas; j++) {
				sb.append("[");
				sb.append(tabuleiro.get(aux));
				sb.append("]");
				sb.append(" ");
				aux++;
			}
			sb.append("\n\n");
		}
		return sb.toString();
	}
	
	public void abrir(int linha, int coluna) {
		try {
			tabuleiro.parallelStream().filter(c-> c.getLinha() == linha && c.getColuna() == coluna).findAny().ifPresent(c->c.abrir());
		} catch (ExplosaoException e) {
			for (Campo campo : tabuleiro) {
				campo.setAberto();
			}
			throw e;
		}
		
	}
	
	public void alternarMarcacao(int linha, int coluna) {
		tabuleiro.parallelStream().filter(c-> c.getLinha() == linha && c.getColuna() == coluna).findAny().ifPresent(c->c.alternarMarcacao());
	}
	
	
}
