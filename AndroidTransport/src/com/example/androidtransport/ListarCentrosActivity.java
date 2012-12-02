package com.example.androidtransport;

import java.io.IOException;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ListarCentrosActivity  extends  ListActivity {

	//definir estrutura de la lista
	public class Node{
			public String idTabla;
			public String nombre;
			public String coordenada;
			public String telefono;
			public String direccion;
			public String dependencia;
	}
	
	private static ArrayList<Node>mArray = new ArrayList<ListarCentrosActivity.Node>();
	
	
	private String URL = "http://"+Global.getDireccionIp()+":8080/ServicioDAL/services/Servicio_movil";
	private String NAMESPACE = "http://aplication.serviciopablo.com";
	
	private MyAdapter mAdapter = null;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        mArray.clear();
        new ObtenerConsultaBackground().execute("9");
        
	}
	
	public void carga()
	{
		mAdapter = new MyAdapter(this);
        this.setListAdapter(mAdapter);
	}
	
	protected void onListItemClick(ListView l,View v, int position, long id){
		
		Intent s = new Intent(this, MostrarDetalleActivity.class);
		s.putExtra("identificador", mArray.get(position).idTabla);
		startActivity(s);
		
	}
	
	
	
class ObtenerConsultaBackground extends AsyncTask<String, Void, String>{
    	
    	private ProgressDialog progressDialog;

		@Override
		protected void onPreExecute() {
    		progressDialog = ProgressDialog.show(
    				ListarCentrosActivity.this,
    				"S.O.S.Salud Prototipo", 
    				"Espere mientras se cargan los centros hospitalarios...", 
    				true);
			super.onPreExecute();
		}
		

		@Override
		protected String doInBackground(String... params) {
			String resultado = new String();
			String METHOD_NAME = "consultaCentrosHospitalarios";
			String SOAP_ACTION = NAMESPACE +"/" +METHOD_NAME;
			SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
			SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
			soapEnvelope.dotNet = true;
			soapEnvelope.implicitTypes = true;
			soapEnvelope.setOutputSoapObject(request);

			request.addProperty("json", params[0]);

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
				Toast.makeText(ListarCentrosActivity.this, "Error al obtener la informacion necesaria", Toast.LENGTH_LONG).show();
			}else if("0".equals(result)){
				Toast.makeText(ListarCentrosActivity.this, "Problemas con la consulta", Toast.LENGTH_LONG).show();
			}else{
				try {
					
					JSONArray hospitalesJSONArray = new JSONArray(result);
					int cantPedidos = hospitalesJSONArray.length();
					for(int i = 0; i< cantPedidos; i ++){
							Node mynode = new Node();
							mynode.idTabla  = hospitalesJSONArray.getJSONObject(i).getString("cod_tabla");
							mynode.nombre = hospitalesJSONArray.getJSONObject(i).getString("nombre");
							//mynode.coordenada = hospitalesJSONArray.getJSONObject(i).getString("mesa");
							//mynode.telefono = hospitalesJSONArray.getJSONObject(i).getString("mesa");
							mynode.direccion = hospitalesJSONArray.getJSONObject(i).getString("direccion");
							Log.i("hola", hospitalesJSONArray.getJSONObject(i).getString("nombre"));
							//mynode.dependencia = hospitalesJSONArray.getJSONObject(i).getString("mesa");
							mArray.add(mynode);
					}
					if(cantPedidos==0){
						Toast.makeText(ListarCentrosActivity.this, "No existen registros de pedidos", Toast.LENGTH_LONG).show();
						
					}
					 carga();
				} catch (JSONException e) {
					Toast.makeText(ListarCentrosActivity.this, "2.Error al obtener la informacion necesaria", Toast.LENGTH_LONG).show();
				}
			}
		}
	
	}
	
	
	public class MyAdapter extends BaseAdapter
	{

		private Context mContext;
		
		public MyAdapter(Context c){
			mContext = c;
		}
		public int getCount() {
			// TODO Auto-generated method stub
			return mArray.size();
		}
		public Object getItem(int posicion) {
			// TODO Auto-generated method stub
			return mArray.get(posicion);
		}
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return 0;
		}
		
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			View view = null;
			
			if(convertView==null){
				LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				view = inflater.inflate(R.layout.activity_listado_hospitales, null);
				//Log.i("Se creo el objeto", "1");
			}else{
				view = convertView;	
			}
			TextView title = (TextView) view.findViewById(R.id.nombre);
			title.setText(mArray.get(position).nombre);
			
			TextView Tdesciption = (TextView) view.findViewById(R.id.direccion);
			Tdesciption.setText(mArray.get(position).direccion);
			
			return view;
		}
		
	}	
	
	

	
}

