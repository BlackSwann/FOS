package com.fos.fos_client_beta1;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.GridLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * This Android-Client is intended to work with the Fast Ordering System - Webservice.
 * 
 * This is the MainActivity of the Android-Client
 * 
 * @author Group 10 - Noguera, Brandt
 * @version beta1 
 */
public class MainActivity extends ListActivity {

	String[] optionList = {"Products","Your Orders"};
	ListView lView;
	int tableNumber = 1; 

	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		setupListView();
		
		//TODO getTableNumber
	}
	
	protected void setupListView()
	{
		lView = getListView();

		//Adapter für die Liste die angezeigt werden soll
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
						android.R.layout.simple_list_item_1, optionList);
				
		lView.setAdapter(adapter);
		
		//Eventlistener hinzufuegen der auf die gewuenschte Activity umschaltet
		lView.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				TextView clickedText = (TextView)view;

				//Product oder Orders geklickt?
				if(optionList[0].toString() == clickedText.getText())
					startActivity(new Intent(MainActivity.this, ProductsActivity.class));
				else if(optionList[1].toString() == clickedText.getText())
					startActivity(new Intent(MainActivity.this, OrdersActivity.class));
			}
			
		});
		
		//TODO get first free table
		
		Toast.makeText(MainActivity.this, "You are on table "+this.tableNumber,
						Toast.LENGTH_LONG).show();
	}
	
	protected static ArrayList<HashMap<String,String>> parseJson(String asJson) {
		ArrayList<HashMap<String,String>> result = new ArrayList<HashMap<String,String>>();
		HashMap<String,String> product;
		String[] fullArray, productVal, keyVal;
		
		asJson = asJson.replace("\"", "");
		asJson = asJson.substring(2,asJson.length()-4);
		fullArray = asJson.split("},{");
		
		for(String productString : fullArray )
		{
			product = new HashMap<String,String>();
					
			//einzelne Key-Val-Paare extrahieren
			productVal = productString.split(",");
			for(String idVal : productVal)
			{
				//key:val teilen
				keyVal = idVal.split(":");
				
				product.put(keyVal[0],keyVal[1]);
			}

			result.add(product);
		}
		
		return result;
	}

	@Override 
	public void onListItemClick(ListView l,View v, int position, long id){
		TextView clickedText = (TextView)v;

		//Product oder Orders geklickt?
		if(optionList[0].toString() == clickedText.getText())
			startActivity(new Intent(MainActivity.this, ProductsActivity.class));
		else if(optionList[1].toString() == clickedText.getText())
			startActivity(new Intent(MainActivity.this, OrdersActivity.class));
	}
	
	protected static ArrayList<String> parseJson2(JSONArray arr) throws JSONException{
		
		ArrayList<String> result = new ArrayList<String>();
		
		for(int i=0; i<arr.length(); i++)
			result.add(arr.getJSONObject(i).getString("name"));
		
		return result;
	}
	
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
    	menu.add("Beenden(test)");
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
    	
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        
        //falls beenden geklickt -> aktivity.finish
        if(item.getTitle().toString().contains("Beenden")){
        	finish();
        }
        
        return super.onOptionsItemSelected(item);
    }
}
