package com.ivianuu.paletteglide;

import android.content.Context;

import com.bumptech.glide.BitmapRequestBuilder;
import com.bumptech.glide.DrawableTypeRequest;
import com.ivianuu.paletteglide.palette.BitmapPaletteTranscoder;
import com.ivianuu.paletteglide.palette.BitmapPaletteWrapper;

public class PaletteGlide {

    public static BitmapRequestBuilder from(DrawableTypeRequest drawableTypeRequest, Context context) {
        return drawableTypeRequest
                .asBitmap()
                .transcode(new BitmapPaletteTranscoder(context), BitmapPaletteWrapper.class);
    }

}
