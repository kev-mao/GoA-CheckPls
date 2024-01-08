package com.example.project_2_goa.Totals_scrn;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project_2_goa.DinerList_scrn.DinerList;
import com.example.project_2_goa.DishList_scrn.DishAdapter;
import com.example.project_2_goa.Objects.Diner;
import com.example.project_2_goa.R;

import java.util.ArrayList;

public class TotalsAdapter extends RecyclerView.Adapter<TotalsAdapter.ViewHolder> {
    Context context;
    ArrayList<Diner> allDiners;
    TDinersAdapter Dadapter;
    boolean counter = true;

    TotalsAdapter(Context context, ArrayList<Diner> allDiners) {
        this.context = context;
        this.allDiners = allDiners;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.rcyclr_totalsdiners, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.name.setText(allDiners.get(position).getName());
        double subtotalfin = DinerList.calculateDinerSubtotal(allDiners.get(position).getId());
        double tip = allDiners.get(position).getTip();
        double totalfin = DinerList.calculateDinerTotal(allDiners.get(position).getId());
        holder.price.setText(String.format("$%,.2f", totalfin * (1 + tip)));
        tip *= subtotalfin;


        double finalTip = tip;
        double finalTip1 = tip;
        holder.expDetails.setOnClickListener(new View.OnClickListener() {
            @SuppressLint({"SetTextI18n", "DefaultLocale"})
            @Override
            public void onClick(View view) {

                if (counter) {
                    holder.Divider2.setVisibility(View.VISIBLE);
                    holder.expDetails.setRotation(180);
                    holder.details.setVisibility(View.VISIBLE);
                    Dadapter = new TDinersAdapter(context, DinerList.allDiner.get(position).getAssociatedDishes());
                    holder.allDishes.setAdapter(Dadapter);
                    LinearLayoutManager ln = new LinearLayoutManager(context, RecyclerView.HORIZONTAL, false);
                    holder.allDishes.setLayoutManager(ln);

                    holder.subtotal.setText("$" + String.format("%,.2f",subtotalfin));
                    holder.tax.setText("$" + String.format("%,.2f", totalfin - subtotalfin));

                    holder.tip.setText("$" + String.format("%,.2f", finalTip1));

                    holder.tip.setText("$" + String.format("%,.2f", finalTip));
                    holder.tipPercent.setText("Tips " + allDiners.get(position).getTip() * 100 + "%:");
                    counter = false;
                } else {
                    holder.Divider2.setVisibility(View.GONE);
                    holder.expDetails.setRotation(0);
                    holder.details.setVisibility(View.GONE);
                    counter = true;
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return allDiners.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name, price, subtotal, tax, tip, tipPercent;
        ImageView expDetails;
        RecyclerView allDishes;
        View details;
        View Divider;
        View Divider2;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tipPercent = itemView.findViewById(R.id.tipPercent);
            name = itemView.findViewById(R.id.totalsname);
            price = itemView.findViewById(R.id.totalsprice);
            expDetails = itemView.findViewById(R.id.view_details);
            subtotal = itemView.findViewById(R.id.diner_subtotal);
            details = itemView.findViewById(R.id.details);
            tax = itemView.findViewById(R.id.diner_tax);
            tip = itemView.findViewById(R.id.diner_tip);
            allDishes = itemView.findViewById(R.id.dineralldishes);
            Divider = itemView.findViewById(R.id.div);
            Divider2= itemView.findViewById(R.id.div2);

        }
    }
}
