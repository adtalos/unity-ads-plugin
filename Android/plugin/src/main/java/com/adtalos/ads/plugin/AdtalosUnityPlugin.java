package com.adtalos.ads.plugin;

import android.app.Activity;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;

public class AdtalosUnityPlugin {
    private static RelativeLayout adsLayout;
    private static Activity context;
    private final static AdtalosUnityPlugin instance = new AdtalosUnityPlugin();

    public Activity getCurrentActivity() {
        try {
            Class<?> unityPlayer = Class.forName("com.unity3d.player.UnityPlayer");
            Activity activity = (Activity) unityPlayer.getField("currentActivity").get(unityPlayer);
            setActivity(activity);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return context;
    }

    public void setActivity(Activity activity) {
        if (activity != context) {
            context = activity;
            context.runOnUiThread(new Runnable() {
                public void run() {
                    AdtalosUnityPlugin.adsLayout = new RelativeLayout(AdtalosUnityPlugin.context);
                    AdtalosUnityPlugin.context.addContentView(AdtalosUnityPlugin.adsLayout, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
                }
            });
        }
    }

    public RelativeLayout getAdsLayout() {
        return adsLayout;
    }

    public static AdtalosUnityPlugin getInstance() {
        return instance;
    }
}
