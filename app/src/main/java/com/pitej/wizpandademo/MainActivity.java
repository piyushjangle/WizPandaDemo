package com.pitej.wizpandademo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


/*
* @uther Piyush J 20-06-2020
* */
public class MainActivity extends AppCompatActivity {

    /*
     * Constants
     * */
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //declare context
        context = this;
    }


    /*
     * open image demo activity
     * */
    public void OpenImageDemo(View view) {
        Intent intent = new Intent(context, ImageDemoActivity.class);
        try {
            startActivity(intent);
            //finish();
            overridePendingTransition(R.anim.slide_in_up, R.anim.slide_out_up);
        } catch (Exception e) {
            Toast.makeText(context, "No Activity Found", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }


    /*
     * open video demo activity
     * */
    public void openVideoDemo(View view) {
        Intent intent = new Intent(context, VideoDemoActivity.class);
        try {
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in_up, R.anim.slide_out_up);
        } catch (Exception e) {
            Toast.makeText(context, "No Activity Found", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }
}
