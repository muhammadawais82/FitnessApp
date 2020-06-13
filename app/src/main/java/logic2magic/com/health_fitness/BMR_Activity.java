package logic2magic.com.health_fitness;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import org.w3c.dom.Text;

public class BMR_Activity extends AppCompatActivity {

    TextView textView_weight, textView_bmr_answer;
    TextView textView_sedentary, textView_lightActive, textView_moderatelyActive, textView_veryActive, textView_extremelyActive;
    EditText editText_age, editText_weight, editText_height;
    Button button_cal;
    ImageButton button_male, button_female;
    ImageView imageView_genderMan, imageView_genderWoman;
    ToggleButton toggleButton_height, toggleButton_weight;
    LinearLayout linearLayout_gender, linearLayout_energyExpenditure, linearLayout_Answer, linearLayout_upper, linearLayout_lower, linearLayout_allContent;

    String gender = "male" , weight_unit = "kg", height_unit = "cm";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bmr_layout);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        linearLayout_gender = (LinearLayout) findViewById(R.id.ll_gender);
        linearLayout_energyExpenditure = (LinearLayout) findViewById(R.id.ll_energyExpenditure);
        linearLayout_Answer = (LinearLayout) findViewById(R.id.ll_answer);
        linearLayout_upper = (LinearLayout) findViewById(R.id.ll_upper);
        linearLayout_lower = (LinearLayout) findViewById(R.id.ll_question_And_Calculate);
        linearLayout_allContent = (LinearLayout) findViewById(R.id.ll_allContent);


        button_cal = (Button) findViewById(R.id.btn_cal);
        button_male = (ImageButton) findViewById(R.id.btn_male);
        button_female = (ImageButton) findViewById(R.id.btn_female);

        toggleButton_height = (ToggleButton) findViewById(R.id.tb_height);
        toggleButton_weight = (ToggleButton) findViewById(R.id.tb_weight);

        editText_age = (EditText) findViewById(R.id.et_age);
        editText_height = (EditText) findViewById(R.id.et_height);
        editText_weight = (EditText) findViewById(R.id.et_weight);

        textView_sedentary = (TextView) findViewById(R.id.tv_sedentary);
        textView_lightActive = (TextView) findViewById(R.id.tv_lightlyActive);
        textView_moderatelyActive = (TextView) findViewById(R.id.tv_moderatelyActive);
        textView_veryActive = (TextView) findViewById(R.id.tv_veryActive);
        textView_extremelyActive = (TextView) findViewById(R.id.tv_extremelyActive);

        textView_bmr_answer = (TextView) findViewById(R.id.tv_bmr_ans);

        // Adding the code for the Startup Anmiations in this BMI Activity....................

        {
            Animation startup_Animation = new AlphaAnimation(0.0f,1.0f);
            startup_Animation.setDuration(1000);
            startup_Animation.setStartOffset(500);
//            linearLayout_allContent.startAnimation(startup_Animation);
            linearLayout_upper.startAnimation(startup_Animation);
            linearLayout_lower.startAnimation(startup_Animation);
        }


        toggleButton_height.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    toggleButton_height.setBackgroundResource(R.drawable.switch_ft);
                    height_unit = "inch";
                    editText_height.setHint("INCH");
                } else {
                    toggleButton_height.setBackgroundResource(R.drawable.switch_cm);
                    height_unit = "cm";
                    editText_height.setHint("CM");
                }
            }
        });

        toggleButton_weight.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    toggleButton_weight.setBackgroundResource(R.drawable.switch_kg);
                    weight_unit = "kg";
                    editText_weight.setHint("KG");
                } else {
                    toggleButton_weight.setBackgroundResource(R.drawable.switch_lb);
                    weight_unit = "lb";
                    editText_weight.setHint("Lb");
                }
            }
        });

        button_cal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String age = editText_age.getText().toString();
                String weight = editText_weight.getText().toString();
                String height = editText_height.getText().toString();

                float a = 0, h = 0, w = 0;

                float w_kg = 0;
                float h_cm = 0;

                try {
                    float ag = Integer.parseInt(age);
                    float wei = Integer.parseInt(weight);
                    float hei = Integer.parseInt(height);

                    a = ag;
                    h = hei;
                    w = wei;

                } catch (Exception e) {
                    Toast.makeText(BMR_Activity.this, "Please Enter Values!", Toast.LENGTH_LONG).show();
                }

                if (weight_unit.equals("kg")) {
                    float kg = w;
                    w_kg = kg;
                } else if (weight_unit.equals("lb")) {
                    float kg = (float) (w / 2.2046);
                    w_kg = kg;
                }

                if (height_unit.equals("cm")) {
                    float cm = h;
                    h_cm = cm;
                } else if (height_unit.equals("inch")) {
                    float cm = (float) (h * 2.54);
                    h_cm = cm;
                }


                if (gender.equals("male")) {
                    float bmr = (float) ((10 * w_kg) + (6.25 * h_cm) - (5 * a) + 5);

                    float bmr_round = Math.round(bmr * 100) / 100.f;

                    textView_bmr_answer.setText(bmr_round + " kcal");

                    float sedentary = (float) (bmr_round * 1.2);
                    float sedentary_round = Math.round(sedentary * 100) / 100.f;

                    float lightActive = (float) (bmr_round * 1.375);
                    float lightActive_round = Math.round(lightActive * 100) / 100.f;

                    float moderatelyActive = (float) (bmr_round * 1.55);
                    float moderatelyActive_round = Math.round(moderatelyActive * 100) / 100.f;

                    float veryActive = (float) (bmr_round * 1.725);
                    float veryActive_round = Math.round(veryActive * 100) / 100.f;

                    float extremelyActive = (float) (bmr_round * 1.9);
                    float extremelyActive_round = Math.round(extremelyActive * 100) / 100.f;

                    textView_sedentary.setText("Sedentary                         "+ (sedentary_round) +" Cal");
                    textView_lightActive.setText("Lightly Active                   "+ (lightActive_round) + " Cal");
                    textView_moderatelyActive.setText("Moderately Active          "+ (moderatelyActive_round) +" Cal");
                    textView_veryActive.setText("Very Active                       "+ (veryActive_round) +" Cal");
                    textView_extremelyActive.setText("Extremely Active            "+ (extremelyActive_round) +" Cal");
                }

                if (gender.equals("female"))
                {
                    float bmr = (float) ((10 * w_kg) + (6.25 * h_cm) - (5 * a) - 161);

                    float bmr_round = Math.round(bmr * 100) / 100.f;

                    textView_bmr_answer.setText(bmr_round + " kcal");

                    float sedentary = (float) (bmr_round * 1.2);
                    float sedentary_round = Math.round(sedentary * 100) / 100.f;

                    float lightActive = (float) (bmr_round * 1.375);
                    float lightActive_round = Math.round(lightActive * 100) / 100.f;

                    float moderatelyActive = (float) (bmr_round * 1.55);
                    float moderatelyActive_round = Math.round(moderatelyActive * 100) / 100.f;

                    float veryActive = (float) (bmr_round * 1.725);
                    float veryActive_round = Math.round(veryActive * 100) / 100.f;

                    float extremelyActive = (float) (bmr_round * 1.9);
                    float extremelyActive_round = Math.round(extremelyActive * 100) / 100.f;

                    textView_sedentary.setText("Sedentary                         "+ (sedentary_round) +" Cal");
                    textView_lightActive.setText("Lightly Active                   "+ (lightActive_round) + " Cal");
                    textView_moderatelyActive.setText("Moderately Active          "+ (moderatelyActive_round) +" Cal");
                    textView_veryActive.setText("Very Active                       "+ (veryActive_round) +" Cal");
                    textView_extremelyActive.setText("Extremely Active            "+ (extremelyActive_round) +" Cal");
                }


            }
        });



        button_male.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button_male.setBackgroundResource(R.drawable.male_select);
                button_female.setBackgroundResource(R.drawable.female);
                gender = "male";

                linearLayout_gender.setBackgroundResource(R.drawable.man);

                Animation animation_genderChange = new AlphaAnimation(0.0f, 0.9f);
                animation_genderChange.setDuration(1000);
                linearLayout_gender.startAnimation(animation_genderChange);

