package entity;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;

import javax.swing.JLabel;

public class Carro {
	private double distancia;
	private Point posicion;
	private Image imagen;
	private Image imagenSeleccionado;
	private Image imagenNormal;
	private String instanceName;
	private Automovil instancia;
	private boolean selected;

	public Carro(Automovil auto) {
		this.selected = false;
		MediaTracker mt = new MediaTracker(new JLabel());
		Toolkit defaultToolkit = Toolkit.getDefaultToolkit();
		this.imagen = defaultToolkit
				.getImage(getClass().getResource("/image/Carro4.gif"));
		this.imagenNormal = this.imagen;

		mt.addImage(this.imagen, 1);
		try {
			mt.waitForAll();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		this.distancia = 0.0D;
		this.posicion = new Point();
		this.instancia = auto;
	}

	public void actualizarCarro(Automovil auto) {
		this.instancia = auto;
	}

	public void paint(Graphics2D g) {
		if (this.selected) {
			g.setColor(this.instancia.darColor());
			g.fillRect(this.posicion.x, this.posicion.y + 80, 90, 60);
			g.drawImage(this.imagen, this.posicion.x + 5, this.posicion.y + 80 +5 , null);
		} else { 
			g.setColor(this.instancia.darColor());
			g.fillRect(this.posicion.x, this.posicion.y + 80, 80, 50);
			g.drawImage(this.imagen, this.posicion.x, this.posicion.y + 80, null);
		}
	}

	public boolean hit(Point p) {
		Rectangle r = new Rectangle(new Point(this.posicion.x, this.posicion.y + 80));
		r.height = 100;
		r.width = 100;
		return r.contains(p);
	}

	public void tick() {
		this.distancia = this.instancia.calcularDistanciaAvance();
		if ((this.distancia > TrackPanel.getDistanciaPista()) || (this.distancia < 0.0D)) {
			this.distancia = this.instancia.calcularDistanciaDespuesAvance();
		} else {
			calcPosicion();
		}
	}

	private void calcPosicion() {
		int gasolina = this.instancia.calcularGasolinaDespuesAvance();
		if (gasolina <= 0) {
			this.instancia.asignarGasolina(0);
		}
		this.posicion.x = ((int) this.distancia);
	}

	public String getIntanceName() {
		return this.instanceName;
	}

	public void setIntanceName(String intanceName) {
		this.instanceName = intanceName;
	}

	public Automovil darAutomovil() {
		return this.instancia;
	}

	public void setPosicion(Point punto) {
		this.posicion = punto;
	}

	public Point getPosicion() {
		return this.posicion;
	}

	public void cambiarImagen(Image i) {
		this.imagen = i;
	}

	public Image darImagenNormal() {
		return this.imagenNormal;
	}

	public Image darImagenSeleccionado() {
		return this.imagenSeleccionado;
	}

	public boolean isSelected() {
		return this.selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}
}
