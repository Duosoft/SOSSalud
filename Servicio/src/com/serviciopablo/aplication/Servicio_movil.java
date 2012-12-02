package com.serviciopablo.aplication;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gson.Gson;
import com.serviciopablo.bz.Bd_coneccion;
import com.serviciopablo.bz.Sql_manager;

public class Servicio_movil {

		
	public String consultaCentrosHospitalarios(String cuidad) throws Exception{
		
		String json = "0";
		
		ArrayList<HashMap<String, String>> listaPedido = new ArrayList<HashMap<String,String>>();
		
		Connection conn = null;
		try{
			
			Bd_coneccion conectar = new Bd_coneccion().conectar();
			conn = conectar.getConexion();
			
			Sql_manager manager = new Sql_manager();
		    //obtener los campos de contenidoActivo segun codDocumento del documento a copiar
			
			ResultSet resulta = consultar(manager.getQueryObtenerCentrosHospitalarios(cuidad));
			while (resulta.next()) {
				HashMap<String, String> mapPedido = new HashMap<String,String>();
				//se crean los nuevos campos
				mapPedido.put("nombre", resulta.getString("nombre"));
				mapPedido.put("direccion", resulta.getString("direccion"));
				mapPedido.put("cod_tabla", resulta.getString("id_hospital"));
				listaPedido.add(mapPedido);
			}
			Gson gson = new Gson();
			json = gson.toJson(listaPedido);
		}catch (SQLException e) {
	          //Si sucede una excepción o un error al meter los datos o incluso una 
	          //perdida de la conexión con la bd se realiza un rollback para deshacer todo
	          if (conn != null) {
	            conn.rollback();
	            System.out.println("Connection rollback...");
	          }
	          Logger.getLogger(e.getSQLState()).setLevel(Level.SEVERE);
	          json = "1";
	        } finally {
	            //Finalmente se cierra la conección si esta permanece abierta,
	          if (conn != null && !conn.isClosed()) {
	            conn.close();
	          }
	          
	        }
		return json;
	}
	
	
	public String consultaDetalle(String id) throws Exception{
		
		String json = "0";
		
		ArrayList<HashMap<String, String>> listaPedido = new ArrayList<HashMap<String,String>>();
		
		Connection conn = null;
		try{
			System.out.println("identificador:"+id);
			Bd_coneccion conectar = new Bd_coneccion().conectar();
			conn = conectar.getConexion();
			
			Sql_manager manager = new Sql_manager();
		    //obtener los campos de contenidoActivo segun codDocumento del documento a copiar
			
			ResultSet resulta = consultar(manager.getConsultadDetalleCentroHospitalario(id));
			while (resulta.next()) {
				HashMap<String, String> mapPedido = new HashMap<String,String>();
				//se crean los nuevos campos
				mapPedido.put("nombre", resulta.getString("nombre"));
				mapPedido.put("telefono", resulta.getString("telefono"));
				mapPedido.put("direccion", resulta.getString("direccion"));
				mapPedido.put("cod_tabla", resulta.getString("id_hospital"));
				mapPedido.put("dependencia", resulta.getString("dependencia"));
				mapPedido.put("localizacion", resulta.getString("localizacion"));
				listaPedido.add(mapPedido);
			}
			Gson gson = new Gson();
			json = gson.toJson(listaPedido);
		}catch (SQLException e) {
	          //Si sucede una excepción o un error al meter los datos o incluso una 
	          //perdida de la conexión con la bd se realiza un rollback para deshacer todo
	          if (conn != null) {
	            conn.rollback();
	            System.out.println("Connection rollback...");
	          }
	          Logger.getLogger(e.getSQLState()).setLevel(Level.SEVERE);
	          json = "1";
	        } finally {
	            //Finalmente se cierra la conección si esta permanece abierta,
	          if (conn != null && !conn.isClosed()) {
	            conn.close();
	          }
	          
	        }
		return json;
	}
	
	public ResultSet consultar(String sql) {
        ResultSet resultado;
        try {
        	Bd_coneccion conectar = new Bd_coneccion().conectar();
            Statement sentencia = conectar.getConexion().createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            resultado = sentencia.executeQuery(sql);
           
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }        return resultado;
    }
}
