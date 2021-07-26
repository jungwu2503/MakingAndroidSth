package com.example.gamemyplane;

import android.graphics.Canvas;
import android.util.Log;
import android.view.SurfaceHolder;

public class MainThread extends Thread{
    private SurfaceHolder surfaceholder;
    private MainView mainView;
    private boolean running = false;

    public MainThread(SurfaceHolder surfaceHolder, MainView mainView) {
        surfaceholder = surfaceHolder;
        this.mainView = mainView;
    }

    public SurfaceHolder getSurfaceHolder() {
        return surfaceholder;
    }

    public void setRunning(boolean run) {
        running = run;
    }

    @Override
    public void run() {
        try {
            Canvas c;
            while(running) {
                c = null;
                try {
                    c = surfaceholder.lockCanvas(null);
                    synchronized (surfaceholder) {
                        try {
                            mainView.onDraw(c);
                            Thread.sleep(2);
                        }
                        catch(Exception exTemp) {
                            Log.e("에러", exTemp.toString());
                        }
                    }
                }
                finally {
                    if (c!=null)
                        surfaceholder.unlockCanvasAndPost(c);
                }
            }
        }
        catch (Exception exTot) {
            Log.e("에러", exTot.toString());
        }
    }

}
