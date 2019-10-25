extern "C" {
    typedef void (*AdtalosListenerProxy) (const char *adUnitId, const char *name, const char *data);
    void _adtalosShowBannerAbsolute(const char *adUnitId, int width, int height, int x, int y, AdtalosListenerProxy listenerProxy);
    void _adtalosShowBannerRelative(const char *adUnitId, int width, int height, int position, int y, AdtalosListenerProxy listenerProxy);
    void _adtalosShowNativeAbsolute(const char *adUnitId, int width, int height, int x, int y, AdtalosListenerProxy listenerProxy);
    void _adtalosShowNativeRelative(const char *adUnitId, int width, int height, int position, int y, AdtalosListenerProxy listenerProxy);
    void _adtalosLoadNativeAd(const char *adUnitId, int width, int height, AdtalosListenerProxy listenerProxy);
    void _adtalosShowNativeAdAbsolute(const char *adUnitId, int x, int y);
    void _adtalosShowNativeAdRelative(const char *adUnitId, int position, int y);
    void _adtalosLoadInterstitialAd(const char *adUnitId, AdtalosListenerProxy listenerProxy);
    void _adtalosLoadSplashAd(const char *adUnitId, AdtalosListenerProxy listenerProxy);
    void _adtalosLoadRewardedVideoAd(const char *adUnitId, AdtalosListenerProxy listenerProxy);
    bool _adtalosIsLoaded(const char *adUnitId);
    void _adtalosShow(const char *adUnitId);
    void _adtalosPlayVideo(const char *adUnitId);
    void _adtalosPauseVideo(const char *adUnitId);
    void _adtalosMuteVideo(const char *adUnitId, bool mute);
    bool _adtalosHasVideo(const char *adUnitId);
    const char * _adtalosGetVideoMetaData(const char *adUnitId);
    void _adtalosDestroy(const char *adUnitId);
}
