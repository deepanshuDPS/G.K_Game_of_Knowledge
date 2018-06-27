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

public class FourbyfourA extends Activity implements View.OnClickListener{

    Button b[]=new Button[16];
    int s;
    int i,k;
    char j;
    String tr;
    TextView t;
    static int next=0;
    Chronometer chronometer;
    MediaPlayer mp,mp1;
    long timestop;
    char a[][]={{'O','L','C','G','I','N','H','J','F','A','K','D','M','E','B'},{'E','N','C','O','A','K','M','J','F','B','L','H','D','G','I'},
            {'K','B','A','O','E','N','C','M','D','H','L','J','G','F','I'},{'B','A','O','C','K','E','N','M','H','L','J','I','D','G','F'},
            {'K','B','O','C','H','A','N','M','D','J','I','F','L','E','G'},
            {'A','O','M','C','K','N','H','F','D','B','L','G','J','E','I'},{'K','A','M','H','B','O','C','N','L','F','E','I','D','J','G'},
            {'K','A','H','N','D','L','C','O','M','F','B','I','E','J','G'},
            {'L','H','D','C','O','J','G','B','N','F','K','A','M','I','E'}};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_fourbyfour);
        b[0]=(Button)findViewById(R.id.fxF1);
        b[1]=(Button)findViewById(R.id.fxF2);
        b[2]=(Button)findViewById(R.id.fxF3);
        b[3]=(Button)findViewById(R.id.fxF4);
        b[4]=(Button)findViewById(R.id.fxF5);
        b[5]=(Button)findViewById(R.id.fxF6);
        b[6]=(Button)findViewById(R.id.fxF7);
        b[7]=(Button)findViewById(R.id.fxF8);
        b[8]=(Button)findViewById(R.id.fxF9);
        b[9]=(Button)findViewById(R.id.fxF10);
        b[10]=(Button)findViewById(R.id.fxF11);
        b[11]=(Button)findViewById(R.id.fxF12);
        b[12]=(Button)findViewById(R.id.fxF13);
        b[13]=(Button)findViewById(R.id.fxF14);
        b[14]=(Button)findViewById(R.id.fxF15);
        b[15]=(Button)findViewById(R.id.fxF16);
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
    public void onClick(View v) {
        try {
            for(s=0;s<16;s++)
            {
                if(b[s].getText().toString().equals(""))
                    break;
            }
            for (i = 0; i < 16; i++) {
                if (v == b[i]) {
                    String t = b[i].getText().toString();
                    j = t.charAt(0);
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
                String t= b[i].getText().toString();
                tr+=t;
            }
            if (tr.equals("AEIMBFJNCGKODHL"))
            {
                for (int p = 0; p < 16; p++) {
                    b[p].setText("");
                    b[p].setBackgroundColor(Color.parseColor("#00000000"));
                }
                chronometer.stop();
                long time = SystemClock.elapsedRealtime() - chronometer.getBase();
                time=time*100+43;
                startActivity(new Intent(FourbyfourA.this, Questions.class).putExtra("TIME", time));
                finish();
            }
        }

        catch(Exception e) {}
    }
    public void restart(View v)
    {
        onCreate(null);
    }
    public void pause(View v)
    {
        SharedPreferences sp = getSharedPreferences("key", 0);
        //smarks.setText(sp.getString("marks", "0"));
        //cs3.setText(sp.getString("a4", " - "));
        timestop=0;
        timestop=chronometer.getBase() - SystemClock.elapsedRealtime(); chronometer.stop();

        // Create custom dialog object
        final Dialog dialog = new Dialog(FourbyfourA.this,android.R.style.Theme_DeviceDefault_Light_NoActionBar_TranslucentDecor);
        // Include dialog.xml file
        dialog.setContentView(R.layout.pauselog);
        // Set dialog title Theme_Material_Light_NoActionBar_TranslucentDecor
        dialog.setTitle("Custom Dialog");
        dialog.setCancelable(false);

        // set values for custom dialog components - text, image and button
        TextView time = (TextView) dialog.findViewById(R.id.st);
        time.setText(sp.getString("a4", " - "));
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
        final Dialog dialog = new Dialog(FourbyfourA.this,android.R.style.Theme_DeviceDefault_Light_NoActionBar_TranslucentDecor);
        // Include dialog.xml file
        dialog.setContentView(R.layout.helplog);
        // Set dialog title Theme_Material_Light_NoActionBar_TranslucentDecor
        dialog.setTitle("Custom Dialog");
        dialog.setCancelable(true);

        // set values for custom dialog components - text, image and button
        ImageView imv=(ImageView) dialog.findViewById(R.id.imv);
        imv.setImageResource(R.drawable.fouras);
        TextView how = (TextView) dialog.findViewById(R.id.howto);
        TextView info = (TextView) dialog.findViewById(R.id.info);
        info.setText("4X4- Alphabets level \n Align The Alphabets \nVertically In Ascending  Order");
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
    public void choice(int i1,char j1,int s1)
    {
        if(Settings.onoff==1)
            mp.start();
        if(s<4||s>7&&s<12)
        {   b[s1].setText(""+j1);
            if(s1%2==0)
            {
                b[s1].setBackgroundColor(Color.parseColor("#caa4f9"));  //light purple
            }
            else
            {
                b[s1].setBackgroundColor(Color.parseColor("#86f770"));  //light green
            }
        }
        else
        {
            b[s1].setText(""+j1);
            if(s1%2==0)
            {
                b[s1].setBackgroundColor(Color.parseColor("#86f770"));  //light green
            }
            else
            {
                b[s1].setBackgroundColor(Color.parseColor("#caa4f9"));  //light purple
            }
        }
        b[i1].setText("");
        b[i1].setBackgroundColor(Color.parseColor("#00000000"));
        tr="";
    }
    public void onBackPressed()
    {
        super.onBackPressed();
        startActivity(new Intent(FourbyfourA.this, Levels1.class));
        finish();
    }

}
