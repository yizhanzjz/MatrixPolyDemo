package com.example.yizhan.matrixpolydemo;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {

    private MatirxPolyView matirxPolyView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        matirxPolyView = (MatirxPolyView) findViewById(R.id.matirxPolyView);

        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                switch (checkedId) {
                    case R.id.radioButton0:
                        matirxPolyView.setPointCounter(0);
                        break;
                    case R.id.radioButton1:
                        matirxPolyView.setPointCounter(1);
                        break;
                    case R.id.radioButton2:
                        matirxPolyView.setPointCounter(2);
                        break;
                    case R.id.radioButton3:
                        matirxPolyView.setPointCounter(3);
                        break;
                    case R.id.radioButton4:
                        matirxPolyView.setPointCounter(4);
                        break;
                }
            }
        });
    }
}
