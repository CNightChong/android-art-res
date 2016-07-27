package com.chong.chapter07;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.nineoldandroids.animation.IntEvaluator;
import com.nineoldandroids.animation.ValueAnimator;
import com.nineoldandroids.animation.ValueAnimator.AnimatorUpdateListener;

public class DemoActivity_1 extends Activity {
    private static final String TAG = "DemoActivity_1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.demo_1);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            Button button = (Button) findViewById(R.id.button1);
            performAnimate(button, button.getWidth(), 500);
        }
    }

    private void performAnimate(final View target, final int start, final int end) {
        // 5000ms内将一个值从1变到100，每10ms增加2
        ValueAnimator valueAnimator = ValueAnimator.ofInt(1, 100);
        valueAnimator.addUpdateListener(new AnimatorUpdateListener() {

            // 估值器，持有一个IntEvaluator对象，方便下面估值的时候使用
            private IntEvaluator mEvaluator = new IntEvaluator();

            @Override
            public void onAnimationUpdate(ValueAnimator animator) {
                // 动画刷新频率10ms/帧，该方法每10ms调用一次
                // 获得当前动画的进度值，整型，1-100之间
                int currentValue = (Integer) animator.getAnimatedValue();
                Log.d(TAG, "current value ==== " + currentValue);

                // 获得当前进度占整个动画过程的比例，浮点型，0-1之间
                float fraction = animator.getAnimatedFraction();
                Log.d(TAG, "fraction === " + fraction);
                // 直接调用整型估值器通过比例计算出宽度，然后再设给Button
                // strat = button的初始宽度，end = 500
                // width + f * (500 - width)
                target.getLayoutParams().width = mEvaluator.evaluate(fraction, start, end);
                Log.d(TAG, "width === " + target.getLayoutParams().width);
                target.requestLayout();
            }
        });

        valueAnimator.setDuration(5000).start();
    }

}
