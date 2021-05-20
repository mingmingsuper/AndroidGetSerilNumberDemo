package com.hhmedic.demo.testgetserialnumber;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.provider.Settings;
import android.text.TextUtils;
import android.widget.TextView;

import java.lang.reflect.Method;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView textView = findViewById(R.id.serial_number);
        String serial = DeviceUuidFactory.getDeviceId(this);
        if (!TextUtils.isEmpty(serial)) {
            textView.setText(serial);
        }

    }

    public static String getSerialNumber() {
//        return "8K888888888888888";
        try {
            Class<?> clz = MainActivity.class.getClassLoader().loadClass("android.os.SystemProperties");
            Method method = clz.getMethod("get", String.class);
            Object value = method.invoke(null, "ro.serialno");
            return value == null ? null : value.toString();

        } catch (Exception e) {
            return null;
        }
    }


    public static String getAndroidId (Context context) {
        String ANDROID_ID = Settings.System.getString(context.getContentResolver(), Settings.System.ANDROID_ID);
        return ANDROID_ID;
    }
}