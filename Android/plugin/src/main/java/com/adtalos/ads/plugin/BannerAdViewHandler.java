package com.adtalos.ads.plugin;

import com.adtalos.ads.sdk.AdSize;
import com.adtalos.ads.sdk.BannerAdView;

class BannerAdViewHandler extends AdViewHandler {
    @Override
    public void prepare(String adUnitId, int width, int height, IAdtalosListener listener) {
        if (adViews.containsKey(adUnitId)) return;
        AdSize adSize = new AdSize(width, height);
        BannerAdView adView = new BannerAdView(getContext());
        AdtalosListenerProxy listenerProxy = new AdtalosListenerProxy(adUnitId, listener);
        adView.setAdSize(adSize);
        adView.setAdListener(listenerProxy);
        adView.setAdDefaultCustomListener(listenerProxy);
        adViews.put(adUnitId, adView);
    }
}
