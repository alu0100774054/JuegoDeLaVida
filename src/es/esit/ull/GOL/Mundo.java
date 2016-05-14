package es.esit.ull.GOL;

import java.util.Random;

public class Mundo {
	private boolean[][] mundo;
	private int tam;
	
	public Mundo(int tam) {
		this.tam = tam;
		mundo = new boolean[tam][tam];
		System.out.println("creado mundo de " + tam);
		crearUniverso();
	}

	private void crearUniverso() {
		for (int i = 0; i < getTam(); i++) {
			for (int j = 0; j < getTam(); j++) {
				getMundo()[i][j] = false;
			}
		}
	}

	public void darVida(int i, int j) {
		getMundo()[i][j] = true;
	}
	
	public void matar(int i, int j) {
		getMundo()[i][j] = false;
	}
	
	public boolean[][] getMundo() {
		return mundo;
	}

	public void setMundo(boolean[][] mundo) {
		this.mundo = mundo;
	}

	public int getTam() {
		return tam;
	}

	public void setTam(int tam) {
		this.tam = tam;
	}
	
	
}
