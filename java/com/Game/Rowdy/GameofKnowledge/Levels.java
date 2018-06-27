package com.Game.Rowdy.GameofKnowledge;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class Levels extends Activity implements View.OnClickListener {

    Button b1,b2,b3;
    static TextView t;
    MediaPlayer mp;
    FullAds fullAds;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_levels3x3);
        b1=(Button) findViewById(R.id.clevel);
        b2=(Button) findViewById(R.id.nlevel);
        b3=(Button) findViewById(R.id.alevel);
        t=(TextView) findViewById(R.id.text);
        int l=getIntent().getIntExtra("Level",0);
        if(l==33)
        {
            t.setText("23");
            saveData();
            startActivity(new Intent(Levels.this, GameLevels.class).putExtra("Open", l));
            finish();
        }
        if(ThreeByThreeC.next==2)
        {
            t.setText("2");
            saveData();
        }
        if(ThreeByThreeN.next==3)
        {
            t.setText("23");
            saveData();
        }
        restoreData();
        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
        b3.setOnClickListener(this);
        SharedPreferences sharedPrefs = getSharedPreferences("Buttons", MODE_PRIVATE);
        b2.setClickable(sharedPrefs.getBoolean("Button", false));
        if(sharedPrefs.getBoolean("Button", false))
            b2.setText("");
        b3.setClickable(sharedPrefs.getBoolean("Button1", false));
        if(sharedPrefs.getBoolean("Button1", false))
        { b2.setText("");
            b3.setText("");}
    }
    private void saveData()
    {
        SharedPreferences sp = getSharedPreferences("key", 0);
        SharedPreferences.Editor sedt = sp.edit();
        sedt.putString("Text0", t.getText().toString());
        sedt.commit();
    }
    private void restoreData()
    {
        SharedPreferences sp = getSharedPreferences("key", 0);
        SharedPreferences.Editor editor = getSharedPreferences("Buttons", MODE_PRIVATE).edit();
        t.setText(sp.getString("Text0", ""));
        if(t.getText().toString().equals("2"))
        {
            b2.setClickable(true);
            b2.setText("");
            editor.putBoolean("Button", true);
        }
        else if(t.getText().toString().equals("23"))
        {
            b3.setClickable(true);
            b3.setText("");
            b2.setClickable(true);
            b2.setText("");
            editor.putBoolean("Button", true);
            editor.putBoolean("Button1", true);
        }
        editor.commit();
        saveData();
    }
    @Override
    public void onClick(View v) {

        mp = MediaPlayer.create(getApplicationContext(), R.raw.click);
        if(Settings.onoff==1)
            mp.start();
        if(v==b1)
        {
            startActivity(new Intent(Levels.this,ThreeByThreeC.class));
            finish();
        }
        else if(v==b2)
        {
            startActivity(new Intent(Levels.this, ThreeByThreeN.class));
            finish();
        }
        else
        {
            startActivity(new Intent(Levels.this,ThreeByThreeA.class));
            finish();
        }
    }
    public void onBackPressed()
    {
        super.onBackPressed();
        fullAds=new FullAds();
        fullAds.startAdd(Levels.this);
        startActivity(new Intent(Levels.this, GameLevels.class));
        finish();
    }

}
