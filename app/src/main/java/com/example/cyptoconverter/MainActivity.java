package com.example.cyptoconverter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    double old_usdInput;
    double old_btcInput;
    double usdInput;
    double btcInput;

    private boolean isEmpty(EditText editText){
        return editText.getText().toString().trim().length() == 0;
    }


    private double usd_to_btc(double usd){
        double btc = usd * 0.00029;
        return btc;
    }
    private double btc_to_usd(double btc){
        double usd = btc * 3416.4;
        return usd;
    }
    private boolean is_equivalent(double usd, double btc){
        if ((btc_to_usd(btc) == usd) && (usd_to_btc(usd) == btc)){
            return true;
        }
        return false;
    }

    public void convert(View view){

        EditText usdVal = (EditText) findViewById(R.id.usd_val);
        EditText btcVal = (EditText) findViewById(R.id.btc_val);



        if (isEmpty(usdVal) && isEmpty(btcVal)){
            return;
        } else if (isEmpty(btcVal)) {
            usdInput = Double.parseDouble(usdVal.getText().toString());
            btcInput = usd_to_btc(usdInput);
        } else if (isEmpty(usdVal)) {
            btcInput = Double.parseDouble(btcVal.getText().toString());
            usdInput = btc_to_usd(btcInput);
        } else if (!isEmpty(usdVal) && !isEmpty(btcVal)) {
            usdInput = Double.parseDouble(usdVal.getText().toString());
            btcInput = Double.parseDouble(btcVal.getText().toString());

            if (is_equivalent(usdInput, btcInput)) {
                return;
            } else if ((usdInput == old_usdInput) && (btcInput == old_btcInput)) {
                return;
            }
            else if ((usdInput != old_usdInput) && (btcInput == old_btcInput)) {
                usdInput = Double.parseDouble(usdVal.getText().toString());
                btcInput = usd_to_btc(usdInput);
            } else if ((usdInput == old_usdInput) && (btcInput != old_btcInput)) {
                btcInput = Double.parseDouble(btcVal.getText().toString());
                usdInput = btc_to_usd(btcInput);
            } else if (!is_equivalent(usdInput, btcInput)) {
                usdInput = Double.parseDouble(usdVal.getText().toString());
                btcInput = usd_to_btc(usdInput);
            } else {
                return;
            }
        }

        usdVal.setText(String.valueOf(usdInput));
        btcVal.setText(String.valueOf(btcInput));
        old_usdInput = usdInput;
        old_btcInput = btcInput;

    }

    public void clear(View view) {
        EditText usdVal = (EditText) findViewById(R.id.usd_val);
        EditText btcVal = (EditText) findViewById(R.id.btc_val);

        if (isEmpty(usdVal) && isEmpty(btcVal)) {
            return;
        } else if (!isEmpty(usdVal) && isEmpty(btcVal)) {
            usdVal.setText("");
        } else if (isEmpty(usdVal) && !isEmpty(btcVal)) {
            btcVal.setText("");
        } else {
            usdVal.setText("");
            btcVal.setText("");
        }
    }
}

