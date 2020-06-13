package logic2magic.com.health_fitness;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by qasim on 22-Feb-16.
 */
public class Splash_Screen extends Activity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen_layout);

        final TextView tv = (TextView) findViewById(R.id.textView);
        final TextView tv_subHeading = (TextView) findViewById(R.id.textView_subHeading);
        final ImageView iv = (ImageView) findViewById(R.id.imageView);
        final Animation an = AnimationUtils.loadAnimation(getBaseContext(),R.anim.animation_rotate);

        iv.startAnimation(an);


        Animation animation_tv = new AlphaAnimation(0.0f,1.0f);
        animation_tv.setDuration(1500);
        animation_tv.setStartOffset(500);
        tv.startAnimation(animation_tv);

        Animation animation_tv_subHeading = new AlphaAnimation(0.0f,1.0f);
        animation_tv_subHeading.setDuration(500);
        animation_tv_subHeading.setStartOffset(1500);
        tv_subHeading.startAnimation(animation_tv_subHeading);



        an.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                finish();
                Intent i = new Intent(getBaseContext(),Sign_in_EMP.class);
                startActivity(i);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
}
