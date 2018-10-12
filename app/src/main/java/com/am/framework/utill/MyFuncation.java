package com.am.framework.utill;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.ViewGroup;

import com.am.framework.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;

public class MyFuncation {

    public static int pxToDp(int px) {
        return (int) (px / Resources.getSystem().getDisplayMetrics().density);
    }

    public static int dpToPx(int dp) {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }


    /**
     * @param context Context For the Loading Process to be cancelled if the activity closed
     * @param imageUrl imageUrl to be loaded in the View or viewGroup
     * @param viewGroup the viewGroup that we want to load the image into its background
     *  this method is used for the to load an image to a view as a background for the views
     *  that does not have setSrc attr
     */

    public void loadImageAsABackgournd(Context context, String imageUrl, ViewGroup viewGroup) {
        GlideApp.with(context).load(imageUrl).into(new SimpleTarget<Drawable>() {
            @Override
            public void onResourceReady(Drawable resource, Transition<? super Drawable> transition) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    viewGroup.setBackground(resource);
                }
            }
        });
    }

}
