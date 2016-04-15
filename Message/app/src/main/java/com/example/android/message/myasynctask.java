package com.example.android.message;

import android.os.AsyncTask;

import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

/**
 * Created by android on 18/03/2016.
 */
public class myasynctask extends AsyncTask<Void,Void,String> {
    public String service="";
    @Override
    protected String doInBackground(Void... voids) {
        String url=List_Contacts.url+service;
        // Create a new RestTemplate instance
        RestTemplate restTemplate = new RestTemplate();

        // Add the String message converter
        restTemplate.getMessageConverters().add(new StringHttpMessageConverter());

        // Make the HTTP GET request, marshaling the response to a String
        String result = restTemplate.getForObject(url, String.class);//, "Android");
        return result;
    }

}
