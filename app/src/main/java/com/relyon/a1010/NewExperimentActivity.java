package com.relyon.a1010;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class NewExperimentActivity extends AppCompatActivity implements DefaultActivity {

    private ImageButton back, add1, add2;
    private RadioGroup experimentTypes;
    private RadioButton photo, text;
    private CardView vote, addMedia;
    private ImageView uploadImage;
    private LinearLayout photoLayout, textLayout, layoutCriteria1, layoutCriteria2, layoutCriteria3;
    private EditText criteria1, criteria2, criteria3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_experiment);

        setLayoutAttributes();

        back.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), MainActivity.class)));
        vote.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), MainActivity.class)));

        add1.setOnClickListener(v -> showLayoutCriteria2());
        add2.setOnClickListener(v -> showLayoutCriteria3());

        experimentTypes.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == photo.getId()) {
                photoLayout.setVisibility(View.VISIBLE);
                textLayout.setVisibility(View.GONE);
            } else if (checkedId == text.getId()) {
                photoLayout.setVisibility(View.GONE);
                textLayout.setVisibility(View.VISIBLE);
            }
        });

        uploadImage.setOnClickListener(v -> openGallery());
        addMedia.setOnClickListener(v -> openGallery());
    }

    private void openGallery() {
        if (criteria1.getText() != null && !criteria1.getText().toString().trim().equals("")) {

        }
    }

    private void showLayoutCriteria2() {
        if (layoutCriteria2.getVisibility() == View.GONE) {
            if (criteria1.getText() != null && !criteria1.getText().toString().trim().equals("")) {
                layoutCriteria2.setVisibility(View.VISIBLE);
            } else {
                showInvalidCriteriaToast();
            }
        }
    }

    private void showInvalidCriteriaToast() {
        Toast.makeText(getApplicationContext(), R.string.invalid_criteria, Toast.LENGTH_SHORT).show();
    }

    private void showLayoutCriteria3() {
        if (layoutCriteria3.getVisibility() == View.GONE) {
            if (criteria1.getText() != null && !criteria1.getText().toString().trim().equals("") && criteria2.getText() != null && !criteria2.getText().toString().trim().equals("")) {
                layoutCriteria3.setVisibility(View.VISIBLE);
            } else {
                showInvalidCriteriaToast();
            }
        }
    }

    @Override
    public void setLayoutAttributes() {
        back = findViewById(R.id.back);
        experimentTypes = findViewById(R.id.experiment_types);
        photo = findViewById(R.id.photo);
        text = findViewById(R.id.text);
        vote = findViewById(R.id.vote);
        photoLayout = findViewById(R.id.photo_layout);
        //textLayout = findViewById(R.id.text_layout);
        add1 = findViewById(R.id.add1);
        add2 = findViewById(R.id.add2);
        criteria1 = findViewById(R.id.criteria1);
        criteria2 = findViewById(R.id.criteria2);
        criteria3 = findViewById(R.id.criteria3);
        uploadImage = findViewById(R.id.photo_view);
        addMedia = findViewById(R.id.add_media);
        layoutCriteria1 = findViewById(R.id.layout_criteria1);
        layoutCriteria2 = findViewById(R.id.layout_criteria2);
        layoutCriteria3 = findViewById(R.id.layout_criteria3);
    }
}