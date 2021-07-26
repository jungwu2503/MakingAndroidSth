package com.example.gamemyplane;

public class ScreenConfig {
    public static int mScreenWidth;
    public static int mScreenHeight;
    public static int mVirtualWidth;
    public static int mVirtualHeight;

    public ScreenConfig(int screenWidth, int screenHeight) {
        mScreenWidth = screenWidth;
        mScreenHeight = screenHeight;
    }

    public void setSize(int width, int height) {
        mVirtualWidth = width;
        mVirtualHeight = height;
    }

    public int getX(int x) {
        return (int) (x * mScreenWidth/mVirtualWidth);
    }

    public int getY(int y) {
        return (int) (y * mScreenHeight/mVirtualHeight);
    }

}
