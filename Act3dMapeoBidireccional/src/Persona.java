import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
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
	//El primer paso ser� a�adir unas variable de tipo List<Empresa> y List<Nomina> dentro de la clase Persona
	//El siguiente paso ser� anotar la clase de la forma correcta para que soporte la relaci�n. 
	//En este caso utilizaremos la anotaci�n OneToMany
	//la anotaci�n  OneToMany  tiene un par�metro que se denomina mappedBy y cuyo valor es �persona�. Este par�metro hace 
	//referencia a que la relaci�n ya fue construida por la otra clase �Empresa� a traves de su variable �persona�
	@OneToMany(mappedBy="persona" )
	List<Empresa> listaEmp= new ArrayList<Empresa>();
	List<Nomina>  listaNom= new ArrayList<Nomina>();
	public Persona() {
		super();
		listaEmp=new ArrayList<Empresa>();
		listaNom=new ArrayList<Nomina>();
	}
	
	//M�todos List Empresa
	
	 public List<Empresa> getListaEmpresa() {
	        return listaEmp;
	    }
	 
	    public void addEmpresa(Empresa e) {
	 
	    	listaEmp.add(e);
	    }
	 
	    public void setlistaEmp(List<Empresa> listaEmp) {
	        this.listaEmp = listaEmp;
	    }
	    ////M�todos List Nomina
		
		 public List<Nomina> getlistaNom() {
		        return listaNom;
		    }
		 
		    public void addNomina(Nomina n) {
		 
		    	listaNom.add(n);
		    }
		 
		    public void setlistaNom(List<Nomina> listaNom) {
		        this.listaNom = listaNom;
		    }
	    

	public Persona(String d, int e,String n ) {
		dni = d;
		edad = e;
		nombre = n;
	}
	public Persona(String n) {
		
		nombre = n;
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
		//System.out.println("Nombre: "+nombre+" y edad "+edad);
	}
	
}

