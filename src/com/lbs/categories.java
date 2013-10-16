package com.lbs;

import android.app.ListActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.MenuItem.OnMenuItemClickListener;


import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class categories extends ListActivity implements OnMenuItemClickListener{

	 static String cat;
	 MenuItem Events;
	 MenuItem RouteMap;
	 @Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		String[] categories = getResources().getStringArray(R.array.categories);
		setListAdapter(new ArrayAdapter<String>(this, R.layout.categories,
				categories));
	}

		public void onListItemClick(ListView l, View view, int position, long id){
		cat= (String) ((TextView) view).getText();
		Intent intent = new Intent(this, results.class);
		startActivity(intent);
		
	}
		public boolean onCreateOptionsMenu(Menu menu) {
			
			MenuInflater inflater = new MenuInflater(this);
			inflater.inflate(R.menu.eventsmenu, menu);
			Events=(MenuItem)(menu.getItem(0));
			RouteMap=(MenuItem)(menu.getItem(1));
			Events.setOnMenuItemClickListener(this);
			RouteMap.setOnMenuItemClickListener(this);
			
			
			return super.onCreateOptionsMenu(menu);
			
			
		}

		public boolean onMenuItemClick(MenuItem item) {
			if(item==Events)
			{
				Toast.makeText(this, "hello1 ", 2000).show();
			}
			else if(item==RouteMap)
			{
				Toast.makeText(this, "hello2 ", 2000).show();
			}
			return false;
		}

		

}
