package com.adtalos.ads.plugin;

import android.app.Activity;
import android.widget.RelativeLayout;

abstract class AbstractHandler {
    public static Activity getContext() {
        return AdtalosUnityPlugin.getInstance().getCurrentActivity();
    }

    public static RelativeLayout getAdsLayout() {
        return AdtalosUnityPlugin.getInstance().getAdsLayout();
    }
}
