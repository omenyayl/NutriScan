package com.nutriscan.scan.listAdapters;

import android.annotation.SuppressLint;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.nutriscan.R;
import com.nutriscan.shared.domain.Nutrient;

import java.util.List;
import java.util.Locale;

/**
 * Adapter for a {@link RecyclerView} that displays a nutrients of Products
 */
public class NutrientListAdapter extends RecyclerView.Adapter<NutrientListAdapter.ViewHolder> {
    private List<Nutrient> nutrients;

    public NutrientListAdapter(List<Nutrient> nutrients) {
        this.nutrients = nutrients;
    }

    /**
     * Creating the nutrients item ViewHolder
     * @param viewGroup parent
     * @param i nutrients item index
     * @return The new nutrients item ViewHolder
     */
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.nutrient_item, viewGroup, false));
    }

    /**
     * Called when binding Views to the nutrients item
     * @param viewHolder The ViewHolder that we are binding to
     * @param i The nutrients item index
     */
    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {
        Nutrient nutrient = this.nutrients.get(i);
        viewHolder.getTextViewNutrientType().setText(nutrient.getNutrientType().toString());
        viewHolder.getTextViewNutrientAmount().setText(String.format(Locale.US, "%.0f", nutrient.getAmount()));
        viewHolder.getTextViewNutrientUnit().setText(nutrient.getUnit().toString());
    }

    /**
     * @return The number of items in this nutrients
     */
    @Override
    public int getItemCount() {
        return nutrients.size();
    }

    /**
     * The ViewHolder for the Product nutrients item
     */
    class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView textViewNutrientType;
        private final TextView textViewNutrientAmount;
        private final TextView textViewNutrientUnit;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.textViewNutrientType = itemView.findViewById(R.id.textViewNutrientType);
            this.textViewNutrientAmount = itemView.findViewById(R.id.textViewNutrientAmount);
            this.textViewNutrientUnit = itemView.findViewById(R.id.textViewNutrientUnit);
        }

        TextView getTextViewNutrientType() {
            return textViewNutrientType;
        }

        TextView getTextViewNutrientAmount() {
            return textViewNutrientAmount;
        }

        TextView getTextViewNutrientUnit() {
            return textViewNutrientUnit;
        }
    }

}
