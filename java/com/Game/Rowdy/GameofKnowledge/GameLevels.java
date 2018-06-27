package com.Game.Rowdy.GameofKnowledge;

import android.app.Activity;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Random;

public class GameLevels extends Activity {

    ListView list;
    RelativeLayout r;
    TextView WL;
    int ch,back;
    double inc;
    final Context context = this;
    String[] itemname ={"Pictures","3X3", "4X4", "5X5", "6X6",};
    Drawable drawable;
    Integer[] imgid={
            R.drawable.collagep,
            R.drawable.collage3,
            R.drawable.collage4,
            R.drawable.collage5,
            R.drawable.collage6
    };
    private void saveData()
    {
        SharedPreferences sp = getSharedPreferences("key", 0);
        SharedPreferences.Editor sedt = sp.edit();
        sedt.putString("Text", WL.getText().toString());
        sedt.commit();
    }
    private void restoreData()
    {
        SharedPreferences sp = getSharedPreferences("key", 0);
        WL.setText(sp.getString("Text", "0"));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_game_levels);
        setCount();
        boolean ask=getIntent().getBooleanExtra("askforrate",false);
        r=(RelativeLayout) findViewById(R.id.rlayout);
        Random ran=new Random();
        back=ran.nextInt(3);
        Resources res = getResources();
        if(back==0){drawable = res.getDrawable(R.drawable.levels);}
        else if(back==1){ drawable = res.getDrawable(R.drawable.levels1);}
        else{ drawable = res.getDrawable(R.drawable.levels2);}
        r.setBackground(drawable);
        WL=(TextView) findViewById(R.id.levels);
        CustomListAdapter adapter=new CustomListAdapter(this, itemname, imgid);
        list=(ListView)findViewById(R.id.list);
        list.setAdapter(adapter);
        ch=getIntent().getIntExtra("Open",0);
        if(ch>0)
        restoreData();
            switch (ch)
            {
                case 33:    inc=Double.parseDouble(WL.getText().toString());
                            if(inc==0)
                            {inc++;}
                            WL.setText(""+inc);
                            saveData();
                            break;
                case 43:    inc=Double.parseDouble(WL.getText().toString());
                            if(inc==1)
                            {inc++;}
                             WL.setText(""+inc);
                            saveData();
                            break;
                case 53:    inc=Double.parseDouble(WL.getText().toString());
                            if(inc==2)
                            {inc++;}
                            WL.setText(""+inc);
                            saveData();
                            break;
                default:    restoreData();
                            break;
            }
        restoreData();
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // TODO Auto-generated method stub
                String slecteditem = itemname[+position];
                if (slecteditem.equals("3X3")) {
                    startActivity(new Intent(GameLevels.this, Levels.class));
                    finish();
                } else if (slecteditem.equals("4X4")) {
                    inc = Double.parseDouble(WL.getText().toString());
                    if (inc > 0) {
                        startActivity(new Intent(GameLevels.this, Levels1.class));
                        finish();
                    } else
                        locked(4);
                } else if (slecteditem.equals("5X5")) {
                    inc = Double.parseDouble(WL.getText().toString());
                    if (inc > 1) {
                        startActivity(new Intent(GameLevels.this, Levels2.class));
                        finish();
                    } else
                        locked(5);
                } else if (slecteditem.equals("6X6")) {
                    inc = Double.parseDouble(WL.getText().toString());
                    if (inc > 2) {
                        startActivity(new Intent(GameLevels.this, Levels3.class));
                        finish();
                    } else
                        locked(6);
                }
                else if(slecteditem.equals("Pictures")){
                    startActivity(new Intent(GameLevels.this, Tablevels.class));
                    finish();
                }

            }
        });
        if(getCount()>2 && getLater() && ask)
            alertForRate();
    }
    public void alertForRate()
    {
        final AlertDialog.Builder builder = new AlertDialog.Builder(GameLevels.this);
        builder.setTitle("RATE THIS GAME");
        builder.setMessage("Hi, take a minute to rate this game and help support to improve more features");
        builder.setCancelable(true);

        builder.setPositiveButton("RATE NOW", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                goToPlayStore();
                SharedPreferences.Editor editor = getSharedPreferences("counts", MODE_PRIVATE).edit();
                editor.putBoolean("later", false);
                editor.commit();
                dialog.dismiss();
            }
        });

        builder.setNegativeButton("NO, THANKS", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                SharedPreferences.Editor editor = getSharedPreferences("counts", MODE_PRIVATE).edit();
                editor.putBoolean("later", false);
                editor.commit();
                dialog.cancel();
            }
        });

        builder.setNeutralButton("REMIND ME LATER", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog diag = builder.create();
        diag.show();
    }
    public void goToPlayStore()
    {
        //path to playstore
        Uri uri = Uri.parse("market://details?id=com.Game.Rowdy.GameofKnowledge");
        Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
        // To count with Play market backstack, After pressing back button,
        // to taken back to our application, we need to add following flags to intent.
        goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY |
                Intent.FLAG_ACTIVITY_NEW_DOCUMENT |
                Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
        try {
            startActivity(goToMarket);
        } catch (ActivityNotFoundException e) {
            startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse("market://details?id=com.Rowdy.Apps.un_coding")));
        }
    }
    public boolean getLater()
    {
        SharedPreferences sharedPrefs = getSharedPreferences("counts", MODE_PRIVATE);
        return sharedPrefs.getBoolean("later", true);
    }
    public int getCount()
    {
        SharedPreferences sharedPrefs = getSharedPreferences("counts", MODE_PRIVATE);
        return sharedPrefs.getInt("noofcounts", 0);
        //    returning the saved data of no of rewards
    }
    public void setCount()
    {
        SharedPreferences.Editor editor = getSharedPreferences("counts", MODE_PRIVATE).edit();
        editor.putInt("noofcounts",getCount()+1);
        editor.commit();
        //    returning the saved data of no of rewards
    }
    public void locked(int l)
    {
        // custom dialog
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.levellock);
        // set the custom dialog components - text, image and button
        TextView text = (TextView) dialog.findViewById(R.id.wlock);
        TextView text1 = (TextView) dialog.findViewById(R.id.wlock1);
        text.setText (" This level ");
        text1.setText(" is locked!!");
        ImageView im = (ImageView) dialog.findViewById(R.id.imgl);

       switch (l)
       {
           case 4: im.setImageResource(R.drawable.collage4);
                    break;
           case 5: im.setImageResource(R.drawable.collage5);
                    break;
           case 6: im.setImageResource(R.drawable.collage6);
                    break;
       }
        dialog.show();

    }
    public void settings(View v)
    {
        startActivity(new Intent(GameLevels.this, Settings.class));
    }
    public void onBackPressed()
    {
        saveData();
        finish();
    }

}
