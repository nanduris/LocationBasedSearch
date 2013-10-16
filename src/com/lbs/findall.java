package com.lbs;

import java.io.IOException;




import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

//import java.util.List;


import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
//import android.location.Address;
//import android.location.Geocoder;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class findall extends ListActivity implements OnClickListener,
		LocationListener {

	String add;
    String area;
    String UserNames[];
    String UserNumbers[];                     
	String UserLatitude[];
	String UserLongitude[];
	String finalLat[];
	String finalLon[];
	public String Area(double x,double y)
	{
		Geocoder gc= new Geocoder(this);
	       
        try {
            List<Address> addresses = gc.getFromLocation(x,y,1); 
               

            add = "";
            if (addresses.size() > 0) 
            {
                for (int i=0; i<addresses.get(0).getMaxAddressLineIndex(); 
                     i++)
                   add += addresses.get(0).getAddressLine(i) + "\n";
            } 
        }
        catch (IOException e) {                
            e.printStackTrace();
        }
		return(add);

	}

	@Override
	public void onCreate(Bundle SavedInstanceState) {
		super.onCreate(SavedInstanceState);

		try {
			LocationManager lm = (LocationManager) getSystemService(LOCATION_SERVICE);

			lm.requestLocationUpdates("gps", 1, 1, this);

			String urlString = "http://10.5.1.172" +
					":8080/DummyWebApp/FindAllServlet?latitude="
					+ screen.sLat + "&longitude=" + screen.sLon;

			urlString = urlString.replace(' ', '+');

			URL url = new URL(urlString);

			System.out.println("URL: " + urlString);

			HttpURLConnection connection = (HttpURLConnection) url
					.openConnection();

			InputStream is = connection.getInputStream();
			ObjectInputStream ois = new ObjectInputStream(is);
			UserNames = (String[]) ois.readObject();
			UserNumbers = (String[]) ois.readObject();
			UserLatitude = (String[]) ois.readObject();
			UserLongitude = (String[]) ois.readObject();
			int i;
			for( i=0;UserNames[i]!=null;i++)
			{		}
			
			if(UserNumbers[0]==null)
			{
			String total1[]=new String[1];
			total1[0]="No results found";
			setListAdapter(new ArrayAdapter<String>(this, R.layout.findall,
					total1));
			}
				
			else
			{
			String total[]=new String[i];
			String Latitudes[]=new String[i];
			String Longitudes[]=new String[i];
			int available=0;
					
			for(int j=0;j<i;j++)
	        {
	        	for(int k=0;k<friend.count;k++)
	        	{   
	        		if((UserNumbers[j].compareTo(friend.contacts[k]))==0)
	        		{   System.out.println(UserNumbers[j]);
	        			total[available]=friend.contactnames[k]+"@"+UserNumbers[j];
	        			Latitudes[available]=UserLatitude[j];
	        			Longitudes[available]=UserLongitude[j];
	        			System.out.println(total[k]);
	        			available++;
	        			
	        		}
	        	}
	        	
	        	
	        }
			System.out.println(available);
			
			
			if(available==0)
			{ String total1[]=new String[1];
				total1[0]="no friends available";
				System.out.println("no friends");
				setListAdapter(new ArrayAdapter<String>(this, R.layout.findall,
						total1));
			}
			else
			{
				String finalist[]=new String[available];
				finalLat=new String[available];
				finalLon=new String[available];
				for(int m=0;m<available;m++)
				{
					finalist[m]=total[m];
					finalLat[m]=Latitudes[m];
					finalLon[m]=Longitudes[m];
					
				}
				
				
			setListAdapter(new ArrayAdapter<String>(this, R.layout.findall,
					finalist));
			}	
			}
										
	    	} catch (Exception e) {
			e.printStackTrace();
		    }

	}

	public void onListItemClick(ListView l, View view, int position, long id) {
		double lat=Double.parseDouble(finalLat[position]);
		double lon=Double.parseDouble(finalLon[position]);
		area= Area(lat,lon);
		
		screen.dLat=UserLatitude[position];
		screen.dLon=UserLongitude[position];
		
		new AlertDialog.Builder(this).setMessage("Location:"+area)
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
		Intent i = new Intent(this, routemap.class);
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
