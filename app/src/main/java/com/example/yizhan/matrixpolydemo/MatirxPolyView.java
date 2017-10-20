package com.example.yizhan.matrixpolydemo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by yizhan on 2017/10/20.
 */

public class MatirxPolyView extends View {

    private final static int TRIGGER_AREA = 160;

    private Bitmap mBitmap;
    private int mBitmapWidth;
    private int mBitmapHeight;
    private Matrix mMatrix;
    private float[] mDst;
    private float[] mSrc;

    private int mPointCounter = 4;
    private Paint mPaint;

    public MatirxPolyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        init();
    }

    private void init() {

        initBitmap();

        initPointPaint();

        initMatrix();
    }

    private void initMatrix() {

        mMatrix = new Matrix();

        float[] temp = {0, 0, mBitmapWidth, 0, mBitmapWidth, mBitmapHeight, 0, mBitmapHeight};
        mSrc = temp.clone();
        mDst = temp.clone();

        //初始时PointCounter为4
        mMatrix.setPolyToPoly(mSrc, 0, mDst, 0, mPointCounter);
    }

    private void initPointPaint() {

        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(20);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setColor(Color.BLACK);
        mPaint.setAntiAlias(true);
    }

    private void initBitmap() {

        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.pro);

        mBitmapWidth = mBitmap.getWidth();
        mBitmapHeight = mBitmap.getHeight();
    }

    @Override
    protected void onDraw(Canvas canvas) {

        canvas.translate(100, 100);

        super.onDraw(canvas);

        //注意，画Bitmap时paint参数设置成了null
        canvas.drawBitmap(mBitmap, mMatrix, null);

        float[] dst = new float[8];
        mMatrix.mapPoints(dst, mSrc);

        for (int i = 0; i < mPointCounter; i++) {
            canvas.drawPoint(dst[2 * i], dst[2 * i + 1], mPaint);
        }
    }

    private void resetPolyMatrix() {
        mMatrix.reset();
        mMatrix.setPolyToPoly(mSrc, 0, mDst, 0, mPointCounter);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_MOVE:
                for (int i = 0; i < mPointCounter; i++) {
                    if (Math.abs(x - mDst[2 * i]) <= TRIGGER_AREA && Math.abs(y - mDst[2 * i + 1]) <= TRIGGER_AREA) {
                        mDst[2 * i] = (float) (x - 50);
                        mDst[2 * i + 1] = (float) (y - 50);
                        break;
                    }
                }
                resetPolyMatrix();
                invalidate();
                break;
        }

        return true;
    }

    public void setPointCounter(int pointCounter) {
        this.mPointCounter = pointCounter;
        mDst = mSrc.clone();
        resetPolyMatrix();
        invalidate();
    }
}
