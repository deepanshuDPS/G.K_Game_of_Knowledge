package com.Game.Rowdy.GameofKnowledge;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import java.io.*;
import java.util.Random;
import android.widget.RatingBar;

public class SharePage extends Activity {

    static TextView cs3, ns3, as3, cs4, ns4, as4, cs5, ns5, as5, cs6, ns6,as6,smarks;
    TextView comm;
    RatingBar ratingBar;
    Button share;
    float percentage;
    String ran="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_share_page);
        cs3 = (TextView) findViewById(R.id.cs3);
        cs4 = (TextView) findViewById(R.id.cs4);
        cs5 = (TextView) findViewById(R.id.cs5);
        cs6 = (TextView) findViewById(R.id.cs6);
        ns3 = (TextView) findViewById(R.id.ns3);
        ns4 = (TextView) findViewById(R.id.ns4);
        ns5 = (TextView) findViewById(R.id.ns5);
        ns6 = (TextView) findViewById(R.id.ns6);
        as3 = (TextView) findViewById(R.id.as3);
        as4 = (TextView) findViewById(R.id.as4);
        as5 = (TextView) findViewById(R.id.as5);
        as6 = (TextView) findViewById(R.id.as6);
        smarks=(TextView) findViewById(R.id.smarks);
        comm=(TextView) findViewById(R.id.comm);
        ratingBar=(RatingBar) findViewById(R.id.ratingBar);
        share=(Button) findViewById(R.id.button3);
        Random k=new Random();
        for(int i=0;i<10;i++)
            ran=ran+(k.nextInt());
        restoreData();
        setRating();
    }

    private void restoreData()
    {
        SharedPreferences sp = getSharedPreferences("key", 0);
        smarks.setText(sp.getString("marks", "0"));
        cs3.setText(sp.getString("c3", " - "));
        ns3.setText(sp.getString("n3", " - "));
        as3.setText(sp.getString("a3", " - "));
        cs4.setText(sp.getString("c4", " - "));
        ns4.setText(sp.getString("n4", " - "));
        as4.setText(sp.getString("a4", " - "));
        cs5.setText(sp.getString("c5", " - "));
        ns5.setText(sp.getString("n5", " - "));
        as5.setText(sp.getString("a5", " - "));
        cs6.setText(sp.getString("c6", " - "));
        ns6.setText(sp.getString("n6", " - "));
        as6.setText(sp.getString("a6", " - "));
    }
    public void setRating()
    {
        if(!as6.getText().toString().equals(" - "))
        {
            percentage=(Float.parseFloat(smarks.getText().toString())/314)*5;
        }
        else if(!ns6.getText().toString().equals(" - "))
        {
            percentage=(Float.parseFloat(smarks.getText().toString())/273)*5;
        }
        else if(!cs6.getText().toString().equals(" - "))
        {
            percentage=(Float.parseFloat(smarks.getText().toString())/233)*5;
        }
        else if(!as5.getText().toString().equals(" - "))
        {
            percentage=(Float.parseFloat(smarks.getText().toString())/190)*5;
        }
        else if(!ns5.getText().toString().equals(" - "))
        {
            percentage=(Float.parseFloat(smarks.getText().toString())/160)*5;
        }
        else if(!cs5.getText().toString().equals(" - "))
        {
            percentage=(Float.parseFloat(smarks.getText().toString())/130)*5;
        }
        else
        {
            percentage=(Float.parseFloat(smarks.getText().toString())/120)*5;
        }
        if(percentage>0)
            ratingBar.setRating(percentage);
        else
            ratingBar.setRating(0);
        if(percentage>4)
        {
            comm.setText("ÖûTStânDînG");
            comm.setTextColor(Color.parseColor("#00ae34"));    //smart green
        }
        else if(percentage>3)
        {
            comm.setText("ÉxcëllènT");
            comm.setTextColor(Color.parseColor("#FF38BEF8"));   //smart sky blue
        }
        else if(percentage>2)
        {
            comm.setText("GóòD");
            comm.setTextColor(Color.parseColor("#8a62ed"));   //purple
        }
        else if(percentage>1)
        {
            comm.setText("PòóR");
            comm.setTextColor(Color.parseColor("#FFFF3C00"));   //smart red
        }
        else if(percentage>0)
        {
            comm.setText("BåD");
            comm.setTextColor(Color.parseColor("#0400ff"));   //blue
        }
        else
        {
            comm.setText("Kèép TryíñG");
            comm.setTextColor(Color.parseColor("#000000"));
        }

    }
    public void ShareIt(View view)
    {
        Bitmap bitmap = takeScreenshot();
        saveBitmap(bitmap);
        sharescoreboard();
    }
    public Bitmap takeScreenshot() {
        share.setVisibility(View.GONE);
        View rootView = findViewById(android.R.id.content).getRootView();
        rootView.setDrawingCacheEnabled(true);
        return rootView.getDrawingCache();

    }
    public void saveBitmap(Bitmap bitmap) {
        File imagePath = new File(Environment.getExternalStorageDirectory() + "/"+ran+".png");
        FileOutputStream fos;
        try {
            fos = new FileOutputStream(imagePath);
            bitmap.compress(Bitmap.CompressFormat.JPEG,100,fos);
            fos.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            Log.e("GREC", e.getMessage(), e);
        } catch (IOException e) {
            Log.e("GREC", e.getMessage(), e);
        }
    }
    public void sharescoreboard()
    {
        Intent shareIntent = new Intent();
        shareIntent.setAction(Intent.ACTION_SEND);
        shareIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Sharing Scoreboard Of G.K Game");
        shareIntent.putExtra(Intent.EXTRA_TEXT, "https://play.google.com/store/apps/details?id=com.Game.Rowdy.GameofKnowledge");
        Uri uri = Uri.fromFile(new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/"+ran+".png"));
        shareIntent.putExtra(Intent.EXTRA_STREAM, uri);  //optional//use this when you want to send an image
        shareIntent.setType("image/png");
        shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        startActivity(Intent.createChooser(shareIntent, "Share Your Scoreboard Through"));
        finish();
    }
    public void onBackPressed()
    {
        super.onBackPressed();
        startActivity(new Intent(SharePage.this, Scoreboard.class));
        finish();
    }

}
