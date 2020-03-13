package com.feibi.cinch.UI.Account;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.os.Bundle;
import android.view.View;

import com.feibi.cinch.NetWork.basic.BasicReq;
import com.feibi.cinch.NetWork.module.Member;
import com.feibi.cinch.NetWork.request.UploadRecordReq;
import com.feibi.cinch.R;
import com.feibi.cinch.UI.Basic.BasicActivity;
import com.feibi.cinch.utils.ChoosePictureUtils;
import com.feibi.cinch.utils.Global;
import com.feibi.cinch.utils.ImageTool;

import java.io.File;

import jh.app.android.basiclibrary.entity.BasicResponseBody;
import jh.app.android.basiclibrary.network.ReqCallBack;

import static com.feibi.cinch.utils.ChoosePictureUtils.ALBUM;
import static com.feibi.cinch.utils.ChoosePictureUtils.CAMERA;
import static com.feibi.cinch.utils.ChoosePictureUtils.CUT_PHOTO;
import static com.feibi.cinch.utils.ChoosePictureUtils.openAlbum;
import static com.feibi.cinch.utils.ChoosePictureUtils.openCamera;
import static jh.app.android.basiclibrary.Constants.MAIN_PATH;
import static jh.app.android.basiclibrary.Constants.getPhotoName;

public class SeeHeadActivity extends BasicActivity {
    String cut_picture_path;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_head);
        findViewById(R.id.iv_back).setOnClickListener(this);
        findViewById(R.id.ll_album).setOnClickListener(this);
        findViewById(R.id.ll_camera).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
            switch (view.getId()){
                case R.id.iv_back:
                    finish();
                    break;
                case R.id.ll_album:
                    openAlbum(this);
                    break;
                case R.id.ll_camera:
                    openCamera(this);
                    break;
            }
    }

    /**
     * 从相册、相机拍照、裁減的回调
     *
     * @param requestCode 根据值判断是从相册还是相机调用的回调.
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != Activity.RESULT_OK) {
            return;
        }
        if (requestCode == ALBUM) {
            if (data == null) {
                return;
            }
            Uri selectedImage = data.getData();
            if (selectedImage == null) {
                return;
            }
            String[] filePathColumns = {MediaStore.Images.Media.DATA};
            Cursor c = getContentResolver().query(selectedImage, filePathColumns, null, null, null);
            if (c == null) {
                return;
            }
            c.moveToFirst();
            int columnIndex = c.getColumnIndex(filePathColumns[0]);
            String imagePath = c.getString(columnIndex);
            c.close();
            cutImg(ImageTool.getUriByPath(this, imagePath));
        } else if (requestCode == CAMERA) {
            Uri uri = ChoosePictureUtils.imageUri;
        } else if (requestCode == CUT_PHOTO) {
            showLoading();
            File file = new File(cut_picture_path);
            uploadPic(file);
        }
    }

    /**
     * 剪切图片
     */
    private void cutImg(Uri uri) {
        if (uri == null) {
            return;
        }
        cut_picture_path = MAIN_PATH + "/" + getPhotoName();
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(cut_picture_path)));
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG);
        intent.putExtra("outputX", 480);
        intent.putExtra("outputY", 480);
        intent.putExtra("scale", true);
        intent.putExtra("scaleUpIfNeeded", true);
        intent.putExtra("return-data", false);
        startActivityForResult(intent, CUT_PHOTO);
    }

    private void uploadPic(File file) {
        File[] lc_pic = {file};
//        new Member(this).UploadPic(new UploadPicReq(new UploadPicReq.FormData(Global.cinchData.getLc_id(), lc_pic)), new ReqCallBack<BasicResponseBody<Object>>() {
//            @Override
//            public void onReqSuccess(BasicResponseBody<Object> result) {
//                dismissLoading();
//            }
//
//            @Override
//            public void onReqFailed(BasicResponseBody result) {
//                dismissLoading();
//            }
//        });
        new Member(this).GetObject(new BasicReq("lcupimg",new UploadRecordReq(Global.cinchData.getLc_id(), "2","filename")), new ReqCallBack<BasicResponseBody<Object>>() {
            @Override
            public void onReqSuccess(BasicResponseBody<Object> result) {
                dismissLoading();
            }

            @Override
            public void onReqFailed(BasicResponseBody result) {
                dismissLoading();
            }
        });
    }
}
