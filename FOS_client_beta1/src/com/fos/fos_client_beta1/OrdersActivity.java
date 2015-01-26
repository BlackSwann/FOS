package com.fos.fos_client_beta1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class OrdersActivity extends ListActivity {

	String[] orderList = {};
	ListView lView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_orders);
		
		setupListView();
	}

	protected void setupListView()
	{	
		//Adapter für die Liste die angezeigt werden soll
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, orderList);

		lView = getListView();
		lView.setAdapter(adapter);
		
		adapter.add("No Orders");
		
		ArrayList<HashMap<String,String>> allOrders = new ArrayList<HashMap<String,String>>();
		
		try {			
			//hole alle Bestellungen vom WebService holen
			allOrders = MainActivity.parseJson(Localhost.orders().getAsJson(String.class));
			
			//loesche alle Eintraege
			adapter.clear();
			
			//alle geholten Produkte hinzufuegen
			for(HashMap<String,String> product : allOrders){
				adapter.add( product.get("name") );
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			Toast.makeText(OrdersActivity.this, 
								"Beim der Verbindung mit dem Service ist ein Fehler ist aufgetreten",
								Toast.LENGTH_LONG
							).show();
		}
		
		//hinzufuegen der geholten Products zur ProductsListView
		String key = "", val = "";
			
		for(HashMap<String,String> map : allOrders)
		{
			for(Entry<String,String> entry : map.entrySet())
			{
				key = entry.getKey();
				if(key.contains("name"))
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
		}
		
		//Eventlistener hinzufuegen der auf die gewuenschte Activity umschaltet
		lView.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//boolean clickedProducts = true;
				
				//TODO products senden
				/*if(clickedProducts)
					startActivity(new Intent(OrdersActivity.this, ProductsActivity.class));
				else
					startActivity(new Intent(OrdersActivity.this, OrdersActivity.class));
					*/
			}
		});
		
		//products in ListView einfügen
	}
	
	@Override
	public void onListItemClick(ListView l,View v, int position, long id){
		
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
}
