package com.example.slideanimation;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.view.animation.BounceInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * Created by wangxiaoyan on 2020/3/2.
 *
 */
public class DrawActivity extends AppCompatActivity {
    private DrawBlueLightView mDrawBlueLightView;
    private Button mStartAnimBtn;
    private Button mStopAnimBtn;
    private DrawCircleView mDrawCircleView;
    private RelativeLayout mMainView;
    private Interpolator mInterp[] = {new BounceInterpolator(), new LinearInterpolator()};
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.draw_bluelight_view);

        mDrawBlueLightView = findViewById(R.id.blue_light_view);
        mStartAnimBtn = findViewById(R.id.start_anim_btn);
        mStopAnimBtn = findViewById(R.id.stop_anim_btn);
        mDrawCircleView = new DrawCircleView(this);
        mDrawCircleView.setMinimumWidth(300);
        mDrawCircleView.setMinimumHeight(500);
        mMainView = findViewById(R.id.mian_view);
        mStartAnimBtn.requestFocus();

        mStartAnimBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDrawBlueLightView.startAnimWithRepeat();
            }
        });

        mStopAnimBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDrawBlueLightView.cancelAnim();
                mMainView.addView(mDrawCircleView);
            }
        });

        mStartAnimBtn.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

                ViewPropertyAnimator ani = mStartAnimBtn.animate();
                if (hasFocus) {
                    ani.scaleX(1.05f).scaleY(1.05f).setDuration(50).setInterpolator(mInterp[0]);
                } else {
                    ani.scaleX(1.f).scaleY(1.f).setDuration(50).setInterpolator(mInterp[1]);
                }
            }
        });

        mStopAnimBtn.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

                ViewPropertyAnimator ani = mStopAnimBtn.animate();
                if (hasFocus) {
                    ani.scaleX(1.05f).scaleY(1.05f).setDuration(50).start();
                } else {
                    ani.scaleX(1.f).scaleY(1.f).setDuration(50).start();
                }
            }
        });

        mDrawCircleView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                mDrawCircleView.mCurrentX = event.getX();
                mDrawCircleView.mCurrentY = event.getY();
                mDrawCircleView.invalidate();//重绘
                return true;
            }
        });
    }
}
