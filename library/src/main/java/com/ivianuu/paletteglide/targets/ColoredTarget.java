package com.ivianuu.paletteglide.targets;

import android.graphics.Color;
import android.graphics.drawable.Drawable;

import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.ivianuu.paletteglide.PaletteProfile;
import com.ivianuu.paletteglide.PaletteUtils;
import com.ivianuu.paletteglide.palette.BitmapPaletteWrapper;

/**
 * @author Manuel Wrage (IVIanuu)
 */

public abstract class ColoredTarget extends SimpleTarget<BitmapPaletteWrapper> {

    private int mFallbackColor;
    private PaletteProfile mPaletteProfile;

    public ColoredTarget() {
        this(Color.TRANSPARENT, PaletteProfile.NO_PROFILE);
    }

    public ColoredTarget(int fallbackColor) {
        this(fallbackColor, PaletteProfile.NO_PROFILE);
    }

    public ColoredTarget(PaletteProfile paletteProfile) {
        this(Color.TRANSPARENT, paletteProfile);
    }

    public ColoredTarget(int fallbackColor, PaletteProfile paletteProfile) {
        mFallbackColor = fallbackColor;
        mPaletteProfile = paletteProfile;
    }

    @Override
    public void onResourceReady(BitmapPaletteWrapper resource, GlideAnimation<? super BitmapPaletteWrapper> glideAnimation) {
        onColorReady(PaletteUtils.getColor(resource.getPalette(), mFallbackColor, mPaletteProfile));
    }

    @Override
    public void onLoadFailed(Exception e, Drawable errorDrawable) {
        super.onLoadFailed(e, errorDrawable);
        onColorReady(mFallbackColor);
    }


    public abstract void onColorReady(int color);
}
