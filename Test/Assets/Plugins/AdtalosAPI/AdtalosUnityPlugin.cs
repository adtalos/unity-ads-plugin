using UnityEngine;
using UnityEngine.Android;
namespace Adtalos {
    public class AdtalosUnityPlugin {
        private static AdtalosUnityPlugin _instance;

        public static AdtalosUnityPlugin Instance() {
            if (_instance == null) {
                _instance = new AdtalosUnityPlugin();
                _instance.PreInitAdtalos();
            }
            return _instance;
        }
#if UNITY_ANDROID
        private AndroidJavaObject jadtalos;

        private void PreInitAdtalos() {
            if (jadtalos == null) {
                AndroidJavaClass adtalosUnityPluginClass = new AndroidJavaClass("com.adtalos.ads.plugin.AdtalosUnityPlugin");
                jadtalos = adtalosUnityPluginClass.CallStatic<AndroidJavaObject>("getInstance");
                if (!Permission.HasUserAuthorizedPermission("android.permission.READ_PHONE_STATE")) {
                    Permission.RequestUserPermission("android.permission.READ_PHONE_STATE");
                }
            }
        }
        public void ShowBannerAbsolute(string adUnitId, int width, int height, int x, int y = 0, AdtalosListener listener = null) {
            jadtalos.Call("showBannerAbsolute", new object[] { adUnitId, width, height, x, y, new AdtalosListenerProxy(listener) });
        }
        public void ShowBannerRelative(string adUnitId, int width, int height, int position, int y = 0, AdtalosListener listener = null) {
            jadtalos.Call("showBannerRelative", new object[] { adUnitId, width, height, position, y, new AdtalosListenerProxy(listener) });
        }
        public void ShowNativeAbsolute(string adUnitId, int width, int height, int x, int y = 0, AdtalosListener listener = null) {
            jadtalos.Call("showNativeAbsolute", new object[] { adUnitId, width, height, x, y, new AdtalosListenerProxy(listener) });
        }
        public void ShowNativeRelative(string adUnitId, int width, int height, int position, int y = 0, AdtalosListener listener = null) {
            jadtalos.Call("showNativeRelative", new object[] { adUnitId, width, height, position, y, new AdtalosListenerProxy(listener) });
        }
        public void LoadInterstitialAd(string adUnitId, bool immersiveMode = true, AdtalosListener listener = null) {
            jadtalos.Call("loadInterstitialAd", new object[] { adUnitId, immersiveMode, new AdtalosListenerProxy(listener) });
        }
        public void LoadSplashAd(string adUnitId, AdtalosListener listener = null) {
            jadtalos.Call("loadSplashAd", new object[] { adUnitId, new AdtalosListenerProxy(listener) });
        }
        public void LoadRewardedVideoAd(string adUnitId, AdtalosListener listener = null) {
            jadtalos.Call("loadRewardedVideoAd", new object[] { adUnitId, new AdtalosListenerProxy(listener) });
        }
        public void IsLoaded(string adUnitId) {
            jadtalos.Call("isLoaded", new object[] { adUnitId });
        }
        public void Show(string adUnitId) {
            jadtalos.Call("show", new object[] { adUnitId });
        }
        public void PlayVideo(string adUnitId) {
            jadtalos.Call("playVideo", new object[] { adUnitId });
        }
        public void PauseVideo(string adUnitId) {
            jadtalos.Call("pauseVideo", new object[] { adUnitId });
        }
        public void MuteVideo(string adUnitId, bool mute) {
            jadtalos.Call("muteVideo", new object[] { adUnitId, mute });
        }
        public bool HasVideo(string adUnitId) {
            return jadtalos.Call<bool>("hasVideo", new object[] { adUnitId });
        }
        public AdtalosVideoMetadata GetVideoMetaData(string adUnitId) {
            string data = jadtalos.Call<string>("getVideoMetaData", new object[] { adUnitId });
            if (data == null) return null;
            return new AdtalosVideoMetadata(data);
        }
        public void Destroy(string adUnitId) {
            jadtalos.Call("destroy", new object[] { adUnitId });
        }
        public void Pause(string adUnitId) {
            jadtalos.Call("pause", new object[] { adUnitId });
        }
        public void Resume(string adUnitId) {
            jadtalos.Call("resume", new object[] { adUnitId });
        }
#else
        private void PreInitAdtalos() {}

        public void ShowBannerAbsolute(string adUnitId, int width, int height, int x, int y = 0, AdtalosListener listener = null) {
            Debug.Log("calling ShowBannerAbsolute");
        }
        public void ShowBannerRelative(string adUnitId, int width, int height, int x, int y = 0, AdtalosListener listener = null) {
            Debug.Log("calling ShowBannerRelative");
        }
        public void ShowNativeAbsolute(string adUnitId, int width, int height, int x, int y = 0, AdtalosListener listener = null) {
            Debug.Log("calling ShowNativeAbsolute");
        }
        public void ShowNativeRelative(string adUnitId, int width, int height, int position, int y = 0, AdtalosListener listener = null) {
            Debug.Log("calling ShowNativeRelative");
        }
        public void LoadInterstitialAd(string adUnitId, bool immersiveMode = true, AdtalosListener listener = null) {
            Debug.Log("calling LoadInterstitialAd");
        }
        public void LoadSplashAd(string adUnitId, AdtalosListener listener = null) {
            Debug.Log("calling LoadSplashAd");
        }
        public void LoadRewardedVideoAd(string adUnitId, AdtalosListener listener = null) {
            Debug.Log("calling LoadRewardedVideoAd");
        }
        public void IsLoaded(string adUnitId) {
            Debug.Log("calling IsLoaded");
        }
        public void Show(string adUnitId) {
            Debug.Log("calling Show");
        }
        public void PlayVideo(string adUnitId) {
            Debug.Log("calling PlayVideo");
        }
        public void PauseVideo(string adUnitId) {
            Debug.Log("calling PauseVideo");
        }
        public void MuteVideo(string adUnitId, bool mute) {
            Debug.Log("calling MuteVideo");
        }
        public bool HasVideo(string adUnitId) {
            Debug.Log("calling HasVideo");
            return false;
        }
        public AdtalosVideoMetadata GetVideoMetaData(string adUnitId) {
            Debug.Log("calling GetVideoMetaData");
            return null;
        }
        public void Destroy(string adUnitId) {
            Debug.Log("calling destroy");
        }
        public void Pause(string adUnitId) {
            Debug.Log("calling Pause");
        }
        public void Resume(string adUnitId) {
            Debug.Log("calling Resume");
        }

#endif
    }
}
