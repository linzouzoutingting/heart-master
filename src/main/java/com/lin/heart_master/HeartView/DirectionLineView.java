package com.lin.heart_master.HeartView;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Property;
import android.view.View;

/**
 * Created by lpk on 2017/8/19.
 */
public class DirectionLineView extends View {
    public DirectionLineView(Context context) {
        super(context);
        init();
    }


    public DirectionLineView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public DirectionLineView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private float mProgress; // 控制器
    private Paint mPaint;

    private static final int Line_COLOR = 0xFFd81e06;

    private void init() {
        mPaint = new Paint();
        mPaint.setStrokeWidth(10);
        mPaint.setColor(Line_COLOR);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setStyle(Paint.Style.FILL);
    }

    private float mMaxSize;
    private Canvas mTempCanvas;
    private Bitmap mTempBitmap;

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mMaxSize = w / 2;
        mTempBitmap = Bitmap.createBitmap(getWidth(), getWidth(), Bitmap.Config.ARGB_8888);
        mTempCanvas = new Canvas(mTempBitmap); // 初始化画布
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (mProgress < 0.3){

        }else if (mProgress == 0.3){
            mProgress = 1 ;
            drawLines(canvas);
        }else {

            drawLines(canvas);
        }
    }

    private void drawLines(Canvas canvas) {
        mTempCanvas.drawColor(0xffffff, PorterDuff.Mode.CLEAR); // 清除颜色, 设置为白色
        //左
        mTempCanvas.drawLine(getWidth() / 2 - mMaxSize / 2-(mMaxSize/4*mProgress), getHeight() / 2, getWidth() / 2 - mMaxSize * 3 / 4, getHeight() / 2, mPaint);

        // 上
        mTempCanvas.drawLine(getWidth() / 2, getHeight() / 2 - mMaxSize / 2-(mMaxSize/4*mProgress), getWidth() / 2, mMaxSize / 4, mPaint);
        //        mTempCanvas.drawCircle(getWidth() / 2, getHeight() / 2, mInnerCircleRadiusProgress * mMaxCircleSize, mMaskPaint);
        //右
        mTempCanvas.drawLine(getWidth() / 2 + mMaxSize / 2+(mMaxSize/4*mProgress), getHeight() / 2, getWidth() / 2 + mMaxSize * 3 / 4, getHeight() / 2, mPaint);
        //下
        mTempCanvas.drawLine(getWidth() / 2, getHeight() / 2 + mMaxSize / 2+(mMaxSize/4*mProgress), getWidth() / 2, getHeight() / 2 + mMaxSize * 3 / 4, mPaint);

        canvas.drawBitmap(mTempBitmap, 0, 0, null);
    }

    public static final Property<DirectionLineView, Float> DIRECTION_LINE_PROGRESS =
            new Property<DirectionLineView, Float>(Float.class, "process") {
                @Override
                public Float get(DirectionLineView object) {
                    return object.getLineProgress();
                }

                @Override
                public void set(DirectionLineView object, Float value) {
                    object.setLineProgress(value);
                }
            };

    public float getLineProgress() {
        return mProgress;

    }

    public void setLineProgress(float lineProgress) {
        Log.i("Line",lineProgress+"   ");
        this.mProgress = lineProgress;
        invalidate();
    }
}
