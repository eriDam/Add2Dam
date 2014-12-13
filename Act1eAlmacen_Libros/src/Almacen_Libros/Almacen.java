package Almacen_Libros;

import java.io.Closeable;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

//Creamos esta clase para gestionar todos los streams y  m�todos de guardar y almacenar 
//para no escribir informacion en la otra clase almacen_libro

public class Almacen {
	
	//Creamos un cosntructor para que pueda ser llamado desde el m�todo principal, creando un objeto
	public void Almacen(){
		
	}
	
	//M�todo/Funci�n que guardar� un libro y el nombre del fichero donde va a ser guardada esta info
	public void guardar(Libro l, String f){
		//Declaramos fuera de try el objeto. 
		ObjectOutputStream out = null;
		
		
		/*Para guardar el objeto libro, creamos un objeto de tipo ObjectOutputStream, este objeto tiene en su constructor 
		 * (hace de envoltura) un objeto de tipo FileOutputStream que crear� el fichero*/
		try {
			out = new ObjectOutputStream (new FileOutputStream (f));//Lo inicializamos
			System.out.println("Fichero creado");
			//el m�todo para escribirlo es writeObject
			out.writeObject(l);
			System.out.println("Fichero escrito ok");
		
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("El fichero no se puede crear");
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Algo a ocurrido...");
		}finally{
			//Con finally indicamos que este c�digo se ejecute pese a las excepciones
			//Hay que cerrar los streams, usamos el m�todo intentar cerrar
			intentarCerrar(out);
		}
		
	}

	public void intentarCerrar(Closeable aCerrar) {
		// TODO Auto-generated method stub
		try{
			if (aCerrar !=null) {
				aCerrar.close();
				System.out.println("Stream cerrado ok");
			}
		}catch (IOException exc) {
			exc.printStackTrace(System.err);
		}
		
		
	}
	
	//M�todo que realiza proceso inverso a intentarCerrar, recupera
	public  Libro recuperar(String f){
	Libro l = null;
	ObjectInputStream in=null;
	try{
		in = new ObjectInputStream (new FileInputStream (f));
		
		//Leer� del fichero al que le pasamos el nombre por par�metro
		/*Hay abr� un objeto de tipo Libro almacenado y lo recuperar�, hacemos casting por 
		*qu� estamos leyendo un objeto de un string y hacemos cast para convertir al tipo de objeto */
		l = (Libro) in.readObject();
		System.out.println("Fichero recuperado ok");
	}catch(ClassNotFoundException ex){
		System.err.println("Error de fichero");
	}catch(IOException ex){
		System.err.println("Error IO");
	}finally{
		intentarCerrar(in);
	}
	return l;
	
}
}
