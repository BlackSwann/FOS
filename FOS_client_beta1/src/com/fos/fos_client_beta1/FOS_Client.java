package com.fos.fos_client_beta1;
//import javax.net.ssl.*;
//import java.security.SecureRandom;
//import java.security.cert.X509Certificate;


//import javax.net.ssl.HttpsURLConnection;
//import javax.net.ssl.SSLContext;
//import javax.net.ssl.TrustManager;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

//import org.glassfish.jersey.client.ClientConfig;


public class FOS_Client {
	
	private Client client;
	
	//private String hostAdress = "https://localhost:3000";
	
	//paths to resources 
	private WebTarget hostWebTarget;
	private WebTarget productsWebTarget;
	private WebTarget ordersWebTarget;
	private WebTarget ordersDetailsWebTarget;
	private WebTarget tablesWebTarget;
	
	public FOS_Client()
	{	 
		//ClientConfig clientConfig = new ClientConfig();
		//clientConfig.register(MyClientResponseFilter.class);
		//clientConfig.register(new AnotherClientFilter());
		
		//Client client = ClientBuilder.newClient(clientConfig);
		
		/*TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
			public X509Certificate[] getAcceptedIssuers() {
				return null;
			}
			public void checkClientTrusted(X509Certificate[] certs, String authType) {}
			public void checkServerTrusted(X509Certificate[] certs, String authType) {}
		} };

		SSLContext context = SSLContext.getInstance("TLS");
		context.init(null, trustAllCerts, new SecureRandom());
		HttpsURLConnection.setDefaultSSLSocketFactory(context.getSocketFactory());

		ClientConfig config = new DefaultClientConfig();
		config.getProperties().put(HTTPSProperties.PROPERTY_HTTPS_PROPERTIES, new HTTPSProperties(new HostnameVerifier() {
			@Override
			public boolean verify(String s, SSLSession sslSession) {
				return true;
			}
		}, context));
		
		this.client = ClientBuilder.newClient(config);*/
		this.client = ClientBuilder.newClient();
		
		//für localhost auch selbstsignierte Zertifikate akzeptieren
		/*if(this.hostAdress.startsWith("https://localhost:") || this.hostAdress.startsWith("https://localhost/"))
		{
			//TODO
			//TrustManager[] tManager = new TrustManager[]();

			// Create a trust manager that does not validate certificate chains
			TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager(){
			    	public X509Certificate[] getAcceptedIssuers(){return null;}
			    	public void checkClientTrusted(X509Certificate[] certs, String authType){}
			    	public void checkServerTrusted(X509Certificate[] certs, String authType){}
				}
			};

			// Install the all-trusting trust manager
			try {
			    SSLContext sc = SSLContext.getInstance("TLS");
			    sc.init(null, trustAllCerts, new SecureRandom());
			    HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
			} catch (Exception e) {
			    ;
			}
		}*/

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
		
	}

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
