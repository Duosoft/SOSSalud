package com.serviciopablo.bz;

import java.sql.Connection;
import java.sql.DriverManager;


public class Bd_coneccion {

	
	private  Connection conexion;
	//declara los uswer y password
	private String user = "root";
	private String password ="parse";
	
	
	public Bd_coneccion conectar() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String BaseDeDatos = "jdbc:mysql://localhost/bd_centrosalud?user="+this.user+"&password="+this.password;
            setConexion(DriverManager.getConnection(BaseDeDatos));
            if(getConexion() != null){
                System.out.println("Conexion Exitosa!");
            }else{
               System.out.println("Conexion Fallida!");                
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this;
    }
	
	
	public Connection getConexion() {
	    return conexion;
	}    
	public void setConexion(Connection conexion) {
	        this.conexion = conexion;
	}    

	
}
