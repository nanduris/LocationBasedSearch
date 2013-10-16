package com.lbs;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;

public class routemap extends Activity{

	public void onCreate(Bundle savedInstanceState){
		
		super.onCreate(savedInstanceState);
		double sLat= screen.sLat;
		double sLon=screen.sLon;	
		String dLat=screen.dLat;
		String dLon=screen.dLon;
		
		WebView w = new WebView(this);
		w.loadUrl("http://maps.google.com/maps?f=d&w=en&saddr="+sLat+","+sLon+"&daddr="+dLat+","+dLon);
		
		setContentView(w);
		
		
		
	}
	
}
