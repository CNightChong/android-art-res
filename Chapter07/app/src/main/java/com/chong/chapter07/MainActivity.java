package com.chong.chapter07;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends Activity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onButtonClick(View v) {
        if (v.getId() == R.id.button1) {
            Intent intent = new Intent(this, TestActivity.class);
            startActivity(intent);
            // 下一个Activity 进入动画 透明度有0到1，在竖直方向上从500平移到0的位置，持续300毫秒
            // 本Activity 退出动画 透明度有1到0，在竖直方向上从0平移到500的位置，持续300毫秒
            overridePendingTransition(R.anim.enter_anim, R.anim.exit_anim);
        } else if (v.getId() == R.id.button2) {
            Intent intent = new Intent(this, DemoActivity_1.class);
            startActivity(intent);
        } else if (v.getId() == R.id.button3) {
            Intent intent = new Intent(this, DemoActivity_2.class);
            startActivity(intent);
        }
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        Log.d(TAG, "onWindowFocusChanged, focus:" + hasFocus);
        super.onWindowFocusChanged(hasFocus);
    }
}
