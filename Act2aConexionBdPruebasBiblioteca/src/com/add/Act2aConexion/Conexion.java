package com.add.Act2aConexion;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;


/** @author Eri
 *  Actividad 2a Conexi�n mySql pruebas biblioteca*/

public class Conexion {
	
	public static void main(String[] args) {
		Connection conexion;
	/**La url debe seguir el protocolo jdb (debe empezar x jdbc:mysql://laruta*/
	String url = "jdbc:mysql://localhost/biblioteca";
	String user = "root";
	String pw = "ecreaweb2";
	
	/** El m�todo class for Name informa a la clase cual es el m�todo que 
		 * hay que cargar a partir de la sentencia. 
		 * Driver es el que se encarga de establecer la conexi�n*/
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.err.println("Error " + e.getMessage());;
			System.out.println("No se encuentra el driver...");
		}
		/**
		 * Establecer la conexi�n con el driverManager
		 * */
		
			try{
				conexion = DriverManager.getConnection(url, user, pw);
				System.out.println("Conexi�n realizada ok, utilizando DriverManager");
				Statement instruccion = (Statement) conexion.createStatement();
				ResultSet conjuntoResultados = instruccion.executeQuery("SELECT * FROM fichalibro");
				while (conjuntoResultados.next())
					System.out.println("\nISBN: "+ conjuntoResultados.getObject("ISBN") + "\nT�tulo: "
							+ conjuntoResultados.getObject("T�tulo") + "Autor" + conjuntoResultados.getObject("Autor") + 
							"\nDescripci�n: "+ conjuntoResultados.getObject("Descripci�n") + "Editorial" + conjuntoResultados.getObject("Editorial") +
							"Coleccion: " + conjuntoResultados.getObject("Colecci�n")
							+ "Precio: " + conjuntoResultados.getObject("Precio") + "\nFecha publicaci�n: " + conjuntoResultados.getObject("Fecha publicaci�n"));
				conexion.close();		
			}catch (SQLException e) {
				System.err.println("Error " + e.getMessage());
			}
			/** obtenemos el driver del controlador desde el DriverManager*/
			try {
				Driver driver = DriverManager.getDriver(url);
				Properties properties = new Properties();
				properties.setProperty("user", user);
				properties.setProperty("password", pw);
				conexion = driver.connect(url, properties);
				System.out.println("\nConexi�n realizada ok usando Driver");
				conexion.close();

			} catch (SQLException ex) {
				System.err.println("Error " + ex.getMessage());
			}

		}

	}

