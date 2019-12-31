package jh.app.android.basiclibrary;

import android.os.Environment;
import android.text.format.DateFormat;

import java.io.File;
import java.util.Calendar;
import java.util.Locale;

/**
 * 常量类
 * Created by baorui on 2017/2/28 0028.
 */

public class Constants {
    //通用分隔符
    public static final String SEPARATOR = "-_-_-";
    // 文件目录结构
    public static final String SD_PATH = Environment.getExternalStorageDirectory().getAbsolutePath();
    public static final String MAIN_PATH_NAME = "com.feibi.honeywell";
    public static final String MAIN_PATH = SD_PATH + File.separator + MAIN_PATH_NAME;
    public static final String PHOTO_PATH = MAIN_PATH + File.separator + "Images";
    public static final String CACHE_PATH = MAIN_PATH_NAME + File.separator + "Cache";
    public static final String IMG_CACHE_PATH = CACHE_PATH + File.separator + "Images";
    public static final String VOICE_CACHE_PATH = CACHE_PATH + File.separator + "Voices";
    public static final String CRASHS_PATH = MAIN_PATH + File.separator + "CrashLogs";

    public static final String FILE_PREFIX = "android_";
    public static final String FILE_NAME_TIME_FORMAT = "yyyyMMddHHmmss";
    public static final String CHANNEL_ID = "1653669509";

    public static String getPhotoName () {
        return getFileName() + ".jpg";
    }

    public static String getVoiceName () {
        return getFileName() + ".aac";
    }

    private static String getFileName () {
        return FILE_PREFIX + DateFormat.format(FILE_NAME_TIME_FORMAT, Calendar.getInstance(Locale.CHINA));
    }

    public static void mkAppDir() {
        File file = new File(MAIN_PATH);
        mkDir(file);
        file = new File(PHOTO_PATH);
        mkDir(file);
        file = new File(CACHE_PATH);
        mkDir(file);
        file = new File(IMG_CACHE_PATH);
        mkDir(file);
        file = new File(VOICE_CACHE_PATH);
        mkDir(file);
        file = new File(CRASHS_PATH);
        mkDir(file);
    }

    public static void mkDir(File file) {
        try {
            if (!file.exists()) {
                file.mkdirs();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
