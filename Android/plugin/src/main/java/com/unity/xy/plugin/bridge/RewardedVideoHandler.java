package com.unity.xy.plugin.bridge;

import com.unity.xy.plugin.Controller;

class RewardedVideoHandler extends ControllerHandler {
    void create(String unitId, IListener listener) {
        Controller controller = new Controller(unitId, Controller.REWARDED_VIDEO);
        ListenerProxy listenerProxy = new ListenerProxy(unitId, listener);
        controller.setListener(listenerProxy);
        controller.setVideoListener(listenerProxy);
        controller.setDefaultCustomListener(listenerProxy);
        controllers.put(unitId, controller);
    }
}
