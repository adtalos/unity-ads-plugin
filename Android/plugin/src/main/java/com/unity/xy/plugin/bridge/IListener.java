package com.unity.xy.plugin.bridge;

public interface IListener {
    void on(String unitId, String name, String data);
}
