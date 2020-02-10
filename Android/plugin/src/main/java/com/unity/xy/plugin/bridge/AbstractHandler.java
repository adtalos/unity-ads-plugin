package com.unity.xy.plugin.bridge;

import android.app.Activity;
import android.widget.RelativeLayout;

abstract class AbstractHandler {
    static Activity getContext() {
        return UnityPlugin.getInstance().getActivity();
    }

    static RelativeLayout getLayout() {
        return UnityPlugin.getInstance().getLayout();
    }
}
