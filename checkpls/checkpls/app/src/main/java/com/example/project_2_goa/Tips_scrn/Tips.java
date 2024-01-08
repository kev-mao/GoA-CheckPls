package com.example.project_2_goa.Tips_scrn;

import static android.view.View.VISIBLE;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.opengl.Visibility;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project_2_goa.DinerList_scrn.DinerList;
import com.example.project_2_goa.DishList_scrn.DishList;
import com.example.project_2_goa.R;
import com.example.project_2_goa.Totals_scrn.Totals;

public class Tips extends AppCompatActivity {
    static Button calcAndSplit;
    RecyclerView tipsRecyclerView;
    Button dinerButton, dishButton;
    TextView backButton;
    TextView errorMessage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tips_scrn);
        calcAndSplit = findViewById(R.id.calculate_and_split);
        calcAndSplit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               if (TipsAdapter.canMoveOn){
                   nextScreen();
               } else {
                calcAndSplit.setBackgroundResource(R.drawable.grey_button);
                }
            }
        });




        backButton = findViewById(R.id.tips_back_button);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDishScreen();
            }
        });

        tipsRecyclerView = findViewById(R.id.tipRecyclerView);
        tipsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        TipsAdapter adapter = new TipsAdapter(this, DinerList.allDiner);
        tipsRecyclerView.setAdapter(adapter);

        

    }


    public void openDishScreen() {
        Intent intent = new Intent(this, DishList.class);
        startActivity(intent);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    public void nextScreen() {
        Intent intent = new Intent(this, Totals.class);
        startActivity(intent);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    public static void greyOutButton(){
        calcAndSplit.setBackgroundResource(R.drawable.grey_button);
    }

    public static void greenOutButton(){
        calcAndSplit.setBackgroundResource(R.drawable.green_button);
    }


}
