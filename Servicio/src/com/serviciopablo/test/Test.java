package com.serviciopablo.test;

import com.serviciopablo.aplication.Servicio_movil;

public class Test {
	
	
	public static void main(String[] args){
		Servicio_movil s = new Servicio_movil();
		
	/*	try {
			System.out.println(s.consultaCentrosHospitalarios("9"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		try {
			System.out.println(s.consultaDetalle("2"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
