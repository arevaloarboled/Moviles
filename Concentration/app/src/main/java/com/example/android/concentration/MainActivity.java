package com.example.android.concentration;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class MainActivity extends Activity {
    public  static  int players=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
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


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }
    public void players(View view){
        Button button = (Button) view;
        String tag = button.getTag().toString();
        players=Integer.parseInt(tag);
        Intent intent = new Intent(this,name.class);
        startActivity(intent);
    }
}
