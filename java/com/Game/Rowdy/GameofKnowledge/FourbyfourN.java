package com.Game.Rowdy.GameofKnowledge;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.app.Activity;
import android.os.SystemClock;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class FourbyfourN extends Activity implements View.OnClickListener {

    Button b[]=new Button[16];
    int s;
    int i,j,k;
    String tr;
    static int next=0;
    Chronometer chronometer;
    MediaPlayer mp,mp1;
    long timestop;
    int a[][]={{15,12,3,7,9,14,8,10,6,1,11,4,13,5,2},{5,14,3,15,1,11,13,10,6,2,12,8,4,7,9},{11,2,1,15,5,14,3,13,4,8,12,10,7,6,9},
            {2,1,15,3,11,5,14,13,8,12,10,9,4,7,6},{11,2,15,3,8,1,14,13,4,10,9,6,12,5,7},{1,15,13,3,11,14,8,6,4,2,12,7,10,5,9},
            {11,1,13,8,2,15,3,14,12,6,5,9,4,10,7},{11,1,8,14,4,12,3,15,13,6,9,2,5,10,7},{12,8,4,3,15,10,7,2,14,6,11,1,13,9,5}};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_fourbyfour_n);
        b[0]=(Button)findViewById(R.id.Fxf1);
        b[1]=(Button)findViewById(R.id.Fxf2);
        b[2]=(Button)findViewById(R.id.Fxf3);
        b[3]=(Button)findViewById(R.id.Fxf4);
        b[4]=(Button)findViewById(R.id.Fxf5);
        b[5]=(Button)findViewById(R.id.Fxf6);
        b[6]=(Button)findViewById(R.id.Fxf7);
        b[7]=(Button)findViewById(R.id.Fxf8);
        b[8]=(Button)findViewById(R.id.Fxf9);
        b[9]=(Button)findViewById(R.id.Fxf10);
        b[10]=(Button)findViewById(R.id.Fxf11);
        b[11]=(Button)findViewById(R.id.Fxf12);
        b[12]=(Button)findViewById(R.id.Fxf13);
        b[13]=(Button)findViewById(R.id.Fxf14);
        b[14]=(Button)findViewById(R.id.Fxf15);
        b[15]=(Button)findViewById(R.id.Fxf16);
        chronometer=(Chronometer) findViewById(R.id.chronometer);
        mp1 = MediaPlayer.create(getApplicationContext(), R.raw.beep);
        mp = MediaPlayer.create(getApplicationContext(), R.raw.push);
        for(i=0;i<16;i++)
            b[i].setOnClickListener(this);
        Random r=new Random();
        k= r.nextInt(9);
        for(i=0;i<15;i++)
        {
                b[i].setText(""+a[k][i]);
        }
        chronometer.start();
    }

    @Override
    public void onClick(View v) 
    {
        try {
            for(s=0;s<16;s++)
            {
                if(b[s].getText().toString().equals(""))
                    break;
            }
            for (i = 0; i < 16; i++) {
                if (v == b[i]) {
                    j = Integer.parseInt(b[i].getText().toString());
                    if (s == (i + 1) && s != 0 && s != 4 && s != 8 && s!=12) {
                        choice(i, j, s);
                        break;
                    } else if (s == (i - 1) && s != 3 && s != 7 && s != 11 && s!=15) {
                        choice(i, j, s);
                        break;
                    } else if (i == (s + 4)) {
                        choice(i, j, s);
                        break;
                    } else if (i == (s - 4)) {
                        choice(i, j, s);
                        break;
                    } else {
                        if(Settings.onoff==1)
                            mp1.start();
                    }
                }
            }

            for (i=0;i<15;i++)
            {
                j=Integer.parseInt(b[i].getText().toString());
                tr+=j;
            }
            if (tr.equals("159132610143711154812"))
            {
                for (int p = 0; p < 16; p++) {
                    b[p].setText("");
                    b[p].setBackgroundColor(Color.parseColor("#00000000"));
                }
                chronometer.stop();
                next=3;
                long time = SystemClock.elapsedRealtime() - chronometer.getBase();
                time=time*100+42;
                startActivity(new Intent(FourbyfourN.this, Questions.class).putExtra("TIME", time));
                finish();
            }
        }

        catch(Exception e)
        {
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
        //cs3.setText(sp.getString("n4", " - "));
        timestop=0;
        timestop=chronometer.getBase() - SystemClock.elapsedRealtime(); chronometer.stop();

        // Create custom dialog object
        final Dialog dialog = new Dialog(FourbyfourN.this,android.R.style.Theme_DeviceDefault_Light_NoActionBar_TranslucentDecor);
        // Include dialog.xml file
        dialog.setContentView(R.layout.pauselog);
        // Set dialog title Theme_Material_Light_NoActionBar_TranslucentDecor
        dialog.setTitle("Custom Dialog");
        dialog.setCancelable(false);

        // set values for custom dialog components - text, image and button
        TextView time = (TextView) dialog.findViewById(R.id.st);
        time.setText(sp.getString("n4", " - "));
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
        final Dialog dialog = new Dialog(FourbyfourN.this,android.R.style.Theme_DeviceDefault_Light_NoActionBar_TranslucentDecor);
        // Include dialog.xml file
        dialog.setContentView(R.layout.helplog);
        // Set dialog title Theme_Material_Light_NoActionBar_TranslucentDecor
        dialog.setTitle("Custom Dialog");
        dialog.setCancelable(true);

        // set values for custom dialog components - text, image and button
        ImageView imv=(ImageView) dialog.findViewById(R.id.imv);
        imv.setImageResource(R.drawable.fourns);
        TextView how = (TextView) dialog.findViewById(R.id.howto);
        TextView info = (TextView) dialog.findViewById(R.id.info);
        info.setText("4X4- Numbers level \n Align The Numbers Vertically\n In Ascending  Order");
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
    public void choice(int i1,int j1,int s1)
    {
        if(Settings.onoff==1)
            mp.start();
        if(s<4||s>7&&s<12)
        {   b[s1].setText(""+j1);
            if(s1%2==0)
            {
                b[s1].setBackgroundColor(Color.parseColor("#86f770"));  //light green
            }
            else
            {
                b[s1].setBackgroundColor(Color.parseColor("#caa4f9"));  //light purple
            }
        }
        else
        {
            b[s1].setText(""+j1);
            if(s1%2==0)
            {
                b[s1].setBackgroundColor(Color.parseColor("#caa4f9"));  //light purple
            }
            else
            {
                b[s1].setBackgroundColor(Color.parseColor("#86f770"));  //light green
            }
        }
            b[i1].setText("");
            b[i1].setBackgroundColor(Color.parseColor("#00000000"));
            tr="";
    }
    public void onBackPressed()
    {
        super.onBackPressed();
        startActivity(new Intent(FourbyfourN.this, Levels1.class));
        finish();
    }

}
