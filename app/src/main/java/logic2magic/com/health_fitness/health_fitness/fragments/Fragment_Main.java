package logic2magic.com.health_fitness.health_fitness.fragments;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import logic2magic.com.health_fitness.BFP_Activity;
import logic2magic.com.health_fitness.BMI_Activity;
import logic2magic.com.health_fitness.BMR_Activity;
import logic2magic.com.health_fitness.DWI_Activity;
import logic2magic.com.health_fitness.EBV_Activity;
import logic2magic.com.health_fitness.EWV_Activity;
import logic2magic.com.health_fitness.IBW_Activity;
import logic2magic.com.health_fitness.MainPage;
import logic2magic.com.health_fitness.R;

/**
 * Created by qasim on 19-Feb-16.
 */
public class Fragment_Main extends Fragment {



    ImageView imageview_logo;
    ScrollView scrollView;
    LinearLayout button_bmi, button_bmr, button_bfp, button_ibw, button_dwi, button_ebv, button_ewv;

    Animation an_button = null;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_main,container,false);

        imageview_logo = (ImageView) view.findViewById(R.id.image_logo);
        button_bmi = (LinearLayout) view.findViewById(R.id.ll_bmibtn);
        button_bmr = (LinearLayout) view.findViewById(R.id.ll_bmrbtn);
        button_bfp = (LinearLayout) view.findViewById(R.id.ll_bfpbtn);
        button_ibw = (LinearLayout) view.findViewById(R.id.ll_ibwbtn);
        button_ebv = (LinearLayout) view.findViewById(R.id.ll_ebvbtn);
        button_dwi = (LinearLayout) view.findViewById(R.id.ll_dwibtn);
        button_ewv = (LinearLayout) view.findViewById(R.id.ll_ewvbtn);

        scrollView = (ScrollView) view.findViewById(R.id.sv_layout);

        an_button = AnimationUtils.loadAnimation(getActivity().getApplicationContext(),R.anim.animation_rotate_buttons);


// Animation of Fade in and out of Logo at main hadder
        Animation animation_image = new AlphaAnimation(0.0f,1.0f);
        animation_image.setDuration(500);
        animation_image.setStartOffset(500);
        imageview_logo.startAnimation(animation_image);

// Animation of Body Mass Index Button
        Animation animation_button_bmi = new AlphaAnimation(0.0f,1.0f);
        animation_button_bmi.setDuration(500);
        animation_button_bmi.setStartOffset(800);
        button_bmi.startAnimation(animation_button_bmi);

// Animation of Basal Metabolic Rate Button
        Animation animation_button_bmr = new AlphaAnimation(0.0f,1.0f);
        animation_button_bmr.setDuration(500);
        animation_button_bmr.setStartOffset(1100);
        button_bmr.startAnimation(animation_button_bmr);

// Animation of Body Fat Percentage Button
        Animation animation_button_bfp = new AlphaAnimation(0.0f,1.0f);
        animation_button_bfp.setDuration(500);
        animation_button_bfp.setStartOffset(1400);
        button_bfp.startAnimation(animation_button_bfp);

// Animation of Ideal Body Weight Button
        Animation animation_button_ibw = new AlphaAnimation(0.0f,1.0f);
        animation_button_ibw.setDuration(500);
        animation_button_ibw.setStartOffset(1700);
        button_ibw.startAnimation(animation_button_ibw);

// Animation of Daily Water Intake Button
        Animation animation_button_dwi = new AlphaAnimation(0.0f,1.0f);
        animation_button_dwi.setDuration(500);
        animation_button_dwi.setStartOffset(2000);
        button_dwi.startAnimation(animation_button_dwi);

// Animation of Body Blood Volume Button
        Animation animation_button_ebv = new AlphaAnimation(0.0f,1.0f);
        animation_button_ebv.setDuration(500);
        animation_button_ebv.setStartOffset(2300);
        button_ebv.startAnimation(animation_button_ebv);

// Animation of Body Water Volume Button
        Animation animation_button_ewv = new AlphaAnimation(0.0f,1.0f);
        animation_button_ewv.setDuration(500);
        animation_button_ewv.setStartOffset(2600);
        button_ewv.startAnimation(animation_button_ewv);

       imageview_logo.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

               final Animation an = AnimationUtils.loadAnimation(getActivity().getApplicationContext(),R.anim.animation_rotate);

               imageview_logo.startAnimation(an);
           }
       });



        button_bmi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startColorAnimation(button_bmi);
