package com.relyon.a1010;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class ProfileActivity extends AppCompatActivity implements DefaultActivity {

    private Button newExperiment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        setLayoutAttributes();

        newExperiment.setOnClickListener(v -> startActivity(new Intent(getApplication(), NewExperimentActivity.class)));
    }

    @Override
    public void setLayoutAttributes() {
        newExperiment = findViewById(R.id.new_experiment);
    }
}