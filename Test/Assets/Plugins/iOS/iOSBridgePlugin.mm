#import <Foundation/Foundation.h>
#import "UnityAppController.h"
#import "iOSBridgePlugin.h"
#import "AdtalosSDK/AdtalosSDK.h"

static NSMutableDictionary *adViews = [NSMutableDictionary new];
static NSMutableDictionary *listeners = [NSMutableDictionary new];
static NSMutableDictionary *ads = [NSMutableDictionary new];

@interface AdtalosBridgePluginListener: NSObject<AdtalosListener, AdtalosVideoListener>

- (void) onAdRendered;
- (void) onAdImpressionFinished;
- (void) onAdImpressionReceivedError:(NSError *)error;
- (void) onAdLoaded;
- (void) onAdFailedToLoad:(NSError *)error;
- (void) onAdClicked;
- (void) onAdLeftApplication;
- (void) onAdOpened;
- (void) onAdClosed;
- (void) onAdCustomEvent:(NSString *)name withData:(NSString *)data;

- (void) onVideoLoad:(NSDictionary *)metadata;
- (void) onVideoStart;
- (void) onVideoPlay;
- (void) onVideoPause;
- (void) onVideoEnd;
- (void) onVideoVolumeChange:(double)volume muted:(BOOL)muted;
- (void) onVideoTimeUpdate:(double)currentTime duration:(double)duration;
- (void) onVideoError;
- (void) onVideoBreak;

@end

@implementation AdtalosBridgePluginListener {
    AdtalosListenerProxy _listenerProxy;
    NSString *_adUnitId;
}

- (instancetype)init:(AdtalosListenerProxy)listenerProxy withAdUnitId:(NSString *)adUnitId {
    if (self = [super init]) {
        _listenerProxy = listenerProxy;
        _adUnitId = adUnitId;
    }
    return self;
}

- (void) onAdRendered {
    _listenerProxy([_adUnitId UTF8String], "onRendered", "");
}

- (void) onAdImpressionFinished {
    _listenerProxy([_adUnitId UTF8String], "onImpressionFinished", "");
}

- (void) onAdImpressionReceivedError:(NSError *)error {
    _listenerProxy([_adUnitId UTF8String], "onImpressionReceivedError", [error.localizedDescription UTF8String]);
}

- (void) onAdLoaded {
    _listenerProxy([_adUnitId UTF8String], "onLoaded", "");
}

- (void) onAdFailedToLoad:(NSError *)error {
    _listenerProxy([_adUnitId UTF8String], "onFailedToLoad", [error.localizedDescription UTF8String]);
}

- (void) onAdClicked {
    _listenerProxy([_adUnitId UTF8String], "onClicked", "");
}

- (void) onAdLeftApplication {
    _listenerProxy([_adUnitId UTF8String], "onLeftApplication", "");
}

- (void) onAdOpened {
    _listenerProxy([_adUnitId UTF8String], "onOpened", "");
}

- (void) onAdClosed {
    _listenerProxy([_adUnitId UTF8String], "onClosed", "");
}

- (void) onAdCustomEvent:(NSString *)name withData:(NSString *)data {
    _listenerProxy([_adUnitId UTF8String], [name UTF8String], [data UTF8String]);
}

- (void) onVideoLoad:(NSDictionary *)metadata {
    _listenerProxy([_adUnitId UTF8String], "onVideoLoad", (const char *)[[NSJSONSerialization dataWithJSONObject:metadata options:NSJSONWritingPrettyPrinted error:nil] bytes]);
}

- (void) onVideoStart {
    _listenerProxy([_adUnitId UTF8String], "onVideoStart", "");
}

- (void) onVideoPlay {
    _listenerProxy([_adUnitId UTF8String], "onVideoPlay", "");
}

- (void) onVideoPause {
    _listenerProxy([_adUnitId UTF8String], "onVideoPause", "");
}

- (void) onVideoEnd {
    _listenerProxy([_adUnitId UTF8String], "onVideoEnd", "");
}

- (void) onVideoVolumeChange:(double)volume muted:(BOOL)muted {
    _listenerProxy([_adUnitId UTF8String], "onVideoVolumeChange", (const char *)[[NSJSONSerialization dataWithJSONObject:@{@"volume": [NSNumber numberWithDouble:volume], @"muted":[NSNumber numberWithBool:muted]} options:NSJSONWritingPrettyPrinted error:nil] bytes]);
}

