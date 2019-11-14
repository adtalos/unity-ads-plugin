package com.unity.xy.plugin.bridge;

import android.view.ViewGroup;

import com.unity.xy.plugin.Size;
import com.unity.xy.plugin.VideoController;
import com.unity.xy.plugin.View;

class NativeViewHandler extends ViewHandler {
    @Override
    public void prepare(String unitId, int width, int height, IListener listener) {
        if (views.containsKey(unitId)) return;
        Size size = new Size(width, height);
        View view = new View(getContext());
        ListenerProxy listenerProxy = new ListenerProxy(unitId, listener);
        view.setSize(size);
        view.setListener(listenerProxy);
        view.getVideoController().setVideoListener(listenerProxy);
        view.setDefaultCustomListener(listenerProxy);
        views.put(unitId, view);
    }

    void playVideo(String unitId) {
        try {
            View view = views.get(unitId);
            if (view == null) return;
            getContext().runOnUiThread(() -> {
                view.getVideoController().play();
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void pauseVideo(String unitId) {
        try {
            View view = views.get(unitId);
            if (view == null) return;
            getContext().runOnUiThread(() -> {
                view.getVideoController().pause();
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void muteVideo(String unitId, boolean mute) {
        try {
            View view = views.get(unitId);
            if (view == null) return;
            getContext().runOnUiThread(() -> {
                view.getVideoController().mute(mute);
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    boolean hasVideo(String unitId) {
        try {
            View view = views.get(unitId);
            if (view == null) return false;
            return view.getVideoController().hasVideo();
        } catch (Exception e) {
            return false;
        }
    }

    String getVideoMetaData(String unitId) {
        View view = views.get(unitId);
        if (view == null) return null;
        VideoController.Metadata metadata = view.getVideoController().getMetadata();
        if (metadata == null) return null;
        return metadata.toString();
    }

    void load(String unitId, int width, int height, IListener listener) {
        getContext().runOnUiThread(() -> {
            prepare(unitId, width, height, listener);
            try {
                View view = views.get(unitId);
                view.load(unitId, false);
            } catch (Exception e) {
            }
        });
    }

    void showAbsolute(String unitId, int x, int y) {
        getContext().runOnUiThread(() -> {
            try {
                View view = views.get(unitId);
                if (view.getParent() != null) {
                    ((ViewGroup) view.getParent()).removeView(view);
                }
                getLayout().addView(view, getAbsoluteLayoutParams(x, y));
                view.show();
            } catch (Exception e) {
            }
        });
    }

    void showRelative(String unitId, int position, int y) {
        getContext().runOnUiThread(() -> {
            try {
                View view = views.get(unitId);
                if (view.getParent() != null) {
                    ((ViewGroup) view.getParent()).removeView(view);
                }
                getLayout().addView(view, getRelationLayoutParams(position, y));
                view.show();
            } catch (Exception e) {
            }
        });
    }

    boolean isLoaded(String unitId) {
        try {
            View view = views.get(unitId);
            if (view == null) return false;
            return view.isLoaded();
        } catch (Exception e) {
            return false;
        }
    }

    boolean isPlaying(String unitId) {
        try {
            View view = views.get(unitId);
            if (view == null) return false;
            return view.getVideoController().isPlaying();
        } catch (Exception e) {
            return false;
        }
    }

    boolean isEnded(String unitId) {
        try {
            View view = views.get(unitId);
            if (view == null) return false;
            return view.getVideoController().isEnded();
        } catch (Exception e) {
            return false;
        }
    }
}
