package com.example.project_2_goa.DinerList_scrn;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project_2_goa.DishList_scrn.DishList;
import com.example.project_2_goa.Objects.Diner;
import com.example.project_2_goa.KeyboardUtils;
import com.example.project_2_goa.Objects.Dishes;
import com.example.project_2_goa.R;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

public class DinerList extends AppCompatActivity {
    RecyclerView dinersRecyclerView;
    static public ArrayList<Diner> allDiner = new ArrayList<>();
    static public ArrayList<Dishes> dish = new ArrayList<>();
    Button addDiner;
    static Button dishButton;
    Button dinerButton;
    private DinerAdapter adapter;
    View view;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dinerlist_scrn);
        view = findViewById(R.id.addDinerContainer);
        dishButton = findViewById(R.id.dishesScrnButton);
        addDiner = findViewById(R.id.addDiner);

        dinersRecyclerView = findViewById(R.id.dinerRcyclrView);
        adapter = new DinerAdapter(this, allDiner);
        dinersRecyclerView.setAdapter(adapter);
        LinearLayoutManager ln = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);

        dinersRecyclerView.setLayoutManager(ln);


        allDiner.add(new Diner("", 0, new ArrayList<>(),new ArrayList<>()));
        allDiner.get(0).setAssociatedDishes(dish);
        System.out.println(allDiner.get(0).getAssociatedDishes().getClass().getName());
        dishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (int i = 0; i < allDiner.size(); i++) {
                    if (allDiner.get(i).getName().equals("")) {
                        allDiner.remove(i);
                    }
                }
                if(DinerAdapter.ableToMoveOn) {
                    nextScreen();
                }
            }
        });



        addDiner.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onClick(View view) {
                allDiner.add(new Diner("", 0, new ArrayList<>(), new ArrayList<>()));
                adapter.notifyItemInserted(allDiner.size() - 1);
                new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        dinersRecyclerView.smoothScrollToPosition(allDiner.size() - 1);
                    }
                }, 300);


            }
        });


    }

    public void nextScreen() {
        Intent intent = new Intent(this, DishList.class);
        startActivity(intent);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);

    }

    public static ArrayList<Diner> getAllDiner() {
        return allDiner;
    }

    public static Diner getDinerFromAllDiner(int id) {
        for (Diner diner : allDiner) {
            if (diner.getId() == id) {
                return diner;
            }
        }
        return null;
    }
    public static double calculateDinerSubtotal(int id) {
        Diner dinerToCheck = getDinerFromAllDiner(id);
        double subtotal = 0;
        for (Dishes dish : dinerToCheck.getAssociatedDishes()) {
            double sum = dish.getPrice();
            int numOfDiner = dish.getAssociatedDiners().size();
            sum /= numOfDiner;
            subtotal += sum;
        }
        BigDecimal bd = new BigDecimal(subtotal).setScale(2, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    public static double calculateDinerTotal(int id) {
        Diner dinerToCheck = getDinerFromAllDiner(id);
        double total = 0;
        for (Dishes dish : dinerToCheck.getAssociatedDishes()) {
            double sum = dish.getPrice() * (1 + dish.getTaxPercentage() / 100);
            int numOfDiner = dish.getAssociatedDiners().size();
            sum /= numOfDiner;
            total += sum;
        }
        BigDecimal bd = new BigDecimal(total).setScale(2, RoundingMode.UP);
        return bd.doubleValue();
    }

    public static void removeDishFromAllDiners(int dishId) {
        for (Diner diner : getAllDiner()) {
            for (Dishes dish : diner.getAssociatedDishes()) {
                if (dish.getId() == dishId) {
                    ArrayList<Dishes> tmpDishes = diner.getAssociatedDishes();
                    tmpDishes.remove(dish);
                    diner.setAssociatedDishes(tmpDishes);
                }
            }
        }
    }
    public static void greyOutButton(){
        dishButton.setBackgroundResource(R.drawable.button_disable_tips);
    }
    public static void greenOutButton(){
        dishButton.setBackgroundResource(R.drawable.green_button);
    }
}
