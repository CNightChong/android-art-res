package com.chong.chapter04;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.Button;

import com.chong.chapter04.ui.TestButton;

public class TestActivity extends Activity implements OnClickListener {

    private static final String TAG = "TestActivity";

    private Button mButton1;
    private TestButton mButton2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        initView();
        measureView();
    }

    private void initView() {
        mButton1 = (Button) findViewById(R.id.button1);
        mButton1.setOnClickListener(this);
        mButton2 = (TestButton) findViewById(R.id.button2);
    }

    /**
     * 得到mButton1测量宽高
     */
    private void measureView() {
        int widthMeasureSpec = MeasureSpec.makeMeasureSpec((1 << 30) - 1, MeasureSpec.AT_MOST);
        int heightMeasureSpec = MeasureSpec.makeMeasureSpec(100, MeasureSpec.EXACTLY);
        mButton1.measure(widthMeasureSpec, heightMeasureSpec);
        Log.d(TAG, "measureView, width = " + mButton1.getMeasuredWidth()
                + " height = " + mButton1.getMeasuredHeight());
    }

    @Override
    protected void onStart() {
        super.onStart();
        // 得到mButton1测量宽高
        mButton1.post(new Runnable() {

            @Override
            public void run() {
                int width = mButton1.getMeasuredWidth();
                int height = mButton1.getMeasuredHeight();
                Log.d(TAG, "post, width = " + width
                        + " height = " + height);
            }
        });


        // 得到mButton1测量宽高
        ViewTreeObserver observer = mButton1.getViewTreeObserver();
        observer.addOnGlobalLayoutListener(new OnGlobalLayoutListener() {

            @SuppressWarnings("deprecation")
            @Override
            public void onGlobalLayout() {
                mButton1.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                int width = mButton1.getMeasuredWidth();
                int height = mButton1.getMeasuredHeight();
                Log.d(TAG, "ViewTreeObserver, width = " + width
                        + " height = " + height);
            }
        });
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            // 得到mButton1测量宽高
            int width = mButton1.getMeasuredWidth();
            int height = mButton1.getMeasuredHeight();
            Log.d(TAG, "onWindowFocusChanged, width = " + width
                    + " height= " + height);
        }
    }

    @Override
    public void onClick(View v) {
        if (v == mButton1) {
            Log.d(TAG, "measure width= " + mButton2.getMeasuredWidth()
                    + " height= " + mButton2.getMeasuredHeight());
            Log.d(TAG, "layout width= " + mButton2.getWidth()
                    + " height= " + mButton2.getHeight());
        }
    }

}
