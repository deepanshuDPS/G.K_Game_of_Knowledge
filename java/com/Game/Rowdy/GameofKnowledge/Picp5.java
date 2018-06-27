package com.Game.Rowdy.GameofKnowledge;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class Picp5 extends Activity implements View.OnClickListener{
    Button b[]=new Button[25];
    int s;
    int i,j,k;
    String tr;
    static int next=0;
    Chronometer chronometer;
    int pic[]=new int[100];
    MediaPlayer mp,mp1;
    long stepc;
    puzzpics choosepic;
    TextView steps;
    int a[][]={{5,20,12,4,24,10,15,13,2,19,23,17,7,9,8,1,6,14,3,16,18,22,11,21},{6,10,20,4,24,15,13,12,23,5,1,7,2,8,19,18,22,17,16,9,11,14,21,3},
            {7,1,20,4,24,10,15,13,12,5,6,22,2,23,9,11,18,17,19,3,14,21,8,16},{8,6,1,15,4,3,11,7,20,24,14,2,10,13,5,21,18,12,17,19,16,22,9,23},
            {9,6,10,1,4,21,3,11,15,24,14,22,2,5,8,16,13,18,7,20,17,12,23,19},{10,15,20,5,19,4,8,12,16,24,21,18,9,6,3,2,22,14,13,17,11,23,1,7}};
    @Override
    protected void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_picp5);
        b[0]=(Button)findViewById(R.id.Pfv1);
        b[1]=(Button)findViewById(R.id.Pfv2);
        b[2]=(Button)findViewById(R.id.Pfv3);
        b[3]=(Button)findViewById(R.id.Pfv4);
        b[4]=(Button)findViewById(R.id.Pfv5);
        b[5]=(Button)findViewById(R.id.Pfv6);
        b[6]=(Button)findViewById(R.id.Pfv7);
        b[7]=(Button)findViewById(R.id.Pfv8);
        b[8]=(Button)findViewById(R.id.Pfv9);
        b[9]=(Button)findViewById(R.id.Pfv10);
        b[10]=(Button)findViewById(R.id.Pfv11);
        b[11]=(Button)findViewById(R.id.Pfv12);
        b[12]=(Button)findViewById(R.id.Pfv13);
        b[13]=(Button)findViewById(R.id.Pfv14);
        b[14]=(Button)findViewById(R.id.Pfv15);
        b[15]=(Button)findViewById(R.id.Pfv16);
        b[16]=(Button)findViewById(R.id.Pfv17);
        b[17]=(Button)findViewById(R.id.Pfv18);
        b[18]=(Button)findViewById(R.id.Pfv19);
        b[19]=(Button)findViewById(R.id.Pfv20);
        b[20]=(Button)findViewById(R.id.Pfv21);
        b[21]=(Button)findViewById(R.id.Pfv22);
        b[22]=(Button)findViewById(R.id.Pfv23);
        b[23]=(Button)findViewById(R.id.Pfv24);
        b[24]=(Button)findViewById(R.id.Pfv25);
        mp1 = MediaPlayer.create(getApplicationContext(), R.raw.beep);
        mp = MediaPlayer.create(getApplicationContext(), R.raw.push);
        steps=(TextView) findViewById(R.id.steps);
        stepc=0;
        choosepic=new puzzpics();
        for(i=0;i<25;i++)
            b[i].setOnClickListener(this);
        Random r=new Random();
        pic=choosepic.choose(2);
        k= r.nextInt(6);
        for(i=0;i<24;i++)
        {
            b[i].setText(""+a[k][i]);
            b[i].setBackgroundResource(pic[a[k][i] - 1]);
        }

    }

    @Override
    public void onClick(View v) {
        try {
            for(s=0;s<25;s++)
            {
                if(b[s].getText().toString().equals(""))
                    break;
            }
            for (i = 0; i < 25; i++) {
                if (v == b[i]) {
                    j = Integer.parseInt(b[i].getText().toString());
                    if (s == (i + 1) && s != 0 && s != 5 && s != 10 && s!=15&&s!=20||i == (s + 5)||i == (s - 5)||s == (i - 1) && s != 4 && s != 9 && s != 14 && s!=19) {
                        choice(i, j, s);
                        break;
                    } else {
                        if(Settings.onoff==1)
                            mp1.start();
                    }
                }
            }

            for (i=0;i<24; i++)
            {
                j=Integer.parseInt(b[i].getText().toString());
                tr+=j;
            }
           if (tr.equals("123456789101112131415161718192021222324"))
            {
                stepc=stepc*10+5;
                startActivity(new Intent(Picp5.this, McqQ.class).putExtra("steps",stepc));
                finish();
            }
        }
        catch(Exception e)
        {
        }
    }
    public void choice(int i1,int j1,int s1)
    {
        if(Settings.onoff==1)
            mp.start();

        b[s1].setText("" + j1);
        b[s1].setBackgroundResource(pic[j1 - 1]);
        b[i1].setText("");
        b[i1].setBackgroundColor(Color.parseColor("#00000000"));
        stepc++;
        steps.setText("" + stepc);
        tr="";
    }
    public void pause(View v)
    {
        SharedPreferences sp = getSharedPreferences("key", 0);
        //smarks.setText(sp.getString("marks", "0"));
        //cs3.setText(sp.getString("c3", " - "));

        // Create custom dialog object
        final Dialog dialog = new Dialog(Picp5.this,android.R.style.Theme_DeviceDefault_Light_NoActionBar_TranslucentDecor);
        // Include dialog.xml file
        dialog.setContentView(R.layout.imagelog);
        // Set dialog title Theme_Material_Light_NoActionBar_TranslucentDecor
        dialog.setTitle("Custom Dialog");
        dialog.setCancelable(false);

        // set values for custom dialog components - text, image and button
        ImageView im=(ImageView) dialog.findViewById(R.id.imv);
        im.setImageResource(R.drawable.five);
        TextView time = (TextView) dialog.findViewById(R.id.st);
        time.setText("\n"+sp.getString("Picp3", "-"));
        Button resumeButton = (Button) dialog.findViewById(R.id.resume);
        resumeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //for timestop
                dialog.cancel();
            }

        });
        dialog.show();
    }
    public void restart(View v)
    {
        onCreate(null);
    }
    public void onBackPressed()
    {
        super.onBackPressed();
        startActivity(new Intent(Picp5.this, Tablevels.class).putExtra("Goto",5));
        finish();
    }
}
