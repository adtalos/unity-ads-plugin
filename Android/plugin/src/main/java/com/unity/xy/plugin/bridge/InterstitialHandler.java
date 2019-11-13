package com.unity.xy.plugin.bridge;

import com.unity.xy.plugin.Controller;

class InterstitialHandler extends ControllerHandler {
    void create(String unitId, boolean immersiveMode, IListener listener) {
        Controller ad = new Controller(unitId, Controller.INTERSTITIAL);
        ad.setImmersiveMode(immersiveMode);
        ListenerProxy listenerProxy = new ListenerProxy(unitId, listener);
        ad.setListener(listenerProxy);
        ad.setVideoListener(listenerProxy);
        ad.setDefaultCustomListener(listenerProxy);
        ads.put(unitId, ad);
    }
}
