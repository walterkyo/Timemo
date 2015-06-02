package com.timemohk.timemo.utils;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.os.Build;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.ProgressBar;

import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

/**
 * Created by walter.lam on 28/4/2015.
 */
public class Utils {
    private static int screenWidth = 0;
    private static int screenHeight = 0;

    public static ImageLoadingListener setMyImageLoadingListener(final ProgressBar progressbar){
        ImageLoadingListener listener = new ImageLoadingListener(){

            @Override
            public void onLoadingStarted(String imageUri, View view) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onLoadingFailed(String imageUri, View view,
                                        FailReason failReason) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onLoadingComplete(String imageUri,
                                          View view, Bitmap loadedImage) {
                progressbar.setVisibility(View.GONE);

            }

            @Override
            public void onLoadingCancelled(String imageUri,
                                           View view) {
                // TODO Auto-generated method stub

            }};
        return listener;
    }

    public static int dpToPx(int dp) {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }

    public static int getScreenHeight(Context c) {
        if (screenHeight == 0) {
            WindowManager wm = (WindowManager) c.getSystemService(Context.WINDOW_SERVICE);
            Display display = wm.getDefaultDisplay();
            Point size = new Point();
            display.getSize(size);
            screenHeight = size.y;
        }

        return screenHeight;
    }

    public static int getScreenWidth(Context c) {
        if (screenWidth == 0) {
            WindowManager wm = (WindowManager) c.getSystemService(Context.WINDOW_SERVICE);
            Display display = wm.getDefaultDisplay();
            Point size = new Point();
            display.getSize(size);
            screenWidth = size.x;
        }

        return screenWidth;
    }

    public static boolean isAndroid5() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP;
    }
}
