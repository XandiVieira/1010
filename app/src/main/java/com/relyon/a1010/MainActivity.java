package com.relyon.a1010;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.lorentzos.flingswipe.SwipeFlingAdapterView;
import com.relyon.a1010.model.Experiment;
import com.relyon.a1010.model.User;
import com.relyon.a1010.util.Constants;
import com.relyon.a1010.util.Util;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements DefaultActivity {

    private Activity activity;
    private FirebaseUser firebaseUser;
    private User user;
    private DatabaseReference mUserDatabaseRef;
    private DatabaseReference mExperimentsRef;
    private String firebaseToken;
    private List<Experiment> experimentList = new ArrayList<>();
    private RecyclerViewExperimentAdapter experimentAdapter;

    private ImageView profile;
    private Button newExperiment;
    private TextView powerIndication;
    private ProgressBar powerScale;
    private Spinner categories;
    private SwipeFlingAdapterView cards;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startFirebaseInstances();

        setLayoutAttributes();

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        if (firebaseUser == null) {
            goLoginScreen();
        } else {
            mUserDatabaseRef.child(firebaseUser.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    mUserDatabaseRef.removeEventListener(this);
                    user = snapshot.getValue(User.class);
                    if (user == null) {
                        createNewUser();
                    }
                    setUserPower();
                    setUserPhoto();
                    mExperimentsRef.orderByChild(Constants.DATABASE_REF_CREATED_AT).startAt(0).limitToFirst(1).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            mExperimentsRef.removeEventListener(this);
                            for (DataSnapshot snap : snapshot.getChildren()) {
                                experimentList.add(snap.getValue(Experiment.class));
                            }
                            experimentAdapter = new RecyclerViewExperimentAdapter(experimentList, getApplicationContext());
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }

        cards.setFlingListener(new SwipeFlingAdapterView.onFlingListener() {
            @Override
            public void removeFirstObjectInAdapter() {
                experimentList.remove(0);
                experimentAdapter.notifyDataSetChanged();
            }

            @Override
            public void onLeftCardExit(Object o) {

            }

            @Override
            public void onRightCardExit(Object o) {

            }

            @Override
            public void onAdapterAboutToEmpty(int i) {
                mExperimentsRef.orderByChild(Constants.DATABASE_REF_CREATED_AT).startAt(experimentAdapter.getLastItemDate()).limitToFirst(5).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        mExperimentsRef.removeEventListener(this);
                        for (DataSnapshot snap : snapshot.getChildren()) {
                            experimentList.add(snap.getValue(Experiment.class));
                        }
                        experimentAdapter = new RecyclerViewExperimentAdapter(experimentList, getApplicationContext());
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }

            @Override
            public void onScroll(float v) {

            }
        });

        profile.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), ProfileActivity.class)));
        newExperiment.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), NewExperimentActivity.class)));
    }

    private void setUserPhoto() {
        Glide.with(this).load(user.getPhotoUrl()).apply(RequestOptions.circleCropTransform()).into(profile);
    }

    private void createNewUser() {
        user = new User(firebaseUser.getUid(), firebaseUser.getDisplayName(), String.valueOf(firebaseUser.getPhotoUrl()), null, 100);
        Util.mUserDatabaseRef.child(user.getId()).setValue(user);
    }

    private void setUserPower() {
        int power = user.getPower();
        powerScale.setProgress(power);
        if (power >= 95) {
            powerIndication.setText(getResources().getString(R.string.power, getResources().getString(R.string.top)));
            powerIndication.setTextColor(getResources().getColor(R.color.red));
        } else if (power >= 60) {
            powerIndication.setText(getResources().getString(R.string.power, getResources().getString(R.string.Alto)));
            powerIndication.setTextColor(getResources().getColor(R.color.orange));
        } else if (power >= 30) {
            powerIndication.setText(getResources().getString(R.string.power, getResources().getString(R.string.Neutro)));
            powerIndication.setTextColor(getResources().getColor(R.color.green));
        } else if (power > 0) {
            powerIndication.setText(getResources().getString(R.string.power, getResources().getString(R.string.Baixo)));
            powerIndication.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
        } else if (power == 0) {
            powerIndication.setText(getResources().getString(R.string.power, getResources().getString(R.string.Vazio)));
            powerIndication.setTextColor(getResources().getColor(R.color.white));
        }
    }

    private void goLoginScreen() {
        Intent intent = new Intent(this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    private void startFirebaseInstances() {
        FirebaseApp.initializeApp(this);
        FirebaseDatabase mFirebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference mDatabaseRef = mFirebaseDatabase.getReference();
        mUserDatabaseRef = mDatabaseRef.child(Constants.DATABASE_REF_USER);
        mExperimentsRef = mDatabaseRef.child(Constants.DATABASE_REF_EXPERIMENT);
        Util.mUserDatabaseRef = mUserDatabaseRef;
    }

    @Override
    public void setLayoutAttributes() {
        profile = findViewById(R.id.profile);
        newExperiment = findViewById(R.id.new_experiment);
        powerIndication = findViewById(R.id.power_indication);
        categories = findViewById(R.id.categories);
        powerScale = findViewById(R.id.power_scale);
        cards = findViewById(R.id.cards);
    }
}