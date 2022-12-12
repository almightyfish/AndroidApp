package com.example.myapplication.util;


import com.example.myapplication.R;

/**
 * Copyright (C) 2019 Baidu, Inc. All Rights Reserved.
 */
public class RoutePlanUtil {

    public static int getBikeRoutePlanActionID(String action) {
        if (action.equals("直行")) {
            return R.drawable.map_route_turn_front;
        }
        if (action.equals("左转")) {
            return R.drawable.map_route_turn_left;
        }
        if (action.equals("过马路左转")) {
            return R.drawable.map_route_turn_left_cross_road;
        }
        if (action.equals("右转")) {
            return R.drawable.map_route_turn_right;
        }
        if (action.equals("过马路右转")) {
            return R.drawable.map_route_turn_right_cross_road;
        }
        return R.drawable.map_route_turn_front;
    }
}
