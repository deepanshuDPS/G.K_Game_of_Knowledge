package com.Game.Rowdy.GameofKnowledge;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class Settings extends Activity implements RadioGroup.OnCheckedChangeListener,View.OnClickListener{

    RadioGroup rg;
    RadioButton on,off;
    Button ins,score;
    static int onoff=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_settings);
        int i=getIntent().getIntExtra("Start",0);
        rg=(RadioGroup) findViewById(R.id.ronoff);
        on=(RadioButton) findViewById(R.id.on);
        off=(RadioButton) findViewById(R.id.off);
        ins=(Button) findViewById(R.id.ins);
        score=(Button) findViewById(R.id.score);
        score.setOnClickListener(this);
        ins.setOnClickListener(this);
        rg.setOnCheckedChangeListener(this);
        SharedPreferences sharedPrefs = getSharedPreferences("checked", MODE_PRIVATE);
        if(sharedPrefs.getBoolean("NameOfThingToSave", true))
        { onoff=1; on.setChecked(true);}
        else
        {  onoff=0; off.setChecked(true);}
        if(i==1)
        {
            //for starting the game
            startActivity(new Intent(Settings.this, Instructions.class).putExtra("Start",1));
            finish();
        }
    }
    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId)
    {   SharedPreferences.Editor editor = getSharedPreferences("checked", MODE_PRIVATE).edit();
        if(group==rg)
        {
            if(checkedId==on.getId())
            { onoff=1; editor.putBoolean("NameOfThingToSave", true);}
            else if(checkedId==off.getId())
            {onoff=0; editor.putBoolean("NameOfThingToSave", false);}
            editor.commit();
        }
    }


    @Override
    public void onClick(View v)
    {
        if(v==score)
        {
            startActivity(new Intent(Settings.this,Scoreboard.class));
        }
        else
        {
            startActivity(new Intent(Settings.this, Instructions.class).putExtra("Start",1));
        }
    }
}
