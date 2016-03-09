package com.example.android.concentration;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.text.Layout;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class name extends Activity {
    public static ArrayList<String> names = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name);
        LinearLayout linear=(LinearLayout) findViewById(R.id.layname);
        for(int i =0; i<MainActivity.players;i++){
            EditText tx=new EditText(this);
            TextView tv=new TextView(this);
            tv.setText(getResources().getString(R.string.NombreT) + Integer.toString(i + 1));
            tv.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            tx.setHint(getResources().getString(R.string.NombreH));
            tx.setTag("name" + Integer.toString(i));
            tx.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            tx.setId(i);
            linear.addView(tv);
            linear.addView(tx);
        }
    }

    public void go(View view){
        names.clear();
        for (int i =0; i<MainActivity.players;i++){
            EditText tx=(EditText)findViewById(i);
            String tmp;
            tmp=tx.getText().toString();
            if (tmp=="" || tmp==" " || tmp.isEmpty() || tmp==null){
                Toast.makeText(getApplicationContext(), getResources().getString(R.string.exepcionN),
                        Toast.LENGTH_LONG).show();
                names.clear();
                return;
            }
            names.add(tmp);
        }
        Button button = (Button) view;
        Intent intent = new Intent(this,game.class);
        startActivity(intent);
    }

    public void Newm(MenuItem item) {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
    public void restartm(MenuItem item) {
        Intent intent = new Intent(this,game.class);
        startActivity(intent);
    }

    public void about_mm(MenuItem item) {
        Intent intent = new Intent(this,about_me.class);
        startActivity(intent);
    }

    public void exitm(MenuItem item){
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

}
