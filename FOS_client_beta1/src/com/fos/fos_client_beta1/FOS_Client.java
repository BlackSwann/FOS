package com.fos.fos_client_beta1;
//import javax.net.ssl.*;
//import java.security.SecureRandom;
//import java.security.cert.X509Certificate;


//import javax.net.ssl.HttpsURLConnection;
//import javax.net.ssl.SSLContext;
//import javax.net.ssl.TrustManager;
/*import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;*/
import java.net.URL;
import java.util.ArrayList;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONArray;
import org.json.JSONException;

import android.os.AsyncTask;

import com.sun.jersey.api.client.AsyncWebResource;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.WebResource.Builder;

//import org.glassfish.jersey.client.ClientConfig;


public class FOS_Client {
	
	private static Client client;
	private static WebResource resource;
	//private static AsyncWebResource resource;
	
	//private String hostAdress = "https://localhost:3000";
	
	//paths to resources 
//	private WebTarget hostWebTarget;
//	private WebTarget productsWebTarget;
//	private WebTarget ordersWebTarget;
//	private WebTarget ordersDetailsWebTarget;
//	private WebTarget tablesWebTarget;
	
	static{
		client = Client.create();
		
		resource = client.resource("http://10.0.2.2:3000/");
		//resource = client.asyncResource("http://10.0.2.2:3000/");
	}
	
	public static void getProductsAsync(final ProductsActivity productsActivity){
		
		//new AsyncTask<URL,Void,JSONArray>(){
		new AsyncTask<ProductsActivity,Void,JSONArray>(){

			/**
			 * request the Productsresource of the webservice
			 * 
			 * @return an JSONArray with all Products and their infomations
			 */
			@Override
			protected JSONArray doInBackground(ProductsActivity... params) {
				return getProducts();
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
					productsActivity.productList = productsList
														.toArray(productsActivity.productList);
					
			}
			
		}.execute(productsActivity);//.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
	}
	
	public static JSONArray getProducts()
	{		
		resource.path("products");
		
		//resource.type(MediaType.APPLICATION_JSON_TYPE);
		
		//ClientResponse response = resource.get(ClientResponse.class);
		//ClientResponse response = resource.accept("application/json").get(ClientResponse.class);
		Builder b = resource.accept("application/json");
		ClientResponse response = b.get(ClientResponse.class);
		

		if (response.getStatus() != 200) {
			throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
		}
		
		//String output = response.getEntity(String.class);
		JSONArray arr = response.getEntity(JSONArray.class);
		/*JSONArray arr = null;
		
		try {
			arr = new JSONArray(output);
		} catch (JSONException e) {
			e.printStackTrace();
		}*/

		return arr;
	}
	
	public static String getOrders()
	{
		resource.path("orders");
		
		ClientResponse response = resource.accept("application/json")
				.get(ClientResponse.class);

		if (response.getStatus() != 200) {
			throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
		}
		
		String output = response.getEntity(String.class);
		
		return output;
	}
	
	/*public FOS_Client(){
		//ClientConfig clientConfig = new ClientConfig();
		//clientConfig.register(MyClientResponseFilter.class);
		//clientConfig.register(new AnotherClientFilter());
		
		//Client client = ClientBuilder.newClient(clientConfig);
		
//		TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
//			public X509Certificate[] getAcceptedIssuers() {
//				return null;
//			}
//			public void checkClientTrusted(X509Certificate[] certs, String authType) {}
//			public void checkServerTrusted(X509Certificate[] certs, String authType) {}
//		} };
//
//		SSLContext context = SSLContext.getInstance("TLS");
//		context.init(null, trustAllCerts, new SecureRandom());
//		HttpsURLConnection.setDefaultSSLSocketFactory(context.getSocketFactory());
//
//		ClientConfig config = new DefaultClientConfig();
//		config.getProperties().put(HTTPSProperties.PROPERTY_HTTPS_PROPERTIES, new HTTPSProperties(new HostnameVerifier() {
//			@Override
//			public boolean verify(String s, SSLSession sslSession) {
//				return true;
//			}
//		}, context));
//		
//		this.client = ClientBuilder.newClient(config);
		//this.client = ClientBuilder.newClient();
		
		//für localhost auch selbstsignierte Zertifikate akzeptieren
//		if(this.hostAdress.startsWith("https://localhost:") || this.hostAdress.startsWith("https://localhost/"))
//		{
//			//TODO
//			//TrustManager[] tManager = new TrustManager[]();
//
//			// Create a trust manager that does not validate certificate chains
//			TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager(){
//			    	public X509Certificate[] getAcceptedIssuers(){return null;}
//			    	public void checkClientTrusted(X509Certificate[] certs, String authType){}
//			    	public void checkServerTrusted(X509Certificate[] certs, String authType){}
//				}
//			};
//
//			// Install the all-trusting trust manager
//			try {
//			    SSLContext sc = SSLContext.getInstance("TLS");
//			    sc.init(null, trustAllCerts, new SecureRandom());
//			    HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
//			} catch (Exception e) {
//			    ;
//			}
//		}

		this.hostWebTarget = client.target("https://localhost:3000");
		
		//define paths to resources 
		this.productsWebTarget = hostWebTarget.path("products");
		this.ordersWebTarget = hostWebTarget.path("orders");
		this.ordersDetailsWebTarget = hostWebTarget.path("ordersdetails");
		this.tablesWebTarget = hostWebTarget.path("tables");
		
		//z. b. für GET hier die ID übergeben
		//WebTarget oneProductsWebTarget = productsWebTarget.path("1");
		
		
		Invocation.Builder invocationBuilder =
				//productsWebTargetWithQueryParam.request(MediaType.TEXT_PLAIN_TYPE);				
				//oneProductsWebTarget.request(MediaType.APPLICATION_JSON);
				//TODO unterschied klarmachen
				//oneProductsWebTarget.request(MediaType.APPLICATION_JSON_TYPE);
				//alle holen
				productsWebTarget.request(MediaType.APPLICATION_JSON_TYPE);
		invocationBuilder.header("some-header", "true");
		
		Response response = invocationBuilder.get();
		
		//returns requeststatus e.g. 200
		System.out.println(response.getStatus());
		
		//returns the JSON-String
		System.out.println(response.readEntity(String.class));
		response.readEntity(String.class);
		
		//POST
		//Response postResponse =
		//        helloworldWebTarget.request(MediaType.TEXT_PLAIN_TYPE)
		//                .post(Entity.entity("A string entity to be POSTed", MediaType.TEXT_PLAIN));
		
	}*/

	public String getOrderDetails()
	{
		String jsonOrderDetails = "";
		
		//TODO
		
		return jsonOrderDetails;
	}
	
	protected boolean orderProduct(String ID)
	{
		boolean result = false;
		
		//TODO -POST order
		//- auswertung zurückgeben z. b. anhand requeststatus,
		//oder durch neue Abfrage der orderdetails
		
		return result;
	}
}
