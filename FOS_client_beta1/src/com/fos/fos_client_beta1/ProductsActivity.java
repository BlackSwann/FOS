package com.fos.fos_client_beta1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

import javax.ws.rs.core.MediaType;

import org.json.JSONArray;
import org.json.JSONException;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.WebResource.Builder;

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
	//GridLayout contentGrid;
	
	String serviceUrl ="http://10.0.2.2:3000/";
	
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
			//return FOS_Client.getProducts();
			//return this.getProducts();
			JSONArray arr=null;
			try {
				arr = this.Test();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return arr;
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
		
		private JSONArray Test() throws IOException{
			
			URL url=null;
			String path = "products.json";
			
			try {
				url = new URL(serviceUrl+path);
			} catch (MalformedURLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			
	        conn.setReadTimeout(10000 /* milliseconds */);
	        conn.setConnectTimeout(15000 /* milliseconds */);
	        
	        try {
				conn.setRequestMethod("GET");
			} catch (ProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        
	        conn.setDoInput(true);
	        
	        // Starts the query
			conn.connect();
	        int response = conn.getResponseCode();
	        
	        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line+"\n");
            }
            br.close();
            
            //oast.makeText(getApplicationContext(), sb.toString(), Toast.LENGTH_LONG).show();
            
            JSONArray result = new JSONArray();
			try {
				result = new JSONArray(sb.toString());
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return result;
		}
		
		private JSONArray getProducts()
		{		
			Client client = Client.create();
			WebResource resource = client.resource(serviceUrl);
			resource = resource.path("products");
			
			//resource.type(MediaType.APPLICATION_JSON);
			
			//ClientResponse response = resource.get(ClientResponse.class);
			//ClientResponse response = resource.accept("application/json").get(ClientResponse.class);
			//Builder b = resource.accept("application/json");
			Builder b = resource.accept(MediaType.APPLICATION_JSON_TYPE);
			String response = b.get(String.class);
			
			JSONArray arr = null;
			try {
				arr = new JSONArray(response);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			return arr;
		}
	}
}
