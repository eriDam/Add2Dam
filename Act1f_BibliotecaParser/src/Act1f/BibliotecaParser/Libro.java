package Act1f.BibliotecaParser;

import java.io.Serializable;

import javax.swing.JOptionPane;

//Las clases que queramos serializar, tienen que implementar la clase serializable
public class Libro implements Serializable {

	//Definimos los atributos a libro
	private String titulo=null;
	private String autor=null;
	private int anyo=0;
	private String editor=null;
	private int paginas=0;
	 
	
	public Libro(){
		
	}
	
	//M�todo d�nde le pasamos por par�metros el t�tulo, autor, a�o publicacion, la editorial y las p�ginas
	public Libro(String ti,int any, String au, String ed, int np){
		this.titulo = ti;
		this.anyo = any;
		this.autor = au;
		this.editor =ed;
		this.paginas =np;

	}
	
	//M�todo para imprimir los datos del objeto

	public void print() {
	 
		//Imoprimimos la informaci�n de persona cuando llamemos a este m�todo
		JOptionPane.showMessageDialog(null, "El t�tulo es: "+titulo+".\nEl autor es: "+autor+".\nEl a�o de publicaci�n es: "
				+ " "+anyo+". Su editor fue: "+editor+".\nTiene "+paginas+" p�ginas.");
		
		System.out.println( "El t�tulo es: "+titulo+".\nEl autor es: "+autor+".\nEl a�o de publicaci�n es:"
				+ " "+anyo+". Su editor fue: "+editor+".\nTiene "+paginas+" p�ginas.");
		
	}

	//Generamos los m�todos getters and setters para acceder a los atributos. 
	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public int getAnyo() {
		return anyo;
	}

	public void setAnyo(int anyo) {
		this.anyo = anyo;
	}

	public String getEditor() {
		return editor;
	}

	public void setEditor(String editor) {
		this.editor = editor;
	}

	public int paginas() {
		return paginas;
	}

	public void setpaginas(int paginas) {
		this.paginas = paginas;
	}
	
}
