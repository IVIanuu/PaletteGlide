/*
 * Copyright 2017 Manuel Wrage
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.ivianuu.paletteglide.targets;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.widget.ImageView;

import com.bumptech.glide.request.animation.GlideAnimation;
import com.ivianuu.paletteglide.PaletteProfile;
import com.ivianuu.paletteglide.PaletteUtils;
import com.ivianuu.paletteglide.palette.BitmapPaletteTarget;
import com.ivianuu.paletteglide.palette.BitmapPaletteWrapper;

/**
 * @author Manuel Wrage (IVIanuu)
 */
public abstract class ColoredImageViewTarget extends BitmapPaletteTarget {

    private int mFallbackColor;
    private PaletteProfile mPaletteProfile;

    public ColoredImageViewTarget(@NonNull ImageView view) {
        this(view, Color.TRANSPARENT, PaletteProfile.NO_PROFILE);
    }

    public ColoredImageViewTarget(@NonNull ImageView view, @ColorInt int fallbackColor) {
        this(view, fallbackColor, PaletteProfile.NO_PROFILE);
    }

    public ColoredImageViewTarget(@NonNull ImageView view, @NonNull PaletteProfile paletteProfile) {
        this(view, Color.TRANSPARENT, paletteProfile);
    }

    public ColoredImageViewTarget(@NonNull ImageView view, @ColorInt int fallbackColor, @NonNull PaletteProfile paletteProfile) {
        super(view);
        mFallbackColor = fallbackColor;
        mPaletteProfile = paletteProfile;
    }

    @Override
    public void onResourceReady(BitmapPaletteWrapper resource, GlideAnimation<? super BitmapPaletteWrapper> glideAnimation) {
        super.onResourceReady(resource, glideAnimation);

        onColorReady(PaletteUtils.getColor(resource.getPalette(), mFallbackColor, mPaletteProfile));
    }

    @Override
    public void onLoadFailed(Exception e, Drawable errorDrawable) {
        super.onLoadFailed(e, errorDrawable);
        onColorReady(mFallbackColor);
    }

    public abstract void onColorReady(@ColorInt int color);

}
