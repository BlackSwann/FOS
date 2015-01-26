package com.fos.fos_client_beta1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

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
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class OrdersActivity extends ListActivity {
	
	ListView lView;
	ArrayAdapter<String> adapter;
	
	String[] orderList = {};
	HashMap<String,String> ordersMap = new HashMap<String,String>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_orders);
		
		setupListView();
	}

	protected void setupListView()
	{	
		//nur wenn überhautp Internetverbindung vorhanden ist holen
		ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
        		new OrderPoller().execute();
        }else{
        	orderList = new String[] {"keine Netzwerkverbindung"};
        }
        
      //Eventlistener hinzufuegen der auf die gewuenschte Activity umschaltet
  		lView.setOnItemClickListener(new OnItemClickListener(){

  			@Override
  			public void onItemClick(AdapterView<?> parent, View view,
  					int position, long id) {
  				// TODO Auto-generated method stub
  				TextView clickedText = (TextView)view;

  				//TODO in HASHMAP suchen und createOrder
  				
  				//Product oder Orders geklickt?
  				/*if(productList[0].toString() == clickedText.getText())
  					startActivity(new Intent(ProductsActivity.this, ProductsActivity.class));
  				else if(productList[1].toString() == clickedText.getText())
  					startActivity(new Intent(ProductsActivity.this, ProductsActivity.class));
  				*/
  			}
  			
  		});
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.orders, menu);
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
	
	private class OrderPoller extends AsyncTask<Void,Void,JSONArray>{

		/**
		 * request the Productsresource of the webservice
		 * 
		 * @return an JSONArray with all Products and their infomations
		 */
		@SuppressWarnings("finally")
		@Override
		protected JSONArray doInBackground(Void... params) {
			JSONArray arr = new JSONArray();
			try {
				arr = FOSProxy.getOrders();
			} catch (Exception e) {
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
				for(int i=0; i<result.length(); i++)
				{
					key = "";
					val = "";
					
					try {
						key = result.getJSONObject(i).getString("name");
						val = result.getJSONObject(i).getString("id");
					} catch (JSONException e) {
						e.printStackTrace();
					}

					ordersMap.put(key, val);
				}
				
				//Parsing the JSONArray
				ArrayList<String> parsedOrdersList = new ArrayList<String>();
				
				try {
					parsedOrdersList.addAll(MainActivity.parseJson2(result));
				} catch (JSONException e) {
					e.printStackTrace();
				}
				
				//listView mit den Geparsten werten bestücken
				OrdersActivity.this.orderList =
						parsedOrdersList.toArray(OrdersActivity.this.orderList);
			}
			else{
				OrdersActivity.this.orderList = 
						new String[]{"Keine Bestellungen gefunden"};
			}
			
			adapter = new ArrayAdapter<String>(OrdersActivity.this,
					android.R.layout.simple_list_item_1, orderList);
			OrdersActivity.this.setListAdapter(adapter);
			lView = getListView();
		}	
	}
}
