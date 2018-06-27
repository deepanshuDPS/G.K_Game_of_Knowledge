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

public class FivebyfiveC extends Activity implements View.OnClickListener{


    Button b[]=new Button[25];
    int s;
    int i,j,k;
    String tr;
    static int next=0;
    Chronometer chronometer;
    MediaPlayer mp,mp1;
    long timestop;
    int a[][]={{3,4,1,3,5,1,2,4,1,2,5,3,5,4,5,1,2,1,3,2,4,3,4,2},{3,4,2,5,2,5,1,4,2,3,2,3,5,3,2,1,4,1,4,1,4,3,5,1},
                {1,4,2,5,2,3,2,4,1,3,5,4,1,4,1,2,3,5,3,2,4,1,3,5},{2,1,2,1,4,3,5,1,5,2,2,4,3,4,3,4,5,1,3,4,5,3,2,1},
                {2,1,3,2,1,1,2,4,1,4,3,1,5,3,5,2,5,2,5,4,3,4,3,4},{1,3,2,1,2,3,2,1,5,3,5,4,2,1,2,4,3,1,5,4,3,4,5,4},
                {1,3,2,3,1,2,4,1,5,2,3,1,3,2,1,5,4,5,4,5,4,3,4,2},{2,3,1,2,1,3,2,4,1,3,4,5,1,5,4,5,1,4,2,5,4,3,2,3},
                {1,3,1,2,3,3,4,2,1,2,1,5,3,4,5,4,3,2,1,4,5,4,5,2},{3,1,3,1,2,1,4,5,4,3,3,5,1,5,2,4,2,4,2,5,2,4,3,1},
                {4,5,4,5,3,1,2,3,1,4,4,5,2,3,1,2,3,4,1,2,5,2,1,3},{5,4,5,3,4,3,2,1,2,3,1,4,5,3,1,4,2,1,2,3,2,5,4,1},
                {5,1,2,4,3,3,5,4,3,5,4,1,3,1,2,2,4,5,2,3,1,2,4,1}};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fivebyfive);
        b[0]=(Button)findViewById(R.id.iXi1);
        b[1]=(Button)findViewById(R.id.iXi2);
        b[2]=(Button)findViewById(R.id.iXi3);
        b[3]=(Button)findViewById(R.id.iXi4);
        b[4]=(Button)findViewById(R.id.iXi5);
        b[5]=(Button)findViewById(R.id.iXi6);
        b[6]=(Button)findViewById(R.id.iXi7);
        b[7]=(Button)findViewById(R.id.iXi8);
        b[8]=(Button)findViewById(R.id.iXi9);
        b[9]=(Button)findViewById(R.id.iXi10);
        b[10]=(Button)findViewById(R.id.iXi11);
        b[11]=(Button)findViewById(R.id.iXi12);
        b[12]=(Button)findViewById(R.id.iXi13);
        b[13]=(Button)findViewById(R.id.iXi14);
        b[14]=(Button)findViewById(R.id.iXi15);
        b[15]=(Button)findViewById(R.id.iXi16);
        b[16]=(Button)findViewById(R.id.iXi17);
        b[17]=(Button)findViewById(R.id.iXi18);
        b[18]=(Button)findViewById(R.id.iXi19);
        b[19]=(Button)findViewById(R.id.iXi20);
        b[20]=(Button)findViewById(R.id.iXi21);
        b[21]=(Button)findViewById(R.id.iXi22);
        b[22]=(Button)findViewById(R.id.iXi23);
        b[23]=(Button)findViewById(R.id.iXi24);
        b[24]=(Button)findViewById(R.id.iXi25);
        chronometer=(Chronometer) findViewById(R.id.chronometer);
        mp1 = MediaPlayer.create(getApplicationContext(), R.raw.beep);
        mp = MediaPlayer.create(getApplicationContext(), R.raw.push);
        try {
            for (i = 0; i < 25; i++)
                b[i].setOnClickListener(this);
            Random r = new Random();
            k = r.nextInt(13);
            for (i = 0; i < 24; i++) {
                if (a[k][i] == 1) {
                    b[i].setText("" + a[k][i]);
                    b[i].setBackgroundColor(Color.BLUE);

                } else if (a[k][i] == 2) {
                    b[i].setText("" + a[k][i]);
                    b[i].setBackgroundColor(Color.GREEN);

                } else if (a[k][i] == 3) {
                    b[i].setText("" + a[k][i]);
                    b[i].setBackgroundColor(Color.parseColor("#ffa500"));  //orange
                } else if (a[k][i] == 4) {
                    b[i].setText("" + a[k][i]);
                    b[i].setBackgroundColor(Color.RED);

                } else {
                    b[i].setText("" + a[k][i]);
                    b[i].setBackgroundColor(Color.YELLOW);
                }
                b[i].setTextColor(Color.parseColor("#00000000"));
            }
            chronometer.start();
        }
        catch (Exception e){}
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
            if (tr.equals("111112222233333444445555"))
            {
                for (int p = 0; p < 25; p++) {
                    b[p].setText("");
                    b[p].setBackgroundColor(Color.parseColor("#00000000"));
                }
                chronometer.stop();
               if(Levels2.t.getText().toString().equals(""))
                   next=2;
                long time = SystemClock.elapsedRealtime() - chronometer.getBase();
                time=time*100+51;
                startActivity(new Intent(FivebyfiveC.this, Questions.class).putExtra("TIME", time));
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
        //cs3.setText(sp.getString("c5", " - "));
        timestop=0;
        timestop=chronometer.getBase() - SystemClock.elapsedRealtime(); chronometer.stop();

        // Create custom dialog object
        final Dialog dialog = new Dialog(FivebyfiveC.this,android.R.style.Theme_DeviceDefault_Light_NoActionBar_TranslucentDecor);
        // Include dialog.xml file
        dialog.setContentView(R.layout.pauselog);
        // Set dialog title Theme_Material_Light_NoActionBar_TranslucentDecor
        dialog.setTitle("Custom Dialog");
        dialog.setCancelable(false);

        // set values for custom dialog components - text, image and button
        TextView time = (TextView) dialog.findViewById(R.id.st);
        time.setText(sp.getString("c5", " - "));
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
        final Dialog dialog = new Dialog(FivebyfiveC.this,android.R.style.Theme_DeviceDefault_Light_NoActionBar_TranslucentDecor);
        // Include dialog.xml file
        dialog.setContentView(R.layout.helplog);
        // Set dialog title Theme_Material_Light_NoActionBar_TranslucentDecor
        dialog.setTitle("Custom Dialog");
        dialog.setCancelable(true);

        // set values for custom dialog components - text, image and button
        ImageView imv=(ImageView) dialog.findViewById(R.id.imv);
        imv.setImageResource(R.drawable.fivecs);
        TextView how = (TextView) dialog.findViewById(R.id.howto);
        TextView info = (TextView) dialog.findViewById(R.id.info);
        info.setText("5X5- Colors level \n Align Color Horizontally In \n Ascending order According to \nFirst alphabet of Colors' name");
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
        if(j1==1)
        {
            b[s1].setText("" + j1);
            b[s1].setBackgroundColor(Color.BLUE);
            b[i1].setText("");
        }
        else if(j1==2)
        {
            b[s1].setText("" + j1);
            b[s1].setBackgroundColor(Color.GREEN);
            b[i1].setText("");
        }
        else if(j1==3)
        {
            b[s1].setText("" + j1);
            b[s1].setBackgroundColor(Color.parseColor("#ffa500"));  //orange
            b[i1].setText("");
        }
        else if(j1==4)
        {
            b[s1].setText("" + j1);
            b[s1].setBackgroundColor(Color.RED);
            b[i1].setText("");
        }
        else
        {
            b[s1].setText("" + j1);
            b[s1].setBackgroundColor(Color.YELLOW);
            b[i1].setText("");
        }
        b[s1].setTextColor(Color.parseColor("#00000000"));
        b[i1].setBackgroundColor(Color.parseColor("#00000000"));
        tr="";
    }
    public void onBackPressed()
    {
        super.onBackPressed();
        startActivity(new Intent(FivebyfiveC.this, Levels2.class));
        finish();
    }


}
