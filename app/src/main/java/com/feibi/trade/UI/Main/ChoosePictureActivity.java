package com.feibi.trade.UI.Main;

import android.content.ContentResolver;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.feibi.trade.Adapter.Picture;
import com.feibi.trade.Adapter.PictureAdapter;
import com.feibi.trade.NetWork.module.NetWork;
import com.feibi.trade.NetWork.request.FileUploadReq;
import com.feibi.trade.NetWork.request.UploadTradeReq;
import com.feibi.trade.NetWork.respond.FileUploadRes;
import com.feibi.trade.NetWork.respond.Spot;
import com.feibi.trade.NetWork.respond.UploadInfoRes;
import com.feibi.trade.R;
import com.feibi.trade.UI.Basic.BasicActivity;
import com.feibi.trade.utils.Global;
import com.google.gson.Gson;

import java.io.File;
import java.util.ArrayList;
import java.util.UUID;

import jh.app.android.basiclibrary.network.ReqCallBack;

public class ChoosePictureActivity extends BasicActivity {

    ImageView iv_back;
    RecyclerView rv_pictures;
    PictureAdapter adapter;
    ArrayList<Picture> pictures = new ArrayList<>();
    ArrayList<Picture> selectPictures = new ArrayList<>();
    NetWork netWork;
    String url;
    private static final String TAG = "readPic";
    UploadTradeReq req ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_picture);
        iv_back = findViewById(R.id.iv_back);
        iv_back.setOnClickListener(this);
        findViewById(R.id.ll_done).setOnClickListener(this);
        rv_pictures = findViewById(R.id.rv_pictures);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        rv_pictures.setLayoutManager(gridLayoutManager);

        netWork = new NetWork(this);
        url = "trip/basic/" + Global.TripInfo.getId() + "/image/" + Global.TripInfo.getToken();
    }

    @Override
    protected void onStart() {
        super.onStart();
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                getPhotoLocation();
//                adapter = new PictureAdapter(ChoosePictureActivity.this, pictures);
//                rv_pictures.setAdapter(adapter);
//            }
//        }).start();
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                getPhotoLocation();
                adapter = new PictureAdapter(ChoosePictureActivity.this, pictures);
                rv_pictures.setAdapter(adapter);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.ll_done:
                selectPictures.clear();
                selectPictures.addAll(adapter.getChoosePictures());
                showLoading();
                req = new UploadTradeReq();
                uploadPic(0);
//                try {
//                    int picNum = pictures.size();
//                    final int[] uploadSuccess = {0};
//                    final int[] uploadFail = {0};
//                    for (Picture picture : pictures) {
//                        File oldFile = new File(picture.getPath());
////                            String oldName = getFileName(picture.getPath());
//                        String oldNameHaveFormat = getFileNameHaveFormat(picture.getPath());
////                            String newNameHaveFormat = oldNameHaveFormat.replace(oldName,"data");
//                        if (!oldNameHaveFormat.endsWith("png") && !oldNameHaveFormat.endsWith("jpg")) {
//                            showToast("圖片格式不支持");
//                        }
////                            if (FileUtils.rename(oldFile, newNameHaveFormat)) {
////                            File newFile = new File(picture.getPath().replace(oldName,"data"));
//                        showLoading();
//                        netWork.uploadPic(url, new FileUploadReq(oldFile), new ReqCallBack<FileUploadRes>() {
//                            @Override
//                            public void onReqSuccess(FileUploadRes result) {
////                              FileUtils.rename(newFile, oldNameHaveFormat);
//                                uploadSuccess[0] += 1;
//                                checkIsDone(uploadSuccess[0],uploadFail[0],picNum);
//                            }
//
//                            @Override
//                            public void onReqFailed(Object result) {
//                                uploadFail[0] += 1;
//                                checkIsDone(uploadSuccess[0],uploadFail[0],picNum);
//                            }
//                        });
////                            } else {
////                                showToast("圖片重命名失敗");
////                            }
//                    }
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
                break;
        }
    }

    private void checkIsDone(int successNum,int failNum,int total){
        if(successNum+failNum==total){
            dismissLoading();
            showToast(successNum+"張照片上傳成功!\n"+failNum+"張照片上傳失敗！");
        }
    }
    private void uploadPic(int index){
        if(index>=selectPictures.size()){
            dismissLoading();
            if(index>0){
                uploadInfo();
            }
            return;
        }
        String type ;
        File oldFile = new File(selectPictures.get(index).getPath());
        String oldNameHaveFormat = getFileNameHaveFormat(selectPictures.get(index).getPath());
        if (oldNameHaveFormat.endsWith("png")) {
            type = "image/png";
        }else if(oldNameHaveFormat.endsWith("jpg")) {
            type = "image/jpg";
        }else {
            appendLoadingTxt("第"+(index+1)+"張圖片格式不支持\n");
            uploadPic(index+1);
            return;
        }
        netWork.uploadPic(url, new FileUploadReq(oldFile), new ReqCallBack<FileUploadRes>() {
            @Override
            public void onReqSuccess(FileUploadRes result) {
                appendLoadingTxt("第"+(index+1)+"張圖片上傳成功\n");
                String id= String.valueOf(UUID.randomUUID());
                Spot spot = new Spot();
                spot.setMedia(new Spot.MediaBean(id,result.getKey(),type));
                spot.setSize(new Spot.SizeBean());
                spot.setPosition(new Spot.PositionBean(String.valueOf(selectPictures.get(index).getLatitude()),String.valueOf(selectPictures.get(index).getLongitude())));
                spot.setId(id);
//                spot.setPano();
                req.addSpot(spot);
                uploadPic(index+1);
            }

            @Override
            public void onReqFailed(Object result) {
                appendLoadingTxt("第"+(index+1)+"張圖片上傳失敗\n");
                uploadPic(index+1);
            }
        });
    }

    private void uploadInfo(){
        showLoading("更新旅程信息中");
        String url = "trip/basic/" + Global.TripInfo.getId() + "/info/" + Global.TripInfo.getToken();
        req.setDescription(Global.GetTripInfo.getDescription());
        req.setPlanBackUrl(Global.GetTripInfo.getPlanBackUrl());
        req.setPlanName(Global.GetTripInfo.getPlanName());
        String json =new Gson().toJson(req);

        netWork.uploadInfo(url, req, new ReqCallBack<UploadInfoRes>() {
            @Override
            public void onReqSuccess(UploadInfoRes result) {
                dismissLoading();
                finish();
            }

            @Override
            public void onReqFailed(Object result) {
                dismissLoading();
                showToast("更新旅程信息失敗");
            }
        });
    }
    private String ReName(String path) {
        int lastPos = path.lastIndexOf("/");
        if (lastPos > 0) {
            String name = (String) path.subSequence(lastPos + 1, path.length() - 4);
            Log.d(TAG, "oldName: " + name);
            path.replace(name, "data");
        }
        return path;
    }

    private String getFileName(String path) {
        int lastPos = path.lastIndexOf("/");
        if (lastPos <= 0) {
            return "";
        }
        return (String) path.subSequence(lastPos + 1, path.length() - 4);
    }

    private String getFileNameHaveFormat(String path) {
        int lastPos = path.lastIndexOf("/");
        if (lastPos <= 0) {
            return "";
        }
        return (String) path.subSequence(lastPos + 1, path.length());
    }

    private static final String[] SELECTIMAGES = {
            MediaStore.Images.Media.DATA,
            MediaStore.Images.Media.DISPLAY_NAME,
            MediaStore.Images.Media.TITLE,
            MediaStore.Images.Media.DATE_ADDED,
            MediaStore.Images.Media.DATE_MODIFIED,
            MediaStore.Images.Media.LATITUDE,
            MediaStore.Images.Media.LONGITUDE,
            MediaStore.Images.Media.SIZE
    };

    private void getPhotoLocation() {
        ContentResolver contentResolver = getContentResolver();
        Cursor cursor = contentResolver.query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                SELECTIMAGES,
                null,
                null,
                null);
        int i = 0;
        if (cursor != null) {
            while (cursor.moveToNext()) {
                String path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
                File file = new File(path);
                if (!file.exists() || !file.canRead()) continue;

                String name = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DISPLAY_NAME));
                String title = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.TITLE));
                long addDate = cursor.getLong(cursor.getColumnIndex(MediaStore.Images.Media.DATE_ADDED));
                long modifyDate = cursor.getLong(cursor.getColumnIndex(MediaStore.Images.Media.DATE_MODIFIED));
                float latitude = cursor.getFloat(cursor.getColumnIndex(MediaStore.Images.Media.LATITUDE));
                float longitude = cursor.getFloat(cursor.getColumnIndex(MediaStore.Images.Media.LONGITUDE));
                long size = cursor.getLong(cursor.getColumnIndex(MediaStore.Images.Media.SIZE));
                i++;
                Log.d(TAG + i, "latitude:---" + latitude + "    "
                        + "longitude:--" + longitude
                );
                if (latitude != 0 && longitude != 0) {
                    pictures.add(new Picture(path, latitude, longitude));
                }
            }
        }
    }
}
