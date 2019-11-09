package com.nutriscan.profile.listAdapters;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.nutriscan.R;
import com.nutriscan.listeners.OnModelClick;
import com.nutriscan.shared.domain.Product;
import com.nutriscan.shared.domain.ScanLog.ScanLog;

import java.util.List;

/**
 * Adapter for a {@link RecyclerView} that displays a products of Products
 */
public class ScanHistoryAdapter extends RecyclerView.Adapter<ScanHistoryAdapter.ViewHolder> {
    private List<Product> products;
    private OnModelClick<Product> onModelClickListener;

    public ScanHistoryAdapter(ScanLog scanLog) {
        this.products = scanLog.getItems();
    }

    /**
     * Creating the products item ViewHolder
     * @param viewGroup parent
     * @param i products item index
     * @return The new products item ViewHolder
     */
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View todoView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.scan_item, viewGroup, false);
        return new ViewHolder(todoView);
    }

    /**
     * Implement the OnModelClick argument, and its onClick method will be called when the user
     * clicks the products item
     * @param onModelClickListener The listener whose onClick function will be called onClick of
     * an item
     */
    public void setOnModelClickListener(OnModelClick<Product> onModelClickListener) {
        this.onModelClickListener = onModelClickListener;
    }

    /**
     * Called when binding Views to the products item
     * @param viewHolder The ViewHolder that we are binding to
     * @param i The products item index
     */
    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {
        viewHolder.getTextViewName().setText(products.get(i).getName());
        viewHolder.getTextViewUPC().setText(products.get(i).getUpc());
        if (this.onModelClickListener != null) {
            viewHolder.getProductItem().setOnClickListener(v ->
                    this.onModelClickListener.onClick(this.products.get(i)));
        }
    }

    /**
     * @return The number of items in this products
     */
    @Override
    public int getItemCount() {
        return products.size();
    }

    /**
     * The ViewHolder for the Product products item
     */
    class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView textViewName;
        private final TextView textViewUPC;
        private final View productItem;
        ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.textViewName);
            textViewUPC = itemView.findViewById(R.id.textViewUPC);
            this.productItem = itemView.findViewById(R.id.cardViewProduct);
        }
        TextView getTextViewName() {
            return textViewName;
        }
        TextView getTextViewUPC() {
            return textViewUPC;
        }
        View getProductItem() { return productItem; }
    }

}
