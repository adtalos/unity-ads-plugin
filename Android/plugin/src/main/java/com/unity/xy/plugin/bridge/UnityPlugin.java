package com.unity.xy.plugin.bridge;

import android.app.Activity;
import android.util.Log;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;

import com.unity.xy.plugin.LandingPageActivity;
import com.unity.xy.plugin.SDK;

public class UnityPlugin {
    private final static UnityPlugin instance = new UnityPlugin();
    private final static BannerViewHandler bannerViewHandler = new BannerViewHandler();
    private final static NativeViewHandler nativeViewHandler = new NativeViewHandler();
    private final static InterstitialHandler interstitialHandler = new InterstitialHandler();
    private final static SplashHandler splashHandler = new SplashHandler();
    private final static RewardedVideoHandler rewardedVideoHandler = new RewardedVideoHandler();
    private RelativeLayout adsLayout;
    private Activity context;

    private void setActivity(Activity activity) {
        if (activity != context) {
            context = activity;
            context.runOnUiThread(() -> {
                adsLayout = new RelativeLayout(context);
                context.addContentView(adsLayout, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
                SDK.requestPermissions(context);
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

    public static UnityPlugin getInstance() {
        return instance;
    }

    public void setOAID(String oaid) {
        Log.d("UnityPlugin", "setOAID");
        SDK.setOAID(oaid);
    }

    public boolean isLandingPageDisplayActionBarEnabled() {
        Log.d("UnityPlugin", "isLandingPageDisplayActionBarEnabled");
        return LandingPageActivity.isDisplayActionBarEnabled();
    }

    public boolean isLandingPageAnimationEnabled() {
        Log.d("UnityPlugin", "isLandingPageAnimationEnabled");
        return LandingPageActivity.isAnimationEnabled();
    }

    public boolean isLandingPageFullScreenEnabled() {
        Log.d("UnityPlugin", "isLandingPageFullScreenEnabled");
        return LandingPageActivity.isFullScreenEnabled();
    }

    public void setLandingPageDisplayActionBarEnabled(boolean enabled) {
        Log.d("UnityPlugin", "setLandingPageDisplayActionBarEnabled");
        LandingPageActivity.setDisplayActionBarEnabled(enabled);
    }

    public void setLandingPageAnimationEnabled(boolean enabled) {
        Log.d("UnityPlugin", "setLandingPageAnimationEnabled");
        LandingPageActivity.setAnimationEnabled(enabled);
    }

    public void setLandingPageFullScreenEnabled(boolean enabled) {
        Log.d("UnityPlugin", "setLandingPageFullScreenEnabled");
        LandingPageActivity.setFullScreenEnabled(enabled);
    }

    public void showBannerAbsolute(String adUnitId, int width, int height, int x, int y, IListener listener) {
        Log.d("UnityPlugin", "showBannerAbsolute");
        bannerViewHandler.showAbsolute(adUnitId, width, height, x, y, listener);
    }

    public void showBannerRelative(String adUnitId, int width, int height, int position, int y, IListener listener) {
        Log.d("UnityPlugin", "showBannerRelative");
        bannerViewHandler.showRelative(adUnitId, width, height, position, y, listener);
    }

    public void showNativeAbsolute(String adUnitId, int width, int height, int x, int y, IListener listener) {
        Log.d("UnityPlugin", "showNativeAbsolute");
        nativeViewHandler.showAbsolute(adUnitId, width, height, x, y, listener);
    }

    public void showNativeRelative(String adUnitId, int width, int height, int position, int y, IListener listener) {
        Log.d("UnityPlugin", "showNativeRelative");
        nativeViewHandler.showRelative(adUnitId, width, height, position, y, listener);
    }

    public void playVideo(String adUnitId) {
        Log.d("UnityPlugin", "playVideo");
        nativeViewHandler.playVideo(adUnitId);
    }

    public void pauseVideo(String adUnitId) {
        Log.d("UnityPlugin", "pauseVideo");
        nativeViewHandler.pauseVideo(adUnitId);
    }

    public void muteVideo(String adUnitId, boolean mute) {
        Log.d("UnityPlugin", "muteVideo");
        nativeViewHandler.muteVideo(adUnitId, mute);
    }

    public boolean hasVideo(String adUnitId) {
        Log.d("UnityPlugin", "hasVideo");
        return nativeViewHandler.hasVideo(adUnitId);
    }

    public String getVideoMetaData(String adUnitId) {
        Log.d("UnityPlugin", "getVideoMetaData");
        return nativeViewHandler.getVideoMetaData(adUnitId);
    }

    public void destroy(String adUnitId) {
        Log.d("UnityPlugin", "destroy");
        ViewHandler.destroy(adUnitId);
    }

    public void pause(String adUnitId) {
        Log.d("UnityPlugin", "pause");
        ViewHandler.pause(adUnitId);
    }

    public void resume(String adUnitId) {
        Log.d("UnityPlugin", "resume");
        ViewHandler.resume(adUnitId);
    }

    public void loadInterstitial(String adUnitId, boolean immersiveMode, IListener listener) {
        Log.d("UnityPlugin", "loadInterstitialAd");
        interstitialHandler.create(adUnitId, immersiveMode, listener);
    }

    public void loadSplash(String adUnitId, IListener listener) {
        Log.d("UnityPlugin", "loadSplashAd");
        splashHandler.create(adUnitId, listener);
    }

    public void loadRewardedVideo(String adUnitId, IListener listener) {
        Log.d("UnityPlugin", "loadRewardedVideoAd");
        rewardedVideoHandler.create(adUnitId, listener);
    }

    public void loadNative(String adUnitId, int width, int height, IListener listener) {
        Log.d("UnityPlugin", "loadNativeAd");
        nativeViewHandler.load(adUnitId, width, height, listener);
    }

    public void showNativeAbsolute(String adUnitId, int x, int y) {
        Log.d("UnityPlugin", "showNativeAbsolute");
        nativeViewHandler.showAbsolute(adUnitId, x, y);
    }

    public void showNativeRelative(String adUnitId, int position, int y) {
        Log.d("UnityPlugin", "showNativeRelative");
        nativeViewHandler.showRelative(adUnitId, position, y);
    }

    public boolean isLoaded(String adUnitId) {
        Log.d("UnityPlugin", "isLoaded");
        if (ControllerHandler.isLoaded(adUnitId)) {
            return true;
        }
        return nativeViewHandler.isLoaded(adUnitId);
    }

    public void show(String adUnitId) {
        Log.d("UnityPlugin", "show");
        ControllerHandler.show(adUnitId);
    }
}
