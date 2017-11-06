package dresteinadmin.kadibu.com.dresteinadmin;

import android.content.Intent;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class splash extends AppCompatActivity {
    ImageView imageView;
    TextView textView,textView1;
    Button button;
    MediaPlayer mp;
    public final int splash_time = 2000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ImageView imageView = (ImageView)findViewById(R.id.iv1);
        TextView textView = (TextView)findViewById(R.id.tv1);
        TextView textView1 = (TextView)findViewById(R.id.tv2);
        Typeface face = Typeface.createFromAsset(getAssets(),"RussoOne-Regular.ttf");
        textView.setTypeface(face);
        textView1.setTypeface(face);

        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_in);
        Animation animation1 = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_out);
        textView.startAnimation(animation);
        textView1.startAnimation(animation);
        imageView.startAnimation(animation);

        new Handler().postDelayed(new Runnable() {

            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */

            @Override
            public void run() {
                 Intent i = new Intent(splash.this, MainActivity.class);
                 startActivity(i);

                finish();
            }

        },splash_time);
    }
}
