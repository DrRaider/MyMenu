package com.github.drraider.mymenu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import android.content.Context;

import android.widget.Toast;


import com.github.drraider.mymenu.Filter.FilterActivity;
import com.github.drraider.mymenu.Scanner.PortraitCaptureActivity;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView statusMessage;
    private TextView barcodeValue;

    private ArrayList<String> result = new ArrayList<>();

    private static final int TIME_INTERVAL = 2000; // # milliseconds, desired time passed between two back presses.
    private long mBackPressed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        statusMessage = (TextView)findViewById(R.id.status_message);
        barcodeValue = (TextView)findViewById(R.id.barcode_value);

        findViewById(R.id.read_barcode).setOnClickListener(this);
        findViewById(R.id.filter).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.read_barcode:
                // launch barcode activity.
                IntentIntegrator integrator = new IntentIntegrator(this);
                integrator.setRequestCode(2);
                integrator.setCaptureActivity(PortraitCaptureActivity.class);
                integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE);
                integrator.setOrientationLocked(true);
                integrator.initiateScan();
                break;

            case R.id.filter:
                Log.i("Filter", "clicked on filter activity");
                Intent intent = new Intent(MainActivity.this, FilterActivity.class);
                this.startActivityForResult(intent, 1);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
        case 1:
            if(resultCode == Activity.RESULT_OK){
                result = data.getStringArrayListExtra("List");
                Log.d("Filter:", result.toString());
            }
            break;
        case 2:
            IntentResult scanResult = IntentIntegrator.parseActivityResult(resultCode, data);
            if (scanResult.getContents() != null) {
                statusMessage.setText(R.string.barcode_success);
                barcodeValue.setText(scanResult.getContents());
                Log.d("SCAN", "Barcode read: " + scanResult.getContents());
            } else {
                statusMessage.setText(R.string.barcode_failure);
                Log.d("SCAN", "No barcode captured, intent data is null");
            }
            break;
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed()
    {
        if (mBackPressed + TIME_INTERVAL > System.currentTimeMillis())
        {
            super.onBackPressed();
            return;
        }
        else { Toast.makeText(getBaseContext(), "Tap back button in order to exit", Toast.LENGTH_SHORT).show(); }

        mBackPressed = System.currentTimeMillis();
    }

}
