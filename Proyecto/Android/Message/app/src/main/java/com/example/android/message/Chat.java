package com.example.android.message;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.example.android.message.modelo.mensaje;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

class MyThread extends Thread{
    public volatile boolean running=true;
    /*public void parar() throws InterruptedException
    {
        running = false;
    }*/
}

public class Chat extends Activity {
    final int ACTIVITY_CHOOSE_FILE = 1;
    final int TAKE_PHOTO_CODE = 0;
    static public String filePath;
    public List<UnMensaje> mensajes=null;
    public List<UnMensaje> mensajesCopia=null;
    public ArrayAdapter<String> adapter_thread;
    public ArrayList<String> arr_thread;
    public String dir="";
    public MyThread thread;

    @Override
    protected void onResume() {
        super.onResume();/*
        if (!thread.isAlive()){
            thread.run();
        }*/
    }

    @Override
    protected void onPause() {
        super.onPause();
        //thread.interrupt();
        /*
        thread.running=false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
    }

    @Override
    protected void onStop() {
        super.onStop();
        //thread.interrupt();
        //thread.running=false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //thread.destroy();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        setTitle(List_Contacts.s_target);
        /*thread=new MyThread(){
            @Override
            public void run() {
                    while (running) {
                        try {
                            sleep(1000);
                            actualizarBD();
                            actualizar_thread();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
            }
        };
        thread.start();*/
        actualizarBD();
        actualizar();
    }
    public void actualizar_thread(){
        mensajesCopia=mensajes;
        mensajes=List_Contacts.bd.consultar(Integer.toString(List_Contacts.target),Integer.toString(List_Contacts.user));
        if(mensajesCopia!=null && mensajes!=null){
            if(mensajesCopia.equals(mensajes))
                return;
        }
        arr_thread = new ArrayList<String>();
        for (int i=0;i<mensajes.size();i++){
            if (mensajes.get(i).from==List_Contacts.user)
                arr_thread.add("yo: "+mensajes.get(i).mensaje);
            else
                arr_thread.add(List_Contacts.s_target+": "+mensajes.get(i).mensaje);
        }
        adapter_thread= new ArrayAdapter<String>(this,R.layout.item, arr_thread);
        final ListView list = (ListView) findViewById(R.id.listViewChat);
        list.post(new Runnable() {
            public void run() {
                list.setAdapter(adapter_thread);
                list.setSelection(arr_thread.size() - 1);
            }
        });
    }

    public void actualizarMensajes(MenuItem item){
        actualizarBD();
        actualizar();/*
        if (!thread.isAlive()){
            thread.run();
        }*/
    }
    public void actualizar(){
        mensajesCopia=mensajes;
        mensajes=List_Contacts.bd.consultar(Integer.toString(List_Contacts.target),Integer.toString(List_Contacts.user));
        if(mensajesCopia!=null && mensajes!=null){
            if(mensajesCopia.equals(mensajes))
                return;
        }
        ArrayList<String> arr = new ArrayList<String>();
        for (int i=0;i<mensajes.size();i++){
            if (mensajes.get(i).from==List_Contacts.user)
                arr.add("yo: "+mensajes.get(i).mensaje);
            else
                arr.add(List_Contacts.s_target+": "+mensajes.get(i).mensaje);
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.item, arr);
        ListView list = (ListView) findViewById(R.id.listViewChat);
        list.setAdapter(adapter);
        list.setSelection(arr.size() - 1);
    }

    public void actualizarBD(){
        myasynctask task= new myasynctask();
        task.service="messages/"+Integer.toString(List_Contacts.target)+"/"+Integer.toString(List_Contacts.user);
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
        List<mensaje> obj;
        obj= null;
        try {
            obj = mapper.readValue(resultado,mapper.getTypeFactory().constructCollectionType(List.class,mensaje.class));
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (resultado=="[]" || resultado==""){
            mensaje tmp=new mensaje();
            tmp.text="";
            obj.add(tmp);
        }
        for (int i =0;i<obj.size();i++){
            List_Contacts.bd.insertar(Integer.toString(obj.get(i).from),Integer.toString(obj.get(i).to),obj.get(i).text,obj.get(i).date);
        }
    }

    public void enviarMensaje(View view){
        EditText t=(EditText)findViewById(R.id.editText);
        String mensaje=t.getText().toString();
        t.setText("");
        sendraw task= new sendraw();
        task.to=Integer.toString(List_Contacts.target);
        task.form=Integer.toString(List_Contacts.user);
        task.text=mensaje;
        task.execute();
        List_Contacts.bd.insertar(Integer.toString(List_Contacts.user),Integer.toString(List_Contacts.target),mensaje,"");
        actualizar();
    }

    public void enviarArchivo(MenuItem item){
        Intent chooseFile;
        Intent intent;
        chooseFile = new Intent(Intent.ACTION_GET_CONTENT);
        chooseFile.setType("*/*");
        intent = Intent.createChooser(chooseFile, "Choose a file");
        startActivityForResult(intent, ACTIVITY_CHOOSE_FILE);
    }
    public String getRealPathFromURI(Uri uri) {
        String[] projection = { MediaStore.Images.Media.DATA };
        @SuppressWarnings("deprecation")
        Cursor cursor = managedQuery(uri, projection, null, null, null);
        int column_index = cursor
                .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch(requestCode) {
            case ACTIVITY_CHOOSE_FILE: {
                if (resultCode == RESULT_OK){
                    Uri uri = data.getData();
                    filePath = uri.getPath();
                    sendata sda=new sendata();
                    sda.f="/root/storage/sdcard/"+filePath.split("14FB\\-1D0D\\:")[1];
                    sda.execute();
                }
                break;
            }
            case TAKE_PHOTO_CODE: {
                if (resultCode == RESULT_OK){
                    sendata sda=new sendata();
                    sda.f=dir;
                    sda.execute();
                }
                break;
            }
        }
    }

    public void abrirCamara(MenuItem item) throws InterruptedException {
        SimpleDateFormat fechaSimple = new SimpleDateFormat("yyyyMMdd_HHmmss");
        String currentDateandTime = fechaSimple.format(new Date());
        dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)+"/"+currentDateandTime+".jpg";
        File nuevoArchivo = new File(dir);
        Uri outputFileUri = Uri.fromFile(nuevoArchivo);
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);
        //thread.interrupt();
        /*
        thread.running=false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        startActivityForResult(cameraIntent, TAKE_PHOTO_CODE);
    }

    public void abrirDescargas(MenuItem item){
        Intent intent = new Intent(this,view_files.class);
        //thread.interrupt();
        /*
        thread.running=false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_chat, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
