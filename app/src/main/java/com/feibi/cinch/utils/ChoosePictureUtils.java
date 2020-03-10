package com.feibi.cinch.utils;

import android.Manifest;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.hardware.Camera;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import java.io.File;

import jh.app.android.basiclibrary.Constants;


public class ChoosePictureUtils {
    public static int ALBUM = 0;
    public static int CAMERA = 1;
    public static int CUT_PHOTO = 2;
    private static File tempFile;
    public static Uri imageUri;//相机拍照图片保存地址
    private static String[] permission = {Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.CAMERA};

    private static Context mContext;
    public ChoosePictureUtils(Context context){
        this.mContext = context;
    }

    /*
     * 獲取權限
     */
    private void getPermissions(Context context){
        if (hasSdcard()) {
            if (lacksPermissions(context)) {//读写权限没开启
                ActivityCompat.requestPermissions((Activity) mContext, permission, 0);
            }
        }
    }
    /*
     * 从相机获取
     */
    public static void openCamera(Activity activity) {
        if(mContext==null){
            mContext = activity;
        }
        // 激活相机
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // 判断存储卡是否可以用，可用进行存储
        if (hasSdcard()) {
            if (lacksPermissions(activity)) {//读写权限没开启
                ActivityCompat.requestPermissions((Activity) mContext, permission, 0);
            } else {
                Constants.mkAppDir();
                tempFile = new File(Constants.PHOTO_PATH + "/" + Constants.getPhotoName());
                //读写权限已开启
                imageUri = getUriByPath(tempFile.getAbsolutePath());
                intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                // 开启一个带有返回值的Activity，请求码为
                activity.startActivityForResult(intent, CAMERA);
                Log.e("TAG", "openCamera: " + imageUri);
            }

        }
    }

    /*
     * 从相冊获取
     */
    public static void openAlbum(Activity activity) {
        if(mContext==null){
            mContext = activity;
        }
        // 激活相冊
        Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        // 判断存储卡是否可以用，可用进行存储
        if (hasSdcard()) {
            if (lacksPermissions(activity)) {//读写权限没开启
                ActivityCompat.requestPermissions((Activity) mContext, permission, 0);
            } else {
                Constants.mkAppDir();
                tempFile = new File(Constants.PHOTO_PATH + "/" + Constants.getPhotoName());
                //读写权限已开启
                imageUri = getUriByPath(tempFile.getAbsolutePath());
                intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                // 开启一个带有返回值的Activity，请求码为0
                activity.startActivityForResult(intent, ALBUM);
                Log.e("TAG", "openCamera: " + imageUri);
            }
        }
    }
    /**
     * 检查设备是否存在SDCard的方法
     */
    public static boolean hasSdcard() {

        String state = Environment.getExternalStorageState();
        if (state.equals(Environment.MEDIA_MOUNTED)) {
            // 有存储的SDCard
            return true;
        } else {
            return false;
        }
    }

    private static Uri getUriByPath(String path) {
        Uri uri = null;
        if (path != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {// 7.0以上获取uri的方式变化
                path = Uri.decode(path);
                ContentResolver cr = mContext.getContentResolver();
                StringBuffer buff = new StringBuffer();
                buff.append("(")
                        .append(MediaStore.Images.ImageColumns.DATA)
                        .append("=")
                        .append("'" + path + "'")
                        .append(")");
                Cursor cur = cr.query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                        new String[]{MediaStore.Images.ImageColumns._ID},
                        buff.toString(), null, null);
                int index = 0;
                for (cur.moveToFirst(); !cur.isAfterLast(); cur.moveToNext()) {
                    index = cur.getColumnIndex(MediaStore.Images.ImageColumns._ID);
                    index = cur.getInt(index);
                }
                if (index == 0) {
                    ContentValues contentValues = new ContentValues(1);
                    contentValues.put(MediaStore.Images.Media.DATA, path);
                    uri = mContext.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);
                } else {
                    Uri uri_temp = Uri.parse("content://media/external/images/media/" + index);
                    if (uri_temp != null) {
                        uri = uri_temp;
                    }
                }
            } else {
                uri = Uri.fromFile(new File(path));
            }
        }
        return uri;
    }

    /**
     * 判断权限集合
     * permissions 权限数组
     * return true-表示没有改权限  false-表示权限已开启
     */
    public static boolean lacksPermissions(Context mContexts) {
        for (String permission : permission) {
            if (lacksPermission(mContexts, permission)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断是否缺少权限
     */
    private static boolean lacksPermission(Context mContexts, String permission) {
        return ContextCompat.checkSelfPermission(mContexts, permission) ==
                PackageManager.PERMISSION_DENIED;
    }
    /**
     * @author miao
     * @createTime 2017年2月10日
     * @lastModify 2017年2月10日
     * @param
     * @return
     */
    public static boolean isCameraCanUse() {
        boolean canUse = true;
        Camera mCamera = null;
        try {
            mCamera = Camera.open();
        } catch (Exception e) {
            canUse = false;
        }
        if (canUse) {
            if (mCamera != null)
                mCamera.release();
            mCamera = null;
        }
        return canUse;
    }

}
