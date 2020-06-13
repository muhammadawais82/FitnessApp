package logic2magic.com.health_fitness;

import android.os.Bundle;
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

public class EWV_Activity extends AppCompatActivity {

    EditText editText_weight, editText_height, editText_age;
    Button button_cal;
    LinearLayout linearLayout_gender;
    ImageButton button_male, button_female;
    ToggleButton toggleButton_height, toggleButton_weight;
    TextView textView_ebwAnswer;

    String gender = "male";
    String height_unit = "cm";
    String weight_unit = "kg";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
        setContentView(R.layout.ewv_layout);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        editText_height = (EditText) findViewById(R.id.et_height);
        editText_weight = (EditText) findViewById(R.id.et_weight);
        editText_age = (EditText) findViewById(R.id.et_age);

        button_male = (ImageButton) findViewById(R.id.btn_male);
        button_female = (ImageButton) findViewById(R.id.btn_female);

        button_cal = (Button) findViewById(R.id.btn_cal);

        textView_ebwAnswer = (TextView) findViewById(R.id.tv_ebwAnswer);
        linearLayout_gender = (LinearLayout) findViewById(R.id.ll_gender);

        toggleButton_height = (ToggleButton) findViewById(R.id.tb_height);
        toggleButton_weight = (ToggleButton) findViewById(R.id.tb_weight);

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

                float age = 0;
                float height = 0;
                float weight = 0;
                float heightConverted = 0;
                float weightConverted = 0;
                float ebv_calculated = 0;

                age = getterAge_convertToFloat();
                height = getterHeight_convertToFloat();
                weight = getterWeight_convertToFloat();

                heightConverted = unitConverter_Height(height,height_unit);
                weightConverted = unitConverter_Weight(weight,weight_unit);

                ebv_calculated = calculate(age,heightConverted,weightConverted);

                setter_convertToString(ebv_calculated);

            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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
    public float getterAge_convertToFloat ()
    {
        float temp = Float.valueOf(editText_age.getText().toString());
        return temp;
    }

    public float unitConverter_Height (float h, String unit)
    {
        float cm_height = 0;

        if( unit == "cm")
        {
            cm_height = h;
        }
        if(unit == "inch")
        {
            cm_height = h * 2.54f;
        }

        Toast.makeText(EWV_Activity.this, "Height = " + String.valueOf(cm_height) , Toast.LENGTH_SHORT).show();

        return cm_height;
    }

    public float unitConverter_Weight (float w, String unit)
    {
        float kg_weight = 0;

        if( unit == "lb")
        {
            kg_weight = w / 2.20462f;
        }
        if(unit == "kg")
        {
            kg_weight = w;
        }

        //lb_weight = Math.round(lb_weight * 100) / 100.f;
        Toast.makeText(EWV_Activity.this, "Weight = " + String.valueOf(kg_weight) , Toast.LENGTH_SHORT).show();

        return kg_weight;
    }

    public float calculate(float a, float h, float w)
    {
        float ebv = 0;

        if (gender == "male")
        {
            ebv = (float) (2.447f - (0.09156 * a) + (0.1074f * h) + (0.3362f * w));
        }

        else if (gender == "female")
        {
            ebv = (float) ( - (2.097f) + (0.1069 * h) + (0.2466f * w)) ;
        }
        return ebv;
    }

    public void setter_convertToString (float ebv)
    {
        float ebv_round = Math.round(ebv * 100) / 100.f;
        String temp = String.valueOf(ebv_round);
        textView_ebwAnswer.setText(temp);
    }



}
