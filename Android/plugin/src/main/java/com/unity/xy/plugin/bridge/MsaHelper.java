package com.unity.xy.plugin.bridge;

import android.content.Context;

import com.bun.miitmdid.core.ErrorCode;
import com.bun.miitmdid.core.MdidSdkHelper;
import com.bun.supplier.IIdentifierListener;
import com.bun.supplier.IdSupplier;
import com.unity.xy.plugin.SDK;

public class MsaHelper implements IIdentifierListener {
    public void getDeviceIds(Context cxt) {
        int code = MdidSdkHelper.InitSdk(cxt, true, this);
        switch (code) {
            case ErrorCode.INIT_ERROR_DEVICE_NOSUPPORT:
                System.out.println("1008612 不支持的设备");
                break;
            case ErrorCode.INIT_ERROR_LOAD_CONFIGFILE:
                System.out.println("1008613 加载配置文件出错");
                break;
            case ErrorCode.INIT_ERROR_MANUFACTURER_NOSUPPORT:
                System.out.println("1008611 不支持的设备厂商");
                break;
            case ErrorCode.INIT_ERROR_RESULT_DELAY:
                System.out.println("1008614 获取接口是异步的，结果会在回调中返回，回调执行的回调可能在工作线程");
                break;
            case ErrorCode.INIT_HELPER_CALL_ERROR:
                System.out.println("1008615 反射调用出错");
                break;
        }
    }

    @Override
    public void OnSupport(boolean isSupport, IdSupplier _supplier) {
        if (_supplier != null && isSupport) {
            SDK.setOAID(_supplier.getOAID());
        }
    }
}
