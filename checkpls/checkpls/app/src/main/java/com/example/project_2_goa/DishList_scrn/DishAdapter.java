package com.example.project_2_goa.DishList_scrn;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project_2_goa.AddDish_scrn.AddDish;
import com.example.project_2_goa.AddDish_scrn.PaidByAdapter;
import com.example.project_2_goa.DinerList_scrn.DinerList;
import com.example.project_2_goa.Objects.Diner;
import com.example.project_2_goa.Objects.Dishes;
import com.example.project_2_goa.R;

import java.util.ArrayList;

public class DishAdapter extends RecyclerView.Adapter<DishAdapter.ViewHolder> {
    Context context;
    ArrayList<Dishes> dishesArrayList;

    ArrayList<Diner> allDiners;


    ArrayList<Dishes> selectedDiner;
    TextView addDishTextView;


    public DishAdapter(Context context, ArrayList<Dishes> dishesArrayList) {
        this.context = context;
        this.dishesArrayList = dishesArrayList;

    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.rcyclr_dish, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.dishName.setText(dishesArrayList.get(position).getName());
        String dollarString = String.format("$%,.2f", Double.valueOf(dishesArrayList.get(position).getPrice() * (1 + (dishesArrayList.get(position).getTaxPercentage() / 100))));
        holder.price.setText(dollarString);
        // String taxString = String.format(""Double.valueOf(dishesArrayList.get(position).getTaxPercentage()));
        double tax = dishesArrayList.get(position).getTaxPercentage();
        holder.tax.setText("Price w/ " + tax + "% Tax");



        //paid by diner names
        String dinnerNames = "";
        for (Diner diner : dishesArrayList.get(position).getAssociatedDiners()) {
            if (dinnerNames.length() > 0) {
                dinnerNames += ", ";
            }
            dinnerNames += diner.getName();
        }
        holder.paidByDiner.setText(dinnerNames);


        //delete button
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DinerList.removeDishFromAllDiners(dishesArrayList.get(position).getId());
                dishesArrayList.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, dishesArrayList.size());
            }
        });


    }



    @Override
    public int getItemCount() {
        return dishesArrayList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView dishName, tax, price, paidByDiner;
        ImageButton delete;
        EditText dishNameEditText, priceEditText, taxPercentageEditText;
        View view;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            dishName = itemView.findViewById(R.id.dish_name_text_view);
            tax = itemView.findViewById(R.id.price_with_tax_text_view);
            price = itemView.findViewById(R.id.price_text_view);
            paidByDiner = itemView.findViewById(R.id.paid_by_diner_list);
            delete = itemView.findViewById(R.id.delete_image_button);

            view = itemView.findViewById(R.id.addDishContainer);
            dishNameEditText = itemView.findViewById(R.id.dish_edit_text);
            priceEditText = itemView.findViewById(R.id.price_edit_text);
            taxPercentageEditText = itemView.findViewById(R.id.tax_edit_text);
        }
    }

}
