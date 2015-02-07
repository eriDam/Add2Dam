package com.dam2add.actividades;
 
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/*Toda entidad debe:
*Proporcionar un constructor por defecto (ya sea de forma impl�cita o expl�cita)
*Ser una clase de primer nivel (no interna)
*No ser final. Implementar la interface java.io.Serializabe si va a ser accedida remotamente
*
*Utilizando arroba Entity delante, indicamos al proveedor de persistencia que ser� una entidad, tabla
*(ser� un objeto de persistencia con el entity manager)Para ser v�lida,*/
@Entity
public class Persona implements Serializable {

	//utilizamos el id para decir que ser� dni clave primaria, para marcar el atributo de la clase
	@Id
	private String dni;
	private int edad;
	private String nombre = null;
	

	//Construiremos la relaci�n del otro lado entre la Persona y la EMPRESA Y NOMINA. 
	 
	//Construiremos la relaci�n del otro lado entre la Persona y la EMPRESA Y NOMINA. 
		//El primer paso ser� a�adir unas variable de tipo List<Empresa> dentro de la clase Persona
		//El siguiente paso ser� anotar la clase de la forma correcta para que soporte la relaci�n. 
		//En este caso utilizaremos la anotaci�n ManyToOne, ya que muchas personas pueden pertenecer a una empresa
		 
	 
	@ManyToOne 
	private Empresa nombreE;
	// Persona es la clase propietaria de la relaci�n con N�mina
		// UNA persona, puede tener UNA n�mina.
		// CascadeType.ALL = Todas las operaciones (MERGE, PERSIST, REFRESH, REMOVE) 
		// afectan en cascada a la n�mina relacionada.
		@OneToOne(cascade = CascadeType.ALL)            	
		private Nomina nomina;
	private Nomina retribucion;
 
	
	
	
	public Persona() {
		
	}
	
	//Constructor al que le paso dni, edad y nombre pero no la Nomina ni la empresa
	public Persona(String dni, int edad,String nom ) {
		dni = dni;
		edad = edad;
		nombre = nom;
		this.nombreE = null;
	 
				
				// Datos por omisi�n
				this.nombreE = null;
				this.nomina = null;
		
	}
	
	//Getters and setters para coger get y poner set
	
	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Empresa getNombreE() {
		return nombreE;
	}

	public void setNombreE(Empresa nombreE) {
		this.nombreE = nombreE;
	}

	public Nomina getNomina() {
		return nomina;
	}

	public void setNomina(Nomina nomina) {
		this.nomina = nomina;
	}

	public Nomina getRetribucion() {
		return retribucion;
	}

	public void setRetribucion(Nomina retribucion) {
		this.retribucion = retribucion;
	}

	@Override
	public String toString() {
		return "Persona [dni=" + dni + ", edad=" + edad + ", nombre=" + nombre
				+ ", nombreE=" + nombreE + ", retribucion=" + retribucion
				+ "]";
	}
	public void print(){
		System.out.println("Persona [dni=" + dni + ", edad=" + edad + ", nombre=" + nombre
				+ ", nombreE=" + nombreE + ", retribucion=" + retribucion
				+  "]");
		
		 
	}
	
}

