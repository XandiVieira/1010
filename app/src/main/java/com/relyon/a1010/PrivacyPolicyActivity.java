package com.relyon.a1010;

import android.os.Bundle;
import android.webkit.WebView;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.relyon.a1010.util.Constants;

public class PrivacyPolicyActivity extends AppCompatActivity {

    private ImageView back;
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_privacy_policy);

        setLayoutAttributes();

        back.setOnClickListener(v -> onBackPressed());

        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setUseWideViewPort(true);
        webView.loadUrl(Constants.PRIVACY_POLICY);
    }

    private void setLayoutAttributes() {
        back = findViewById(R.id.back);
        webView = findViewById(R.id.webView);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}