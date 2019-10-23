extern "C" {
    typedef void (*AdtalosListenerProxy) (const char *adUnitId, const char *name, const char *data);
    void _adtalosShowBannerAbsolute(const char *adUnitId, int width, int height, int x, int y, AdtalosListenerProxy listenerProxy);
    void _adtalosShowBannerRelative(const char *adUnitId, int width, int height, int position, int y, AdtalosListenerProxy listenerProxy);
    void _adtalosShowNativeAbsolute(const char *adUnitId, int width, int height, int x, int y, AdtalosListenerProxy listenerProxy);
    void _adtalosShowNativeRelative(const char *adUnitId, int width, int height, int position, int y, AdtalosListenerProxy listenerProxy);
}
