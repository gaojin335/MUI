package com.gb.mui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.gb.mui.view.TimerView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    TimerView mTimerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initListener();
    }

    private void initView() {
        mTimerView = findViewById(R.id.timerView);
    }

    private void initListener() {
        mTimerView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.timerView:
                mTimerView.start();
                break;
        }
    }
}
