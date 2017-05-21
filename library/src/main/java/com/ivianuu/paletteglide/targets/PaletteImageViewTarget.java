package com.ivianuu.paletteglide.targets;

import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.graphics.Palette;
import android.widget.ImageView;

import com.bumptech.glide.request.animation.GlideAnimation;
import com.ivianuu.paletteglide.palette.BitmapPaletteTarget;
import com.ivianuu.paletteglide.palette.BitmapPaletteWrapper;

/**
 * @author Manuel Wrage (IVIanuu)
 */

public abstract class PaletteImageViewTarget extends BitmapPaletteTarget {

    public PaletteImageViewTarget(@NonNull ImageView view) {
        super(view);
    }

    @Override
    public void onResourceReady(BitmapPaletteWrapper resource, GlideAnimation<? super BitmapPaletteWrapper> glideAnimation) {
        super.onResourceReady(resource, glideAnimation);
        onPaletteReady(resource.getPalette());
    }

    @Override
    public void onLoadFailed(Exception e, Drawable errorDrawable) {
        super.onLoadFailed(e, errorDrawable);
        onPaletteReady(null);
    }

    public abstract void onPaletteReady(@Nullable Palette palette);
}
