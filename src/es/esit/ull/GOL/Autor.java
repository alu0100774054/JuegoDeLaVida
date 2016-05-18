package es.esit.ull.GOL;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class Autor {

	private ImageIcon erik;
	private final String TITU = "Información acerca del programa:";
    private final String UNI = "\n\nUniversidad de La Laguna.";
    private final String FACUL = "\nEscuela Superior de Ingeniería Técnica.";
    private final String GRAD = "\nGrado en Ingeniería Informática.";
    private final String FECHA = "\nDía 9 de Mayo de 2016";
    private final String AUTOR = "\n\nErik Andreas Barreto de Vera";
    private final String CONTACTO = "\nalu0100774054@ull.edu.es";
    
    public Autor() {
		erik = new ImageIcon("imagenes/Erik.png");
	}
    public void mostrar() {
    	JOptionPane.showMessageDialog(null, getTITU() + getUNI() + getFACUL() + getGRAD() + getFECHA() + getAUTOR() + getCONTACTO(), "Acerca del autor", JOptionPane.INFORMATION_MESSAGE, getErik());
    }

	public ImageIcon getErik() {
		return erik;
	}
	public void setErik(ImageIcon erik) {
		this.erik = erik;
	}
	public String getTITU() {
		return TITU;
	}
	public String getUNI() {
		return UNI;
	}
	public String getFACUL() {
		return FACUL;
	}
	public String getGRAD() {
		return GRAD;
	}
	public String getFECHA() {
		return FECHA;
	}
	public String getAUTOR() {
		return AUTOR;
	}
	public String getCONTACTO() {
		return CONTACTO;
	}
    
    
}
