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
import android.content.Intent;
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
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;


public class ProductsActivity extends ListActivity {

	String[] productList = {};
	ListView lView;
	ArrayAdapter<String> adapter;
	//GridLayout contentGrid;
	HashMap<String,String> productsMap = new HashMap<String,String>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_products);
		
		setupListView();
	}

	public void setupListView()
	{
		//nur wenn überhautp Internetverbindung vorhanden ist holen
		ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
        		//Asynchron die Daten abfragen
        		new ProductPoller().execute();
        }else{
        	productList = new String[] {"keine Netzwerkverbindung"};
        }
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
	
	private class ProductPoller extends AsyncTask<Void,Void,JSONArray>{

		/**
		 * request the Productsresource of the webservice
		 * 
		 * @return an JSONArray with all Products and their infomations
		 */
		@SuppressWarnings("finally")
		@Override
		protected JSONArray doInBackground(Void... params) {
			JSONArray arr=new JSONArray();
			try {
				arr = FOSProxy.getProducts();
			//} catch (IOException e) {
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				return arr;
			}
		}
		
		/**
		 * Fills the View with the Productnames from the previous retrieved JSONArray
		 */
		@Override
		public void onPostExecute(JSONArray result)
		{	
			//Falls keine Elemente -> leerer ListView
			//sonst parsen und an ListView binden
			if(0 < result.length())
			{
				//Das JSON-Array parsen und als HashMapspeichern
				String key, val;
				for(int i=0; i<result.length(); i++){
					key = "";
					val = "";
					
					try {
						key = result.getJSONObject(i).getString("name");
						val = result.getJSONObject(i).getString("id");
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					productsMap.put(key, val);
				}
				
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
			}
			else{
				ProductsActivity.this.productList = 
						new String[]{"Keine Produkte gefunden"};
			}
			
			//Alles der View hinzufügen
			adapter = new ArrayAdapter<String>(ProductsActivity.this,
					android.R.layout.simple_list_item_1, productList);
			ProductsActivity.this.setListAdapter(adapter);
			lView = getListView();
			
			//Listener erstellen
			lView.setOnItemClickListener(new OnItemClickListener(){
				
				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					// TODO Auto-generated method stub
					TextView clickedText = (TextView)view;
		
					Toast.makeText(ProductsActivity.this,
							clickedText.getText(), Toast.LENGTH_LONG).show();
					//TODO in HASHMAP suchen und createOrder
					
					//Product oder Orders geklickt?
//					if(productList[0].toString() == clickedText.getText())
//						startActivity(new Intent(ProductsActivity.this, ProductsActivity.class));
//					else if(productList[1].toString() == clickedText.getText())
//						startActivity(new Intent(ProductsActivity.this, ProductsActivity.class));
					
				}
				
			});
		}	
	}
}
