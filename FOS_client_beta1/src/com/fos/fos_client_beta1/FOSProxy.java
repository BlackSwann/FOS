package com.fos.fos_client_beta1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONException;

public class FOSProxy {

	static String serviceUrl = "http://10.0.2.2:3000/";
	static String otherURl = "192.168.178.20:3000";
	
	static String productsPath = "products.json";
	static String ordersPath = "orders.json";
	
	public static int createOrder(){
		int state = 0;
		
		//TODO
		
		return state;
	}
	
	//TODO noch absichern dass es keine Abstürze gibt
	public static JSONArray getProducts() throws IOException{
		
		JSONArray result = new JSONArray();
		
		URL url=null;
		
		try {
			url = new URL(serviceUrl+productsPath);
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
		}
        
        conn.setDoInput(true);
        
        // Starts the query
		conn.connect();
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

		return result;
	}
	
	public static JSONArray getOrders() throws IOException{
		
		JSONArray result = new JSONArray();
		
		URL url=null;
		
		try {
			url = new URL(serviceUrl+ordersPath);
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
		}
        
        conn.setDoInput(true);
        
        // Starts the query
		conn.connect();
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

		return result;
	}
}
