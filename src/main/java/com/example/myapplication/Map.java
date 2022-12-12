package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.Window;
import android.view.animation.LinearInterpolator;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.CoordType;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.PoiDetailInfo;
import com.baidu.mapapi.search.core.PoiInfo;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.poi.OnGetPoiSearchResultListener;
import com.baidu.mapapi.search.poi.PoiCitySearchOption;
import com.baidu.mapapi.search.poi.PoiDetailResult;
import com.baidu.mapapi.search.poi.PoiDetailSearchOption;
import com.baidu.mapapi.search.poi.PoiDetailSearchResult;
import com.baidu.mapapi.search.poi.PoiIndoorResult;
import com.baidu.mapapi.search.poi.PoiNearbySearchOption;
import com.baidu.mapapi.search.poi.PoiResult;
import com.baidu.mapapi.search.poi.PoiSearch;
import com.example.myapplication.overlayutil.PoiOverlay;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;


public class Map extends AppCompatActivity {
    private MapView mMapView;

    private boolean isFirstLocate = true;
    BaiduMap mBaiduMap;
    LocationClient mLocationClient;
    int totalPage;
    private PoiSearch mPoiSearch;
    LatLng latlng = new LatLng(22.536269, 113.95169);
    EditText editText;
    private LocationClient mLocClient;
    private Boolean isFirstLoc=true;
    Button btn;
    public static final String PN_BAIDU_MAP = "com.baidu.BaiduMap";
    double latitude;
    double longitude;

    @SuppressLint("MissingInflatedId")
    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        SDKInitializer.setAgreePrivacy(getApplicationContext(), true);
        SDKInitializer.initialize(getApplicationContext());
        SDKInitializer.setCoordType(CoordType.BD09LL);
        setContentView(R.layout.map);


        mMapView = (MapView) findViewById(R.id.bmapView);

        mBaiduMap = mMapView.getMap();
        mBaiduMap.setMyLocationEnabled(true);
        LocationClient.setAgreePrivacy(true);

