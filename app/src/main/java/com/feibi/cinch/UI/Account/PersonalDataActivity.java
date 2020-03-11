package com.feibi.cinch.UI.Account;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.feibi.cinch.NetWork.module.NetWork;
import com.feibi.cinch.NetWork.request.UploadPicReq;
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
import static jh.app.android.basiclibrary.Constants.MAIN_PATH;
import static jh.app.android.basiclibrary.Constants.getPhotoName;

public class PersonalDataActivity extends BasicActivity {

    ImageView iv_user_head;
    String cut_picture_path;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_data);
        findViewById(R.id.iv_back).setOnClickListener(this);
        iv_user_head = findViewById(R.id.iv_user_head);
        iv_user_head.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_user_head:
                ChoosePictureUtils.openAlbum(this);
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
        new NetWork(this).UploadPic(new UploadPicReq(new UploadPicReq.FormData(Global.cinchData.getLc_id(), file)), new ReqCallBack<BasicResponseBody<Object>>() {
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
