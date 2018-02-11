package com.gb.mui.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;


/**
 * 创建人 gaobo
 * 创建时间 2017年05月18日  10:16
 * 用途 用于倒数计时
 */
@SuppressLint("AppCompatCustomView")
public class TimerView extends TextView {
    // 倒计时总数，单位为秒
    private final int totalCount = 30;
    private Timer timer;
    // 经过的数字，比如：10代表经过了10秒
    private int throughCount = 0;
    // 默认显示的文字
    private CharSequence defaultText;

    public TimerView(Context context) {
        this(context, null);
    }

    public TimerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        defaultText = getText();
    }

    public void start() {
        start(this.totalCount);
    }

    /**
     * 开始倒计时
     * @param remain remain time.
     */
    public void start(int remain) throws RuntimeException {
        setEnabled(false);
//            setBackgroundResource(R.color.grey);
        this.throughCount = this.totalCount - remain;
        if (this.throughCount > this.totalCount || this.throughCount < 0) {
            throw new RuntimeException("Error remain time.");
        }
        timer = new Timer();
        timer.schedule(new WorkTask(), 500, 1000);
    }

    public void stop() {
        setEnabled(true);
        if (timer != null) {
            timer.cancel();
            timer.purge();
            timer = null;
        }
//            setBackgroundResource(R.color.btn_color_normal);
        setText(defaultText);
    }


    class WorkTask extends TimerTask {
        @Override
        public void run() {
            throughCount++;
            post(new Runnable() {
                @Override
                public void run() {
                    setText(totalCount - throughCount + ("(秒)"));
                }
            });
            if (throughCount == totalCount) {
                post(new Runnable() {
                    @Override
                    public void run() {
                        stop();
                    }
                });
            }
        }
    }

}
