package com.example.myapplication.util.overlayutil;

import java.util.ArrayList;
import java.util.List;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.Overlay;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.map.Polyline;
import com.baidu.mapapi.map.PolylineOptions;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.route.TransitRouteLine;

import android.os.Bundle;
import android.util.Log;

/**
 * 用于显示换乘路线的Overlay，自3.4.0版本起可实例化多个添加在地图中显示
 */
public class TransitRouteOverlay extends OverlayManager {

    private TransitRouteLine mRouteLine = null;

    /**
     * 构造函数
     *
     * @param baiduMap 该TransitRouteOverlay引用的 BaiduMap 对象
     */
    public TransitRouteOverlay(BaiduMap baiduMap) {
        super(baiduMap);
    }

    @Override
    public final List<OverlayOptions> getOverlayOptions() {

        if (mRouteLine == null) {
            return null;
        }

        List<OverlayOptions> overlayOptionses = new ArrayList<OverlayOptions>();
        // step node
        if (mRouteLine.getAllStep() != null
                && mRouteLine.getAllStep().size() > 0) {

            for (TransitRouteLine.TransitStep step : mRouteLine.getAllStep()) {
                Bundle b = new Bundle();
                b.putInt("index", mRouteLine.getAllStep().indexOf(step));
                if (step.getEntrance() != null) {
                    overlayOptionses
                            .add((new MarkerOptions()).position(step.getEntrance().getLocation())
                                    .anchor(0.5f, 0.5f).zIndex(10).extraInfo(b)
                                    .icon(getIconForStep(step)));
                }
                // 最后路段绘制出口点
                if (mRouteLine.getAllStep().indexOf(step) == (mRouteLine
                                                                      .getAllStep().size() - 1)
                        && step.getExit() != null) {
                    overlayOptionses
                            .add((new MarkerOptions()).position(step.getExit().getLocation())
                                    .anchor(0.5f, 0.5f).zIndex(10).icon(getIconForStep(step)));
                }
            }
        }

        if (mRouteLine.getStarting() != null) {
            overlayOptionses
                    .add((new MarkerOptions()).position(mRouteLine.getStarting().getLocation())
                            .icon(getStartMarker() != null ? getStartMarker()
                                    : BitmapDescriptorFactory.fromAssetWithDpi("Icon_start.png"))
                            .zIndex(10));
        }
        if (mRouteLine.getTerminal() != null) {
            overlayOptionses
                    .add((new MarkerOptions())
                            .position(mRouteLine.getTerminal().getLocation())
                            .icon(getTerminalMarker() != null ? getTerminalMarker() :
                                    BitmapDescriptorFactory
                                            .fromAssetWithDpi("Icon_end.png"))
                            .zIndex(10));
        }
        // polyline
        if (mRouteLine.getAllStep() != null
                && mRouteLine.getAllStep().size() > 0) {

            ArrayList<Integer> textureIndexs = new ArrayList<Integer>();
            List<LatLng> points = new ArrayList<LatLng>();

            for (TransitRouteLine.TransitStep step : mRouteLine.getAllStep()) {
                if (step.getWayPoints() == null) {
                    continue;
                }
                for (int i = 0; i < step.getWayPoints().size(); ++i) {
                    if (step.getStepType()
                            != TransitRouteLine.TransitStep.TransitRouteStepType.WAKLING) {
                        textureIndexs.add(0);
                    } else {
                        textureIndexs.add(1);
                    }
                }
                points.addAll(step.getWayPoints());
            }
            overlayOptionses.add(new PolylineOptions().points(points).width(15).dottedLine(true)
                    .customTextureList(getCustomTextureList()).textureIndex(textureIndexs)
                    .zIndex(0));
        }
        return overlayOptionses;
    }

    private BitmapDescriptor getIconForStep(TransitRouteLine.TransitStep step) {
        switch (step.getStepType()) {
            case BUSLINE:
                return BitmapDescriptorFactory.fromAssetWithDpi("Icon_bus_station.png");
            case SUBWAY:
                return BitmapDescriptorFactory.fromAssetWithDpi("Icon_subway_station.png");
            case WAKLING:
                return BitmapDescriptorFactory.fromAssetWithDpi("Icon_walk_route.png");
            default:
                return null;
        }
    }

    /**
     * 设置路线数据
     *
     * @param routeOverlay 路线数据
     */
    public void setData(TransitRouteLine routeOverlay) {
        this.mRouteLine = routeOverlay;
    }

    /**
     * 覆写此方法以改变默认起点图标
     *
     * @return 起点图标
     */
    public BitmapDescriptor getStartMarker() {
        return null;
    }

    /**
     * 覆写此方法以改变默认终点图标
     *
     * @return 终点图标
     */
    public BitmapDescriptor getTerminalMarker() {
        return null;
    }

    public int getLineColor() {
        return 0;
    }

    public List<BitmapDescriptor> getCustomTextureList() {
        ArrayList<BitmapDescriptor> list = new ArrayList<BitmapDescriptor>();
        list.add(BitmapDescriptorFactory.fromAsset("Icon_road_blue_arrow.png"));
        list.add(BitmapDescriptorFactory.fromAsset("Icon_road_green_arrow.png"));
        return list;
    }

    /**
     * 覆写此方法以改变起默认点击行为
     *
     * @param i 被点击的step在
     *          {@link TransitRouteLine#getAllStep()}
     *          中的索引
     * @return 是否处理了该点击事件
     */
    public boolean onRouteNodeClick(int i) {
        if (mRouteLine.getAllStep() != null
                && mRouteLine.getAllStep().get(i) != null) {
            Log.i("baidumapsdk", "TransitRouteOverlay onRouteNodeClick");
        }
        return false;
    }

    @Override
    public final boolean onMarkerClick(Marker marker) {
        for (Overlay mMarker : mOverlayList) {
            if (mMarker instanceof Marker && mMarker.equals(marker)) {
                if (marker.getExtraInfo() != null) {
                    onRouteNodeClick(marker.getExtraInfo().getInt("index"));
                }
            }
        }
        return true;
    }

    @Override
    public boolean onPolylineClick(Polyline polyline) {
        // TODO Auto-generated method stub
        return false;
    }

}
