package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class intro extends AppCompatActivity {

    ImageView imageView;
    TextView tv;
    Animation top, bottom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_intro);

        imageView = findViewById(R.id.imageView);
        tv = findViewById(R.id.textView);
        //Animation
        top = AnimationUtils.loadAnimation(this,R.anim.top);
        bottom = AnimationUtils.loadAnimation(this,R.anim.bottom);

        imageView.setAnimation(top);
        tv.setAnimation(bottom);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(intro.this,LoginActivity.class);
                Pair[] pairs = new Pair[2];
                pairs[0] = new Pair<View,String>(imageView, "logo_img");
                pairs[1] = new Pair<View,String>(tv, "logo_text");

                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(intro.this,pairs);
                    startActivity(intent,options.toBundle());
                }
            }
        },3000);

    }
}