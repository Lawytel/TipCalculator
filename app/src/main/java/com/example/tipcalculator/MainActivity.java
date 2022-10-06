package com.example.tipcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.tipcalculator.databinding.ActivityMainBinding;
import java.lang.Math;
import java.text.NumberFormat;


public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.calculateButton.setOnClickListener((v) -> {
            calculateTip();
        });
    }

    private void calculateTip() {
        String stringTextField  = String.valueOf(binding.costOfService.getText());
        double cost = Double.parseDouble(stringTextField);
        int selectedId = binding.tipOptions.getCheckedRadioButtonId();
        double tipPercentage;
        switch (selectedId) {
            case R.id.option_twenty_percent:
                tipPercentage = 0.20;
                break;
            case R.id.option_eighteen_percent:
                tipPercentage = 0.18;
                break;
            default:
                tipPercentage = 0.15;
                break;
        }
        double tip = tipPercentage * cost;
        boolean roundUp = binding.roundUpSwitch.isChecked();
        if (roundUp) {
            tip = Math.ceil(tip);
        }
        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        String formattedTip = formatter.format(tip);
        binding.tipResult.setText(getString(R.string.tip_amount, formattedTip));
    }
}