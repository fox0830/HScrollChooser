package com.feibi.trade.UI.Main;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.feibi.trade.NetWork.module.NetWork;
import com.feibi.trade.NetWork.respond.AddTradeRes;
import com.feibi.trade.NetWork.respond.GetTradeRes;
import com.feibi.trade.R;
import com.feibi.trade.UI.Basic.BasicActivity;
import com.feibi.trade.UI.View.MyDialog;
import com.feibi.trade.utils.Global;
import com.feibi.trade.utils.ImageUtils;
import com.feibi.trade.utils.LocationUtil;
import com.feibi.trade.utils.PreferencesUtil;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.OnStreetViewPanoramaReadyCallback;
import com.google.android.gms.maps.StreetViewPanorama;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;

import jh.app.android.basiclibrary.network.ReqCallBack;
import jh.app.android.basiclibrary.utils.PermissionsGetter;

import static jh.app.android.basiclibrary.utils.ObjUtils.jsonToObject;

public class MainActivity extends BasicActivity implements OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        GoogleMap.OnMarkerClickListener, LocationListener, OnStreetViewPanoramaReadyCallback {


    private GoogleMap mMap;
    private GoogleApiClient mGoogleApiClient;

    private TextView tv_title;
    private ImageView iv_add, iv_upload, iv_head, iv_360;
    private LinearLayout ll_360, ll_album, ll_bar;