- (void) onVideoTimeUpdate:(double)currentTime duration:(double)duration {
    _listenerProxy([_adUnitId UTF8String], "onVideoTimeUpdate", (const char *)[[NSJSONSerialization dataWithJSONObject:@{@"currentTime":[NSNumber numberWithDouble:currentTime], @"duration":[NSNumber numberWithDouble:duration]} options:NSJSONWritingPrettyPrinted error:nil] bytes]);
}

- (void) onVideoError {
    _listenerProxy([_adUnitId UTF8String], "onVideoError", "");
}

- (void) onVideoBreak {
    _listenerProxy([_adUnitId UTF8String], "onVideoBreak", "");
}

@end

void _adtalosShowAdAbsolute(const char *adUnitId, int width, int height, int x, int y, CGFloat aspectRatio, AdtalosListenerProxy listenerProxy) {
    if (width <= 0 || height <= 0) {
        width = (int)[UIScreen mainScreen].bounds.size.width;
        height = (int)(width * aspectRatio);
        if (y == [UIScreen mainScreen].bounds.size.height) {
            y -= height;
        }
    }
    NSString *unitId = [[NSString alloc] initWithUTF8String:adUnitId];
    AdtalosAdView *adView = [[AdtalosAdView alloc] initWithFrame:CGRectMake(x, y, width, height)];
    adView.autoresizingMask = UIViewAutoresizingFlexibleLeftMargin | UIViewAutoresizingFlexibleRightMargin;
    adViews[unitId] = adView;
    AdtalosBridgePluginListener *listener = [[AdtalosBridgePluginListener alloc] init:listenerProxy withAdUnitId:unitId];
    listeners[unitId] = listener;
    adView.delegate = listener;
    adView.videoController.delegate = listener;
    [adView loadAd:unitId];
    [GetAppController().rootView addSubview:adView];
}

void _adtalosShowBannerAbsolute(const char *adUnitId, int width, int height, int x, int y, AdtalosListenerProxy listenerProxy) {
    _adtalosShowAdAbsolute(adUnitId, width, height, x, y, 5.0 / 32.0, listenerProxy);
}

void _adtalosShowNativeAbsolute(const char *adUnitId, int width, int height, int x, int y, AdtalosListenerProxy listenerProxy) {
    _adtalosShowAdAbsolute(adUnitId, width, height, x, y, 5.0 / 7.0, listenerProxy);
}

typedef NS_ENUM(int, AdtalosAdPosition) {
    AdtalosAdPositionAbsolute         = 0,
    AdtalosAdPositionTopLeft          = 1,
    AdtalosAdPositionTopCenter        = 2,
    AdtalosAdPositionTopRight         = 3,
    AdtalosAdPositionMiddleLeft       = 4,
    AdtalosAdPositionMiddleCenter     = 5,
    AdtalosAdPositionMiddleRight      = 6,
    AdtalosAdPositionBottomLeft       = 7,
    AdtalosAdPositionBottomCenter     = 8,
    AdtalosAdPositionBottomRight      = 9
};

