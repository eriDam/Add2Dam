import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

// Un Stream o flujo representa la manera de manejar la entrada y salida de
//datos mediante un camino unidireccional, para enviar de un sitio a otro.
//2 tipos flujos: de caracteres y flujos de bytes

public class streams {
	//A la hora de leer un fichero puede saltar la excepcion de que el fichero no exista, puedo encerrar el 
	//c�digo dentro  de un try catch o en la mismma l�nea del metodo main, indicando as� que el main puede lanzar
	//la excepci�n FileNotFoundException

	public static void main(String[] args) throws IOException {
		// Creo un objeto de la clase FileReader, ya que quiero leer de un fichero de texto 
		//Esta clase como se puede ver en el API de java, puede recibir un String por par�metro con 
		//con el nombre del fichero en este caso entrada
		FileReader fr = new FileReader("entrada.txt");

		//Una de las opciones de FileReader es read, que solo puede leer 1 caracter, devuelve el caracter leido o 
		//devuelve -1 en caso de que sea el final del stream, tambien puede lanzar una excepci�n de entrada salida, por eso marca error
		//hay que a�adir la exccepci�n
		//int c= fr.read();
		//Ya que he puesto la lectura dentro del while, elimino la lectura inicial y dejo solo la variable declarada. 
		int c;
		//PARA UTILIZAR FLUJOS DE SALIDA
		//FileWriter es la clase que se ocupa de permitirlo
		FileWriter fw = new FileWriter("salida.txt");//importamos tambi�n la clase
		
		System.out.print("Los caracteres le�dos son:  ");
		/*una vez leido el carcater podemos mostrar por pantalla ese caracter
		*devuelve un int hay que realizar Casting o cast de ese int para convertir a char y nos muestre el 1er car�cter
				//System.out.println("El car�cter es: "+(char)c);
		*Si queremos leer mas de un caracter hay que realizar un bucle que recorra el archivo, 
		*mientras la lectura sea diferente de -1
		*que ejecute el c�digo*/
		while((c=fr.read())!=-1){
		System.out.print((char)c);
		//Escribimos el caracter leido en el fichero salida.txt, que crear� si no existe
		fw.write(c);
		}
		//Se requiere cerrar el flujo de datos, ya que si no, crea fichero pero en blanco
		fw.close();
		fr.close();		
	}
	
}
