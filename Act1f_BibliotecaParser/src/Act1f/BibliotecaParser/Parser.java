package Act1f.BibliotecaParser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;//Fijarse en que sea este import, esta dependencia es con la uqe trabajamos
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/*Clase d�nde definimos el objeto Document y un arrayList de objetos libro*/
public class Parser {
	// Defino un objeto global
	private Document dom = null;
	private ArrayList<Libro> libros = null;

	// CONSTRUCTOR
	// Inicializamos el arrayList
	public Parser() {
		libros = new ArrayList<Libro>();
	}

	// Dado el XML crear el objeto document, parsear y asignarselo al objeto

	// M�todo para parsear el Fichero, recibir� el nombre del fichero a parsear
	public void parseFicheroXml(String fichero) {

		// 1� Creamos 1 DocumentBuilderFactory
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db;
		// 2� Dentro de try/catch para tratar de ejecutar el c�digo y si hay
		// algun error capturo
		try {
			// Creamos un DocumentBuilder
			db = dbf.newDocumentBuilder();

			// con db (documentBuilder.parse) parseamos el XML y obtendremos una
			// representaci�n DOM
			dom = db.parse(fichero);// le pasamos el fichero a parsear por
		    // par�metro en la clase Parser_Persona,
			// aqu� en el m�todo coloco
			// fichero que es el parametro String que le he indicado
		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (SAXException saxe) {
			saxe.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	// Funci�n que dado el objeto documento ir� leyendo el objeto y crear� una
	// lista de objetos libros

	public void parseDocument() {
		// Obtenemos el elemento raiz, mediante la funci�n getDocumentElement
		// que nos da un objeto de tipo Element
		Element docEle = dom.getDocumentElement();

		// Obtenemos el nodelist de elementos, mediante getElementsByTagName con
		// la etiqueta que queremos
		// recuperar, crear una lista de nodos de tipo libros por eso usamos la
		// etiqueta libro
		NodeList nl = docEle.getElementsByTagName("libro");

		// Si nl es distinto de null y su length mayor q 0
		if (nl != null && nl.getLength() > 0) {
			/*
			 * El for lo que hace es iterar, para cada elemento que encontremos en
			 * la lista de nodos de la jeraqu�a de Dom cojeremos el 1�
			 */
			for (int i = 0; i < nl.getLength(); i++) {

				// Obtenemos en cada iteraci�n un elemento de la lista (libros)
				// Dado un elemento, recuperar� todos los subelementos y toda la
				// informaci�n que tenga asociada una persona y crear� objeto
				Element el = (Element) nl.item(i);

				// Obtenemos una persona, para cada uno realizar� un getLibro
				Libro li = getLibro(el);

				// Lo a�adiremos al array
				libros.add(li);
			}
		}
	}

	/*
	 * Funci�n que dado un elemento recupera todos los subelementos y toda la
	 * informaci�n que tenga asociado un libro, crear� 1 objeto.
	 */
	private Libro getLibro(Element libroLi) {

		// para cada elemento libro obtenemos el t�tulo, autor etc...
		String titulo = getTextValue(libroLi, "titulo");
		String autor = getTextValue(libroLi, "autor");
		String editor = getTextValue(libroLi, "editor");
		int paginas = getIntValue(libroLi, "paginas");

		/*
		 * PRUEBA
		 * 
		 * Determinar la presencia d un atributo boolean has =
		 * libro.hasAttribute("anyo"); // true
		 * // Get an attribute value;
		 * returns null if not present String attrValue =
		 * libro.getAttribute("anyo"); // value1
		 */

		/*
		 * PRUEBA2
		 * 
		 * Atributo de titulo int anyo =libroLi.getAttribute(titulo);
		 */

		// Hay que leer el atributo de titulo denominado anyo
		// Se crea una variable String que almacena esta info
		String anyoStr = null;

		// Se crea una lista de nodos para los t�tulos
		NodeList nlAny = libroLi.getElementsByTagName("titulo");

		// Se obtiene un elemento de la lista titulo
		Element elAny = (Element) nlAny.item(0);
		// De cada elemento titulo cojemos el atributo anyo
		anyoStr = elAny.getAttribute("anyo");
		// Se convierte la cadena anyoStr en int
		int anyo = Integer.parseInt(anyoStr);

		/*
		 * Como ya tenemos titulo, autor, anyo,editorial,numPag, creamos un
		 * objeto de tipo Libro
		 */
		Libro lib = new Libro(titulo, anyo, autor, editor, paginas);
																	

		// Devolver� ese objeto
		return lib;
	}

	private int getIntValue(Element ele, String tagName) {
		//
		return Integer.parseInt(getTextValue(ele, tagName));
	}

	/*
	 * Este m�todo, dado un elemento libro y una etiqueta, en este caso las
	 * anteriores deber� recuperar el contenido
	 */
	private String getTextValue(Element ele, String tagName) {

		String textVal = null;
		/*
		 * Creamos una lista de nodos por la etiqueta , con la diferencia que
		 * aqu� solo recuperamos un elemento, por eso no hay 1 bucle de
		 * repetici�n que recorra los elementos
		 */
		NodeList nl = ele.getElementsByTagName(tagName);
		if (nl != null && nl.getLength() > 0) {
			Element el = (Element) nl.item(0);// Nos quedamos con el primer
												// elemento
			textVal = el.getFirstChild().getNodeValue();// Despu�s de este primero nos quedamos con el valor de ese nodo									
		}
		// Devolvemos el String
		return textVal;
	}

	// Dada la lista de personas, recorrerla e imprimir
	public void print() {
		Iterator it = libros.iterator();
		while (it.hasNext()) {
			Libro l = (Libro) it.next();
			l.print();
		}

	}
}
