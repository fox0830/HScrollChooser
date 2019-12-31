package com.feibi.trade.UI.Main;

import android.content.ContentResolver;
import android.database.Cursor;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.feibi.trade.Adapter.Picture;
import com.feibi.trade.Adapter.PictureAdapter;
import com.feibi.trade.R;
import com.feibi.trade.UI.Basic.BasicActivity;

import java.io.File;
import java.util.ArrayList;

public class ChoosePictureActivity extends BasicActivity {

    ImageView iv_back;
    RecyclerView rv_pictures;
    PictureAdapter adapter;
    ArrayList<Picture> pictures = new ArrayList<>();
    private static final String TAG = "readPic";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_picture);
        iv_back = findViewById(R.id.iv_back);iv_back.setOnClickListener(this);
        findViewById(R.id.ll_done).setOnClickListener(this);
        rv_pictures = findViewById(R.id.rv_pictures);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        rv_pictures.setLayoutManager(gridLayoutManager);

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
        switch (v.getId()){
            case R.id.iv_back:
                finish();
                break;
            case R.id.ll_done:
                adapter.getChoosePictures();
                finish();//todo
                break;
        }
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
                Log.d(TAG + i,  "latitude:---" + latitude + "    "
                        + "longitude:--" + longitude
                );
                if(latitude!=0&&longitude!=0){
                    pictures.add(new Picture(path,latitude,longitude));
                }
            }
        }
    }
}
