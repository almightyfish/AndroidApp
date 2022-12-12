/*
 * Copyright (C) 2015 Baidu, Inc. All Rights Reserved.
 */

package com.example.myapplication.util.clusterutil.clustering;

import java.util.Collection;

import com.baidu.mapapi.model.LatLng;

/**
 * A collection of ClusterItems that are nearby each other.
 */
public interface Cluster<T extends com.example.myapplication.util.clusterutil.clustering.ClusterItem> {
    public LatLng getPosition();

    Collection<T> getItems();

    int getSize();
}