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

package com.ivianuu.paletteglide;

import android.graphics.Bitmap;
import android.support.annotation.ColorInt;
import android.support.annotation.Nullable;
import android.support.v7.graphics.Palette;

import java.util.Collections;
import java.util.Comparator;

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
