package com.cm.vitor.modelo;

import java.util.ArrayList;
import java.util.List;

import com.cm.vitor.excecao.ExplosaoException;

public class Campo {

	private boolean aberto = false;
	private boolean minado = false;
	private boolean marcado = false;

	private int linha;
	private int coluna;

	public Campo(int linha, int coluna) {
		this.linha = linha;
		this.coluna = coluna;
	}

	private List<Campo> vizinhos = new ArrayList<>();

	public boolean adicionarVizinho(Campo vizinho) {
		int distH = Math.abs(coluna - vizinho.coluna);
		int distV = Math.abs(linha - vizinho.linha);
		if (distV == 1 && distH == 1) {
			vizinhos.add(vizinho);
			return true;
		} else if (distV + distH == 1) {
			vizinhos.add(vizinho);
			return true;
		} else {
			return false;
		}
	}

	public void alternarMarcacao() {
		if (!aberto) {
			marcado = !marcado;
		}
	}

	public boolean abrir() {
		if (!aberto && !marcado) {
			aberto = true;
			if (minado == true) {
				throw new ExplosaoException();
			}
			if (vizinhacaSegura()) {
				vizinhos.forEach(v -> v.abrir());
				;
			}
			return true;
		} else {
			return false;
		}
	}

	public boolean vizinhacaSegura() {
		return vizinhos.stream().allMatch(c -> !c.minado);
	}

	public boolean isMarcado() {
		return marcado;
	}

	public boolean isMinado() {
		return minado;
	}

	public boolean isAberto() {
		return aberto;
	}

	public boolean minar() {
		if (!minado) {
			minado = true;
		}
		return minado;
	}

	public int getLinha() {
		return linha;
	}

	public int getColuna() {
		return coluna;
	}

	public boolean objetivoSucesso() {
		boolean caso1 = minado && marcado;
		boolean caso2 = !minado && aberto;
		return caso1 || caso2;
	}

	public int bombasPerto() {
		return (int) vizinhos.stream().filter(c -> c.isMinado()).count();
	}

	void reiniciarCasas() {
		minado = false;
		aberto = false;
		marcado = false;
	}

	public String toString() {
		if (marcado) {
			return "\u001B[33mX\u001B[0m";
		} else if (aberto && minado) {
			 return "\u001B[31m*\u001B[0m";
		} else if (aberto && bombasPerto() >= 1) {
			return "\u001B[94m" + bombasPerto() + "\u001B[0m";
		} else if (aberto) {
			return " ";
		} else {
			return "\u001B[35m?\u001B[0m";
		}
	}

	public void setAberto() {
		aberto = true;
	}

	
}
