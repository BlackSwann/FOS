package com.fos.fos_client_beta1;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class FOSProxy {

	/**
	 * for my real device
	 */
	//static String otherURl = "192.168.178.20:3000";
	
	/**
	 * Android Zugriff auf http://localhost:3000
	 */
	//static String serviceUrl = "http://10.0.2.2:3000/";
	static String serviceUrl = "https://10.0.2.2:3000/";
	
	static String tablesPath = "tables/free";
	static String ordersPath = "orders";
	static String productsPath = "products";
	
	/**
	 * Send the selected product as order to the service
	 * @param productId
	 * @param productName
	 * @return response state
	 * @throws IOException
	 */
	public static int createOrder(String productId,String productName) throws IOException
	{
		int state = 0;
		
		JSONObject newOrder = new JSONObject();
		try {
			newOrder.put("id", productId);
			newOrder.put("name", productName);
		} catch (JSONException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		//jsonParam.put("enable", "true");

		//The encoded JSON-String to post
		try {
			String encodedJson = URLEncoder.encode(newOrder.toString(),"UTF-8");
		} catch (UnsupportedEncodingException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		//TODO
		
		//JSONArray result = new JSONArray();
		
		URL url=null;
		
		try {
			url = new URL(serviceUrl+ordersPath+"/new");
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		}

		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		
        conn.setReadTimeout(10000 /* milliseconds */);
        conn.setConnectTimeout(15000 /* milliseconds */);
        
        try {
			conn.setRequestMethod("POST");
			//conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			state = conn.getResponseCode();
		} catch (ProtocolException e) {
			e.printStackTrace();
			
			state = conn.getResponseCode();
		}finally{
			//TODO ggf. Meldung hier zum result hinzufügen
		}
        
        //conn.setUseCaches (false);
        conn.setDoInput(true);
        conn.setDoOutput(true);
        
//        DataOutputStream wr = new DataOutputStream (conn.getOutputStream ());
//        wr.writeBytes (encodedJson);
//        wr.flush ();
//        wr.close ();
        OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
        //wr.write(encodedJson);
        wr.write(newOrder.toString());
        
        
        // GetResponse
		try {
			conn.connect();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
        int response = conn.getResponseCode();
        if(response != 400)
        {
	        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	        StringBuilder sb = new StringBuilder();
	        String line;
	        while ((line = br.readLine()) != null) {
	            sb.append(line+"\n");
	        }
	        br.close();
	        
	        state = conn.getResponseCode();
        }else{
    		state = conn.getResponseCode();
        }
        
		conn.disconnect();
		
		return state;
	}
	//TODO noch absichern dass es keine Abstürze gibt
	
	/**
	 * Falls benötigt wird dass die Festlegung dynamisch sein soll auch freien Tisch
	 * abrufen
	 * @return JSONArray with all free tables
	 * @throws IOException
	 */
	public static JSONArray getFreeTables() throws IOException{
		
		JSONArray result = new JSONArray();
		
		URL url=null;
		
		try {
			url = new URL(serviceUrl+tablesPath+".json");
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		}

		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		
        conn.setReadTimeout(10000 /* milliseconds */);
        conn.setConnectTimeout(15000 /* milliseconds */);
        
        try {
			conn.setRequestMethod("GET");
		} catch (ProtocolException e) {
			e.printStackTrace();
			
			try {
				result = new JSONArray("[{\"name\":\"Keine Verbindung zum Service\"}]");
			} catch (JSONException e1) {
				e.printStackTrace();
			}
		}
        
        conn.setDoInput(true);
        
        // Starts the query
		try {
			conn.connect();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
        int response = conn.getResponseCode();
        if(response != 400)
        {
	        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	        StringBuilder sb = new StringBuilder();
	        String line;
	        while ((line = br.readLine()) != null) {
	            sb.append(line+"\n");
	        }
	        br.close();
	        
			try {
				result = new JSONArray(sb.toString());
			} catch (JSONException e) {
				e.printStackTrace();
			}
        }else{
        	try {
				result = new JSONArray("[{\"name\":\"Keine Verbindung zum Service\"}]");
			} catch (JSONException e) {
				e.printStackTrace();
			}
        }
        
        conn.disconnect();
        
		return result;
	}

	/**
	 * get all Order you toke
	 * @return
	 * @throws IOException
	 */
	public static JSONArray getOrders() throws IOException{
		
		JSONArray result = new JSONArray();
		
		URL url=null;
		
		try {
			url = new URL(serviceUrl+ordersPath+".json");
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		}

		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		
        conn.setReadTimeout(10000 /* milliseconds */);
        conn.setConnectTimeout(15000 /* milliseconds */);
        
        try {
			conn.setRequestMethod("GET");
		} catch (ProtocolException e) {
			e.printStackTrace();
			
			try {
				result = new JSONArray("[{\"name\":\"Keine Verbindung zum Service.\"}]");
			} catch (JSONException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}finally{
			//TODO ggf. Meldung hier zum result hinzufügen
		}
        
        conn.setDoInput(true);
        
        // Starts the query
		try {
			conn.connect();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
        int response = conn.getResponseCode();
        if(response != 400)
        {
	        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	        StringBuilder sb = new StringBuilder();
	        String line;
	        while ((line = br.readLine()) != null) {
	            sb.append(line+"\n");
	        }
	        br.close();
	        
			try {
				result = new JSONArray(sb.toString());
			} catch (JSONException e) {
				e.printStackTrace();
			}
        }else{
        	try {
				result = new JSONArray("[{\"name\":\"Keine Verbindung zum Service.\nCode: "+response+"\"}]");
			} catch (JSONException e) {
				e.printStackTrace();
			}
        }
        
        conn.disconnect();

		return result;
	}
	
	/**
	 * get all products you can order
	 * @return JSONArray with all Products
	 * @throws IOException
	 */
	public static JSONArray getProducts() throws IOException{
		
		JSONArray result = new JSONArray();
		
		URL url=null;
		
		try {
			url = new URL(serviceUrl+productsPath+".json");
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		}

		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		
        conn.setReadTimeout(10000 /* milliseconds */);
        conn.setConnectTimeout(15000 /* milliseconds */);
        
        try {
			conn.setRequestMethod("GET");
		} catch (ProtocolException e) {
			e.printStackTrace();
			
			try {
				result = new JSONArray("[{\"name\":\"Keine Verbindung zum Service\"}]");
			} catch (JSONException e1) {
				e.printStackTrace();
			}
		}
        
        conn.setDoInput(true);
        
        // Starts the query
		try {
			conn.connect();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
        int response = conn.getResponseCode();
        if(response != 400)
        {
	        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	        StringBuilder sb = new StringBuilder();
	        String line;
	        while ((line = br.readLine()) != null) {
	            sb.append(line+"\n");
	        }
	        br.close();
	        
			try {
				result = new JSONArray(sb.toString());
			} catch (JSONException e) {
				e.printStackTrace();
			}
        }else{
        	try {
				result = new JSONArray("[{\"name\":\"Keine Verbindung zum Service\"}]");
			} catch (JSONException e) {
				e.printStackTrace();
			}
        }
        
        conn.disconnect();
        
		return result;
	}
}
