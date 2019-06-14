package com.adtalos.ads.plugin;

import android.app.Activity;
import android.widget.RelativeLayout;

abstract class AbstractHandler {
    static Activity getContext() {
        return AdtalosUnityPlugin.getInstance().getCurrentActivity();
    }

    static RelativeLayout getAdsLayout() {
        return AdtalosUnityPlugin.getInstance().getAdsLayout();
    }
}
