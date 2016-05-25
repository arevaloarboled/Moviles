package com.example.android.message;

import android.os.AsyncTask;
import android.os.Environment;

//import org.apache.http.HttpEntity;
//import org.apache.http.HttpResponse;
//import org.apache.http.client.HttpClient;
//import org.apache.http.client.methods.HttpPost;
//import org.apache.http.entity.StringEntity;
//import org.apache.http.entity.mime.HttpMultipartMode;
//import org.apache.http.entity.mime.MultipartEntity;
//import org.apache.http.entity.mime.content.FileBody;
//import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

/**
 * Created by android on 12/04/2016.
 */
public class sendata extends AsyncTask<Void,Void,String> {
    static String service="";
    static public String f="";
    private MultiValueMap<String, Object> formData;
    @Override
    protected void onPreExecute() {

        Resource resource = new ClassPathResource(f);

        // populate the data to post
        formData = new LinkedMultiValueMap<String, Object>();
        formData.add("description", "Spring logo");
        formData.add("file", resource);
    }
    @Override
    protected String doInBackground(Void... voids) {
        try {
            // The URL for making the POST request
            String url = List_Contacts.url+"files/"+Integer.toString(List_Contacts.user)+"/"+Integer.toString(List_Contacts.target);

            HttpHeaders requestHeaders = new HttpHeaders();

            // Sending multipart/form-data
            requestHeaders.setContentType(MediaType.MULTIPART_FORM_DATA);

            // Populate the MultiValueMap being serialized and headers in an HttpEntity object to use for the request
            HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<MultiValueMap<String, Object>>(formData, requestHeaders);

            // Create a new RestTemplate instance
            RestTemplate restTemplate = new RestTemplate(true);

            // Make the network request, posting the message, expecting a String in response from the server
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, requestEntity,String.class);
            String hola="";
            // Return the response body to display to the user
            ////return response.getBody();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return "1";
    }
}