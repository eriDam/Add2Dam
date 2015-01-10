import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Constraint;
import com.db4o.query.Predicate;
import com.db4o.query.Query;

/**
 * @author erika_000
 * 
 * Alternativas para realizar consultas en nuestra Bd DB4O a parte de SODA
 * 
 * Query by example  QBE: 
 *				 Utiliza un par�metro donde indicamos el objeto que queremos 
 * 				 recuperar y los valores que queremos recuperar.
 * 
 * */
public class Principal {

	public static void main(String[] args) {
		
		ObjectContainer bbdd = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), "prueba.db4o");
				
		try{	
			/**
			 * Realizamos consulta de personas que tengan cualquier dni, 
			 * cualquier edad, cualquier nombre.
 			 * Cuando ya tenemos la consulta, recuperaremos un objeto set
 			 *  de tipo objetctset que es un tipo de objeto que implementa la interfaz list
 			 *  de java y asi podemos utilizar los m�todos de next y has next
 			 * 	 * */
			ObjectSet<Persona> set = bbdd.queryByExample(new Persona(null, 0, null));
			/**Recorremos la lista e imprimimos el resultado*/
			System.out.println("Las personas son: ");
			while (set.hasNext()) {				
				Persona p = set.next();				
				p.print();				
			}

			System.out.println("******************************************");
			System.out.println("Realizamos consulta QBE con Patr�n Pepe");
			/**Realizamos consulta QBE con un patron, pero no podemos filtrar por un rango de edades
			 * para eso se pude con las native query*/
			set = bbdd.queryByExample(new Persona("Pepe",0, null));
			/**Recorremos la lista e imprimimos el resultado*/
			while (set.hasNext()) {				
				Persona p = set.next();				
				p.print();				
			}
			
			/**3 manera de feninir consultas, con las 
			 * Native Query:
			 * 		 definen una interfaz llamada predicate con un metodo match donde 
			 * 		 especificamos las condiciones que tienen que tener los objetos para
			 *       ser seleccionados de la bd*/
			
			set = bbdd.query(new Predicate<Persona>() {
				
				public boolean match(Persona p) {
					/**especificaremos el patron que queremos encontrar
					 * recuperamos con p getNombre el nombre de la persona, y con compareTo
					 * se compara si es "El string pepe que le pasamos", o la edad > q 20*/
					return p.getNombre().compareTo("Pepe")==0 || p.getEdad()>20;
				}
				
			});
			
			System.out.println("******************************************");
			/**Se puede utilizar un for igual que un while para recorrer la lista*/
			for(Persona p: set){
				System.out.println("Personas >20 a�os: ");
				p.print();
				
			}	
			
		}finally{
			bbdd.close();
		}

	}
	/**
	 * Ver ejemplo de Salva diferente mio en Act4c
	 * 
	 * public List<Vehiculo> recuperarVehiculosAvanzados(){ //Creamos la
	 * setencia nativa List<Vehiculo> set = bbdd.query(new Predicate<Vehiculo>()
	 * { //Le pasamos los parametros public boolean match(Vehiculo v) { return
	 * v.getAnyoMatriculacion()>2010 ||
	 * v.getReparacion().compareTo("cambio de aceite")==0 ||
	 * v.getMatricula().startsWith("G") || v.getMatricula().startsWith("F"); }
	 * }); //Bucle for para imprimir for(Vehiculo p: set){ p.print(); } return
	 * null; }
	 */
}
