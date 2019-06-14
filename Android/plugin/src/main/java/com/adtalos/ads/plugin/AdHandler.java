package com.adtalos.ads.plugin;

import com.adtalos.ads.sdk.InterstitialAd;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

abstract class AdHandler extends AbstractHandler {
    protected static Map<String, InterstitialAd> ads = new ConcurrentHashMap<>();

    static boolean isLoaded(String adUnitId) {
        InterstitialAd ad = ads.get(adUnitId);
        if (ad == null) return false;
        return ad.isLoaded();
    }

    static void show(String adUnitId) {
        getContext().runOnUiThread(() -> {
            InterstitialAd ad = ads.get(adUnitId);
            if (ad != null) ad.show();
        });
    }
}
