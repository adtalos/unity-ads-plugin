package com.unity.xy.plugin.bridge;

import android.app.Activity;
import android.widget.RelativeLayout;

abstract class AbstractHandler {
    static Activity getContext() {
        return UnityPlugin.getInstance().getCurrentActivity();
    }

    static RelativeLayout getAdsLayout() {
        return UnityPlugin.getInstance().getAdsLayout();
    }
}