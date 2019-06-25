package com.adtalos.ads.plugin;

import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;

import com.adtalos.ads.sdk.AdView;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

abstract class AdViewHandler extends AbstractHandler {

    static final int ABSOLUTE = 0;
    static final int TOP_LEFT = 1;
    static final int TOP_CENTER = 2;
    static final int TOP_RIGHT = 3;
    static final int MIDDLE_LEFT = 4;
    static final int MIDDLE_CENTER = 5;
    static final int MIDDLE_RIGHT = 6;
    static final int BOTTOM_LEFT = 7;
    static final int BOTTOM_CENTER = 8;
    static final int BOTTOM_RIGHT = 9;

    static Map<String, AdView> adViews = new ConcurrentHashMap<>();

    public abstract void prepare(String adUnitId, int width, int height, IAdtalosListener listener);

    LayoutParams getAbsoluteLayoutParams(int x, int y) {
        LayoutParams layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        layoutParams.leftMargin = x;
        layoutParams.topMargin = y;
        layoutParams.alignWithParent = true;
        return layoutParams;
    }

    LayoutParams getRelationLayoutParams(int position, int y) {
        LayoutParams layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        if (y > 0) {
            layoutParams.topMargin = y;
        } else if (y < 0) {
            layoutParams.bottomMargin = -y;
        }
        switch (position) {
            case ABSOLUTE:
            case TOP_LEFT:
                layoutParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);
                layoutParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
                break;
            case TOP_CENTER:
                layoutParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);
                layoutParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
                break;
            case TOP_RIGHT:
                layoutParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);
                layoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
                break;
            case MIDDLE_LEFT:
                layoutParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
                layoutParams.addRule(RelativeLayout.CENTER_VERTICAL);
                break;
            case MIDDLE_CENTER:
                layoutParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
                layoutParams.addRule(RelativeLayout.CENTER_VERTICAL);
                break;
            case MIDDLE_RIGHT:
                layoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
                layoutParams.addRule(RelativeLayout.CENTER_VERTICAL);
                break;
            case BOTTOM_LEFT:
                layoutParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
                layoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
                break;
            case BOTTOM_CENTER:
                layoutParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
                layoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
                break;
            case BOTTOM_RIGHT:
                layoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
                layoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
                break;
            default:
                layoutParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);
                layoutParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
                break;
        }
        return layoutParams;
    }

    void showAbsolute(String adUnitId, int width, int height, int x, int y, IAdtalosListener listener) {
        getContext().runOnUiThread(() -> {
            prepare(adUnitId, width, height, listener);
            AdView adView = adViews.get(adUnitId);
            if (adView.getParent() != null) {
                ((ViewGroup) adView.getParent()).removeView(adView);
            }
            getAdsLayout().addView(adView, getAbsoluteLayoutParams(x, y));
            adView.loadAd(adUnitId);
        });
    }

    void showRelative(String adUnitId, int width, int height, int position, int y, IAdtalosListener listener) {
        getContext().runOnUiThread(() -> {
            prepare(adUnitId, width, height, listener);
            AdView adView = adViews.get(adUnitId);
            if (adView.getParent() != null) {
                ((ViewGroup) adView.getParent()).removeView(adView);
            }
            getAdsLayout().addView(adView, getRelationLayoutParams(position, y));
            adView.loadAd(adUnitId);
        });
    }

    static void destroy(String adUnitId) {
        getContext().runOnUiThread(() -> {
            AdView adView = adViews.get(adUnitId);
            if (adView != null) {
                if (adView.getParent() != null) {
                    ((ViewGroup) adView.getParent()).removeView(adView);
                }
                adView.destroy();
            }
        });
    }

    static void pause(String adUnitId) {
        getContext().runOnUiThread(() -> {
            AdView adView = adViews.get(adUnitId);
            if (adView != null) adView.pause();
        });
    }

    static void resume(String adUnitId) {
        getContext().runOnUiThread(() -> {
            AdView adView = adViews.get(adUnitId);
            if (adView != null) adView.resume();
        });
    }
}
