package com.lbs;

import java.io.InputStream;

import java.io.ObjectInputStream;
import java.net.HttpURLConnection;
import java.net.URL;


import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class everesults extends ListActivity implements OnClickListener,LocationListener {
	
	String EventNames[];
	String EventVenue[];
	String EventDetails[];
	String EventLatitude[];
	String EventLongitude[];
	String EventArea[];
	
	@Override
	public void onCreate(Bundle SavedInstanceState) {
		super.onCreate(SavedInstanceState);

		try {
			 LocationManager lm = (LocationManager) getSystemService(LOCATION_SERVICE);
			    
			    lm.requestLocationUpdates("gps", 1, 1, this);
			    
			
			
			String param = eventCategories.eveCat;
			URL url = new URL(
					"http://10.5.1.172:8080/DummyWebApp/EventServlet?evecat="+param+"&latitude="+screen.sLat+"&longitude="+screen.sLon);

			HttpURLConnection connection = (HttpURLConnection) url
					.openConnection();

			InputStream is = connection.getInputStream();
			ObjectInputStream ois = new ObjectInputStream(is);
			EventNames = (String[]) ois.readObject();
			EventVenue=(String[])ois.readObject();
			EventDetails=(String[])ois.readObject();
			EventLatitude=(String[])ois.readObject();
			EventLongitude=(String[])ois.readObject();
			EventArea=(String[])ois.readObject();
			
			int i;
			for(i=0;EventNames[i]!=null;i++){}
			
			
			if(EventNames[0]==null)
			{   String total1[]=new String[1];
				total1[0]="no results found";
				setListAdapter(new ArrayAdapter<String>(this, R.layout.everesults,
						total1));
				
			}
			else{
				String total[]=new String[i];
									
			for(int j=0;j<i;j++)
			{
				total[j]=EventNames[j]+" @ "+EventVenue[j]+"\n"+EventArea[j];
			}
			setListAdapter(new ArrayAdapter<String>(this, R.layout.everesults,
					total));
			}
		
			
													
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void onListItemClick(ListView l, View view, int position, long id)
	{
		screen.dLat=EventLatitude[position];
		screen.dLon=EventLongitude[position];
		new AlertDialog.Builder(this)
		.setMessage("Event Detals:\n"+EventDetails[position])
		.setPositiveButton("routemap", this)
		.setNegativeButton("cancel",
                new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {}
                                 }
                            )
		.setCancelable(true)
		.show();
						
	}
		
	public void onClick(DialogInterface arg0, int arg1) {
		Intent i= new Intent(this,routemap.class);
		startActivity(i);
		
	}

	public void onLocationChanged(Location location) {
		
		screen.sLat=location.getLatitude();
	    screen.sLon=location.getLongitude();
	    
	    System.out.println("New Latitide"+screen.sLat);
	    System.out.println("New Longitude"+screen.sLon);
	  
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
