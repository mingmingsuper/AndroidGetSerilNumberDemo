package com.hhmedic.demo.testgetserialnumber;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;

public class DeviceInfoSharePreferenceUtils {

    private static final String PreferenceName = "HH_DEVICE_INFO";

    public static void setValue(@NonNull Context context, @NonNull String key, @NonNull String value) {
        getEditor(context).putString(key,value).apply();
    }

    public static void setValue(@NonNull Context context, @NonNull String key, int value) {
        getEditor(context).putInt(key,value).apply();
    }

    public static void setValue(@NonNull Context context, @NonNull String key, boolean value) {
        getEditor(context).putBoolean(key,value).apply();
    }

    public static String getStringValue(@NonNull Context context,@NonNull String key) {
        return getSharePreference(context).getString(key,null);
    }

    public static String getStringValue(@NonNull Context context,@NonNull String key,String defaultValue) {
        return getSharePreference(context).getString(key,defaultValue);
    }

    public static int getIntValue(@NonNull Context context,@NonNull String key) {
        return getSharePreference(context).getInt(key,-1);
    }

    public static int getIntValue(@NonNull Context context,@NonNull String key,int defaultValue) {
        return getSharePreference(context).getInt(key,defaultValue);
    }

    public static boolean getBooleanValue(@NonNull Context context,@NonNull String key) {
        return getSharePreference(context).getBoolean(key,false);
    }

    public static boolean getBooleanValue(@NonNull Context context,@NonNull String key,boolean defaultValue) {
        return getSharePreference(context).getBoolean(key,defaultValue);
    }

    private static SharedPreferences getSharePreference(@NonNull Context context) {
        return context.getSharedPreferences(PreferenceName,Context.MODE_PRIVATE);
    }

    private static SharedPreferences.Editor getEditor(@NonNull Context context) {
        return getSharePreference(context).edit();
    }
}
