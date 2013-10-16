package com.lbs;

import java.io.InputStream;




import java.net.HttpURLConnection;
import java.net.URL;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class sharelocation extends Activity implements android.view.View.OnClickListener, LocationListener {
	
	String username;
	Button update;
	Button logout;
	EditText name;
	static sharelocation s;

	public void onCreate(Bundle SavedInstanceState) {
		super.onCreate(SavedInstanceState);
		setContentView(R.layout.shareloc);
		update = (Button) findViewById(R.id.update);
		name = (EditText)findViewById(R.id.name);
		logout=(Button)findViewById(R.id.logout);
		s = this;
		update.setOnClickListener(this);
		logout.setOnClickListener(this);
		
		}
		
		public void onClick(View v) {
		if(v==update){
		try {
            
			//username = name.getEditableText().toString();
			username="divya";
			LocationManager lm = (LocationManager) getSystemService(LOCATION_SERVICE);
						lm.requestLocationUpdates("gps", 1, 1, this);

			URL url = new URL(
					"http://10.5.1.172:8080/DummyWebApp/UpdateServlet?username=" + username
							+ "&latitude=" + screen.sLat + "&longitude=" + screen.sLon);

			HttpURLConnection connection = (HttpURLConnection) url
					.openConnection();

			InputStream is = connection.getInputStream();
			if(is.read()==1)
			{
				friend.flag=1;
				new AlertDialog.Builder(this)
				.setMessage("location updated")
				.setNegativeButton("ok",new DialogInterface.OnClickListener() {
		            public void onClick(DialogInterface dialog, int whichButton) {
		            	update.setVisibility(2);
					    logout.setVisibility(0);		            
		            }
		                                 }
		                            )
				.setCancelable(true)
				.show();
					
			}
			else
			{
				new AlertDialog.Builder(this)
				.setMessage("error")
				.setNegativeButton("ok",new DialogInterface.OnClickListener() {
		            public void onClick(DialogInterface dialog, int whichButton) {
		            	
		            }
		                                 }
		                            )
				.setCancelable(true)
				.show();
			 }
	         }
			catch (Exception e) {
			e.printStackTrace();
		    }
		}
		else if(v==logout)
		{
			System.out.println("remove button");
			try{
			URL url = new URL(
					"http://10.5.1.172:8080/DummyWebApp/LogoutServlet"
							);

			HttpURLConnection connection = (HttpURLConnection) url
					.openConnection();

			InputStream is = connection.getInputStream();
			if(is.read()==1)
			{
				friend.flag=0;
				new AlertDialog.Builder(this)
				.setMessage("ur location removed")
				.setNegativeButton("ok",new DialogInterface.OnClickListener() {
		            public void onClick(DialogInterface dialog, int whichButton) {
		               	//update.setVisibility(0);
		            	//logout.setVisibility(2);
		            }
		                                 }
		                            )
				.setCancelable(true)
				.show();
				
			}
			else
			{
				new AlertDialog.Builder(this)
				.setMessage("error")
				.setNegativeButton("ok", new DialogInterface.OnClickListener() {
		         public void onClick(DialogInterface dialog, int whichButton) {}
		                                 }
		                            )
				.setCancelable(true)
				.show();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		}
		
		
	}

	public void onLocationChanged(Location location) {
		screen.sLat = location.getLatitude();
		screen.sLon = location.getLongitude();
		try{
			
			URL url = new URL(
					"http://10.5.1.172:8080/DummyWebApp/UpdateLoc?latitude="
							
							+ screen.sLat + "&longitude=" + screen.sLon);

			HttpURLConnection connection = (HttpURLConnection) url
					.openConnection();
			InputStream is=connection.getInputStream();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		

		System.out.println("New Latitide" + screen.sLat);
		System.out.println("New Longitude" + screen.sLon);

	}

	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub

	}

	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub

	}

	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub

	}

}
