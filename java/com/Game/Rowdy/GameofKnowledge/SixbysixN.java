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

public class SixbysixN extends Activity implements View.OnClickListener {
    
    Button b[]=new Button[36];
    int s;
    int i,j,k;
    String tr;
    static int next=0;
    Chronometer chronometer;
    MediaPlayer mp,mp1;
    long timestop;
    int a[][]={{35,25,15,5,1,6,11,17,23,29,34,30,26,21,16,12,7,2,8,13,18,24,28,33,3,9,14,22,32,19,20,27,4,31,10},
            {34,8,21,29,17,10,20,33,32,19,14,25,30,22,11,2,12,24,16,3,13,1,31,26,9,35,7,27,23,5,15,28,4,18,6},
            {33,20,8,21,19,10,16,22,32,17,29,25,13,9,2,31,14,24,30,15,11,12,1,26,34,3,7,27,5,6,35,28,4,18,23},
            {32,13,20,9,21,25,22,8,31,2,10,19,30,16,7,33,29,17,15,27,18,24,14,26,12,35,28,6,11,34,3,5,4,23,1},
            {30,20,31,10,25,19,16,13,9,21,32,14,7,22,8,2,33,29,15,27,28,11,17,26,12,35,24,18,6,34,3,5,4,23,1},
            {31,13,9,20,32,14,7,16,8,19,10,25,3,15,27,21,33,29,5,22,2,17,28,26,12,6,4,11,23,34,1,18,24,30,35}};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_sixbysix_n);
        b[0]=(Button)findViewById(R.id.Sxs1);
        b[1]=(Button)findViewById(R.id.Sxs2);
        b[2]=(Button)findViewById(R.id.Sxs3);
        b[3]=(Button)findViewById(R.id.Sxs4);
        b[4]=(Button)findViewById(R.id.Sxs5);
        b[5]=(Button)findViewById(R.id.Sxs6);
        b[6]=(Button)findViewById(R.id.Sxs7);
        b[7]=(Button)findViewById(R.id.Sxs8);
        b[8]=(Button)findViewById(R.id.Sxs9);
        b[9]=(Button)findViewById(R.id.Sxs10);
        b[10]=(Button)findViewById(R.id.Sxs11);
        b[11]=(Button)findViewById(R.id.Sxs12);
        b[12]=(Button)findViewById(R.id.Sxs13);
        b[13]=(Button)findViewById(R.id.Sxs14);
        b[14]=(Button)findViewById(R.id.Sxs15);
        b[15]=(Button)findViewById(R.id.Sxs16);
        b[16]=(Button)findViewById(R.id.Sxs17);
        b[17]=(Button)findViewById(R.id.Sxs18);
        b[18]=(Button)findViewById(R.id.Sxs19);
        b[19]=(Button)findViewById(R.id.Sxs20);
        b[20]=(Button)findViewById(R.id.Sxs21);
        b[21]=(Button)findViewById(R.id.Sxs22);
        b[22]=(Button)findViewById(R.id.Sxs23);
        b[23]=(Button)findViewById(R.id.Sxs24);
        b[24]=(Button)findViewById(R.id.Sxs25);
        b[25]=(Button)findViewById(R.id.Sxs26);
        b[26]=(Button)findViewById(R.id.Sxs27);
        b[27]=(Button)findViewById(R.id.Sxs28);
        b[28]=(Button)findViewById(R.id.Sxs29);
        b[29]=(Button)findViewById(R.id.Sxs30);
        b[30]=(Button)findViewById(R.id.Sxs31);
        b[31]=(Button)findViewById(R.id.Sxs32);
        b[32]=(Button)findViewById(R.id.Sxs33);
        b[33]=(Button)findViewById(R.id.Sxs34);
        b[34]=(Button)findViewById(R.id.Sxs35);
        b[35]=(Button)findViewById(R.id.Sxs36);
        chronometer=(Chronometer) findViewById(R.id.chronometer);
        mp1 = MediaPlayer.create(getApplicationContext(), R.raw.beep);
        mp = MediaPlayer.create(getApplicationContext(), R.raw.push);
        try {
            for (i = 0; i < 36; i++)
                b[i].setOnClickListener(this);
            Random r = new Random();
            k = r.nextInt(6);
            for (i = 0; i < 35; i++)
            {
                    b[i].setText("" + a[k][i]);
            }
            chronometer.start();
        }
        catch (Exception e){}
    }

    @Override
    public void onClick(View v) {
        try {
            for(s=0;s<36;s++)
            {
                if(b[s].getText().toString().equals(""))
                    break;
            }
            for (i = 0; i < 36; i++) {
                if (v == b[i]) {
                    j = Integer.parseInt(b[i].getText().toString());
                    if (s == (i + 1) && s != 0 && s != 6 && s != 12 && s!=18&&s!=24&&s!=30) {
                        choice(i, j, s);
                        break;
                    } else if (s == (i - 1) && s != 5 && s != 11 && s != 17 && s!=23&&s!=29) {
                        choice(i, j, s);
                        break;
                    } else if (i == (s + 6)) {
                        choice(i, j, s);
                        break;
                    } else if (i == (s - 6)) {
                        choice(i, j, s);
                        break;
                    } else {
                        if(Settings.onoff==1)
                            mp1.start();
                    }
                }
            }

            for (i=0;i<35; i++)
            {
                j=Integer.parseInt(b[i].getText().toString());
                tr+=j;
            }
            if (tr.equals("1713192531281420263239152127334101622283451117232935612182430"))
            {
                for (int p = 0; p < 36; p++) {
                    b[p].setText("");
                    b[p].setBackgroundColor(Color.parseColor("#00000000"));
                }
                chronometer.stop();
                next=3;
                long time = SystemClock.elapsedRealtime() - chronometer.getBase();
                time=time*100+62;
                startActivity(new Intent(SixbysixN.this, Questions.class).putExtra("TIME", time));
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
        //cs3.setText(sp.getString("n6", " - "));
        timestop=0;
        timestop=chronometer.getBase() - SystemClock.elapsedRealtime(); chronometer.stop();

        // Create custom dialog object
        final Dialog dialog = new Dialog(SixbysixN.this,android.R.style.Theme_DeviceDefault_Light_NoActionBar_TranslucentDecor);
        // Include dialog.xml file
        dialog.setContentView(R.layout.pauselog);
        // Set dialog title Theme_Material_Light_NoActionBar_TranslucentDecor
        dialog.setTitle("Custom Dialog");
        dialog.setCancelable(false);

        // set values for custom dialog components - text, image and button
        TextView time = (TextView) dialog.findViewById(R.id.st);
        time.setText(sp.getString("n6", " - "));
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
        final Dialog dialog = new Dialog(SixbysixN.this,android.R.style.Theme_DeviceDefault_Light_NoActionBar_TranslucentDecor);
        // Include dialog.xml file
        dialog.setContentView(R.layout.helplog);
        // Set dialog title Theme_Material_Light_NoActionBar_TranslucentDecor
        dialog.setTitle("Custom Dialog");
        dialog.setCancelable(true);

        // set values for custom dialog components - text, image and button
        ImageView imv=(ImageView) dialog.findViewById(R.id.imv);
        imv.setImageResource(R.drawable.sixns);
        TextView how = (TextView) dialog.findViewById(R.id.howto);
        TextView info = (TextView) dialog.findViewById(R.id.info);
        info.setText("6X6- Numbers level \n Align The Numbers Vertically\n In Ascending  Order");
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

        if(s<6||s>11&&s<18||s>23&&s<30)
        {   b[s1].setText(""+j1);
            if(s1%2==0)
            {
                b[s1].setBackgroundColor(Color.BLACK);
                b[s1].setTextColor(Color.WHITE);
            }
            else
            {
                b[s1].setBackgroundColor(Color.WHITE);
                b[s1].setTextColor(Color.BLACK);
            }
        }
        else
        {
            b[s1].setText(""+j1);
            if(s1%2==0)
            {
                b[s1].setBackgroundColor(Color.WHITE);
                b[s1].setTextColor(Color.BLACK);
            }
            else
            {
                b[s1].setBackgroundColor(Color.BLACK);
                b[s1].setTextColor(Color.WHITE);
            }
        }
            b[i1].setText("");
            b[i1].setBackgroundColor(Color.parseColor("#00000000"));

        tr="";
    }
    public void onBackPressed()
    {
        super.onBackPressed();
        startActivity(new Intent(SixbysixN.this, Levels3.class));
        finish();
    }
}
