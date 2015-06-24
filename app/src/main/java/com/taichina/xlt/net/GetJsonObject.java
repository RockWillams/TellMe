package com.taichina.xlt.net;

import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.protocol.HTTP;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by uu on 2015-06-17.
 */
public class GetJsonObject {
    int test =1;
    JSONObject jsonObject2 = new JSONObject();
    JSONObject jsonObject3 = new JSONObject();

    public JSONObject GetJsonObject(final JSONObject jsonObject,final String addr) {

        new Thread() {
            public void run() {
                Log.e("+++", "222222");
                HttpClient client = new DefaultHttpClient();
                StringBuilder builder = new StringBuilder();
                client.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 2000);
                client.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 2000);

                HttpPost httpPost = new HttpPost(addr);


                StringEntity entity = null;


                Log.e("sssstjq", jsonObject.toString());

                try {
                    entity = new StringEntity(jsonObject.toString(), HTTP.UTF_8);
                } catch (Exception e) {

                }
                entity.setContentType("application/json");
                httpPost.setEntity(entity);
                Log.e("input tjq", jsonObject.toString());
                try {
                    HttpResponse response = client.execute(httpPost);
                    if (response.getStatusLine().getStatusCode() == 200) {
                        BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
                        for (String s = reader.readLine(); s != null; s = reader.readLine()) {
                            builder.append(s);
                        }
                        jsonObject2 = new JSONObject(builder.toString());

                        Log.e("output + tjq", jsonObject2.toString());
                    } else {
                        Log.e("connection error", "error");
                    }

                } catch (Exception e) {
                    Log.e("eror","eooro");
                    e.printStackTrace();
                    if(test == 1){
                        try {

                            jsonObject3.put("keyNotes","keyNotes1111");
                            jsonObject3.put("keyItems","keyItems2222");
                            jsonObject3.put("verifyRecords", "verifyRecords3333");
                            jsonObject3.put("e", 0);

                        }catch (Exception e1){

                        }

                    }
                }


            }
        }.start();
        if ( jsonObject2 == null)
            return jsonObject3;

        return jsonObject3;


    }

}
