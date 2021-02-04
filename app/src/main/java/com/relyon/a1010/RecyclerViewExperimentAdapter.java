package com.relyon.a1010;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.relyon.a1010.model.Experiment;

import java.util.List;

public class RecyclerViewExperimentAdapter extends RecyclerView.Adapter<RecyclerViewExperimentAdapter.ViewHolder> {

    private final List<Experiment> experiments;
    private Context context;

    public RecyclerViewExperimentAdapter(List<Experiment> experiments, Context context) {
        this.experiments = experiments;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rowView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_experiment, parent, false);
        return new ViewHolder(rowView);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {

        final Experiment experiment = experiments.get(position);

        holder.numValuations.setText(context.getString(experiment.getNumberOfValuations(), R.string.valuations));
        RecyclerViewCriteriaAdapter adapter = new RecyclerViewCriteriaAdapter(experiment, context, experiment.getNumberOfValuations());
        holder.recyclerView.setAdapter(adapter);
    }

    @Override
    public int getItemCount() {
        return experiments.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView photo;
        private TextView numValuations;
        private RecyclerView recyclerView;

        ViewHolder(View rowView) {
            super(rowView);
            this.photo = rowView.findViewById(R.id.photo);
            this.numValuations = rowView.findViewById(R.id.num_valuations);
            this.recyclerView = rowView.findViewById(R.id.bars);
        }
    }

    public Long getLastItemDate() {
        if (experiments != null && experiments.get(experiments.size() - 1) != null) {
            return experiments.get(experiments.size() - 1).getCreatedAt();
        } else {
            return 0L;
        }
    }
}