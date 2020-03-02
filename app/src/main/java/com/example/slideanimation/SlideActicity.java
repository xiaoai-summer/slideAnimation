package com.example.slideanimation;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.Scroller;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * Created by wangxiaoyan on 2020/3/2.
 */
public class SlideActicity extends AppCompatActivity {
    private Scroller mScroller;
    private int mMenuWidth = 200;
    private Button mButtonOpen;
    private Button mButtonClose;
    private TextView mTxt;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.slide_layout);
        mButtonOpen = findViewById(R.id.open_slide_btn);
        mButtonClose = findViewById(R.id.close_slide_btn);
        mTxt = findViewById(R.id.text);
        mScroller = new Scroller(this, new DecelerateInterpolator(2.0F));

        mButtonOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mScroller.startScroll(mMenuWidth, 0, -mMenuWidth, 0);
                mTxt.setVisibility(View.VISIBLE);
            }
        });

        mButtonClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mScroller.startScroll(-mMenuWidth, 0, mMenuWidth, 0);
                mTxt.setVisibility(View.GONE);
            }
        });
    }
}