//                imageView_genderWoman.setVisibility(v.INVISIBLE);
//                imageView_genderMan.setVisibility(v.VISIBLE);

//                textView_underweight.setText("Underweight        <----20");
//                textView_normal.setText("Normal                   20-25");
//                textView_overweight.setText("Overweight           25-30");
//                textView_obese.setText("Obese                     30-40");
//                textView_morbidlyobese.setText("Morbidly Obese   40---->");

                linearLayout_energyExpenditure.startAnimation(animation_genderChange);


            }
        });

        button_female.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button_female.setBackgroundResource(R.drawable.female_select);
                button_male.setBackgroundResource(R.drawable.male);
                gender = "female";

                linearLayout_gender.setBackgroundResource(R.drawable.woman);

                Animation animation_genderChange = new AlphaAnimation(0.0f, 0.9f);
                animation_genderChange.setDuration(1000);
                linearLayout_gender.startAnimation(animation_genderChange);

//                imageView_genderMan.setVisibility(v.INVISIBLE);
//                imageView_genderWoman.setVisibility(v.VISIBLE);

//                textView_underweight.setText("Underweight        <----19");
//                textView_normal.setText("Normal                   19-24");
//                textView_overweight.setText("Overweight           24-30");
//                textView_obese.setText("Obese                     30-40");
//                textView_morbidlyobese.setText("Morbidly Obese   40---->");

                linearLayout_energyExpenditure.startAnimation(animation_genderChange);
            }
        });

    }

}
