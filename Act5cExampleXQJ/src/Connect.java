import javax.xml.xquery.XQConnection;
import javax.xml.xquery.XQDataSource;
import javax.xml.xquery.XQException;
import javax.xml.xquery.XQExpression;
import javax.xml.xquery.XQResultSequence;
/**
 * Esta Aplicaci�n representa un ejemplo de utilizacion de XQJ
 *
 * Importamos las 3 librerias que nos proporcina el sigiente API.
 * API :  http://xqj.net/javadoc/  
 * 
 * */
public class Connect {

	public static void main(String[] args) {
		/**1-Instanciamos el objeto de XQconnection*/
		XQConnection conn = null;
				
		try {
			// Abrimos la sesi�n en la base de datos
			/**2-XQDataSource es una f�brica  de objetos XQConnection.
			 * forma: XQDataSource xqs = new XQDataSource BaseXXQDataSource();
			 * 
			 * Pero no lo instanciaremos as�, la forma de proceder PARA PODER CONECTARNOS contra 
			 * OTROS Sistemas gestores de Bd, utilizamos un string que nos haga referencia al 
			 * driver que vamos a utilizar.
			 * 
			 *Despues generar (casteando) un objeto XQDataSource 
			 * haremos un  Class.forName de ese driver que queremos utilizar para BaseX este*/
			XQDataSource xqs = (XQDataSource) Class.forName("net.xqj.basex.BaseXXQDataSource").newInstance();
			
			/**Realizamos un BUCLE for para recorrer las propiedades del vector y que nos las muestre */
			
			/**4- Si no sabemos las propiedades del sistema gestor de bd determinado, para ver como 
			 * OBTENERLAS, con getSupportedPropertyNames, las obtenemos, es un vector de Strings que nos
			 * devuelve las distintas propiedades que tiene el sistema gestor de DB*/
			System.out.println("Obtengo las propiedades");
			for (int i=0;i<xqs.getSupportedPropertyNames().length;i++)
				System.out.println(xqs.getSupportedPropertyNames()[i]);
			
			/**Si queremos indicar las propiedades, maquina, puerto...*/
			xqs.setProperty("serverName", "localhost");
			xqs.setProperty("port", "1984");
			xqs.setProperty("user", "admin");
			xqs.setProperty("password", "admin");
			
			/**3- Cuando ya tenemos un objeto de tipo XQDataSource, podemos establecer la conexi�n 
			 * con una serie de par�metros o sin ellos con getConnection, hay 3 m�todos en este 
			 * caso utilizo el m�todo sin ellos*/
			conn = xqs.getConnection();

			System.out.println("Connexi�n correcta con el SGBD BaseX");
			
			/** Creamos XQExpression:  para la ejecuci�n inmediata de sentencias XQuery
			 *  Este objeto puede ser creado a partir de la XQConnection y la ejecuci�n se puede 
			 *  hacer usando el executeQuery() o executeCommand() m�todo, que pasa en la expresi�n XQuery.
			 *  
			 *  Sirve para ejecutar sentencias de consulata: conjunto de resultados, y pueden procesar ordenes 
			 *  de insercion, eliminacion y actualizacion.
			 *  
			 *  Creo el objeto xqe que lo obtengo de XQConnection (conn)mediante createExpression*/
			XQExpression xqe = conn.createExpression();
			// Preparamos la instrucci�n para BaseX, en este caso solicito que me devuelva todas las personas por nombre
			String cad;
			cad = "for $c in doc('personas')/personas/persona return $c/nombre";

			/**Ejecutamos:
			 * 
			 * Las sentencias de consultas XQExpression pueden devolver un CONJUNTO DE RESULTADOS, 
			 * en caso de querer obtener resultados.
			 * 
			 * Esta interfaz XQResultSequence representa una secuencia de elementos obtenidos 
			 * como resultado de expresiones XQuery evaluaci�n.
			 * La secuencia resultado est� ligada a la XQconnection objeto en el que se evalu� la expresi�n.
			 * */
			System.out.println("Ejecutamos consulta: " + cad);	
			/** Ejecutamos la consulta  xqe.executeQuery pasandole la cadena y 
			 * obtenemos en el  XQResultSequence xqrs el resultado de esa ejecuci�n*/
			XQResultSequence xqrs = xqe.executeQuery(cad);
			
			/**Para poder mostrar los resultados, uno a uno, convertidos a String mediante un bucle While
			 * utilizando el m�todo next, vamos recorriendo y con xqrs.getItemAsString(null), va a mostrar
			 * los distintos nombres de las personas que hay.*/
			System.out.println("\nLos resultados son: ");
			while (xqrs.next())
				System.out.println(xqrs.getItemAsString(null));
		} catch (Exception e) {
			e.printStackTrace();
		} finally { /** Cerramos la sesi�n*/
			try {
				if (conn != null)
					conn.close();
				System.out.println("Conexi�n cerrada correctamente");
			} catch (XQException xe) {
				xe.printStackTrace();
				System.out.println("Error!!  no se ha podido cerrar");
			}
		}
	}
}