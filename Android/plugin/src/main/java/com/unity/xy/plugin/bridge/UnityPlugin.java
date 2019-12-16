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
    private RelativeLayout layout;
    private Activity context;

    private void setActivity(Activity activity) {
        if (activity != context) {
            context = activity;
            context.runOnUiThread(() -> {
                layout = new RelativeLayout(context);
                context.addContentView(layout, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
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

    RelativeLayout getLayout() {
        return layout;
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

    public void showBannerAbsolute(String unitId, int width, int height, int x, int y, IListener listener) {
        Log.d("UnityPlugin", "showBannerAbsolute");
        bannerViewHandler.showAbsolute(unitId, width, height, x, y, listener);
    }

    public void showBannerRelative(String unitId, int width, int height, int position, int y, IListener listener) {
        Log.d("UnityPlugin", "showBannerRelative");
        bannerViewHandler.showRelative(unitId, width, height, position, y, listener);
    }

    public void showNativeAbsolute(String unitId, int width, int height, int x, int y, IListener listener) {
        Log.d("UnityPlugin", "showNativeAbsolute");
        nativeViewHandler.showAbsolute(unitId, width, height, x, y, listener);
    }

    public void showNativeRelative(String unitId, int width, int height, int position, int y, IListener listener) {
        Log.d("UnityPlugin", "showNativeRelative");
        nativeViewHandler.showRelative(unitId, width, height, position, y, listener);
    }

    public void playVideo(String unitId) {
        Log.d("UnityPlugin", "playVideo");
        nativeViewHandler.playVideo(unitId);
    }

    public void pauseVideo(String unitId) {
        Log.d("UnityPlugin", "pauseVideo");
        nativeViewHandler.pauseVideo(unitId);
    }

    public void muteVideo(String unitId, boolean mute) {
        Log.d("UnityPlugin", "muteVideo");
        nativeViewHandler.muteVideo(unitId, mute);
    }

    public boolean hasVideo(String unitId) {
        Log.d("UnityPlugin", "hasVideo");
        return nativeViewHandler.hasVideo(unitId);
    }

    public String getVideoMetaData(String unitId) {
        Log.d("UnityPlugin", "getVideoMetaData");
        return nativeViewHandler.getVideoMetaData(unitId);
    }

    public boolean isPlaying(String unitId) {
        Log.d("UnityPlugin", "isPlaying");
        return nativeViewHandler.isPlaying(unitId);
    }

    public boolean isEnded(String unitId) {
        Log.d("UnityPlugin", "isEnded");
        return nativeViewHandler.isEnded(unitId);
    }

    public void destroy(String unitId) {
        Log.d("UnityPlugin", "destroy");
        ViewHandler.destroy(unitId);
    }

    public void pause(String unitId) {
        Log.d("UnityPlugin", "pause");
        ViewHandler.pause(unitId);
    }

    public void resume(String unitId) {
        Log.d("UnityPlugin", "resume");
        ViewHandler.resume(unitId);
    }

    public void loadInterstitial(String unitId, boolean immersiveMode, IListener listener) {
        Log.d("UnityPlugin", "loadInterstitialAd");
        interstitialHandler.load(unitId, immersiveMode, listener);
    }

    public void loadSplash(String unitId, IListener listener) {
        Log.d("UnityPlugin", "loadSplashAd");
        splashHandler.load(unitId, listener);
    }

    public void loadRewardedVideo(String unitId, IListener listener) {
        Log.d("UnityPlugin", "loadRewardedVideoAd");
        rewardedVideoHandler.load(unitId, listener);
    }

    public void loadNative(String unitId, int width, int height, IListener listener) {
        Log.d("UnityPlugin", "loadNativeAd");
        nativeViewHandler.load(unitId, width, height, listener);
    }

    public void showNativeAbsolute(String unitId, int x, int y) {
        Log.d("UnityPlugin", "showNativeAbsolute");
        nativeViewHandler.showAbsolute(unitId, x, y);
    }

    public void showNativeRelative(String unitId, int position, int y) {
        Log.d("UnityPlugin", "showNativeRelative");
        nativeViewHandler.showRelative(unitId, position, y);
    }

    public boolean isLoaded(String unitId) {
        Log.d("UnityPlugin", "isLoaded");
        if (ControllerHandler.isLoaded(unitId)) {
            return true;
        }
        return nativeViewHandler.isLoaded(unitId);
    }

    public void show(String unitId) {
        Log.d("UnityPlugin", "show");
        ControllerHandler.show(unitId);
    }
}
