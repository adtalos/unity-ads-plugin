package com.adtalos.ads.plugin;

import com.adtalos.ads.sdk.RewardedVideoAd;

class RewardedVideoAdHandler extends AdHandler {
    void create(String adUnitId, IAdtalosListener listener) {
        RewardedVideoAd ad = new RewardedVideoAd(adUnitId);
        AdtalosListenerProxy listenerProxy = new AdtalosListenerProxy(adUnitId, listener);
        ad.setAdListener(listenerProxy);
        ad.setAdDefaultCustomListener(listenerProxy);
        ad.setAdVideoListener(listenerProxy);
    }
}
