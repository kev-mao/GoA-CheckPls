package com.example.project_2_goa.Totals_scrn;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project_2_goa.DinerList_scrn.DinerList;
import com.example.project_2_goa.DishList_scrn.DishList;
import com.example.project_2_goa.R;
import com.example.project_2_goa.Tips_scrn.Tips;

public class Totals extends AppCompatActivity {
    RecyclerView totalsRecyclerView;
    TotalsAdapter adapter;
    Button startOver;
    TextView previous;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.totals_scrn);

        totalsRecyclerView = findViewById(R.id.totalsDiners);
        adapter = new TotalsAdapter(this, DinerList.allDiner);
        totalsRecyclerView.setAdapter(adapter);
        LinearLayoutManager ln = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        totalsRecyclerView.setLayoutManager(ln);

        startOver= findViewById(R.id.startOver);

        startOver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                restart();
            }
        });

        previous= findViewById(R.id.totalsBack);

        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBack();
            }
        });

    }
    public void restart(){
        Intent intent = new Intent(this,DinerList.class);
        startActivity(intent);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        DinerList.allDiner.clear();
        DishList.dishArrayList.clear();

    }
    public void onBack(){
        Intent intent = new Intent(this, Tips.class);
        startActivity(intent);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }





}
