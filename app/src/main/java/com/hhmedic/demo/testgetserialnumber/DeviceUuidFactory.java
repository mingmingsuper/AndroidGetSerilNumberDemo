package com.hhmedic.demo.testgetserialnumber;

import android.app.Service;
import android.content.Context;
import android.os.Build;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

import androidx.annotation.NonNull;

public class DeviceUuidFactory {

    private static final String DEVICE_ID_KEY = "hh_device_id";

    public static String getDeviceId(@NonNull Context context) {
        try {
            String deviceId = DeviceInfoSharePreferenceUtils.getStringValue(context, DEVICE_ID_KEY);
            if (!TextUtils.isEmpty(deviceId)) {
                return deviceId;
            }

            deviceId = getAndroidId(context);
            if (!TextUtils.isEmpty(deviceId)) {
                save(context, deviceId);
                return deviceId;
            }

            deviceId = getIMEI(context);
            if (!TextUtils.isEmpty(deviceId)) {
                save(context, deviceId);
                return deviceId;
            }

            deviceId = Installation.id(context);
            if (!TextUtils.isEmpty(deviceId)) {
                save(context,deviceId);
                return deviceId;
            }
        } catch (Exception exception) {
            return null;
        }
        return null;
    }

    private static String getAndroidId(@NonNull Context context) {
        try {
            String deviceId = Secure.getString(context.getContentResolver(), Secure.ANDROID_ID);
            //需要判断是不是设备坏设备ID，有可能有厂家多设备获取的一样的设备ID
            if (!TextUtils.isEmpty(deviceId) && deviceId.equalsIgnoreCase("9774d56d682e549c")) {
                deviceId = null;
            }
            return deviceId;
        } catch (Exception exception) {
            return null;
        }
    }

    private static String getIMEI(@NonNull Context context) {
        try {
            TelephonyManager tm = (TelephonyManager) context.getSystemService(Service.TELEPHONY_SERVICE);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                return tm.getImei();
            } else {
                return tm.getDeviceId();
            }
        } catch (Exception exception) {
            return null;
        }
    }

    private static void save(Context context, String value) {
        DeviceInfoSharePreferenceUtils.setValue(context, DEVICE_ID_KEY, value);
    }
}
