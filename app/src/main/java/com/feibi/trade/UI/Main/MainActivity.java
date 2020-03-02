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
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.feibi.trade.NetWork.module.NetWork;
import com.feibi.trade.NetWork.respond.AddTradeRes;
import com.feibi.trade.NetWork.respond.GetTradeRes;
import com.feibi.trade.NetWork.respond.UrlSpot;
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
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import jh.app.android.basiclibrary.network.ReqCallBack;
import jh.app.android.basiclibrary.utils.PermissionsGetter;

import static jh.app.android.basiclibrary.utils.ObjUtils.jsonToObject;

public class MainActivity extends BasicActivity implements OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        GoogleMap.OnMarkerClickListener, LocationListener {

    Marker marker;
    private GoogleMap mMap;
    private GoogleApiClient mGoogleApiClient;

    View view;
    ImageView iv_bottom_icon;

    ConstraintLayout cl_head;

    //    private TextView tv_title;
    private ImageView iv_add, iv_upload, iv_360;
    private LinearLayout ll_360, ll_album, ll_bar;
    ViewPager banner;
    ImageView iv_head;

    boolean hasTripe = false;
    boolean hasSpot = false;
    ImageView iv_left, iv_right;

    private List<ItemFragment> mFragments = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkPermission();
//        tv_title = findViewById(R.id.tv_title);

        view = findViewById(R.id.view);
        iv_bottom_icon = findViewById(R.id.iv_bottom_icon);

        cl_head = findViewById(R.id.cl_head);
        iv_add = findViewById(R.id.iv_add);
        iv_add.setOnClickListener(this);
        iv_upload = findViewById(R.id.iv_upload);
        iv_upload.setOnClickListener(this);
//        iv_head = findViewById(R.id.iv_head);
        iv_360 = findViewById(R.id.iv_360);
        ll_360 = findViewById(R.id.ll_360);
        ll_360.setOnClickListener(this);
        ll_album = findViewById(R.id.ll_album);
        ll_album.setOnClickListener(this);
        ll_bar = findViewById(R.id.ll_bar);
        cl_head = findViewById(R.id.cl_head);

        iv_head = findViewById(R.id.iv_head);
        iv_left = findViewById(R.id.iv_left);
        iv_left.setOnClickListener(this);
        iv_right = findViewById(R.id.iv_right);
        iv_right.setOnClickListener(this);
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

