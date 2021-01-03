package com.relyon.a1010;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.relyon.a1010.model.Criteria;

import java.util.List;

public class RecyclerViewCriteriaAdapter extends RecyclerView.Adapter<RecyclerViewCriteriaAdapter.ViewHolder> {

    private final List<Criteria> criteriaList;
    private Context context;
    private int totalValuations;

    public RecyclerViewCriteriaAdapter(List<Criteria> criteriaList, Context context, int totalValuations) {
        this.criteriaList = criteriaList;
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
    }

    @Override
    public int getItemCount() {
        return criteriaList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private ProgressBar rate;
        private TextView criteria;
        private TextView rateIndication;

        ViewHolder(View rowView) {
            super(rowView);
            this.rate = rowView.findViewById(R.id.criteria_progress);
            this.criteria = rowView.findViewById(R.id.criteria);
            this.rateIndication = rowView.findViewById(R.id.criteria_rate);
        }
    }
}