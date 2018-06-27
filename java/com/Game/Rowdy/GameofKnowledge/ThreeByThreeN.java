package com.Game.Rowdy.GameofKnowledge;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class ThreeByThreeN extends Activity implements View.OnClickListener{

    Button b[]=new Button[9];
    int s;
    int i,j;
    int count=100;
    static int next=0;
    TextView t;
    Chronometer chronometer;
    MediaPlayer mp,mp1;
    long timestop;
    int a[][]={{7,5,8,6,4,2,3,1},{6,4,8,3,2,5,7,1},{3,8,4,1,7,2,6,5},{2,7,1,8,6,4,3,5},{1,7,3,4,8,5,6,2},{7,3,5,1,8,2,4,6,},{3,5,2,6,1,7,8,4}};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_three_by_three_n);
        b[0]=(Button)findViewById(R.id.Txt1);
        b[1]=(Button)findViewById(R.id.Txt2);
        b[2]=(Button)findViewById(R.id.Txt3);
        b[3]=(Button)findViewById(R.id.Txt4);
        b[4]=(Button)findViewById(R.id.Txt5);
        b[5]=(Button)findViewById(R.id.Txt6);
        b[6]=(Button)findViewById(R.id.Txt7);
        b[7]=(Button)findViewById(R.id.Txt8);
        b[8]=(Button)findViewById(R.id.Txt9);
        t=(TextView) findViewById(R.id.text);
        chronometer=(Chronometer) findViewById(R.id.chronometer);
        mp1 = MediaPlayer.create(getApplicationContext(), R.raw.beep);
        mp = MediaPlayer.create(getApplicationContext(), R.raw.push);
        for(i=0;i<9;i++)
            b[i].setOnClickListener(this);
        Random r=new Random();
        int k=r.nextInt(7);
        for(i=0;i<8;i++)
        {
                b[i].setText(""+a[k][i]);
        }
        chronometer.start();
    }
    public void onClick(View v)
    {
        try {
            for(s=0;s<9;s++)
            {
                if(b[s].getText().toString().equals(""))
                    break;
            }
            for (i = 0; i < 9; i++) {
                if (v == b[i]) {
                    j = Integer.parseInt(b[i].getText().toString());
                    if (s == (i + 1) && s != 0 && s != 3 && s != 6) {
                        choice(i, j, s);
                        break;
                    } else if (s == (i - 1) && s != 2 && s != 5 && s != 8) {
                        choice(i, j, s);
                        break;
                    } else if (i == (s + 3)) {
                        choice(i, j, s);
                        break;
                    } else if (i == (s - 3)) {
                        choice(i, j, s);
                        break;
                    } else {
                        if(Settings.onoff==1)
                            mp1.start();
                        count=0;
                    }
                }
            }
            for (i=0;i<8; i++)
            {
                j=Integer.parseInt(b[i].getText().toString());
                if(j!=i+1)
                    break;
                else;
                count++;
            }

            if (count==8)
            {
                for (int p = 0; p < 9; p++) {
                    b[p].setText("");
                    b[p].setBackgroundColor(Color.parseColor("#00000000"));
                }
                chronometer.stop();
                next=3;
                long time = SystemClock.elapsedRealtime() - chronometer.getBase();
                time=time*100+32;
                startActivity(new Intent(ThreeByThreeN.this, Questions.class).putExtra("TIME", time));
                finish();
            }
        }
        catch(Exception e) {
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
        //cs3.setText(sp.getString("c3", " - "));

        timestop=0;
        timestop=chronometer.getBase() - SystemClock.elapsedRealtime(); chronometer.stop();

        // Create custom dialog object
        final Dialog dialog = new Dialog(ThreeByThreeN.this,android.R.style.Theme_DeviceDefault_Light_NoActionBar_TranslucentDecor);
        // Include dialog.xml file
        dialog.setContentView(R.layout.pauselog);
        // Set dialog title Theme_Material_Light_NoActionBar_TranslucentDecor
        dialog.setTitle("Custom Dialog");
        dialog.setCancelable(false);

        // set values for custom dialog components - text, image and button
        TextView time = (TextView) dialog.findViewById(R.id.st);
        time.setText(sp.getString("n3", " - "));
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
        final Dialog dialog = new Dialog(ThreeByThreeN.this,android.R.style.Theme_DeviceDefault_Light_NoActionBar_TranslucentDecor);
        // Include dialog.xml file
        dialog.setContentView(R.layout.helplog);
        // Set dialog title Theme_Material_Light_NoActionBar_TranslucentDecor
        dialog.setTitle("Custom Dialog");
        dialog.setCancelable(true);

        // set values for custom dialog components - text, image and button
        ImageView imv=(ImageView) dialog.findViewById(R.id.imv);
        imv.setImageResource(R.drawable.threens);
        TextView how = (TextView) dialog.findViewById(R.id.howto);
        TextView info = (TextView) dialog.findViewById(R.id.info);
        info.setText("3X3- Numbers level \n Align The Numbers \nHorizontally In Ascending Order");
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
    public void choice(int i1, int j1, int s1)
    {
        if(Settings.onoff==1)
            mp.start();

        if(s1%2==0)
        {
            b[s1].setBackgroundColor(Color.parseColor("#d3d3d3"));  //Gray
        }
        else
        {
            b[s1].setBackgroundColor(Color.parseColor("#6eebf7"));  //sky blue
        }
            b[s1].setText("" + j1);
            b[i1].setText("");
            b[i1].setBackgroundColor(Color.parseColor("#00000000"));
            t.setText("");
            count=0;
    }
    public void onBackPressed()
    {
        super.onBackPressed();
        startActivity(new Intent(ThreeByThreeN.this, Levels.class));
        finish();
    }

}
