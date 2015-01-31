package com.eriDam.addAct5;
import javax.xml.xquery.XQConnection;
import javax.xml.xquery.XQDataSource;
import javax.xml.xquery.XQException;
import javax.xml.xquery.XQExpression;
import javax.xml.xquery.XQResultSequence;

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
 * */


public class GestionBdXQJ {
	XQConnection conn = null;
	/**@method constructor*/
	public GestionBdXQJ(){
	 }
	
	/**@method conectar  Permite conectar a la base de datos*/
	 public void conectar(){
		 /**1-Instanciamos el objeto de XQconnection*/
		 
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
				 * haremos un  Class.forName de ese driver que queremos utilizar*/
				XQDataSource xqs = (XQDataSource) Class.forName("net.xqj.basex.BaseXXQDataSource").newInstance();
				
				/**Realizamos un BUCLE for para recorrer las propiedades del vector y que nos las muestre */
				
				/**4- Si no sabemos las propiedades del sistema gestor de bd determinado, para ver como 
				 * OBTENERLAS, con getSupportedPropertyNames, las obtenemos, es un vector de Strings que nos
				 * devuelve las distintas propiedades que tiene el sistema gestor de DB*/
//				System.out.println("Obtengo las propiedades");
//				for (int i=0;i<xqs.getSupportedPropertyNames().length;i++)
//					System.out.println(xqs.getSupportedPropertyNames()[i]);
				
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
			} catch (Exception e) {
				e.printStackTrace();
			} 
	 }//Fin conectar
	 
