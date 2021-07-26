package com.example.gamemyplane;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class MyPlane {

    public int mNum;
    private Bitmap mBitmap;
    private Bitmap mBitmapL;
    private Bitmap mBitmapR;
    public double mX;
    public double mY;
    private int mWidth;
    private int mHeight;
    private boolean mIsDraw = true;
    private boolean mIsAble = true;
    public boolean mGoCls = false;
    public boolean mActive = true;
    public double mGabX = 0;
    public double mGabY = 0;
    public ScreenConfig mScreenConfig;
    public int mDirectionMode = 1;
    public int mTimeLine = 0;
    public int mSensorMode;

    public MyPlane(ScreenConfig screenConfig, Bitmap bitmaporg, Bitmap bitmaporgL, Bitmap bitmaporgR) {
        mScreenConfig = screenConfig;
        mWidth = screenConfig.getX(200);
        mHeight = screenConfig.getY(200);
        mBitmap = Bitmap.createScaledBitmap(bitmaporg, mWidth, mHeight, false);
        mBitmapL = Bitmap.createScaledBitmap(bitmaporgL, mWidth, mHeight, false);
        mBitmapR = Bitmap.createScaledBitmap(bitmaporgR, mWidth, mHeight, false);
    }

    public void destroy() {
        try {
            if (mBitmap != null) {
                mBitmap.recycle();
            }
        }
        catch (Exception ex) {}
    }

    public void move(int x, int y) {
        mX = mScreenConfig.getX(x);
        mY = mScreenConfig.getY(y);
    }

    public void moveX(int x) {
        mX = mScreenConfig.getX(x);
    }

    public void moveY(int y) {
        mY = mScreenConfig.getY(y);
    }

    public boolean isSelected(int x, int y) {
        boolean is_selected = false;
        if (mIsAble == true) {
            if( (x > mX - mWidth/3 && x < mX + mWidth/3) && (y > mY - mHeight/3 && y < mY + mHeight/3)) {
                is_selected = true;
            }
        }
        return is_selected;
    }

    public void draw(Canvas canvas) {
        if (mIsDraw == true && mActive == true) {
            if (mDirectionMode == 1) {
                canvas.drawBitmap(mBitmap, (int)(mX - mWidth/2), (int)(mY - mHeight/2), null);
            } else if (mDirectionMode == 2) {
                canvas.drawBitmap(mBitmapR, (int)(mX - mWidth/2), (int)(mY - mHeight/2), null);
            } else if (mDirectionMode == 3) {
                canvas.drawBitmap(mBitmapL, (int)(mX - mWidth/2), (int)(mY - mHeight/2), null);
            }
        }
    }

}
