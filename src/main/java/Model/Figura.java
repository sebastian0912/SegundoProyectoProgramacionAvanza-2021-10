package Model;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Model.Dibujo;

import java.util.LinkedList;

/**
 *
 * @author GabyL
 */
public abstract class Figura {
	private String nombre;
    private Dibujo correspondeAdibujo;
    private LinkedList<Punto> Puntos = new LinkedList<>();   

    
    public Figura() {
		super();
	}  
    
	public Figura(String nombre, LinkedList<Punto> puntos) {		
		this.nombre = nombre;
		Puntos = puntos;
	}

	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public Figura(LinkedList<Punto> puntos) {
		super();
		Puntos = puntos;
	}

	public Dibujo getCorrespondeAdibujo() {
        return correspondeAdibujo;
    }

    public void setCorrespondeAdibujo(Dibujo correspondeAdibujo) {
        this.correspondeAdibujo = correspondeAdibujo;
    }

    public LinkedList<Punto> getPuntos() {
        return Puntos;
    }

    public void setPuntos(LinkedList<Punto> puntos) {
        Puntos = puntos;
    }
    
    protected abstract void DibujarFigura(LinkedList <Punto>punti, String NombreArch);
}
