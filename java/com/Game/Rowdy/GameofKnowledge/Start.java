package com.Game.Rowdy.GameofKnowledge;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.app.Activity;
import android.view.WindowManager;
import android.widget.ImageView;
import android.os.Handler;


public class Start extends Activity {
    //private static final String TAG = "AnimationStarter";
    //ObjectAnimator textColorAnim;
    ImageView im;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_start);
        //bt=(TextView) findViewById(R.id.bt);

        final Handler handler = new Handler();
        AnimationDrawable animation = new AnimationDrawable();
        animation.addFrame(getResources().getDrawable(R.drawable.gk), 400);
        animation.addFrame(getResources().getDrawable(R.drawable.gk1),400 );
        animation.addFrame(getResources().getDrawable(R.drawable.gk2), 400);
        animation.setOneShot(true);

        im=(ImageView) findViewById(R.id.imageView);
        im.setBackgroundDrawable(animation);
        // start the animation!
        animation.start();
        handler.postDelayed(new Runnable() {
            public void run() {
                if(getInf())
                    startActivity(new Intent(Start.this, GameLevels.class).putExtra("askforrate",true));
                else
                    startActivity(new Intent(Start.this, Instructions.class));
                finish();
            }
        }, 1200);
    }
    public boolean getInf()
    {
        SharedPreferences sharedPrefs = getSharedPreferences("checked", MODE_PRIVATE);
        return sharedPrefs.getBoolean("info", false);
    }
}
