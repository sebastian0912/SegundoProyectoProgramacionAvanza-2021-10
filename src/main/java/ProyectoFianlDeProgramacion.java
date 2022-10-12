
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//Desarrollado por Sebastian Guarnizo, Valentina Hernandez, Gabriela Luigi y Camilo Melo


import Model.Usuario;

import java.util.Scanner;
/**
 *
 * @author GabyL
 */
public class ProyectoFianlDeProgramacion {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
   String correo, clave;
			
				Usuario Controlador= new Usuario();
				Scanner scan = new Scanner(System.in);
				Scanner scan2 = new Scanner(System.in);	
				int target; 	
				
				do
		        {            
                    System.out.println(" 1. Inicia sesion \n 2. Crear Cuenta ");
                    System.out.println(" 3. Para salir");
		            System.out.print(" Opcion: ");
		            target=scan.nextInt();   
		        
		            switch(target)
		            {
		                case 1:	
		                	Controlador.PasardelarchivoaLista();
		                	System.out.print(" Ingrese el Correo:");
		                	correo = scan2.nextLine(); 
		                	System.out.print(" Ingrese la Contraseña:");
							clave = scan2.nextLine(); 	
							if( Controlador.buscarUsuario(correo, clave)   != null) {
								
								if(Controlador.buscarUsuario(correo, clave).getAdmin() >=1) {
								System.out.println("");	
									do {					                										
							            System.out.println(" 1. Eliminar usuario");
							            System.out.println(" 2. Mostrarlo");
							            System.out.println(" 3. Crear Figuras");
			                                            System.out.println(" 4. Para salir");
							            System.out.print("Opcion: ");
							            target=scan.nextInt();   
							        
							            switch(target)
							            {
							                case 1:
							                	Scanner scan3 = new Scanner(System.in);
							                	System.out.print(" Ingrese el Correo a eliminar:");
							                	String correoEliminar = scan2.nextLine(); 
							                	Controlador.eliminar(correoEliminar) ;
							                	Controlador.eliminarEnArchivo();
							                    break;   
							                    
							                case 2:
							                	Controlador.mostrarUsuarios();
							                    break;   
					                             
							                case 3:
							                	Controlador.CrearDibujo(Controlador.buscarUsuario(correo, clave));;
							                    break;
							                    
							                case 4:
							                	
							                	
							                    break;
							                    
							                default:
							                	System.out.print("Ingrese una opcion valida ");                   
							            }
							            System.out.println("");
							        }while(target !=4);			
									
								}	
								else {
									do {
							            System.out.println(" 1. Crear Figuras");
			                            System.out.println(" 2. Para salir");
							            System.out.print("Opcion: ");
							            target=scan.nextInt();   
							        
							            switch(target)
							            {
							                case 1:
							                	Controlador.CrearDibujo(Controlador.buscarUsuario(correo, clave));
							                    break;   
							                    
							                case 2:
							                	
							                    break;   
							                    
							                default:
							                	System.out.print("Ingrese una opcion valida ");                   
							            }
							            System.out.println("");
							        }while(target !=4);		
								}
								
							}						
		                    break;
		                    
		                case 2:		                	
		                	Controlador.InsertarUsuario();
                            break;  
		                case 3:
		                
		                	break;
                            
		                default:
		                	System.out.print("Ingrese una opcion valida ");                   
		            }
		            System.out.println("");
		        }while(target !=3);	
					
				
	}	
}