        String uploadPicInfo = PreferencesUtil.getUploadPicInfo(this);
        Global.hasUpload = jsonToObject(uploadPicInfo, Global.HasUpload.class);
        if (Global.hasUpload == null) {
            Global.hasUpload = new Global.HasUpload(new ArrayList<>());
        }
        cl_head.post(new Runnable() {
            @Override
            public void run() {
                int width = cl_head.getMeasuredWidth();
                ConstraintLayout.LayoutParams linearParams = (ConstraintLayout.LayoutParams) cl_head.getLayoutParams();
                linearParams.height = width*10/16;
                cl_head.setLayoutParams(linearParams);
            }
        });
        banner = findViewById(R.id.banner);
        banner.setOffscreenPageLimit(1);
        banner.setAdapter(new ConversionFragmentAdapter(getSupportFragmentManager()));
        banner.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                setCurrentItem(i);
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }

    private void setCurrentItem(int i) {
        if (i == 0) {
            iv_left.setAlpha((float) 0.5);
        } else {
            iv_left.setAlpha((float) 1);
        }
        if (i == mFragments.size() - 1) {
            iv_right.setAlpha((float) 0.5);
        } else {
            iv_right.setAlpha((float) 1);
        }
        if (Global.GetTripInfo.getSpots().size() > i) {
            if (Global.GetTripInfo.getSpots().get(i) != null && Global.GetTripInfo.getSpots().get(i).getPosition() != null) {
                String lat = Global.GetTripInfo.getSpots().get(i).getPosition().getPictureLat();
                String lng = Global.GetTripInfo.getSpots().get(i).getPosition().getPictureLng();
                moveToLocation(new LatLng(Double.valueOf(lat), Double.valueOf(lng)));
            }
        }
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
//                    textIntent.putExtra(Intent.EXTRA_TEXT, Global.TripInfo.getUrl());
                    textIntent.putExtra(Intent.EXTRA_TEXT, "http://61.222.197.34:10093/Map/trip/" + Global.TripInfo.getId()); //我們的web view地址
                    startActivity(Intent.createChooser(textIntent, "分享"));
                }
                break;
            case R.id.ll_360:
                if (!hasTripe || !hasSpot) {
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
            case R.id.iv_left:
                if (banner.getCurrentItem() == 0) {
                    return;
                }
                banner.setCurrentItem(banner.getCurrentItem() - 1);
                break;
            case R.id.iv_right:
                if (banner.getCurrentItem() == mFragments.size() - 1) {
                    return;
                }
                banner.setCurrentItem(banner.getCurrentItem() + 1);
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
                    if (Global.GetTripInfo.getSpots().size() > 0) {
                        ll_360.setBackground(getDrawable(R.drawable.oval_red));
                        iv_360.setImageDrawable(getDrawable(R.mipmap.degrees_360_red));
                        view.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                        iv_bottom_icon.setImageDrawable(getDrawable(R.mipmap.details_icon));
                        banner.setVisibility(View.VISIBLE);
                        iv_left.setVisibility(View.VISIBLE);
                        iv_right.setVisibility(View.VISIBLE);
                        iv_head.setVisibility(View.INVISIBLE);
                        hasSpot = true;
                        mFragments.clear();
                        for (UrlSpot spot : Global.GetTripInfo.getSpots()) {
                            mFragments.add(ItemFragment.newInstance(spot));
                        }
                        if (banner.getAdapter() != null)
                            banner.getAdapter().notifyDataSetChanged();
                        setCurrentItem(banner.getCurrentItem());
                    } else {
                        ll_360.setBackground(getDrawable(R.drawable.oval_gray));
                        iv_360.setImageDrawable(getDrawable(R.mipmap.degrees_360));
                        view.setBackgroundColor(getResources().getColor(R.color.color_d9));
                        iv_bottom_icon.setImageDrawable(getDrawable(R.mipmap.gray_icon));
                        banner.setVisibility(View.INVISIBLE);
                        iv_left.setVisibility(View.INVISIBLE);
                        iv_right.setVisibility(View.INVISIBLE);
                        iv_head.setVisibility(View.VISIBLE);
                        hasSpot = false;
                    }

                }

                @Override
                public void onReqFailed(Object result) {
                    showToast("error");
                }
            });
        } else {
            ll_bar.setAlpha((float) 0.5);
            addTrade();
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
                Global.hasUpload = new Global.HasUpload(new ArrayList<>());
                PreferencesUtil.saveUploadPicInfo(MainActivity.this, "");
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
            addMarkers(latLng);
        }
    }

    /**
     * 添加mark
     *
     * @param
     */
    private void addMarkers(LatLng latLng) {
        if (marker != null)
            marker.remove();
        BitmapDescriptor bitmap = BitmapDescriptorFactory.fromBitmap(ImageUtils.drawable2Bitmap(getDrawable(R.mipmap.marker)));
        marker = mMap.addMarker(new MarkerOptions().icon(bitmap).position(latLng));
    }

    public class ConversionFragmentAdapter extends FragmentStatePagerAdapter {

        public ConversionFragmentAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public int getItemPosition(Object object) {
            return POSITION_NONE;
        }
    }


    public static class ItemFragment extends Fragment {
        ImageView imageView;
        UrlSpot spot;

        public static ItemFragment newInstance(UrlSpot spot) {
            Bundle args = new Bundle();
            ItemFragment fragment = new ItemFragment();
            fragment.setArguments(args);
            fragment.setSpot(spot);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.fragment_img, container, false);
            imageView = view.findViewById(R.id.imageView);
            String url = spot.getImageUrl();
            if (getContext() != null)
                Glide.with(getContext()).load(url).into(imageView);
            return view;
        }

        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
        }

        @Override
        public void onResume() {
            super.onResume();
        }

        public void setSpot(UrlSpot spot) {
            this.spot = spot;
        }

    }


}
