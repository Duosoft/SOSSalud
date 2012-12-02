/*
 * Autor: Yuri Perez Garrido
 * Estudiante Ing. Informática - UFRO.
 * email: yuriperezgarrido@hotmail.com
 */
package com.serviciopablo.bz;

public class Sql_manager {

	
	/**
	 * Instantiates a new sql_manager.
	 */
	public Sql_manager(){
		
	}
	
	/**
	 * Funcion que regresa una sentencia query con campos generales de los centros hospitalarios.
	 * @param cuidad
	 * @return
	 */
	public String getQueryObtenerCentrosHospitalarios(String cuidad){
		String sentencia = " select t.id_hospital, t.nombre ,t.direccion from tbl_hospital t " +
				"inner join tbl_ciudad c on (c.codCiudad = t.cod_ciudad) where t.cod_ciudad = '"+cuidad+"';";
		return sentencia;
	}
	
	
	/**
	 * Funcion que regresa una query de un campo tbl_hopital segun su identificador de tabla.
	 * @param idTabla
	 * @return
	 */
	public String getConsultadDetalleCentroHospitalario(String idTabla){
		String sentencia = "select * from tbl_hospital t where id_hospital = "+idTabla+";";
		return sentencia;
	}
	
	
}
