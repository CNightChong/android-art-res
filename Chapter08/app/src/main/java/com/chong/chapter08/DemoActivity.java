package com.chong.chapter08;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.WindowManager.LayoutParams;
import android.widget.TextView;

public class DemoActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.demo_1);
        initView();
    }

    private void initView() {
        Dialog dialog = new Dialog(this);
        TextView textView = new TextView(this);
        textView.setText("this is toast!");
        dialog.setContentView(textView);
        dialog.getWindow().setType(LayoutParams.TYPE_APPLICATION);
        dialog.show();
    }

}
