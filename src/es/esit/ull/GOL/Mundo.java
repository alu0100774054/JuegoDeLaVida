/**
 * @author Erik Andreas Barreto de Vera.
 * E-mail: alu0100774054@ull.edu.es
 * Fecha: 18/05/2016
 * Asignatura: Programación de Aplicaciones Interactivas.
 * Comentario: Clase que contiene el algoritmo del juego.
 */
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

	/**
	 * Obtiene la siguiente generación de celulas.
	 */
	public void siguiente() {
		for (int i = 0; i < getTam(); i++) {
			System.out.println();
			for (int j = 0; j < getTam(); j++) {
				getSiguienteGeneracion()[i][j] = vive(i, j);
			}
		}
		actualizar();
	}

	/**
	 * Actualiza el mundo actual al mundo en la siguiente generación.
	 */
	private void actualizar() {
		for (int i = 0; i < getTam(); i++) {
			for (int j = 0; j < getTam(); j++) {
				getMundo()[i][j] = getSiguienteGeneracion()[i][j];
			}
		}
	}

	/**
	 * Método donde se definen las reglas de supervivencia.
	 * @param i fila de la celula.
	 * @param j columna de la celula.
	 * @return si vive o muere.
	 */
	private boolean vive(int i, int j) {
		int vecinos = 0;
		if (i > 0 && i < getTam() - 1) {
			if (getMundo()[i - 1][j]) {
				vecinos++;
			}
			if (getMundo()[i + 1][j]) {
				vecinos++;
			}
		} if (j > 0 && j < getTam() - 1) {
			if (getMundo()[i][j - 1]) {
				vecinos++;
			}

			if (getMundo()[i][j + 1]) {
				vecinos++;
			}
		} if (i > 0 && i < getTam() - 1 && j > 0 && j < getTam() - 1) {
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
		} 

		if (getMundo()[i][j] == true) {
			if (vecinos  == 2 || vecinos == 3) {
				return true;
			} else
				return false;
		} else {
			if (vecinos == 3) {
				return true;
			} else
				return false;
		}

	}

	/**
	 * Crea el universo en el primer instante.
	 */
	private void crearUniverso() {
		Random dios = new Random();
		for (int i = 0; i < getTam(); i++) {
			for (int j = 0; j < getTam(); j++) {
				getMundo()[i][j] = dios.nextBoolean();
				//getMundo()[i][j] = false;
			}
		}
		iniciarNuevaGen();
	}

	/**
	 * Inicia la primera generación de celulas.
	 */
	private void iniciarNuevaGen() {
		for (int i = 0; i < getTam(); i++) {
			for (int j = 0; j < getTam(); j++) {
				getSiguienteGeneracion()[i][j] = getMundo()[i][j];
			}
		}
	}
	
	/**
	 * Muestra la generación actual.
	 */
	public void mostrar() {
		for (int i = 0; i < getTam(); i++) {
			System.out.println();
			for (int j = 0; j < getTam(); j++) {
				System.out.print(getMundo()[i][j]);
			}
		}
	}
	
	/**
	 * Da vida o muerte a una celula determinada.
	 * @param i Fila de la celula.
	 * @param j Columna de la celula.
	 */
	public void darVida(int i, int j) {
		if (getMundo()[i][j]) {
			getMundo()[i][j]  = false;
		} else {
			getMundo()[i][j]  = true;
		}
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
