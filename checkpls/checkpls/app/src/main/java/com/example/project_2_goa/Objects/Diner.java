package com.example.project_2_goa.Objects;

import java.util.ArrayList;

public class Diner {
    private String name;
    private double tip;
    private ArrayList<Dishes> associatedDishes;
    private ArrayList<Double> dinerTotal;
    private int id;
    private static int numberOfDiner = 0;
    private double totalTax;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getTip() {
        return tip;
    }

    public void setTip(double tip) {
        this.tip = tip;
    }

    public ArrayList<Dishes> getAssociatedDishes() {
        return associatedDishes;
    }

    public void setAssociatedDishes(ArrayList<Dishes> associatedDishes) {
        this.associatedDishes = associatedDishes;
    }

    public void addDishToDiner(Dishes dishes) {
        if (associatedDishes == null) {
            associatedDishes = new ArrayList<>();
        }
        associatedDishes.add(dishes);
    }



    public Diner(String name, double tip, ArrayList<Double> dinerTotal, ArrayList<Dishes> asctdDishes) {
        this.name = name;
        this.tip = tip;
        this.dinerTotal = dinerTotal;
        this.associatedDishes = asctdDishes;
        id = ++numberOfDiner;
    }


    public int getId() {
        return id;
    }


}
