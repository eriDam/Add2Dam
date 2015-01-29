import java.io.IOException;

//import org.basex.query.value.item.Str;
import org.basex.server.ClientSession;

/**
 * @author erika_000
 * 
 * Clase principal que usa un client sesion definido en otra clase que utilizaremos
 * para crear la conex�n - consultar - a�adir - borrar 
 * 
 * Importamos la libreria externa que se encuentra dentro de archivos de 
 * programa/baseX/baseX.jar
 * 
 * http://xqj.net/javadoc/ (info para siguiente actv.)
 * 
 * */
public class Connect {

	public static void main(String[] args) {
		/**Creo un objeto de GestionBaseX para utilizar mis m�todos creados*/
		GestionBaseX gestorBX = new GestionBaseX();
		
		/**Utilizo el m�todo creado para conectar a la BD*/
		gestorBX.conectar();
		
		/**Comprobamos que hay en la BD utilizando el m�todo creado */
		gestorBX.recuperarPersonasAll();
		
		/**Utilizo los  m�todos solicitados en la actividad y creados en GestionBaseX */
		gestorBX.recuperarPersonaPorNombre("Erika");
		gestorBX.recuperarPersonaPorDni("29204528");
		gestorBX.insertarPersona("44235689","Ana", 25 );
		/**comprobamos la inserci�n consultando todas las personas de nuevo*/
		System.out.println("****************");
		gestorBX.recuperarPersonasAll();
		gestorBX.borrarPersona("29204528");
		System.out.println("*********** Consultando el borrado de Erika");
		gestorBX.recuperarPersonasAll();
		
		
		/**Utilizo el m�todo creado para cerrar la BD*/
		gestorBX.cerrarSesion();
	
	}//Fin main

}//Fin class
