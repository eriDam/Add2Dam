package Marshaller_Libro;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

/** En esta clase Marshaller ser� donde se definen los distintos m�todos. 
	  */

public class Marshaller {

	/**Creo un objeto de tipo Document y un ArrayList de libros, que rellenaremos
		* pasandole por par�metro todos los datos desde el m�todo main*/
		private Document dom = null;
		private ArrayList<Libro> biblioteca = null;
		
		public Marshaller(ArrayList<Libro> l) {
			biblioteca = l;
		}
		public void crearDocumento() {
			
			/**Creo un DocumentbuilderFactory*/
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			
			try {
				/**Creo un Documentbuilder*/
				DocumentBuilder db = dbf.newDocumentBuilder();
				
				/**Creo una instancia de DOM ya que no hay que parsear el documento xml*/
				dom = db.newDocument();
			} catch (ParserConfigurationException pce) {
				System.out.println("Error..."+pce);
				pce.printStackTrace();
			}
		}
			
		/**creo el elemento raiz y sus subelementos, todo el �rbol de la estructura DOM
		   -La creacion de un elemento es: Dado el objeto linea 38*/
			public void crearArbolDOM() {
				
				/**creamos el elemento ra�z personas*/
				Element docEle = dom.createElement("biblioteca");
				dom.appendChild(docEle);//Lo a�adimos al Document
				
				/**recorremos con Iterator*/
				Iterator it = biblioteca.iterator();
				while (it.hasNext()) {
					Libro e = (Libro) it.next();
					/**para cada objeto libro se crea el elemento <libro> y se a�ade*/
					Element LibroEle = setLibro(e);
					docEle.appendChild(LibroEle);
					
					 
				}
				
			}
			
			/**Metodo setLibro*/
			private Element setLibro(Libro l) {
				
				/**creo el elemento Libro*/
				Element LibroEle = dom.createElement("libro");
				
				/**creo el elemento titulo, el nodo de texto y lo a�ado al element*/
				Element tituloEle = dom.createElement("titulo");
				Text tituloTexto = dom.createTextNode(l.getTitulo());
				tituloEle.appendChild(tituloTexto);/**a�ade el elemento texto en el elemento titulo*/
				LibroEle.appendChild(tituloEle);/**a su vez lo a�ade en el elemento raiz
				*creo el atributo anyo y se lo paso  al elem titulo con el m�todo setAttribute	*/			 
				tituloEle.setAttribute("anyo", Integer.toString(l.getAnyo()));
				
				/**Creo el  elemento autor, el nodo de texto y lo a�ado al element*/
				Element autorEle = dom.createElement("autor");
				Text autorTexto = dom.createTextNode(l.getAutor());
				autorEle.appendChild(autorTexto);/**a�ade el elemento texto en el elemento titulo*/
				LibroEle.appendChild(autorEle);/**a su vez lo a�ade en el elemento raiz*/
				
				/**Creo el elemento editor, el nodo de texto y lo a�ado al element*/
				Element editorEle = dom.createElement("editor");
				Text editorTexto = dom.createTextNode(l.getEditor());
				editorEle.appendChild(editorTexto);/**a�ade el elemento texto en el elemento titulo*/
				LibroEle.appendChild(editorEle);/**a su vez lo a�ade en el elemento raiz*/
							
				/**creo el elemento p�ginas, el nodo de texto y lo a�ado al element*/
				Element paginasEle = dom.createElement("paginas");
				Text paginasTexto = dom.createTextNode(Integer.toString(l.getPaginas()));
				paginasEle.appendChild(paginasTexto);
				LibroEle.appendChild(paginasEle);
				
				return LibroEle;
		}
			
			/**M�todo para transformar de DOM a XML*/
			public void escribirDocumentAXML(File file) throws TransformerException {
				
				/**Se crea una instancia/variable para escribir el resultado utilizando la factory, por q 
				 * transforme es una clase abstracta y de estas no se puede crear objetos
				 */
				Transformer trans = TransformerFactory.newInstance().newTransformer();
				trans.setOutputProperty(OutputKeys.INDENT,"yes");
				
				/**Especificamos donde escribiremos y la fuente de datos*/
				StreamResult result = new StreamResult(file);
				DOMSource source = new DOMSource(dom);
				trans.transform(source, result);
			
			}
			

	}
