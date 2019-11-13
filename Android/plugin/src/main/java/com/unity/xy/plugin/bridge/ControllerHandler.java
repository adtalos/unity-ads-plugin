package com.unity.xy.plugin.bridge;

import com.unity.xy.plugin.Controller;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

abstract class ControllerHandler extends AbstractHandler {
    protected static Map<String, Controller> ads = new ConcurrentHashMap<>();

    static boolean isLoaded(String unitId) {
        Controller ad = ads.get(unitId);
        if (ad == null) return false;
        return ad.isLoaded();
    }

    static void show(String unitId) {
        getContext().runOnUiThread(() -> {
            Controller ad = ads.get(unitId);
            if (ad != null) ad.show();
        });
    }
}
