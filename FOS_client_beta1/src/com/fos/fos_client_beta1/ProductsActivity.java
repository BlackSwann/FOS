package com.fos.fos_client_beta1;

import java.util.ArrayList;
import java.util.HashMap;
import android.app.ListActivity;
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
	GridLayout contentGrid;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_products);
		
		setupListView();
	}
	
	
	public void setupListView()
	{
		//holt alle products und speichert sie in der productList
		getAllProd();
		
		lView = getListView();
		
		//Adapter für die Liste die angezeigt werden soll
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, productList);

		lView.setAdapter(adapter);
		
		
		
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
		lView.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Toast.makeText(ProductsActivity.this, "auf ein Produkt geklickt", Toast.LENGTH_LONG)
						.show();

				//boolean clickedProducts = true;
				
				//TODO products senden
				/*if(clickedProducts)
					startActivity(new Intent(ProductsActivity.this, ProductsActivity.class));
				else
					startActivity(new Intent(ProductsActivity.this, OrdersActivity.class));
					*/
				
			}
		});
		
		//products in ListView einfügen
	}
	
	//private ArrayList<HashMap<String,String>> getAllProd(){
	private void getAllProd(){
		
		ArrayList<HashMap<String,String>> allProducts = new ArrayList<HashMap<String,String>>();
		
		allProducts = MainActivity.parseJson(Localhost.products().getAsJson(String.class));
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
}
