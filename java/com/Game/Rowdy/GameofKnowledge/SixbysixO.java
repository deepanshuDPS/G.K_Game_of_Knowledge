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

public class SixbysixO extends Activity implements View.OnClickListener{

    Button b[]=new Button[36];
    int s;
    int i,j,k, tr;
    static int next=0;
    Chronometer chronometer;
    MediaPlayer mp,mp1;
    int a[]=new int[35];
    long timestop;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_sixbysix_o);
        b[0]=(Button)findViewById(R.id.sxS1);
        b[1]=(Button)findViewById(R.id.sxS2);
        b[2]=(Button)findViewById(R.id.sxS3);
        b[3]=(Button)findViewById(R.id.sxS4);
        b[4]=(Button)findViewById(R.id.sxS5);
        b[5]=(Button)findViewById(R.id.sxS6);
        b[6]=(Button)findViewById(R.id.sxS7);
        b[7]=(Button)findViewById(R.id.sxS8);
        b[8]=(Button)findViewById(R.id.sxS9);
        b[9]=(Button)findViewById(R.id.sxS10);
        b[10]=(Button)findViewById(R.id.sxS11);
        b[11]=(Button)findViewById(R.id.sxS12);
        b[12]=(Button)findViewById(R.id.sxS13);
        b[13]=(Button)findViewById(R.id.sxS14);
        b[14]=(Button)findViewById(R.id.sxS15);
        b[15]=(Button)findViewById(R.id.sxS16);
        b[16]=(Button)findViewById(R.id.sxS17);
        b[17]=(Button)findViewById(R.id.sxS18);
        b[18]=(Button)findViewById(R.id.sxS19);
        b[19]=(Button)findViewById(R.id.sxS20);
        b[20]=(Button)findViewById(R.id.sxS21);
        b[21]=(Button)findViewById(R.id.sxS22);
        b[22]=(Button)findViewById(R.id.sxS23);
        b[23]=(Button)findViewById(R.id.sxS24);
        b[24]=(Button)findViewById(R.id.sxS25);
        b[25]=(Button)findViewById(R.id.sxS26);
        b[26]=(Button)findViewById(R.id.sxS27);
        b[27]=(Button)findViewById(R.id.sxS28);
        b[28]=(Button)findViewById(R.id.sxS29);
        b[29]=(Button)findViewById(R.id.sxS30);
        b[30]=(Button)findViewById(R.id.sxS31);
        b[31]=(Button)findViewById(R.id.sxS32);
        b[32]=(Button)findViewById(R.id.sxS33);
        b[33]=(Button)findViewById(R.id.sxS34);
        b[34]=(Button)findViewById(R.id.sxS35);
        b[35]=(Button)findViewById(R.id.sxS36);
        chronometer=(Chronometer) findViewById(R.id.chronometer);
        mp1 = MediaPlayer.create(getApplicationContext(), R.raw.beep);
        mp = MediaPlayer.create(getApplicationContext(), R.raw.push);
        try {
            for (i = 0; i < 36; i++)
                b[i].setOnClickListener(this);
            for (i = 0; i < 35; i++)
            {
                Random r=new Random();
                k=r.nextInt(100);
                a[i]=k;
                b[i].setText(""+k);
            }
            for(i=0;i<35-1;i++)
            {
                for(j=0;j<35-i-1;j++)
                {
                    if(a[j]<a[j+1])
                    {
                        int temp=a[j];
                        a[j]=a[j+1];
                        a[j+1]=temp;
                    }
                }
            }
           /* k = r.nextInt(13);
            for (i = 0; i < 24; i++) {
                if (a[k][i] == 1) {
                    b[i].setText("" + a[k][i]);
                    b[i].setBackgroundColor(Color.BLUE);
                    b[i].setTextColor(Color.BLUE);
                } else if (a[k][i] == 2) {

                    b[i].setText("" + a[k][i]);
                    b[i].setBackgroundColor(Color.GREEN);
                    b[i].setTextColor(Color.GREEN);
                } else if (a[k][i] == 3) {
                    b[i].setText("" + a[k][i]);
                    b[i].setBackgroundColor(Color.parseColor("#ffa500"));  //orange
                    b[i].setTextColor(Color.parseColor("#ffa500"));        //orange
                } else if (a[k][i] == 4) {
                    b[i].setText("" + a[k][i]);
                    b[i].setBackgroundColor(Color.RED);
                    b[i].setTextColor(Color.RED);

                } else {
                    b[i].setText("" + a[k][i]);
                    b[i].setBackgroundColor(Color.YELLOW);
                    b[i].setTextColor(Color.YELLOW);
                }
            }*/
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
                        tr=0;
                    }
                }
            }

            for (i=0;i<35; i++)
            {
                j=Integer.parseInt(b[i].getText().toString());
                if(a[i]!=j)
                    break;
                else
                    tr++;
            }
            if (tr==35)
            {
                for (int p = 0; p < 36; p++) {
                    b[p].setText("");
                    b[p].setBackgroundColor(Color.parseColor("#00000000"));
                }
                chronometer.stop();
                long time = SystemClock.elapsedRealtime() - chronometer.getBase();
                time=time*100+63;
                startActivity(new Intent(SixbysixO.this, Questions.class).putExtra("TIME", time));
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
        //cs3.setText(sp.getString("a6", " - "));
        timestop=0;
        timestop=chronometer.getBase() - SystemClock.elapsedRealtime(); chronometer.stop();

        // Create custom dialog object
        final Dialog dialog = new Dialog(SixbysixO.this,android.R.style.Theme_DeviceDefault_Light_NoActionBar_TranslucentDecor);
        // Include dialog.xml file
        dialog.setContentView(R.layout.pauselog);
        // Set dialog title Theme_Material_Light_NoActionBar_TranslucentDecor
        dialog.setTitle("Custom Dialog");
        dialog.setCancelable(false);

        // set values for custom dialog components - text, image and button
        TextView time = (TextView) dialog.findViewById(R.id.st);
        time.setText(sp.getString("a6", " - "));
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
        final Dialog dialog = new Dialog(SixbysixO.this,android.R.style.Theme_DeviceDefault_Light_NoActionBar_TranslucentDecor);
        // Include dialog.xml file
        dialog.setContentView(R.layout.helplog);
        // Set dialog title Theme_Material_Light_NoActionBar_TranslucentDecor
        dialog.setTitle("Custom Dialog");
        dialog.setCancelable(true);

        // set values for custom dialog components - text, image and button
        ImageView imv=(ImageView) dialog.findViewById(R.id.imv);
        imv.setImageResource(R.drawable.sixs);
        TextView how = (TextView) dialog.findViewById(R.id.howto);
        TextView info = (TextView) dialog.findViewById(R.id.info);
        info.setText("6X6- Numbers level \n Align The Numbers \nHorizontally In Descending Order");
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
                b[s1].setBackgroundColor(Color.parseColor("#302013"));  //brown
                b[s1].setTextColor(Color.parseColor("#eac086"));    //skin
            }
            else
            {
                b[s1].setBackgroundColor(Color.parseColor("#eac086"));
                b[s1].setTextColor(Color.parseColor("#302013"));
            }
        }
        else
        {
            b[s1].setText(""+j1);
            if(s1%2==0)
            {
                b[s1].setBackgroundColor(Color.parseColor("#eac086"));
                b[s1].setTextColor(Color.parseColor("#302013"));
            }
            else
            {
                b[s1].setBackgroundColor(Color.parseColor("#302013"));
                b[s1].setTextColor(Color.parseColor("#eac086"));
            }
        }
        b[i1].setText("");
        b[i1].setBackgroundColor(Color.parseColor("#00000000"));

        tr=0;
    }
    public void onBackPressed()
    {
        super.onBackPressed();
        startActivity(new Intent(SixbysixO.this, Levels3.class));
        finish();
    }
}
