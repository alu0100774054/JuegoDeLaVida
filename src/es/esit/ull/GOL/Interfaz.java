package es.esit.ull.GOL;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Interfaz extends JFrame {
	private MundoGrafico mundo;
	private JSlider velocidadSlider;
	private JButton iniciarBoton;
	private JButton siguienteBoton;
	private JPanel panelControles;
	private JTextField tamJuego;
	private final String VEL = "Velocidad";
	private final String INI = "Iniciar";
	private final String PASO = "Mostrar siguiente generación";
	private Timer timer;
	private boolean pausado;
	private int delay = 1000;
	private int delayNuevo;
	private int minVelocidad = 1;
	private int maxVelocidad = 100;
	private int tam;
	
	public Interfaz(int delay, int tam) {
		this.delayNuevo = delay;
		this.tam = tam;
		pausado = false;
		iniciar();
		iniciarComponentes();
		iniciarOyentes();
	}

	private void iniciarOyentes() {
		setTimer(new Timer(getDelay(), new TimerListener()));
		getIniciarBoton().addActionListener(new IniciarListener());
		getSiguienteBoton().addActionListener(new SiguienteListener());
		getVelocidadSlider().addChangeListener(new VelocidadListener());
		getTamJuego().addActionListener(new TamListener());
	}

	private void iniciarComponentes() {
		setMundo(new MundoGrafico(getTam()));
		setTamJuego(new JTextField("Tamaño del juego", 10));
		setVelocidadSlider(new JSlider(getMinVelocidad(), getMaxVelocidad()));
		setIniciarBoton(new JButton(getINI()));
		setSiguienteBoton(new JButton(getPASO()));
		setPanelControles(new JPanel());
		
		getPanelControles().setLayout(new GridLayout(1, 4));
		getPanelControles().setBackground(Color.LIGHT_GRAY);
		
		add(getMundo(), BorderLayout.CENTER);
		getPanelControles().add(getTamJuego());
		getPanelControles().add(getVelocidadSlider());
		getPanelControles().add(getIniciarBoton());
		getPanelControles().add(getSiguienteBoton());
		add(getPanelControles(), BorderLayout.PAGE_END);
	}

	private void iniciar() {
		setSize(new Dimension(600, 600));
		setLayout(new BorderLayout());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("El Juego de la vida");
	}
	
	class TimerListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			repaint();
		}
		
	}
	
	class IniciarListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			getTimer().start();
		}
		
	}
	class SiguienteListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			getTimer().stop();
			repaint();
		}
		
	}
	
	class VelocidadListener implements ChangeListener {

		@Override
		public void stateChanged(ChangeEvent e) {
			JSlider vel = (JSlider)e.getSource();
			setDelayNuevo(getDelay() / vel.getValue());
			getTimer().setDelay(getDelayNuevo());
			System.out.println(getDelayNuevo());
			getTimer().start();
		}
		
	}
	class TamListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			JTextField tam = (JTextField)e.getSource();
			getTimer().stop();
			getMundo().reiniciar(Integer.parseInt(tam.getText()));
		}
		
	}
	
	/**
	 * Getter & Setter
	 */
	
	public JPanel getPanelControles() {
		return panelControles;
	}

	public int getTam() {
		return tam;
	}

	public void setTam(int tam) {
		this.tam = tam;
	}

	public JTextField getTamJuego() {
		return tamJuego;
	}

	public void setTamJuego(JTextField tamJuego) {
		this.tamJuego = tamJuego;
	}

	public int getDelayNuevo() {
		return delayNuevo;
	}

	public void setDelayNuevo(int delayNuevo) {
		this.delayNuevo = delayNuevo;
	}

	public void setPanelControles(JPanel panelControles) {
		this.panelControles = panelControles;
	}

	public int getMinVelocidad() {
		return minVelocidad;
	}

	public void setMinVelocidad(int minVelocidad) {
		this.minVelocidad = minVelocidad;
	}

	public int getMaxVelocidad() {
		return maxVelocidad;
	}

	public void setMaxVelocidad(int maxVelocidad) {
		this.maxVelocidad = maxVelocidad;
	}

	public MundoGrafico getMundo() {
		return mundo;
	}

	public void setMundo(MundoGrafico mundo) {
		this.mundo = mundo;
	}

	public JSlider getVelocidadSlider() {
		return velocidadSlider;
	}

	public void setVelocidadSlider(JSlider velocidadSlider) {
		this.velocidadSlider = velocidadSlider;
	}

	public JButton getIniciarBoton() {
		return iniciarBoton;
	}

	public void setIniciarBoton(JButton iniciarBoton) {
		this.iniciarBoton = iniciarBoton;
	}

	public JButton getSiguienteBoton() {
		return siguienteBoton;
	}

	public void setSiguienteBoton(JButton siguienteBoton) {
		this.siguienteBoton = siguienteBoton;
	}

	public Timer getTimer() {
		return timer;
	}

	public void setTimer(Timer timer) {
		this.timer = timer;
	}

	public boolean isPausado() {
		return pausado;
	}

	public void setPausado(boolean pausado) {
		this.pausado = pausado;
	}

	public int getDelay() {
		return delay;
	}

	public void setDelay(int delay) {
		this.delay = delay;
	}

	public String getVEL() {
		return VEL;
	}

	public String getINI() {
		return INI;
	}

	public String getPASO() {
		return PASO;
	}
	
	
}
