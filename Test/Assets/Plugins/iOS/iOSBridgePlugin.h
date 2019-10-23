#import "AdtalosSDK/AdtalosSDK.h"

extern "C" {
    typedef void (*AdtalosListener) (const char *adUnitId, const char *name, const char *data);
    void _adtalosShowBannerAbsolute(char *adUnitId, int width, int height, int x, int y, AdtalosListener listener);
    void _adtalosShowBannerRelative(char *adUnitId, int width, int height, int position, int y, AdtalosListener listener);
    void _adtalosShowNativeAbsolute(char *adUnitId, int width, int height, int x, int y, AdtalosListener listener);
    void _adtalosShowNativeRelative(char *adUnitId, int width, int height, int position, int y, AdtalosListener listener);
}
