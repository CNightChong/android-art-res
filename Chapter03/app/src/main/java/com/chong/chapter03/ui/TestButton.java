package com.chong.chapter03.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import android.widget.TextView;

import com.nineoldandroids.view.ViewHelper;

public class TestButton extends TextView {
    private static final String TAG = "TestButton";
    /**
     * 系统识别的最小滑动距离
     */
    private int mScaledTouchSlop;
    // 分别记录上次滑动的坐标
    private int mLastX = 0;
    private int mLastY = 0;

    public TestButton(Context context) {
        this(context, null);
    }

    public TestButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TestButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        // 系统识别的最小滑动距离
        mScaledTouchSlop = ViewConfiguration.get(getContext())
                .getScaledTouchSlop();
        Log.d(TAG, "sts:" + mScaledTouchSlop);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // 绝对距离，距离屏幕左上角的值
        int x = (int) event.getRawX();
        int y = (int) event.getRawY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN: {
                break;
            }
            case MotionEvent.ACTION_MOVE: {
                int deltaX = x - mLastX;
                int deltaY = y - mLastY;
                Log.d(TAG, "move, deltaX:" + deltaX + " deltaY:" + deltaY);
                int translationX = (int) ViewHelper.getTranslationX(this) + deltaX;
                int translationY = (int) ViewHelper.getTranslationY(this) + deltaY;
                ViewHelper.setTranslationX(this, translationX);
                ViewHelper.setTranslationY(this, translationY);
                break;
            }
            case MotionEvent.ACTION_UP: {
                break;
            }
            default:
                break;
        }

        mLastX = x;
        mLastY = y;
        return true;
    }

}
