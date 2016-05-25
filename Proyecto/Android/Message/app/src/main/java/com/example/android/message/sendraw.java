package com.example.android.message;

import android.os.AsyncTask;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

/**
 * Created by android on 12/04/2016.
 */
public class sendraw extends AsyncTask<Void,Void,String> {
    public String service="";
    public String form;
    public String to;
    public String text;
    @Override
    protected String doInBackground(Void... voids) {
        String url = List_Contacts.url+"messages"+service;
        HttpClient hc = new DefaultHttpClient();
        String message;

        HttpPost p = new HttpPost(url);
        JSONObject object = new JSONObject();
        try {

            object.put("from", Integer.parseInt(form));
            object.put("to", Integer.parseInt(to));
            object.put("text", text);

        } catch (Exception ex) {

        }

        try {
            message = object.toString();


            p.setEntity(new StringEntity(message, "UTF8"));
            p.setHeader("Content-type", "application/json");
            HttpResponse resp = hc.execute(p);
//            if (resp != null) {
//                if (resp.getStatusLine().getStatusCode() == 204)
//                    result = true;
//            }
//
//            Log.d("Status line", "" + resp.getStatusLine().getStatusCode());
        } catch (Exception e) {
            e.printStackTrace();

        }

        return "1";
    }
}