        List<String>permissionList = new ArrayList<>();
        if(ContextCompat.checkSelfPermission(Map.this, Manifest.permission.ACCESS_NETWORK_STATE)!= PackageManager.PERMISSION_GRANTED){
            permissionList.add(Manifest.permission.ACCESS_NETWORK_STATE);
        }
        if(ContextCompat.checkSelfPermission(Map.this, Manifest.permission.INTERNET)!= PackageManager.PERMISSION_GRANTED){
            permissionList.add(Manifest.permission.INTERNET);
        }
        if(ContextCompat.checkSelfPermission(Map.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
            permissionList.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
        if(ContextCompat.checkSelfPermission(Map.this, Manifest.permission.READ_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
            permissionList.add(Manifest.permission.READ_EXTERNAL_STORAGE);
        }
        if(ContextCompat.checkSelfPermission(Map.this, Manifest.permission.ACCESS_COARSE_LOCATION)!= PackageManager.PERMISSION_GRANTED){
            permissionList.add(Manifest.permission.ACCESS_COARSE_LOCATION);
        }
        if(ContextCompat.checkSelfPermission(Map.this, Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED){
            permissionList.add(Manifest.permission.ACCESS_FINE_LOCATION);
        }
        //如果非空
        if(!permissionList.isEmpty()){
            String[] permissions = permissionList.toArray(new String[permissionList.size()]);
            ActivityCompat.requestPermissions(Map.this,permissions,1);
        }else{

        }
        init();
        //设置地图定位的一些参数，如定位图标，精度圈颜色等
        configure();
        //定位初始化
        try {
            init_location();
        } catch (Exception e) {
            e.printStackTrace();
        }
        mPoiSearch = PoiSearch.newInstance();
        btn = findViewById(R.id.btn);
        System.out.println(longitude);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if(!isBaiduMapInstalled()){
//                    Toast.makeText(Map.this,"Please Install",Toast.LENGTH_LONG).show();
//                }
//                else{
                    Intent i1 = new Intent();
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("baidumap://map/direction?destination=name:");
                stringBuilder.append("非遗");
                stringBuilder.append("|").append("latlng:");
                stringBuilder.append(latitude);
                stringBuilder.append(",");
                stringBuilder.append(longitude);
                stringBuilder.append("&coord_type=gcj02");
                i1.setData(Uri.parse(stringBuilder.toString()));
                i1.setPackage("com.baidu.BaiduMap");
//                    i1.setData(Uri.parse("baidumap://map/place/search?query=非遗&region=beijing&location=30.5,120.6&radius=1000&bounds=37.8608310000,112.5963090000,42.1942670000,118.9491260000&src=andr.baidu.openAPIdemo"));
//                    i1.setData(Uri.parse("baidumap://map/newsassistant?src=andr.baidu.openAPIdemo"));
                    startActivity(i1);
//                }


            }
        });
    }

    public static boolean isBaiduMapInstalled(){
        return isInstallPackage(PN_BAIDU_MAP);
    }

    private static boolean isInstallPackage(String packageName) {
        return new File("/data/data/" + packageName).exists();
    }


    public class MyOverLay extends PoiOverlay {
        /**
         * 构造函数
         */
        PoiSearch poiSearch;
        public MyOverLay(BaiduMap baiduMap, PoiSearch poiSearch) {
            super(baiduMap);
            this.poiSearch = poiSearch;
        }
        /**
         * 覆盖物被点击时
         */
        @Override
        public boolean onPoiClick(int i) {
            //获取点击的标记物的数据
            PoiInfo poiInfo = getPoiResult().getAllPoi().get(i);
            Log.e("TAG", poiInfo.name + "   " + poiInfo.address + "   " + poiInfo.phoneNum);
            //  发起一个详细检索,要使用uid
            poiSearch.searchPoiDetail(new PoiDetailSearchOption().poiUid(poiInfo.uid));
            return true;
        }
    }

    OnGetPoiSearchResultListener resultListener = new OnGetPoiSearchResultListener() {
        //获得POI的检索结果，一般检索数据都是在这里获取
        @Override
        public void onGetPoiResult(PoiResult poiResult) {
            //如果搜索到的结果不为空，并且没有错误
            if (poiResult != null && poiResult.error == PoiResult.ERRORNO.NO_ERROR) {
                MyOverLay overlay = new MyOverLay(mBaiduMap, mPoiSearch);//这传入search对象，因为一般搜索到后，点击时方便发出详细搜索
                //设置数据,这里只需要一步，
                overlay.setData(poiResult);
                //添加到地图
                overlay.addToMap();
                //将显示视图拉倒正好可以看到所有POI兴趣点的缩放等级
                overlay.zoomToSpan();//计算工具
                //设置标记物的点击监听事件
                mBaiduMap.setOnMarkerClickListener(overlay);
            } else {
                Toast.makeText(getApplication(), "搜索不到你需要的信息！", Toast.LENGTH_SHORT).show();
            }
        }
        //获得POI的详细检索结果，如果发起的是详细检索，这个方法会得到回调(需要uid)
        //详细检索一般用于单个地点的搜索，比如搜索一大堆信息后，选择其中一个地点再使用详细检索
        @Override
        public void onGetPoiDetailResult(PoiDetailResult poiDetailResult) {
            if (poiDetailResult.error != SearchResult.ERRORNO.NO_ERROR) {
                Toast.makeText(getApplication(), "抱歉，未找到结果",
                        Toast.LENGTH_SHORT).show();
            } else {// 正常返回结果的时候，此处可以获得很多相关信息
                Toast.makeText(getApplication(), poiDetailResult.getName() + ": "
                                + poiDetailResult.getAddress(),
                        Toast.LENGTH_LONG).show();
            }
        }

        @Override
        public void onGetPoiDetailResult(PoiDetailSearchResult poiDetailSearchResult) {

        }

        //获得POI室内检索结果
        @Override
        public void onGetPoiIndoorResult(PoiIndoorResult poiIndoorResult) {
        }
    };

    @Override

    protected void onResume() {

        // TODO Auto-generated method stub

        super.onResume();

        mMapView.onResume();

    }

    @Override

    protected void onPause() {

        // TODO Auto-generated method stub

        super.onPause();

        mMapView.onPause();

    }

    @Override

    protected void onDestroy() {
        mLocationClient.stop();
        mBaiduMap.setMyLocationEnabled(false);
        mMapView.onDestroy();
        mMapView = null;
        super.onDestroy();

    }

    public class MyLocationListener extends BDAbstractLocationListener {
        @Override
        public void onReceiveLocation(BDLocation location) {
            //mapView 销毁后不在处理新接收的位置
            if (location == null || mMapView == null){
                return;
            }
            latitude = location.getLatitude();    //获取纬度信息
            longitude = location.getLongitude();    //获取经度信息
            float radius = location.getRadius();    //获取定位精度，默认值为0.0f
            String coorType = location.getCoorType();
            //获取经纬度坐标类型，以LocationClientOption中设置过的坐标类型为准

            int errorCode = location.getLocType();
            MyLocationData locData = new MyLocationData.Builder()
                    .accuracy(location.getRadius())
                    // 此处设置开发者获取到的方向信息，顺时针0-360
                    .direction(location.getDirection()).latitude(location.getLatitude())
                    .longitude(location.getLongitude()).build();
            mBaiduMap.setMyLocationData(locData);
        }
    }

    /**
     *    设置地图放大的倍数
     */
    public  void init(){
        //设置地图放大的倍数
        MapStatus.Builder  builder=new MapStatus.Builder();
        builder.zoom(18f);
        mBaiduMap.setMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));
    }

    /**
     * 自定义内容:
     * 参数说明
     * (1)定位模式 地图SDK支持三种定位模式：NORMAL（普通态）, FOLLOWING（跟随态）, COMPASS（罗盘态）
     * （2）是否开启方向
     * （3）自定义定位图标 支持自定义定位图标样式，
     * （4）自定义精度圈填充颜色
     * （5）自定义精度圈边框颜色
     */
    public void configure(){
        mBaiduMap.setMyLocationConfiguration(new MyLocationConfiguration(MyLocationConfiguration.LocationMode.FOLLOWING,true,
                BitmapDescriptorFactory.fromResource(R.drawable.icon_gcoding),
                0xAAFFFF88,0xAA00FF00));
    }



    /**
     * 定位的初始化
     */
    public void init_location() throws Exception {
        mBaiduMap.setMyLocationEnabled(true);
        mLocationClient = new LocationClient(Map.this);
        LocationClientOption option = new LocationClientOption();
        option.setOpenGps(true); // 打开gps
        option.setCoorType("bd09ll"); // 设置坐标类型
        option.setScanSpan(1000);
        mLocationClient.setLocOption(option);
        MyLocationListener myLocationListener = new MyLocationListener();
        mLocationClient.registerLocationListener(myLocationListener);
        mLocationClient.start();
    }

}