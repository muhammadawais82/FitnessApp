package logic2magic.com.health_fitness;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class BFP_Activity extends AppCompatActivity {

    String gender = "male";
    float bfp_calculated = 0;

    // Male Strings..........
    String weight_male_unit = "kg";
    String waist_male_unit = "inch";

    // Female Strings..........
    String weight_female_unit = "kg";
    String waist_female_uinit = "inch";
    String wrist_female_unit = "inch";
    String hip_female_unit = "inch";
    String foreArm_female_unit = "inch";

    //Male Floats..........
    float maleWeight = 0;
    float maleWaist = 0;
    float maleWeight_converted = 0;
    float maleWaist_converted = 0;

    //Female Floats..........
    float femaleWeight = 0;
    float femaleWaist = 0;
    float femaleWrist = 0;
    float femaleHip = 0;
    float femaleForeArm = 0;
    float femaleWeight_converted = 0;
    float femaleWaist_converted = 0;
    float femaleWrist_converted = 0;
    float femaleHip_converted = 0;
    float femaleForeArm_converted = 0;

    //.................All Layout and Java objects and Controls Connections.............
    Button button_calculate;
    ImageButton button_male, button_female;
    LinearLayout linearLayout_male, linearLayout_female;
    EditText editText_weight_male, editText_waist_male;
    EditText editText_weight_female, editText_waist_female, editText_wrist_female, editText_hip_female, editText_foreArm_female;
    ToggleButton toggleButton_weight_male, toggleButton_waist_male;
    ToggleButton toggleButton_weight_female, toggleButton_waist_female, toggleButton_wrist_female, toggleButton_hip_female, toggleButton_foreArm_female;
    TextView textView_answer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bfp_layout);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        button_male = (ImageButton) findViewById(R.id.btn_male);
        button_female = (ImageButton) findViewById(R.id.btn_female);
        button_calculate = (Button) findViewById(R.id.btn_calBFP);

        linearLayout_male = (LinearLayout) findViewById(R.id.ll_male);
        linearLayout_female = (LinearLayout) findViewById(R.id.ll_female);

        textView_answer = (TextView) findViewById(R.id.tv_bfp_ans);

        //Male EditTexts...
        editText_weight_male = (EditText) findViewById(R.id.et_weight_male);
        editText_waist_male = (EditText) findViewById(R.id.et_waist_male);

        //Female EditTexts...
        editText_weight_female = (EditText) findViewById(R.id.et_weight_female);
        editText_waist_female = (EditText) findViewById(R.id.et_waist_female);
        editText_wrist_female = (EditText) findViewById(R.id.et_wrist_female);
        editText_hip_female = (EditText) findViewById(R.id.et_hip_female);
        editText_foreArm_female = (EditText) findViewById(R.id.et_forearm_female);

        //Male Toggle Buttons...
        toggleButton_weight_male = (ToggleButton) findViewById(R.id.tb_weight_male);
        toggleButton_waist_male = (ToggleButton) findViewById(R.id.tb_waist_male);

        //Female Toggle Buttons...
        toggleButton_weight_female = (ToggleButton) findViewById(R.id.tb_weight_female);
        toggleButton_waist_female = (ToggleButton) findViewById(R.id.tb_waist_female);
        toggleButton_wrist_female = (ToggleButton) findViewById(R.id.tb_wrist_female);
        toggleButton_hip_female = (ToggleButton) findViewById(R.id.tb_hip_female);
        toggleButton_foreArm_female = (ToggleButton) findViewById(R.id.tb_forearm_female);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Codeing of all male female image buttons......................................................

        button_male.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button_male.setBackgroundResource(R.drawable.male_select);
                button_female.setBackgroundResource(R.drawable.female);
                gender = "male";

                linearLayout_female.setVisibility(View.GONE);

                Animation animation_genderChange = new AlphaAnimation(0.0f, 0.9f);
                animation_genderChange.setDuration(1000);
                linearLayout_male.startAnimation(animation_genderChange);

                linearLayout_male.setVisibility(View.VISIBLE);
            }
        });

        button_female.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button_male.setBackgroundResource(R.drawable.male);
                button_female.setBackgroundResource(R.drawable.female_select);
                gender = "female";

                linearLayout_male.setVisibility(View.GONE);

                Animation animation_genderChange = new AlphaAnimation(0.0f, 0.9f);
                animation_genderChange.setDuration(1000);
                linearLayout_female.startAnimation(animation_genderChange);

                linearLayout_female.setVisibility(View.VISIBLE);
            }
        });

        // Codeing of all the toggle buttons................................................................

        //......................Male Toggle Buttons Codding....................

        toggleButton_weight_male.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    toggleButton_weight_male.setBackgroundResource(R.drawable.switch_kg);
                    weight_male_unit = "kg";
                    editText_weight_male.setHint("Weight(KG)");
                } else {
                    toggleButton_weight_male.setBackgroundResource(R.drawable.switch_lb);
                    weight_male_unit = "lb";
                    editText_weight_male.setHint("Weight(Lb)");
                }
            }
        });

        toggleButton_waist_male.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    toggleButton_waist_male.setBackgroundResource(R.drawable.switch_ft);
                    waist_male_unit = "inch";
                    editText_waist_male.setHint("Waist(INCH)");
                } else {
                    toggleButton_waist_male.setBackgroundResource(R.drawable.switch_cm);
                    waist_male_unit = "cm";
                    editText_waist_male.setHint("Waist(CM)");
                }
            }
        });

        //......................Female Toggle Buttons Codding....................

        toggleButton_weight_female.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    toggleButton_weight_female.setBackgroundResource(R.drawable.switch_kg);
                    weight_female_unit = "kg";
                    editText_weight_female.setHint("Weight(KG)");
                } else {
                    toggleButton_weight_female.setBackgroundResource(R.drawable.switch_lb);
                    weight_female_unit = "lb";
                    editText_weight_female.setHint("Weight(Lb)");
                }
            }
        });

        toggleButton_waist_female.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    toggleButton_waist_female.setBackgroundResource(R.drawable.switch_ft);
                    waist_female_uinit = "inch";
                    editText_waist_female.setHint("Waist(INCH)");
                } else {
                    toggleButton_waist_female.setBackgroundResource(R.drawable.switch_cm);
                    waist_female_uinit = "cm";
                    editText_waist_female.setHint("Waist(CM)");
                }
            }
        });


        toggleButton_wrist_female.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    toggleButton_wrist_female.setBackgroundResource(R.drawable.switch_ft);
                    wrist_female_unit = "inch";
                    editText_wrist_female.setHint("Wrist(INCH)");
                } else {
                    toggleButton_wrist_female.setBackgroundResource(R.drawable.switch_cm);
                    wrist_female_unit = "cm";
                    editText_wrist_female.setHint("Wrist(CM)");
                }
            }
        });


        toggleButton_hip_female.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    toggleButton_hip_female.setBackgroundResource(R.drawable.switch_ft);
                    hip_female_unit = "inch";
                    editText_hip_female.setHint("Hip(INCH)");
                } else {
                    toggleButton_hip_female.setBackgroundResource(R.drawable.switch_cm);
                    hip_female_unit = "cm";
                    editText_hip_female.setHint("Hip(CM)");
                }
            }
        });


        toggleButton_foreArm_female.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    toggleButton_foreArm_female.setBackgroundResource(R.drawable.switch_ft);
                    foreArm_female_unit = "inch";
                    editText_foreArm_female.setHint("Fore Arm(INCH)");
                } else {
                    toggleButton_foreArm_female.setBackgroundResource(R.drawable.switch_cm);
                    foreArm_female_unit = "cm";
                    editText_foreArm_female.setHint("Fore Arm(CM)");
                }
            }
        });

        button_calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(gender == "male")
                {
                    maleWeight = getterWeightMale_convertToFloat();
                    maleWaist = getterWaistMale_convertToFloat();

                    maleWeight_converted = convertWeight_Ponds(maleWeight,weight_male_unit);
                    maleWaist_converted = convertLenght_Inches(maleWaist, waist_male_unit);

                    bfp_calculated = calculateBFP_forMale(maleWeight_converted,maleWaist_converted);
                }
                if(gender == "female")
                {
                    femaleWeight = getterWeightFemale_convertToFloat();
                    femaleWaist = getterWaistFemale_convertToFloat();
                    femaleWrist = getterWristFemale_convertToFloat();
                    femaleHip = getterHipFemale_convertToFloat();
                    femaleForeArm = getterForeArmFemale_convertToFloat();

                    femaleWeight_converted = convertWeight_Ponds(femaleWeight,weight_female_unit);
                    femaleWaist_converted = convertLenght_Inches(femaleWaist, waist_female_uinit);
                    femaleWrist_converted = convertLenght_Inches(femaleWrist, wrist_female_unit);
                    femaleHip_converted = convertLenght_Inches(femaleHip, hip_female_unit);
                    femaleForeArm_converted = convertLenght_Inches(femaleForeArm, foreArm_female_unit);

                    bfp_calculated = calculateBFP_forFemale(femaleWeight_converted,femaleWaist_converted,femaleWrist_converted,femaleHip_converted,femaleForeArm_converted);
                }

                setter_convertToString(bfp_calculated);

            }
        });

    }

    //..........Male Getters............
    public float getterWaistMale_convertToFloat ()
    {
        float temp = 0;
        try
        {
            temp = Float.valueOf(editText_waist_male.getText().toString());
        }
        catch (Exception ex)
        {
            Toast.makeText(BFP_Activity.this, "Enter Valid Value Waist", Toast.LENGTH_SHORT).show();
        }
        return temp;
    }

    public float getterWeightMale_convertToFloat ()
    {
        float temp = 0;
        try
        {
            temp = Float.valueOf(editText_weight_male.getText().toString());
        }
        catch (Exception ex)
        {
            Toast.makeText(BFP_Activity.this, "Enter Valid Value Waist", Toast.LENGTH_SHORT).show();
        }
        return temp;
    }

    //............Female Getters..........
    public float getterWaistFemale_convertToFloat ()
    {
        float temp = Float.valueOf(editText_waist_female.getText().toString());
        return temp;
    }

    public float getterWeightFemale_convertToFloat ()
    {
        float temp = Float.valueOf(editText_weight_female.getText().toString());
        return temp;
    }

    public float getterWristFemale_convertToFloat ()
    {
        float temp = Float.valueOf(editText_wrist_female.getText().toString());
        return temp;
    }

    public float getterHipFemale_convertToFloat ()
    {
        float temp = Float.valueOf(editText_hip_female.getText().toString());
        return temp;
    }

    public float getterForeArmFemale_convertToFloat ()
    {
        float temp = Float.valueOf(editText_foreArm_female.getText().toString());
        return temp;
    }

    //...............Weight Converter...............
    public float convertWeight_Ponds(float w,String unitWeight)
    {
        float weight_ponds = 0;
        if(unitWeight == "kg")
        {
            weight_ponds = w * 2.20462f;
        }
        if(unitWeight == "lb")
        {
            weight_ponds = w;
        }
        return weight_ponds;
    }

    //...............Height Converter..............
    public float convertLenght_Inches(float l, String unitLenght)
    {
        float length_inch = 0;
        if (unitLenght == "inch")
        {
            length_inch = l;
        }
        if (unitLenght == "cm")
        {
            length_inch = l / 2.54f;
        }
        return length_inch;
    }

    public float calculateBFP_forMale(float weight,float waist)
    {
        float factor1 = 0;
        float factor2 = 0;
        float leanBodyMass = 0;
        float bodyFatWeight = 0;
        float bodyFatPercentage = 0;

        factor1 = (weight * 1.082f) + 94.42f;
        factor2 = waist * 4.15f;
        leanBodyMass = factor1 - factor2;
        bodyFatWeight = weight - leanBodyMass;
        bodyFatPercentage = (float) ((bodyFatWeight * 100f) / weight);

        return bodyFatPercentage;
    }

    public float calculateBFP_forFemale(float weight,float waist, float wrist, float hip, float foreArm)
    {
        float factor1 = 0;
        float factor2 = 0;
        float factor3 = 0;
        float factor4 = 0;
        float factor5 = 0;
        float leanBodyMass = 0;
        float bodyFatWeight = 0;
        float bodyFatPercentage = 0;

        factor1 = (weight * 0.732f) + 8.987f;
        factor2 = wrist / 3.140f;
        factor3 = waist * 0.157f;
        factor4 = hip * 0.249f;
        factor5 = foreArm * 0.434f;
        leanBodyMass = (float) (factor1 + factor2 - factor3 - factor4 + factor5);
        bodyFatWeight = weight - leanBodyMass;
        bodyFatPercentage = (float) ((bodyFatWeight * 100f) / weight);

        return bodyFatPercentage;
    }

    public void setter_convertToString (float bfp)
    {
        float bfp_round = Math.round(bfp * 100) / 100.f;
        String temp = String.valueOf(bfp_round);
        textView_answer.setText(temp + " %");
    }

}
