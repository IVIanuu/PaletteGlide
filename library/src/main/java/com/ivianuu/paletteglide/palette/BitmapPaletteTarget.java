package com.ivianuu.paletteglide.palette;

import android.support.annotation.NonNull;
import android.widget.ImageView;

import com.bumptech.glide.request.target.ImageViewTarget;

public class BitmapPaletteTarget extends ImageViewTarget<BitmapPaletteWrapper> {
    public BitmapPaletteTarget(@NonNull ImageView view) {
        super(view);
    }

    @Override
    protected void setResource(BitmapPaletteWrapper bitmapPaletteWrapper) {
        if (view != null) {
            view.setImageBitmap(bitmapPaletteWrapper.getBitmap());
        }
    }
}
