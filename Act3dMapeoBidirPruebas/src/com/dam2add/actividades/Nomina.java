package com.dam2add.actividades;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
/**
 * Clase N�nima
 * @class nomina
 * 
 * @author erika_000
 * 
 * */

//Definiremos la entidad para que sea persistente
@Entity
public class Nomina implements Serializable{
	//utilizamos el id para decir que ser� retribucion clave primaria, para marcar el atributo de la clase
	@Id
	//indicammos que va a ser clave autogenerada
	@GeneratedValue(strategy=GenerationType.AUTO)
	
	private float retribucion;
	private int id; //Id clave autogenerada
	 
	// Persona es la clase propietaria de la relaci�n con N�mina.
	// 1 sola n�mina est� asociada a 1 sola persona.
	 //la anotaci�n  OneToOne  tiene un par�metro que se denomina mappedBy 
	// cuyo valor es �nomina�. Este par�metro hace 
    //referencia a que la relaci�n ya fue construida por la otra clase �Persona� a traves de su variable �persona�
	 
		@OneToOne(mappedBy="nomina")	
		private Persona persona;
 
	//Constructor
	public Nomina(){
		
	}
	
	//Constructor incluyendo persona
	public Nomina( int ret, Persona pers) {
		 
		retribucion = ret;
		persona = pers;
		
	}
	//Getters and Setters

	//Devolver la retribucion
	public float getRetribucion() {
		return retribucion;
	}
	
	//Poner la retribucion

	public void setRetribucion(float retribucion) {
		this.retribucion = retribucion;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}
	//M�todo imprimir  
		public void printNomina(){
			System.out.println("Nombre: " +retribucion+"Nominas"+persona);
		}

}
