package com.example.slideanimation;

import android.os.Bundle;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * Created by wangxiaoyan on 2020/3/2.
 */
public class MActivity extends AppCompatActivity {
    private Button mStartAnimBtn;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout);
        mStartAnimBtn = findViewById(R.id.start_anim_btn);
        mStartAnimBtn.requestFocus();
    }
}
