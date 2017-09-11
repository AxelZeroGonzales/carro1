package entity;

import java.awt.Color;

public class Automovil {
	private int velocidad;
	private int gasolina;
	private int colorR;
	private int colorG;
	private int colorB;
	private double distancia;

	public Automovil(int gasolinaP) {
		this.gasolina = gasolinaP;
		this.velocidad = 5;
		this.colorR = 255;
		this.colorG = 0;
		this.colorB = 0;
		this.distancia = 0.0D;
	}

	public void asignarColor(int rP, int gP, int bP) {
		this.colorR = rP;
		this.colorG = gP;
		this.colorB = bP;
	}

	public void asignarVelocidad(int velocidadP) {
		this.velocidad = velocidadP;
	}

	public void asignarGasolina(int gasolinaP) {
		this.gasolina = gasolinaP;
	}

	public void asignarDistancia(double distanciaP) {
		this.distancia = distanciaP;
	}

	public Color darColor() {
		return new Color(this.colorR, this.colorG, this.colorB);
	}

	public int darGasolina() {
		return this.gasolina;
	}

	public int darVelocidad() {
		return this.velocidad;
	}

	public double calcularDistanciaAvance() {
		if (this.gasolina > 0) {
			this.distancia += this.velocidad;
		}
		return this.distancia;
	}

	public double calcularDistanciaDespuesAvance() {
		this.distancia -= this.velocidad;
		return this.distancia;
	}

	public int calcularGasolinaDespuesAvance() {
		if (this.gasolina > 0) {
			this.gasolina -= Math.abs(this.velocidad);
		} else {
			this.gasolina = 0;
		}
		return this.gasolina;
	}

}
