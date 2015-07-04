package com.example.adubashi.enterprisevalue;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.adubashi.enterprisevalue.R;

public class Dilution extends Activity {

    EditText numberValueOptions;
    EditText exerciseOption;
    EditText sharePriceValue;

    TextView dilutionOptionValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dilution);
        Button button = (Button) findViewById(R.id.calculateButton);
        dilutionOptionValue = (TextView) findViewById(R.id.dilutionOptionValue);

        //Options
        numberValueOptions = (EditText)findViewById(R.id.numberValueOptions);
        exerciseOption = (EditText)findViewById(R.id.exerciseOption);
        sharePriceValue = (EditText)findViewById(R.id.sharePriceValue);

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.v("MainActivity", "Calculate options button pressed");
                int dilution = dilutionOptionsEditText(numberValueOptions, exerciseOption, sharePriceValue);
                dilutionOptionValue.setText(Integer.toString(dilution));

            }
        });




    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.dilution, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.Enterprise) {
            Intent i = new Intent(getApplicationContext(), Enterprise.class);
            startActivityForResult(i, 100);
        }
        return super.onOptionsItemSelected(item);
    }
    /*
    Once we have this option information,
    we subtract the exercise price of the options from the current share price (or per share purchase price for an M&A analysis)
    , divide by the share price (or purchase price) and multiply by the number of options outstanding.
      We repeat this calculation for each subset of options reported in the 10K
      (usually companies will report several line items of options categorized by exercise price).
        Aggregating the calculations gives us the amount of diluted shares.
        If the exercise price of an option is greater than the share price
        (or purchase price) then the options are out-of-the-money and have no dilutive effect.
     */

    public static int dilutionOptionsEditText(EditText numberValueOptions, EditText exerciseOption, EditText sharePriceValue)
    {
        int numberOfOptions = 0;
        try {
            numberOfOptions = Integer.parseInt(numberValueOptions.getText().toString());
        }catch(NumberFormatException e){
            numberOfOptions = 0;
        }

        double excisePrice = 0;
        try {
            excisePrice = Double.parseDouble(exerciseOption.getText().toString());
        }catch(NumberFormatException e){
            excisePrice = 0;
        }

        double currentSharePrice = 0;
        try {
            currentSharePrice = Double.parseDouble(sharePriceValue.getText().toString());
        }catch(NumberFormatException e){
            currentSharePrice = 0;
        }
        return dilutionOptions(numberOfOptions, currentSharePrice, excisePrice);
    }

    public static int dilutionOptions(int numberOfOptions, double currentSharePrice, double excisePrice){
        if(excisePrice > currentSharePrice){
            return 0;
        } else {
            double test = excisePrice * numberOfOptions;
            double netOptions = test/(double)currentSharePrice;

            return (int)netOptions;
        }
    }
}
