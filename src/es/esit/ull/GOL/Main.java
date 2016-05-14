package es.esit.ull.GOL;

public class Main {

	public static void main(String[] args) {
		Interfaz interfaz = new Interfaz(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
		interfaz.setVisible(true);
	}

}
