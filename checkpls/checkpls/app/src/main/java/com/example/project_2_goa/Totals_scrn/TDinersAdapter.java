package com.example.project_2_goa.Totals_scrn;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project_2_goa.Objects.Dishes;
import com.example.project_2_goa.R;

import java.util.ArrayList;

public class TDinersAdapter extends RecyclerView.Adapter<TDinersAdapter.ViewHolder> {
    Context context;
    ArrayList<Dishes> asctdDishes;

    TDinersAdapter(Context context, ArrayList<Dishes> asctdDishes) {
        this.context = context;
        this.asctdDishes = asctdDishes;

    }

    @NonNull
    @Override
    public TDinersAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.rcyclr_dishes, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull TDinersAdapter.ViewHolder holder, int position) {
        holder.dishName.setText(asctdDishes.get(position).getName());
        holder.dishTotal.setText("$"+String.format("%,.2f", asctdDishes.get(position).getPrice()));

    }

    @Override
    public int getItemCount() {
        return asctdDishes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView dishName, dishTotal;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            dishName = itemView.findViewById(R.id.dish_name);
            dishTotal = itemView.findViewById(R.id.dish_total);

        }
    }
}
