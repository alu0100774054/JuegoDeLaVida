/**
 * @author Erik Andreas Barreto de Vera.
 * E-mail: alu0100774054@ull.edu.es
 * Fecha: 18/05/2016
 * Asignatura: Programación de Aplicaciones Interactivas.
 * Comentario: Clase que contiene la interfaz del juego.
 */

package es.esit.ull.GOL;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
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
	private Autor erik;
	private JButton botonInformacion;
	private ImageIcon info;

	public Interfaz(int delay, int tam) {
		this.delayNuevo = delay;
		this.tam = tam;
		pausado = false;
		iniciar();
		iniciarComponentes();
		iniciarOyentes();
	}

	/**
	 * Método que inicia los oyentes.
	 */
	private void iniciarOyentes() {
		setTimer(new Timer(getDelay(), new TimerListener()));
		getIniciarBoton().addActionListener(new IniciarListener());
		getSiguienteBoton().addActionListener(new SiguienteListener());
		getVelocidadSlider().addChangeListener(new VelocidadListener());
		getTamJuego().addKeyListener(new TamListener());
		getBotonInformacion().addActionListener(new InfoListener());
	}

	/**
	 * Método que inicializa los componentes de la interfaz.
	 */
	private void iniciarComponentes() {
		setErik(new Autor());
		setInfo(new ImageIcon("imagenes/info.png"));
		setBotonInformacion(new JButton(getInfo()));
		getBotonInformacion().setBorder(null);
		setMundo(new MundoGrafico(getTam()));
		setTamJuego(new JTextField("Tamaño del juego", 10));
		setVelocidadSlider(new JSlider(getMinVelocidad(), getMaxVelocidad()));
		setIniciarBoton(new JButton(getINI()));
		setSiguienteBoton(new JButton(getPASO()));
		setPanelControles(new JPanel());

		getPanelControles().setLayout(new GridLayout(1, 5));
		getPanelControles().setBackground(Color.LIGHT_GRAY);

		add(getMundo(), BorderLayout.CENTER);
		getPanelControles().add(getTamJuego());
		getPanelControles().add(getVelocidadSlider());
		getPanelControles().add(getIniciarBoton());
		getPanelControles().add(getSiguienteBoton());
		getPanelControles().add(getBotonInformacion());
		add(getPanelControles(), BorderLayout.PAGE_END);
	}

	/**
	 * Método que inicia la interfaz.
	 */
	private void iniciar() {
		setSize(new Dimension(1000, 800));
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
			getMundo().setCorriendo(true);
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
	class TamListener implements KeyListener {

		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void keyPressed(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				JTextField tam = (JTextField)e.getSource();
				if (Integer.parseInt(tam.getText()) > 50 || Integer.parseInt(tam.getText()) < 10) {
					JOptionPane.showMessageDialog(getMundo(), "El tamaño debe ser entre 10 y 50");
				} else {
					getTimer().stop();
					getMundo().reiniciar(Integer.parseInt(tam.getText()));
				}
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub

		}
	}

	class InfoListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			getErik().mostrar();
		}
		
	}
	/**
	 * Getter & Setter
	 */

	public JPanel getPanelControles() {
		return panelControles;
	}

	public ImageIcon getInfo() {
		return info;
	}

	public void setInfo(ImageIcon info) {
		this.info = info;
	}

	public JButton getBotonInformacion() {
		return botonInformacion;
	}

	public void setBotonInformacion(JButton botonInformacion) {
		this.botonInformacion = botonInformacion;
	}

	public Autor getErik() {
		return erik;
	}

	public void setErik(Autor erik) {
		this.erik = erik;
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