void _adtalosShowRelative(const char *adUnitId, int width, int height, int position, int y, CGFloat aspectRatio, AdtalosListenerProxy listenerProxy) {
    if (width <= 0 || height <= 0) {
        width = (int)[UIScreen mainScreen].bounds.size.width;
        height = (int)(width * aspectRatio);
    }
    CGFloat left_x = 0;
    CGFloat top_y = 0;
    CGFloat right_x = [UIScreen mainScreen].bounds.size.width - width;
    CGFloat bottom_y = [UIScreen mainScreen].bounds.size.height - height;
    CGFloat center_x = right_x / 2;
    CGFloat middle_y = bottom_y / 2;
    CGPoint point;
    UIViewAutoresizing autoresizingMask;
    switch((AdtalosAdPosition)position) {
        case AdtalosAdPositionAbsolute:
        case AdtalosAdPositionTopLeft:
            point.x = left_x;
            point.y = top_y + y;
            autoresizingMask = UIViewAutoresizingFlexibleRightMargin | UIViewAutoresizingFlexibleBottomMargin;
            break;
        case AdtalosAdPositionTopCenter:
            point.x = center_x;
            point.y = top_y + y;
            autoresizingMask = UIViewAutoresizingFlexibleLeftMargin | UIViewAutoresizingFlexibleRightMargin | UIViewAutoresizingFlexibleBottomMargin;
            break;
        case AdtalosAdPositionTopRight:
            point.x = right_x;
            point.y = top_y + y;
            autoresizingMask = UIViewAutoresizingFlexibleLeftMargin | UIViewAutoresizingFlexibleBottomMargin;
            break;
        case AdtalosAdPositionMiddleLeft:
            point.x = left_x;
            point.y = middle_y + y;
            autoresizingMask = UIViewAutoresizingFlexibleRightMargin | UIViewAutoresizingFlexibleTopMargin | UIViewAutoresizingFlexibleBottomMargin;
            break;
        case AdtalosAdPositionMiddleCenter:
            point.x = center_x;
            point.y = middle_y + y;
            autoresizingMask = UIViewAutoresizingFlexibleLeftMargin | UIViewAutoresizingFlexibleRightMargin | UIViewAutoresizingFlexibleTopMargin | UIViewAutoresizingFlexibleBottomMargin;
            break;
        case AdtalosAdPositionMiddleRight:
            point.x = right_x;
            point.y = middle_y + y;
            autoresizingMask = UIViewAutoresizingFlexibleLeftMargin | UIViewAutoresizingFlexibleTopMargin | UIViewAutoresizingFlexibleBottomMargin;
            break;
        case AdtalosAdPositionBottomLeft:
            point.x = left_x;
            point.y = bottom_y + y;
            autoresizingMask = UIViewAutoresizingFlexibleRightMargin | UIViewAutoresizingFlexibleTopMargin;
            break;
        case AdtalosAdPositionBottomCenter:
            point.x = center_x;
            point.y = bottom_y + y;
            autoresizingMask = UIViewAutoresizingFlexibleLeftMargin | UIViewAutoresizingFlexibleRightMargin | UIViewAutoresizingFlexibleTopMargin;
            break;
        case AdtalosAdPositionBottomRight:
            point.x = right_x;
            point.y = bottom_y + y;
            autoresizingMask = UIViewAutoresizingFlexibleLeftMargin | UIViewAutoresizingFlexibleTopMargin;
            break;
    }
    NSString *unitId = [[NSString alloc] initWithUTF8String:adUnitId];
    AdtalosAdView *adView = [[AdtalosAdView alloc] initWithFrame:CGRectMake(point.x, point.y, width, height)];
    adView.autoresizingMask = autoresizingMask;
    adViews[unitId] = adView;
    AdtalosBridgePluginListener *listener = [[AdtalosBridgePluginListener alloc] init:listenerProxy withAdUnitId:unitId];
    listeners[unitId] = listener;
    adView.delegate = listener;
    adView.videoController.delegate = listener;
    [adView loadAd:unitId];
    [GetAppController().rootView addSubview:adView];
}

void _adtalosShowBannerRelative(const char *adUnitId, int width, int height, int position, int y, AdtalosListenerProxy listenerProxy) {
    _adtalosShowRelative(adUnitId, width, height, position, y, 5.0 / 32.0, listenerProxy);
}

void _adtalosShowNativeRelative(const char *adUnitId, int width, int height, int position, int y, AdtalosListenerProxy listenerProxy) {
    _adtalosShowRelative(adUnitId, width, height, position, y, 5.0 / 7.0, listenerProxy);
}

void _adtalosLoadNativeAd(const char *adUnitId, int width, int height, AdtalosListenerProxy listenerProxy) {
    if (width <= 0 || height <= 0) {
        width = (int)[UIScreen mainScreen].bounds.size.width;
        height = (int)(width * 5.0 / 7.0);
    }
    NSString *unitId = [[NSString alloc] initWithUTF8String:adUnitId];
    AdtalosAdView *adView = [[AdtalosAdView alloc] initWithFrame:CGRectMake(0, 0, width, height)];
    adViews[unitId] = adView;
    AdtalosBridgePluginListener *listener = [[AdtalosBridgePluginListener alloc] init:listenerProxy withAdUnitId:unitId];
    listeners[unitId] = listener;
    adView.delegate = listener;
    adView.videoController.delegate = listener;
    [adView loadAd:unitId autoShow:NO];
}

