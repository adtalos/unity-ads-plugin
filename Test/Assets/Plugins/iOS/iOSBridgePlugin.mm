#import "UnityAppController.h"
#import "iOSBridgePlugin.h"

void _adtalosShowAdAbsolute(char *adUnitId, int width, int height, int x, int y, CGFloat aspectRatio, AdtalosListener listener) {
    if (width <= 0 || height <= 0) {
        width = (int)[UIScreen mainScreen].bounds.size.width;
        height = (int)(width * aspectRatio);
        if (y == [UIScreen mainScreen].bounds.size.height) {
            y -= height;
        }
    }
    AdtalosAdView *adView = [[AdtalosAdView alloc] initWithFrame:CGRectMake(x, y, width, height)];
    adView.autoresizingMask = UIViewAutoresizingFlexibleLeftMargin | UIViewAutoresizingFlexibleRightMargin;
    [adView loadAd: [[NSString alloc] initWithUTF8String:adUnitId]];
    [GetAppController().rootView addSubview:adView];
}

void _adtalosShowBannerAbsolute(char *adUnitId, int width, int height, int x, int y, AdtalosListener listener) {
    _adtalosShowAdAbsolute(adUnitId, width, height, x, y, 5.0 / 32.0, listener);
}

void _adtalosShowNativeAbsolute(char *adUnitId, int width, int height, int x, int y, AdtalosListener listener) {
    _adtalosShowAdAbsolute(adUnitId, width, height, x, y, 5.0 / 7.0, listener);
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

void _adtalosShowRelative(char *adUnitId, int width, int height, int position, int y, CGFloat aspectRatio, AdtalosListener listener) {
    if (width <= 0 || height <= 0) {
        width = (int)[UIScreen mainScreen].bounds.size.width;
        height = (int)(width * aspectRatio);
        if (y == [UIScreen mainScreen].bounds.size.height) {
            y -= height;
        }
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
    AdtalosAdView *adView = [[AdtalosAdView alloc] initWithFrame:CGRectMake(point.x, point.y, width, height)];
    adView.autoresizingMask = autoresizingMask;
    [adView loadAd: [[NSString alloc] initWithUTF8String:adUnitId]];
    [GetAppController().rootView addSubview:adView];
}

void _adtalosShowBannerRelative(char *adUnitId, int width, int height, int position, int y, AdtalosListener listener) {
    _adtalosShowRelative(adUnitId, width, height, position, y, 5.0 / 32.0, listener);
}

void _adtalosShowNativeRelative(char *adUnitId, int width, int height, int position, int y, AdtalosListener listener) {
    _adtalosShowRelative(adUnitId, width, height, position, y, 5.0 / 7.0, listener);
}
