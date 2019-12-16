package com.unity.xy.plugin.bridge;

import com.unity.xy.plugin.Controller;

class InterstitialHandler extends ControllerHandler {
    void load(String unitId, boolean immersiveMode, IListener listener) {
        Controller controller = controllers.get(unitId);
        if (controller == null) {
            controller = new Controller(unitId, Controller.INTERSTITIAL);
            controller.setImmersiveMode(immersiveMode);
            ListenerProxy listenerProxy = new ListenerProxy(unitId, listener);
            controller.setListener(listenerProxy);
            controller.setVideoListener(listenerProxy);
            controller.setDefaultCustomListener(listenerProxy);
            controllers.put(unitId, controller);
        }
        controller.load();
    }
}
