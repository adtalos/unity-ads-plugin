package com.adtalos.ads.plugin;

import com.adtalos.ads.sdk.AdSize;
import com.adtalos.ads.sdk.NativeAdView;
import com.adtalos.ads.sdk.VideoController;

class NativeAdViewHandler extends AdViewHandler {
    @Override
    public void prepare(String adUnitId, int width, int height, IAdtalosListener listener) {
        if (adViews.containsKey(adUnitId)) return;
        AdSize adSize = new AdSize(width, height);
        NativeAdView adView = new NativeAdView(getContext());
        AdtalosListenerProxy listenerProxy = new AdtalosListenerProxy(adUnitId, listener);
        adView.setAdSize(adSize);
        adView.setAdListener(listenerProxy);
        adView.getVideoController().setAdVideoListener(listenerProxy);
        adView.setAdDefaultCustomListener(listenerProxy);
        adViews.put(adUnitId, adView);
    }

    void playVideo(String adUnitId) {
        try {
            NativeAdView adView = (NativeAdView) adViews.get(adUnitId);
            if (adView == null) return;
            getContext().runOnUiThread(() -> {
                adView.getVideoController().play();
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void pauseVideo(String adUnitId) {
        try {
            NativeAdView adView = (NativeAdView) adViews.get(adUnitId);
            if (adView == null) return;
            getContext().runOnUiThread(() -> {
                adView.getVideoController().pause();
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void muteVideo(String adUnitId, boolean mute) {
        try {
            NativeAdView adView = (NativeAdView) adViews.get(adUnitId);
            if (adView == null) return;
            getContext().runOnUiThread(() -> {
                adView.getVideoController().mute(mute);
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    boolean hasVideo(String adUnitId) {
        try {
            NativeAdView adView = (NativeAdView) adViews.get(adUnitId);
            if (adView == null) return false;
            return adView.getVideoController().hasVideo();
        } catch (Exception e) {
            return false;
        }
    }

    String getVideoMetaData(String adUnitId) {
        NativeAdView adView = (NativeAdView) adViews.get(adUnitId);
        if (adView == null) return null;
        VideoController.Metadata metadata = adView.getVideoController().getMetadata();
        if (metadata == null) return null;
        return metadata.toString();
    }
}
