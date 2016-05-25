package com.example.android.message;

import android.app.DownloadManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by android on 13/04/2016.
 */
public class down extends AsyncTask<Void,Void,String> {
    public static String service="";
    public  static  String name="";
    @Override
    protected String doInBackground(Void... voids) {
        //http://stackoverflow.com/questions/29180130/document-path-is-not-valid-error-in-android-emulator
//        String dir = List_Contacts.url+"files/"+service;
//        int count;
//        try {
//            URL url = new URL(dir);
//            URLConnection conexion = url.openConnection();
//            conexion.connect();
//
//            int lenghtOfFile = conexion.getContentLength();
//
//            InputStream input = new BufferedInputStream(url.openStream());
//            OutputStream output = new FileOutputStream(Environment.getDataDirectory()+"/"+name);
//
//            byte data[] = new byte[1024];
//
//            while ((count = input.read(data)) != -1) {
//                output.write(data, 0, count);
//            }
//
//            output.flush();
//            output.close();
//            input.close();
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }

//        DownloadManager download;
//        download = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
//        Uri Download_Uri = Uri.parse( List_Contacts.url+"files/"+service);
//        DownloadManager.Request request = new DownloadManager.Request(Download_Uri);
//        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI | DownloadManager.Request.NETWORK_MOBILE);
//        //Set whether this download may proceed over a roaming connection.
//        request.setAllowedOverRoaming(false);
//        //Set the title of this download, to be displayed in notifications (if enabled).
//        request.setTitle(name);
//        //Set a description of this download, to be displayed in notifications (if enabled)
//        request.setDescription("Android Data download using DownloadManager.");
//        //Set the local destination for the downloaded file to a path within the application's external files directory
//        request.setDestinationInExternalFilesDir(this, Environment.DIRECTORY_DOWNLOADS, name);
//
//        //Enqueue a new download and same the referenceId
//        long downloadReference = download.enqueue(request);
        return "1";
    }
}
