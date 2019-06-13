package com.adtalos.ads.plugin;

import com.adtalos.ads.sdk.AdDefaultCustomListener;
import com.adtalos.ads.sdk.AdListener;
import com.adtalos.ads.sdk.AdVideoListener;
import com.adtalos.ads.sdk.VideoController;

public class AdtalosListenerProxy implements AdListener, AdVideoListener, AdDefaultCustomListener {
    private String adUnitId;
    public IAdtalosListener listener;

    public AdtalosListenerProxy(String adUnitId, IAdtalosListener listener) {
        this.adUnitId = adUnitId;
        this.listener = listener;
    }

    @Override
    public void on(String name, String data) {
        if (listener != null) {
            listener.onAdtalosEvent(adUnitId, name, data);
        }
    }

    @Override
    public void onAdImpressionFinished() {
        if (listener != null) {
            listener.onAdtalosEvent(adUnitId, "onAdImpressionFinished", null);
        }
    }

    @Override
    public void onAdImpressionReceivedError(int errorCode, String description) {
        if (listener != null) {
            listener.onAdtalosEvent(adUnitId, "onAdImpressionReceivedError", errorCode + ":" + description);
        }
    }

    @Override
    public void onAdLoaded() {
        if (listener != null) {
            listener.onAdtalosEvent(adUnitId, "onAdLoaded", null);
        }
    }

    @Override
    public void onAdFailedToLoad(Exception e) {
        if (listener != null) {
            listener.onAdtalosEvent(adUnitId, "onAdFailedToLoad", e.getMessage());
        }
    }

    @Override
    public void onAdOpened() {
        if (listener != null) {
            listener.onAdtalosEvent(adUnitId, "onAdOpened", null);
        }
    }

    @Override
    public void onAdClicked() {
        if (listener != null) {
            listener.onAdtalosEvent(adUnitId, "onAdClicked", null);
        }
    }

    @Override
    public void onAdLeftApplication() {
        if (listener != null) {
            listener.onAdtalosEvent(adUnitId, "onAdLeftApplication", null);
        }
    }

    @Override
    public void onAdClosed() {
        if (listener != null) {
            listener.onAdtalosEvent(adUnitId, "onAdClosed", null);
        }
    }

    @Override
    public void onVideoLoad(VideoController.Metadata metadata) {
        if (listener != null) {
            listener.onAdtalosEvent(adUnitId, "onVideoLoad", metadata.toString());
        }
    }

    @Override
    public void onVideoStart() {
        if (listener != null) {
            listener.onAdtalosEvent(adUnitId, "onVideoStart", null);
        }
    }

    @Override
    public void onVideoPlay() {
        if (listener != null) {
            listener.onAdtalosEvent(adUnitId, "onVideoPlay", null);
        }
    }

    @Override
    public void onVideoPause() {
        if (listener != null) {
            listener.onAdtalosEvent(adUnitId, "onVideoPause", null);
        }
    }

    @Override
    public void onVideoEnd() {
        if (listener != null) {
            listener.onAdtalosEvent(adUnitId, "onVideoEnd", null);
        }
    }

    @Override
    public void onVideoVolumeChange(double volume, boolean muted) {
        if (listener != null) {
            listener.onAdtalosEvent(adUnitId, "onVideoVolumeChange", "{ volume: " + volume + ", muted: " + muted + " }");
        }
    }

    @Override
    public void onVideoTimeUpdate(double currentTime, double duration) {
        if (listener != null) {
            listener.onAdtalosEvent(adUnitId, "onVideoTimeUpdate", "{ currentTime: " + currentTime + ", duration: " + duration + " }");
        }
    }

    @Override
    public void onVideoError() {
        if (listener != null) {
            listener.onAdtalosEvent(adUnitId, "onVideoError", null);
        }
    }

    @Override
    public void onVideoBreak() {
        if (listener != null) {
            listener.onAdtalosEvent(adUnitId, "onVideoBreak", null);
        }
    }
}
