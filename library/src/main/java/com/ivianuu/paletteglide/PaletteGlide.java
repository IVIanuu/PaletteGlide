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

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.support.annotation.IntegerRes;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

import com.bumptech.glide.BitmapRequestBuilder;
import com.bumptech.glide.DrawableTypeRequest;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.ivianuu.paletteglide.palette.BitmapPaletteTranscoder;
import com.ivianuu.paletteglide.palette.BitmapPaletteWrapper;

import java.io.File;

public class PaletteGlide {

    public static PaletteGlideRequestManager with(@NonNull Context context) {
        return new PaletteGlideRequestManager(context);
    }

    public static PaletteGlideRequestManager with(@NonNull Activity activity) {
        return new PaletteGlideRequestManager(activity);
    }

    public static PaletteGlideRequestManager with(@NonNull Fragment fragment) {
        return new PaletteGlideRequestManager(fragment);
    }

    public static PaletteGlideRequestManager with(@NonNull android.app.Fragment fragment) {
        return new PaletteGlideRequestManager(fragment);
    }

    public static PaletteGlideRequestManager with(@NonNull FragmentActivity fragmentActivity) {
        return new PaletteGlideRequestManager(fragmentActivity);
    }

    public static class PaletteGlideRequestManager {

        private Context context;
        private RequestManager requestManager;

        private PaletteGlideRequestManager(Context context) {
            this.context = context;
            this.requestManager = Glide.with(context);
        }

        private PaletteGlideRequestManager(Activity activity) {
            this.context = activity;
            this.requestManager = Glide.with(activity);
        }

        private PaletteGlideRequestManager(Fragment fragment) {
            this.context = fragment.getContext();
            this.requestManager = Glide.with(fragment);
        }

        private PaletteGlideRequestManager(android.app.Fragment fragment) {
            this.context = fragment.getActivity();
            this.requestManager = Glide.with(fragment);
        }

        private PaletteGlideRequestManager(FragmentActivity fragmentActivity) {
            this.context = fragmentActivity;
            this.requestManager = Glide.with(fragmentActivity);
        }

        @NonNull
        public BitmapRequestBuilder load(byte[] model) {
            return createRequest(requestManager.load(model));
        }

        @NonNull
        public BitmapRequestBuilder load(@NonNull File file) {
            return createRequest(requestManager.load(file));
        }

        @NonNull
        public BitmapRequestBuilder load(@IntegerRes int resourceId) {
            return createRequest(requestManager.load(resourceId));
        }

        @NonNull
        public BitmapRequestBuilder load(@NonNull String string) {
            return createRequest(requestManager.load(string));
        }

        @NonNull
        public BitmapRequestBuilder load(@NonNull Object object) {
            return createRequest(requestManager.load(object));
        }

        @NonNull
        public BitmapRequestBuilder load(@NonNull Uri uri) {
            return createRequest(requestManager.load(uri));
        }

        @NonNull
        public BitmapRequestBuilder loadFromMediaStore(@NonNull Uri uri) {
            return createRequest(requestManager.loadFromMediaStore(uri));
        }

        @NonNull
        private BitmapRequestBuilder createRequest(DrawableTypeRequest drawableTypeRequest) {
            return drawableTypeRequest.asBitmap().transcode(new BitmapPaletteTranscoder(context), BitmapPaletteWrapper.class);
        }

    }

}
