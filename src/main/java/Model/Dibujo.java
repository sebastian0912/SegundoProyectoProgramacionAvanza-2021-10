package Model;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;

/**
 *
 * @author GabyL
 */
public class Dibujo {
   	private String Nombre;
	private LinkedList<Figura> figuras = new LinkedList<Figura>();	
	private Usuario usu; 

        /**    
*Nombre: crearArchivoConPuntos()
 *Objetivo: crea a el archivo y pinta las figuras
 *Entradas: nombre del propietario y del archivo 
 *Salida: void
 */
	public void crearArchivoConPuntos(Usuario propi, String NombreDibujo) {
		FileWriter flwriter=null;  //Para que el archivo este en zero
		Scanner scan = new Scanner(System.in);
		
		 try{     	  
	            flwriter=new FileWriter(NombreDibujo+".txt"); //Abrir el archivo
	            BufferedWriter bfwriter=new BufferedWriter(flwriter);
	           
	            
	            bfwriter.write(propi.getNombre());
	            bfwriter.write(" -> ");
	            for(Dibujo dib: propi.getDibujosHechos()) {	           		
            			if(dib.Nombre.equals(NombreDibujo)) {
            				for(Figura fig: dib.getFiguras() ) {    
                				bfwriter.write(fig.getNombre());
                				bfwriter.write(" -> ");
                				for(Punto pun: fig.getPuntos()) {
                					bfwriter.write("("+pun.getX()+","+pun.getY()+")");    								
                				}
                	               bfwriter.newLine();           

                			}
            			}
            	}
	            
	            
	            bfwriter.close();
	            
	        }catch(Exception e){
	            System.out.println(" Ocurrio un error al escribir al archivo!");
	            System.out.println(" Informacion relevante para el desarrolador: ");
	            e.printStackTrace();
	        }finally{
	            try{
	              if (flwriter!=null)  
	                flwriter.close();
	            }catch (IOException e){
	              System.out.println("Ocurrió un error al cerrar al archivo!");
	            }
	        }
	}
	
        /**    
*Nombre: eliminarFigura()
 *Objetivo: eliminar la figura del archivo
 *Entradas: nombre del propietario,de la figura y del archivo 
 *Salida: un objeto Model.Figura
 */
        public Figura eliminarFigura(String NombreFigura, Usuario ususe, String NombreDibujo) {    		
		for(Dibujo dibujito: ususe.getDibujosHechos()) {
			if(NombreDibujo.equals(dibujito.getNombre())){
				for(Figura fig:dibujito.getFiguras()){
					if(NombreFigura.equals(fig.getNombre())){
						dibujito.getFiguras().remove(fig);
						return fig;
					}
                }      
             }
		}
		return null;  
	}
	
                /**    
*Nombre: crearDibujo()
 *Objetivo: crea el dibujo en un archivo
 *Entradas: nombre del propietario y un usuario 
 *Salida: un objeto dibujo
 */
	public Dibujo crearDibujo(String nombre, Usuario usu) {		
		return new Dibujo(nombre, usu);
	}
	
                /**    
*Nombre: mostrarDibujos
 *Objetivo: imprimir los dibujos
 *Entradas: nombre del propietario 
 *Salida: void
 */
	public void mostrarDibujos(Usuario propi) {				
		for(Dibujo dib: propi.getDibujosHechos()) {
			if(propi.getDibujosHechos().isEmpty()) {
				System.out.println("No tiene dibujos Hechos");
			}
			System.out.println(dib.getNombre()) ;
		}
	}	
	
                /**    
*Nombre: verificarUsuarioDeDibujo()
 *Objetivo: verifica si el usuario es el mismo usuario qie pinto en el dibujo
 *Entradas: un usuario y la lista usuarios  
 *Salida: un booleano
 */
        public boolean verificarUsuarioDeDibujos(LinkedList<Usuario>usuas, Usuario usu) {
		for(Usuario usa: usuas) {
			if(usuas.contains(usu) ) {
				return true; 
			}
		}
		return false;
	}
	
	
	
	public Dibujo(Usuario usu) {
		
		this.usu = usu;
	}

	public Dibujo(String nombre) {
		
		Nombre = nombre;
	}
	
	public Dibujo(String nombre, Usuario usu) {
		super();
		Nombre = nombre;
		this.usu = usu;
	}

	public Usuario getUsu() {
		return usu;
	}
	public void setUsu(Usuario usu) {
		this.usu = usu;
	}
	
	public String getNombre() {
		return Nombre;
	}
	public void setNombre(String nombre) {
		Nombre = nombre;
	}
	public LinkedList<Figura> getFiguras() {
		return figuras;
	}
	public void setFiguras(LinkedList<Figura> figuras) {
		this.figuras = figuras;
	}
		
}
