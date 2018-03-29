package com.github.drraider.mymenu;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import android.widget.Toast;

import java.io.InputStream;
import java.net.URL;
import java.net.HttpURLConnection;

import com.github.drraider.mymenu.dialogfragment.EulaDialogFragment;
import com.github.drraider.mymenu.filter.FilterActivity;
import com.github.drraider.mymenu.scanner.PortraitCaptureActivity;
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
        findViewById(R.id.redirectMenu).setOnClickListener(this);

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
            case R.id.redirectMenu:
                Intent menuActivity = new Intent(MainActivity.this, MenuDisplay.class);
                startActivity(menuActivity);
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


                String url = scanResult.getContents();
                String result = null;
                //String result = http_call(url);
                AsyncHTTP task = new AsyncHTTP(this);
                task.execute(url);

                //TesterConnexionHTTP(url);
                //Log.d("Data", "Data received: " + result);
                //barcodeValue.setText(result);


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
        if (id == R.id.action_eula) {
            showDialog();
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


    void showDialog() {
        DialogFragment newFragment = EulaDialogFragment.newInstance(1);
        newFragment.show(getFragmentManager(), "dialog");
    }


    public void TesterConnexionHTTP (String address) {
        URL uneURL=null;
        int ch;
        try {
            uneURL = new URL(address);
            HttpURLConnection connexion = (HttpURLConnection)uneURL.openConnection();
            InputStream flux = connexion.getInputStream();
            barcodeValue.setText("Status de la connexion : " + connexion.getResponseMessage());

            if (connexion.getResponseCode() == HttpURLConnection.HTTP_OK)
                while ((ch=flux.read())!= -1)
                    barcodeValue.setText((char) ch);

            flux.close();
            connexion.disconnect();
        }
        catch(Exception e) {
            Log.d("Error connexion", e.toString());
        }
    }
}