//	 /**Cerrar conexi�n*/
//	 
//	 finally { /** Cerramos la sesi�n*/
//			try {
//				if (conn != null)
//					conn.close();
//				System.out.println("Conexi�n cerrada correctamente");
//			} catch (XQException xe) {
//				xe.printStackTrace();
//				System.out.println("Error!!  no se ha podido cerrar");
//			}
//		}
	 
	 public Persona recuperarPersonasAll(){
			conectar();
			try{
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
			 
			String cadConsultaAll = "doc ('personas')/personas/persona";

			/**Ejecutamos:
			 * 
			 * Las sentencias de consultas XQExpression pueden devolver un CONJUNTO DE RESULTADOS, 
			 * en caso de querer obtener resultados.
			 * 
			 * Esta interfaz XQResultSequence representa una secuencia de elementos obtenidos 
			 * como resultado de expresiones XQuery evaluaci�n.
			 * La secuencia resultado est� ligada a la XQconnection objeto en el que se evalu� la expresi�n.
			 * */
			System.out.println("Ejecutamos consulta: " + cadConsultaAll);	
			/** Ejecutamos la consulta  xqe.executeQuery pasandole la cadena y 
			 * obtenemos en el  XQResultSequence xqrs el resultado de esa ejecuci�n*/
			XQResultSequence xqrs = xqe.executeQuery(cadConsultaAll);
			
			/**Para poder mostrar los resultados, uno a uno, convertidos a String mediante un bucle While
			 * utilizando el m�todo next, vamos recorriendo y con xqrs.getItemAsString(null), va a mostrar
			 * los distintos dni de las personas que hay.*/
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
			return null;
	}//Fin metodo recuperarPersonaPorDni
	/**
	 * @method M�todo public Persona recuperarPersonaPorDni(String dni). Devuelve la
		persona que coincide con el dni pasado por par�metro.
	 * */
		public Persona recuperarPersonaPorDni(String dni){
			conectar();
			try{
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
			 
			String cadConsultaDni = "doc ('personas')/personas/persona[dni='"+dni+"']";

			/**Ejecutamos:
			 * 
			 * Las sentencias de consultas XQExpression pueden devolver un CONJUNTO DE RESULTADOS, 
			 * en caso de querer obtener resultados.
			 * 
			 * Esta interfaz XQResultSequence representa una secuencia de elementos obtenidos 
			 * como resultado de expresiones XQuery evaluaci�n.
			 * La secuencia resultado est� ligada a la XQconnection objeto en el que se evalu� la expresi�n.
			 * */
			System.out.println("Ejecutamos consulta: " + cadConsultaDni);	
			/** Ejecutamos la consulta  xqe.executeQuery pasandole la cadena y 
			 * obtenemos en el  XQResultSequence xqrs el resultado de esa ejecuci�n*/
			XQResultSequence xqrs = xqe.executeQuery(cadConsultaDni);
			
			/**Para poder mostrar los resultados, uno a uno, convertidos a String mediante un bucle While
			 * utilizando el m�todo next, vamos recorriendo y con xqrs.getItemAsString(null), va a mostrar
			 * los distintos dni de las personas que hay.*/
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
			return null;
	}//Fin metodo recuperarPersonaPorDni
		
		/**
	 * @method public void insertarPersona(String dni, String nombre, int edad).
	 *         A�ade la persona con los datos pasados por par�metro. Para ello
	 *         se realizar� una instrucci�n Xquery utilizando �insert node� y
	 *         utilizando las etiquetas adecuadas para cada elemento.
	 * */
		 public void insertarPersona(String dni, String nombre, int edad){
			 
			 conectar();
				try{
					/** Creamos XQExpression:  para la ejecuci�n inmediata de sentencias XQuery
				 *  Este objeto puede ser creado a partir de la XQConnection y la ejecuci�n se puede 
				 *  hacer usando el executeQuery() o executeCommand() m�todo, que pasa en la expresi�n XQuery.
				 *  
				 *  Sirve para ejecutar sentencias de consulata: conjunto de resultados, y pueden procesar ordenes 
				 *  de insercion, eliminacion y actualizacion.
				 *  
				 *  Creo el objeto xqe que lo obtengo de XQConnection (conn)mediante createExpression*/
				XQExpression xqe = conn.createExpression();
				// Preparamos la instrucci�n de insercion para BaseX  
				/**Creamos un string para realizar la Consulta*/
				String cadInsert = "insert node  <persona><dni>" + dni
					+ "</dni><nombre>" + nombre + "</nombre><edad>" + edad
					+ "</edad></persona> into doc('personas') /personas";

				/**Ejecutamos:
				 * 
				 * Las sentencias de consultas XQExpression pueden devolver un CONJUNTO DE RESULTADOS, 
				 * en caso de querer obtener resultados.
				 * 
				 * Esta interfaz XQResultSequence representa una secuencia de elementos obtenidos 
				 * como resultado de expresiones XQuery evaluaci�n.
				 * La secuencia resultado est� ligada a la XQconnection objeto en el que se evalu� la expresi�n.
				 * */
				System.out.println("Ejecutamos consulta: " + cadInsert);	
				/** Ejecutamos la consulta  xqe.executeQuery pasandole la cadena y 
				 * obtenemos en el  XQResultSequence xqrs el resultado de esa ejecuci�n*/
				XQResultSequence xqrs = xqe.executeQuery(cadInsert);
				
				/**Para poder mostrar los resultados, uno a uno, convertidos a String mediante un bucle While
				 * utilizando el m�todo next, vamos recorriendo y con xqrs.getItemAsString(null), va a mostrar
				 * los distintos dni de las personas que hay.*/
				System.out.println("\nLos resultados son: ");
				while (xqrs.next())
					System.out.println(xqrs.getItemAsString(null));
				System.out.println("Se ha insertado correctamente: "+cadInsert);
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
				return;
		}//Fin metodo recuperarPersonaPorDni
		 /**
			 * @method public void borrarPersona(String dni). Borra el nodo correspondiente
				a la persona que coincided con el dni pasado por par�metro. Para ello se
				realizar� una instrucci�n Xquery utilizando �delete node�.
			 * */
		 public void borrarPersona(String dni){
			 /**Conectamos a la Bd*/
				conectar();
				try{
					/** Creamos XQExpression:  para la ejecuci�n inmediata de sentencias XQuery
				 *  Este objeto puede ser creado a partir de la XQConnection y la ejecuci�n se puede 
				 *  hacer usando el executeQuery() o executeCommand() m�todo, que pasa en la expresi�n XQuery.
				 *  
				 *  Sirve para ejecutar sentencias de consulata: conjunto de resultados, y pueden procesar ordenes 
				 *  de insercion, eliminacion y actualizacion.
				 *  
				 *  Creo el objeto xqe que lo obtengo de XQConnection (conn)mediante createExpression*/
				XQExpression xqe = conn.createExpression(); 
			 
				/**Creamos un string para realizar el borrado*/
				String cadBorrado = "delete node doc('personas')/personas/persona[dni='"+dni+"']";
			 
				/**Ejecutamos:
				 * 
				 * Las sentencias de consultas XQExpression pueden devolver un CONJUNTO DE RESULTADOS, 
				 * en caso de querer obtener resultados.
				 * 
				 * Esta interfaz XQResultSequence representa una secuencia de elementos obtenidos 
				 * como resultado de expresiones XQuery evaluaci�n.
				 * La secuencia resultado est� ligada a la XQconnection objeto en el que se evalu� la expresi�n.
				 * */
				System.out.println("Ejecutamos consulta: " + cadBorrado);	
				/** Ejecutamos la consulta  xqe.executeQuery pasandole la cadena y 
				 * obtenemos en el  XQResultSequence xqrs el resultado de esa ejecuci�n*/
				XQResultSequence xqrs = xqe.executeQuery(cadBorrado);
				
//				/**Para poder mostrar los resultados, uno a uno, convertidos a String mediante un bucle While
//				 * utilizando el m�todo next, vamos recorriendo y con xqrs.getItemAsString(null), va a mostrar
//				 * los distintos dni de las personas que hay.*/
//				System.out.println("\nLos resultados son: ");
//				while (xqrs.next())
//					System.out.println(xqrs.getItemAsString(null));
				System.out.println("Se ha Borrado correctamente: "+cadBorrado);
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
				return;
		}//Fin metodo
		 
		
}
