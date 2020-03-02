package com.example.slideanimation;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;

import androidx.annotation.Nullable;

/**
 * Created by wangxiaoyan on 2020/3/2.
 *
 *
 * ValueAnimator 是 ObjectAnimator 的父类，他两之间的区别是，ObjectAnimator 在ValueAnimator 的基础上，
 *  通过反射技术实现了动画功能，也就像我刚刚所举的例子，只要给了 ObjectAnimator 两个值（from，to），再确定动画类型（“scale，translate”），它就能自动生成动画。
 *  与之形成区别，虽然我们同样需要给 ValueAnimator 传递起始和最终两个值，但是 ValueAnimator 并不会自动去执行什么，
 *  而是会通过 addUpdateListener 的监听方法，在时间插值器的作用下，有序的返回一连串数值，然后我们就可以通过这些数值，对控件进行设置。
 *
 */
public class DrawBlueLightView extends View implements ValueAnimator.AnimatorUpdateListener {
    private ValueAnimator mValueAnimator;
    private float mAnimateValue;
    private Drawable mBlueLightDrawable, mWhiteLightDrawable;
    private int mWidth, mHeight, mBlueLightWidth, mBlueLightHeight;
    private Paint mPaint;

    public DrawBlueLightView(Context context) {
        this(context, null, 0);
    }

    public DrawBlueLightView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DrawBlueLightView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        mPaint = new Paint();

        mBlueLightDrawable = getResources().getDrawable(R.drawable.loading_blue);
        mWhiteLightDrawable = getResources().getDrawable(R.drawable.loading_white);
        mValueAnimator = ValueAnimator.ofFloat(0f, 1f);
        mValueAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
        mValueAnimator.setDuration(1500);
        mValueAnimator.addUpdateListener(this);
        mWidth = mWhiteLightDrawable.getIntrinsicWidth();
        mHeight = mWhiteLightDrawable.getIntrinsicHeight();
        mBlueLightWidth = mBlueLightDrawable.getIntrinsicWidth();
        mBlueLightHeight = mBlueLightDrawable.getIntrinsicHeight();
        setLayerType(LAYER_TYPE_SOFTWARE, mPaint);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (mWhiteLightDrawable instanceof BitmapDrawable && mBlueLightDrawable instanceof BitmapDrawable) {
            canvas.clipRect(getPaddingLeft(), getPaddingTop(), mWidth, mHeight);
            canvas.drawBitmap(((BitmapDrawable) mWhiteLightDrawable).getBitmap(), 0, 0, mPaint);
            if (mWidth > 0) {
                float x = (mWidth - mBlueLightWidth) * mAnimateValue;
                canvas.drawBitmap(((BitmapDrawable) mBlueLightDrawable).getBitmap(),x,0, mPaint);
            }
            mPaint.setXfermode(null);
            canvas.restore();
        }
        super.onDraw(canvas);
    }

    public void startAnimWithRepeat() {
        mValueAnimator.start();
        mValueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        invalidate();
    }

    public void cancelAnim(){
        mValueAnimator.end();
    }

    @Override
    public void onAnimationUpdate(ValueAnimator animation) {
        mAnimateValue = (float) animation.getAnimatedValue();
        invalidate();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(mWidth, mHeight);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (mValueAnimator != null){
            mValueAnimator.addUpdateListener(this);
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (mValueAnimator != null){
            mValueAnimator.removeAllUpdateListeners();
        }
    }
}
