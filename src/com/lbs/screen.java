package com.lbs;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class screen extends Activity implements android.view.View.OnClickListener{
    /** Called when the activity is first created. */
    
	static Double sLat=17.498022;
	static Double sLon=78.388583 ;	
	
	static String dLat;
	static String dLon;	
	
	
	
	ImageButton place;
    ImageButton events;
    ImageButton friends;
	static screen s1;
	 @Override
    public void onCreate(Bundle SavedInstanceState)
    {
    super.onCreate(SavedInstanceState);
    setContentView(R.layout.main);
    s1=this;
    place=(ImageButton)findViewById(R.id.place);
    events=(ImageButton)findViewById(R.id.events);
    friends=(ImageButton)findViewById(R.id.friends);
    place.setOnClickListener(this);
    events.setOnClickListener(this);
    friends.setOnClickListener(this);
    
    
   
    
    }
    public void onClick(View v)
    {
    	if(v == place){
    		Intent intent=new Intent(this,browse.class);
    	    startActivity(intent);
    	}
    	else if(v == events)
    	{
    		Intent intent=new Intent(this,eventCategories.class);
    	    startActivity(intent);
    	}
    	else if(v == friends)
    	{
    		Intent intent=new Intent(this,friend.class);
    	    startActivity(intent);
    	}
    	
    	
    	
    	    	
    	
    }
	    	    }






