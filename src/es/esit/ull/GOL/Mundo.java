package es.esit.ull.GOL;

import java.util.Random;

public class Mundo {
	private boolean[][] mundo;
	private boolean[][] siguienteGeneracion;
	private int tam;

	public Mundo(int tam) {
		this.tam = tam;
		mundo = new boolean[tam][tam];
		siguienteGeneracion = new boolean[tam][tam];
		crearUniverso();
	}

	public void siguiente() {
		for (int i = 1; i < getTam() - 1; i++) {
			System.out.println();
			for (int j = 1; j < getTam() - 1; j++) {
				getSiguienteGeneracion()[i][j] = vive(i, j);
				System.out.print("[" + getSiguienteGeneracion()[i][j] + "]");
			}
		}
		actualizar();
	}

	private void actualizar() {
		for (int i = 0; i < getTam(); i++) {
			for (int j = 0; j < getTam(); j++) {
				getMundo()[i][j] = getSiguienteGeneracion()[i][j];
			}
		}
	}

	private boolean vive(int i, int j) {
		int vecinos = 0;

		if (getMundo()[i - 1][j]) {
			vecinos++;
		}
		if (getMundo()[i + 1][j]) {
			vecinos++;
		}
		if (getMundo()[i][j - 1]) {
			vecinos++;
		}
		if (getMundo()[i][j + 1]) {
			vecinos++;
		}
		if (getMundo()[i - 1][j - 1]) {
			vecinos++;
		}
		if (getMundo()[i - 1][j + 1]) {
			vecinos++;
		}
		if (getMundo()[i + 1][j + 1]) {
			vecinos++;
		}
		if (getMundo()[i + 1][j - 1]) {
			vecinos++;
		}
		
		if (vecinos  == 2 || vecinos == 3) {
			return true;
		} else
			return false;
	}

	private void crearUniverso() {
		Random dios = new Random();
		for (int i = 0; i < getTam(); i++) {
			for (int j = 0; j < getTam(); j++) {
				getMundo()[i][j] = dios.nextBoolean();
				//System.out.println("[" + getMundo()[i][j] + "]");
			}
		}
		iniciarNuevaGen();
	}

	private void iniciarNuevaGen() {
		for (int i = 0; i < getTam(); i++) {
			for (int j = 0; j < getTam(); j++) {
				getSiguienteGeneracion()[i][j] = getMundo()[i][j];
			}
		}
	}
	public void darVida(int i, int j) {
		getSiguienteGeneracion()[i][j] = true;
	}

	public void matar(int i, int j) {
		getSiguienteGeneracion()[i][j] = false;
	}

	public boolean[][] getSiguienteGeneracion() {
		return siguienteGeneracion;
	}

	public void setSiguienteGeneracion(boolean[][] siguienteGeneracion) {
		this.siguienteGeneracion = siguienteGeneracion;
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
