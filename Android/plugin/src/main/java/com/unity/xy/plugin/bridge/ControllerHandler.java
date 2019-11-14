package com.unity.xy.plugin.bridge;

import com.unity.xy.plugin.Controller;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

abstract class ControllerHandler extends AbstractHandler {
    protected static Map<String, Controller> controllers = new ConcurrentHashMap<>();

    static boolean isLoaded(String unitId) {
        Controller controller = controllers.get(unitId);
        if (controller == null) return false;
        return controller.isLoaded();
    }

    static void show(String unitId) {
        getContext().runOnUiThread(() -> {
            Controller controller = controllers.get(unitId);
            if (controller != null) controller.show();
        });
    }
}
