package com.lbs;

import android.app.Activity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.ImageButton;

	public class friend extends Activity implements android.view.View.OnClickListener{
		ImageButton findOne;
	    ImageButton findAll;
	    ImageButton shareloc;
		static friend f1;	   
		static int flag=0;
		static int count;
		static String contacts[];
		static String contactnames[];
		@Override		    
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.friend);
	        f1=this;
	        findOne=(ImageButton)findViewById(R.id.findone);
	        findAll=(ImageButton)findViewById(R.id.findall);
	        shareloc=(ImageButton)findViewById(R.id.sharelocation);
	        findOne.setOnClickListener(this);
	        findAll.setOnClickListener(this);
	        shareloc.setOnClickListener(this);
	       
	        getContacts();
	        
	        
	        for(int i=0;i<count;i++)
	       {
	        	System.out.println(contacts[i]+" , "+contactnames[i]);
	        }
	        
	        
	        
	    }
		public void onClick(View v) {
		 if(v == findOne){
		 Intent intent=new Intent(this,findone.class);
		 startActivity(intent);
		              	}
		else if(v == findAll) {
		Intent intent=new Intent(this,findall.class);
		startActivity(intent);
					    	}
		else if(v==shareloc) {
		Intent intent=new Intent(this,sharelocation.class);
		startActivity(intent);
					    	}		
		}
		


	    private void getContacts() {
	    	int i=0,j=0;
	    		Cursor c = getContentResolver().query(
	    				ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
	    		count=c.getCount();
	    		contacts=new String[count];
	    	    contactnames=new String[count];
	    		while (c.moveToNext()) {
	    			String ContactID = c.getString(c
	    					.getColumnIndex(ContactsContract.Contacts._ID));
	    			String name = c.getString(c
	    					.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
	    			System.out.println("Name: " + name + " _ID: " + ContactID);
	    			String hasPhone = c
	    					.getString(c
	    							.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER));
	    			if (Integer.parseInt(hasPhone) == 1) {
	    				Cursor phoneCursor = getContentResolver().query(
	    						ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
	    						null,
	    						ContactsContract.CommonDataKinds.Phone.CONTACT_ID
	    								+ "='" + ContactID + "'", null, null);
	    			
	    				while (phoneCursor.moveToNext()) {
	    					String number = phoneCursor
	    							.getString(phoneCursor
	    									.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
	    					String numberType = phoneCursor
	    							.getString(phoneCursor
	    									.getColumnIndex(ContactsContract.CommonDataKinds.Phone.TYPE));
	    					switch (Integer.parseInt(numberType)) {
	    					// insert switch cases to handle all types of phone numbers.
	    					// There are 20 types!
	    					default:
	    						contacts[i++]=number;
	    						contactnames[j++]=name;
	    						System.out.println("phone number: " + number + "("
	    								+ numberType + ")");
	    						break;
	    					}

	    				}
	    				phoneCursor.close();
	    			} else {
	    				System.out.println("no phone number found");
	    			}
	    		}
	    	}

	    
	    
		    
		    
		    
		
		
		
		
		
		
		
		
	}
	