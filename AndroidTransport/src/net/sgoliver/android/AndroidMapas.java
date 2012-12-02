package net.sgoliver.android;

import java.util.List;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.example.androidtransport.R;
import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;

public class AndroidMapas extends MapActivity {

	private MapView mapa = null;
	private Button btnSatelite = null;
	private Button btnCentrar = null;
	private MapController controlMapa = null;
    private String[] coord = null;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_android_mapas);
		Log.i("Mostrar MAPA","4");
		
		Bundle bundle=getIntent().getExtras();
		
		InfoMapa m = new InfoMapa();
		m.setNombre(bundle.getString("nombre"));
		
		//Log.i("hola", bundle.getString("coordenada"));
		coord = (String.valueOf(bundle.getString("coordenada")).split(",")); 
		m.setLatitud(Double.parseDouble(coord[0]));
		m.setLongitud(Double.parseDouble(coord[1]));
		//Log.i("lati", coord[0]);
		//Log.i("long", coord[1]);
		
		// Obtenemos una referencia a los controles
		mapa = (MapView) findViewById(R.id.mapa);
		btnSatelite = (Button) findViewById(R.id.BtnSatelite);
		btnCentrar = (Button) findViewById(R.id.BtnCentrar);

		// Controlador del mapa
		controlMapa = mapa.getController();

		// Mostramos los controles de zoom sobre el mapa
		mapa.setBuiltInZoomControls(true);

		// Añadimos la capa de marcas
		List<Overlay> capas = mapa.getOverlays();
		OverlayMapa om = new OverlayMapa();
		om.setLatitud(Double.parseDouble(coord[0]) * 1E6);
		om.setLongitud(Double.parseDouble(coord[1]) * 1E6);
		capas.add(om);
		mapa.postInvalidate();

		btnSatelite.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				if (mapa.isSatellite())
					mapa.setSatellite(false);
				else
					mapa.setSatellite(true);
			}
		});

		btnCentrar.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				Double latitud = Double.parseDouble(coord[0]) * 1E6;
				Double longitud = Double.parseDouble(coord[1]) * 1E6;

				GeoPoint loc = new GeoPoint(latitud.intValue(), longitud
						.intValue());

				controlMapa.setCenter(loc);
				controlMapa.setZoom(10);
			}
		});

	}

	@Override
	protected boolean isRouteDisplayed() {
		return true;
	}
}
