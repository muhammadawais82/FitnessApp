package logic2magic.com.health_fitness;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class DWI_Activity extends AppCompatActivity {

    Button button_kg, button_lb, button_cal;
    TextView textView_answer;
    EditText editText_weight;

    String weight_unit = "kg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dwi_layout);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        button_kg = (Button) findViewById(R.id.btn_kg);
        button_lb = (Button) findViewById(R.id.btn_lb);
        button_cal = (Button) findViewById(R.id.btn_cal);

        textView_answer = (TextView) findViewById(R.id.tv_dwiAnswer);

        editText_weight = (EditText) findViewById(R.id.et_weight);


        button_kg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button_kg.setBackgroundColor(Color.parseColor("#383737"));
                button_kg.setTextColor(Color.parseColor("#ffffff"));
                button_lb.setBackgroundColor(Color.parseColor("#bcbcbc"));
                button_lb.setTextColor(Color.parseColor("#000000"));

                weight_unit = "kg";
                editText_weight.setHint("KG");
            }
        });

        button_lb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button_lb.setBackgroundColor(Color.parseColor("#383737"));
                button_lb.setTextColor(Color.parseColor("#ffffff"));
                button_kg.setBackgroundColor(Color.parseColor("#bcbcbc"));
                button_kg.setTextColor(Color.parseColor("#000000"));

                weight_unit = "lb";
                editText_weight.setHint("Lb");

            }
        });

        button_cal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                float getterValue = 0;
                float getterValue_converted = 0;
                float dwiCalculated = 0;
                float dwiCalculatedLiters = 0;

                getterValue = getter_convertToFloat();
                getterValue_converted = unitConverter_Weight(getterValue,weight_unit);
                dwiCalculated = cal_DWI(getterValue_converted);
                dwiCalculatedLiters = convertToLiters(dwiCalculated);
                setter_convertToString(dwiCalculatedLiters);
            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public float getter_convertToFloat ()
    {
        float temp = Float.valueOf(editText_weight.getText().toString());
        return temp;
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

    public float cal_DWI(float w)
    {
        float temp = 0;
        temp = 2.81f * (w * 0.5f);
        return temp;
    }

    public float convertToLiters (float water_oz)
    {
        float water_liters = 0;
        water_liters = water_oz/33.8135f;
        return water_liters;
    }

    public void setter_convertToString (float water)
    {
        float water_round = Math.round(water * 100) / 100.f;
        String temp = String.valueOf(water_round);
        textView_answer.setText(temp + " Liters");
    }



}
