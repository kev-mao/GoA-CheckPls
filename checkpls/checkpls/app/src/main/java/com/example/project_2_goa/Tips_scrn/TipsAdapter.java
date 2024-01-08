package com.example.project_2_goa.Tips_scrn;

import static android.view.View.VISIBLE;
import static com.example.project_2_goa.R.color.error;

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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project_2_goa.DinerList_scrn.DinerList;
import com.example.project_2_goa.Objects.Diner;
import com.example.project_2_goa.R;
import java.util.ArrayList;

public class TipsAdapter extends RecyclerView.Adapter<TipsAdapter.ViewHolder> {
    Context context;
    ArrayList<Diner> allDiners;
    static boolean canMoveOn = false;



    TipsAdapter(Context context, ArrayList<Diner> allDiners) {
        this.context = context;
        this.allDiners = allDiners;
    }

    @NonNull
    @Override
    public TipsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.rcyclr_tip, parent, false);
        return new TipsAdapter.ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull TipsAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.name.setText(allDiners.get(position).getName());
        holder.custom_tip_input.setText("0");



        if (DinerList.calculateDinerTotal(allDiners.get(position).getId()) == 0) {
            holder.price.setText("$00.00");
        } else {
            holder.price.setText("$" + String.format("%,.2f",DinerList.calculateDinerTotal(allDiners.get(position).getId())));
        }
        holder.tip_rg.setOnCheckedChangeListener((group, checkedId) -> {
            if (holder.option1.isChecked()) {
                holder.option1.setTextColor(Color.WHITE);
                allDiners.get(position).setTip(0.10);
            } else {
                holder.option1.setTextColor(Color.BLACK);
            }
            if (holder.option2.isChecked()) {
                holder.option2.setTextColor(Color.WHITE);
                allDiners.get(position).setTip(0.15);
            } else {
                holder.option2.setTextColor(Color.BLACK);
            }
            if (holder.custom_option.isChecked()) {
                holder.custom_option.setTextColor(Color.WHITE);
                holder.custom_tip_group.setVisibility(View.VISIBLE);
                holder.custom_tip_header.setVisibility(View.VISIBLE);
                holder.custom_tip_input.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        try {
                            allDiners.get(position).setTip(Double.parseDouble(holder.custom_tip_input.getText().toString().trim())/100);
                        } catch (NumberFormatException e) {
                            allDiners.get(position).setTip(0.0);
                        }
                    }
                });

            } else {
                holder.custom_option.setTextColor(Color.BLACK);
                holder.custom_tip_group.setVisibility(View.GONE);
                holder.custom_tip_header.setVisibility(View.GONE);
            }


        });

    }

    @Override
    public int getItemCount() {
        return allDiners.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name, price;
        RadioGroup tip_rg;
        RadioButton option1;
        RadioButton option2;
        RadioButton custom_option;
        EditText custom_tip_input;
        RelativeLayout custom_tip_group;
        ImageView input_textbox;
        TextView percent_symbol;
        TextView error_message;
        TextView custom_tip_header;
        Button calc_and_split;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            price = itemView.findViewById(R.id.cost);
            tip_rg = itemView.findViewById(R.id.tip_options);
            option1 = itemView.findViewById(R.id.tip_button1);
            option2 = itemView.findViewById(R.id.tip_button2);
            custom_option = itemView.findViewById(R.id.custom_tip_button);
            custom_tip_input = itemView.findViewById(R.id.input_percent);
            custom_tip_group = itemView.findViewById(R.id.relative_layout);
            input_textbox = itemView.findViewById(R.id.tip_textbox);
            percent_symbol = itemView.findViewById(R.id.percent_symbol);
            error_message = itemView.findViewById(R.id.error_message);
            custom_tip_header = itemView.findViewById(R.id.custom_tip_header);
            calc_and_split = itemView.findViewById(R.id.calculate_and_split);

            custom_tip_input.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    if (custom_option.isChecked()&&custom_tip_input.getText().toString().trim().isEmpty()){
                        input_textbox.setBackgroundResource(R.drawable.textfield_error);
                        percent_symbol.setTextColor(Color.parseColor("#FF3030"));
                        error_message.setVisibility(View.VISIBLE);
                        custom_tip_input.setTextColor(Color.parseColor("#FF3030"));
                        custom_tip_input.setHintTextColor(Color.parseColor("#FF3030"));
                        canMoveOn = false;
                        Tips.greyOutButton();

                    } else {
                        error_message.setVisibility(View.GONE);
                        input_textbox.setBackgroundResource(R.drawable.rounded_rectangle);
                        percent_symbol.setTextColor(Color.parseColor("#5D5D5D"));
                        custom_tip_input.setTextColor(Color.parseColor("#5D5D5D"));
                        custom_tip_input.setHintTextColor(Color.parseColor("#C4C4C4"));
                        canMoveOn = true;
                        Tips.greenOutButton();


                } }
            });
        }

    }

}