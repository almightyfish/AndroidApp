package com.example.myapplication.util;

import android.app.Activity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

public class Utils {
    /**
     * 隐藏键盘
     */
    public static void hideKeyBoard(Activity activity) {
        if (null == activity) {
            return;
        }
        InputMethodManager imm =
                (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        View v = activity.getWindow().peekDecorView();
        if (null != v) {
            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
        }
    }
}
