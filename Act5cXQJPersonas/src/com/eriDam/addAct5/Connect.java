package com.eriDam.addAct5;
/**
 * Actividad 5e XQJ con BaseX Variables ligadas
 * @author erika_000
 * 
 * Programa   que incluya un men� que muestra varias opciones al usuario, 
 * entre las que se incluye recuperar una persona por
 * DNI, insertar y borrar. Dependiendo de la opci�n seleccionada, el programa
 * mostrar� por pantalla un mensaje con los datos de la persona recuperada (en
 * caso de que seleccione una consulta), o bien un mensaje de confirmaci�n (en
 * caso que realice una inserci�n o un borrado).
 * 
 * utilizar variables ligadas para realizar
 * instrucciones Xquery con datos que introduce el usuario. Para ello, se partir�
 * de la clase Persona con la que hemos trabajado y de la funcionalidad*desarrollada en la pr�ctica 5c, y se ampliar�n los siguientes conceptos:
 * 
 * El menu solo puede ejecutar una �nica opci�n ya que no lo he conseguido colocar dentro 
 * de un bucle infinito.  Hay que iniciar cada vez la aplicaci�n.
 * 
 * */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.InputMismatchException;

public class Connect {

	public static void main(String[] args) {
		//Creo un objeto de la clase GestionBdXQJ
		GestionBdXQJ gestorXQJ = new GestionBdXQJ();
		// Creamos la entrada de datos por consola, almacenando en un buffer
		// lector, usamos envolturas 1 trimestre
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		// Creamos variable para recoger la opcion del usuario
		int userOpcion;
		String userIntroDni;
		String userAddPersona;
		String userDeletePers;
		String dni;
		
		// Opciones: recuperar una persona por DNI, insertar y borrar.
		System.out.println("         ******                                   ******");
		System.out.println("         ******   �Bienvenido al men� de tu BD!   ******\n");
		System.out.println("            	 1- Recuperar persona por DNI");
		System.out.println("            	     2- Insertar persona");
		System.out.println("            	      3- Borrar persona");
		System.out.println("            	         4- Ver todo");
		System.out.println("            	            5- Salir");
		System.out.println("                   ****** ****** ****** ******");
		System.out.println("                          ****** ****** ");
		System.out.println("                             ****** ");
		System.out.print("Escoge una opci�n y pulsa enter/intro: ");
		
		try {
			userOpcion = Integer.parseInt(in.readLine());
			
			//userIntroDni = in.readLine();
			/**
			 * Creo un swicth, para elecci�n, siendo cada numero un case
			 * case
			 */
			
			switch (userOpcion) {
			case 1:
				System.out.println("\nOpci�n escogida: \"" + userOpcion
						+ "\" Recuperar persona por Dni");
				System.out.print("Introduce el dni de la persona: ");
				
				gestorXQJ.recuperarPersonaPorDni(userIntroDni = in.readLine());

				System.out.println("ok");
				break;
			case 2:
				System.out.println("Opci�n escogida: \"" + userOpcion
						+ "\" Insertar persona");
				System.out.println("Insertando persona...Pulsa enter para completar");
				userAddPersona = in.readLine();
				gestorXQJ.insertarPersona("21001001Q","Tara",11);			
				System.out.println("************");
				break;
			case 3:
				System.out.println("Opci�n escogida :  \"" + userOpcion
						+ "\" Borrar persona");
				System.out.println("Inserta un dni para eliminar a esa persona");
				userDeletePers = in.readLine();
				gestorXQJ.borrarPersona("2920528W");
				break;
			case 4:
				String userFind;
				System.out.println("Mostrando todos los resultados... ");
				System.out.println("Pulsa enter para continuar!");
				userFind = in.readLine();
				gestorXQJ.recuperarPersonasAll();
				break;
			case 5:
				String userExit;
				System.out.println("Finalizado \"" + userOpcion
						+ "\"****");
				userExit = in.readLine();
				System.exit(userOpcion);
				break;
			 
			default:
				break;
			}
		} catch (InputMismatchException e) {
			System.out.println("Has de introducir un entero!  " + e);

		} catch (NumberFormatException nfe) {
			System.out.println("Error");
			nfe.printStackTrace();
		} catch (IOException ioe) {
			System.out.println("Error");
			ioe.printStackTrace();
		}

		// Podr�a usar tambi�n if
		// if (userOpcion==1)
		// {
		// System.out.println("Escribe el  ");
		//
		// }
		// if (userOpcion==2)
		// {

	}

}
