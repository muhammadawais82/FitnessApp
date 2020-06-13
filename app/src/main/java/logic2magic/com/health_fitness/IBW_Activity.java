package logic2magic.com.health_fitness;

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
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class IBW_Activity extends AppCompatActivity {

    String gender = "male", height_unit = "inch";
    String height_str = null;
    float height = 0;
    float height_converted = 0;
    float ibw_calculated = 0;
    float ibw_converted = 0;
    Button button_calculate;
    float weight = 50;


    ImageButton button_male, button_female;
    LinearLayout linearLayout_gender;
    ToggleButton toggleButton_height;
    EditText editText_height;
    TextView textView_showibw, textView_ibwRange;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ibw_layout);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        button_male = (ImageButton) findViewById(R.id.btn_male);
        button_female = (ImageButton) findViewById(R.id.btn_female);
        toggleButton_height = (ToggleButton) findViewById(R.id.tb_height);
        editText_height = (EditText) findViewById(R.id.et_height);
        linearLayout_gender = (LinearLayout) findViewById(R.id.ll_gender);
        button_calculate = (Button) findViewById(R.id.btn_cal_ibw);
        textView_showibw = (TextView) findViewById(R.id.tv_showibw);
        textView_ibwRange = (TextView) findViewById(R.id.tv_show_ibwRange);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        toggleButton_height.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    toggleButton_height.setBackgroundResource(R.drawable.switch_ft);
                    height_unit = "inch";
                    editText_height.setHint("Height(Inch)");
                } else {
                    toggleButton_height.setBackgroundResource(R.drawable.switch_cm);
                    height_unit = "cm";
                    editText_height.setHint("Height(cm)");
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
            }
        });

        button_female.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button_male.setBackgroundResource(R.drawable.male);
                button_female.setBackgroundResource(R.drawable.female_select);
                gender = "female";

                linearLayout_gender.setBackgroundResource(R.drawable.woman);

                Animation animation_genderChange = new AlphaAnimation(0.0f,0.9f);
                animation_genderChange.setDuration(1000);
                linearLayout_gender.startAnimation(animation_genderChange);
            }
        });

        button_calculate.setOnClickListener(new View.OnClickListener()
        {





            @Override
            public void onClick(View v)
            {

                height = getter_convertToFloat();       // Calling Function to get value for editText and convert it into float for operations...

                height_converted = unitConverter(height, height_unit);

                ibw_calculated = calculate(height_converted);

                ibw_converted = convertToKg(ibw_calculated);

                rangeCalculate(ibw_converted);

                setter_convertToString(ibw_converted);         // Calling Function to get value of calculated ideal weight as argument and convert it into string and sets display on textView...

                final Animation an = AnimationUtils.loadAnimation(getBaseContext(), R.anim.animation_rotate_ans);

                textView_showibw.startAnimation(an);

            }





        });

    }

    public float getter_convertToFloat ()
    {
        float temp = Float.valueOf(editText_height.getText().toString());
        return temp;
    }


    public float unitConverter (float h, String unit)
    {
        float inch_height = 0;

        if( unit == "cm")
        {
            inch_height = h/2.54f;
        }
        if(unit == "inch")
        {
            inch_height = h;
        }

        return inch_height;
    }


    public float calculate (float h)
    {

        int temp = 0;
        float ibw_inside = 0;

        if(gender == "male")
        {
            if( h <= 60)
            {
                for (int i=60; i > h; i--)
                {
                    temp ++;
                }
                temp = temp * 6;
                ibw_inside = 106 - temp;

            }
            else if( h > 60)
            {
                for (int i=60; i < h; i++)
                {
                    temp ++;
                }
                temp = temp * 6;
                ibw_inside = temp + 106;
            }
        }

        if (gender == "female")
        {
            if( h <= 60)
            {
                for (int i=60; i > h; i--)
                {
                    temp ++;
                }
                temp = temp * 5;
                ibw_inside = 105 - temp;
            }
            else if( h > 60)
            {
                for (int i=60; i < h; i++)
                {
                    temp ++;
                }
                temp = temp * 5;
                ibw_inside = temp + 105;
            }
        }
        return  ibw_inside;
    }

    public void rangeCalculate(float ibwans)
    {
        float upperRange = Math.round(((ibwans + (ibwans * 0.1f)) * 10) / 10.f);
        float lowerRange = Math.round(((ibwans - (ibwans * 0.1f)) * 10) / 10.f);

        String upperRange_str = String.valueOf(upperRange);
        String lowerRange_Str = String.valueOf(lowerRange);

        textView_ibwRange.setText(lowerRange_Str + " - - - - - - " + upperRange_str);
    }

    public float convertToKg(float ibwLb)
    {
        float ibwKg = 0;
        ibwKg = ibwLb * 0.453592f;
        return ibwKg;
    }


    public void setter_convertToString (float h)
    {
        float ibw_round = Math.round(h * 100) / 100.f;
        String temp = String.valueOf(ibw_round);
        textView_showibw.setText(temp + " kg");
    }
}
