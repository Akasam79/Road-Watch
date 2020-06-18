/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thinktank;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.simple.JSONObject;

/**
 *
 * @author HP
 */
public final class SendSMS {
    
    public static String now(String recipient, String msg, String sender)throws ClientProtocolException, IOException{

        JSONObject sms = new JSONObject();
            if (msg.length()>161)msg=msg.substring(0,161);
            sms.put("from", sender);
            sms.put("to", recipient);
            sms.put("text", msg);

        StringEntity entity = new StringEntity(sms.toString());

        StringBuilder result = new StringBuilder();
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpPost httpPost = new HttpPost("https://api.infobip.com/sms/1/text/single");
     
            httpPost.setHeader("Authorization", "Basic Y2hpbndhMTp0dmx3dXoxMiM=");
            httpPost.setHeader("Content-Type", "application/json");
            httpPost.setEntity(entity);
            
            CloseableHttpResponse response = client.execute(httpPost);
            
            BufferedReader rd = new BufferedReader(
		new InputStreamReader(response.getEntity().getContent()));

            String line;
            while ((line = rd.readLine()) != null) {
                result.append(line);
            }
        }
        return ""+result;
    }
    
    public static String notify(ArrayList<String> recipient, String msg, String sender) throws IOException{
        //notify agents via whatsapp
        
        //notify agents via android app
        
        //via text message
        String res = now(recipient, msg, sender);
        return res;
    }
    public static String now(ArrayList<String> recipient, String msg, String sender)throws ClientProtocolException, IOException{

        JSONObject sms = new JSONObject();
        Gson gson = new Gson();
            if (msg.length()>161)msg=msg.substring(0,161);
            sms.put("from", sender);
            sms.put("to", gson.toJsonTree(recipient));
            sms.put("text", msg);

        StringEntity entity = new StringEntity(sms.toString());

        StringBuilder result = new StringBuilder();
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpPost httpPost = new HttpPost("https://api.infobip.com/sms/1/text/single");
     
            httpPost.setHeader("Authorization", "Basic Y2hpbndhMTp0dmx3dXoxMiM=");
            httpPost.setHeader("Content-Type", "application/json");
            httpPost.setEntity(entity);
            
            CloseableHttpResponse response = client.execute(httpPost);
            
            BufferedReader rd = new BufferedReader(
		new InputStreamReader(response.getEntity().getContent()));

            String line;
            while ((line = rd.readLine()) != null) {
                result.append(line);
            }
        }
        return ""+result;
    }
}
