package com.ivianuu.paletteglide.sample;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.graphics.Palette;
import android.widget.ImageView;

import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.ivianuu.paletteglide.PaletteProfile;
import com.ivianuu.paletteglide.targets.ColoredImageViewTarget;
import com.ivianuu.paletteglide.PaletteGlide;
import com.ivianuu.paletteglide.targets.ColoredTarget;
import com.ivianuu.paletteglide.targets.PaletteImageViewTarget;
import com.ivianuu.paletteglide.targets.PaletteTarget;
import com.ivianuu.paletteglide.targets.SwatchTarget;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PaletteGlide.with(this)
                .load("http://www.mostbeautifulthings.net/wp-content/uploads/2014/04/sweet-cats-10.jpg")
                .into(new ColoredImageViewTarget((ImageView) findViewById(R.id.image)) {
                    @Override
                    public void onColorReady(int color) {
                        findViewById(R.id.colored_button).setBackgroundColor(color);
                    }
                });
    }
}
