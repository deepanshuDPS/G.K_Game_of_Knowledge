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

public class Picp6 extends Activity implements View.OnClickListener{
    Button b[]=new Button[36];
    int s;
    int i,j,k,pic[]=new int[100];
    String tr;
    static int next=0;
    MediaPlayer mp,mp1;
    long stepc;
    TextView steps;
    puzzpics choosepic;
    int a[][]={{34,25,15,5,1,6,11,17,23,29,35,30,26,21,16,12,7,2,8,13,18,24,28,33,3,9,14,22,32,19,20,27,4,31,10},
            {35,8,21,29,17,10,20,33,32,19,14,25,30,22,11,2,12,24,16,3,13,1,31,26,9,34,7,27,23,5,15,28,4,18,6},
            {33,20,8,21,19,10,16,22,32,17,29,25,13,9,2,31,14,24,30,15,11,12,1,26,35,3,7,27,5,6,34,28,4,18,23},
            {32,13,20,9,21,25,22,8,31,2,10,19,30,16,7,33,29,17,15,27,18,24,14,26,12,34,28,6,11,35,3,5,4,23,1},
            {30,20,31,10,25,19,16,13,9,21,32,14,7,22,8,2,33,29,15,27,28,11,17,26,12,34,24,18,6,35,3,5,4,23,1}};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_picp6);
        b[0]=(Button)findViewById(R.id.Ps1);
        b[1]=(Button)findViewById(R.id.Ps2);
        b[2]=(Button)findViewById(R.id.Ps3);
        b[3]=(Button)findViewById(R.id.Ps4);
        b[4]=(Button)findViewById(R.id.Ps5);
        b[5]=(Button)findViewById(R.id.Ps6);
        b[6]=(Button)findViewById(R.id.Ps7);
        b[7]=(Button)findViewById(R.id.Ps8);
        b[8]=(Button)findViewById(R.id.Ps9);
        b[9]=(Button)findViewById(R.id.Ps10);
        b[10]=(Button)findViewById(R.id.Ps11);
        b[11]=(Button)findViewById(R.id.Ps12);
        b[12]=(Button)findViewById(R.id.Ps13);
        b[13]=(Button)findViewById(R.id.Ps14);
        b[14]=(Button)findViewById(R.id.Ps15);
        b[15]=(Button)findViewById(R.id.Ps16);
        b[16]=(Button)findViewById(R.id.Ps17);
        b[17]=(Button)findViewById(R.id.Ps18);
        b[18]=(Button)findViewById(R.id.Ps19);
        b[19]=(Button)findViewById(R.id.Ps20);
        b[20]=(Button)findViewById(R.id.Ps21);
        b[21]=(Button)findViewById(R.id.Ps22);
        b[22]=(Button)findViewById(R.id.Ps23);
        b[23]=(Button)findViewById(R.id.Ps24);
        b[24]=(Button)findViewById(R.id.Ps25);
        b[25]=(Button)findViewById(R.id.Ps26);
        b[26]=(Button)findViewById(R.id.Ps27);
        b[27]=(Button)findViewById(R.id.Ps28);
        b[28]=(Button)findViewById(R.id.Ps29);
        b[29]=(Button)findViewById(R.id.Ps30);
        b[30]=(Button)findViewById(R.id.Ps31);
        b[31]=(Button)findViewById(R.id.Ps32);
        b[32]=(Button)findViewById(R.id.Ps33);
        b[33]=(Button)findViewById(R.id.Ps34);
        b[34]=(Button)findViewById(R.id.Ps35);
        b[35]=(Button)findViewById(R.id.Ps36);
        mp1 = MediaPlayer.create(getApplicationContext(), R.raw.beep);
        mp = MediaPlayer.create(getApplicationContext(), R.raw.push);
        steps=(TextView) findViewById(R.id.steps);
        stepc=0;
        choosepic=new puzzpics();
        pic=choosepic.choose(3);
            for (i = 0; i < 36; i++)
                b[i].setOnClickListener(this);
            Random r = new Random();
            k = r.nextInt(5);
            for (i = 0; i < 35; i++)
            {
                b[i].setText("" + a[k][i]);
                b[i].setBackgroundResource(pic[a[k][i] - 1]);
            }
            
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
                    if (s == (i + 1) && s != 0 && s != 6 && s != 12 && s!=18&&s!=24&&s!=30||i == (s + 6)||s == (i - 1) && s != 5 && s != 11 && s != 17 && s!=23&&s!=29||i == (s - 6)) {
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
            if (tr.equals("1234567891011121314151617181920212223242526272829303132333435"))
            {
                stepc=stepc*10+6;
                startActivity(new Intent(Picp6.this, McqQ.class).putExtra("steps",stepc));
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
        final Dialog dialog = new Dialog(Picp6.this,android.R.style.Theme_DeviceDefault_Light_NoActionBar_TranslucentDecor);
        // Include dialog.xml file
        dialog.setContentView(R.layout.imagelog);
        // Set dialog title Theme_Material_Light_NoActionBar_TranslucentDecor
        dialog.setTitle("Custom Dialog");
        dialog.setCancelable(false);

        // set values for custom dialog components - text, image and button
        ImageView im=(ImageView) dialog.findViewById(R.id.imv);
        im.setImageResource(R.drawable.six);
        TextView time = (TextView) dialog.findViewById(R.id.st);
        time.setText("\n"+sp.getString("Picp4", ""));
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
        startActivity(new Intent(Picp6.this, Tablevels.class).putExtra("Goto",6));
        finish();
    }
}
