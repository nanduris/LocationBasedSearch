package com.lbs;

import android.app.ListActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;


import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;



public class eventCategories extends ListActivity{
    /** Called when the activity is first created. */
	 static String eveCat;
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	  super.onCreate(savedInstanceState);
    	    	  String[] eventCategories = getResources().getStringArray(R.array.eventCategories);
    	  setListAdapter(new ArrayAdapter<String>(this, R.layout.evecat,eventCategories));
    	   	     	  
    }
    public void onListItemClick(ListView l, View view, int position, long id){
		eveCat= (String) ((TextView) view).getText();
		Intent intent = new Intent(this, everesults.class);
		startActivity(intent);
		
	}
}

