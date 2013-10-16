package com.lbs;

import java.io.InputStream;




import java.io.ObjectInputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import android.app.AlertDialog;
import android.app.ListActivity;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import android.view.View;

import android.widget.ArrayAdapter;
import android.widget.ListView;






public class results extends ListActivity implements  OnClickListener,LocationListener  {
		
	String Distance[];
	String Distance2[];
	String PlaceNames[];
	String PlaceArea[];
	String PlaceLatitude[];
	String PlaceLongitude[];
	String EventNames[];
	String EventDetails[];

	
	@Override
	public void onCreate(Bundle SavedInstanceState) {
		super.onCreate(SavedInstanceState);

		try {
			 LocationManager lm = (LocationManager) getSystemService(LOCATION_SERVICE);
			    
			    lm.requestLocationUpdates("gps", 1, 1, this);
			    		
						String param = categories.cat;
			URL url = new URL(
					"http://10.5.1.172:8080/DummyWebApp/TestServlet?cat="+param+"&latitude="+screen.sLat+"&longitude="+screen.sLon);

			HttpURLConnection connection = (HttpURLConnection) url
					.openConnection();

			InputStream is = connection.getInputStream();
			ObjectInputStream ois = new ObjectInputStream(is);
			PlaceNames = (String[]) ois.readObject();
			PlaceArea=(String[])ois.readObject();
			PlaceLatitude=(String[])ois.readObject();
			PlaceLongitude=(String[])ois.readObject();
			EventNames=(String[])ois.readObject();
			EventDetails=(String[])ois.readObject();
			
			int i;
			
			for( i=0;PlaceNames[i]!=null;i++)
			{		}
			
			for(int e=0;e<i;e++)
			{
			if(EventNames[e]==null)
			{
				EventNames[e]="no events";
				EventDetails[e]="-";
			}
			}
			
			if(PlaceNames[0]==null)
			{
				String total1[]=new String[1];
			total1[0]="No results found";	
			setListAdapter(new ArrayAdapter<String>(this, R.layout.results,
					total1));
			}
			else
			{
			String total[]=new String[i];
			Distance=new String[i];
			Distance2=new String[i];
			double lat1=screen.sLat;
			double lon1=screen.sLon;
		
			
			for(int k=0;k<i;k++)
			{
				double lat2=Double.parseDouble(PlaceLatitude[k]);
				double lon2=Double.parseDouble(PlaceLongitude[k]);
			int R = 6371; //km
			double deltaLat = (lat2-lat1)/57.29577951;
			double deltaLon = (lon2-lon1)/57.29577951;
			double a = Math.sin(deltaLat/2) * Math.sin(deltaLat/2) +
			       Math.cos(lat1/57.29577951) * Math.cos(lat2/57.29577951) *
			       Math.sin(deltaLon/2) * Math.sin(deltaLon/2);
			double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
			double d = R * c;
			Distance[k]=Double.toString(d);
			}			
			
			for(int l=0;l<i;l++)
			{
				Distance2[l]="";
			}
			for(int m=0;m<i;m++)
			{
				Distance2[m]+= Distance[m].charAt(0);
				Distance2[m]+= Distance[m].charAt(1);
				Distance2[m]+= Distance[m].charAt(2);
			}
			
			for(int j=0;j<i;j++)
			{
					total[j]=PlaceNames[j]+","+Distance2[j]+"km"+
					"\n"+PlaceArea[j];
			
			}
			setListAdapter(new ArrayAdapter<String>(this, R.layout.results,
					total));
			}
															
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public void onListItemClick(ListView l, View view, int position, long id)
	{
		screen.dLat=PlaceLatitude[position];
		screen.dLon=PlaceLongitude[position];
		new AlertDialog.Builder(this)
		.setMessage("Events \n"+EventNames[position])
		.setPositiveButton("routemap",this)
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
