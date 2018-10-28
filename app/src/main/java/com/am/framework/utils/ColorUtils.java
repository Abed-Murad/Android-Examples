/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.am.framework.utils;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;

import com.am.framework.R;

public class ColorUtils {

    public static int getViewHolderBackgroundColorFromInstance(Context context, int instanceNum) {
        switch (instanceNum) {
            case 0:
                return ContextCompat.getColor(context, R.color.black_alpha_60);
            case 1:
                return ContextCompat.getColor(context, R.color.black_alpha_60);
            case 2:
                return ContextCompat.getColor(context, R.color.black_alpha_60);
            case 3:
                return ContextCompat.getColor(context, R.color.black_alpha_60);
            case 4:
                return ContextCompat.getColor(context, R.color.black_alpha_60);
            case 5:
                return ContextCompat.getColor(context, R.color.black_alpha_60);
            case 6:
                return ContextCompat.getColor(context, R.color.black_alpha_60);
            case 7:
                return ContextCompat.getColor(context, R.color.black_alpha_60);
            case 8:
                return ContextCompat.getColor(context, R.color.black_alpha_60);
            case 9:
                return ContextCompat.getColor(context, R.color.black_alpha_60);
            case 10:
                return ContextCompat.getColor(context, R.color.black_alpha_60);
            case 11:
                return ContextCompat.getColor(context, R.color.black_alpha_60);
            case 12:
                return ContextCompat.getColor(context, R.color.black_alpha_60);
            case 13:
                return ContextCompat.getColor(context, R.color.black_alpha_60);
            case 14:
                return ContextCompat.getColor(context, R.color.black_alpha_60);
            case 15:
                return ContextCompat.getColor(context, R.color.black_alpha_60);
            case 16:
                return ContextCompat.getColor(context, R.color.black_alpha_60);
            case 17:
                return ContextCompat.getColor(context, R.color.black_alpha_60);

            default:
                return Color.WHITE;
        }
    }
}
