package com.eriDam.addAct5;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.InputMismatchException;

import javax.xml.xquery.XQConnection;
import javax.xml.xquery.XQDataSource;
import javax.xml.xquery.XQException;
import javax.xml.xquery.XQExpression;
import javax.xml.xquery.XQResultSequence;

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
 * * utilizar variables ligadas para realizar
 * instrucciones Xquery con datos que introduce el usuario. Para ello, se partir�
 * de la clase Persona con la que hemos trabajado y de la funcionalidad*desarrollada 
 * en la pr�ctica 5c, y se ampliar�n los siguientes conceptos:
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
	 
	 //M�todo  Actividad 5e A�adir un elemento �fecha_nacimiento� y un atributo �identificador� del
	 //elemento �persona� que sea un entero
	 public Persona addElementF(String fecha_nacimiento, int id){
		
		 
		 return null;
		 
	 }
	 
	 
	 
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
	 * @method public void insertarPersona(String dni, String nombre, int edad). Modificado para nuevos campos en Act5e
	 *         A�ade la persona con los datos pasados por par�metro. Para ello
	 *         se realizar� una instrucci�n Xquery utilizando �insert node� y
	 *         utilizando las etiquetas adecuadas para cada elemento.
	 * */
		 public void insertarPersona(String dni, String nombre, String fecha_nacimiento,int edad){
			 
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
				//"insert node  <persona id="+id+"><dni>" + dni, no cosigo insertar el atributo me da error
				/**Creamos un string para realizar la Consulta*/
				String cadInsert = "insert node  <persona><dni>" + dni
					+ "</dni><nombre>" + nombre + "</nombre><fecha_nacimiento>"+fecha_nacimiento+"</fecha_nacimiento><edad>" + edad
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
		 
		 //M�todo buscar por rango de edad para Act5e 
		 public void buscarPorEdad(int edad1, int edad2){
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
				// Preparamos la instrucci�n para BaseX, en este caso solicito que me devuelva todas las personas por edad
				 
				String cadConsultaEdad=  "doc('personas')/personas/persona[edad="+edad1+" or edad="+edad2+"]";

				/**Ejecutamos:
				 * 
				 * Las sentencias de consultas XQExpression pueden devolver un CONJUNTO DE RESULTADOS, 
				 * en caso de querer obtener resultados.
				 * 
				 * Esta interfaz XQResultSequence representa una secuencia de elementos obtenidos 
				 * como resultado de expresiones XQuery evaluaci�n.
				 * La secuencia resultado est� ligada a la XQconnection objeto en el que se evalu� la expresi�n.
				 * */
				System.out.println("Ejecutamos consulta: " + cadConsultaEdad);	
				/** Ejecutamos la consulta  xqe.executeQuery pasandole la cadena y 
				 * obtenemos en el  XQResultSequence xqrs el resultado de esa ejecuci�n*/
				XQResultSequence xqrs = xqe.executeQuery(cadConsultaEdad);
				
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
				return;
		 }
		 
		 
		 //M�todo bsucar por id para Act5e
		 
		 public void buscarPorId(int id1, int id2){
			
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
				// Preparamos la instrucci�n para BaseX, en este caso solicito que me devuelva todas las personas por id
				 
				String cadConsultaId =  "doc('personas')/personas/persona[@id>"+id1+" and @id<"+id2+"]";

				/**Ejecutamos:
				 * 
				 * Las sentencias de consultas XQExpression pueden devolver un CONJUNTO DE RESULTADOS, 
				 * en caso de querer obtener resultados.
				 * 
				 * Esta interfaz XQResultSequence representa una secuencia de elementos obtenidos 
				 * como resultado de expresiones XQuery evaluaci�n.
				 * La secuencia resultado est� ligada a la XQconnection objeto en el que se evalu� la expresi�n.
				 * */
				System.out.println("Ejecutamos consulta: " + cadConsultaId);	
				/** Ejecutamos la consulta  xqe.executeQuery pasandole la cadena y 
				 * obtenemos en el  XQResultSequence xqrs el resultado de esa ejecuci�n*/
				XQResultSequence xqrs = xqe.executeQuery(cadConsultaId);
				
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
				return;
		 }
		 //MENUS
		 public void lanzarMenuPrincipal(){
			// Creamos la entrada de datos por consola, almacenando en un buffer
				// lector, usamos envolturas 1 trimestre
				BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
				// Creamos variable para recoger la opcion del usuario
				int userOpcion;
					
				// Opciones: recuperar una persona por DNI, insertar y borrar.
				System.out.println("         ******                                   ******");
				System.out.println("         ******   �Bienvenido al men� Principal!   ******\n");
				System.out.println("            	 	 1- Gestionar BD");
				System.out.println("            	           2- Salir ");          	   
				System.out.println("                  ****** ****** ****** ******");
				System.out.println("                         ****** ****** ");
				System.out.println("                            ****** ");
				System.out.print("Escoge una opci�n y pulsa enter/intro: ");
				try {
					userOpcion = Integer.parseInt(in.readLine());
				
					/**
					 * Creo un swicth, para elecci�n, siendo cada numero un case
					 * case
					 */
					
					switch (userOpcion) {
					case 1:
						System.out.println("Opci�n escogida: \"" + userOpcion
								+ "\" Gestionar");
						lanzarMenuBD();
									
						System.out.println("************");
						break;
					
					case 2:
						System.out.println("Opci�n escogida: \"" + userOpcion
								+ "\" Salir. \n");
					
									
						System.out.println("  ****** Terminado ******");
						System.out.println("****** Vuelve pronto!! ******");
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
				};
				
		 }
		 
		 public void lanzarMenuBD(){
			// Creamos la entrada de datos por consola, almacenando en un buffer
				// lector, usamos envolturas 1 trimestre
				BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
				// Creamos variable para recoger la opcion del usuario
				int userOpcion;
				String userIntroDni;
				String userAddPersona;
				String userDeletePers;
				String dni;
				int userEdad1;
				int userEdad2;
				int id1;
				int id2;
				
				
				// Opciones: recuperar una persona por DNI, insertar y borrar.
				System.out.println("         ******                                   ******");
				System.out.println("         ******   �Bienvenido al men� de tu BD!   ******\n");
				
				System.out.println("            	 1- Recuperar persona por DNI");
				System.out.println("            	     2- Insertar persona");
				System.out.println("            	      3- Borrar persona");
				System.out.println("              4- \"NOVEDAD\" Busca por edad");
				System.out.println("              5- \"NOVEDAD\" Busca por Id");
				System.out.println("            	         6- Ver todo");
				System.out.println("            	            7- Salir");
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
					//Creo un objeto de la clase GestionBdXQJ
					GestionBdXQJ gestorXQJ = new GestionBdXQJ();
					switch (userOpcion) {
					case 1:
						System.out.println("\nOpci�n escogida: \"" + userOpcion
								+ "\"Recuperar persona por DNI");
						System.out.print("Introduce el dni de la persona: ");
						
						gestorXQJ.recuperarPersonaPorDni(userIntroDni = in.readLine());

						System.out.println("ok");
						System.out.println("\nVolviendo al men� principal...\n");
						lanzarMenuPrincipal();
						break;
					case 2:
						System.out.println("Opci�n escogida: \"" + userOpcion
								+ "\" Insertar persona");
						System.out.println("Insertando persona...Pulsa enter para completar");
						userAddPersona = in.readLine();
						gestorXQJ.insertarPersona("21001001Q","Tara","17/04/2003",11);			
						System.out.println("************");
						System.out.println("\nVolviendo al men� principal...\n");
						lanzarMenuPrincipal();
						break;
					case 3:
						System.out.println("Opci�n escogida :  \"" + userOpcion
								+ "\" Borrar persona");
						System.out.println("Inserta un dni para eliminar a esa persona");
						userDeletePers = in.readLine();
						gestorXQJ.borrarPersona("2920528W");
						System.out.println("\nVolviendo al men� principal...\n");
						lanzarMenuPrincipal();
						break;
					case 4:
						System.out.println("\nOpci�n escogida: \"" + userOpcion
								+ "\"\"**NOVEDAD**\" Busca por edad");
						System.out.print("Introduce las edades para buscar las persona: ");
						
						gestorXQJ.buscarPorEdad(userEdad1 = Integer.parseInt(in.readLine()), userEdad2 = Integer.parseInt(in.readLine()));

						System.out.println("ok");
						System.out.println("\nVolviendo al men� principal...\n");
						lanzarMenuPrincipal();
						break;	
						case 5:
							System.out.println("\nOpci�n escogida: \"" + userOpcion
									+ "\"\"**NOVEDAD**\" Busca por Id");
							System.out.print("Introduce los id para buscar las personas: ");
							
							gestorXQJ.buscarPorId(id1 = Integer.parseInt(in.readLine()), id2 = Integer.parseInt(in.readLine()));

							System.out.println("ok");
							System.out.println("\nVolviendo al men� principal...\n");
							lanzarMenuPrincipal();
							break;	
					case 6:
						String userFind;
						System.out.println("Mostrando todos los resultados... ");
						System.out.println("Pulsa enter para continuar!");
						userFind = in.readLine();
						gestorXQJ.recuperarPersonasAll();
						System.out.println("\nVolviendo al men� principal...\n");
						lanzarMenuPrincipal();
						break;
						
					case 7:
						String userExit;
						System.out.println("Finalizado \"" + userOpcion
								+ "\"****");
						userExit = in.readLine();
						System.exit(userOpcion);
						System.out.println("\nVolviendo al men� principal...\n");
						lanzarMenuPrincipal();
						break;
					
					 
					default:
						System.out.println("\nVolviendo al men� principal...\n");
						lanzarMenuPrincipal();
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