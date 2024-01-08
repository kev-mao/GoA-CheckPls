package com.example.project_2_goa.DishList_scrn;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project_2_goa.AddDish_scrn.AddDish;
import com.example.project_2_goa.DinerList_scrn.DinerList;
import com.example.project_2_goa.Objects.Dishes;
import com.example.project_2_goa.R;
import com.example.project_2_goa.Tips_scrn.Tips;

import java.util.ArrayList;

public class DishList extends AppCompatActivity {

    RecyclerView dishListRecyclerView;
    Button addDishesButton, goToTipsButton, addCompleteDishButton, backButton;
    public static ArrayList<Dishes> dishArrayList = new ArrayList<>();
    public static Dishes currentDish;
    public DishAdapter adapter;
    ImageButton addDishBowlButton;
    View view;
    TextView addDishTextView;
    EditText dishNameEditText, priceEditText, taxPercentageEditText;
    Drawable greenButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dishlist_scrn);
        view = findViewById(R.id.addDishContainer);
        addDishesButton = findViewById(R.id.AddDishButton);
        addDishBowlButton = findViewById(R.id.add_dish_image_button);
        goToTipsButton = findViewById(R.id.go_to_tips_button);
        backButton = findViewById(R.id.back_button);
        dishNameEditText = findViewById(R.id.dish_edit_text);
        priceEditText = findViewById(R.id.price_edit_text);
        taxPercentageEditText = findViewById(R.id.tax_edit_text);
        addDishTextView = findViewById(R.id.please_add_dish_text_view);

        //add dish button
        addDishesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addDish();

            }
        });
        //go to tips button
        goToTipsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tips();
            }
        });
        //dish list back button
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                diners();
            }
        });
        //dish bowl image button
        addDishBowlButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addDish();
            }
        });

        goToTipsButton.setClickable(false);

        //sets text plus icon to invis when dish is added
        if (dishArrayList.size() >= 1) {
            addDishTextView.setVisibility(View.INVISIBLE);
            addDishBowlButton.setVisibility(View.INVISIBLE);
            goToTipsButton.setClickable(true);
           goToTipsButton.setBackgroundResource(R.drawable.green_button);
        }


        //initializing dish card view list recycler view
        dishListRecyclerView = findViewById(R.id.DishListRecyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        dishListRecyclerView.setLayoutManager(layoutManager);
        adapter = new DishAdapter(this, DishList.dishArrayList);
        dishListRecyclerView.setAdapter(adapter);
        addCompleteDishButton = findViewById(R.id.add_dish_button);


    }

    public void tips() {
        Intent intent = new Intent(this, Tips.class);
        startActivity(intent);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);

    }

    public void addDish() {
        Intent intent = new Intent(this, AddDish.class);
        startActivity(intent);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);

    }

    public void dinerList() {
        Intent intent = new Intent(this, DinerList.class);
        startActivity(intent);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    public void diners() {
        Intent intent = new Intent(this, DinerList.class);
        startActivity(intent);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }
    public void clearCurrentDish(){



    }
}












