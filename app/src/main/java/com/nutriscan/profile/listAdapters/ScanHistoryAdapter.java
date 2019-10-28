package com.nutriscan.profile.listAdapters;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;
import com.nutriscan.R;
import java.util.List;

public class ScanHistoryAdapter extends RecyclerView.Adapter<ScanHistoryAdapter.ViewHolder> {
    private List<String> list;
    private AdapterView.OnItemClickListener onItemClickListener;

    public ScanHistoryAdapter(List<String> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View todoView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.scan_item, viewGroup, false);
        return new ViewHolder(todoView);
    }

    void setOnItemClickListener(AdapterView.OnItemClickListener onClickListener) {
        this.onItemClickListener = onClickListener;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {
        viewHolder.textViewScanItem.setText(list.get(i));
        if (this.onItemClickListener != null) {

        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        final View itemView;
        final TextView textViewScanItem;
        ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemView = itemView;
            textViewScanItem = itemView.findViewById(R.id.textViewScanItem);
        }
    }

}
