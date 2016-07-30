package com.chong.chapter11.utils;

import android.os.Environment;

public class MyConstants {
    public static final String CHAPTER_PATH = Environment
            .getExternalStorageDirectory().getPath()
            + "/chong/chapter/";

    public static final String CACHE_FILE_PATH = CHAPTER_PATH + "usercache";

    public static final int MSG_FROM_CLIENT = 0;
    public static final int MSG_FROM_SERVICE = 1;

}
