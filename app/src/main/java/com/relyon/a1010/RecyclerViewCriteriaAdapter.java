package com.relyon.a1010;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.relyon.a1010.model.Criteria;
import com.relyon.a1010.model.Experiment;
import com.relyon.a1010.util.Constants;
import com.relyon.a1010.util.Util;

import java.util.List;

public class RecyclerViewCriteriaAdapter extends RecyclerView.Adapter<RecyclerViewCriteriaAdapter.ViewHolder> {

    private final List<Criteria> criteriaList;
    private Context context;
    private int totalValuations;
    private Experiment experiment;

    public RecyclerViewCriteriaAdapter(Experiment experiment, Context context, int totalValuations) {
        this.experiment = experiment;
        this.criteriaList = experiment.getCriteria();
        this.context = context;
        this.totalValuations = totalValuations;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rowView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_criteria, parent, false);
        return new ViewHolder(rowView);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {

        final Criteria criteria = criteriaList.get(position);

        holder.criteria.setText(criteria.getCriteria());
        holder.rateIndication.setText(String.valueOf((int) (criteria.getSumOfValuations() / totalValuations)));
        holder.rate.setProgress((int) (criteria.getSumOfValuations() / totalValuations));

        holder.rate.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                for (Criteria crit : criteriaList){
                    if (crit.getCriteria().equals(criteria.getCriteria())){

                    }
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return criteriaList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private SeekBar rate;
        private TextView criteria;
        private TextView rateIndication;

        ViewHolder(View rowView) {
            super(rowView);
            this.rate = rowView.findViewById(R.id.criteria_progress);
            this.criteria = rowView.findViewById(R.id.criteria);
            this.rateIndication = rowView.findViewById(R.id.criteria_rate);
        }
    }

    public void saveValuation(){
        Util.mUserDatabaseRef.child(experiment.getUserID()).child(Constants.DATABASE_REF_EXPERIMENT).child(experiment.getId()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Util.mUserDatabaseRef.child(experiment.getUserID()).child(Constants.DATABASE_REF_EXPERIMENT).child(experiment.getId()).removeEventListener(this);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        })
    }
}