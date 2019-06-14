package com.adtalos.ads.plugin;

import com.adtalos.ads.sdk.SplashAd;

class SplashAdHandler extends AdHandler {
    void create(String adUnitId, IAdtalosListener listener) {
        SplashAd ad = new SplashAd(adUnitId);
        AdtalosListenerProxy listenerProxy = new AdtalosListenerProxy(adUnitId, listener);
        ad.setAdListener(listenerProxy);
        ad.setAdDefaultCustomListener(listenerProxy);
    }
}
