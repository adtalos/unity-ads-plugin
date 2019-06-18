package com.adtalos.ads.plugin;

import android.app.Activity;
import android.util.Log;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;

public class AdtalosUnityPlugin {
    private final static AdtalosUnityPlugin instance = new AdtalosUnityPlugin();
    private final static BannerAdViewHandler bannerAdViewHandler = new BannerAdViewHandler();
    private final static NativeAdViewHandler nativeAdViewHandler = new NativeAdViewHandler();
    private final static InterstitialAdHandler interstitialAdHandler = new InterstitialAdHandler();
    private final static SplashAdHandler splashAdHandler = new SplashAdHandler();
    private final static RewardedVideoAdHandler rewardedVideoAdHandler = new RewardedVideoAdHandler();
    private RelativeLayout adsLayout;
    private Activity context;

    private void setActivity(Activity activity) {
        if (activity != context) {
            context = activity;
            context.runOnUiThread(() -> {
                adsLayout = new RelativeLayout(context);
                context.addContentView(adsLayout, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
            });
        }
    }

    Activity getCurrentActivity() {
        try {
            Class<?> unityPlayer = Class.forName("com.unity3d.player.UnityPlayer");
            Activity activity = (Activity) unityPlayer.getField("currentActivity").get(unityPlayer);
            setActivity(activity);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return context;
    }

    RelativeLayout getAdsLayout() {
        return adsLayout;
    }

    public static AdtalosUnityPlugin getInstance() {
        return instance;
    }

    public void showBannerAbsolute(String adUnitId, int width, int height, int x, int y, IAdtalosListener listener) {
        Log.d("UnityPlugin", "showBannerAbsolute");
        bannerAdViewHandler.showAbsolute(adUnitId, width, height, x, y, listener);
    }

    public void showBannerRelative(String adUnitId, int width, int height, int position, int y, IAdtalosListener listener) {
        Log.d("UnityPlugin", "showBannerRelative");
        bannerAdViewHandler.showRelative(adUnitId, width, height, position, y, listener);
    }

    public void showNativeAbsolute(String adUnitId, int width, int height, int x, int y, IAdtalosListener listener) {
        Log.d("UnityPlugin", "showNativeAbsolute");
        nativeAdViewHandler.showAbsolute(adUnitId, width, height, x, y, listener);
    }

    public void showNativeRelative(String adUnitId, int width, int height, int position, int y, IAdtalosListener listener) {
        Log.d("UnityPlugin", "showNativeRelative");
        nativeAdViewHandler.showRelative(adUnitId, width, height, position, y, listener);
    }

    public void playVideo(String adUnitId) {
        Log.d("UnityPlugin", "playVideo");
        nativeAdViewHandler.playVideo(adUnitId);
    }

    public void pauseVideo(String adUnitId) {
        Log.d("UnityPlugin", "pauseVideo");
        nativeAdViewHandler.pauseVideo(adUnitId);
    }

    public void muteVideo(String adUnitId, boolean mute) {
        Log.d("UnityPlugin", "muteVideo");
        nativeAdViewHandler.muteVideo(adUnitId, mute);
    }

    public boolean hasVideo(String adUnitId) {
        Log.d("UnityPlugin", "hasVideo");
        return nativeAdViewHandler.hasVideo(adUnitId);
    }

    public String getVideoMetaData(String adUnitId) {
        Log.d("UnityPlugin", "getVideoMetaData");
        return nativeAdViewHandler.getVideoMetaData(adUnitId);
    }

    public void destroy(String adUnitId) {
        Log.d("UnityPlugin", "destroy");
        AdViewHandler.destroy(adUnitId);
    }

    public void pause(String adUnitId) {
        Log.d("UnityPlugin", "pause");
        AdViewHandler.pause(adUnitId);
    }

    public void resume(String adUnitId) {
        Log.d("UnityPlugin", "resume");
        AdViewHandler.resume(adUnitId);
    }

    public void loadInterstitialAd(String adUnitId, boolean immersiveMode, IAdtalosListener listener) {
        Log.d("UnityPlugin", "loadInterstitialAd");
        interstitialAdHandler.create(adUnitId, immersiveMode, listener);
    }

    public void loadSplashAd(String adUnitId, IAdtalosListener listener) {
        Log.d("UnityPlugin", "loadSplashAd");
        splashAdHandler.create(adUnitId, listener);
    }

    public void loadRewardedVideoAd(String adUnitId, IAdtalosListener listener) {
        Log.d("UnityPlugin", "loadRewardedVideoAd");
        rewardedVideoAdHandler.create(adUnitId, listener);
    }

    public boolean isLoaded(String adUnitId) {
        Log.d("UnityPlugin", "isLoaded");
        return AdHandler.isLoaded(adUnitId);
    }

    public void show(String adUnitId) {
        Log.d("UnityPlugin", "show");
        AdHandler.show(adUnitId);
    }

}
