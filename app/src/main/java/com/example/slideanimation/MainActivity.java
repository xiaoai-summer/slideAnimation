package com.example.slideanimation;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button mButton;
    private TextView mTextAni;
    private Handler mUIHandler = new Handler(Looper.getMainLooper());
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mButton = findViewById(R.id.btn);
        mTextAni = findViewById(R.id.textAni);
        mTextAni.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 *
                 * Android 属性动画框架 ObjectAnimator、ValueAnimator
                 *
                 *
                 * ObjectAnimator 实现四大动画框架：
                 *
                 * alpha
                 * scaleX/scaleY
                 * translateX/translateY
                 * rotation
                 *
                 */
                //将一个TextView在5秒中内从常规变换成全透明，再从全透明变换成常规
//                ObjectAnimator animator = ObjectAnimator.ofFloat(mTextAni,"alpha",1f,0f,1f);
//                animator.setDuration(5000);
//                animator.start();
                //将TextView进行一次360度的旋转
//                ObjectAnimator animator = ObjectAnimator.ofFloat(mTextAni,"rotation",0f, 360f);
//                animator.setDuration(5000);
//                animator.start();
                //将TextView先向左移出屏幕，然后再移动回来
//                float currentTranslationX = mTextAni.getTranslationX();
//                ObjectAnimator animator = ObjectAnimator.ofFloat(mTextAni,"translationX",currentTranslationX, -2000f,currentTranslationX);
//                animator.setDuration(5000);
//                animator.start();
                //将TextView在垂直方向上放大3倍再还原
//                ObjectAnimator animator = ObjectAnimator.ofFloat(mTextAni,"scaleY",1f,3f,1f);
//                animator.setDuration(5000);
//                animator.start();
                //让TextView先从屏幕外移动进屏幕，然后开始旋转360度，旋转的同时进行淡入淡出操作
//                ObjectAnimator moveIn = ObjectAnimator.ofFloat(mTextAni, "translationX", -2000f, 0f);
//                ObjectAnimator rotate = ObjectAnimator.ofFloat(mTextAni, "rotation", 0f, 360f);
//                ObjectAnimator fadeInOut = ObjectAnimator.ofFloat(mTextAni, "alpha", 1f, 0f, 1f);
//                AnimatorSet animSet = new AnimatorSet();
//                animSet.play(rotate).with(fadeInOut).after(moveIn);
//                animSet.setDuration(5000);
//                animSet.start();

                //补间动画
                /**
                 * view动画也称为补间动画，因为我们只需要拿到一个view，设定它开始和结束的位置，中间的view会自动由系统补齐，而不需要帧动画每一幅图都是提前准备好的。
                 */
                //translation 平移动画
                Animation translateAnimation = new TranslateAnimation(TranslateAnimation.RELATIVE_TO_SELF, 0.0f, TranslateAnimation.RELATIVE_TO_SELF, 0.0f,
                        TranslateAnimation.RELATIVE_TO_SELF, 1.0f, TranslateAnimation.RELATIVE_TO_SELF, 0.0f);
                translateAnimation.setInterpolator(new AccelerateInterpolator());
                translateAnimation.setDuration(300);//动画持续时间
                mButton.setVisibility(View.VISIBLE);
                mButton.startAnimation(translateAnimation);//添加的动画效果

                mUIHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Animation translateAnimation = new TranslateAnimation(TranslateAnimation.RELATIVE_TO_SELF,0.0f, TranslateAnimation.RELATIVE_TO_SELF, 0.0f,
                        TranslateAnimation.RELATIVE_TO_SELF, 0.0f, TranslateAnimation.RELATIVE_TO_SELF, 1.0f);//设置平移的起点和终点
                        translateAnimation.setInterpolator(new AccelerateInterpolator());
                        translateAnimation.setDuration(300);//动画持续时间
                        mButton.setVisibility(View.GONE);
                        mButton.startAnimation(translateAnimation);//添加的动画效果
                    }
                },2000);

                StringBuilder builder = new StringBuilder();
                Toast.makeText(getApplicationContext(),String.valueOf(builder.toString().length()),Toast.LENGTH_LONG).show();
            }
        });
    }
}
