package logic2magic.com.health_fitness;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class RW_Activity extends AppCompatActivity {

    Intent intent;
    String receivedData = null;
    String goal = null;
    float receivedCalories = 0;
    int newCalories = 0;
    String newCalories_string = null;
    public final static String MESSAGEKEY = "logicToMagic.com/reduceWeight";


    TextView textView_showReceived, textView_showAnswer;
    Button button_weightLoss, button_maintainWeight, button_weightGain;
    Button button_suggested, button_aggressive, button_reckless;
    LinearLayout linearLayout_receivedCalories, linearLayout_goal, linearLayout_intensity, linearLayout_requiredEnergy, linearLayout_goalImg, linearLayout_intensityImg, linearLayout_requiredEnergyImg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rw_layout);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        textView_showReceived = (TextView) findViewById(R.id.tv_showReceived);
        textView_showAnswer = (TextView) findViewById(R.id.tv_showAnswer);

        button_weightLoss = (Button) findViewById(R.id.btn_weightLoss);
        button_maintainWeight = (Button) findViewById(R.id.btn_maintainWeight);
        button_weightGain = (Button) findViewById(R.id.btn_weightGain);
        button_suggested = (Button) findViewById(R.id.btn_suggested);
        button_aggressive = (Button) findViewById(R.id.btn_aggressive);
        button_reckless = (Button) findViewById(R.id.btn_reckless);

        linearLayout_receivedCalories = (LinearLayout) findViewById(R.id.ll_receivedCalories);
        linearLayout_goalImg = (LinearLayout) findViewById(R.id.ll_imgGoal);
        linearLayout_goal = (LinearLayout) findViewById(R.id.ll_goal);
        linearLayout_intensityImg = (LinearLayout) findViewById(R.id.ll_imgIntensity);
        linearLayout_intensity = (LinearLayout) findViewById(R.id.ll_intensity);
        linearLayout_requiredEnergyImg = (LinearLayout) findViewById(R.id.ll_imgRequiredEnergy);
        linearLayout_requiredEnergy = (LinearLayout) findViewById(R.id.ll_requiredEnergy);

        intent = getIntent();
        receivedData  = intent.getStringExtra(MESSAGEKEY);
        textView_showReceived.setText(receivedData);
        receivedCalories = Float.valueOf(receivedData);


        Animation animation_ll_received = new AlphaAnimation(0.0f,1.0f);
        animation_ll_received.setDuration(800);
        animation_ll_received.setStartOffset(100);
        linearLayout_receivedCalories.setVisibility(View.VISIBLE);
        linearLayout_receivedCalories.startAnimation(animation_ll_received);

        Animation animation_ll_goalImg = new AlphaAnimation(0.0f,1.0f);
        animation_ll_goalImg.setDuration(800);
        animation_ll_goalImg.setStartOffset(600);
        linearLayout_goalImg.setVisibility(View.VISIBLE);
        linearLayout_goalImg.startAnimation(animation_ll_goalImg);

        Animation animation_ll_goal = new AlphaAnimation(0.0f,1.0f);
        animation_ll_goal.setDuration(800);
        animation_ll_goal.setStartOffset(1000);
        linearLayout_goal.setVisibility(View.VISIBLE);
        linearLayout_goal.startAnimation(animation_ll_goal);



        button_weightLoss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goal = "weightLoss";
                button_weightLoss.setBackgroundResource(R.drawable.roundcorner_button_selected);
                button_maintainWeight.setBackgroundResource(R.drawable.roundcorner_button);
                button_weightGain.setBackgroundResource(R.drawable.roundcorner_button);

                linearLayout_requiredEnergyImg.setVisibility(View.GONE);
                linearLayout_requiredEnergy.setVisibility(View.GONE);

                final Animation an = AnimationUtils.loadAnimation(getBaseContext(),R.anim.animation_rotate_arrow_from_right2left);
                linearLayout_intensityImg.setVisibility(View.VISIBLE);
                linearLayout_intensityImg.startAnimation(an);

                Animation animation_ll_intensity = new AlphaAnimation(0.0f,1.0f);
                animation_ll_intensity.setDuration(800);
                animation_ll_intensity.setStartOffset(400);
                linearLayout_intensity.setVisibility(View.VISIBLE);
                linearLayout_intensity.startAnimation(animation_ll_intensity);
            }
        });
        button_maintainWeight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goal = "maintainWeight";
                button_weightLoss.setBackgroundResource(R.drawable.roundcorner_button);
                button_maintainWeight.setBackgroundResource(R.drawable.roundcorner_button_selected);
                button_weightGain.setBackgroundResource(R.drawable.roundcorner_button);
                newCalories = (int) receivedCalories;
                newCalories_string = String.valueOf(newCalories) + " cal/day";
                textView_showAnswer.setText(newCalories_string);

                linearLayout_intensityImg.setVisibility(View.GONE);
                linearLayout_intensity.setVisibility(View.GONE);

                Animation animation_ll_requiredEnergyImg = new AlphaAnimation(0.0f,1.0f);
                animation_ll_requiredEnergyImg.setDuration(800);
                animation_ll_requiredEnergyImg.setStartOffset(200);
                linearLayout_requiredEnergyImg.setVisibility(View.VISIBLE);
                linearLayout_requiredEnergyImg.startAnimation(animation_ll_requiredEnergyImg);

                Animation animation_ll_requiredEnergy = new AlphaAnimation(0.0f,1.0f);
                animation_ll_requiredEnergy.setDuration(800);
                animation_ll_requiredEnergy.setStartOffset(400);
                linearLayout_requiredEnergy.setVisibility(View.VISIBLE);
                linearLayout_requiredEnergy.startAnimation(animation_ll_requiredEnergy);
            }
        });
        button_weightGain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goal = "weightGain";
                button_weightLoss.setBackgroundResource(R.drawable.roundcorner_button);
                button_maintainWeight.setBackgroundResource(R.drawable.roundcorner_button);
                button_weightGain.setBackgroundResource(R.drawable.roundcorner_button_selected);

                linearLayout_requiredEnergyImg.setVisibility(View.GONE);
                linearLayout_requiredEnergy.setVisibility(View.GONE);

                final Animation an = AnimationUtils.loadAnimation(getBaseContext(),R.anim.animation_rotate_arrow_from_left2right);
                linearLayout_intensityImg.setVisibility(View.VISIBLE);
                linearLayout_intensityImg.startAnimation(an);

                Animation animation_ll_intensity = new AlphaAnimation(0.0f,1.0f);
                animation_ll_intensity.setDuration(800);
                animation_ll_intensity.setStartOffset(400);
                linearLayout_intensity.setVisibility(View.VISIBLE);
                linearLayout_intensity.startAnimation(animation_ll_intensity);

            }
        });
        button_suggested.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button_suggested.setBackgroundResource(R.drawable.roundcorner_button_selected);
                button_aggressive.setBackgroundResource(R.drawable.roundcorner_button);
                button_reckless.setBackgroundResource(R.drawable.roundcorner_button);

                switch (goal)
                {
                    case "weightLoss":
                    {
                        newCalories = (int) (receivedCalories - (receivedCalories * 0.15f));
                        break;
                    }
                    case "weightGain":
                    {
                        newCalories = (int) (receivedCalories + (receivedCalories * 0.15f));
                        break;
                    }
                }

                newCalories_string = String.valueOf(newCalories) + " cal/day";
                textView_showAnswer.setText(newCalories_string);

                final Animation an = AnimationUtils.loadAnimation(getBaseContext(),R.anim.animation_rotate_arrow_from_right2left);
                linearLayout_requiredEnergyImg.setVisibility(View.VISIBLE);
                linearLayout_requiredEnergyImg.startAnimation(an);

                Animation animation_ll_requiredEnergy = new AlphaAnimation(0.0f,1.0f);
                animation_ll_requiredEnergy.setDuration(800);
                animation_ll_requiredEnergy.setStartOffset(400);
                linearLayout_requiredEnergy.setVisibility(View.VISIBLE);
                linearLayout_requiredEnergy.startAnimation(animation_ll_requiredEnergy);
            }
        });
        button_aggressive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button_suggested.setBackgroundResource(R.drawable.roundcorner_button);
                button_aggressive.setBackgroundResource(R.drawable.roundcorner_button_selected);
                button_reckless.setBackgroundResource(R.drawable.roundcorner_button);

                switch (goal)
                {
                    case "weightLoss":
                    {
                        newCalories = (int) (receivedCalories - (receivedCalories * 0.20f));
                        break;
                    }
                    case "weightGain":
                    {
                        newCalories = (int) (receivedCalories + (receivedCalories * 0.20f));
                        break;
                    }
                }

                newCalories_string = String.valueOf(newCalories) + " cal/day";
                textView_showAnswer.setText(newCalories_string);

                Animation animation_ll_requiredEnergyImg = new AlphaAnimation(0.0f,1.0f);
                animation_ll_requiredEnergyImg.setDuration(800);
                animation_ll_requiredEnergyImg.setStartOffset(200);
                linearLayout_requiredEnergyImg.setVisibility(View.VISIBLE);
                linearLayout_requiredEnergyImg.startAnimation(animation_ll_requiredEnergyImg);

                Animation animation_ll_requiredEnergy = new AlphaAnimation(0.0f,1.0f);
                animation_ll_requiredEnergy.setDuration(800);
                animation_ll_requiredEnergy.setStartOffset(400);
                linearLayout_requiredEnergy.setVisibility(View.VISIBLE);
                linearLayout_requiredEnergy.startAnimation(animation_ll_requiredEnergy);
            }
        });
        button_reckless.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button_suggested.setBackgroundResource(R.drawable.roundcorner_button);
                button_aggressive.setBackgroundResource(R.drawable.roundcorner_button);
                button_reckless.setBackgroundResource(R.drawable.roundcorner_button_selected);

                switch (goal)
                {
                    case "weightLoss":
                    {
                        newCalories = (int) (receivedCalories - (receivedCalories * 0.25f));
                        break;
                    }
                    case "weightGain":
                    {
                        newCalories = (int) (receivedCalories + (receivedCalories * 0.25f));
                        break;
                    }
                }

                newCalories_string = String.valueOf(newCalories) + " cal/day";
                textView_showAnswer.setText(newCalories_string);

                final Animation an = AnimationUtils.loadAnimation(getBaseContext(),R.anim.animation_rotate_arrow_from_left2right);
                linearLayout_requiredEnergyImg.setVisibility(View.VISIBLE);
                linearLayout_requiredEnergyImg.startAnimation(an);

                Animation animation_ll_requiredEnergy = new AlphaAnimation(0.0f,1.0f);
                animation_ll_requiredEnergy.setDuration(800);
                animation_ll_requiredEnergy.setStartOffset(400);
                linearLayout_requiredEnergy.setVisibility(View.VISIBLE);
                linearLayout_requiredEnergy.startAnimation(animation_ll_requiredEnergy);
            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
