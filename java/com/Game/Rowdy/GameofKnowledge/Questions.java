package com.Game.Rowdy.GameofKnowledge;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;

public class Questions extends Activity
{
    final Context context = this;
    TextView ques,result,Time;
    String getQ,getA;
    String total;
    int level;
    long time;
    int i;
    MediaPlayer mp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_questions);
        Time=(TextView) findViewById(R.id.st);
        ques=(TextView) findViewById(R.id.question);
        result = (TextView) findViewById(R.id.result);
        Random r=new Random();
        qanda p=new qanda();
        time =getIntent().getLongExtra("TIME", 0);
        level=(int)time%100;
        time/=100;
        switch(level)
        {
            case 31:    i=r.nextInt(4);
                        getQ=p.ques3(i);
                        getA=p.ans3(i);
                        break;
            case 32:    i=r.nextInt(4)+4;
                        getQ=p.ques3(i);
                        getA=p.ans3(i);
                        break;
            case 33:    i=r.nextInt(4)+8;
                        getQ=p.ques3(i);
                        getA=p.ans3(i);
                        break;
            case 41:    i=r.nextInt(4);
                        getQ=p.ques4(i);
                        getA=p.ans4(i);
                         break;
            case 42:    i=r.nextInt(4)+4;
                        getQ=p.ques4(i);
                        getA=p.ans4(i);
                        break;
            case 43:    i=r.nextInt(4)+8;
                        getQ=p.ques4(i);
                        getA=p.ans4(i);
                        break;
            case 51:    i=r.nextInt(4);
                        getQ=p.ques5(i);
                        getA=p.ans5(i);
                        break;
            case 52:    i=r.nextInt(4)+4;
                        getQ=p.ques5(i);
                        getA=p.ans5(i);
                        break;
            case 53:    i=r.nextInt(4)+8;
                        getQ=p.ques5(i);
                        getA=p.ans5(i);
                        break;
            case 61:    i=r.nextInt(4);
                        getQ=p.ques6(i);
                        getA=p.ans6(i);
                        break;
            case 62:    i=r.nextInt(4)+4;
                        getQ=p.ques6(i);
                        getA=p.ans6(i);
                        break;
            case 63:    i=r.nextInt(3)+8;
                        getQ=p.ques6(i);
                        getA=p.ans6(i);
                        break;

        }
        ques.setText(getQ);
        dialogBox(getQ);
        timetaken(time);
    }

    public void timetaken(long time)
    {
        int hours = (int) (time / 3600000);
        int minutes = (int) (time- hours * 3600000) / 60000;
        int seconds = (int) (time- hours * 3600000 - minutes * 60000) / 1000;
        if(seconds>9&&minutes>9)
            total = "" + minutes + ":" + seconds;
        else if(seconds<10&&minutes>9)
             total = "" + minutes + ":0" + seconds;
        else if(minutes<10&&seconds<10)
             total = "" + "0"+minutes + ":0" + seconds;
        else if(minutes<10&&seconds>9)
            total=""+"0"+minutes+":"+seconds;
        else if(minutes==0)
        {   if(seconds<10)
             total = "" + "00:0" + seconds;
            else
            total = "" + "00:" + seconds;
        }
        Time.setText(total);
        Scoreboard.time=total;
    }
    public void dialogBox(String q)
    {

        // get dialog.xml view
        LayoutInflater li = LayoutInflater.from(context);
        View dialogView = li.inflate(R.layout.dialog,null);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);

        // set dialog.xml to alertdialog builder
        alertDialogBuilder.setView(dialogView);

        final EditText userInput = (EditText) dialogView.findViewById(R.id.answer);
        TextView question=(TextView) dialogView.findViewById(R.id.ques);
        question.setText(q);
        // set dialog message
        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton("SUMBIT",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int id) {
                                // get user input and set it to result
                                // edit text
                                result.setText(userInput.getText());
                                check(getA);
                            }
                        })
                .setNegativeButton("DON'T KNOW",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int id) {
                                Scoreboard.mark=0;
                                dialog.cancel();
                            }
                        });

        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();
        // show it
        alertDialog.show();

    }
    public void check(String a)
    {
        String rep=result.getText().toString();
        rep= rep.replaceAll("\\s", "");
        rep= rep.replaceAll("[^a-z\\sA-Z0-9]","");
        if(rep.equalsIgnoreCase(a))
        {
            mp = MediaPlayer.create(getApplicationContext(), R.raw.correct);
            if(Settings.onoff==1)
                mp.start();
            String temp=result.getText().toString();
            temp=temp.replaceAll("\\s","");
            temp=temp.toUpperCase();
            result.setText(temp);
            result.setTextColor(Color.GREEN);
            if(level/10==3)
                Scoreboard.mark=10;
            else if(level/10==4)
                Scoreboard.mark=20;
            else if(level/10==5)
                Scoreboard.mark=30;
            else if(level/10==6)
                Scoreboard.mark=40;
        }
        else if(rep.equals(""))
        {
            String temp;
            temp="DIDN'T KNOW";
            result.setText(temp);
            result.setTextColor(Color.BLACK);
                Scoreboard.mark=0;
        }
        else
        {
            mp = MediaPlayer.create(getApplicationContext(), R.raw.wrong);
            if(Settings.onoff==1)
                mp.start();
            String temp=result.getText().toString();
            temp=temp.toUpperCase();
            result.setText(temp);
            result.setTextColor(Color.RED);
            if(level/10==3)
                Scoreboard.mark=-5;
            if(level/10==4)
                Scoreboard.mark=-10;
            if(level/10==5)
                Scoreboard.mark=-15;
            if(level/10==6)
                Scoreboard.mark=-20;
        }
    }
    public void Cont(View v)
    {
        startActivity(new Intent(Questions.this,Scoreboard.class).putExtra("Hello",level));
        finish();
    }
    public void onBackPressed()
    {
        super.onBackPressed();
        startActivity(new Intent(Questions.this, GameLevels.class));
        finish();
    }
}
