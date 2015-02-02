package com.eriDam.addAct5;
/**
 * Actividad 5c XQJ con BaseX
 * @author erika_000
 * 
 * Programa   que incluya un men� que muestra varias opciones al usuario, 
 * entre las que se incluye recuperar una persona por
 * DNI, insertar y borrar. Dependiendo de la opci�n seleccionada, el programa
 * mostrar� por pantalla un mensaje con los datos de la persona recuperada (en
 * caso de que seleccione una consulta), o bien un mensaje de confirmaci�n (en
 * caso que realice una inserci�n o un borrado).
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
		 gestorXQJ.lanzarMenuPrincipal();
		

	}

}
