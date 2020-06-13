package logic2magic.com.health_fitness.health_fitness.fragments;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Fragment;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.text.DecimalFormat;

import logic2magic.com.health_fitness.R;

/**
 * Created by qasim on 19-Feb-16.
 */
public class Fragment_BMI extends Fragment {

    TextView textView_weight, textView_bmi;
    TextView textView_underweight, textView_normal, textView_overweight, textView_obese, textView_morbidlyobese;
    EditText editText_weight, editText_height;
    Button button_cal;
    ImageButton button_male, button_female;
    ImageView imageView_genderMan, imageView_genderWoman;
    ToggleButton toggleButton_height, toggleButton_weight;
    LinearLayout linearLayout_gender;

    String gender = "male" , weight_unit = "kg", height_unit = "cm";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_bmi,container,false);


        textView_bmi = (TextView) view.findViewById(R.id.tv_bmi);
        textView_weight = (TextView) view.findViewById(R.id.tv_weight);

        textView_underweight = (TextView) view.findViewById(R.id.tv_undrweight);
        textView_normal = (TextView) view.findViewById(R.id.tv_normal);
        textView_overweight = (TextView) view.findViewById(R.id.tv_overweight);
        textView_obese = (TextView) view.findViewById(R.id.tv_obese);
        textView_morbidlyobese = (TextView) view.findViewById(R.id.tv_morbidlyobese);

        editText_height = (EditText) view.findViewById(R.id.et_height);
        editText_weight = (EditText) view.findViewById(R.id.et_weight);

        button_cal = (Button) view.findViewById(R.id.btn_cal);
        button_male = (ImageButton) view.findViewById(R.id.btn_male);
        button_female = (ImageButton) view.findViewById(R.id.btn_female);

        toggleButton_height = (ToggleButton) view.findViewById(R.id.tb_height);
        toggleButton_weight = (ToggleButton) view.findViewById(R.id.tb_weight);

//        imageView_genderMan = (ImageView) view.findViewById(R.id.iv_gender_man);
//        imageView_genderWoman = (ImageView) view.findViewById(R.id.iv_gender_woman);
        linearLayout_gender = (LinearLayout) view.findViewById(R.id.ll_gender);

        toggleButton_height.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    toggleButton_height.setBackgroundResource(R.drawable.switch_ft);
                    height_unit = "inch";
                } else {
                    toggleButton_height.setBackgroundResource(R.drawable.switch_cm);
                    height_unit = "cm";
                }
            }
        });

        toggleButton_weight.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    toggleButton_weight.setBackgroundResource(R.drawable.switch_kg);
                    weight_unit = "kg";
                } else {
                    toggleButton_weight.setBackgroundResource(R.drawable.switch_lb);
                    weight_unit = "lb";
                }
            }
        });


        button_cal.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                try
                {
                    String weight = editText_weight.getText().toString();
                    String height = editText_height.getText().toString();
                    float h =0, w=0;

                    try
                    {
                        float hei = Integer.parseInt(height);
                        float wei = Integer.parseInt(weight);
                        h = hei;
                        w = wei;
                    }catch (Exception e)
                    {
                        Activity act = getActivity();
                        Toast.makeText(act, "Please Enter Values!", Toast.LENGTH_LONG).show();
                    }

                    float h_meter_sq = 0;
                    float w_kg = 0;

                    if (height_unit.equals("inch"))
                    {
                        float h_meter = (float) (h/39.370);
                        h_meter_sq = h_meter * h_meter;
                    }
                    else if (height_unit.equals("cm"))
                    {
                        float h_meter = h/100;
                        h_meter_sq = h_meter * h_meter;
                    }

                    if(weight_unit.equals("lb"))
                    {
                        float kg = (float) (w/2.2046);
                        w_kg = kg;
                    }
                    else if (weight_unit.equals("kg"))
                    {
                        float kg = w;
                        w_kg = kg;
                    }


                    float bmi = w_kg/h_meter_sq;
                    //DecimalFormat df = new DecimalFormat();


                    float bmi_round = Math.round(bmi*100)/100.f;

                    textView_bmi.setText("YOUR BMI: " + bmi_round);

                    if(gender.equals("male"))
                    {
                        if(bmi < 20 )
                        {
                            textView_weight.setText("Underweight");
                            linearLayout_gender.setMinimumWidth(40);

                        } else if (bmi >= 20 && bmi < 25) {
                            textView_weight.setText("Normal");
                        } else if (bmi >= 25 && bmi < 30) {
                            textView_weight.setText("Overweight");
                        } else if (bmi >= 30 && bmi < 40) {
                            textView_weight.setText("Obese");
                        } else if (bmi >= 40) {
                            textView_weight.setText("Mrbidly Obese");
                        }
                    }
                    else if(gender.equals("female"))
                    {
                        if(bmi < 19 )
                        {
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
                }catch (Exception e)
                {
                    Activity activity = getActivity();
                    Toast.makeText(activity,"Error" + e, Toast.LENGTH_LONG).show();
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
//                imageView_genderWoman.setVisibility(v.INVISIBLE);
//                imageView_genderMan.setVisibility(v.VISIBLE);

                textView_underweight.setText("Underweight         <----20");
                textView_normal.setText("Normal                  20-25");
                textView_overweight.setText("Overweight           25-30");
                textView_obese.setText("Obese                    30-40");
                textView_morbidlyobese.setText("Morbidly Obese     40---->");

            }
        });

        button_female.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button_female.setBackgroundResource(R.drawable.female_select);
                button_male.setBackgroundResource(R.drawable.male);
                gender = "female";

                linearLayout_gender.setBackgroundResource(R.drawable.woman);
//                imageView_genderMan.setVisibility(v.INVISIBLE);
//                imageView_genderWoman.setVisibility(v.VISIBLE);

                textView_underweight.setText("Underweight         <----19");
                textView_normal.setText("Normal                  19-24");
                textView_overweight.setText("Overweight           24-30");
                textView_obese.setText("Obese                    30-40");
                textView_morbidlyobese.setText("Morbidly Obese     40---->");
            }
        });


        return view;
    }


}

