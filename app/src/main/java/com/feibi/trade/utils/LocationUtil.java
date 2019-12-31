package com.feibi.trade.utils;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;

import jh.app.android.basiclibrary.utils.PermissionsGetter;

public class LocationUtil {

    public static boolean checkLocationPermissions(Activity activity) {
        String[] permissions = new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};
        return PermissionsGetter.checkSelfPermissions(activity, permissions);
    }

    public static Location getMyLocation(Activity activity) {
        LocationManager lm = (LocationManager) activity.getSystemService(Context.LOCATION_SERVICE);

        if (!checkLocationPermissions(activity)) {
            return null;
        }
        if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return null;
        }
        Location myLocation = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        if (myLocation == null) {
            Criteria criteria = new Criteria();
            criteria.setAccuracy(Criteria.ACCURACY_COARSE);
            String provider = lm.getBestProvider(criteria, true);
            myLocation = lm.getLastKnownLocation(provider);
        }
        return myLocation;
    }

}
