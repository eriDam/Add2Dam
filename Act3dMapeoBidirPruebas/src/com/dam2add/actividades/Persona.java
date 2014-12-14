package com.dam2add.actividades;
 
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/*Toda entidad debe:
*Proporcionar un constructor por defecto (ya sea de forma impl�cita o expl�cita)
*Ser una clase de primer nivel (no interna)
*No ser final. Implementar la interface java.io.Serializabe si va a ser accedida remotamente
*
*Utilizando arroba Entity delante, indicamos al proveedor de persistencia que ser� una entidad, 
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
	//Establecemos la relaci�n con la otra tabla
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "persona")
	private Nomina retribucion;
	private List<Empresa> listaEmpresas;
	
	public Persona() {
		
	}
	//Constructor al que le paso Nomina y List empresas
	public Persona(String d, int e,String n, Nomina retr, List<Empresa> empresas) {
		dni = d;
		edad = e;
		nombre = n;
		retribucion = retr;
		listaEmpresas = empresas;
		
	}
	
	public Empresa getNombreE() {
		return nombreE;
	}

	public void setNombreE(Empresa nombreE) {
		this.nombreE = nombreE;
	}

	public Nomina getRetribucion() {
		return retribucion;
	}

	public void setRetribucion(Nomina retribucion) {
		this.retribucion = retribucion;
	}

	public List<Empresa> getListaEmpresas() {
		return listaEmpresas;
	}

	public void setListaEmpresas(List<Empresa> listaEmpresas) {
		this.listaEmpresas = listaEmpresas;
	}

	public String getDni() {
	 return dni;
	}

	public String getNombre() {
		return nombre;
	}

	public int getEdad() {
		return edad;
	}
	
	public void setDni(String d) {
		dni = d;
	}

	public void setNombre(String n) {
		nombre = n;
	}

	public void setEdad(int e) {
		edad = e;
	}
	
	public void print(){
		System.out.println("Dni: "+dni+" Nombre: "+nombre+" y edad "+edad);
		 
	}
	
}

