package com.mrz.demo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void normal(View view) {
        startActivity(new Intent(this, NormalActivity.class));
    }

    public void load(View view) {
        startActivity(new Intent(this, LoadActivity.class));
    }


}
