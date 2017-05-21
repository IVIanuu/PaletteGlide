package com.ivianuu.paletteglide;

import android.graphics.Bitmap;
import android.support.annotation.ColorInt;
import android.support.annotation.Nullable;
import android.support.v7.graphics.Palette;

import java.util.Collections;
import java.util.Comparator;

import static com.ivianuu.paletteglide.PaletteProfile.MUTED;
import static com.ivianuu.paletteglide.PaletteProfile.MUTED_DARK;
import static com.ivianuu.paletteglide.PaletteProfile.MUTED_LIGHT;
import static com.ivianuu.paletteglide.PaletteProfile.VIBRANT;
import static com.ivianuu.paletteglide.PaletteProfile.VIBRANT_DARK;
import static com.ivianuu.paletteglide.PaletteProfile.VIBRANT_LIGHT;

/**
 * Author IVIanuu.
 */

public class PaletteUtils {

    @Nullable
    public static Palette generatePalette(Bitmap bitmap) {
        if (bitmap == null) return null;
        return Palette.from(bitmap).generate();
    }

    @ColorInt
    public static int getColor(@Nullable Palette palette, int fallback, PaletteProfile paletteProfile) {
        // hmm no palette so lets return
        if (palette == null) return fallback;

        switch (paletteProfile) {
            case VIBRANT:
                return palette.getVibrantSwatch() != null ? palette.getVibrantSwatch().getRgb(): fallback;
            case VIBRANT_DARK:
                return palette.getDarkVibrantSwatch() != null ? palette.getDarkVibrantSwatch().getRgb(): fallback;
            case VIBRANT_LIGHT:
                return palette.getLightVibrantSwatch() != null ? palette.getLightVibrantSwatch().getRgb(): fallback;
            case MUTED:
                return palette.getMutedSwatch() != null ? palette.getMutedSwatch().getRgb(): fallback;
            case MUTED_DARK:
                return palette.getDarkMutedSwatch() != null ? palette.getDarkMutedSwatch().getRgb(): fallback;
            case MUTED_LIGHT:
                return palette.getLightMutedSwatch() != null ? palette.getLightMutedSwatch().getRgb(): fallback;
            default:
                // return first available color
                if (palette.getVibrantSwatch() != null) {
                    return palette.getVibrantSwatch().getRgb();
                } else if (palette.getMutedSwatch() != null) {
                    return palette.getMutedSwatch().getRgb();
                } else if (palette.getDarkVibrantSwatch() != null) {
                    return palette.getDarkVibrantSwatch().getRgb();
                } else if (palette.getDarkMutedSwatch() != null) {
                    return palette.getDarkMutedSwatch().getRgb();
                } else if (palette.getLightVibrantSwatch() != null) {
                    return palette.getLightVibrantSwatch().getRgb();
                } else if (palette.getLightMutedSwatch() != null) {
                    return palette.getLightMutedSwatch().getRgb();
                } else if (!palette.getSwatches().isEmpty()) {
                    return Collections.max(palette.getSwatches(), SwatchComparator.getInstance()).getRgb();
                }
        }

        return fallback;
    }

    private static class SwatchComparator implements Comparator<Palette.Swatch> {
        private static SwatchComparator sInstance;

        static SwatchComparator getInstance() {
            if (sInstance == null) {
                sInstance = new SwatchComparator();
            }
            return sInstance;
        }

        @Override
        public int compare(Palette.Swatch lhs, Palette.Swatch rhs) {
            return lhs.getPopulation() - rhs.getPopulation();
        }
    }

}
