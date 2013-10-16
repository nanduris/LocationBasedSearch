package com.lbs;

	import android.app.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;



	public class browse extends Activity implements android.view.View.OnClickListener{
	    /** Called when the activity is first created. */
		Button browse;
		static browse b;
	    @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.browse);
	        b=this;
	        browse=(Button)findViewById(R.id.browse);
	        browse.setOnClickListener(this);
	        }
	        public void onClick(View v)
	        {
	        Intent intent=new Intent(this,categories.class);
	        startActivity(intent);
	        }
	        
	    }
	

