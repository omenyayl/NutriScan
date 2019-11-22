package com.nutriscan.analysis.listAdapters;

import android.annotation.SuppressLint;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.nutriscan.R;
import com.nutriscan.misc.listeners.OnModelClick;
import com.nutriscan.shared.domain.HealthFactor;
import com.nutriscan.shared.domain.Product;

import java.util.List;
import java.util.Locale;

/**
 * Adapter for a {@link RecyclerView} that displays a healthFactors of Products
 */
public class HealthFactorsAdapter extends RecyclerView.Adapter<HealthFactorsAdapter.ViewHolder> {
    private List<HealthFactor> healthFactors;

    public HealthFactorsAdapter(List<HealthFactor> factors) {
        this.healthFactors = factors;
    }

    /**
     * Creating the healthFactors item ViewHolder
     * @param viewGroup parent
     * @param i healthFactors item index
     * @return The new healthFactors item ViewHolder
     */
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View todoView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.health_factor_item, viewGroup, false);
        return new ViewHolder(todoView);
    }


    /**
     * Called when binding Views to the healthFactors item
     * @param viewHolder The ViewHolder that we are binding to
     * @param i The healthFactors item index
     */
    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {
        HealthFactor healthFactor = this.healthFactors.get(i);
        viewHolder.getTextViewNameValue().setText(healthFactor.getName());
        viewHolder.getTextViewWeightValue().setText(String.format(Locale.US, "%f", healthFactor.getWeight()));
        viewHolder.getTextViewMagnitudeValue().setText(String.format(Locale.US, "%d", healthFactor.getMagnitude()));
    }

    /**
     * @return The number of items in this healthFactors
     */
    @Override
    public int getItemCount() {
        return healthFactors.size();
    }

    /**
     * The ViewHolder for the Product healthFactors item
     */
    class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView textViewNameValue;
        private final TextView textViewWeightValue;
        private final TextView textViewMagnitudeValue;


        ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.textViewNameValue = itemView.findViewById(R.id.textViewNameValue);
            this.textViewWeightValue = itemView.findViewById(R.id.textViewWeightValue);
            this.textViewMagnitudeValue = itemView.findViewById(R.id.textViewMagnitudeValue);
        }

        public TextView getTextViewNameValue() {
            return textViewNameValue;
        }

        public TextView getTextViewWeightValue() {
            return textViewWeightValue;
        }

        public TextView getTextViewMagnitudeValue() {
            return textViewMagnitudeValue;
        }
    }

}
