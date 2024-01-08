package com.example.project_2_goa.DinerList_scrn;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project_2_goa.Objects.Diner;
import com.example.project_2_goa.R;

import java.util.ArrayList;

public class DinerAdapter extends RecyclerView.Adapter<DinerAdapter.ViewHolder>{
    Context context;
    ArrayList<Diner> allDiners;
    Boolean onCreate=true;
    static boolean ableToMoveOn= false;
    public DinerAdapter(Context context, ArrayList<Diner> allDiners){
        this.context= context;
        this.allDiners=allDiners;

    }
    @NonNull
    @Override
    public DinerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.rcyclr_diners,parent,false);
        onCreate=true;
        ableToMoveOn=false;
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull DinerAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        if (holder.textWatcher != null) {
            holder.dinerName.removeTextChangedListener(holder.textWatcher);
        }

        holder.dinerName.setText(allDiners.get(position).getName());
        holder.textWatcher = new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void afterTextChanged(Editable s) {

                allDiners.get(position).setName(s.toString());
                if(holder.dinerName.getText().equals("")){
                }
            }
        };
        holder.dinerName.addTextChangedListener(holder.textWatcher);
        holder.deleteDiner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                allDiners.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, allDiners.size());
            }
        });


    }
    @Override
    public int getItemCount() {
        return allDiners.size() ;
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        EditText dinerName;
        Button deleteDiner;
        TextWatcher textWatcher;
        ImageView inputBox;
        TextView errorMsg;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            dinerName= itemView.findViewById(R.id.edDinername);
            deleteDiner= itemView.findViewById(R.id.delete_diner);
            errorMsg= itemView.findViewById(R.id.error_msg);
            inputBox= itemView.findViewById(R.id.input_textbox);
            dinerName.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void afterTextChanged(Editable editable) {
                    if(dinerName.getText().toString().matches("^[0-9]+$")||dinerName.getText().toString().isEmpty()&&!onCreate){
                       inputBox.setBackgroundResource(R.drawable.textfield_error);
                       errorMsg.setVisibility(View.VISIBLE);
                       dinerName.setTextColor(Color.parseColor("#FF3030"));
                       dinerName.setHintTextColor(Color.parseColor("#FF3030"));
                       ableToMoveOn=false;
                       DinerList.greyOutButton();

                    }else if(!(dinerName.getText().toString().isEmpty()&&onCreate)){
                        ableToMoveOn=true;
                        errorMsg.setVisibility(View.GONE);
                        inputBox.setBackgroundResource(R.drawable.dinerlist_input_name);
                        dinerName.setTextColor(Color.parseColor("#5D5D5D"));
                        dinerName.setHintTextColor(Color.parseColor("#C4C4C4"));
                        onCreate=false;
                        DinerList.greenOutButton();
                    }

                }
            });

        }
    }

}