void _adtalosShowNativeAdAbsolute(const char *adUnitId, int x, int y) {
    NSString *unitId = [[NSString alloc] initWithUTF8String:adUnitId];
    AdtalosAdView *adView = adViews[unitId];
    if (adView != nil) {
        if (y == [UIScreen mainScreen].bounds.size.height) {
            y = (int)(y - adView.frame.size.height);
        }
        adView.frame = CGRectMake(x, y, adView.frame.size.width, adView.frame.size.height);
        adView.autoresizingMask = UIViewAutoresizingFlexibleLeftMargin | UIViewAutoresizingFlexibleRightMargin;
        [GetAppController().rootView addSubview:adView];
        [adView show];
    }
}

void _adtalosShowNativeAdRelative(const char *adUnitId, int position, int y) {
    NSString *unitId = [[NSString alloc] initWithUTF8String:adUnitId];
    AdtalosAdView *adView = adViews[unitId];
    if (adView != nil) {
        CGFloat left_x = 0;
        CGFloat top_y = 0;
        CGFloat right_x = [UIScreen mainScreen].bounds.size.width - adView.frame.size.width;
        CGFloat bottom_y = [UIScreen mainScreen].bounds.size.height - adView.frame.size.height;
        CGFloat center_x = right_x / 2;
        CGFloat middle_y = bottom_y / 2;
        CGPoint point;
        UIViewAutoresizing autoresizingMask;
        switch((AdtalosAdPosition)position) {
            case AdtalosAdPositionAbsolute:
            case AdtalosAdPositionTopLeft:
                point.x = left_x;
                point.y = top_y + y;
                autoresizingMask = UIViewAutoresizingFlexibleRightMargin | UIViewAutoresizingFlexibleBottomMargin;
                break;
            case AdtalosAdPositionTopCenter:
                point.x = center_x;
                point.y = top_y + y;
                autoresizingMask = UIViewAutoresizingFlexibleLeftMargin | UIViewAutoresizingFlexibleRightMargin | UIViewAutoresizingFlexibleBottomMargin;
                break;
            case AdtalosAdPositionTopRight:
                point.x = right_x;
                point.y = top_y + y;
                autoresizingMask = UIViewAutoresizingFlexibleLeftMargin | UIViewAutoresizingFlexibleBottomMargin;
                break;
            case AdtalosAdPositionMiddleLeft:
                point.x = left_x;
                point.y = middle_y + y;
                autoresizingMask = UIViewAutoresizingFlexibleRightMargin | UIViewAutoresizingFlexibleTopMargin | UIViewAutoresizingFlexibleBottomMargin;
                break;
            case AdtalosAdPositionMiddleCenter:
                point.x = center_x;
                point.y = middle_y + y;
                autoresizingMask = UIViewAutoresizingFlexibleLeftMargin | UIViewAutoresizingFlexibleRightMargin | UIViewAutoresizingFlexibleTopMargin | UIViewAutoresizingFlexibleBottomMargin;
                break;
            case AdtalosAdPositionMiddleRight:
                point.x = right_x;
                point.y = middle_y + y;
                autoresizingMask = UIViewAutoresizingFlexibleLeftMargin | UIViewAutoresizingFlexibleTopMargin | UIViewAutoresizingFlexibleBottomMargin;
                break;
            case AdtalosAdPositionBottomLeft:
                point.x = left_x;
                point.y = bottom_y + y;
                autoresizingMask = UIViewAutoresizingFlexibleRightMargin | UIViewAutoresizingFlexibleTopMargin;
                break;
            case AdtalosAdPositionBottomCenter:
                point.x = center_x;
                point.y = bottom_y + y;
                autoresizingMask = UIViewAutoresizingFlexibleLeftMargin | UIViewAutoresizingFlexibleRightMargin | UIViewAutoresizingFlexibleTopMargin;
                break;
            case AdtalosAdPositionBottomRight:
                point.x = right_x;
                point.y = bottom_y + y;
                autoresizingMask = UIViewAutoresizingFlexibleLeftMargin | UIViewAutoresizingFlexibleTopMargin;
                break;
        }
        adView.frame = CGRectMake(point.x, point.y, adView.frame.size.width, adView.frame.size.height);
        adView.autoresizingMask = autoresizingMask;
        [GetAppController().rootView addSubview:adView];
        [adView show];
    }
}

