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

public class EBV_Activity extends AppCompatActivity {

    TextView textView_showAnswer;
    EditText editText_weight, editText_height;
    ToggleButton toggleButton_height, toggleButton_weight;
    ImageButton button_male, button_female;
    LinearLayout linearLayout_gender;
    Button button_cal;

    String gender = "male" , weight_unit = "kg", height_unit = "inch";

    float height = 0;
    float weight = 0;
    float heightConverted = 0;
    float weightConverted = 0;
    float ebvCalculated = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ebv_layout);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        editText_height = (EditText) findViewById(R.id.et_height);
        editText_weight = (EditText) findViewById(R.id.et_weight);

        toggleButton_height = (ToggleButton) findViewById(R.id.tb_height);
        toggleButton_weight = (ToggleButton) findViewById(R.id.tb_weight);

        button_male = (ImageButton) findViewById(R.id.btn_male);
        button_female = (ImageButton) findViewById(R.id.btn_female);

        linearLayout_gender = (LinearLayout) findViewById(R.id.ll_genderImg);

        textView_showAnswer = (TextView) findViewById(R.id.tv_ebvAnswer);

        button_cal = (Button) findViewById(R.id.btn_cal);

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
            }
        });

        button_cal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                height = getterHeight_convertToFloat();
                weight = getterWeight_convertToFloat();

                heightConverted = unitConverter_Height(height,height_unit);
                weightConverted = unitConverter_Weight(weight,weight_unit);

                ebvCalculated = calculate(heightConverted,weightConverted);

                setter_convertToString(ebvCalculated);
            }
        });


    }


    public float getterHeight_convertToFloat ()
    {
        float temp = Float.valueOf(editText_height.getText().toString());
        return temp;
    }

    public float getterWeight_convertToFloat ()
    {
        float temp = Float.valueOf(editText_weight.getText().toString());
        return temp;
    }

    public float unitConverter_Height (float h, String unit)
    {
        float meter_height = 0;

        if( unit == "cm")
        {
            meter_height = h/100f;
        }
        if(unit == "inch")
        {
            meter_height = h/39.3701f;
        }


        //Toast.makeText(EBV_Activity.this, String.valueOf(meter_height) , Toast.LENGTH_SHORT).show();
        return meter_height;
    }

    public float unitConverter_Weight (float w, String unit)
    {
        float kg_weight = 0;

        if( unit == "lb")
        {
            kg_weight = w/2.20462f;
        }
        if(unit == "kg")
        {
            kg_weight = w;
        }

        kg_weight = Math.round(kg_weight * 100) / 100.f;

        return kg_weight;
    }

    public float calculate (float h, float w)
    {

        float temp = 0;

        if(gender == "male")
        {
            //Toast.makeText(EBV_Activity.this, String.valueOf(h) + " and H3 = " + String.valueOf((0.3669f * (h*h*h)) + (0.03219f * w) + "Weight = " + String.valueOf(w))  , Toast.LENGTH_SHORT).show();

            temp =  (0.3669f * (h*h*h)) + (0.03219f * w) + (0.6041f);
        }

        if (gender == "female")
        {
            temp = (float) ( (0.3561f * (h*h*h)) + (0.03308f * w) + (0.1833f) );
        }
        return  temp;
    }

    public void setter_convertToString (float h)
    {
        float ebv_round = Math.round(h * 100) / 100.f;
        String temp = String.valueOf(ebv_round);
        textView_showAnswer.setText(temp);
    }



}