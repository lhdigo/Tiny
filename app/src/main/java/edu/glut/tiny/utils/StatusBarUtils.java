package edu.glut.tiny.utils;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;


public class StatusBarUtils {

    /*
    * 获取状态栏高度
    * */
    public static int getHeight(Context context) {
        int statusBarHeight = 0;
        try {
            int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen",
                    "android");
            if (resourceId > 0) {
                statusBarHeight = context.getResources().getDimensionPixelSize(resourceId);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return statusBarHeight;
    }

    /*
    * 设置状态栏颜色
    * */
    public static void setColor(@NonNull Window window, @ColorInt int color) {
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        window.setStatusBarColor(color);
    }

    public static void setColor(Context context, @ColorInt int color) {
        if (context instanceof Activity) {
            setColor(((Activity) context).getWindow(), color);
        }
    }


}
