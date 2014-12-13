import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
/*Toda entidad debe:
*Proporcionar un constructor por defecto (ya sea de forma impl�cita o expl�cita)
*Ser una clase de primer nivel (no interna)
*No ser final. Implementar la interface java.io.Serializabe si va a ser accedida remotamente
*
*Utilizamos arroba Entity indicamos al proveedor de persistencia que ser� una entidad, 
*(ser� un objeto de persistencia con el entity manager)Para ser v�lida,*/
@Entity
public class Peliculas implements Serializable {

	//utilizamos el id para decir que ser� id clave primaria, para marcar el atributo de la clase
	@Id 
	private String id;
	private String titulo = null;
	private int duracion;

	public Peliculas() {
	}

	public Peliculas(String t, int d, String id) {
		titulo = t;
		duracion = d;
		id = id;
	}
	
	 
	//M�todo para imprimir las peliculas
	public void print(){
		System.out.println("Id: "+id+" T�tulo: "+titulo+" y duraci�n "+duracion);
		 
	}
	
	
	
	//M�todos getters and setter para acceder a los atributos de peliculas
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public int getDuracion() {
		return duracion;
	}

	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}
	
}

