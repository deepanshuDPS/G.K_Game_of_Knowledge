package com.Game.Rowdy.GameofKnowledge;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import com.google.android.gms.ads.InterstitialAd;

public class Scoreboard extends Activity {

    static TextView c3, n3, a3, c4, n4, a4, c5, n5, a5, c6, n6,a6,markscore;
    static String time;
    int i,m;
    static int mark=0;
    AlertDialog.Builder abd;
    FullAds fullAds;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_scoreboard);
        c3 = (TextView) findViewById(R.id.c3);
        c4 = (TextView) findViewById(R.id.c4);
        c5 = (TextView) findViewById(R.id.c5);
        c6 = (TextView) findViewById(R.id.c6);
        n3 = (TextView) findViewById(R.id.n3);
        n4 = (TextView) findViewById(R.id.n4);
        n5 = (TextView) findViewById(R.id.n5);
        n6 = (TextView) findViewById(R.id.n6);
        a3 = (TextView) findViewById(R.id.a3);
        a4 = (TextView) findViewById(R.id.a4);
        a5 = (TextView) findViewById(R.id.a5);
        a6 = (TextView) findViewById(R.id.a6);
        markscore=(TextView) findViewById(R.id.marks);
        i = getIntent().getIntExtra("Hello", 0);
        restoreData();
        if (i != 0)
            scoresave(time, i);
        saveData();
        fullAds=new FullAds();
        fullAds.startAdd(Scoreboard.this);
    }
    String calculate(String ptime,String ntime)
    {
        int min,sec;
        int min1=Integer.parseInt(ptime.substring(0, 2));
        int min2=Integer.parseInt(ntime.substring(0,2));
        int sec1=Integer.parseInt(ptime.substring(3,5));
        int sec2=Integer.parseInt(ntime.substring(3,5));
        if(min1>=min2)
        min=min2;
        else
        min=min1;
        if(sec1>=sec2)
            sec=sec2;
        else
        sec=sec1;
        if(sec>9&&min>9)
            ntime = "" + min + ":" + sec;
        else if(sec<10&&min>9)
            ntime = "" + min + ":0" + sec;
        else if(min<10&&sec<10)
            ntime = "" + "0"+min + ":0" + sec;
        else if(min<10&&sec>9)
            ntime=""+"0"+min+":"+sec;
        else if(min==0)
        {   if(sec<10)
            ntime = "" + "00:0" + sec;
        else
            ntime = "" + "00:" + sec;
        }
        return ntime;
    }
    private void saveData()
    {
        SharedPreferences sp = getSharedPreferences("key", 0);
        SharedPreferences.Editor sedt = sp.edit();
        sedt.putString("marks", markscore.getText().toString());
        sedt.putString("c3", c3.getText().toString());
        sedt.putString("n3", n3.getText().toString());
        sedt.putString("a3", a3.getText().toString());
        sedt.putString("c4", c4.getText().toString());
        sedt.putString("n4", n4.getText().toString());
        sedt.putString("a4", a4.getText().toString());
        sedt.putString("c5", c5.getText().toString());
        sedt.putString("n5", n5.getText().toString());
        sedt.putString("a5", a5.getText().toString());
        sedt.putString("c6", c6.getText().toString());
        sedt.putString("n6", n6.getText().toString());
        sedt.putString("a6", a6.getText().toString());
        sedt.commit();
    }
    private void restoreData()
    {
        SharedPreferences sp = getSharedPreferences("key", 0);
        markscore.setText(sp.getString("marks", "0"));
        c3.setText(sp.getString("c3", " - "));
        n3.setText(sp.getString("n3", " - "));
        a3.setText(sp.getString("a3", " - "));
        c4.setText(sp.getString("c4", " - "));
        n4.setText(sp.getString("n4", " - "));
        a4.setText(sp.getString("a4", " - "));
        c5.setText(sp.getString("c5", " - "));
        n5.setText(sp.getString("n5", " - "));
        a5.setText(sp.getString("a5", " - "));
        c6.setText(sp.getString("c6", " - "));
        n6.setText(sp.getString("n6", " - "));
        a6.setText(sp.getString("a6", " - "));
    }

    public void shareit(View v)
    {
        try {
            if(!a4.getText().toString().equals(" - "))
            {startActivity(new Intent(Scoreboard.this, SharePage.class)); finish();}
            else
            {
                abd=new AlertDialog.Builder(this);
                abd.setCancelable(true);
                abd.setMessage("You have to complete minimum 6 levels to share your scoreboard...!!!");
                abd.setPositiveButton("Play", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (!a3.getText().toString().equals(" - "))
                            startActivity(new Intent(Scoreboard.this, Levels1.class));
                        else
                            startActivity(new Intent(Scoreboard.this, Levels.class));
                        finish();
                    }
                });
                abd.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {@Override public void onClick(DialogInterface dialog, int which) {}});
                AlertDialog alertDialog = abd.create();
                alertDialog.setTitle("CANNOT SHARE");
                alertDialog.show();
            }
        }
        catch(Exception e){}
    }

    public void scoresave(String time, int i) {
        if (i==31) {
            if(c3.getText().toString().equals(" - ")||c3.getText().toString().equals(""))
                c3.setText(time);
            else {
                time= calculate(c3.getText().toString(),time);
                c3.setText(time);
            }
        }
       else if(i==32)
        {
            if(n3.getText().toString().equals(" - ")||n3.getText().toString().equals(""))
                n3.setText(time);
            else {
                time= calculate(n3.getText().toString(),time);
                n3.setText(time);
            }
        }
        else if(i==33)
        {
            if(a3.getText().toString().equals(" - ")||a3.getText().toString().equals(""))
                a3.setText(time);
            else {
                time= calculate(a3.getText().toString(),time);
                a3.setText(time);
            }
        }
        else if (i==41) {
            if(c4.getText().toString().equals(" - ")||c4.getText().toString().equals(""))
                c4.setText(time);
            else {
                time= calculate(c4.getText().toString(),time);
                c4.setText(time);
            }
        }
        else if(i==42)
        {
            if(n4.getText().toString().equals(" - ")||n4.getText().toString().equals(""))
                n4.setText(time);
            else {
                time= calculate(n4.getText().toString(),time);
                n4.setText(time);
            }
        }
       else if(i==43)
        {
            if(a4.getText().toString().equals(" - ")||a4.getText().toString().equals(""))
                a4.setText(time);
            else {
                time= calculate(a4.getText().toString(),time);
                a4.setText(time);
            }
        }
       else  if (i==51) {
            if(c5.getText().toString().equals(" - ")||c5.getText().toString().equals(""))
                c5.setText(time);
            else {
                time= calculate(c5.getText().toString(),time);
                c5.setText(time);
            }
        }
        else if(i==52)
        {
            if(n5.getText().toString().equals(" - ")||n5.getText().toString().equals(""))
                n5.setText(time);
            else {
                time= calculate(n5.getText().toString(),time);
                n5.setText(time);
            }
        }
        else if(i==53)
        {
            if(a5.getText().toString().equals(" - ")||a5.getText().toString().equals(""))
                a5.setText(time);
            else {
                time= calculate(a5.getText().toString(),time);
                a5.setText(time);
            }
        }
        else if (i==61) {
            if(c6.getText().toString().equals(" - ")||c6.getText().toString().equals(""))
                c6.setText(time);
            else {
                time= calculate(c6.getText().toString(),time);
                c5.setText(time);
            }
        }
       else if(i==62)
        {
            if(n6.getText().toString().equals(" - ")||n6.getText().toString().equals(""))
                n6.setText(time);
            else {
                time= calculate(n6.getText().toString(),time);
                n6.setText(time);
            }
        }
       else  if(i==63)
        {
            if(a6.getText().toString().equals(" - ")||a6.getText().toString().equals(""))
                a6.setText(time);
            else {
                time= calculate(a6.getText().toString(),time);
                a6.setText(time);
            }
        }
        m = Integer.parseInt(markscore.getText().toString());
        m = m + mark;
        markscore.setText("" + m);
        saveData();
        // Show Add
       // fullAds=new FullAds();
       // fullAds.startAdd(Scoreboard.this);
        //Go to next activity
        if(i/10==3)
        startActivity(new Intent(Scoreboard.this, Levels.class).putExtra("Level",i));
        else if(i/10==4)
            startActivity(new Intent(Scoreboard.this, Levels1.class).putExtra("Level1", i));
            else if(i/10==5)
                startActivity(new Intent(Scoreboard.this, Levels2.class).putExtra("Level2", i));
                else if(i/10==6)
                    startActivity(new Intent(Scoreboard.this, Levels3.class).putExtra("Level3", i));

        finish();
    }

}