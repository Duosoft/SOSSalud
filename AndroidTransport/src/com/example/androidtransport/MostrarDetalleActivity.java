package com.example.androidtransport;

import java.io.IOException;

import net.sgoliver.android.*;

import org.json.JSONArray;
import org.json.JSONException;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MostrarDetalleActivity extends Activity {

	
	private String URL = "http://"+Global.getDireccionIp()+":8080/ServicioDAL/services/Servicio_movil";
	private String NAMESPACE = "http://aplication.serviciopablo.com";
	
	private TextView nombreET;
	private TextView direccionET;
	private TextView telefonoET;
	private TextView dependenciaET;
	
	private String coordenada; 
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("llego", "llego");
        setContentView(R.layout.activity_detalle_hospital);
        
        nombreET = (TextView) findViewById(R.id.txtnombre);
        direccionET = (TextView) findViewById(R.id.txtDireccion);
        telefonoET = (TextView) findViewById(R.id.txtTelefono);
        dependenciaET = (TextView) findViewById(R.id.txtDependencia);
        
        
        Bundle extra = this.getIntent().getExtras();
        Log.i("msm", extra.getString("identificador"));
        new ObtenerConsultaDetalle().execute(extra.getString("identificador"));
        
        
       
    }
	public void verMapa(View v){
		Log.i("pasar a mapas", "0");
		Intent i = new Intent(MostrarDetalleActivity.this,AndroidMapas.class);
		i.putExtra("coordenada", coordenada);
		i.putExtra("nombre", nombreET.getText());
		startActivity(i);
	}

   
	
	
class ObtenerConsultaDetalle extends AsyncTask<String, Void, String>{
    	
    	private ProgressDialog progressDialog;

		@Override
		protected void onPreExecute() {
    		progressDialog = ProgressDialog.show(
    				MostrarDetalleActivity.this,
    				"S.O.S.Salud Prototipo", 
    				"Espere mientras se carga el detalle del centro asistencial...", 
    				true);
			super.onPreExecute();
		}
		

		@Override
		protected String doInBackground(String... params) {
			String resultado = new String();
			String METHOD_NAME = "consultaDetalle";
			String SOAP_ACTION = NAMESPACE +"/" +METHOD_NAME;
			SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
			SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
			soapEnvelope.dotNet = true;
			soapEnvelope.implicitTypes = true;
			soapEnvelope.setOutputSoapObject(request);
			Log.i("parametro", params[0]);
			request.addProperty("id", params[0]);

			HttpTransportSE aht = new HttpTransportSE(URL);

			try {
				aht.call(SOAP_ACTION, soapEnvelope);
				SoapPrimitive resultString = (SoapPrimitive)soapEnvelope.getResponse();
				resultado = resultString.toString();

			} catch (IOException e) {
				e.printStackTrace();
			} catch (XmlPullParserException e) {
				e.printStackTrace();
			}

			return resultado;
		}
		
		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);
			Log.i("llegada", result);
			progressDialog.dismiss();
			if("".equals(result)){
				Toast.makeText(MostrarDetalleActivity.this, "Error al obtener la informacion necesaria", Toast.LENGTH_LONG).show();
			}else if("0".equals(result)){
				Toast.makeText(MostrarDetalleActivity.this, "Problemas con la consulta", Toast.LENGTH_LONG).show();
			}else{
				try {
				JSONArray hospitalesJSONArray = new JSONArray(result);
				
				int cantPedidos = hospitalesJSONArray.length();
				
				for(int i = 0; i< cantPedidos; i ++){
					Log.i("dato",cantPedidos+ hospitalesJSONArray.getJSONObject(i).getString("nombre"));
					    nombreET.setText(hospitalesJSONArray.getJSONObject(i).getString("nombre"));
						telefonoET.setText(hospitalesJSONArray.getJSONObject(i).getString("telefono"));
						dependenciaET.setText(hospitalesJSONArray.getJSONObject(i).getString("dependencia"));
						direccionET.setText(hospitalesJSONArray.getJSONObject(i).getString("direccion"));
						coordenada = hospitalesJSONArray.getJSONObject(i).getString("localizacion");
				}
				if(cantPedidos==0){
					Toast.makeText(MostrarDetalleActivity.this, "No existen registros de pedidos", Toast.LENGTH_LONG).show();
					
				}
				
			} catch (JSONException e) {
				Toast.makeText(MostrarDetalleActivity.this, "2.Error al obtener la informacion necesaria", Toast.LENGTH_LONG).show();
			}	
			}
		}
	
	}
}
