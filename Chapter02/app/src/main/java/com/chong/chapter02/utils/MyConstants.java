package com.chong.chapter02.utils;

import android.os.Environment;

public class MyConstants {
    public static final String CHAPTER_02_PATH = Environment
            .getExternalStorageDirectory().getPath()
            + "/android_art/chapter02/";

    public static final String CACHE_FILE_PATH = CHAPTER_02_PATH + "usercache";

    public static final int MSG_FROM_CLIENT = 0;
    public static final int MSG_FROM_SERVICE = 1;

}
