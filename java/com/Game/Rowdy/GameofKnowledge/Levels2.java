package com.Game.Rowdy.GameofKnowledge;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class Levels2 extends Activity implements View.OnClickListener {

    Button b1,b2,b3;
    static TextView t;
    MediaPlayer mp;
    FullAds fullAds;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_levels2);
        b1=(Button) findViewById(R.id.clevel2);
        b2=(Button) findViewById(R.id.nlevel2);
        b3=(Button) findViewById(R.id.alevel2);
        t=(TextView) findViewById(R.id.text2);
        int l=getIntent().getIntExtra("Level2",0);
        if(l==53)
        {
            t.setText("45");
            saveData();
            startActivity(new Intent(Levels2.this,GameLevels.class).putExtra("Open",l));
            finish();
        }
         if(FivebyfiveC.next==2)
        {
            t.setText("4");
            saveData();
        }
         if(FivebyfiveN.next==3)
        {
            t.setText("45");
            saveData();
        }
        restoreData();
        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
        b3.setOnClickListener(this);
        SharedPreferences sharedPrefs = getSharedPreferences("Buttons", MODE_PRIVATE);
        b2.setClickable(sharedPrefs.getBoolean("Button4", false));
        if(sharedPrefs.getBoolean("Button4", false))
            b2.setText("");
        b3.setClickable(sharedPrefs.getBoolean("Button5", false));
        if(sharedPrefs.getBoolean("Button5", false))
        { b2.setText("");
            b3.setText("");}
    }
    private void saveData()
    {
        SharedPreferences sp = getSharedPreferences("key", 0);
        SharedPreferences.Editor sedt = sp.edit();
        sedt.putString("Text2", t.getText().toString());
        sedt.commit();
    }
    private void restoreData()
    {
        SharedPreferences sp = getSharedPreferences("key", 0);
        SharedPreferences.Editor editor = getSharedPreferences("Buttons", MODE_PRIVATE).edit();
        t.setText(sp.getString("Text2", ""));
        if(t.getText().toString().equals("4"))
        {
            b2.setClickable(true);
            b2.setText("");
            editor.putBoolean("Button4", true);
        }
        else if(t.getText().toString().equals("45"))
        {
            b3.setClickable(true);
            b3.setText("");
            b2.setClickable(true);
            b2.setText("");
            editor.putBoolean("Button4", true);
            editor.putBoolean("Button5", true);
        }
        else
        {
            b2.setClickable(false);
            b3.setClickable(false);
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
            startActivity(new Intent(Levels2.this, FivebyfiveC.class));
            finish();
        }
        else if(v==b2)
        {
            startActivity(new Intent(Levels2.this, FivebyfiveN.class));
            finish();
        }
        else
        {
            startActivity(new Intent(Levels2.this, FivebyfivA.class));
            finish();
        }
    }
    public void onBackPressed()
    {
        super.onBackPressed();
        fullAds=new FullAds();
        fullAds.startAdd(Levels2.this);
        startActivity(new Intent(Levels2.this, GameLevels.class));
        finish();
    }

}
