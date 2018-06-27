package com.Game.Rowdy.GameofKnowledge;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

public class Instructions extends Activity implements View.OnClickListener{
    Button gi,ngi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_instructions);
        int i=getIntent().getIntExtra("Start",0);
        gi=(Button) findViewById(R.id.gotit);
        ngi=(Button) findViewById(R.id.notgotit);
        if(i==1)
        {
            gi.setVisibility(View.GONE);
            ngi.setClickable(false);
            ngi.setVisibility(View.GONE);
            gi.setClickable(false);
        }
        gi.setOnClickListener(this);
        ngi.setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
        if(v==gi)
        {
            SharedPreferences.Editor editor = getSharedPreferences("checked", MODE_PRIVATE).edit();
            editor.putBoolean("info", true);
            editor.commit();
            startActivity(new Intent(Instructions.this,GameLevels.class).putExtra("askforrate",true));
        }
        else
        {
            startActivity(new Intent(Instructions.this,GameLevels.class).putExtra("askforrate",true));
        }
        finish();
    }
}
