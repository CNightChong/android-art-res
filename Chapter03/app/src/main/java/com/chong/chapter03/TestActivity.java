package com.chong.chapter03;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.chong.chapter03.ui.TestButton;

public class TestActivity extends Activity implements OnClickListener,
        OnLongClickListener {

    private static final String TAG = "TestActivity";

    private static final int MESSAGE_SCROLL_TO = 1;
    private static final int FRAME_COUNT = 30;
    private static final int DELAYED_TIME = 33;

    private Button mButton1;
    private TestButton mButton2;

    private int mCount = 0;

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MESSAGE_SCROLL_TO: {
                    mCount++;
                    if (mCount <= FRAME_COUNT) {
                        float fraction = mCount / (float) FRAME_COUNT;
                        int scrollX = (int) (fraction * 100);
                        mButton1.scrollTo(scrollX, 0);
                        mHandler.sendEmptyMessageDelayed(MESSAGE_SCROLL_TO, DELAYED_TIME);
                    }
                    break;
                }

                default:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        initView();
    }

    private void initView() {
        mButton1 = (Button) findViewById(R.id.button1);
        mButton1.setOnClickListener(this);
        mButton2 = (TestButton) findViewById(R.id.button2);
        mButton2.setOnLongClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == mButton1) {
//            translation();
//            objectAnimation();
//            useLayout();
            useScrollTo();
//
//            mHandler.sendEmptyMessageDelayed(MESSAGE_SCROLL_TO, DELAYED_TIME);
        }
    }

    /**
     * 通过scrollTo方法平移
     * view 的getLeft，getTop,getX()和getY()都  不会  变化
     * scrollTo只改变view内容的位置，不改变view的位置
     */
    private void useScrollTo() {
        final int startX = 0;
        final int deltaX = 100;
        ValueAnimator animator = ValueAnimator.ofInt(0,
                1).setDuration(1000);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animator) {
                float fraction = animator.getAnimatedFraction();
                // parentView.scrollTo 会移动所有子view的位置
                View parentView = (View) mButton1.getParent();
//                parentView.scrollTo(-(startX + (int) (deltaX * fraction)), 0);

                // mButton1.scrollTo会移动内容（文字）的位置
                mButton1.scrollTo(startX + (int) (deltaX * fraction), 0);

                // 通过log可以看到，parentView 的getLeft，getTop,getX()和getY()都  不会  变化
                Log.d(TAG, "scroll parentView.left==" + parentView.getLeft());
                Log.d(TAG, "scroll parentView.top==" + parentView.getTop());
                Log.d(TAG, "scroll parentView.x==" + parentView.getX());
                Log.d(TAG, "scroll parentView.y==" + parentView.getY());

                // 通过log可以看到，button1 的getLeft，getTop,getX()和getY()都  不会  变化
                Log.d(TAG, "scroll button1.left==" + mButton1.getLeft());
                Log.d(TAG, "scroll button1.top==" + mButton1.getTop());
                Log.d(TAG, "scroll button1.x==" + mButton1.getX());
                Log.d(TAG, "scroll button1.y==" + mButton1.getY());
            }
        });
        animator.start();
    }

    /**
     * 通过重新布局平移
     * view 的getLeft，getTop，getX()和getY()都  会  变化
     */
    private void useLayout() {
        // 通过多次连续点击可以看到，view 的getLeft，getTop，getX()和getY()都  会  变化，
        Log.d(TAG, "click requestLayout button1.left==" + mButton1.getLeft());
        Log.d(TAG, "click requestLayout button1.top==" + mButton1.getTop());
        Log.d(TAG, "click requestLayout button1.x==" + mButton1.getX());
        Log.d(TAG, "click requestLayout button1.y==" + mButton1.getY());
        ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) mButton1
                .getLayoutParams();
        params.width += 100;
        params.leftMargin += 100;
        mButton1.requestLayout();
//        mButton1.setLayoutParams(params);
    }

    /**
     * 通过属性动画平移
     * view 的getLeft，getTop的值  不会  变化，
     * getX()和getY()的值  会  变化
     */
    private void objectAnimation() {
        Log.d(TAG, "click animator button1.left==" + mButton1.getLeft());
        Log.d(TAG, "click animator button1.top==" + mButton1.getTop());
        Log.d(TAG, "click animator button1.x==" + mButton1.getX());
        Log.d(TAG, "click animator button1.y==" + mButton1.getY());
        ObjectAnimator animator = ObjectAnimator.ofFloat(mButton1, "translationX", 0, 100)
                .setDuration(1000);
        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                Log.d(TAG, "start animator button1.left==" + mButton1.getLeft());
                Log.d(TAG, "start animator button1.top==" + mButton1.getTop());
                Log.d(TAG, "start animator button1.x==" + mButton1.getX());
                Log.d(TAG, "start animator button1.y==" + mButton1.getY());
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                // 通过log可以看到，view 的getLeft，getTop的值  不会  变化，
                // getX()和getY()的值  会  变化
                Log.d(TAG, "end animator button1.left=" + mButton1.getLeft());
                Log.d(TAG, "end animator button1.top==" + mButton1.getTop());
                Log.d(TAG, "end animator button1.x==" + mButton1.getX());
                Log.d(TAG, "end animator button1.y==" + mButton1.getY());
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        animator.start();
    }

    /**
     * 通过translation方法平移
     * view 的getLeft，getTop的值  不会  变化，
     * getX()和getY()的值  会  变化
     */
    private void translation() {
        Log.d(TAG, "before translationX button1.left==" + mButton1.getLeft());
        Log.d(TAG, "before translationY button1.top==" + mButton1.getTop());
        Log.d(TAG, "before translationX button1.x==" + mButton1.getX());
        Log.d(TAG, "before translationY button1.y==" + mButton1.getY());
        // 向右平移200
        mButton1.setTranslationX(200);
        // 向下平移300
        mButton1.setTranslationY(300);

        // 通过log可以看到，view 的getLeft，getTop的值  不会  变化，
        // getX()和getY()的值  会  变化
        Log.d(TAG, "after translationX button1.left=" + mButton1.getLeft());
        Log.d(TAG, "after translationY button1.top==" + mButton1.getTop());
        Log.d(TAG, "after translationX button1.x==" + mButton1.getX());
        Log.d(TAG, "after translationY button1.y==" + mButton1.getY());
    }

    @Override
    public boolean onLongClick(View v) {
        Toast.makeText(this, "long click", Toast.LENGTH_SHORT).show();
        return true;
    }
}
