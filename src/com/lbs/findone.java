package com.lbs;

import java.io.IOException;





import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
//import java.util.List;

import android.app.Activity;
//import android.location.Address;
//import android.location.Geocoder;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;


public class findone extends Activity implements android.view.View.OnClickListener{
	String userphone;
	EditText number;
    ImageButton searchno;
	TextView t;
	static findone f;
	String add;
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
                System.out.println(add);
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
		setContentView(R.layout.findone);
		f=this;
		t=(TextView)findViewById(R.id.results);
		number=(EditText)findViewById(R.id.findfriend);
		
		searchno=(ImageButton)findViewById(R.id.searchno);
		searchno.setOnClickListener(this);
		
	}
	
	public void onClick(View v) {
		
		try {
			userphone=number.getEditableText().toString();
			String copy=userphone;
			String x=copy.substring(0,3);
			String y=copy.substring(3,6);
			String z=copy.substring(6, 10);
			System.out.println(x+ ","+y+","+z);
			userphone=x+"-"+y+"-"+z;
			
			 URL url = new URL(
					"http://10.5.1.172:8080/DummyWebApp/FindOneServlet?userphone="+userphone);

			HttpURLConnection connection = (HttpURLConnection) url
					.openConnection();

			InputStream is = connection.getInputStream();
			ObjectInputStream ois = new ObjectInputStream(is);
			String latitude = (String) ois.readObject();
			String longitude=(String)ois.readObject();
			if(latitude.compareTo(" ")==0)
			{
				t.setText("search results \n Location not available");
			}
			else
			{
				double lat=Double.parseDouble(latitude);
				double lon=Double.parseDouble(longitude);
			    t.setText("search results \n"+Area(lat,lon));
		
			}
			
															
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

	