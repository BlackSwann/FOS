package com.fos.fos_client_beta1;

import java.util.ArrayList;
import java.util.HashMap;

import javax.ws.rs.core.MediaType;

import org.json.JSONArray;
import org.json.JSONException;

import android.app.ListActivity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridLayout;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;


public class ProductsActivity extends ListActivity {

	String[] productList = {};
	ListView lView;
	ArrayAdapter<String> adapter;
	GridLayout contentGrid;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_products);
		
		setupListView();
	}
	
	
	public void setupListView()
	{
		ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
        	new Poller().execute();
        }else{
        	productList = new String[] {"keine Netzwerkverbindung"};
        }
	        	
		
//		getAllProd();
		
//		lView = getListView();
//		
//		//Adapter für die Liste die angezeigt werden soll
//		adapter = new ArrayAdapter<String>(this,
//				android.R.layout.simple_list_item_1, productList);
//
//		lView.setAdapter(adapter);
		
		
		
		//hinzufuegen der geholten Products zur ProductsListView
		/*String key = "", val = "";
			
		for(HashMap<String,String> map : allProducts)
		{
			for(Entry<String,String> entry : map.entrySet())
			{
				if((key = entry.getKey()).contains("name"))
				{
					//key = entry.getKey();
					val = entry.getValue();
					
					//TODO products in ListView einfuegen
					adapter.add(val);				
				}
				else{
					//TODO ID usw muss auch gemerkt werden
					continue;
				}
			}
		}*/
		
		//Eventlistener hinzufuegen der auf die gewuenschte Activity umschaltet
		/*lView.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				//Toast.makeText(ProductsActivity.this, "auf ein Produkt geklickt", Toast.LENGTH_LONG).show();

				//boolean clickedProducts = true;
				
				//TODO products senden
//				if(clickedProducts)
//					startActivity(new Intent(ProductsActivity.this, ProductsActivity.class));
//				else
//					startActivity(new Intent(ProductsActivity.this, OrdersActivity.class));
//					
				
			}
		});*/
		
		//products in ListView einfügen
	}
	
	//private ArrayList<HashMap<String,String>> getAllProd(){
	protected void getAllProd(){
		
		ArrayList<HashMap<String,String>> allProducts = new ArrayList<HashMap<String,String>>();
		
		try {
			//String response = Localhost.products().getAsJson(String.class);
			//String response = FOS_Client.getProducts();
			
			FOS_Client.getProductsAsync(this);
			
			//is done in Products
			//allProducts = MainActivity.parseJson(response); 
				
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//FOS_Client client = new FOS_Client();
		
		/*
		try {			
			//hole alle Produkte vom WebService
			allProducts = MainActivity.parseJson(Localhost.products().getAsJson(String.class));

		} catch (Exception e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
		}
		finally{
			Toast.makeText(ProductsActivity.this, 
								"Beim der Verbindung mit dem Service ist ein Fehler ist aufgetreten",
								Toast.LENGTH_LONG
							).show();
		}
		*/
		//loesche alle Eintraege
		//adapter.clear();
		
		ArrayList<String> buffer = new ArrayList<String>();
		
		//alle geholten Produkte hinzufuegen
		for(HashMap<String,String> product : allProducts){
			//adapter.add( product.get("name") );
			buffer.add( product.get("name") );
		}
		
		this.productList = (String[]) buffer.toArray(productList);
		
		//return allProducts;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.products, menu);
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
		return super.onOptionsItemSelected(item);
	}
	
	private class Poller extends AsyncTask<Void,Void,JSONArray>{

		/**
		 * request the Productsresource of the webservice
		 * 
		 * @return an JSONArray with all Products and their infomations
		 */
		@Override
		protected JSONArray doInBackground(Void... params) {
			// TODO Auto-generated method stub
			return FOS_Client.getProducts();
		}
		
		/**
		 * Fills the View with the Productnames from the previous retrieved JSONArray
		 */
		@Override
		public void onPostExecute(JSONArray result){
			
				//Parsing the JSONArray
				ArrayList<String> productsList = new ArrayList<String>();
				
				try {
					productsList.addAll(MainActivity.parseJson2(result));
				} catch (JSONException e) {
					e.printStackTrace();
				}
				
				//listView mit den Geparsten werten bestücken
				ProductsActivity.this.productList =
						productsList.toArray(ProductsActivity.this.productList);

				adapter = new ArrayAdapter<String>(ProductsActivity.this,
						android.R.layout.simple_list_item_1, productList);
				ProductsActivity.this.setListAdapter(adapter);
				lView = getListView();
		}		
	}
}
