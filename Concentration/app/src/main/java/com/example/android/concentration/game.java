package com.example.android.concentration;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;


public class game extends Activity {
    public static ArrayList<Integer> score = new ArrayList<Integer>();
    public static int[] colors={ Color.RED ,Color.BLACK, Color.BLUE, Color.YELLOW,Color.CYAN,Color.GREEN,Color.MAGENTA,Color.DKGRAY};
    public static ArrayList<Integer> tipe_colors = new ArrayList<Integer>();
    public static  int state=0;
    public static int turno=0;
    public static int tmptag=0;
    public static ArrayList<Boolean> ok = new ArrayList<Boolean>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        createboard();
    }

    private void createboard(){
        turno=0;
        tipe_colors.clear();
        ArrayList<Integer> tipe= new ArrayList<Integer>(Arrays.asList(0, 0, 1, 1, 2, 2, 3, 3, 4, 4, 5, 5, 6, 6, 7, 7));
        //tipe= (ArrayList<Integer>) Arrays.asList(0,0,1,1,2,2,3,3,4,4,5,5,6,6,7,7);
        //ok= (ArrayList<Boolean>) Arrays.asList(false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false);
        ok.clear();
        for (int i=0;i<16;i++){
            ok.add(false);
        }
        Random rnd= new Random();
        int c=1;
        while (tipe.size()>0){
            int tmp=0;
            if (tipe.size()!=1){
                tmp = (rnd.nextInt((tipe.size()-1)+1));
            }
            tipe_colors.add(colors[tipe.get(tmp)]);
            tipe.remove(tmp);
            Button b=(Button)findViewById(getResources().getIdentifier("boton"+Integer.toString(c),"id",getPackageName()));
            b.setBackgroundColor(Color.GRAY);
            c++;
        }
        score.clear();
        LinearLayout linear=(LinearLayout) findViewById(R.id.laygame);
        for (int i=0;i<MainActivity.players;i++){
            TextView tx= new TextView(this);
            tx.setText(name.names.get(i)+" 0");
            tx.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            tx.setId(i);
            linear.addView(tx);
            score.add(0);
        }
        TextView tx= new TextView(this);
        tx.setText(getResources().getString(R.string.turno)+Integer.toString(turno)+getResources().getString(R.string.juega)+name.names.get(0));
        tx.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        tx.setId(MainActivity.players);
        linear.addView(tx);
    }

    public void play(View view) {
        final Button button = (Button) view;
        int tag = Integer.parseInt(button.getTag().toString());
        if(!ok.get(tag-1)){
            if (state==0){
                state=1;
                button.setBackgroundColor(tipe_colors.get(tag-1));
                tmptag=tag;
            }
            else {
                state=0;
                Log.d("tmptag", Integer.toString(tmptag));
                Log.d("tipe_color(tmptag-1)", Integer.toString(tipe_colors.get(tmptag-1)));
                Log.d("tag", Integer.toString(tag));
                Log.d("tipe_color(tag-1)", Integer.toString(tipe_colors.get(tag-1)));
                if ((int)tipe_colors.get(tmptag-1)==(int)tipe_colors.get(tag-1)){
                    score.set(turno % MainActivity.players, score.get(turno % MainActivity.players) + 1);
                    final Button b=(Button)findViewById(getResources().getIdentifier("boton"+Integer.toString(tmptag),"id",getPackageName()));
                    button.setBackgroundColor(tipe_colors.get(tag - 1));
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            b.setBackgroundColor(Color.WHITE);
                            button.setBackgroundColor(Color.WHITE);
                            //Do nothing
                        }
                    }, 1000);
                    //Thread.sleep(1000);
                    //b.setBackgroundColor(Color.WHITE);
                    //button.setBackgroundColor(Color.WHITE);
                    ok.set(tmptag - 1, true);
                    ok.set(tag-1,true);
                    Toast.makeText(getApplicationContext(), getResources().getString(R.string.bien),
                            Toast.LENGTH_LONG).show();
                    TextView tx=(TextView)findViewById(turno%MainActivity.players);
                    tx.setText(name.names.get(turno%MainActivity.players)+" "+Integer.toString((score.get(turno%MainActivity.players))));
                    for (int i=0;i<ok.size();i++){
                        if (ok.get(i)==false){
                            return;
                        }
                    }
                    Intent intent = new Intent(this,score_board.class);
                    startActivity(intent);
                }
                else {
                    button.setBackgroundColor(tipe_colors.get(tag - 1));
                    //Thread.sleep(1000);
                    final Button b=(Button)findViewById(getResources().getIdentifier("boton"+Integer.toString(tmptag),"id",getPackageName()));
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            //Do nothing
                            b.setBackgroundColor(Color.GRAY);
                            button.setBackgroundColor(Color.GRAY);
                        }
                    }, 1000);
                    turno++;
                    Toast.makeText(getApplicationContext(), getResources().getString(R.string.mal),
                            Toast.LENGTH_LONG).show();
                    TextView tx=(TextView)findViewById(MainActivity.players);
                    tx.setText(getResources().getString(R.string.turno)+Integer.toString(turno)+getResources().getString(R.string.juega)+name.names.get(turno%MainActivity.players));
                }
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


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_game, menu);
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
