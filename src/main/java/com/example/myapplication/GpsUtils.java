package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;

public class GpsUtils {
    public static void gotoBaiduMap(Context context, double lat, double lng, String end) {
        try {
            Uri uri = Uri.parse("baidumap://map/direction?destination=latlng:" + lat + "," + lng + "|name:" + end + "&mode=driving");
            context.startActivity(new Intent(Intent.ACTION_VIEW, uri));
        } catch (Exception e) {
            System.out.println("请安装百度");
        }
    }

}
