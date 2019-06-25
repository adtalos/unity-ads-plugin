package com.adtalos.ads.plugin;

import android.view.ViewGroup;

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

    void loadAd(String adUnitId, int width, int height, IAdtalosListener listener) {
        getContext().runOnUiThread(() -> {
            prepare(adUnitId, width, height, listener);
            try {
                NativeAdView adView = (NativeAdView) adViews.get(adUnitId);
                adView.loadAd(adUnitId, false);
            } catch (Exception e) {
            }
        });
    }

    void showAbsolute(String adUnitId, int x, int y) {
        getContext().runOnUiThread(() -> {
            try {
                NativeAdView adView = (NativeAdView) adViews.get(adUnitId);
                if (adView.getParent() != null) {
                    ((ViewGroup) adView.getParent()).removeView(adView);
                }
                getAdsLayout().addView(adView, getAbsoluteLayoutParams(x, y));
                adView.show();
            } catch (Exception e) {
            }
        });
    }

    void showRelative(String adUnitId, int position, int y) {
        getContext().runOnUiThread(() -> {
            try {
                NativeAdView adView = (NativeAdView) adViews.get(adUnitId);
                if (adView.getParent() != null) {
                    ((ViewGroup) adView.getParent()).removeView(adView);
                }
                getAdsLayout().addView(adView, getRelationLayoutParams(position, y));
                adView.show();
            } catch (Exception e) {
            }
        });
    }

    boolean isLoaded(String adUnitId) {
        try {
            NativeAdView adView = (NativeAdView) adViews.get(adUnitId);
            if (adView == null) return false;
            return adView.isLoaded();
        } catch (Exception e) {
            return false;
        }
    }
}
