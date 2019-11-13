package com.unity.xy.plugin.bridge;

import com.unity.xy.plugin.Size;
import com.unity.xy.plugin.View;

class BannerViewHandler extends ViewHandler {
    @Override
    public void prepare(String unitId, int width, int height, IListener listener) {
        if (views.containsKey(unitId)) return;
        Size size = new Size(width, height);
        View view = new View(getContext());
        ListenerProxy listenerProxy = new ListenerProxy(unitId, listener);
        view.setSize(size);
        view.setAnimationEnabled(true);
        view.setCarouselModeEnabled(true);
        view.setListener(listenerProxy);
        view.setDefaultCustomListener(listenerProxy);
        views.put(unitId, view);
    }
}
