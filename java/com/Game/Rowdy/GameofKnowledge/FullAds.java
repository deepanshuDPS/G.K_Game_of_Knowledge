package com.Game.Rowdy.GameofKnowledge;

import android.content.Context;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

public class FullAds
{

    InterstitialAd mInterstitialAd;

    public void startAdd(Context c)
    {
        mInterstitialAd = new InterstitialAd(c);

        // set the ad unit ID
        mInterstitialAd.setAdUnitId("ca-app-pub-5369990305529266/4457061868");

        AdRequest adRequest = new AdRequest.Builder()
                .build();

        // Load ads into Interstitial Ads
        mInterstitialAd.loadAd(adRequest);

        mInterstitialAd.setAdListener(new AdListener() {
            public void onAdLoaded() {
                showInterstitial();
            }
        });
    }

    private void showInterstitial() {
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        }
    }
}
