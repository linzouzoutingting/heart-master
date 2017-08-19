package com.lin.heart_master.HeartView;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.lin.heart_master.R;

/**
 * Created by lpk on 2017/8/19.
 */
public class HeartView extends FrameLayout {
    public HeartView(Context context) {
        super(context);
        init();
    }

    public HeartView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public HeartView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    private DecelerateInterpolator mDecelerate; // 减速插值
    private OvershootInterpolator mOvershoot; // 超出插值
    private AccelerateDecelerateInterpolator mAccelerateDecelerate; // 加速度减速插值
    private AnimatorSet mAnimatorSet; // 动画集合

    private ImageView iv_heart;
    private CircleView heart_circle;
    private DirectionLineView heart_lines ;
    private void init() {
        isInEditMode();

        LayoutInflater.from(getContext()).inflate(R.layout.view_heart, this, true);
        iv_heart = (ImageView) findViewById(R.id.iv_heart);
        heart_circle = (CircleView) findViewById(R.id.heart_circle);
         heart_lines = (DirectionLineView) findViewById(R.id.heart_lines);
        mDecelerate = new DecelerateInterpolator(); // 减速插值器
        mOvershoot = new OvershootInterpolator(4); // 超出插值器
        mAccelerateDecelerate = new AccelerateDecelerateInterpolator(); // 加速再减速插值器

        mAnimatorSet = new AnimatorSet();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (mAnimatorSet != null) {
            mAnimatorSet.cancel();
        }
        //先设置图片缩放
        iv_heart.animate().cancel();
        iv_heart.setScaleX(0);
        iv_heart.setScaleY(0);

        heart_circle.setInnerCircleRadiusProgress(0);
        heart_circle.setOuterCircleRadiusProgress(0);
        ObjectAnimator outerCircleAnimator = ObjectAnimator.ofFloat(heart_circle, CircleView.OUTER_CIRCLE_RADIUS_PROGRESS, 0.1f, 1f);
        outerCircleAnimator.setDuration(600);
        outerCircleAnimator.setInterpolator(mDecelerate);

        // 延迟擦除
        ObjectAnimator innerCircleAnimator = ObjectAnimator.ofFloat(heart_circle, CircleView.INNER_CIRCLE_RADIUS_PROGRESS, 0.1f, 1f);
        innerCircleAnimator.setDuration(400);
        innerCircleAnimator.setStartDelay(300);
        innerCircleAnimator.setInterpolator(mDecelerate);


        ObjectAnimator heartAnimator_X = ObjectAnimator.ofFloat(iv_heart, "scaleX", 0.3f, 1f);
        heartAnimator_X.setDuration(400);
        heartAnimator_X.setStartDelay(800);
        heartAnimator_X.setInterpolator(mOvershoot);

        ObjectAnimator heartAnimator_Y = ObjectAnimator.ofFloat(iv_heart, "scaleY", 0.3f, 1f);
        heartAnimator_Y.setDuration(400);
        heartAnimator_Y.setStartDelay(800);
        heartAnimator_Y.setInterpolator(mOvershoot);

        ObjectAnimator lineAnimator = ObjectAnimator.ofFloat(heart_lines, DirectionLineView.DIRECTION_LINE_PROGRESS, 0f, 1f);
        lineAnimator.setDuration(800);
        lineAnimator.setStartDelay(600);


        ObjectAnimator dismissAnimation = ObjectAnimator.ofFloat(iv_heart,"alpha", 1f, 0f);
        dismissAnimation.setDuration(500);
        dismissAnimation.setStartDelay(2000);

        // 放入动画集合
        mAnimatorSet.playTogether(
                outerCircleAnimator,
                innerCircleAnimator,
                heartAnimator_X,
                heartAnimator_Y,
                lineAnimator,
                dismissAnimation
        );


        mAnimatorSet.start();

    }
}
