package com.ivianuu.paletteglide;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.bumptech.glide.request.animation.GlideAnimation;
import com.ivianuu.paletteglide.palette.BitmapPaletteTarget;
import com.ivianuu.paletteglide.palette.BitmapPaletteWrapper;

public abstract class ColoredTarget extends BitmapPaletteTarget {

    private int mFallbackColor;

    public ColoredTarget(ImageView view) {
        this(view, Color.TRANSPARENT);
    }

    public ColoredTarget(ImageView view, int fallbackColor) {
        super(view);
        mFallbackColor = fallbackColor;
    }

    @Override
    public void onLoadFailed(Exception e, Drawable errorDrawable) {
        super.onLoadFailed(e, errorDrawable);
        onColorReady(mFallbackColor);
    }

    @Override
    public void onResourceReady(BitmapPaletteWrapper resource, GlideAnimation<? super BitmapPaletteWrapper> glideAnimation) {
        super.onResourceReady(resource, glideAnimation);
        onColorReady(ColorUtils.getColor(resource.getPalette(), mFallbackColor));
    }

    public abstract void onColorReady(int color);

}
