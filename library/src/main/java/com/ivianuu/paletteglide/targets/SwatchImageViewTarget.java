package com.ivianuu.paletteglide.targets;

import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.graphics.Palette;
import android.widget.ImageView;

import com.bumptech.glide.request.animation.GlideAnimation;
import com.ivianuu.paletteglide.PaletteProfile;
import com.ivianuu.paletteglide.palette.BitmapPaletteTarget;
import com.ivianuu.paletteglide.palette.BitmapPaletteWrapper;

/**
 * @author Manuel Wrage (IVIanuu)
 */

public abstract class SwatchImageViewTarget extends BitmapPaletteTarget {

    private PaletteProfile mPaletteProfile;

    public SwatchImageViewTarget(@NonNull ImageView view, @NonNull PaletteProfile paletteProfile) {
        super(view);
        mPaletteProfile = paletteProfile;
    }

    @Override
    public void onResourceReady(BitmapPaletteWrapper resource, GlideAnimation<? super BitmapPaletteWrapper> glideAnimation) {
        super.onResourceReady(resource, glideAnimation);

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
