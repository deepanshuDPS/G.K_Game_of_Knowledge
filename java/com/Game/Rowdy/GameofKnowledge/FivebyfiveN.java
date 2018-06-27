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

public class FivebyfiveN extends Activity implements View.OnClickListener{

    Button b[]=new Button[25];
    int s;
    int i,j,k;
    String tr;
    static int next=0;
    Chronometer chronometer;
    MediaPlayer mp,mp1;
    long timestop;
    int a[][]={{5,20,12,4,24,10,15,13,2,19,23,17,7,9,8,1,6,14,3,16,18,22,11,21},{6,10,20,4,24,15,13,12,23,5,1,7,2,8,19,18,22,17,16,9,11,14,21,3},
                {7,1,20,4,24,10,15,13,12,5,6,22,2,23,9,11,18,17,19,3,14,21,8,16},{8,6,1,15,4,3,11,7,20,24,14,2,10,13,5,21,18,12,17,19,16,22,9,23},
                {9,6,10,1,4,21,3,11,15,24,14,22,2,5,8,16,13,18,7,20,17,12,23,19},{10,15,20,5,19,4,8,12,16,24,21,18,9,6,3,2,22,14,13,17,11,23,1,7},
                {11,20,10,13,19,22,4,7,12,8,5,17,16,24,21,3,1,15,23,18,9,6,2,14},{12,22,10,17,15,20,5,8,4,13,9,3,14,21,2,6,7,19,16,24,11,18,1,23},
                {13,20,3,24,23,10,15,7,22,9,5,8,4,14,19,12,6,18,21,16,2,1,11,17},{1,16,8,19,24,15,5,11,13,3,21,7,12,22,14,4,20,6,18,17,2,10,9,23},
                {2,8,22,13,24,19,15,11,6,3,5,1,18,4,14,12,10,16,9,23,20,21,7,17},{3,13,15,6,24,22,17,1,8,14,19,10,21,9,23,5,18,2,4,11,12,16,7,20},
                {4,10,6,17,23,19,24,1,22,14,13,16,12,8,21,5,2,15,18,20,9,7,11,3}};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fivebyfive_n);
        b[0]=(Button)findViewById(R.id.Ixi1);
        b[1]=(Button)findViewById(R.id.Ixi2);
        b[2]=(Button)findViewById(R.id.Ixi3);
        b[3]=(Button)findViewById(R.id.Ixi4);
        b[4]=(Button)findViewById(R.id.Ixi5);
        b[5]=(Button)findViewById(R.id.Ixi6);
        b[6]=(Button)findViewById(R.id.Ixi7);
        b[7]=(Button)findViewById(R.id.Ixi8);
        b[8]=(Button)findViewById(R.id.Ixi9);
        b[9]=(Button)findViewById(R.id.Ixi10);
        b[10]=(Button)findViewById(R.id.Ixi11);
        b[11]=(Button)findViewById(R.id.Ixi12);
        b[12]=(Button)findViewById(R.id.Ixi13);
        b[13]=(Button)findViewById(R.id.Ixi14);
        b[14]=(Button)findViewById(R.id.Ixi15);
        b[15]=(Button)findViewById(R.id.Ixi16);
        b[16]=(Button)findViewById(R.id.Ixi17);
        b[17]=(Button)findViewById(R.id.Ixi18);
        b[18]=(Button)findViewById(R.id.Ixi19);
        b[19]=(Button)findViewById(R.id.Ixi20);
        b[20]=(Button)findViewById(R.id.Ixi21);
        b[21]=(Button)findViewById(R.id.Ixi22);
        b[22]=(Button)findViewById(R.id.Ixi23);
        b[23]=(Button)findViewById(R.id.Ixi24);
        b[24]=(Button)findViewById(R.id.Ixi25);
        chronometer=(Chronometer) findViewById(R.id.chronometer);
        mp1 = MediaPlayer.create(getApplicationContext(), R.raw.beep);
        mp = MediaPlayer.create(getApplicationContext(), R.raw.push);
        for(i=0;i<25;i++)
            b[i].setOnClickListener(this);
        Random r=new Random();
        k= r.nextInt(9);
        for(i=0;i<24;i++)
        {
                b[i].setText(""+a[k][i]);
            if(i%2==1) {

                b[i].setBackgroundColor(Color.GREEN);
            }
            if(i%2==0)
            {
                b[i].setBackgroundColor(Color.parseColor("#00bfff"));
            }

        }
        chronometer.start();
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
                    if (s == (i + 1) && s != 0 && s != 5 && s != 10 && s!=15&&s!=20) {
                        choice(i, j, s);
                        break;
                    } else if (s == (i - 1) && s != 4 && s != 9 && s != 14 && s!=19) {
                        choice(i, j, s);
                        break;
                    } else if (i == (s + 5)) {
                        choice(i, j, s);
                        break;
                    } else if (i == (s - 5)) {
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
                for (int p = 0; p < 25; p++) {
                    b[p].setText("");
                    b[p].setBackgroundColor(Color.parseColor("#00000000"));
                }
                chronometer.stop();
                next=3;
                long time = SystemClock.elapsedRealtime() - chronometer.getBase();
                time=time*100+52;
                startActivity(new Intent(FivebyfiveN.this, Questions.class).putExtra("TIME", time));
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
        //cs3.setText(sp.getString("n5", " - "));
        timestop=0;
        timestop=chronometer.getBase() - SystemClock.elapsedRealtime(); chronometer.stop();

        // Create custom dialog object
        final Dialog dialog = new Dialog(FivebyfiveN.this,android.R.style.Theme_DeviceDefault_Light_NoActionBar_TranslucentDecor);
        // Include dialog.xml file
        dialog.setContentView(R.layout.pauselog);
        // Set dialog title Theme_Material_Light_NoActionBar_TranslucentDecor
        dialog.setTitle("Custom Dialog");
        dialog.setCancelable(false);

        // set values for custom dialog components - text, image and button
        TextView time = (TextView) dialog.findViewById(R.id.st);
        time.setText(sp.getString("n5", " - "));
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
        final Dialog dialog = new Dialog(FivebyfiveN.this,android.R.style.Theme_DeviceDefault_Light_NoActionBar_TranslucentDecor);
        // Include dialog.xml file
        dialog.setContentView(R.layout.helplog);
        // Set dialog title Theme_Material_Light_NoActionBar_TranslucentDecor
        dialog.setTitle("Custom Dialog");
        dialog.setCancelable(true);

        // set values for custom dialog components - text, image and button
        ImageView imv=(ImageView) dialog.findViewById(R.id.imv);
        imv.setImageResource(R.drawable.fivens);
        TextView how = (TextView) dialog.findViewById(R.id.howto);
        TextView info = (TextView) dialog.findViewById(R.id.info);
        info.setText("5X5- Numbers level \n Align The Numbers \nHorizontally In Ascending Order");
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

        b[i1].setText("");
        b[i1].setBackgroundColor(Color.parseColor("#00000000"));

        if(s1%2==1) {
            b[s1].setText(""+j1);
            b[s1].setBackgroundColor(Color.GREEN);
        }
            if(s1%2==0)
            {   b[s1].setText(""+j1);
                b[s1].setBackgroundColor(Color.parseColor("#00bfff"));
            }

      
        tr="";
    }
    public void onBackPressed()
     {
         super.onBackPressed();
         startActivity(new Intent(FivebyfiveN.this, Levels2.class));
         finish();
     }

}
