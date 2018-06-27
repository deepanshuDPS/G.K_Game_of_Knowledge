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

public class Levels3 extends Activity implements View.OnClickListener{

    Button b1,b2,b3;
    static TextView t;
    MediaPlayer mp;
    FullAds fullAds;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_levels3);
        b1=(Button) findViewById(R.id.clevel3);
        b2=(Button) findViewById(R.id.nlevel3);
        b3=(Button) findViewById(R.id.alevel3);
        t=(TextView) findViewById(R.id.text3);
        int l=getIntent().getIntExtra("Level3",0);
        if(l==63)
        {
            t.setText("56");
            saveData();
            startActivity(new Intent(Levels3.this,GameLevels.class).putExtra("Open",l));
            finish();
        }
        if(SixbysixC.next==2)
        {
            t.setText("5");
            saveData();
        }
        if(SixbysixN.next==3)
        {
            t.setText("56");
            saveData();
        }
        restoreData();
        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
        b3.setOnClickListener(this);
        SharedPreferences sharedPrefs = getSharedPreferences("Buttons", MODE_PRIVATE);
        b2.setClickable(sharedPrefs.getBoolean("Button6", false));
        b3.setClickable(sharedPrefs.getBoolean("Button7", false));
    }

    private void saveData()
    {
        SharedPreferences sp = getSharedPreferences("key", 0);
        SharedPreferences.Editor sedt = sp.edit();
        sedt.putString("Text3", t.getText().toString());
        sedt.commit();
    }
    private void restoreData()
    {
        SharedPreferences sp = getSharedPreferences("key", 0);
        SharedPreferences.Editor editor = getSharedPreferences("Buttons", MODE_PRIVATE).edit();
        t.setText(sp.getString("Text3", ""));
        if(t.getText().toString().equals("5"))
        {
            b2.setClickable(true);
            b2.setText("");
            editor.putBoolean("Button6", true);
        }
        else if(t.getText().toString().equals("56"))
        {
            b3.setClickable(true);
            b3.setText("");
            b2.setClickable(true);
            b2.setText("");
            editor.putBoolean("Button6", true);
            editor.putBoolean("Button7", true);
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
            startActivity(new Intent(Levels3.this, SixbysixC.class));
            finish();
        }
        else if(v==b2)
        {
            startActivity(new Intent(Levels3.this, SixbysixN.class));
            finish();
        }
        else
        {
            startActivity(new Intent(Levels3.this, SixbysixO.class));
            finish();
        }
    }
    public void onBackPressed()
    {
        super.onBackPressed();
        fullAds=new FullAds();
        fullAds.startAdd(Levels3.this);
        startActivity(new Intent(Levels3.this, GameLevels.class));
        finish();
    }
}