    boolean hasTripe = false;
    boolean hasSpot = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkPermission();
        tv_title = findViewById(R.id.tv_title);
        iv_add = findViewById(R.id.iv_add);
        iv_add.setOnClickListener(this);
        iv_upload = findViewById(R.id.iv_upload);
        iv_upload.setOnClickListener(this);
        iv_head = findViewById(R.id.iv_head);
        iv_360 = findViewById(R.id.iv_360);
        ll_360 = findViewById(R.id.ll_360);
        ll_360.setOnClickListener(this);
        ll_album = findViewById(R.id.ll_album);
        ll_album.setOnClickListener(this);
        ll_bar = findViewById(R.id.ll_bar);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        if (mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(this)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
        }
        String tripInfo = PreferencesUtil.getTripInfo(this);
        Global.TripInfo = jsonToObject(tripInfo, AddTradeRes.class);

//        StreetViewPanoramaView streetViewPanoramaView = findViewById(R.id.svpv);
//        streetViewPanoramaView.getStreetViewPanoramaAsync(this);
//        StreetViewPanoramaFragment streetViewPanoramaFragment =(StreetViewPanoramaFragment) getFragmentManager().findFragmentById(R.id.map);
//        streetViewPanoramaFragment.getStreetViewPanoramaAsync(this);
//        StreetViewPanoramaOptions options = new StreetViewPanoramaOptions();
//        LatLng latLng = new LatLng(24,121);
//        options.position(latLng);
////        options.panoramaId("1232435465768");
////        String panoId =  options.getPanoramaId();
//        StreetViewPanoramaView streetViewPanoramaView = new StreetViewPanoramaView(this,options);
//        streetViewPanoramaView.getStreetViewPanoramaAsync(this);

    }


    @Override
    protected void onStart() {
        super.onStart();
        mGoogleApiClient.connect();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mGoogleApiClient != null && mGoogleApiClient.isConnected()) {
            mGoogleApiClient.disconnect();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        dismissLoading();
        changeUI();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.addCategory(Intent.CATEGORY_HOME);
            startActivity(intent);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    public void checkPermission() {
        int targetSdkVersion = 0;
        String[] PermissionString = {Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.CAMERA, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};
        try {
            final PackageInfo info = this.getPackageManager().getPackageInfo(this.getPackageName(), 0);
            targetSdkVersion = info.applicationInfo.targetSdkVersion;//获取应用的Target版本
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
//            Log.e("err", "检查权限_err0");
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            //Build.VERSION.SDK_INT是获取当前手机版本 Build.VERSION_CODES.M为6.0系统
            //如果系统>=6.0
            if (targetSdkVersion >= Build.VERSION_CODES.M) {
                //第 1 步: 检查是否有相应的权限
                boolean isAllGranted = checkPermissionAllGranted(PermissionString);
                if (isAllGranted) {
                    //Log.e("err","所有权限已经授权！");
                    return;
                }
                // 一次请求多个权限, 如果其他有权限是已经授予的将会自动忽略掉
                ActivityCompat.requestPermissions(this,
                        PermissionString, 1);
            }
        }
    }

    /**
     * 检查是否拥有指定的所有权限
     */
    private boolean checkPermissionAllGranted(String[] permissions) {
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                // 只要有一个权限没有被授予, 则直接返回 false
                //Log.e("err","权限"+permission+"没有授权");
                return false;
            }
        }
        return true;
    }

    //申请权限结果返回处理
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {
            boolean isAllGranted = true;
            for (int grant : grantResults) {
                if (grant != PackageManager.PERMISSION_GRANTED) {
                    isAllGranted = false;
                    break;
                }
            }
            if (isAllGranted) {
                Log.e("err", "权限都授权了");
            } else {
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_add:
                new MyDialog(this).builder().setIcon(getDrawable(R.mipmap.danger)).setTitle("確認").setMsg("重啓新旅程")
                        .setDoneButton(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                addTrade();
                            }
                        })
                        .show();
                break;
            case R.id.iv_upload:
                if (hasTripe) {
                    Intent textIntent = new Intent(Intent.ACTION_SEND);
                    textIntent.setType("text/plain");
                    textIntent.putExtra(Intent.EXTRA_TEXT, Global.TripInfo.getUrl());
                    startActivity(Intent.createChooser(textIntent, "分享"));
                }
                break;
            case R.id.ll_360:
                if (!hasTripe||!hasSpot) {
                    return;
                }
                startActivity(new Intent(this, Trade360Activity.class));
                break;
            case R.id.ll_album:
                if (!hasTripe) {
                    return;
                }
                showLoading();
                startActivity(new Intent(this, ChoosePictureActivity.class));
                break;
        }
    }

    private void changeUI() {
        hasTripe = !(Global.TripInfo == null || TextUtils.isEmpty(Global.TripInfo.getId()) || TextUtils.isEmpty(Global.TripInfo.getToken()));
        if (hasTripe) {
            ll_bar.setAlpha(1);
            new NetWork(this).getTrade("trip/" + Global.TripInfo.getId() + "/detail", new ReqCallBack<GetTradeRes>() {
                @Override
                public void onReqSuccess(GetTradeRes result) {
                    Global.GetTripInfo = result;
                    if(Global.GetTripInfo.getSpots().size()>0){
                        ll_360.setBackground(getDrawable(R.drawable.oval_red));
                        iv_360.setImageDrawable(getDrawable(R.mipmap.degrees_360_red));
                        hasSpot = true;
//                        String headUrl = Global.GetTripInfo.getTheme().getHeadericon();
//                        Glide.with(MainActivity.this).load(headUrl).into(iv_head);
                    }else {
                        ll_360.setBackground(getDrawable(R.drawable.oval_gray));
                        iv_360.setImageDrawable(getDrawable(R.mipmap.degrees_360));
                        hasSpot = false;
                        Glide.with(MainActivity.this).load(R.mipmap.logo_allsetwhite).into(iv_head);
                    }
                }

                @Override
                public void onReqFailed(Object result) {
                    showToast("error");
                }
            });
        } else {
            ll_bar.setAlpha((float) 0.5);
        }
    }

    private void addTrade() {
        new NetWork(this).addTrade("trip/create/mc/05671f63-088d-489e-8c63-6f536d4a110d?qrcode=false", new ReqCallBack<AddTradeRes>() {
            @Override
            public void onReqSuccess(AddTradeRes result) {
                Global.TripInfo = result;
                changeUI();
                String jsonString = new Gson().toJson(result);
                PreferencesUtil.saveTripInfo(MainActivity.this, jsonString);
            }

            @Override
            public void onReqFailed(Object result) {
                showToast("error");
            }
        });
    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        return false;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        checkLocationPermissions(this);
        mMap.setOnMarkerClickListener(this);
        moveToUserPosition();
    }

    public static boolean checkLocationPermissions(Activity activity) {
        String[] permissions = new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};
        return PermissionsGetter.checkSelfPermissions(activity, permissions);
    }

    /**
     * 移動
     *
     * @param
     */
    private void moveToUserPosition() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Location myLocation = LocationUtil.getMyLocation(MainActivity.this);
                if (myLocation != null) {
                    moveToLocation(myLocation);
                    addMarkers(myLocation);
                }
            }
        });
    }

    private void moveToLocation(Location location) {
        if (location != null) {
            LatLng sydney = new LatLng(location.getLatitude(), location.getLongitude());
            moveToLocation(sydney);
        }
    }

    private void moveToLocation(LatLng latLng) {
        if (latLng != null) {
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 14.0f));
        }
    }

    /**
     * 添加mark
     *
     * @param
     */
    private void addMarkers(Location location) {
        BitmapDescriptor bitmap = BitmapDescriptorFactory.fromBitmap(ImageUtils.drawable2Bitmap(getDrawable(R.mipmap.marker)));
        mMap.addMarker(new MarkerOptions().icon(bitmap).position(new LatLng(location.getLatitude(), location.getLongitude())).title("My Location"));
    }

    @Override
    public void onStreetViewPanoramaReady(StreetViewPanorama streetViewPanorama) {
//        streetViewPanorama.setPosition();
        String panoId = streetViewPanorama.getLocation().panoId;
        showToast(panoId);
    }
}
