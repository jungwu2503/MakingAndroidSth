package com.example.gamemyplane;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.io.BufferedInputStream;
import java.util.*;

public class MainView extends SurfaceView implements SurfaceHolder.Callback {
    private MainActivity mGameActivity;
    MainThread mMainThread;
    Handler mHandler;
    Context mMainContext;
    boolean mDrawCls = false;
    private ScreenConfig mScreenConfig;

    private MyPlane mMyPlane;
    public MainView(Context r, AttributeSet a) {
        super(r, a);
        getHolder().addCallback(this);
        mMainThread = new MainThread(getHolder(), this);
        setFocusable(true);
        mMainContext = r;
    }

    public void init(int width, int height, MainActivity mGameActivity) {
        this.mGameActivity = mGameActivity;
        mScreenConfig = new ScreenConfig(width, height);
        mScreenConfig.setSize(1000,2000);
        try {
            Bitmap mMyPlaneBitmap = loadBitmap("plane.png");
            Bitmap mMyPlaneLBitmap = loadBitmap("planel.png");
            Bitmap mMyPlaneRBitmap = loadBitmap("planer.png");
            mMyPlane = new MyPlane(mScreenConfig, mMyPlaneBitmap, mMyPlaneLBitmap, mMyPlaneRBitmap);
            mMyPlane.move(500, 1000);
        }
        catch (Exception exLoadBitmap) {
        }
        mDrawCls = true;
    }

    public Bitmap loadBitmap(String filename) throws Exception {
        Bitmap bm;
        AssetManager am = mMainContext.getAssets();
        BufferedInputStream buf = new BufferedInputStream(am.open(filename));
        bm = BitmapFactory.decodeStream(buf);
        return bm;
    }

    @Override
    public void onDraw(Canvas canvas) {
        if (mDrawCls == false)
            return;
        canvas.drawColor(Color.rgb(0, 0, 100));
        mMyPlane.draw(canvas);
    }

    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
    }

    public void surfaceCreated(SurfaceHolder holder) {
        mMainThread.setRunning(true);
        try {
            if (mMainThread.getState() == Thread.State.TERMINATED) {
                mMainThread = new MainThread(getHolder(),this);
                mMainThread.setRunning(true);
                setFocusable(true);
                mMainThread.start();
            } else {
                mMainThread.start();
            }
        }
        catch (Exception ex) {
            Log.i("MainView", "ex:" + ex.toString());
        }
    }

    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;
        mMainThread.setRunning(false);
        while(retry) {
            try {
                mMainThread.join();
                retry = false;
            }
            catch (Exception e) {}
        }
    }

}
