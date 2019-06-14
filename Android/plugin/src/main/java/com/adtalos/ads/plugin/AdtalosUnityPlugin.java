package com.adtalos.ads.plugin;

import android.app.Activity;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;

public class AdtalosUnityPlugin {
    private static RelativeLayout adsLayout;
    private static Activity context;
    private final static AdtalosUnityPlugin instance = new AdtalosUnityPlugin();
    private final static BannerAdViewHandler bannerAdViewHandler = new BannerAdViewHandler();
    private final static NativeAdViewHandler nativeAdViewHandler = new NativeAdViewHandler();

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

    public void showBannerAbsolute(String adUnitId, int width, int height, int x, int y, IAdtalosListener listener) {
        bannerAdViewHandler.showAbsolute(adUnitId, width, height, x, y, listener);
    }

    public void showBannerRelative(String adUnitId, int width, int height, int position, int y, IAdtalosListener listener) {
        bannerAdViewHandler.showRelative(adUnitId, width, height, position, y, listener);
    }

    public void showNativeAbsolute(String adUnitId, int width, int height, int x, int y, IAdtalosListener listener) {
        nativeAdViewHandler.showAbsolute(adUnitId, width, height, x, y, listener);
    }

    public void showNativeRelative(String adUnitId, int width, int height, int position, int y, IAdtalosListener listener) {
        nativeAdViewHandler.showRelative(adUnitId, width, height, position, y, listener);
    }

    public void playVideo(String adUnitId) {
        nativeAdViewHandler.playVideo(adUnitId);
    }

    public void pauseVideo(String adUnitId) {
        nativeAdViewHandler.pauseVideo(adUnitId);
    }

    public void muteVideo(String adUnitId, boolean mute) {
        nativeAdViewHandler.muteVideo(adUnitId, mute);
    }

    public boolean hasVideo(String adUnitId) {
        return nativeAdViewHandler.hasVideo(adUnitId);
    }

    public String getVideoMetaData(String adUnitId) {
        return nativeAdViewHandler.getVideoMetaData(adUnitId);
    }

    public static void destory(String adUnitId) {
        AdViewHandler.destory(adUnitId);
    }

    public static void pause(String adUnitId) {
        AdViewHandler.pause(adUnitId);
    }

    public static void resume(String adUnitId) {
        AdViewHandler.resume(adUnitId);
    }
}
