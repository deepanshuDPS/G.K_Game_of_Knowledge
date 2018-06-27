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
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class Picp4 extends Activity implements View.OnClickListener{

    Button b[]=new Button[16];
    int s;
    int i,j,k;
    String tr;
    static int next=0;
    int pic[]=new int[100];
    MediaPlayer mp,mp1;
    long stepc;
    puzzpics choosepic;
    TextView steps;
    int a[][]={{15,12,3,7,9,14,8,10,6,1,11,4,13,5,2},{5,14,3,15,1,11,13,10,6,2,12,8,4,7,9},{1,15,13,3,11,14,8,6,4,2,12,7,10,5,9},
            {2,1,15,3,11,5,14,13,8,12,10,9,4,7,6},{12,8,4,3,15,10,7,2,14,6,11,1,13,9,5}};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_picp4);
        b[0]=(Button)findViewById(R.id.Pfr1);
        b[1]=(Button)findViewById(R.id.Pfr2);
        b[2]=(Button)findViewById(R.id.Pfr3);
        b[3]=(Button)findViewById(R.id.Pfr4);
        b[4]=(Button)findViewById(R.id.Pfr5);
        b[5]=(Button)findViewById(R.id.Pfr6);
        b[6]=(Button)findViewById(R.id.Pfr7);
        b[7]=(Button)findViewById(R.id.Pfr8);
        b[8]=(Button)findViewById(R.id.Pfr9);
        b[9]=(Button)findViewById(R.id.Pfr10);
        b[10]=(Button)findViewById(R.id.Pfr11);
        b[11]=(Button)findViewById(R.id.Pfr12);
        b[12]=(Button)findViewById(R.id.Pfr13);
        b[13]=(Button)findViewById(R.id.Pfr14);
        b[14]=(Button)findViewById(R.id.Pfr15);
        b[15]=(Button)findViewById(R.id.Pfr16);
        mp1 = MediaPlayer.create(getApplicationContext(), R.raw.beep);
        mp = MediaPlayer.create(getApplicationContext(), R.raw.push);
        steps=(TextView) findViewById(R.id.steps);
        choosepic=new puzzpics();
        stepc=0;
        for(i=0;i<16;i++)
            b[i].setOnClickListener(this);
        Random r=new Random();
        pic=choosepic.choose(1);
        k= r.nextInt(5);
        for(i=0;i<15;i++)
        {
            b[i].setText(""+a[k][i]);
            b[i].setBackgroundResource(pic[a[k][i] - 1]);
        }
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
                    j = Integer.parseInt(b[i].getText().toString());
                    if (s == (i + 1) && s != 0 && s != 4 && s != 8 && s!=12||s == (i - 1) && s != 3 && s != 7 && s != 11 && s!=15||i == (s + 4)||i == (s - 4)) {
                        choice(i, j, s);
                        break;
                    }
                    else {
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
            if (tr.equals("123456789101112131415"))
            {
                stepc=stepc*10+4;
                startActivity(new Intent(Picp4.this, McqQ.class).putExtra("steps",stepc));
                finish();
            }
        }

        catch(Exception e) {}
    }
    public void choice(int i1, int j1, int s1)
    {
        if(Settings.onoff==1)
            mp.start();
        b[s1].setText("" + j1);
        b[s1].setBackgroundResource(pic[j1 - 1]);
        b[i1].setText("");
        b[i1].setBackgroundColor(Color.parseColor("#00000000"));
        stepc++;
        steps.setText(""+stepc);
        tr="";

    }
    public void pause(View v)
    {
        SharedPreferences sp = getSharedPreferences("key", 0);
        //smarks.setText(sp.getString("marks", "0"));
        //cs3.setText(sp.getString("c3", " - "));

        // Create custom dialog object
        final Dialog dialog = new Dialog(Picp4.this,android.R.style.Theme_DeviceDefault_Light_NoActionBar_TranslucentDecor);
        // Include dialog.xml file
        dialog.setContentView(R.layout.imagelog);
        // Set dialog title Theme_Material_Light_NoActionBar_TranslucentDecor
        dialog.setTitle("Custom Dialog");
        dialog.setCancelable(false);

        // set values for custom dialog components - text, image and button
        ImageView im=(ImageView) dialog.findViewById(R.id.imv);
        im.setImageResource(R.drawable.four);
        TextView time = (TextView) dialog.findViewById(R.id.st);
        time.setText("\n"+sp.getString("Picp2", "-"));
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
        startActivity(new Intent(Picp4.this, Tablevels.class).putExtra("Goto",4));
        finish();
    }
}
