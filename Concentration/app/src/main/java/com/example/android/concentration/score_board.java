package com.example.android.concentration;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;


public class score_board extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_board);
        LinearLayout linear=(LinearLayout) findViewById(R.id.layscore);
        int m=-1;
        boolean draw=false;
        for (int i=0;i<MainActivity.players;i++){
            TextView tx= new TextView(this);
            tx.setText(name.names.get(i) + " " + Integer.toString(game.score.get(i)));
            tx.setTextSize(10 + (5 * (int) game.score.get(i)));
            tx.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            tx.setId(i);
            linear.addView(tx);
            if (game.score.get(i)==m){
                draw=true;
            }
            if (game.score.get(i)>m){
                m=game.score.get(i);
            }
        }

        if (MainActivity.players>1){
            if (draw){
                TextView tx= new TextView(this);
                tx.setText(getResources().getString(R.string.empate));
                tx.setTextSize(30);
                tx.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                tx.setId(MainActivity.players);
                linear.addView(tx);
            }
            else {
                TextView tx= new TextView(this);
                tx.setText(getResources().getString(R.string.ganador));
                tx.setTextSize(20);
                tx.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                tx.setId(MainActivity.players+1);
                linear.addView(tx);
                TextView tv= new TextView(this);
                tv.setText(name.names.get(game.score.indexOf(m)));
                tv.setTextSize(30);
                tv.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                tv.setId(MainActivity.players);
                linear.addView(tv);
            }
        }
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

    public void New(View view){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }

    public void restart(View view){
        Intent intent = new Intent(this,game.class);
        startActivity(intent);
    }
    public void about_m(View view){
        Intent intent = new Intent(this,about_me.class);
        startActivity(intent);
    }

    public void exit(View view){
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_score_board, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }
}
