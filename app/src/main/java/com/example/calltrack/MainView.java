package com.example.calltrack;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainView extends AppCompatActivity {
    private MyView mMyView=null;//a custom view for drawing

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main_view);
        //replace the view with my custom designed view
        mMyView=new MyView(this);
        setContentView(mMyView);
    }

}