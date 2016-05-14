package es.esit.ull.GOL;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

public class MundoGrafico extends JPanel {
	private Mundo mundo;
	private int proporcionX;
	private int proporcionY;
	private int tam;
	private boolean iniciado;
	
	public MundoGrafico(int tam) {
		this.tam = tam;
		mundo = new Mundo(tam);
		iniciado = false;
		addMouseListener(new CrearVidaListener());
	}
	
	public void reiniciar(int tam) {
		mundo = new Mundo(tam);
	}
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (!isIniciado()) {
			dibujarUniverso(g);
			dibujarVida(g);
			dibujarUniverso(g);
			setIniciado(true);
		} else {
			getMundo().siguiente();
			dibujarVida(g);
		}	
	}

	private void dibujarUniverso(Graphics g) {
		
		
		setProporcionX(this.getWidth() / getTam());
		setProporcionY(this.getHeight() / getTam());
		
		int inicioX = 0;
		int inicioY = 0;
		for (int i = 0; i < getTam(); i++) {
			g.setColor(Color.DARK_GRAY);
			g.fillRect(inicioX, inicioY, getProporcionX(), getProporcionY());
			g.setColor(Color.BLACK);
			g.drawLine(inicioX, 0, inicioX, this.getHeight());
			g.drawLine(0, inicioY, this.getWidth(), inicioY);
			inicioX += getProporcionX();
			inicioY += getProporcionY();
		}
	}

	private void dibujarVida(Graphics g) {
		int inicioX = 0;
		int inicioY = -getProporcionY();
		
		for (int i = 0; i < getMundo().getTam(); i++) {
			inicioX = 0;
			inicioY += getProporcionY();
			for (int j = 0; j < getMundo().getTam(); j++) {
				if (getMundo().getSiguienteGeneracion()[i][j]) {
					g.setColor(Color.YELLOW);
					g.fillRect(inicioX, inicioY, getProporcionX(), getProporcionY());
					g.setColor(Color.BLACK);
					g.drawLine(inicioX, 0, inicioX, this.getHeight());
					g.drawLine(0, inicioY, this.getWidth(), inicioY);
				} else {
					g.setColor(Color.BLACK);
					g.fillRect(inicioX, inicioY, getProporcionX(), getProporcionY());
					g.setColor(Color.BLACK);
					g.drawLine(inicioX, 0, inicioX, this.getHeight());
					g.drawLine(0, inicioY, this.getWidth(), inicioY);
				}
				inicioX += getProporcionX();
				
			}
		}
	}

	class CrearVidaListener implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			int  x = e.getX() / getProporcionX();
			int y = e.getY() / getProporcionY();
			System.out.println("Proporciones: "+ getProporcionX() + "-" + getProporcionY() + ", puntos = "  + e.getX() + "," + e.getY() + "--->" + x + "," + y);
			getMundo().darVida((int) x,(int) y);
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

	public boolean isIniciado() {
		return iniciado;
	}

	public void setIniciado(boolean iniciado) {
		this.iniciado = iniciado;
	}

	public void setTam(int tam) {
		this.tam = tam;
	}

	public int getProporcionX() {
		return proporcionX;
	}

	public void setProporcionX(int proporcionX) {
		this.proporcionX = proporcionX;
	}

	public int getProporcionY() {
		return proporcionY;
	}

	public void setProporcionY(int proporcionY) {
		this.proporcionY = proporcionY;
	}

	public Mundo getMundo() {
		return mundo;
	}

	public void setMundo(Mundo mundo) {
		this.mundo = mundo;
	}
	
	
}
