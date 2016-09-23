package com.tracyis.scrolltextview;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;

import com.tracyis.scrolltextview.view.AutoHorizontalScrollTextView;
import com.tracyis.scrolltextview.view.AutoVerticalScrollTextView;
import com.tracyis.slidingtextview.R;

public class MainActivity extends AppCompatActivity {

    private AutoHorizontalScrollTextView mAhstv_horizontal;
    private AutoVerticalScrollTextView mAvstv_vertical;
    public String[] strv = {"锄禾日当午", "汗滴禾下土", "谁知盘中餐", "粒粒皆辛苦"};
    public String strh = "锄禾日当午 汗滴禾下土 谁知盘中餐 粒粒皆辛苦";
    private boolean isRunning = true;
    private int number = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        mAhstv_horizontal = (AutoHorizontalScrollTextView) findViewById(R.id.ahstv_horizontal);
        mAhstv_horizontal.setText(strh);

        mAvstv_vertical = (AutoVerticalScrollTextView) findViewById(R.id.avstv_vertical);
        mAvstv_vertical.setText(strv[0]);

        new Thread() {
            @Override
            public void run() {
                while (isRunning) {
                    SystemClock.sleep(2000);
                    mHandler.sendEmptyMessage(199);
                }
            }
        }.start();
    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 199) {
                mAvstv_vertical.next();
                number++;
                mAvstv_vertical.setText(strv[number % strv.length]);
            }
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
