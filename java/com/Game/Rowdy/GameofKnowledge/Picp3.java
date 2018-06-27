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

public class Picp3 extends Activity implements View.OnClickListener{

    Button b[]=new Button[9];
    int s,i,j,count=100;
    puzzpics choosepic;
    TextView steps;
    MediaPlayer mp,mp1;
    long stepc;
    int pic[]=new int[100];
    int a[][]={{7,5,8,6,4,2,3,1},{6,4,8,3,2,5,7,1},{3,8,4,1,7,2,6,5},{2,7,1,8,6,4,3,5},{7,3,5,1,8,2,4,6}};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_picp3);
        b[0]=(Button)findViewById(R.id.Pt1);
        b[1]=(Button)findViewById(R.id.Pt2);
        b[2]=(Button)findViewById(R.id.Pt3);
        b[3]=(Button)findViewById(R.id.Pt4);
        b[4]=(Button)findViewById(R.id.Pt5);
        b[5]=(Button)findViewById(R.id.Pt6);
        b[6]=(Button)findViewById(R.id.Pt7);
        b[7]=(Button)findViewById(R.id.Pt8);
        b[8]=(Button)findViewById(R.id.Pt9);
        steps=(TextView) findViewById(R.id.steps);
        mp1 = MediaPlayer.create(getApplicationContext(), R.raw.beep);
        mp = MediaPlayer.create(getApplicationContext(), R.raw.push);
        choosepic=new puzzpics();
        stepc=0;
        for(i=0;i<9;i++)
            b[i].setOnClickListener(this);
        Random r=new Random();
        pic=choosepic.choose(0);
        int k=r.nextInt(5);
        for(i=0;i<8;i++)
        {
            b[i].setText(""+a[k][i]);
            b[i].setBackgroundResource(pic[a[k][i] - 1]);
        }
    }

    @Override
    public void onClick(View v)
    {
        try
        {
            for(s=0;s<9;s++)
            {
                if(b[s].getText().toString().equals(""))
                    break;
            }
            for (i = 0; i < 9; i++) {
                if (v == b[i]) {
                    j = Integer.parseInt(b[i].getText().toString());
                    if (s == (i + 1) && s != 0 && s != 3 && s != 6||s == (i - 1) && s != 2 && s != 5 && s != 8||i == (s - 3)||i == (s + 3))
                    {
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
                stepc=stepc*10+3;
                startActivity(new Intent(Picp3.this, McqQ.class).putExtra("steps",stepc));
                finish();
            }
        }
        catch (Exception e){}

    }
    public void choice(int i1, int j1, int s1)
    {
        if(Settings.onoff==1)
            mp.start();
        b[s1].setText("" + j1);
        b[s1].setBackgroundResource(pic[j1 - 1]);
        b[i1].setText("");
        b[i1].setBackgroundColor(Color.parseColor("#00000000"));
        count=0;
        stepc++;
        steps.setText(""+stepc);
    }
    public void pause(View v)
    {
        SharedPreferences sp = getSharedPreferences("key", 0);
        //smarks.setText(sp.getString("marks", "0"));
        //cs3.setText(sp.getString("c3", " - "));

        // Create custom dialog object
        final Dialog dialog = new Dialog(Picp3.this,android.R.style.Theme_DeviceDefault_Light_NoActionBar_TranslucentDecor);
        // Include dialog.xml file
        dialog.setContentView(R.layout.imagelog);
        // Set dialog title Theme_Material_Light_NoActionBar_TranslucentDecor
        dialog.setTitle("Custom Dialog");
        dialog.setCancelable(false);

        // set values for custom dialog components - text, image and button
        ImageView im=(ImageView) dialog.findViewById(R.id.imv);
        im.setImageResource(R.drawable.three);
        TextView time = (TextView) dialog.findViewById(R.id.st);
        time.setText("\n"+sp.getString("Picp1", "-"));
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
        startActivity(new Intent(Picp3.this, Tablevels.class).putExtra("Goto",3));
        finish();
    }
}