void _adtalosLoadInterstitialAd(const char *adUnitId, AdtalosListenerProxy listenerProxy) {
    NSString *unitId = [[NSString alloc] initWithUTF8String:adUnitId];
    AdtalosInterstitialAd *ad = [[AdtalosInterstitialAd alloc] init:unitId];
    AdtalosBridgePluginListener *listener = [[AdtalosBridgePluginListener alloc] init:listenerProxy withAdUnitId:unitId];
    listeners[unitId] = listener;
    ad.delegate = listener;
    ad.videoDelegate = listener;
    ads[unitId] = ad;
}

void _adtalosLoadSplashAd(const char *adUnitId, AdtalosListenerProxy listenerProxy) {
    NSString *unitId = [[NSString alloc] initWithUTF8String:adUnitId];
    AdtalosSplashAd *ad = [[AdtalosSplashAd alloc] init:unitId];
    AdtalosBridgePluginListener *listener = [[AdtalosBridgePluginListener alloc] init:listenerProxy withAdUnitId:unitId];
    listeners[unitId] = listener;
    ad.delegate = listener;
    ad.videoDelegate = listener;
    ads[unitId] = ad;
}

void _adtalosLoadRewardedVideoAd(const char *adUnitId, AdtalosListenerProxy listenerProxy) {
    NSString *unitId = [[NSString alloc] initWithUTF8String:adUnitId];
    AdtalosRewardedVideoAd *ad = [[AdtalosRewardedVideoAd alloc] init:unitId];
    AdtalosBridgePluginListener *listener = [[AdtalosBridgePluginListener alloc] init:listenerProxy withAdUnitId:unitId];
    listeners[unitId] = listener;
    ad.delegate = listener;
    ad.videoDelegate = listener;
    ads[unitId] = ad;
}

bool _adtalosIsLoaded(const char *adUnitId) {
    NSString *unitId = [[NSString alloc] initWithUTF8String:adUnitId];
    AdtalosInterstitialAd *ad = ads[unitId];
    if (ad != nil) {
        return (bool)[ad isLoaded];
    }
    AdtalosAdView *adView = adViews[unitId];
    if (adView != nil) {
        return (bool)[adView isLoaded];
    }
    return false;
}

void _adtalosShow(const char *adUnitId) {
    NSString *unitId = [[NSString alloc] initWithUTF8String:adUnitId];
    AdtalosInterstitialAd *ad = ads[unitId];
    if (ad != nil) {
        [ad show];
    }
}

void _adtalosPlayVideo(const char *adUnitId) {
    NSString *unitId = [[NSString alloc] initWithUTF8String:adUnitId];
    AdtalosAdView *adView = adViews[unitId];
    if (adView != nil) {
        [adView.videoController play];
    }
}

void _adtalosPauseVideo(const char *adUnitId) {
    NSString *unitId = [[NSString alloc] initWithUTF8String:adUnitId];
    AdtalosAdView *adView = adViews[unitId];
    if (adView != nil) {
        [adView.videoController pause];
    }
}

void _adtalosMuteVideo(const char *adUnitId, bool mute) {
    NSString *unitId = [[NSString alloc] initWithUTF8String:adUnitId];
    AdtalosAdView *adView = adViews[unitId];
    if (adView != nil) {
        [adView.videoController mute:(BOOL)mute];
    }
}

bool _adtalosHasVideo(const char *adUnitId) {
    NSString *unitId = [[NSString alloc] initWithUTF8String:adUnitId];
    AdtalosAdView *adView = adViews[unitId];
    if (adView != nil) {
        return (bool)[adView.videoController hasVideo];
    }
    return false;
}

const char * _adtalosGetVideoMetaData(const char *adUnitId) {
    NSString *unitId = [[NSString alloc] initWithUTF8String:adUnitId];
    AdtalosAdView *adView = adViews[unitId];
    if (adView != nil) {
        return (const char *)[[NSJSONSerialization dataWithJSONObject:adView.videoController.metadata options:NSJSONWritingPrettyPrinted error:nil] bytes];
    }
    return nil;
}

void _adtalosDestroy(const char *adUnitId) {
    NSString *unitId = [[NSString alloc] initWithUTF8String:adUnitId];
    AdtalosAdView *adView = adViews[unitId];
    if (adView != nil) {
        [adViews removeObjectForKey:unitId];
        [adView removeFromSuperview];
    }
    AdtalosInterstitialAd *ad = ads[unitId];
    if (ad != nil) {
        [ads removeObjectForKey:unitId];
    }
    AdtalosBridgePluginListener *listener = listeners[unitId];
    if (listener != nil) {
        [listeners removeObjectForKey:unitId];
    }
}
