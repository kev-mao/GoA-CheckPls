package com.example.project_2_goa.AddDish_scrn;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project_2_goa.DinerList_scrn.DinerList;
import com.example.project_2_goa.DishList_scrn.DishList;
import com.example.project_2_goa.Objects.Diner;
import com.example.project_2_goa.Objects.Dishes;
import com.example.project_2_goa.R;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;


public class AddDish extends AppCompatActivity implements PaidByListener {

    RecyclerView recycler_view;
    PaidByAdapter adapter;
    Button addDish;
    EditText dish, price, tax;
    TextView error_message_dish, error_message_price, error_message_tax, cancel, cash_symbol, percent_symbol, paidBy;
    public static TextView error_message_paidby;
    ImageView dish_edit_text_box, price_edit_text_box, tax_edit_text_box;


    //ArrayList<Diner> associatedDiners;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adddish_scrn);
        dish = findViewById(R.id.dish_edit_text);
        price = findViewById(R.id.price_edit_text);
        tax = findViewById(R.id.tax_edit_text);
        error_message_dish = findViewById(R.id.error_message_dish);
        error_message_price = findViewById(R.id.error_message_price);
        error_message_tax = findViewById(R.id.error_message_tax);
        error_message_paidby = findViewById(R.id.error_message_paidby);
        paidBy = findViewById(R.id.paid_by);
        cancel = findViewById(R.id.matches_back_button);
        cash_symbol = findViewById(R.id.cash_symbol);
        percent_symbol = findViewById(R.id.percent_symbol);
        dish_edit_text_box = findViewById(R.id.dish_edit_text_box);
        price_edit_text_box = findViewById(R.id.price_edit_text_box);
        tax_edit_text_box = findViewById(R.id.tax_edit_text_box);







        addDish = findViewById(R.id.add_dish_button);

        addDish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // validate input
                boolean isValid = true;
                if ((dish.getText() == null) || (StringUtils.isEmpty(dish.getText().toString())) || dish.getText().toString().matches("^[0-9]+$")) {
                    isValid = false;
                    error_message_dish.setVisibility(View.VISIBLE);
                    dish_edit_text_box.setBackgroundResource(R.drawable.textfield_error);
                    dish.setHintTextColor(Color.parseColor("#FF3030"));
                } else {
                    error_message_dish.setVisibility(View.GONE);
                    dish_edit_text_box.setBackgroundResource(R.drawable.rounded_rectangle);
                    dish.setHintTextColor(Color.parseColor("#5D5D5D"));
                }
                if ((price.getText() == null) || (StringUtils.isEmpty(price.getText().toString()))) {
                    isValid = false;
                    error_message_price.setVisibility(View.VISIBLE);
                    price_edit_text_box.setBackgroundResource(R.drawable.textfield_error);
                    cash_symbol.setTextColor(Color.parseColor("#FF3030"));
                    price.setHintTextColor(Color.parseColor("#FF3030"));
                } else {
                    error_message_price.setVisibility(View.GONE);
                    price_edit_text_box.setBackgroundResource(R.drawable.rounded_rectangle);
                    cash_symbol.setTextColor(Color.parseColor("#5D5D5D"));
                    price.setHintTextColor(Color.parseColor("#5D5D5D"));
                }
                if ((tax.getText() == null) || (StringUtils.isEmpty(tax.getText().toString()))) {
                    isValid = false;
                    error_message_tax.setVisibility(View.VISIBLE);
                    tax_edit_text_box.setBackgroundResource(R.drawable.textfield_error);
                    percent_symbol.setTextColor(Color.parseColor("#FF3030"));
                    tax.setHintTextColor(Color.parseColor("#FF3030"));
                } else {
                    error_message_tax.setVisibility(View.GONE);
                    tax_edit_text_box.setBackgroundResource(R.drawable.rounded_rectangle);
                    percent_symbol.setTextColor(Color.parseColor("#5D5D5D"));
                    tax.setHintTextColor(Color.parseColor("#5D5D5D"));
                }
                if ((adapter.selectedDiner == null) || (adapter.selectedDiner.size() == 0)) {
                    isValid = false;
                    error_message_paidby.setVisibility(View.VISIBLE);
                } else {
                    error_message_paidby.setVisibility(View.GONE);
                }

                if (!isValid) {
                    return;
                }


                error_message_paidby.setVisibility(View.GONE);

                String dishname = dish.getText().toString();
                double dishprice = Double.parseDouble(price.getText().toString());
                double taxpercent = Double.parseDouble(tax.getText().toString());

                // add new dishes
                Dishes dishToShare = new Dishes(dishname, dishprice, taxpercent, adapter.selectedDiner);
                DishList.currentDish = dishToShare;
                DishList.dishArrayList.add(dishToShare);

                // add assoicate dishes to each selected diner
                for (Diner diner : adapter.selectedDiner) {
                    diner.addDishToDiner(dishToShare);
                }

                addDish();



            }
        });


        recycler_view = findViewById(R.id.AddDishRecyclerView);

        setRecyclerView();

        dish.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (StringUtils.isAlphanumeric(s.toString())) {
                    error_message_dish.setVisibility(View.GONE);
                    dish_edit_text_box.setBackgroundResource(R.drawable.rounded_rectangle);
                    dish.setHintTextColor(Color.parseColor("#5D5D5D"));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        price.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (StringUtils.isNumeric(s.toString())) {
                    error_message_price.setVisibility(View.GONE);
                    price_edit_text_box.setBackgroundResource(R.drawable.rounded_rectangle);
                    cash_symbol.setTextColor(Color.parseColor("#5D5D5D"));
                    price.setHintTextColor(Color.parseColor("#5D5D5D"));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        tax.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (StringUtils.isNumeric(s.toString())) {
                    error_message_tax.setVisibility(View.GONE);
                    tax_edit_text_box.setBackgroundResource(R.drawable.rounded_rectangle);
                    percent_symbol.setTextColor(Color.parseColor("#5D5D5D"));
                    tax.setHintTextColor(Color.parseColor("#5D5D5D"));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDishScreen();
            }
        });


    }




    public void openDishScreen() {
        Intent intent = new Intent(this, DishList.class);
        startActivity(intent);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }


    public void addDish() {
        Intent intent = new Intent(this, DishList.class);
        startActivity(intent);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);

    }

    private void setRecyclerView() {
        recycler_view.setHasFixedSize(true);
        recycler_view.setLayoutManager(new LinearLayoutManager(this));
        adapter = new PaidByAdapter(this, DinerList.allDiner, this);
        recycler_view.setAdapter(adapter);
    }

    @Override
    public void onQuantityChange(ArrayList<Diner> diners) {
    }


}
