package com.example.android.message;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.android.message.modelo.contacts;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class List_Contacts extends Activity {
    public static String url="http://192.168.250.35:8191/rest/";
    //public static String url="http://172.17.23.229:8191/rest/";
    public static int user=1;
    static public int target=0;
    static public String s_target="";
    static public List<contacts> obj;
    static public BDAyuda bd;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list__contacts);
        setTitle("Contactos");
        bd=new BDAyuda(this);
        bd.abrir();
        actualizar();
    }
    public void cambiar(MenuItem item){
        Intent intent = new Intent(this,Change.class);
        startActivity(intent);
    }
    public void actualizarContactos(MenuItem item){
        actualizar();
    }
    public void actualizar(){
        myasynctask task=new myasynctask();
        task.service="contacts/"+Integer.toString(user);
        String resultado="";
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
            obj = mapper.readValue(resultado,mapper.getTypeFactory().constructCollectionType(List.class,contacts.class));
        } catch (IOException e) {
            e.printStackTrace();
        }
        ArrayList<String> contactos = new ArrayList<String>();
        for (int i =0;i<obj.size();i++){
            contactos.add(obj.get(i).userName);
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.myitem, contactos);
        ListView listaContactos = (ListView) findViewById(R.id.list);
        listaContactos.setAdapter(adapter);
    }

    public void IniciarConversacion(View view){
        TextView contacto = (TextView) view;
        s_target=(String)contacto.getText();
        for (int i=0;i<obj.size();i++){
            if (s_target.equals(obj.get(i).userName)){
                target=obj.get(i).userId;
                break;
            }
        }
        Intent intent = new Intent(this,Chat.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_list__contacts, menu);
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
