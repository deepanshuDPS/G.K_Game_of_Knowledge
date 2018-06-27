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

public class FourbyfourC extends Activity implements View.OnClickListener{

    Button b[]=new Button[16];
    int s;
    int i,j,k;
    String tr;
    static int next=0;
    Chronometer chronometer;
    MediaPlayer mp,mp1;
    long timestop;
    int a[][]={{4,2,3,2,1,3,1,4,3,1,2,3,4,2,1},{4,1,3,2,1,4,2,3,4,2,3,2,1,3,1},{1,4,2,3,2,1,3,2,3,4,1,3,2,1,4},{3,1,2,3,1,2,1,4,2,4,2,3,4,1,3},
            {4,3,4,1,1,2,3,2,3,1,2,1,4,2,3},{2,3,4,3,1,2,1,2,3,4,3,1,1,2,4},{4,1,4,1,1,4,2,3,3,2,3,2,1,3,2}};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_fourbyfour_c);
        b[0]=(Button)findViewById(R.id.fXf1);
        b[1]=(Button)findViewById(R.id.fXf2);
        b[2]=(Button)findViewById(R.id.fXf3);
        b[3]=(Button)findViewById(R.id.fXf4);
        b[4]=(Button)findViewById(R.id.fXf5);
        b[5]=(Button)findViewById(R.id.fXf6);
        b[6]=(Button)findViewById(R.id.fXf7);
        b[7]=(Button)findViewById(R.id.fXf8);
        b[8]=(Button)findViewById(R.id.fXf9);
        b[9]=(Button)findViewById(R.id.fXf10);
        b[10]=(Button)findViewById(R.id.fXf11);
        b[11]=(Button)findViewById(R.id.fXf12);
        b[12]=(Button)findViewById(R.id.fXf13);
        b[13]=(Button)findViewById(R.id.fXf14);
        b[14]=(Button)findViewById(R.id.fXf15);
        b[15]=(Button)findViewById(R.id.fXf16);
        chronometer=(Chronometer) findViewById(R.id.chronometer);
        mp1 = MediaPlayer.create(getApplicationContext(), R.raw.beep);
        mp = MediaPlayer.create(getApplicationContext(), R.raw.push);
        for(i=0;i<16;i++)
            b[i].setOnClickListener(this);
        Random r=new Random();
        k= r.nextInt(7);
        for(i=0;i<15;i++)
        {
            if(a[k][i]==1)
            {
                b[i].setText(""+a[k][i]);
                b[i].setBackgroundColor(Color.BLUE);
            }
            else if (a[k][i] == 2)
            {

                b[i].setText(""+a[k][i]);
                b[i].setBackgroundColor(Color.GREEN);
            }
            else if (a[k][i] == 3)
            {
                b[i].setText(""+a[k][i]);
                b[i].setBackgroundColor(Color.RED);
            }
            else
            {
                b[i].setText(""+a[k][i]);
                b[i].setBackgroundColor(Color.YELLOW);
            }
            b[i].setTextColor(Color.parseColor("#00000000"));
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

            for (i=0;i<15; i++)
            {
                j=Integer.parseInt(b[i].getText().toString());
                tr+=j;
            }
            if (tr.equals("123412341234123"))
            {
                for (int p = 0; p < 16; p++) {
                    b[p].setText("");
                    b[p].setBackgroundColor(Color.parseColor("#00000000"));
                }
                chronometer.stop();
                if(Levels1.t.getText().toString().equals(""))
                    next=2;
                long time = SystemClock.elapsedRealtime() - chronometer.getBase();
                time=time*100+41;
                startActivity(new Intent(FourbyfourC.this, Questions.class).putExtra("TIME", time));
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
        //cs3.setText(sp.getString("c4", " - "));
        timestop=0;
        timestop=chronometer.getBase() - SystemClock.elapsedRealtime(); chronometer.stop();

        // Create custom dialog object
        final Dialog dialog = new Dialog(FourbyfourC.this,android.R.style.Theme_DeviceDefault_Light_NoActionBar_TranslucentDecor);
        // Include dialog.xml file
        dialog.setContentView(R.layout.pauselog);
        // Set dialog title Theme_Material_Light_NoActionBar_TranslucentDecor
        dialog.setTitle("Custom Dialog");
        dialog.setCancelable(false);

        // set values for custom dialog components - text, image and button
        TextView time = (TextView) dialog.findViewById(R.id.st);
        time.setText(sp.getString("c4", " - "));
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
        final Dialog dialog = new Dialog(FourbyfourC.this,android.R.style.Theme_DeviceDefault_Light_NoActionBar_TranslucentDecor);
        // Include dialog.xml file
        dialog.setContentView(R.layout.helplog);
        // Set dialog title Theme_Material_Light_NoActionBar_TranslucentDecor
        dialog.setTitle("Custom Dialog");
        dialog.setCancelable(true);

        // set values for custom dialog components - text, image and button
        ImageView imv=(ImageView) dialog.findViewById(R.id.imv);
        imv.setImageResource(R.drawable.fourcs);
        TextView how = (TextView) dialog.findViewById(R.id.howto);
        TextView info = (TextView) dialog.findViewById(R.id.info);
        info.setText("4X4- Colors level \n Align Color Vertically In \n Ascending order According to \nFirst alphabet of Colors' name");
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
            b[s1].setText(""+j1);
            b[s1].setBackgroundColor(Color.BLUE);
            b[s1].setTextColor(Color.BLUE);
            b[i1].setText("");
            b[i1].setBackgroundColor(Color.parseColor("#00000000"));
        }
        else if(j1==2)
        {
            b[s1].setText(""+j1);
            b[s1].setBackgroundColor(Color.GREEN);
            b[s1].setTextColor(Color.GREEN);
            b[i1].setText("");
            b[i1].setBackgroundColor(Color.parseColor("#00000000"));
        }
        else if(j1==3)
        {
            b[s1].setText(""+j1);
            b[s1].setBackgroundColor(Color.RED);
            b[s1].setTextColor(Color.RED);
            b[i1].setText("");
            b[i1].setBackgroundColor(Color.parseColor("#00000000"));
        }
        else
        {
            b[s1].setText(""+j1);
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
        startActivity(new Intent(FourbyfourC.this, Levels1.class));
        finish();
    }

}
