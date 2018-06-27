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

public class FivebyfivA extends Activity implements View.OnClickListener{


    Button b[]=new Button[25];
    int s;
    int i,k;
    char j;
    String tr;
    static int next=0;
    Chronometer chronometer;
    MediaPlayer mp,mp1;
    long timestop;
     char a[][]={{'D','J','F','Q','W','S','X','A','V','N','M','P','L','H','U','E','B','O','R','T','I','G','K','C'},
             {'C','M','O','F','X','V','Q','A','H','N','S','J','U','I','W','E','R','B','D','K','L','P','G','T'},
             {'B','H','V','M','X','S','O','K','F','C','E','A','R','D','N','L','J','P','I','W','T','U','G','Q'},
             {'A','P','H','S','X','O','E','K','M','C','U','G','L','V','N','D','T','F','R','Q','B','J','I','W'},
             {'J','O','T','E','S','D','H','L','P','X','U','R','I','F','C','B','V','N','M','Q','K','W','A','G'},
             {'K','T','J','M','S','V','D','G','L','H','E','Q','P','X','U','C','A','O','W','R','I','F','B','N'},
             {'L','V','J','Q','O','T','E','H','D','M','I','C','N','U','B','F','G','S','P','X','K','R','A','W'},
             {'M','T','C','X','W','J','O','G','V','I','E','H','D','N','S','L','F','R','U','P','B','A','K','Q'},
             {'E','A','S','M','X','O','H','P','K','C','L','V','J','F','N','B','U','R','I','Q','T','G','D','W'},
             {'I','H','T','E','V','O','Q','C','P','S','W','F','M','X','B','U','J','N','D','R','L','G','K','A'},
             {'H','E','T','V','S','O','Q','D','P','C','F','J','M','I','X','W','L','K','N','B','U','G','A','R'},
             {'G','O','E','V','S','J','F','T','P','N','H','D','Q','X','A','U','W','L','R','C','K','B','M','I'}
     };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_fivebyfiv);
        b[0]=(Button)findViewById(R.id.ixI1);
        b[1]=(Button)findViewById(R.id.ixI2);
        b[2]=(Button)findViewById(R.id.ixI3);
        b[3]=(Button)findViewById(R.id.ixI4);
        b[4]=(Button)findViewById(R.id.ixI5);
        b[5]=(Button)findViewById(R.id.ixI6);
        b[6]=(Button)findViewById(R.id.ixI7);
        b[7]=(Button)findViewById(R.id.ixI8);
        b[8]=(Button)findViewById(R.id.ixI9);
        b[9]=(Button)findViewById(R.id.ixI10);
        b[10]=(Button)findViewById(R.id.ixI11);
        b[11]=(Button)findViewById(R.id.ixI12);
        b[12]=(Button)findViewById(R.id.ixI13);
        b[13]=(Button)findViewById(R.id.ixI14);
        b[14]=(Button)findViewById(R.id.ixI15);
        b[15]=(Button)findViewById(R.id.ixI16);
        b[16]=(Button)findViewById(R.id.ixI17);
        b[17]=(Button)findViewById(R.id.ixI18);
        b[18]=(Button)findViewById(R.id.ixI19);
        b[19]=(Button)findViewById(R.id.ixI20);
        b[20]=(Button)findViewById(R.id.ixI21);
        b[21]=(Button)findViewById(R.id.ixI22);
        b[22]=(Button)findViewById(R.id.ixI23);
        b[23]=(Button)findViewById(R.id.ixI24);
        b[24]=(Button)findViewById(R.id.ixI25);
        chronometer=(Chronometer) findViewById(R.id.chronometer);
        mp1 = MediaPlayer.create(getApplicationContext(), R.raw.beep);
        mp = MediaPlayer.create(getApplicationContext(), R.raw.push);
        for(i=0;i<25;i++)
            b[i].setOnClickListener(this);
        Random r=new Random();
        k= r.nextInt(12);
        for(i=0;i<24;i++)
        {
                b[i].setText(""+a[k][i]);
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

                    String t = b[i].getText().toString();
                   j = t.charAt(0);
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
                String t=b[i].getText().toString();
                tr+=t;
            }
            if (tr.equals("ABCDEFGHIJKLMNOPQRSTUVWX"))
            {
                for (int p = 0; p < 25; p++) {
                    b[p].setText("");
                    b[p].setBackgroundColor(Color.parseColor("#00000000"));
                }
                chronometer.stop();
                long time = SystemClock.elapsedRealtime() - chronometer.getBase();
                time=time*100+53;
                startActivity(new Intent(FivebyfivA.this, Questions.class).putExtra("TIME", time));
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
        //cs3.setText(sp.getString("a5", " - "));

        timestop=0;
        timestop=chronometer.getBase() - SystemClock.elapsedRealtime(); chronometer.stop();

        // Create custom dialog object
        final Dialog dialog = new Dialog(FivebyfivA.this,android.R.style.Theme_DeviceDefault_Light_NoActionBar_TranslucentDecor);
        // Include dialog.xml file
        dialog.setContentView(R.layout.pauselog);
        // Set dialog title Theme_Material_Light_NoActionBar_TranslucentDecor
        dialog.setTitle("Custom Dialog");
        dialog.setCancelable(false);

        // set values for custom dialog components - text, image and button
        TextView time = (TextView) dialog.findViewById(R.id.st);
        time.setText(sp.getString("a5", " - "));
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
        final Dialog dialog = new Dialog(FivebyfivA.this,android.R.style.Theme_DeviceDefault_Light_NoActionBar_TranslucentDecor);
        // Include dialog.xml file
        dialog.setContentView(R.layout.helplog);
        // Set dialog title Theme_Material_Light_NoActionBar_TranslucentDecor
        dialog.setTitle("Custom Dialog");
        dialog.setCancelable(true);

        // set values for custom dialog components - text, image and button
        ImageView imv=(ImageView) dialog.findViewById(R.id.imv);
        imv.setImageResource(R.drawable.fiveas);
        TextView how = (TextView) dialog.findViewById(R.id.howto);
        TextView info = (TextView) dialog.findViewById(R.id.info);
        info.setText("5X5- Alphabets level \n Align The Alphabets \nHorizontally In Ascending Order");
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

        b[i1].setText("");
        b[i1].setBackgroundColor(Color.parseColor("#00000000"));
        b[s1].setText("" + j1);
        if(s1%2==0)
            b[s1].setBackgroundColor(Color.parseColor("#ffa500"));
        if(s1%2==1)
            b[s1].setBackgroundColor(Color.YELLOW);


        tr="";
    }
    public void onBackPressed()
     {
         super.onBackPressed();
         startActivity(new Intent(FivebyfivA.this, Levels2.class));
         finish();
     }


}
