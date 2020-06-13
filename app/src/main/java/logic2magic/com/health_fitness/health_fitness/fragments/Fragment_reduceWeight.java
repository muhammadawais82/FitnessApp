package logic2magic.com.health_fitness.health_fitness.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import logic2magic.com.health_fitness.BFP_Activity;
import logic2magic.com.health_fitness.IBW_Activity;
import logic2magic.com.health_fitness.R;
import logic2magic.com.health_fitness.RW_Activity;

/**
 * Created by qasim on 19-Feb-16.
 */
public class Fragment_reduceWeight extends Fragment {

    String gender = "male" , weight_unit = "kg", height_unit = "cm", activityLevel = null, valueToSend_string = null;
    float valueToSend_float = 0;
    Intent intent;

    public final static String MESSAGEKEY = "logicToMagic.com/reduceWeight";

    Button button_reqiredCalories, button_reduceWeight;
    TextView textView_showReqiredCalories;
    Spinner spinner_activityLevel;
    EditText editText_age, editText_height,editText_weight;
    ImageButton imageButton_male, imageButton_female;
    ToggleButton toggleButton_height, toggleButton_weight;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_reducweight,container,false);

        button_reqiredCalories = (Button) view.findViewById(R.id.btn_requiredCalories);
        button_reduceWeight = (Button) view.findViewById(R.id.btn_reduceWeight);

        textView_showReqiredCalories = (TextView) view.findViewById(R.id.tv_showRequiredCalories);

        spinner_activityLevel = (Spinner) view.findViewById(R.id.sp_activityLevel);

        editText_age = (EditText) view.findViewById(R.id.et_age);
        editText_height = (EditText) view.findViewById(R.id.et_height);
        editText_weight = (EditText) view.findViewById(R.id.et_weight);

        imageButton_female = (ImageButton) view.findViewById(R.id.btn_female);
        imageButton_male = (ImageButton) view.findViewById(R.id.btn_male);

        toggleButton_height = (ToggleButton) view.findViewById(R.id.tb_height);
        toggleButton_weight = (ToggleButton) view.findViewById(R.id.tb_weight);


        imageButton_male.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageButton_male.setBackgroundResource(R.drawable.male_select);
                imageButton_female.setBackgroundResource(R.drawable.female);
                gender = "male";
            }
        });

        imageButton_female.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageButton_male.setBackgroundResource(R.drawable.male);
                imageButton_female.setBackgroundResource(R.drawable.female_select);
                gender = "female";
            }
        });

        toggleButton_height.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    toggleButton_height.setBackgroundResource(R.drawable.switch_ft);
                    height_unit = "inch";
                    editText_height.setHint("HEIGHT(INCH)");
                } else {
                    toggleButton_height.setBackgroundResource(R.drawable.switch_cm);
                    height_unit = "cm";
                    editText_height.setHint("HEIGHT(CM)");
                }
            }
        });

        toggleButton_weight.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    toggleButton_weight.setBackgroundResource(R.drawable.switch_kg);
                    weight_unit = "kg";
                    editText_weight.setHint("WEIGHT(KG)");
                } else {
                    toggleButton_weight.setBackgroundResource(R.drawable.switch_lb);
                    weight_unit = "lb";
                    editText_weight.setHint("WEIGHT(Lb)");
                }
            }
        });


        button_reduceWeight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Activity activity = getActivity();
                Intent intent = new Intent(activity, RW_Activity.class);
                startActivity(intent);
            }
        });

        button_reqiredCalories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String age = editText_age.getText().toString();
                String weight = editText_weight.getText().toString();
                String height = editText_height.getText().toString();

                float a = 0, h = 0, w = 0;

                float w_kg = 0;
                float h_cm = 0;

                activityLevel = spinner_activityLevel.getSelectedItem().toString();

                try {
                    float ag = Integer.parseInt(age);
                    float wei = Integer.parseInt(weight);
                    float hei = Integer.parseInt(height);

                    a = ag;
                    h = hei;
                    w = wei;

                } catch (Exception e) {
                    Toast.makeText(getActivity(), "Please Enter Values!", Toast.LENGTH_LONG).show();
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
                    float ee_toSet = 0;
                    float ee_toSetRound = 0;
                    float ee = (float) ((10 * w_kg) + (6.25 * h_cm) - (5 * a) + 5);

                    float ee_round = Math.round(ee * 100) / 100.f;

                    switch (activityLevel)
                    {
                        case "Sedentary":
                        {
                            ee_toSet = (float) (ee_round * 1.2);
                            ee_toSetRound = Math.round(ee_toSet * 100) / 100.f;
                            Toast.makeText(getActivity(), "Sad", Toast.LENGTH_SHORT).show();
                            break;
                        }
                        case "Little":
                        {
                            ee_toSet = (float) (ee_round * 1.375);
                            ee_toSetRound = Math.round(ee_toSet * 100) / 100.f;
                            Toast.makeText(getActivity(), "Little", Toast.LENGTH_SHORT).show();
                            break;
                        }
                        case "Moderate":
                        {
                            ee_toSet = (float) (ee_round * 1.55);
                            ee_toSetRound = Math.round(ee_toSet * 100) / 100.f;
                            break;
                        }
                        case "Hard":
                        {
                            ee_toSet = (float) (ee_round * 1.725);
                            ee_toSetRound = Math.round(ee_toSet * 100) / 100.f;
                            break;
                        }
                        case "Very Hard":
                        {
                            ee_toSet = (float) (ee_round * 1.9);
                            ee_toSetRound = Math.round(ee_toSet * 100) / 100.f;
                            break;
                        }
                    }

//                    if (activityLevel.equals("Sedentary"))
//                    {
//                        ee_toSet = (float) (ee_round * 1.2);
//                        ee_toSetRound = Math.round(ee_toSet * 100) / 100.f;
//                        Toast.makeText(getActivity(), "Sad", Toast.LENGTH_SHORT).show();
//
//                    }
//                    else if (activityLevel.equals("Little"))
//                    {
//                        ee_toSet = (float) (ee_round * 1.375);
//                        ee_toSetRound = Math.round(ee_toSet * 100) / 100.f;
//                        Toast.makeText(getActivity(), "Little", Toast.LENGTH_SHORT).show();
//                    }
//                    else if (activityLevel.equals("Moderate"))
//                    {
//                        ee_toSet = (float) (ee_round * 1.55);
//                        ee_toSetRound = Math.round(ee_toSet * 100) / 100.f;
//                    }
//                    else if (activityLevel.equals("Hard"))
//                    {
//                        ee_toSet = (float) (ee_round * 1.725);
//                        ee_toSetRound = Math.round(ee_toSet * 100) / 100.f;
//                    }
//                    else if (activityLevel.equals("Very Hard"))
//                    {
//                        ee_toSet = (float) (ee_round * 1.9);
//                        ee_toSetRound = Math.round(ee_toSet * 100) / 100.f;
//                    }
                    Toast.makeText(getActivity(), activityLevel + " " + String.valueOf(ee_round) + " " + String.valueOf(ee_toSet) , Toast.LENGTH_SHORT).show();
                    textView_showReqiredCalories.setText(String.valueOf(ee_toSetRound));
                    valueToSend_string = String.valueOf(ee_toSetRound);
                }

                if (gender.equals("female"))
                {
                    float ee_toSet = 0;
                    float ee_toSetRound = 0;
                    float ee = (float) ((10 * w_kg) + (6.25 * h_cm) - (5 * a) - 161);

                    float ee_round = Math.round(ee * 100) / 100.f;

                    switch (activityLevel)
                    {
                        case "Sedentary":
                        {
                            ee_toSet = (float) (ee_round * 1.2);
                            ee_toSetRound = Math.round(ee_toSet * 100) / 100.f;
                            Toast.makeText(getActivity(), "Sad", Toast.LENGTH_SHORT).show();
                            break;
                        }
                        case "Little":
                        {
                            ee_toSet = (float) (ee_round * 1.375);
                            ee_toSetRound = Math.round(ee_toSet * 100) / 100.f;
                            Toast.makeText(getActivity(), "Little", Toast.LENGTH_SHORT).show();
                            break;
                        }
                        case "Moderate":
                        {
                            ee_toSet = (float) (ee_round * 1.55);
                            ee_toSetRound = Math.round(ee_toSet * 100) / 100.f;
                            break;
                        }
                        case "Hard":
                        {
                            ee_toSet = (float) (ee_round * 1.725);
                            ee_toSetRound = Math.round(ee_toSet * 100) / 100.f;
                            break;
                        }
                        case "Very Hard":
                        {
                            ee_toSet = (float) (ee_round * 1.9);
                            ee_toSetRound = Math.round(ee_toSet * 100) / 100.f;
                            break;
                        }
                    }
                    Toast.makeText(getActivity(), activityLevel + " " + String.valueOf(ee_round) + " " + String.valueOf(ee_toSet) , Toast.LENGTH_SHORT).show();
                    textView_showReqiredCalories.setText(String.valueOf(ee_toSetRound));
                }

            }
        });


        button_reduceWeight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Activity activity = getActivity();
                intent = new Intent(activity, RW_Activity.class);
                intent.putExtra(MESSAGEKEY,valueToSend_string);
                startActivity(intent);
            }
        });


        return view;
    }
}
