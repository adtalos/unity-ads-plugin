using System.Runtime.InteropServices;
using AOT;
using UnityEngine;
using UnityEngine.Android;
using System.Collections.Generic;
using System;

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
#if UNITY_IOS
        private static Dictionary<string, AdtalosListener> adtalosListenerDictionary = new Dictionary<string, AdtalosListener>();

        [MonoPInvokeCallback(typeof(AdtalosListenerProxy))]
        static void DefaultAdtalosListener(string adUnitId, string name, string data) {
            adtalosListenerDictionary[adUnitId]?.Invoke(adUnitId, name, data);
        }

        private void PreInitAdtalos() {
        }

        [DllImport("__Internal")]
        private static extern void _adtalosShowBannerAbsolute(string adUnitId, int width, int height, int x, int y, AdtalosListenerProxy listenerProxy);
        public void ShowBannerAbsolute(string adUnitId, int width, int height, int x, int y = 0, AdtalosListener listener = null) {
            adtalosListenerDictionary[adUnitId] = listener;
           _adtalosShowBannerAbsolute(adUnitId, width, height, x, y, DefaultAdtalosListener);
        }
        [DllImport("__Internal")]
        private static extern void _adtalosShowBannerRelative(string adUnitId, int width, int height, int position, int y, AdtalosListenerProxy listenerProxy);
        public void ShowBannerRelative(string adUnitId, int width, int height, int position, int y = 0, AdtalosListener listener = null) {
            adtalosListenerDictionary[adUnitId] = listener;
            _adtalosShowBannerRelative(adUnitId, width, height, position, y, DefaultAdtalosListener);
        }
        [DllImport("__Internal")]
        private static extern void _adtalosShowNativeAbsolute(string adUnitId, int width, int height, int x, int y, AdtalosListenerProxy listenerProxy);
        public void ShowNativeAbsolute(string adUnitId, int width, int height, int x, int y = 0, AdtalosListener listener = null)
        {
            adtalosListenerDictionary[adUnitId] = listener;
            _adtalosShowNativeAbsolute(adUnitId, width, height, x, y, DefaultAdtalosListener);
        }
        [DllImport("__Internal")]
        private static extern void _adtalosShowNativeRelative(string adUnitId, int width, int height, int position, int y, AdtalosListenerProxy listenerProxy);
        public void ShowNativeRelative(string adUnitId, int width, int height, int position, int y = 0, AdtalosListener listener = null)
        {
            adtalosListenerDictionary[adUnitId] = listener;
            _adtalosShowNativeRelative(adUnitId, width, height, position, y, DefaultAdtalosListener);
        }
        [DllImport("__Internal")]
        private static extern void _adtalosLoadNativeAd(string adUnitId, int width, int height, AdtalosListenerProxy listenerProxy);
        public void LoadNativeAd(string adUnitId, int width, int height, AdtalosListener listener = null) {
            adtalosListenerDictionary[adUnitId] = listener;
            _adtalosLoadNativeAd(adUnitId, width, height, DefaultAdtalosListener);
        }
        [DllImport("__Internal")]
        private static extern void _adtalosShowNativeAdAbsolute(string adUnitId, int x, int y);
        public void ShowNativeAbsolute(string adUnitId, int x, int y = 0) {
            _adtalosShowNativeAdAbsolute(adUnitId, x, y);
        }
        [DllImport("__Internal")]
        private static extern void _adtalosShowNativeAdRelative(string adUnitId, int position, int y);
        public void ShowNativeRelative(string adUnitId, int position, int y = 0) {
            _adtalosShowNativeAdRelative(adUnitId, position, y);
        }
        [DllImport("__Internal")]
        private static extern void _adtalosLoadInterstitialAd(string adUnitId, AdtalosListenerProxy listenerProxy);
        public void LoadInterstitialAd(string adUnitId, bool immersiveMode = true, AdtalosListener listener = null) {
            adtalosListenerDictionary[adUnitId] = listener;
            _adtalosLoadInterstitialAd(adUnitId, DefaultAdtalosListener);
        }
        [DllImport("__Internal")]
        private static extern void _adtalosLoadSplashAd(string adUnitId, AdtalosListenerProxy listenerProxy);
        public void LoadSplashAd(string adUnitId, AdtalosListener listener = null) {
            adtalosListenerDictionary[adUnitId] = listener;
            _adtalosLoadSplashAd(adUnitId, DefaultAdtalosListener);
        }
        [DllImport("__Internal")]
        private static extern void _adtalosLoadRewardedVideoAd(string adUnitId, AdtalosListenerProxy listenerProxy);
        public void LoadRewardedVideoAd(string adUnitId, AdtalosListener listener = null) {
            adtalosListenerDictionary[adUnitId] = listener;
            _adtalosLoadRewardedVideoAd(adUnitId, DefaultAdtalosListener);
        }
        [DllImport("__Internal")]
        private static extern bool _adtalosIsLoaded(string adUnitId);
        public void IsLoaded(string adUnitId) {
            _adtalosIsLoaded(adUnitId);
        }
        [DllImport("__Internal")]
        private static extern void _adtalosShow(string adUnitId);
        public void Show(string adUnitId) {
            _adtalosShow(adUnitId);
        }
        [DllImport("__Internal")]
        private static extern void _adtalosPlayVideo(string adUnitId);
        public void PlayVideo(string adUnitId) {
            _adtalosPlayVideo(adUnitId);
        }
        [DllImport("__Internal")]
        private static extern void _adtalosPauseVideo(string adUnitId);
        public void PauseVideo(string adUnitId) {
            _adtalosPauseVideo(adUnitId);
        }
        [DllImport("__Internal")]
        private static extern void _adtalosMuteVideo(string adUnitId, bool mute);
        public void MuteVideo(string adUnitId, bool mute) {
            _adtalosMuteVideo(adUnitId, mute);
        }
        [DllImport("__Internal")]
        private static extern bool _adtalosHasVideo(string adUnitId);
        public bool HasVideo(string adUnitId) {
            return _adtalosHasVideo(adUnitId);
        }
        [DllImport("__Internal")]
        private static extern string _adtalosGetVideoMetaData(string adUnitId);
        public AdtalosVideoMetadata GetVideoMetaData(string adUnitId) {
            string data = _adtalosGetVideoMetaData(adUnitId);
            if (data == null) return null;
            return new AdtalosVideoMetadata(data);
        }
        [DllImport("__Internal")]
        private static extern void _adtalosDestroy(string adUnitId);
        public void Destroy(string adUnitId) {
            _adtalosDestroy(adUnitId);
        }
        public void Pause(string adUnitId) {
            _adtalosPauseVideo(adUnitId);
        }
        public void Resume(string adUnitId) {
        }

