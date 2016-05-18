/**
 * @author Erik Andreas Barreto de Vera.
 * E-mail: alu0100774054@ull.edu.es
 * Fecha: 18/05/2016
 * Asignatura: Programación de Aplicaciones Interactivas.
 * Comentario: Clase que contiene el panel donde se muestra el mundo.
 */

package es.esit.ull.GOL;

public class Main {

	public static void main(String[] args) {
		Interfaz interfaz = new Interfaz(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
		interfaz.setVisible(true);
	}

}
