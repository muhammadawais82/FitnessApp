package logic2magic.com.health_fitness;

import android.app.Activity;
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

public class BMI_Activity extends AppCompatActivity {

    TextView textView_weight, textView_bmi;
    TextView textView_underweight, textView_normal, textView_overweight, textView_obese, textView_morbidlyobese;
    EditText editText_weight, editText_height;
    Button button_cal;
    ImageButton button_male, button_female;
   // ImageView imageView_genderMan, imageView_genderWoman;
    ToggleButton toggleButton_height, toggleButton_weight;
    LinearLayout linearLayout_gender, linearLayout_weightMeasures, linearLayout_Answer, linearLayout_upper, linearLayout_lower, linearLayout_allContent;

    String gender = "male" , weight_unit = "kg", height_unit = "cm";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bmi_layout);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        textView_bmi = (TextView) findViewById(R.id.tv_bmi);
        textView_weight = (TextView) findViewById(R.id.tv_weight);

        textView_underweight = (TextView) findViewById(R.id.tv_undrweight);
        textView_normal = (TextView) findViewById(R.id.tv_normal);
        textView_overweight = (TextView) findViewById(R.id.tv_overweight);
        textView_obese = (TextView) findViewById(R.id.tv_obese);
        textView_morbidlyobese = (TextView) findViewById(R.id.tv_morbidlyobese);

        editText_height = (EditText) findViewById(R.id.et_height);
        editText_weight = (EditText) findViewById(R.id.et_weight);

        button_cal = (Button) findViewById(R.id.btn_cal);
        button_male = (ImageButton) findViewById(R.id.btn_male);
        button_female = (ImageButton) findViewById(R.id.btn_female);

        toggleButton_height = (ToggleButton) findViewById(R.id.tb_height);
        toggleButton_weight = (ToggleButton) findViewById(R.id.tb_weight);

//        imageView_genderMan = (ImageView) view.findViewById(R.id.iv_gender_man);
//        imageView_genderWoman = (ImageView) view.findViewById(R.id.iv_gender_woman);
        linearLayout_gender = (LinearLayout) findViewById(R.id.ll_gender);
        linearLayout_weightMeasures = (LinearLayout) findViewById(R.id.ll_weightMeasuers);
        linearLayout_Answer = (LinearLayout) findViewById(R.id.ll_answer);
        linearLayout_upper = (LinearLayout) findViewById(R.id.ll_upper);
        linearLayout_lower = (LinearLayout) findViewById(R.id.ll_question_And_Calculate);
        linearLayout_allContent = (LinearLayout) findViewById(R.id.ll_allContent);


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
                try {
                    String weight = editText_weight.getText().toString();
                    String height = editText_height.getText().toString();
                    float h = 0, w = 0;

                    try {
                        float hei = Integer.parseInt(height);
                        float wei = Integer.parseInt(weight);
                        h = hei;
                        w = wei;
                    } catch (Exception e) {
                        Toast.makeText(BMI_Activity.this, "Please Enter Values!", Toast.LENGTH_LONG).show();
                    }

                    float h_meter_sq = 0;
                    float w_kg = 0;

                    Animation animation_calculate = new RotateAnimation(0,360);
                    animation_calculate.setDuration(1000);
                    linearLayout_Answer.startAnimation(animation_calculate);

                    if (height_unit.equals("inch")) {
                        float h_meter = (float) (h / 39.370);
                        h_meter_sq = h_meter * h_meter;
                    } else if (height_unit.equals("cm")) {
                        float h_meter = h / 100;
                        h_meter_sq = h_meter * h_meter;
                    }

                    if (weight_unit.equals("lb")) {
                        float kg = (float) (w / 2.2046);
                        w_kg = kg;
                    } else if (weight_unit.equals("kg")) {
                        float kg = w;
                        w_kg = kg;
                    }


                    float bmi = w_kg / h_meter_sq;
                    //DecimalFormat df = new DecimalFormat();


                    float bmi_round = Math.round(bmi * 100) / 100.f;

                    final String answer = "YOUR BMI: " + bmi_round;


                    new android.os.Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            textView_bmi.setText(answer);
                        }
                    }, 800);



                    if (gender.equals("male")) {
                        if (bmi < 20) {

                            textView_weight.setText("Underweight");
//                            int ll_h = linearLayout_gender.getHeight();
//                            int ll_w = linearLayout_gender.getWidth();
//                            linearLayout_gender.setLayoutParams(new LinearLayout.LayoutParams(ll_w-20,ll_h));
                        } else if (bmi >= 20 && bmi < 25) {
                            textView_weight.setText("Normal");
                        } else if (bmi >= 25 && bmi < 30) {
                            textView_weight.setText("Overweight");
                        } else if (bmi >= 30 && bmi < 40) {
                            textView_weight.setText("Obese");
                        } else if (bmi >= 40) {
                            textView_weight.setText("Mrbidly Obese");
                        }
                    } else if (gender.equals("female")) {
                        if (bmi < 19) {
                            textView_weight.setText("Underweight");
                        } else if (bmi >= 19 && bmi < 24) {
                            textView_weight.setText("Normal");
                        } else if (bmi >= 24 && bmi < 30) {
                            textView_weight.setText("Overweight");
                        } else if (bmi >= 30 && bmi < 40) {
                            textView_weight.setText("Obese");
                        } else if (bmi >= 40) {
                            textView_weight.setText("Mrbidly Obese");
                        }

                    }
                } catch (Exception e) {
                    Toast.makeText(BMI_Activity.this, "Error! " + e, Toast.LENGTH_SHORT).show();
                    throw e;
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

                Animation animation_genderChange = new AlphaAnimation(0.0f,0.9f);
                animation_genderChange.setDuration(1000);
                linearLayout_gender.startAnimation(animation_genderChange);

//                imageView_genderWoman.setVisibility(v.INVISIBLE);
//                imageView_genderMan.setVisibility(v.VISIBLE);

                textView_underweight.setText("Underweight        <----20");
                textView_normal.setText("Normal                   20-25");
                textView_overweight.setText("Overweight           25-30");
                textView_obese.setText("Obese                     30-40");
                textView_morbidlyobese.setText("Morbidly Obese   40---->");

                linearLayout_weightMeasures.startAnimation(animation_genderChange);



            }
        });

        button_female.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button_female.setBackgroundResource(R.drawable.female_select);
                button_male.setBackgroundResource(R.drawable.male);
                gender = "female";

                linearLayout_gender.setBackgroundResource(R.drawable.woman);

                Animation animation_genderChange = new AlphaAnimation(0.0f,0.9f);
                animation_genderChange.setDuration(1000);
                linearLayout_gender.startAnimation(animation_genderChange);

//                imageView_genderMan.setVisibility(v.INVISIBLE);
//                imageView_genderWoman.setVisibility(v.VISIBLE);

                textView_underweight.setText("Underweight        <----19");
                textView_normal.setText("Normal                   19-24");
                textView_overweight.setText("Overweight           24-30");
                textView_obese.setText("Obese                     30-40");
                textView_morbidlyobese.setText("Morbidly Obese   40---->");

                linearLayout_weightMeasures.startAnimation(animation_genderChange);
            }
        });





    }

}
