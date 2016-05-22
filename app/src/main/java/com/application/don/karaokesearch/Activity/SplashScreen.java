package com.application.don.karaokesearch.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.application.don.karaokesearch.R;

/**
 * Created by Don on 5/22/16.
 */

public class SplashScreen extends Activity {

    private static int SPLASH_TIME_OUT = 5000;
    Thread splashTread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        startAnimations();
    }

    private void startAnimations() {
        Animation anim = AnimationUtils.loadAnimation(this, R.anim.translate);
        anim.reset();
        ImageView imgLogo = (ImageView) findViewById(R.id.imgLogo);
        imgLogo.clearAnimation();
        imgLogo.startAnimation(anim);

        anim = AnimationUtils.loadAnimation(this, R.anim.alpha);
        anim.reset();
        TextView txtAppName =(TextView) findViewById(R.id.txtAppName);
        txtAppName.clearAnimation();
        txtAppName.startAnimation(anim);

        splashTread = new Thread() {
            @Override
            public void run() {
                try {
                    int waited = 0;
                    // Splash screen pause time
                    while (waited < 3500) {
                        sleep(100);
                        waited += 100;
                    }
                    Intent intent = new Intent(SplashScreen.this,
                            MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(intent);
                    SplashScreen.this.finish();
                } catch (InterruptedException e) {
                    // do nothing
                } finally {
                    SplashScreen.this.finish();
                }

            }
        };
        splashTread.start();
    }
}
