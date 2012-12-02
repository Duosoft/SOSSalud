package net.sgoliver.android;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.widget.Toast;

import com.example.androidtransport.R;
import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.Projection;

public class OverlayMapa extends Overlay {

	public Double latitud ;
	private Double longitud;

	@Override
	public void draw(Canvas canvas, MapView mapView, boolean shadow) {
		Projection projection = mapView.getProjection();
		GeoPoint geoPoint = new GeoPoint(latitud.intValue(),
				longitud.intValue());

		if (shadow == false) {
			Point centro = new Point();
			projection.toPixels(geoPoint, centro);

			// Definimos el pincel de dibujo
			Paint p = new Paint();
			p.setColor(Color.BLUE);

			// Marca Ejemplo 1: Círculo y Texto
			// canvas.drawCircle(centro.x, centro.y, 5, p);
			// canvas.drawText("Sevilla", centro.x+10, centro.y+5, p);

			// Marca Ejemplo 2: Bitmap
			Bitmap bm = BitmapFactory.decodeResource(mapView.getResources(),
					R.drawable.marcador_google_maps);

			canvas.drawBitmap(bm, centro.x - bm.getWidth(),
					centro.y - bm.getHeight(), p);
		}
	}

	
	
	
	public Double getLatitud() {
		return latitud;
	}




	public void setLatitud(Double latitud) {
		this.latitud = latitud;
	}




	public Double getLongitud() {
		return longitud;
	}




	public void setLongitud(Double longitud) {
		this.longitud = longitud;
	}




	@Override
	public boolean onTap(GeoPoint point, MapView mapView) {
		Context contexto = mapView.getContext();
		String msg = "Lat: " + point.getLatitudeE6() / 1E6 + " - " + "Lon: "
				+ point.getLongitudeE6() / 1E6;

		Toast toast = Toast.makeText(contexto, msg, Toast.LENGTH_SHORT);
		toast.show();

		return true;
	}
}
