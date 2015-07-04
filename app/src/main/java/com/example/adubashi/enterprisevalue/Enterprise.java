package com.example.adubashi.enterprisevalue;

import android.app.ActionBar;
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




public class Enterprise extends Activity {

    EditText equityValue;
    EditText cashEntry;
    EditText debtEntry;
    EditText noncontrollingEntry;
    EditText preferredStockEntry;

    TextView enterprise;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enterprise);

        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        Button button = (Button) findViewById(R.id.submitEnterprise);
        enterprise = (TextView) findViewById(R.id.finalEnterpriseValue);


        equityValue = (EditText)findViewById(R.id.equityValueEntry);
        cashEntry = (EditText)findViewById(R.id.cashEntry);
        debtEntry = (EditText)findViewById(R.id.debtEntry);
        noncontrollingEntry = (EditText)findViewById(R.id.noncontrollingEntry);
        preferredStockEntry = (EditText)findViewById(R.id.preferredStockEntry);


        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.v("MainActivity", "Submit Button Pressed");
                int newEnterpriseVal = EnterpriseValue(equityValue, cashEntry, debtEntry,noncontrollingEntry,preferredStockEntry);
                enterprise.setText(Integer.toString(newEnterpriseVal));

            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.enterprise, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.dilution) {
            Intent i = new Intent(getApplicationContext(), Dilution.class);
            startActivityForResult(i, 100);
        }
        return super.onOptionsItemSelected(item);
    }

    public static int EnterpriseValue(EditText equityValue, EditText cashEntry, EditText debtEntry,
                                      EditText noncontrollingEntry, EditText preferredStockEntry){
        int equityVal = 0;
        try {
            equityVal = Integer.parseInt(equityValue.getText().toString());
        }catch(NumberFormatException e){
            equityVal = 0;
        }

        int cash = 0;
        try {
            cash = Integer.parseInt(cashEntry.getText().toString());
        }catch(NumberFormatException e){
            cash = 0;
        }

        int debt  = 0;
        try {
            debt = Integer.parseInt(debtEntry.getText().toString());
        }catch(NumberFormatException e){
            debt = 0;
        }

        int noncontrolling = 0;
        try {
            noncontrolling = Integer.parseInt(noncontrollingEntry.getText().toString());
        }catch(NumberFormatException e){
            noncontrolling = 0;
        }

        int preferredStock = 0;
        try {
            preferredStock = Integer.parseInt(preferredStockEntry.getText().toString());
        }catch(NumberFormatException e){
            preferredStock = 0;
        }



        return equityVal + debt + noncontrolling + preferredStock - cash;
    }


}
