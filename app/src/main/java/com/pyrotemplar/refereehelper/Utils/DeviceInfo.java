package com.pyrotemplar.refereehelper.Utils;

import android.os.Build;
import android.text.TextUtils;

/**
 * Created by Manuel Montes de Oca on 4/26/2017.
 */

public class DeviceInfo {

    public static final String API_LEVEL = " / API level ";
    public static final String VERSION = ", version ";

    public static String getDeviceInfo() {
        String manufacturer = Build.MANUFACTURER;
        String model = Build.MODEL;
        String version = Integer.toString(Build.VERSION.SDK_INT);
        String versionRelease = Build.VERSION.RELEASE;
        if (model.startsWith(manufacturer)) {
            return capitalize(model);
        }
        return capitalize(manufacturer) + " " + model + API_LEVEL + version + VERSION + versionRelease;
    }

    private static String capitalize(String str) {
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        char[] arr = str.toCharArray();
        boolean capitalizeNext = true;
        String phrase = "";
        for (char c : arr) {
            if (capitalizeNext && Character.isLetter(c)) {
                phrase += Character.toUpperCase(c);
                capitalizeNext = false;
                continue;
            } else if (Character.isWhitespace(c)) {
                capitalizeNext = true;
            }
            phrase += c;
        }
        return phrase;
    }
}