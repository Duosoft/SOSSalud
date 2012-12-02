package net.sgoliver.android;

public class InfoMapa {
	
	
	 public static Double latitud; 
	 public static Double longitud; 
	 public static String nombre;
	 
	 public InfoMapa(){
		 
	 }
	 
	 

	public static void setLatitud(Double latitud) {
		InfoMapa.latitud = latitud;
	}



	public static void setLongitud(Double longitud) {
		InfoMapa.longitud = longitud;
	}



	public static void setNombre(String nombre) {
		InfoMapa.nombre = nombre;
	}



	public static Double getLatitud() {
		return latitud;
	}

	public static Double getLongitud() {
		return longitud;
	}

	public static String getNombre() {
		return nombre;
	}
	 

}
