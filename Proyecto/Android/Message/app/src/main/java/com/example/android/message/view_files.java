package com.example.android.message;

import android.app.Activity;
import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.android.message.modelo.s_files;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;


public class view_files extends Activity {
    static public List<s_files> obj;
    public Context context=this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_files);
        setTitle("Archivos compartidos con "+List_Contacts.s_target);
        actualizar();
    }
    public void actu_f(MenuItem item){
        actualizar();
    }
    public void actualizar(){
        ListView list = (ListView) findViewById(R.id.listfiles);
        myasynctask task=new myasynctask();
        task.service="shared_files/"+Integer.toString(List_Contacts.target)+"/"+Integer.toString(List_Contacts.user);
        String resultado="";
        //task.doInBackground();
        try {
            resultado=task.execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        ObjectMapper mapper = new ObjectMapper();
        obj= null;
        try {
            obj = mapper.readValue(resultado,mapper.getTypeFactory().constructCollectionType(List.class,s_files.class));
        } catch (IOException e) {
            e.printStackTrace();
        }
        //String [] arr= new String[]{"asdf","miow","nya",":3",obj.get(0).title};
        ArrayList<String> arr = new ArrayList<String>();
        for (int i =0;i<obj.size();i++){
            arr.add(obj.get(i).name+"["+obj.get(i).date+"]");
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.fitem, arr);
        //ListView list = (ListView) findViewById(R.id.list);
        list.setAdapter(adapter);
    }
    public void downfile(View view){
        //http://www.softwarepassion.com/android-series-download-files-with-progress-dialog/
        TextView t = (TextView) view;
        String s_target=((String)t.getText()).split("\\[")[0];
        for (int i=0;i<obj.size();i++){
            if (s_target.equals(obj.get(i).name)){
//                down d=new down();
//                d.service=Integer.toString(obj.get(i).id);
//                d.name=obj.get(i).name;
//                d.execute();
                try {
                    DownloadManager download;
                    download = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
                    Uri uri = Uri.parse( List_Contacts.url+"files/"+Integer.toString(obj.get(i).id));
                    DownloadManager.Request request = new DownloadManager.Request(uri);
                    request.setTitle(obj.get(i).name);
                    request.setDescription("Android Data download using DownloadManager.");
                    request.setDestinationInExternalFilesDir(this, Environment.DIRECTORY_DOWNLOADS, obj.get(i).name);
                    long downloadReference = download.enqueue(request);
                }catch (Exception e){
                    System.out.println(e.getMessage());
                }
                break;
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_view_files, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
