package com.ivianuu.paletteglide.targets;

import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v7.graphics.Palette;

import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.ivianuu.paletteglide.palette.BitmapPaletteWrapper;

/**
 * @author Manuel Wrage (IVIanuu)
 */

public abstract class PaletteTarget extends SimpleTarget<BitmapPaletteWrapper> {

    public PaletteTarget() {

    }

    @Override
    public void onResourceReady(BitmapPaletteWrapper resource, GlideAnimation<? super BitmapPaletteWrapper> glideAnimation) {
        onPaletteReady(resource.getPalette());
    }

    @Override
    public void onLoadFailed(Exception e, Drawable errorDrawable) {
        super.onLoadFailed(e, errorDrawable);
        onPaletteReady(null);
    }


    public abstract int onPaletteReady(@Nullable Palette palette);
}
