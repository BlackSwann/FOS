
package com.fos.fos_client_beta1;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Configurable;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import org.glassfish.jersey.uri.UriTemplate;

@Generated(value = {
    "wadl|file:/home/ich/workspace/FOS_client_beta/./wadl-dist-1.1.6/fos.wadl"
}, comments = "wadl2java, http://wadl.java.net", date = "2014-11-24T01:22:49.267+01:00")
public class Localhost {

    /**
     * The base URI for the resource represented by this proxy
     * 
     */
    
	public final static URI BASE_URI;
    private static String localUri = "http://localhost:3000/",
    						testUri = "http://192.168.178.20:3000/";
    
    static {
		URI originalURI = URI.create("http://localhost:3000/");
        
        // Look up to see if we have any indirection in the local copy
        // of META-INF/java-rs-catalog.xml file, assuming it will be in the
        // oasis:name:tc:entity:xmlns:xml:catalog namespace or similar duck type
        java.io.InputStream is = Localhost.class.getResourceAsStream("/META-INF/jax-rs-catalog.xml");
        if (is!=null) {
            try {
                // Ignore the namespace in the catalog, can't use wildcard until
                // we are sure we have XPath 2.0
                String found = javax.xml.xpath.XPathFactory.newInstance().newXPath().evaluate(
                    "/*[name(.) = 'catalog']/*[name(.) = 'uri' and @name ='" + originalURI +"']/@uri", 
                    new org.xml.sax.InputSource(is)); 
                if (found!=null && found.length()>0) {
                    originalURI = java.net.URI.create(found);
                }
                
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
            finally {
                try {
                    is.close();
                } catch (java.io.IOException e) {
                }
            }
        }
        BASE_URI = originalURI;
    }

    public static Localhost.Orders orders(Client client, URI baseURI) {
        return new Localhost.Orders(client, baseURI);
    }

    /**
     * Template method to allow tooling to customize the new Client
     * 
     */
    private static void customizeClientConfiguration(Configurable cc) {
    }

    /**
     * Template method to allow tooling to override Client factory
     * 
     */
    private static Client createClientInstance() {
		return ClientBuilder.newClient();
    }

    /**
     * Create a new Client instance
     * 
     */
    public static Client createClient() {
        Client client = createClientInstance();
        customizeClientConfiguration(client);
        return client;
    }

    public static Localhost.Orders orders() {
        return orders(createClient(), BASE_URI);
    }

    public static Localhost.Orders orders(Client client) {
        return orders(client, BASE_URI);
    }

    public static Localhost.Products products(Client client, URI baseURI) {
        return new Localhost.Products(client, baseURI);
    }

    public static Localhost.Products products() {
    	return products(createClient(), BASE_URI);
    }

    public static Localhost.Products products(Client client) {
        return products(client, BASE_URI);
    }

    public static class Orders {

        private Client _client;
        private UriBuilder _uriBuilder;
        private Map<String, Object> _templateAndMatrixParameterValues;

        private Orders(Client client, UriBuilder uriBuilder, Map<String, Object> map) {
            _client = client;
            _uriBuilder = uriBuilder.clone();
            _templateAndMatrixParameterValues = map;
        }

        /**
         * Create new instance using existing Client instance, and a base URI and any parameters
         * 
         */
        public Orders(Client client, URI baseUri) {
            _client = client;
            _uriBuilder = UriBuilder.fromUri(baseUri);
            _uriBuilder = _uriBuilder.path("orders");
            _templateAndMatrixParameterValues = new HashMap<String, Object>();
        }

        public<T >T getAsJson(GenericType<T> returnType) {
            UriBuilder localUriBuilder = _uriBuilder.clone();
            WebTarget resource = _client.target(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
            javax.ws.rs.client.Invocation.Builder resourceBuilder = resource.request("application/json");
            Response response;
            response = resourceBuilder.build("GET").invoke();
            switch (response.getStatus()) {
                case  400 :
                    throw new Localhost.WebApplicationExceptionMessage(response);
                    //break;
                default:
                    if (response.getStatus()>= 400) {
                        throw new Localhost.WebApplicationExceptionMessage(response);
                    }
            }
            return response.readEntity(returnType);
        }

        public<T >T getAsJson(Class<T> returnType) {
            UriBuilder localUriBuilder = _uriBuilder.clone();
            WebTarget resource = _client.target(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
            javax.ws.rs.client.Invocation.Builder resourceBuilder = resource.request("application/json");
            Response response;
            response = resourceBuilder.build("GET").invoke();
            switch (response.getStatus()) {
                case  400 :
                    throw new Localhost.WebApplicationExceptionMessage(response);
                    //break;
                default:
                    if (!Response.class.isAssignableFrom(returnType)) {
                        if (response.getStatus()>= 400) {
                            throw new Localhost.WebApplicationExceptionMessage(response);
                        }
                    }
            }
            if (!Response.class.isAssignableFrom(returnType)) {
                return response.readEntity(returnType);
            } else {
                return returnType.cast(response);
            }
        }

        public<T >T postAsJson(GenericType<T> returnType) {
            UriBuilder localUriBuilder = _uriBuilder.clone();
            WebTarget resource = _client.target(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
            javax.ws.rs.client.Invocation.Builder resourceBuilder = resource.request("application/json");
            Response response;
            response = resourceBuilder.build("POST").invoke();
            switch (response.getStatus()) {
                case  400 :
                    throw new Localhost.WebApplicationExceptionMessage(response);
                    //break;
                default:
                    if (response.getStatus()>= 400) {
                        throw new Localhost.WebApplicationExceptionMessage(response);
                    }
            }
            return response.readEntity(returnType);
        }

        public<T >T postAsJson(Class<T> returnType) {
            UriBuilder localUriBuilder = _uriBuilder.clone();
            WebTarget resource = _client.target(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
            javax.ws.rs.client.Invocation.Builder resourceBuilder = resource.request("application/json");
            Response response;
            response = resourceBuilder.build("POST").invoke();
            switch (response.getStatus()) {
                case  400 :
                    throw new Localhost.WebApplicationExceptionMessage(response);
                    //break;
                default:
                    if (!Response.class.isAssignableFrom(returnType)) {
                        if (response.getStatus()>= 400) {
                            throw new Localhost.WebApplicationExceptionMessage(response);
                        }
                    }
            }
            if (!Response.class.isAssignableFrom(returnType)) {
                return response.readEntity(returnType);
            } else {
                return returnType.cast(response);
            }
        }

        public Localhost.Orders.OrderID orderID(String orderid) {
            return new Localhost.Orders.OrderID(_client, _uriBuilder.buildFromMap(_templateAndMatrixParameterValues), orderid);
        }

        public static class OrderID {

            private Client _client;
            private UriBuilder _uriBuilder;
            private Map<String, Object> _templateAndMatrixParameterValues;

            private OrderID(Client client, UriBuilder uriBuilder, Map<String, Object> map) {
                _client = client;
                _uriBuilder = uriBuilder.clone();
                _templateAndMatrixParameterValues = map;
            }

            /**
             * Create new instance using existing Client instance, and a base URI and any parameters
             * 
             */
            public OrderID(Client client, URI baseUri, String orderid) {
                _client = client;
                _uriBuilder = UriBuilder.fromUri(baseUri);
                _uriBuilder = _uriBuilder.path("{orderID}");
                _templateAndMatrixParameterValues = new HashMap<String, Object>();
                _templateAndMatrixParameterValues.put("orderID", orderid);
            }

            /**
             * Create new instance using existing Client instance, and the URI from which the parameters will be extracted
             * 
             */
            public OrderID(Client client, URI uri) {
                _client = client;
                StringBuilder template = new StringBuilder(BASE_URI.toString());
                if (template.charAt((template.length()- 1))!= '/') {
                    template.append("/orders/{orderID}");
                } else {
                    template.append("orders/{orderID}");
                }
                _uriBuilder = UriBuilder.fromPath(template.toString());
                _templateAndMatrixParameterValues = new HashMap<String, Object>();
                UriTemplate uriTemplate = new UriTemplate(template.toString());
                HashMap<String, String> parameters = new HashMap<String, String>();
                uriTemplate.match(uri.toString(), parameters);
                _templateAndMatrixParameterValues.putAll(parameters);
            }

            /**
             * Get orderID
             * 
             */
            public String getOrderid() {
                return ((String) _templateAndMatrixParameterValues.get("orderID"));
            }

            /**
             * Duplicate state and set orderID
             * 
             */
            public Localhost.Orders.OrderID setOrderid(String orderid) {
                Map<String, Object> copyMap;
                copyMap = new HashMap<String, Object>(_templateAndMatrixParameterValues);
                UriBuilder copyUriBuilder = _uriBuilder.clone();
                copyMap.put("orderID", orderid);
                return new Localhost.Orders.OrderID(_client, copyUriBuilder, copyMap);
            }

            public<T >T postAsJson(GenericType<T> returnType) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                WebTarget resource = _client.target(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                javax.ws.rs.client.Invocation.Builder resourceBuilder = resource.request("application/json");
                Response response;
                response = resourceBuilder.build("POST").invoke();
                switch (response.getStatus()) {
                    case  400 :
                        throw new Localhost.WebApplicationExceptionMessage(response);
                        //break;
                    default:
                        if (response.getStatus()>= 400) {
                            throw new Localhost.WebApplicationExceptionMessage(response);
                        }
                }
                return response.readEntity(returnType);
            }

            public<T >T postAsJson(Class<T> returnType) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                WebTarget resource = _client.target(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                javax.ws.rs.client.Invocation.Builder resourceBuilder = resource.request("application/json");
                Response response;
                response = resourceBuilder.build("POST").invoke();
                switch (response.getStatus()) {
                    case  400 :
                        throw new Localhost.WebApplicationExceptionMessage(response);
                        //break;
                    default:
                        if (!Response.class.isAssignableFrom(returnType)) {
                            if (response.getStatus()>= 400) {
                                throw new Localhost.WebApplicationExceptionMessage(response);
                            }
                        }
                }
                if (!Response.class.isAssignableFrom(returnType)) {
                    return response.readEntity(returnType);
                } else {
                    return returnType.cast(response);
                }
            }

            public<T >T getAsJson(GenericType<T> returnType) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                WebTarget resource = _client.target(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                javax.ws.rs.client.Invocation.Builder resourceBuilder = resource.request("application/json");
                Response response;
                response = resourceBuilder.build("GET").invoke();
                switch (response.getStatus()) {
                    case  400 :
                        throw new Localhost.WebApplicationExceptionMessage(response);
                        //break;
                    default:
                        if (response.getStatus()>= 400) {
                            throw new Localhost.WebApplicationExceptionMessage(response);
                        }
                }
                return response.readEntity(returnType);
            }

            public<T >T getAsJson(Class<T> returnType) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                WebTarget resource = _client.target(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                javax.ws.rs.client.Invocation.Builder resourceBuilder = resource.request("application/json");
                Response response;
                response = resourceBuilder.build("GET").invoke();
                switch (response.getStatus()) {
                    case  400 :
                        throw new Localhost.WebApplicationExceptionMessage(response);
                        //break;
                    default:
                        if (!Response.class.isAssignableFrom(returnType)) {
                            if (response.getStatus()>= 400) {
                                throw new Localhost.WebApplicationExceptionMessage(response);
                            }
                        }
                }
                if (!Response.class.isAssignableFrom(returnType)) {
                    return response.readEntity(returnType);
                } else {
                    return returnType.cast(response);
                }
            }

            /*
            public<T >T postAsJson(GenericType<T> returnType) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                WebTarget resource = _client.target(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                javax.ws.rs.client.Invocation.Builder resourceBuilder = resource.request("application/json");
                Response response;
                response = resourceBuilder.build("POST").invoke();
                switch (response.getStatus()) {
                    case  400 :
                        throw new Localhost.WebApplicationExceptionMessage(response);
                        //break;
                    default:
                        if (response.getStatus()>= 400) {
                            throw new Localhost.WebApplicationExceptionMessage(response);
                        }
                }
                return response.readEntity(returnType);
            }

            public<T >T postAsJson(Class<T> returnType) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                WebTarget resource = _client.target(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                javax.ws.rs.client.Invocation.Builder resourceBuilder = resource.request("application/json");
                Response response;
                response = resourceBuilder.build("POST").invoke();
                switch (response.getStatus()) {
                    case  400 :
                        throw new Localhost.WebApplicationExceptionMessage(response);
                        //break;
                    default:
                        if (!Response.class.isAssignableFrom(returnType)) {
                            if (response.getStatus()>= 400) {
                                throw new Localhost.WebApplicationExceptionMessage(response);
                            }
                        }
                }
                if (!Response.class.isAssignableFrom(returnType)) {
                    return response.readEntity(returnType);
                } else {
                    return returnType.cast(response);
                }
            }*/

        }

    }

    public static class Products {

        private Client _client;
        private UriBuilder _uriBuilder;
        private Map<String, Object> _templateAndMatrixParameterValues;

        private Products(Client client, UriBuilder uriBuilder, Map<String, Object> map) {
            _client = client;
            _uriBuilder = uriBuilder.clone();
            _templateAndMatrixParameterValues = map;
        }

        /**
         * Create new instance using existing Client instance, and a base URI and any parameters
         * 
         */
        public Products(Client client, URI baseUri) {
            _client = client;
            _uriBuilder = UriBuilder.fromUri(baseUri);
            _uriBuilder = _uriBuilder.path("products");
            _templateAndMatrixParameterValues = new HashMap<String, Object>();
        }
/*
        public<T >T postAsXml(GenericType<T> returnType) {
            UriBuilder localUriBuilder = _uriBuilder.clone();
            WebTarget resource = _client.target(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
            javax.ws.rs.client.Invocation.Builder resourceBuilder = resource.request("application/xml");
            Response response;
            response = resourceBuilder.build("POST").invoke();
            switch (response.getStatus()) {
                case  400 :
                    throw new Localhost.WebApplicationExceptionMessage(response);
                    //break;
                default:
                    if (response.getStatus()>= 400) {
                        throw new Localhost.WebApplicationExceptionMessage(response);
                    }
            }
            return response.readEntity(returnType);
        }

        public<T >T postAsXml(Class<T> returnType) {
            UriBuilder localUriBuilder = _uriBuilder.clone();
            WebTarget resource = _client.target(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
            javax.ws.rs.client.Invocation.Builder resourceBuilder = resource.request("application/xml");
            Response response;
            response = resourceBuilder.build("POST").invoke();
            switch (response.getStatus()) {
                case  400 :
                    throw new Localhost.WebApplicationExceptionMessage(response);
                    //break;
                default:
                    if (!Response.class.isAssignableFrom(returnType)) {
                        if (response.getStatus()>= 400) {
                            throw new Localhost.WebApplicationExceptionMessage(response);
                        }
                    }
            }
            if (!Response.class.isAssignableFrom(returnType)) {
                return response.readEntity(returnType);
            } else {
                return returnType.cast(response);
            }
        }
*/
        public<T >T getAsJson(GenericType<T> returnType) {
            UriBuilder localUriBuilder = _uriBuilder.clone();
            WebTarget resource = _client.target(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
            javax.ws.rs.client.Invocation.Builder resourceBuilder = resource.request("application/json");
            Response response;
            response = resourceBuilder.build("GET").invoke();
            switch (response.getStatus()) {
                case  400 :
                    throw new Localhost.WebApplicationExceptionMessage(response);
                    //break;
                default:
                    if (response.getStatus()>= 400) {
                        throw new Localhost.WebApplicationExceptionMessage(response);
                    }
            }
            return response.readEntity(returnType);
        }

        public<T >T getAsJson(Class<T> returnType) {
            UriBuilder localUriBuilder = _uriBuilder.clone();
            WebTarget resource = _client.target(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
            javax.ws.rs.client.Invocation.Builder resourceBuilder = resource.request("application/json");
            Response response;
            response = resourceBuilder.build("GET").invoke();
            switch (response.getStatus()) {
                case  400 :
                    throw new Localhost.WebApplicationExceptionMessage(response);
                    //break;
                default:
                    if (!Response.class.isAssignableFrom(returnType)) {
                        if (response.getStatus()>= 400) {
                            throw new Localhost.WebApplicationExceptionMessage(response);
                        }
                    }
            }
            if (!Response.class.isAssignableFrom(returnType)) {
                return response.readEntity(returnType);
            } else {
                return returnType.cast(response);
            }
        }

        public Localhost.Products.Index index() {
            return new Localhost.Products.Index(_client, _uriBuilder.buildFromMap(_templateAndMatrixParameterValues));
        }

        public static class Index {

            private Client _client;
            private UriBuilder _uriBuilder;
            private Map<String, Object> _templateAndMatrixParameterValues;

            private Index(Client client, UriBuilder uriBuilder, Map<String, Object> map) {
                _client = client;
                _uriBuilder = uriBuilder.clone();
                _templateAndMatrixParameterValues = map;
            }

            /**
             * Create new instance using existing Client instance, and a base URI and any parameters
             * 
             */
            public Index(Client client, URI baseUri) {
                _client = client;
                _uriBuilder = UriBuilder.fromUri(baseUri);
                _uriBuilder = _uriBuilder.path("");
                _templateAndMatrixParameterValues = new HashMap<String, Object>();
            }

            /*public<T >T postAsJson(GenericType<T> returnType) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                WebTarget resource = _client.target(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                javax.ws.rs.client.Invocation.Builder resourceBuilder = resource.request("application/json");
                Response response;
                response = resourceBuilder.build("POST").invoke();
                switch (response.getStatus()) {
                    case  400 :
                        throw new Localhost.WebApplicationExceptionMessage(response);
                        //break;
                    default:
                        if (response.getStatus()>= 400) {
                            throw new Localhost.WebApplicationExceptionMessage(response);
                        }
                }
                return response.readEntity(returnType);
            }

            public<T >T postAsJson(Class<T> returnType) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                WebTarget resource = _client.target(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                javax.ws.rs.client.Invocation.Builder resourceBuilder = resource.request("application/json");
                Response response;
                response = resourceBuilder.build("POST").invoke();
                switch (response.getStatus()) {
                    case  400 :
                        throw new Localhost.WebApplicationExceptionMessage(response);
                        //break;
                    default:
                        if (!Response.class.isAssignableFrom(returnType)) {
                            if (response.getStatus()>= 400) {
                                throw new Localhost.WebApplicationExceptionMessage(response);
                            }
                        }
                }
                if (!Response.class.isAssignableFrom(returnType)) {
                    return response.readEntity(returnType);
                } else {
                    return returnType.cast(response);
                }
            }*/

            public<T >T getAsJson(GenericType<T> returnType) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                WebTarget resource = _client.target(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                javax.ws.rs.client.Invocation.Builder resourceBuilder = resource.request("application/json");
                Response response;
                response = resourceBuilder.build("GET").invoke();
                switch (response.getStatus()) {
                    case  400 :
                        throw new Localhost.WebApplicationExceptionMessage(response);
                        //break;
                    default:
                        if (response.getStatus()>= 400) {
                            throw new Localhost.WebApplicationExceptionMessage(response);
                        }
                }
                return response.readEntity(returnType);
            }

            public<T >T getAsJson(Class<T> returnType) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                WebTarget resource = _client.target(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                javax.ws.rs.client.Invocation.Builder resourceBuilder = resource.request("application/json");
                Response response;
                response = resourceBuilder.build("GET").invoke();
                switch (response.getStatus()) {
                    case  400 :
                        throw new Localhost.WebApplicationExceptionMessage(response);
                        //break;
                    default:
                        if (!Response.class.isAssignableFrom(returnType)) {
                            if (response.getStatus()>= 400) {
                                throw new Localhost.WebApplicationExceptionMessage(response);
                            }
                        }
                }
                if (!Response.class.isAssignableFrom(returnType)) {
                    return response.readEntity(returnType);
                } else {
                    return returnType.cast(response);
                }
            }

            public<T >T postAsJson(GenericType<T> returnType) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                WebTarget resource = _client.target(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                javax.ws.rs.client.Invocation.Builder resourceBuilder = resource.request("application/json");
                Response response;
                response = resourceBuilder.build("POST").invoke();
                switch (response.getStatus()) {
                    case  400 :
                        throw new Localhost.WebApplicationExceptionMessage(response);
                        //break;
                    default:
                        if (response.getStatus()>= 400) {
                            throw new Localhost.WebApplicationExceptionMessage(response);
                        }
                }
                return response.readEntity(returnType);
            }

            public<T >T postAsJson(Class<T> returnType) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                WebTarget resource = _client.target(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                javax.ws.rs.client.Invocation.Builder resourceBuilder = resource.request("application/json");
                Response response;
                response = resourceBuilder.build("POST").invoke();
                switch (response.getStatus()) {
                    case  400 :
                        throw new Localhost.WebApplicationExceptionMessage(response);
                        //break;
                    default:
                        if (!Response.class.isAssignableFrom(returnType)) {
                            if (response.getStatus()>= 400) {
                                throw new Localhost.WebApplicationExceptionMessage(response);
                            }
                        }
                }
                if (!Response.class.isAssignableFrom(returnType)) {
                    return response.readEntity(returnType);
                } else {
                    return returnType.cast(response);
                }
            }

        }

    }


    /**
     * Workaround for JAX_RS_SPEC-312
     * 
     */
    private static class WebApplicationExceptionMessage
        extends WebApplicationException
    {


        private WebApplicationExceptionMessage(Response response) {
            super(response);
        }

        /**
         * Workaround for JAX_RS_SPEC-312
         * 
         */
        public String getMessage() {
            Response response = getResponse();
            Response.Status status = Response.Status.fromStatusCode(response.getStatus());
            if (status!= null) {
                return (response.getStatus()+(" "+ status.getReasonPhrase()));
            } else {
                return Integer.toString(response.getStatus());
            }
        }

        public String toString() {
            String s = "javax.ws.rs.WebApplicationException";
            String message = getLocalizedMessage();
            return (s +(": "+ message));
        }

    }

}
