package com.unity.xy.plugin.bridge;

import com.unity.xy.plugin.DefaultCustomListener;
import com.unity.xy.plugin.Listener;
import com.unity.xy.plugin.VideoController;
import com.unity.xy.plugin.VideoListener;

class ListenerProxy implements Listener, VideoListener, DefaultCustomListener {
    private String unitId;
    private IListener listener;

    ListenerProxy(String unitId, IListener listener) {
        this.unitId = unitId;
        this.listener = listener;
    }

    @Override
    public void on(String name, String data) {
        if (listener != null) {
            listener.on(unitId, name, data);
        }
    }

    @Override
    public void onRendered() {
        if (listener != null) {
            listener.on(unitId, "onRendered", "");
        }
    }

    @Override
    public void onImpressionFinished() {
        if (listener != null) {
            listener.on(unitId, "onImpressionFinished", "");
        }
    }

    @Override
    public void onImpressionFailed() {
        if (listener != null) {
            listener.on(unitId, "onImpressionFailed", "");
        }
    }

    @Override
    public void onImpressionReceivedError(int errorCode, String description) {
        if (listener != null) {
            listener.on(unitId, "onImpressionReceivedError", errorCode + ":" + description);
        }
    }

    @Override
    public void onLoaded() {
        if (listener != null) {
            listener.on(unitId, "onLoaded", "");
        }
    }

    @Override
    public void onFailedToLoad(Exception e) {
        if (listener != null) {
            listener.on(unitId, "onFailedToLoad", e.getMessage());
        }
    }

    @Override
    public void onOpened() {
        if (listener != null) {
            listener.on(unitId, "onOpened", "");
        }
    }

    @Override
    public void onClicked() {
        if (listener != null) {
            listener.on(unitId, "onClicked", "");
        }
    }

    @Override
    public void onLeftApplication() {
        if (listener != null) {
            listener.on(unitId, "onLeftApplication", "");
        }
    }

    @Override
    public void onClosed() {
        if (listener != null) {
            listener.on(unitId, "onClosed", "");
        }
    }

    @Override
    public void onVideoLoad(VideoController.Metadata metadata) {
        if (listener != null) {
            listener.on(unitId, "onVideoLoad", metadata.toString());
        }
    }

    @Override
    public void onVideoStart() {
        if (listener != null) {
            listener.on(unitId, "onVideoStart", "");
        }
    }

    @Override
    public void onVideoPlay() {
        if (listener != null) {
            listener.on(unitId, "onVideoPlay", "");
        }
    }

    @Override
    public void onVideoPause() {
        if (listener != null) {
            listener.on(unitId, "onVideoPause", "");
        }
    }

    @Override
    public void onVideoEnd() {
        if (listener != null) {
            listener.on(unitId, "onVideoEnd", "");
        }
    }

    @Override
    public void onVideoVolumeChange(double volume, boolean muted) {
        if (listener != null) {
            listener.on(unitId, "onVideoVolumeChange", "{ volume: " + volume + ", muted: " + muted + " }");
        }
    }

    @Override
    public void onVideoTimeUpdate(double currentTime, double duration) {
        if (listener != null) {
            listener.on(unitId, "onVideoTimeUpdate", "{ currentTime: " + currentTime + ", duration: " + duration + " }");
        }
    }

    @Override
    public void onVideoError() {
        if (listener != null) {
            listener.on(unitId, "onVideoError", "");
        }
    }

    @Override
    public void onVideoBreak() {
        if (listener != null) {
            listener.on(unitId, "onVideoBreak", "");
        }
    }
}
