package Model;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Usuario {	
	
	private String nombre; 
	private String correoElectonico; 
	private String clave; 
	private String fechaRegistro; 
	private int admin; 
	private Dibujo dueno;
	private LinkedList<Usuario> usus =new LinkedList<>();
	private LinkedList<Dibujo> dibujosHechos= new LinkedList<>();
	Usuario objeto = null;	
	
	File FicheroUsuario = new File("usuarios.txt");
	
	  /**    
*Nombre: InsertarUsuario()
 *Objetivo: Ingresar un Model.Usuario a la lista
 *Entradas: No tiene parametros
 *Salida: Un objeto tipo Model.Usuario
 */ 
        public Usuario InsertarUsuario() {		
		int admin; 
		String correoElectonico , clave , fechaRegistro, nombre; 
		Scanner scan = new Scanner(System.in);		
	    try {
	    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
	    	fechaRegistro = sdf.format(new Date());	
			
			System.out.print(" Escriba su nombre: ");		
			nombre = scan.nextLine();
			System.out.print(" Escriba su correo: ");
			correoElectonico = scan.nextLine();
			System.out.print(" Escriba la clave: ");
			clave = scan.nextLine();
			
			System.out.print(" Escriba si es admin 1: ");
			admin = scan.nextInt();	
			
	        BufferedWriter Fescribe = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(FicheroUsuario, true)));
	        
	        Fescribe.write(nombre + "," + correoElectonico + "," + clave + ","+ admin +","+ fechaRegistro  + "\r\n");
	        System.out.println(" El usuario ha sido insertado en la base de datos");	       
	        Fescribe.close();	
	        return new Usuario(nombre, correoElectonico, clave, fechaRegistro,admin ); 
	    } catch (Exception ex) {
	        System.out.println(ex.getMessage());
	    }
	    
	    return null; 
	}
	
	  /**    
*Nombre: buscarUsuario()
 *Objetivo: busca un Model.Usuario dentro de la lista
 *Entradas: String 
 *Salida: Un objeto tipo Model.Usuario
 */ 
        public Usuario buscarUsuario( String correo){	
		for(Usuario usu: usus ) {
			if(correo.equals(usu.getCorreoElectonico())  ) {
				return usu;				
			}
		}
        return null;
    }	
		  /**    
*Nombre: eliminar()
 *Objetivo: eliminar un Model.Usuario de la lista
 *Entradas: No tiene parametros
 *Salida: Un objeto tipo Model.Usuario
 */ 
	public Usuario eliminar(String Correo) {
			for(Usuario usu: usus) {				
				usus.remove(buscarUsuario(Correo));		        
				return usu;
				
		}	
		return null; 
	}
	
	  /**    
*Nombre: PasardelarchivoaLista()
 *Objetivo: Grabar la lista de usuarios dentro de un archivo, para que se conserven los datos (persistencia)
 *Entradas: No tiene parametros
 *Salida: void
 */ 
        public void PasardelarchivoaLista() {
		File FicheroUsuario = new File("usuarios.txt");
	    try {
	        String linea = null;
	        BufferedReader leerFichero = new BufferedReader(new FileReader(FicheroUsuario));
	        while ((linea = leerFichero.readLine()) != null) {
	            //este bucle es para meter todos los datos leidos de archivo de texto separlo en atributos y convertirlos a objeto para poder trabajar con el 
	            //en esta parte le digo que lo separe en tokens pero ojo estos tokens son solo Strings tengo que convertirlos para poder emterlos en el objeto y trabajar con ellos
	            StringTokenizer mistokens = new StringTokenizer(linea, ",");
	            String Nombre = mistokens.nextToken().trim();
	            String Correo = mistokens.nextToken().trim();
	            String Clave = mistokens.nextToken().trim();
	            int admin = Integer.parseInt(mistokens.nextToken().trim()) ;
	            String Fecha = mistokens.nextToken().trim();
	            objeto = new Usuario(Nombre, Correo, Clave, Fecha, admin);
	            usus.add(objeto);	
	        }
	        leerFichero.close();
	
	    } catch (Exception ex) {
	        System.out.println(ex.getMessage());
	    }
	}
		
	  /**    
*Nombre: ActualizarLista()
 *Objetivo: limpia y acutaliza la lista con los datos del archivo
 *Entradas: No tiene parametros
 *Salida: void
 */ 
        public void ActualizarLista()	{
			usus.clear();
			PasardelarchivoaLista(); 
	}
	
	  /**    
*Nombre: mostrarsUusuarios()
 *Objetivo: imprimir usuarios 
 *Entradas: No tiene parametros
 *Salida: void
 */ 
        public void mostrarUsuarios() {
		if (usus.isEmpty()) 
            System.out.println("Lista vacia");
           
		for(Usuario usu: usus) {
			System.out.println(" Nombre: "+ usu.getNombre() + "\n Correo: "+ usu.getCorreoElectonico() + "\n Fecha de registro: "+ usu.getFechaRegistro()+ "\n Admin: "+ usu.getAdmin()+ "\n");
		}
	}
	
	  /**    
*Nombre: AtenderPaciente()
 *Objetivo: dar estatus del paciente
 *Entradas: Stirng correo, String clave
 *Salida: Un objeto tipo Model.Usuario
 */ 
        public Usuario buscarUsuario( String correo, String clave){   
		
		for(Usuario usu: usus ) {
			if(correo.equals(usu.getCorreoElectonico()) && clave.equals(usu.getClave()) ) {
				return usu;				
			}
		}
        return null;
    }
		
	  /**    
*Nombre: eliminarEnArchivo()
 *Objetivo: eliminar usuarios del archivo 
 *Entradas: No tiene parametros
 *Salida: void
 */ 
        public void eliminarEnArchivo() {
		FileWriter flwriter=null;  //Para que el archivo este en zero
        try{
     	   
            flwriter=new FileWriter("usuarios.txt"); //Abrir el archivo
            BufferedWriter bfwriter=new BufferedWriter(flwriter);
            
            
            for(Usuario usu: usus) {         	   
               bfwriter.write(usu.getNombre() );  
               bfwriter.write(",");
               bfwriter.write(usu.getCorreoElectonico() );  
               bfwriter.write(",");
               bfwriter.write(usu.getClave() );  
               bfwriter.write(",");
               bfwriter.write(String.valueOf(usu.getAdmin()) );     
         	   bfwriter.write(",");
               bfwriter.write(usu.getFechaRegistro() );
         	   bfwriter.newLine();             	   
            }	               	            
            
            bfwriter.close();
            System.out.println(" Archivo creado correctamente");
        }catch(Exception e){
            System.out.println(" Ocurrio un error al escribir al archivo!");
            System.out.println(" Informacion relevante para el desarrolador: ");
            e.printStackTrace();
        }finally{
            try{
              if (flwriter!=null)  
                flwriter.close();
            }catch (IOException e){
              System.out.println("OcurriÃ³ un error al cerrar al archivo!");
            }
        }
		
	}
	
	  /**    
*Nombre: puntos()
 *Objetivo: crea una lista con los puntos ingresados por el usuarios
 *Entradas: No tiene parametros
 *Salida: una lista de los puntos de la figura
 */ 
        public LinkedList<Punto> puntos(){
		System.out.println("EscogiÃ³ el cuadrado, este se representarÃ¡ con el caracter ,");
        Scanner cin = new Scanner(System.in);
        LinkedList<Punto> PuntosC = new LinkedList<>();

        for (int i = 0; i < 2; i++) {
            int x, y;
            System.out.println("Digite la x del punto " + (i + 1));
            x = cin.nextInt();
            System.out.println("Digite la y del punto " + (i + 1));
            y = cin.nextInt();
            Punto puntoTemp = new Punto(x, y);
            PuntosC.add(puntoTemp);
        }
        for (int i = 0; i < 2; i++) {
            int x, y;
            System.out.println("Digite la x del punto " + (i + 1));
            x = cin.nextInt();
            System.out.println("Digite la y del punto " + (i + 1));
            y = cin.nextInt();
            Punto puntoTemp = new Punto(x, y);
            PuntosC.add(puntoTemp);
        }
        return PuntosC;		
	}
        
	  /**    
*Nombre: puntosT()
 *Objetivo: crea una lista con los puntos ingresados por el usuarios para el triangulo
 *Entradas: No tiene parametros
 *Salida: una lista de los puntos de la figura
 */
        public LinkedList<Punto> puntosT(){
		System.out.println("EscogiÃ³ el cuadrado, este se representarÃ¡ con el caracter ,");
        Scanner cin = new Scanner(System.in);
        LinkedList<Punto> PuntosT = new LinkedList<>();

        for (int i = 0; i < 1; i++) {
            int x, y;
            System.out.println("Digite la x del punto " + (i + 1));
            x = cin.nextInt();
            System.out.println("Digite la y del punto " + (i + 1));
            y = cin.nextInt();
            Punto puntoTemp = new Punto(x, y);
            PuntosT.add(puntoTemp);
        }
        for (int i = 0; i < 2; i++) {
            int x, y;
            System.out.println("Digite la x del punto " + (i + 1));
            x = cin.nextInt();
            System.out.println("Digite la y del punto " + (i + 1));
            y = cin.nextInt();
            Punto puntoTemp = new Punto(x, y);
            PuntosT.add(puntoTemp);
        }
        return PuntosT;		
	}
	
	  /**    
*Nombre: buscarDibujo()
 *Objetivo: buscar el dibujo
 *Entradas: String Nombre y propietario del dibujo
 *Salida:  Un objeto tipo Model.Dibujo
 */ 
        public Dibujo buscarDibujo(Usuario propi, String nombre){
		for(Usuario us: usus) {
			if(us.getNombre().equals(propi.getNombre()) ) {
				for(Dibujo dib: us.getDibujosHechos()) {
					if(dib.getNombre().equals(nombre)) {
						return dib; 						
					}						
				}
			}
		}
		return null;
	}
	
	  /**    
*Nombre: CrearDibujo()
 *Objetivo: dar estatus del paciente
 *Entradas: propietario del dibujo
 *Salida: void 
 */ 
        public void CrearDibujo(Usuario propi) {
		FileWriter flwriter=null;  //Para que el archivo este en zero
		Scanner scan = new Scanner(System.in);
		Scanner scan1 = new Scanner(System.in);
		Scanner scan2 = new Scanner(System.in);
        Scanner scan3 = new Scanner(System.in);
        Scanner scan4 = new Scanner(System.in);
        Scanner scan5 = new Scanner(System.in);
		int target;
        String edit;
		boolean bandera; 		
		System.out.println("Desea editar una figura ya creada digite s");
        edit=scan5.nextLine();
        String nombreDibujo;
        if (edit.equals("s")){
            System.out.println("Digite el nombre del dibujo que va a editar");	
        	nombreDibujo = scan.nextLine();

        }
        else {
    		System.out.println("Digite el nombre del dibujo que va a realizar");
    		nombreDibujo = scan.nextLine();
			propi.setDueno(new Dibujo(nombreDibujo));
           					
        	propi.getDibujosHechos().add(propi.getDueno().crearDibujo(nombreDibujo, propi)) ;
        	
 
        }
		
       	System.out.println("Quiere hacer figuras? s: si");	
		String opcion= scan1.nextLine();
		if(opcion.equals("s")) {
			do {	
                       
	            System.out.println(" 1. Model.Cuadrado");
	            System.out.println(" 2. Model.Rectangulo");
                    System.out.println(" 3. triangulo");
                    System.out.println(" 4. Eliminar");
                    System.out.println(" 5. Para salir");
	            System.out.print("Opcion: ");
	            target=scan.nextInt();   
                    LinkedList <Punto>temp=new LinkedList<>();
                    String tempo;
	            switch(target)
	            {
	            case 1: 
                    System.out.println("Digite el nombre de la figura");   
                    String nombrefiguraC = scan1.nextLine();
                    String guardar;
                    
                    temp=this.puntos();
            	
                        System.out.println("Digite s si desea guardar y n si no: ");
                        guardar= scan2.nextLine();
                        if (guardar.equals("s")){
                            buscarDibujo(propi, nombreDibujo).getFiguras().add(new Cuadrado(nombrefiguraC, temp));

                            this.buscarDibujo(propi, nombreDibujo).crearArchivoConPuntos(propi, nombreDibujo);
                            this.buscarFigura(propi, nombreDibujo,nombrefiguraC).DibujarFigura(temp, nombreDibujo);
                        }
                        break;
            case 2:
                    System.out.println("Digite el nombre de la figura");   
                    String nombrefiguraR = scan1.nextLine();
                    temp=this.puntos();
                    String guardarr;
            	
            	
                        System.out.println("Digite s si desea guardar y n si no: ");
                        guardarr= scan3.nextLine();
                        if (guardarr.equals("s")){
                            buscarDibujo(propi, nombreDibujo).getFiguras().add(new Rectangulo(nombrefiguraR, temp));

                            this.buscarDibujo(propi, nombreDibujo).crearArchivoConPuntos(propi, nombreDibujo);
                            this.buscarFigura(propi, nombreDibujo,nombrefiguraR).DibujarFigura(temp, nombreDibujo);
                        }
            	
                break; 
            case 3: 
                  System.out.println("Digite el nombre de la figura");   
                    String nombrefiguraT = scan1.nextLine();
                    temp=this.puntosT();
                    String guardart;       
            	
            	
                        System.out.println("Digite s si desea guardar y n si no: ");
                        guardart= scan4.nextLine();
                        if (guardart.equals("s")){
                        	buscarDibujo(propi, nombreDibujo).getFiguras().add(new Triangulo(nombrefiguraT, temp));
                            this.buscarDibujo(propi, nombreDibujo).crearArchivoConPuntos(propi, nombreDibujo);
                            this.buscarFigura(propi, nombreDibujo,nombrefiguraT).DibujarFigura(temp, nombreDibujo);
                        }
            	
                        
                break; 
                    case 4:     
                    
                    System.out.println("DIGITE EL NOMBRE DE LA FIGURA: ");
                     String NombreFigura=scan1.nextLine();
                    
                     eliminarFigura(NombreFigura,propi,nombreDibujo);
                     this.buscarDibujo(propi, nombreDibujo).crearArchivoConPuntos(propi, nombreDibujo);
                                              
                      tempo=nombreDibujo+"Model.Dibujo";
                      clearTheFile(tempo);
                      this.Figurita(propi, nombreDibujo);
                         break;

	                                 
	            }
	            System.out.println("");
	        }while(target !=5);
		}
		
	}
    
	  /**    
*Nombre: clearTheFile()
 *Objetivo: dar estatus del paciente
 *Entradas: nombres del archivo
 *Salida: void
 */ 
        public static void clearTheFile(String nombreArch) {
        nombreArch=nombreArch+".txt";
        FileWriter fwOb = null; 
            try {
                fwOb = new FileWriter(nombreArch, false);
            } catch (IOException ex) {
                Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
            }
        PrintWriter pwOb = new PrintWriter(fwOb, false);
        pwOb.flush();
        pwOb.close();
            try {
                fwOb.close();
            } catch (IOException ex) {
                Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
        
	  /**    
*Nombre: buscarFigura()
 *Objetivo: buscar figura dentro de dibujo
 *Entradas: nombre de la figura, el propietario y del archivo 
 *Salida: objeto figura
 */ 
        public Figura buscarFigura(Usuario propi, String nombre, String nombrefig) {
        if(this.buscarDibujo(propi, nombre) !=null) {
            for(Figura fig: this.buscarDibujo(propi, nombre).getFiguras()) {
                if (nombrefig.equals(fig.getNombre())){
                    return fig; 
                }   
            }
        }
        return null;
        }
		
	  /**    
*Nombre: mostrarDibujos()
 *Objetivo: imprimir propietarios
 *Entradas: propietario
 *Salida: void
 */ 
        public void mostrarDibujos(Usuario propi) {
		for(Usuario us: usus ) {
			if(usus.isEmpty()) {
				System.out.println("No hay usuarios");
			}
			if(usus.contains(propi)) {
				System.out.println(us.getNombre());
				for(Dibujo dib: propi.getDibujosHechos()) {
					if(usus.isEmpty()) {
						System.out.println("No tiene dibujos Hechos");
					}
					System.out.println(dib.getNombre()) ;

					
				}
			}
			
		}
	}
		
	 
	  /**    
*Nombre: eliminarFigura()
 *Objetivo: dar estatus del paciente
 *Entradas: Nombre de la figura, un usuario y el nombre del Model.Dibujo
 *Salida: void
 */ 
        public void eliminarFigura(String NombreFigura, Usuario usu, String NombreDibujo) {
    	this.buscarDibujo(usu, NombreDibujo).eliminarFigura(NombreFigura, usu, NombreDibujo);
    }
        
     /**    
*Nombre: Figurita()
 *Objetivo: buscar las figuras no eliminadas dentro del archivo y los vuelve a escribir en el archivo nuevo
 *Entradas: un usuario y el nombre del dibujo
 *Salida: void
 */ 
    public void Figurita( Usuario usu, String NombreDibujo) {
    NombreDibujo=NombreDibujo;
		for(Usuario ususe: this.usus) {	
	        if(ususe.nombre.equals(usu.nombre)){
	          for(Dibujo dibujito: ususe.getDibujosHechos()) {
	              if(NombreDibujo.equals(dibujito.getNombre())){
	            	  for(Figura fig:dibujito.getFiguras()){                    
	                    fig.DibujarFigura(fig.getPuntos(), NombreDibujo);
	                    for (Punto p:fig.getPuntos()){
	                        System.out.println(p.getX()+","+p.getY());
	                    }                    
	                  }      
	              }
	          }	   		
			} 
		}
    }
     
    
    
    
    
	
    public Usuario() {
		super();
	}
	public Usuario(String nombre, String correoElectonico, String clave, String fechaRegistro, int admin) {
		super();
		this.nombre = nombre;
		this.correoElectonico = correoElectonico;
		this.clave = clave;
		this.fechaRegistro = fechaRegistro;
		this.admin = admin;
		
	}

	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getCorreoElectonico() {
		return correoElectonico;
	}
	
	public void setCorreoElectonico(String correoElectonico) {
		this.correoElectonico = correoElectonico;
	}

	public Dibujo getDueno() {
		return dueno;
	}

	public void setDueno(Dibujo dueno) {
		this.dueno = dueno;
	}

	public String getClave() {
		return clave;
	}
	public void setClave(String clave) {
		this.clave = clave;
	}
	public String getFechaRegistro() {
		return fechaRegistro;
	}
	public void setFechaRegistro(String fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}
	public int getAdmin() {
		return admin;
	}
	public void setAdmin(int admin) {
		this.admin = admin;
	}
	public LinkedList<Dibujo> getDibujosHechos() {
		return dibujosHechos;
	}
	public void setDibujosHechos(LinkedList<Dibujo> dibujosHechos) {
		this.dibujosHechos = dibujosHechos;
	}
}
