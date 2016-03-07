package com.example.windows.concentration;

import android.app.ActionBar;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class game extends AppCompatActivity {
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
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        //star_game();
        createboard();
    }

    private void createboard(){
        tipe_colors.clear();
        ArrayList<Integer> tipe= new ArrayList<Integer>(Arrays.asList(0,0,1,1,2,2,3,3,4,4,5,5,6,6,7,7));
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
        for (int i=0;i<MainActivity.players;i++){
            score.add(0);
        }
        turno=0;
    }

    public void play(View view) {
        Button button = (Button) view;
        int tag = Integer.parseInt(button.getTag().toString());
        if(!ok.get(tag-1)){
            if (state==0){
                state=1;
                button.setBackgroundColor(tipe_colors.get(tag));
                tmptag=tag;
            }
            else {
                state=0;
                if (tipe_colors.get(tmptag-1)==tipe_colors.get(tag-1)){
                    score.set(turno % MainActivity.players, score.get(turno % MainActivity.players) + 1);
                    Button b=(Button)findViewById(getResources().getIdentifier("boton"+Integer.toString(tmptag),"id",getPackageName()));
                    button.setBackgroundColor(tipe_colors.get(tag - 1));
                    //Thread.sleep(1000);
                    b.setBackgroundColor(Color.WHITE);
                    button.setBackgroundColor(Color.WHITE);
                    ok.set(tmptag - 1, true);
                    ok.set(tag-1,true);
                }
                else {
                    button.setBackgroundColor(tipe_colors.get(tag - 1));
                    //Thread.sleep(1000);
                    button.setBackgroundColor(Color.GRAY);
                    Button b=(Button)findViewById(getResources().getIdentifier("boton"+Integer.toString(tmptag),"id",getPackageName()));
                    b.setBackgroundColor(Color.GRAY);
                    turno++;
                }
            }
        }
    }

    private void star_game(){
        int n=size.size;
        int m=MainActivity.players+1;
        for (int i = 0; i < n*m; i++) {
            LinearLayout linear=new LinearLayout(this);
            linear.setOrientation(LinearLayout.HORIZONTAL);
            linear.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            linear.setGravity(Gravity.CENTER);
            for (int j = 0; i< m; i++){
                Button b = new Button(this);
                b.setText("     ");
                b.setId(i);
                b.setTag(Integer.toString(n) + "," + Integer.toString(n));
                b.setWidth(0);
                b.setHeight(0);
                final int id_ = b.getId();

                //LinearLayout layout = (LinearLayout) findViewById(R.id.lay);
                linear.addView(b);

//            myButton.setOnClickListener(new View.OnClickListener() {
//                public void onClick(View view) {
//                    Toast.makeText(DynamicLayout.this,
//                            "Button clicked index = " + id_, Toast.LENGTH_SHORT)
//                            .show();
//                }
//            });
            }
            LinearLayout layout = (LinearLayout) findViewById(R.id.lay);
            layout.addView(linear);
        };
    }
}
