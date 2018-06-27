package com.Game.Rowdy.GameofKnowledge;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.view.WindowManager;
import android.widget.RadioButton;
import android.widget.*;

import java.util.Random;

public class McqQ extends Activity implements RadioGroup.OnCheckedChangeListener{
    Mcq q;
    String ques,op1,op2,op3;
    int ans,level,i;
    long steps;
    RadioGroup rg;
    RadioButton a,b,c;
    TextView qt,at,stps;
    Button twoinone;
    MediaPlayer mp;
    FullAds fullAds;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_mcq_q);
        steps =getIntent().getLongExtra("steps", 0);
        stps=(TextView) findViewById(R.id.Stps);
        Random r=new Random();
        level=(int)steps%10;
        steps/=10;
        stps.setText("Steps taken:  "+steps);
        q=new Mcq();
        switch(level)
        {
            case 3: i=r.nextInt(3);
                ques=q.question(i);op1=q.op1(i);op2=q.op2(i);op3=q.op3(i);ans=q.answer(i);
                break;
            case 4: i=r.nextInt(3)+3;
                ques=q.question(i);op1=q.op1(i);op2=q.op2(i);op3=q.op3(i);ans=q.answer(i);
                break;
            case 5: i=r.nextInt(3)+6;
                ques=q.question(i);op1=q.op1(i);op2=q.op2(i);op3=q.op3(i);ans=q.answer(i);
                break;
            case 6: i=r.nextInt(3)+9;
                ques=q.question(i);op1=q.op1(i);op2=q.op2(i);op3=q.op3(i);ans=q.answer(i);;
                break;
        }

        twoinone=(Button)findViewById(R.id.twoinone);
        qt=(TextView) findViewById(R.id.qt);
        at=(TextView) findViewById(R.id.at);
        rg=(RadioGroup) findViewById(R.id.rg);
        a=(RadioButton) findViewById(R.id.op1);
        b=(RadioButton) findViewById(R.id.op2);
        c=(RadioButton) findViewById(R.id.op3);
        rg.setOnCheckedChangeListener(this);
        qt.setText(ques);
        a.setText(op1);
        b.setText(op2);
        c.setText(op3);
    }
    public void correct()
    {
        mp = MediaPlayer.create(getApplicationContext(), R.raw.correct);
        if(Settings.onoff==1)
            mp.start();
    }
    @Override
    public void onCheckedChanged(RadioGroup group, int i)
    {
        if(i==a.getId()&&ans==1)
        {   correct();
            at.setText("CORRECT");
            at.setTextColor(Color.GREEN);
        }
        else if(i==b.getId()&&ans==2) {
            correct();
            at.setText("CORRECT");
            at.setTextColor(Color.GREEN);
        }
        else if(i==c.getId()&&ans==3)
        {
            correct();
            at.setText("CORRECT");
            at.setTextColor(Color.GREEN);
        }
        else {
            mp = MediaPlayer.create(getApplicationContext(), R.raw.wrong);
            if(Settings.onoff==1)
                mp.start();
            at.setText("WRONG");
            at.setTextColor(Color.RED);
        }
        twoinone.setText("CONTINUE>>");
        a.setClickable(false);
        b.setClickable(false);
        c.setClickable(false);
    }

    public void cont(View v)
    {
        String temp=at.getText().toString();
        if(temp.equals("CORRECT"))
        {
            calc(level,true);
        }
        else if(temp.equals("WRONG"))
        {
            calc(level,false);
        }
        store(steps,level);
        fullAds=new FullAds();
        fullAds.startAdd(McqQ.this);
        startActivity(new Intent(McqQ.this, Tablevels.class).putExtra("Goto",level));
        finish();
    }
    public void store(long st,int l)
    {
        String temp;
        SharedPreferences sp = getSharedPreferences("key", 0);
        SharedPreferences.Editor sedt = sp.edit();
        if(l==3)
        {
            if(sp.getString("Picp1", "-").toString().equals("-"))
                sedt.putString("Picp1",""+st );
            else
            {   temp=sp.getString("Picp1", "-").toString();
                if(Integer.parseInt(temp)>st)
                    sedt.putString("Picp1",""+st);
            }
        }
        else if(l==4)
        {
            if(sp.getString("Picp2", "-").toString().equals("-"))
                sedt.putString("Picp2",""+st );
            else
            {   temp=sp.getString("Picp2", "-").toString();
                if(Integer.parseInt(temp)>st)
                    sedt.putString("Picp2",""+st);
            }}
        else if(l==5)
        {
            if(sp.getString("Picp3", "-").toString().equals("-"))
                sedt.putString("Picp3",""+st );
            else
            {   temp=sp.getString("Picp3", "-").toString();
                if(Integer.parseInt(temp)>st)
                    sedt.putString("Picp3",""+st );
            }}
        else if(l==6)
        {
            if(sp.getString("Picp4", "-").toString().equals("-"))
                sedt.putString("Picp4",""+st );
            else
            {   temp=sp.getString("Picp4", "-").toString();
                if(Integer.parseInt(temp)>st)
                    sedt.putString("Picp4",""+st );
            }}
        sedt.commit();
    }
    public void calc(int l,boolean tf)
    {
        SharedPreferences sp = getSharedPreferences("key", 0);
        int marks=Integer.parseInt(sp.getString("marks", "0"));
        if(l==3)
        {
            if(tf){marks+=10;}
            else{marks-=5;}
        }
        else if(l==4)
        {
            if(tf){marks+=20;}
            else{marks-=10;}
        }
        else if(l==5)
        {
            if(tf){marks+=30;}
            else{marks-=15;}
        }
        else if(l==6)
        {
            if(tf){marks+=40;}
            else{marks-=20;}
        }
        SharedPreferences.Editor sedt = sp.edit();
        sedt.putString("marks", ""+marks);
        sedt.commit();
    }
    public void onBackPressed()
    {
        super.onBackPressed();
        startActivity(new Intent(McqQ.this, Tablevels.class).putExtra("Goto",level));
        finish();
    }
}
