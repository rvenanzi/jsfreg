package it.prova;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.entity.FileEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;


public class Httpretriever {
   
   private DefaultHttpClient client = new DefaultHttpClient();   
   
   public String retrieve(String url) {
      
        HttpGet getRequest = new HttpGet(url);

        // HttpHost proxy = new HttpHost("172.25.1.18", 8080);
        // client.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY, proxy);
        
      try {
         
         HttpResponse getResponse = client.execute(getRequest);
         final int statusCode = getResponse.getStatusLine().getStatusCode();
         
         if (statusCode != HttpStatus.SC_OK) { 
            System.out.println(getClass().getSimpleName() + "Error " + statusCode + " for URL " + url); 
            return null;
         }
         
         HttpEntity getResponseEntity = getResponse.getEntity();
         
         if (getResponseEntity != null) {
            return EntityUtils.toString(getResponseEntity);
         }
         
      } 
      catch (IOException e) {
         getRequest.abort();
         System.out.println(getClass().getSimpleName() + "Error for URL " + url + e);
      }
      
      return null;
      
   }
   
   public String retrievePost(String url) {

	   File file = new File("messaggio.txt");
	   FileEntity fileEntity = new FileEntity(file, "text/plain; charset=\"UTF-8\"");

	   HttpPost httppost = new HttpPost(url);
	   httppost.setEntity(fileEntity);
	   httppost.setHeader("Content-Type", "text/xml; charset=utf-8");
	   httppost.setHeader("SOAPAction", "");
	
	   try {
		   HttpResponse response = client.execute(httppost);
		   final int statusCode = response.getStatusLine().getStatusCode();
		   
		   if (statusCode != HttpStatus.SC_OK) { 
	            System.out.println(getClass().getSimpleName() + "Error " + statusCode + " for URL " + url); 
	            return null;
	       }
		   
		   HttpEntity entity = response.getEntity();
		   
		   if (entity != null) {
			   return EntityUtils.toString(entity);
		   }
		   
	   } catch (IOException e) {
		   	httppost.abort();
	         System.out.println(getClass().getSimpleName() + "Error for URL " + url + e);
	   }
	   
	   return null;

   }
   
   public InputStream retrieveStream(String url) {
      
      HttpGet getRequest = new HttpGet(url);
        
      try {
         
         HttpResponse getResponse = client.execute(getRequest);
         final int statusCode = getResponse.getStatusLine().getStatusCode();
         
         if (statusCode != HttpStatus.SC_OK) { 
        	 System.out.println(getClass().getSimpleName() + "Error " + statusCode + " for URL " + url); 
            return null;
         }

         HttpEntity getResponseEntity = getResponse.getEntity();
         return getResponseEntity.getContent();
         
      } 
      catch (IOException e) {
         getRequest.abort();
         System.out.println(getClass().getSimpleName() +  "Error for URL " + url + e);
      }
      
      return null;
      
   }
}
