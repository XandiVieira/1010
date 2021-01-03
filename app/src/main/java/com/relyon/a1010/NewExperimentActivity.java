package com.relyon.a1010;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class NewExperimentActivity extends AppCompatActivity implements DefaultActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_experiment);

        setLayoutAttributes();
    }

    @Override
    public void setLayoutAttributes() {

    }
}