#elif UNITY_ANDROID
        private AndroidJavaObject jadtalos;

        private void PreInitAdtalos() {
            if (jadtalos == null) {
                AndroidJavaClass adtalosUnityPluginClass = new AndroidJavaClass("com.unity.xy.plugin.bridge.UnityPlugin");
                jadtalos = adtalosUnityPluginClass.CallStatic<AndroidJavaObject>("getInstance");
                //if (!Permission.HasUserAuthorizedPermission("android.permission.READ_PHONE_STATE")) {
                //    Permission.RequestUserPermission("android.permission.READ_PHONE_STATE");
                //}
            }
        }

        private string oaid = string.Empty;
        public string OAID {
            get { return oaid; }
            set {
                oaid = value;
                jadtalos.Call("setOAID", oaid);
            }
        }

        public bool LandingPageDisplayActionBarEnabled {
            get {
                return jadtalos.Call<bool>("isLandingPageDisplayActionBarEnabled");
            }
            set {
                jadtalos.Call("setLandingPageDisplayActionBarEnabled", value);
            }
        }

        public bool LandingPageAnimationEnabled {
            get {
                return jadtalos.Call<bool>("isLandingPageAnimationEnabled");
            }
            set {
                jadtalos.Call("setLandingPageAnimationEnabled", value);
            }
        }

        public bool LandingPageFullScreenEnabled {
            get {
                return jadtalos.Call<bool>("isLandingPageFullScreenEnabled");
            }
            set {
                jadtalos.Call("setLandingPageFullScreenEnabled", value);
            }
        }

        public void ShowBannerAbsolute(string adUnitId, int width, int height, int x, int y = 0, AdtalosListener listener = null) {
            jadtalos.Call("showBannerAbsolute", adUnitId, width, height, x, y, new ListenerProxy(listener));
        }
        public void ShowBannerRelative(string adUnitId, int width, int height, int position, int y = 0, AdtalosListener listener = null) {
            jadtalos.Call("showBannerRelative", adUnitId, width, height, position, y, new ListenerProxy(listener));
        }
        public void ShowNativeAbsolute(string adUnitId, int width, int height, int x, int y = 0, AdtalosListener listener = null) {
            jadtalos.Call("showNativeAbsolute", adUnitId, width, height, x, y, new ListenerProxy(listener));
        }
        public void ShowNativeRelative(string adUnitId, int width, int height, int position, int y = 0, AdtalosListener listener = null) {
            jadtalos.Call("showNativeRelative", adUnitId, width, height, position, y, new ListenerProxy(listener));
        }
        public void LoadNativeAd(string adUnitId, int width, int height, AdtalosListener listener = null) {
            jadtalos.Call("loadNative", adUnitId, width, height, new ListenerProxy(listener));
        }
        public void ShowNativeAbsolute(string adUnitId, int x, int y = 0) {
            jadtalos.Call("showNativeAbsolute", adUnitId, x, y);
        }
        public void ShowNativeRelative(string adUnitId, int position, int y = 0) {
            jadtalos.Call("showNativeRelative", adUnitId, position, y);
        }
        public void LoadInterstitialAd(string adUnitId, bool immersiveMode = true, AdtalosListener listener = null) {
            jadtalos.Call("loadInterstitial", adUnitId, immersiveMode, new ListenerProxy(listener));
        }
        public void LoadSplashAd(string adUnitId, AdtalosListener listener = null) {
            jadtalos.Call("loadSplash", adUnitId, new ListenerProxy(listener));
        }
        public void LoadRewardedVideoAd(string adUnitId, AdtalosListener listener = null) {
            jadtalos.Call("loadRewardedVideo", adUnitId, new ListenerProxy(listener));
        }
        public void IsLoaded(string adUnitId) {
            jadtalos.Call("isLoaded", adUnitId);
        }
        public void Show(string adUnitId) {
            jadtalos.Call("show", adUnitId);
        }
        public void PlayVideo(string adUnitId) {
            jadtalos.Call("playVideo", adUnitId);
        }
        public void PauseVideo(string adUnitId) {
            jadtalos.Call("pauseVideo", adUnitId);
        }
        public void MuteVideo(string adUnitId, bool mute) {
            jadtalos.Call("muteVideo", adUnitId, mute);
        }
        public bool HasVideo(string adUnitId) {
            return jadtalos.Call<bool>("hasVideo", adUnitId);
        }
        public AdtalosVideoMetadata GetVideoMetaData(string adUnitId) {
            string data = jadtalos.Call<string>("getVideoMetaData", adUnitId);
            if (data == null) return null;
            return new AdtalosVideoMetadata(data);
        }
        public void Destroy(string adUnitId) {
            jadtalos.Call("destroy", adUnitId);
        }
        public void Pause(string adUnitId) {
            jadtalos.Call("pause", adUnitId);
        }
        public void Resume(string adUnitId) {
            jadtalos.Call("resume", adUnitId);
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
        public void LoadNativeAd(string adUnitId, int width, int height, AdtalosListener listener = null) {
            Debug.Log("calling LoadNativeAd");
        }
        public void ShowNativeAbsolute(string adUnitId, int x, int y = 0) {
            Debug.Log("calling ShowNativeAbsolute");
        }
        public void ShowNativeRelative(string adUnitId, int position, int y = 0) {
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
