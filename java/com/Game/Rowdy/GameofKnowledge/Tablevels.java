package com.Game.Rowdy.GameofKnowledge;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Tablevels extends Activity implements View.OnClickListener{

    TextView levelname;
    Button right,left;
    ImageView imv;
    MediaPlayer mp;
    int level ;
    FullAds fullAds;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_tablevels);
        level=getIntent().getIntExtra("Goto",0);
        levelname=(TextView) findViewById(R.id.lname);
        right=(Button) findViewById(R.id.right);
        left=(Button) findViewById(R.id.left);
        imv=(ImageView) findViewById(R.id.imv);
            switch(level)
            {
                case 0:
                case 3:
                    levelname.setText("3X3");
                    imv.setBackgroundResource(R.drawable.three);
                    left.setVisibility(View.GONE);
                    left.setClickable(false);
                    break;
                case 4:
                    levelname.setText("4X4");
                    imv.setBackgroundResource(R.drawable.four);
                    bothclickable();
                    break;
                case 5:
                    levelname.setText("5X5");
                    imv.setBackgroundResource(R.drawable.five);
                    bothclickable();
                    break;
                case 6:
                    levelname.setText("6X6");
                    imv.setBackgroundResource(R.drawable.six);
                    right.setVisibility(View.GONE);
                    right.setClickable(false);
                    break;
            }
        right.setOnClickListener(this);
        left.setOnClickListener(this);
    }
    @Override
    public void onClick(View v)
    {
        String temp=levelname.getText().toString();
       if(v==right)
       {
           if(temp.equals("3X3"))
           {
               levelname.setText("4X4");
               imv.setBackgroundResource(R.drawable.four);
               bothclickable();
           }
           else if(temp.equals("4X4"))
           {
               levelname.setText("5X5");
               imv.setBackgroundResource(R.drawable.five);
               bothclickable();
           }
           else
           {
               levelname.setText("6X6");
               imv.setBackgroundResource(R.drawable.six);
               right.setVisibility(View.GONE);
               right.setClickable(false);
           }
       }
        else
       {
           if(temp.equals("4X4"))
           {
               levelname.setText("3X3");
               imv.setBackgroundResource(R.drawable.three);
               left.setVisibility(View.GONE);
               left.setClickable(false);
           }
           else if(temp.equals("5X5"))
           {
               levelname.setText("4X4");
               imv.setBackgroundResource(R.drawable.four);
               bothclickable();
           }
           else
           {
               levelname.setText("5X5");
               imv.setBackgroundResource(R.drawable.five);
               bothclickable();
           }
       }

    }
    public void bothclickable()
    {
        left.setVisibility(View.VISIBLE);
        right.setVisibility(View.VISIBLE);
        left.setClickable(true);
        right.setClickable(true);
    }
    public void picpuzz(View v)
    {
        mp = MediaPlayer.create(getApplicationContext(), R.raw.click);
        if(Settings.onoff==1)
            mp.start();
        String temp=levelname.getText().toString();
        if(temp.equals("3X3"))
            startActivity(new Intent(Tablevels.this, Picp3.class));
        else if(temp.equals("4X4"))
            startActivity(new Intent(Tablevels.this, Picp4.class));
        else if(temp.equals("5X5"))
            startActivity(new Intent(Tablevels.this, Picp5.class));
        else
            startActivity(new Intent(Tablevels.this, Picp6.class));

        finish();
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        fullAds=new FullAds();
        fullAds.startAdd(Tablevels.this);
        startActivity(new Intent(Tablevels.this,GameLevels.class));
        finish();
    }


}
