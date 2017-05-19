package com.ivianuu.paletteglide.sample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.RequestManager;
import com.ivianuu.paletteglide.ColoredTarget;
import com.ivianuu.paletteglide.PaletteGlide;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PaletteGlide.with(this)
                .load("http://www.mostbeautifulthings.net/wp-content/uploads/2014/04/sweet-cats-10.jpg")
                .into(new ColoredTarget((ImageView) findViewById(R.id.image)) {
                    @Override
                    public void onColorReady(int color) {
                        findViewById(R.id.colored_button).setBackgroundColor(color);
                    }
                });
    }
}
