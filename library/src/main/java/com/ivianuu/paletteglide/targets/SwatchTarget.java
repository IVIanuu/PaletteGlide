package com.ivianuu.paletteglide.targets;

/**
 * @author Manuel Wrage (IVIanuu)
 */

import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v7.graphics.Palette;

import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.ivianuu.paletteglide.PaletteProfile;
import com.ivianuu.paletteglide.palette.BitmapPaletteWrapper;

/**
 * @author Manuel Wrage (IVIanuu)
 */

public abstract class SwatchTarget extends SimpleTarget<BitmapPaletteWrapper> {

    private PaletteProfile mPaletteProfile;

    public SwatchTarget(PaletteProfile paletteProfile) {
        mPaletteProfile = paletteProfile;
    }

    @Override
    public void onResourceReady(BitmapPaletteWrapper resource, GlideAnimation<? super BitmapPaletteWrapper> glideAnimation) {
        Palette palette = resource.getPalette();
        if (palette == null) {
            onSwatchReady(null);
            return;
        }

        switch (mPaletteProfile) {
            case VIBRANT:
                onSwatchReady(resource.getPalette().getVibrantSwatch());
                break;
            case VIBRANT_DARK:
                onSwatchReady(resource.getPalette().getDarkVibrantSwatch());
                break;
            case VIBRANT_LIGHT:
                onSwatchReady(resource.getPalette().getLightVibrantSwatch());
                break;
            case MUTED:
                onSwatchReady(resource.getPalette().getVibrantSwatch());
                break;
            case MUTED_DARK:
                onSwatchReady(resource.getPalette().getDarkMutedSwatch());
                break;
            case MUTED_LIGHT:
                onSwatchReady(resource.getPalette().getLightMutedSwatch());
                break;
        }
    }

    @Override
    public void onLoadFailed(Exception e, Drawable errorDrawable) {
        super.onLoadFailed(e, errorDrawable);
        onSwatchReady(null);
    }


    public abstract void onSwatchReady(@Nullable Palette.Swatch swatch);
}
