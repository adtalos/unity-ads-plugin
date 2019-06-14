package com.adtalos.ads.plugin;

import com.adtalos.ads.sdk.InterstitialAd;

class InterstitialAdHandler extends AdHandler {
    void create(String adUnitId, boolean immersiveMode, IAdtalosListener listener) {
        InterstitialAd ad = new InterstitialAd(adUnitId);
        ad.setImmersiveMode(immersiveMode);
        AdtalosListenerProxy listenerProxy = new AdtalosListenerProxy(adUnitId, listener);
        ad.setAdListener(listenerProxy);
        ad.setAdDefaultCustomListener(listenerProxy);
    }
}
