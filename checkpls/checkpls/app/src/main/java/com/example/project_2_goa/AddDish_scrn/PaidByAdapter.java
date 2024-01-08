package com.example.project_2_goa.AddDish_scrn;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project_2_goa.DinerList_scrn.DinerList;
import com.example.project_2_goa.DishList_scrn.DishList;
import com.example.project_2_goa.Objects.Diner;
import com.example.project_2_goa.R;

import java.util.ArrayList;

public class PaidByAdapter extends RecyclerView.Adapter<PaidByAdapter.ViewHolder> {

    View view;
    Context context;
    ArrayList<Diner> allDiner;
    PaidByListener paidByListener;
    ArrayList<Diner> selectedDiner = new ArrayList<>();



    public PaidByAdapter(ArrayList<Diner> allDiner) {
        this.allDiner = allDiner;
    }

    public PaidByAdapter(Context context, ArrayList<Diner> allDiner, PaidByListener paidByListener) {
        this.context = context;
        this.allDiner = allDiner;
        this.paidByListener = paidByListener;

    }

    public View getView() {
        return view;
    }


    @NonNull
    @Override
    public PaidByAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(context).inflate(R.layout.rcyclr_paidby, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PaidByAdapter.ViewHolder holder, int position) {
        if (allDiner != null && allDiner.size() > 0) {
            if (!allDiner.get(position).getName().equals("")) {
                holder.check_box.setText(allDiner.get(position).getName());
                holder.check_box.setId(allDiner.get(position).getId());
            }
            if (holder.check_box.isChecked()) {
                selectedDiner.add(allDiner.get(position));
            } else {
                selectedDiner.remove(allDiner.get(position));
            }
            paidByListener.onQuantityChange(selectedDiner);

        }

    }

    @Override
    public int getItemCount() {
        return allDiner.size();

    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        public CheckBox check_box;
        TextView paidByTextView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            check_box = (CheckBox) itemView.findViewById(R.id.dinerCheckBox);
            paidByTextView = itemView.findViewById(R.id.paid_by);
            check_box.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        int index = buttonView.getId();
                        selectedDiner.add(DinerList.getDinerFromAllDiner(index));
                        AddDish.error_message_paidby.setVisibility(View.GONE);
                    } else {
                        selectedDiner.remove(DinerList.getDinerFromAllDiner(buttonView.getId()));
                    }
                }
            });
        }
    }
    public int paidByNumber(){
     return DishList.dishArrayList.size();
    }

    public ArrayList<Diner> getSelectedDiner() {
        return selectedDiner;
    }

    public void setSelectedDiner(ArrayList<Diner> selectedDiner) {
        this.selectedDiner = selectedDiner;
    }
}
