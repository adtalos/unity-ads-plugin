package com.unity.xy.plugin.bridge;


import com.unity.xy.plugin.Controller;

class SplashHandler extends ControllerHandler {
    void create(String unitId, IListener listener) {
        Controller controller = new Controller(unitId, Controller.SPLASH);
        ListenerProxy listenerProxy = new ListenerProxy(unitId, listener);
        controller.setListener(listenerProxy);
        controller.setVideoListener(listenerProxy);
        controller.setDefaultCustomListener(listenerProxy);
        ads.put(unitId, controller);
    }
}
