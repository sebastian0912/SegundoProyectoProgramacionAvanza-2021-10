package Model;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author GabyL
 */
public class Cuadrado extends Figura {
    LinkedList<Punto> PuntosC = new LinkedList<>();


	public Cuadrado(String nombre, LinkedList<Punto> pun ) {
		super(nombre, pun);
		
	}

                /**    
*Nombre: DibujarFigura()
 *Objetivo: pinta el cuadrado en el archivo
 *Entradas: nombre del archivo y lista de puntos 
 *Salida: void
 */
    @Override
    protected void DibujarFigura(LinkedList <Punto>x, String NombreArch) {
        LinkedList<Punto> Puntos = new LinkedList<>();
        LinkedList<Punto> Puntos1 = new LinkedList<>();
        System.out.println("Escogió el cuadrado, este se representará con el caracter ,");
        File archivo;
        BufferedWriter Fescribe;
        PrintWriter linea;
        archivo = new File(NombreArch+"Model.Dibujo"+".txt");
        if (!archivo.exists()) {
            try {
                Fescribe = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(archivo, true)));
                linea = new PrintWriter(Fescribe);   
                for (int i=0;i<x.size()-2;i++) {
                    Puntos.add(x.get(i));
                }
                for (int i=2;i<x.size();i++) {
                    Puntos1.add(x.get(i));
                }
                
                for (Punto point : Puntos) {
                    for (int i = 0; i < point.getX() - 1; i++) {
                        linea.print(" ");
                    }
                    linea.print(",");
                }
                linea.println();
                for (Punto point : Puntos1) {
                    for (int i = 0; i < point.getX() - 1; i++) {
                        linea.print(" ");
                    }
                    linea.print(",");
                }

                linea.close();
                Fescribe.close();

            } catch (IOException ex) {
                Logger.getLogger(Cuadrado.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {
            try {
                archivo.createNewFile();
                Fescribe = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(archivo, true)));
                linea = new PrintWriter(Fescribe);
                linea.println();
                for (int i=0;i<x.size()-2;i++) {
                    Puntos.add(x.get(i));
                }
                for (int i=2;i<x.size();i++) {
                    Puntos1.add(x.get(i));
                }
                
                for (Punto point : Puntos) {
                    for (int i = 0; i < point.getX() - 1; i++) {
                        linea.print(" ");
                    }
                    linea.print(",");
                }
                linea.println();
                for (Punto point : Puntos1) {
                    for (int i = 0; i < point.getX() - 1; i++) {
                        linea.print(" ");
                    }
                    linea.print(",");
                }

                linea.close();
                Fescribe.close();

            } catch (IOException ex) {
                Logger.getLogger(Cuadrado.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }	
}