//                Animation animation = new AlphaAnimation(1.0f,0.0f);
//                animation.setDuration(1000);
//                button_bmi.startAnimation(animation);
//
//                Animation animation1 = new RotateAnimation(360, 0);
//                animation1.setDuration(500);
                button_bmi.startAnimation(an_button);

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Activity activity = getActivity();
                        Intent intent = new Intent(activity, BMI_Activity.class);
                        startActivity(intent);
                    }
                }, 500);

            }


        });

        button_bmr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startColorAnimation(button_bmi);
//                Animation animation = new AlphaAnimation(1.0f,0.0f);
//                animation.setDuration(1000);
//                button_bmi.startAnimation(animation);
//
//                Animation animation1 = new RotateAnimation(0,360);
//                animation1.setDuration(500);
                button_bmr.startAnimation(an_button);

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Activity activity = getActivity();
                        Intent intent = new Intent(activity, BMR_Activity.class);
                        startActivity(intent);
                    }
                }, 500);
            }
        });

        button_bfp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startColorAnimation(button_bmi);
//                Animation animation = new AlphaAnimation(1.0f,0.0f);
//                animation.setDuration(1000);
//                button_bmi.startAnimation(animation);
//
//                Animation animation1 = new RotateAnimation(360,0);
//                animation1.setDuration(500);
                button_bfp.startAnimation(an_button);

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Activity activity = getActivity();
                        Intent intent = new Intent(activity, BFP_Activity.class);
                        startActivity(intent);
                    }
                }, 500);
            }
        });

        button_ibw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startColorAnimation(button_bmi);
//                Animation animation = new AlphaAnimation(1.0f,0.0f);
//                animation.setDuration(1000);
//                button_bmi.startAnimation(animation);
//
//                Animation animation1 = new RotateAnimation(0,360);
//                animation1.setDuration(500);
                button_ibw.startAnimation(an_button);

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Activity activity = getActivity();
                        Intent intent = new Intent(activity, IBW_Activity.class);
                        startActivity(intent);
                    }
                }, 500);

            }
        });

        button_ebv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startColorAnimation(button_bmi);
//                Animation animation = new AlphaAnimation(1.0f,0.0f);
//                animation.setDuration(1000);
//                button_bmi.startAnimation(animation);
//
//                Animation animation1 = new RotateAnimation(360,0);
//                animation1.setDuration(500);
                button_ebv.startAnimation(an_button);

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Activity activity = getActivity();
                        Intent intent = new Intent(activity, EBV_Activity.class);
                        startActivity(intent);
                    }
                }, 500);
            }
        });

        button_dwi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startColorAnimation(button_bmi);
//                Animation animation = new AlphaAnimation(1.0f,0.0f);
//                animation.setDuration(1000);
//                button_bmi.startAnimation(animation);
//
//                Animation animation1 = new RotateAnimation(360,0);
//                animation1.setDuration(500);
                button_dwi.startAnimation(an_button);

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Activity activity = getActivity();
                        Intent intent = new Intent(activity, DWI_Activity.class);
                        startActivity(intent);
                    }
                }, 500);
            }
        });


        button_ewv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startColorAnimation(button_bmi);
//                Animation animation = new AlphaAnimation(1.0f,0.0f);
//                animation.setDuration(1000);
//                button_bmi.startAnimation(animation);
//
//                Animation animation1 = new RotateAnimation(360,0);
//                animation1.setDuration(500);
                button_ewv.startAnimation(an_button);

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Activity activity = getActivity();
                        Intent intent = new Intent(activity, EWV_Activity.class);
                        startActivity(intent);
                    }
                }, 500);
            }
        });

        return view;

    }
//    int colorStart;
//    public void startColorAnimation(View view)
//    {
//        colorStart = view.getSolidColor();
//        int colorEnd = 0xffff0000;
//
//        ValueAnimator colorAnimator = ObjectAnimator.ofInt(view,"backgroundColor",colorStart,colorEnd);
//
//        colorAnimator.setDuration(500);
//        colorAnimator.setEvaluator(new ArgbEvaluator());
//        //colorAnimator.setRepeatCount(1);
//        //colorAnimator.setRepeatMode(ValueAnimator.RESTART);
//        colorAnimator.start();
//        button_bmi.setBackgroundColor(colorStart);
//
//    }
}
