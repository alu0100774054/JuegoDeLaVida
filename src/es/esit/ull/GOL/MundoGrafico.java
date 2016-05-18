/**
 * @author Erik Andreas Barreto de Vera.
 * E-mail: alu0100774054@ull.edu.es
 * Fecha: 18/05/2016
 * Asignatura: Programación de Aplicaciones Interactivas.
 * Comentario: Clase que contiene el panel donde se muestra el mundo.
 */

package es.esit.ull.GOL;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class MundoGrafico extends JPanel {
	private Mundo mundo;
	private int celTamX;
	private int celTamY;
	private int tam;
	private boolean iniciado;
	private int anchoVentana;
	private int altoVentana;
	private int bordeX;
	private int bordeY;
	private boolean corriendo;

	public MundoGrafico(int tam) {
		this.tam = tam;
		mundo = new Mundo(tam);
		iniciado = false;
		corriendo = false;
		addMouseListener(new CrearVidaListener());
	}

	/**
	 * Reinicia el juego.
	 * @param tam tam del juego
	 */
	public void reiniciar(int tam) {
		mundo = new Mundo(tam);
		this.tam = tam;
		iniciado = false;
		corriendo = false;
		repaint();
	}
	
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (!isIniciado()) {
			dibujarUniverso(g);
			setIniciado(true);
		} else {
			if (!isCorriendo()) {
				dibujarUniverso(g);
			} else {
				getMundo().siguiente();
				dibujarUniverso(g);
			}
		}	
	}

	/**
	 * Dibuja el universo del juego de la vida. 
	 * @param g
	 */
	private void dibujarUniverso(Graphics g) {
		comprobarEscala();
		comprobarBorde();
		int inicioX = bordeX;
		int inicioY = bordeY;
		for (int i = 0; i < getTam(); i++) {
			inicioX = getBordeX();
			if (i > 0) {
				inicioY += getCelTamY();
			}
			for (int j = 0; j < getTam(); j++) {
				if (getMundo().getMundo()[i][j]) {
					g.setColor(Color.YELLOW);
					g.fillRect(inicioX, inicioY, getCelTamX(), getCelTamY());
				} else {
					g.setColor(Color.DARK_GRAY);
					g.fillRect(inicioX, inicioY, getCelTamX(), getCelTamY());
				}
				inicioX += getCelTamX();
			}
		}


		inicioX = bordeX;
		inicioY = bordeY;
		for (int i = 0; i < getTam(); i++) {
			g.setColor(Color.BLACK);
			g.drawLine(bordeX, inicioY, getAnchoVentana() + bordeX, inicioY);
			g.drawLine(inicioX, bordeY, inicioX, getAltoVentana() + bordeY);
			inicioX += celTamX;
			inicioY += celTamY;
		}
	}

	/**
	 * Método que ajusta el borde a un valor entero con división exacta.
	 */
	private void comprobarBorde() {
		int bordeX = getBordeX();
		while (bordeX % 2 != 0) {
			bordeX -= 1;
		}
		setBordeX(bordeX / 2);
		int bordeY = getBordeY();
		while (bordeY % 2 != 0) {
			bordeY -= 1;
		}
		setBordeY(bordeY / 2);
	}

	/**
	 * Método que ajusta el ancho y alto de la ventana a un valor entero con división exacta.
	 */
	private void comprobarEscala() {
		setAnchoVentana(this.getWidth());
		int nuevoAncho = 0;
		setBordeX(0);
		while (getAnchoVentana() % getTam() != 0) {
			setAnchoVentana(getAnchoVentana() - 1);
			setBordeX(getBordeX() + 1);
		}
		setCelTamX(getAnchoVentana() / getTam());

		setAltoVentana(this.getHeight());
		int altoNuevo = 0;
		setBordeY(0);
		while (getAltoVentana() % getTam() != 0) {
			setAltoVentana(getAltoVentana() - 1);
			setBordeY(getBordeY() + 1);
		}
		setCelTamY(getAltoVentana() / getTam());
	}

	class CrearVidaListener implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			int fila = e.getX() / getCelTamX();
			int col = e.getY() / getCelTamY();
			System.out.println("vive [" + col + ", " + fila + "]");
			getMundo().darVida(col, fila);
			repaint();
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub

		}

	}

	/*
	 * Getter & Setter
	 */

	public int getTam() {
		return tam;
	}


	public boolean isCorriendo() {
		return corriendo;
	}

	public void setCorriendo(boolean corriendo) {
		this.corriendo = corriendo;
	}

	public int getBordeX() {
		return bordeX;
	}

	public void setBordeX(int bordeX) {
		this.bordeX = bordeX;
	}

	public int getBordeY() {
		return bordeY;
	}

	public void setBordeY(int bordeY) {
		this.bordeY = bordeY;
	}

	public int getAnchoVentana() {
		return anchoVentana;
	}

	public void setAnchoVentana(int anchoVentana) {
		this.anchoVentana = anchoVentana;
	}

	public int getAltoVentana() {
		return altoVentana;
	}

	public void setAltoVentana(int altoVentana) {
		this.altoVentana = altoVentana;
	}

	public boolean isIniciado() {
		return iniciado;
	}

	public void setIniciado(boolean iniciado) {
		this.iniciado = iniciado;
	}

	public void setTam(int tam) {
		this.tam = tam;
	}



	public int getCelTamX() {
		return celTamX;
	}

	public void setCelTamX(int celTamX) {
		this.celTamX = celTamX;
	}

	public int getCelTamY() {
		return celTamY;
	}

	public void setCelTamY(int celTamY) {
		this.celTamY = celTamY;
	}

	public Mundo getMundo() {
		return mundo;
	}

	public void setMundo(Mundo mundo) {
		this.mundo = mundo;
	}

}
