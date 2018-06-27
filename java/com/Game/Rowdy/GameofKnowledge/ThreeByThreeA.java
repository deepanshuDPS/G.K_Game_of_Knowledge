package com.Game.Rowdy.GameofKnowledge;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class ThreeByThreeA extends Activity implements View.OnClickListener{

    Button b[]=new Button[9];
    int s;
    int i;
    char j;
    String count="h";
    TextView t1;
    Chronometer chronometer;
    MediaPlayer mp,mp1;
    long timestop;
    char a[][]={{'H','D','C','F','B','G','A','E'},{'A','D','H','C','F','E','G','B'},{'G','B','H','A','F','E','D','C'},
            {'H','E','B','F','C','A','D','G'},{'E','B','F','C','G','A','H','D'},{'A','B','E','G','C','F','D','H'},{'H','F','A','G','C','D','B','E'}};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_three_by_three);
        b[0]=(Button)findViewById(R.id.txT1);
        b[1]=(Button)findViewById(R.id.txT2);
        b[2]=(Button)findViewById(R.id.txT3);
        b[3]=(Button)findViewById(R.id.txT4);
        b[4]=(Button)findViewById(R.id.txT5);
        b[5]=(Button)findViewById(R.id.txT6);
        b[6]=(Button)findViewById(R.id.txT7);
        b[7]=(Button)findViewById(R.id.txT8);
        b[8]=(Button)findViewById(R.id.txT9);
        t1=(TextView) findViewById(R.id.text);
        chronometer=(Chronometer) findViewById(R.id.chronometer);
        mp1 = MediaPlayer.create(getApplicationContext(), R.raw.beep);
        mp = MediaPlayer.create(getApplicationContext(), R.raw.push);
        for(i=0;i<9;i++)
            b[i].setOnClickListener(this);
        Random r=new Random();
        int k=r.nextInt(7);
        for(i=0;i<8;i++)
        {
            b[i].setText(""+a[k][i]);
        }
        chronometer.start();
    }
    public void onClick(View v)
    {

        try {
            for(s=0;s<9;s++)
            {
                if(b[s].getText().toString().equals(""))
                    break;
            }
            for (i = 0; i < 9; i++) {
                if (v == b[i]) {
                    String t = b[i].getText().toString();
                    j = t.charAt(0);
                    if (s == (i + 1) && s != 0 && s != 3 && s != 6) {
                        choice(i,j, s);
                        break;
                    } else if (s == (i - 1) && s != 2 && s != 5 && s != 8) {
                        choice(i, j, s);
                        break;
                    } else if (i == (s + 3)) {
                        choice(i, j, s);
                        break;
                    } else if (i == (s - 3)) {
                        choice(i, j, s);
                        break;
                    } else {
                        if(Settings.onoff==1)
                            mp1.start();
                    }
                }
            }
            for (i=0;i<8; i++)
            {
                String t= b[i].getText().toString();
               count+=t;
            }
            t1.setText("");
            if (count.equals("ABCDEFGH"))
            {
                for (int p = 0; p < 9; p++) {
                    b[p].setText("");
                    b[p].setBackgroundColor(Color.parseColor("#00000000"));
                }
                chronometer.stop();
                //next=2;
                long time = SystemClock.elapsedRealtime() - chronometer.getBase();
                time=time*100+33;
                startActivity(new Intent(ThreeByThreeA.this, Questions.class).putExtra("TIME", time));
                finish();
            }
        }
        catch(Exception e) {
        }
    }
    public void restart(View v)
    {
        onCreate(null);
    }
    public void pause(View v)
    {
        SharedPreferences sp = getSharedPreferences("key", 0);
        //smarks.setText(sp.getString("marks", "0"));
        //cs3.setText(sp.getString("a3", " - "));
        timestop=0;
        timestop=chronometer.getBase() - SystemClock.elapsedRealtime(); chronometer.stop();

        // Create custom dialog object
        final Dialog dialog = new Dialog(ThreeByThreeA.this,android.R.style.Theme_DeviceDefault_Light_NoActionBar_TranslucentDecor);
        // Include dialog.xml file
        dialog.setContentView(R.layout.pauselog);
        // Set dialog title Theme_Material_Light_NoActionBar_TranslucentDecor
        dialog.setTitle("Custom Dialog");
        dialog.setCancelable(false);

        // set values for custom dialog components - text, image and button
        TextView time = (TextView) dialog.findViewById(R.id.st);
        time.setText(sp.getString("a3", " - "));
        TextView marks = (TextView) dialog.findViewById(R.id.markss);
        marks.setText(sp.getString("marks", "0"));

        Button restartButton = (Button) dialog.findViewById(R.id.restart);
        restartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
                onCreate(null);
            }

        });
        Button resumeButton = (Button) dialog.findViewById(R.id.resume);
        resumeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //for timestop
                dialog.cancel();
                chronometer.setBase(SystemClock.elapsedRealtime() + timestop); chronometer.start();
            }

        });
        dialog.show();
    }
    public void help(View v)
    {
        // Create custom dialog object
        final Dialog dialog = new Dialog(ThreeByThreeA.this,android.R.style.Theme_DeviceDefault_Light_NoActionBar_TranslucentDecor);
        // Include dialog.xml file
        dialog.setContentView(R.layout.helplog);
        // Set dialog title Theme_Material_Light_NoActionBar_TranslucentDecor
        dialog.setTitle("Custom Dialog");
        dialog.setCancelable(true);

        // set values for custom dialog components - text, image and button
        ImageView imv=(ImageView) dialog.findViewById(R.id.imv);
        imv.setImageResource(R.drawable.threeas);
        TextView how = (TextView) dialog.findViewById(R.id.howto);
        TextView info = (TextView) dialog.findViewById(R.id.info);
        info.setText("3X3- Alphabets level \n Align The Alphabets \nHorizontally In Ascending Order");
        Button restartButton = (Button) dialog.findViewById(R.id.rest);
        restartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
                onCreate(null);// for restart the game
            }

        });
        Button contButton = (Button) dialog.findViewById(R.id.cont);
        contButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();// for continue the game
            }

        });
        dialog.show();
    }
    public void choice(int i1, char j1, int s1)
    {
        if(Settings.onoff==1)
        mp.start();

        if(s1%2==1)
        {
            b[s1].setBackgroundColor(Color.parseColor("#d3d3d3"));  //Gray
        }
        else
        {
            b[s1].setBackgroundColor(Color.parseColor("#6eebf7"));  //sky blue
        }
        b[s1].setText(""+j1);
        b[i1].setText("");
        b[i1].setBackgroundColor(Color.parseColor("#00000000"));
        t1.setText("");
        count="";
    }

    public void onBackPressed()
    {
        super.onBackPressed();
        startActivity(new Intent(ThreeByThreeA.this, Levels.class));
        finish();
    }

}
