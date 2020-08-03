package com.example.calltrack;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    Button CallBtn, Nextbtn;
    EditText PhoneNo;
    String phonenumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CallBtn = findViewById(R.id.callbtn);
        Nextbtn = findViewById(R.id.nextbtn);
        PhoneNo = findViewById(R.id.phone);

        CallBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (PhoneNo.getText().toString().length() == 0) {
                    Toast.makeText(MainActivity.this, "Please Enter Phone number", Toast.LENGTH_LONG).show();
                } else {
                    CallingApi(PhoneNo.getText().toString().trim());
                }

            }
        });


    }

    private void CallingApi(String number) {
        Log.e("Test1", "pass");
        number = "0" + number;

       /* ExotelCall ext = new ExotelCall();
        ext.customerNumber=number;
        ext.connectCustomerToFlow();
        Log.e("Test2", "pass");*/
        ExotelAgent exotelAgent = new ExotelAgent();
        exotelAgent.agentNumber = number;
        exotelAgent.connectToAgent();
        Log.e("Test2", "pass");

    }
}