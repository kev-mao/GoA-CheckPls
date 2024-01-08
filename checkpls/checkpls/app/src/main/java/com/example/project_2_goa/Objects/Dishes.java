package com.example.project_2_goa.Objects;

import java.util.ArrayList;

public class Dishes {
    private int id;
    private String name;

    public void setPrice(double price) {
        this.price = price;
    }

    public void setTaxPercentage(double taxPercentage) {
        this.taxPercentage = taxPercentage;
    }

    private double price;
    private double taxPercentage;

    public void setAssociatedDiners(ArrayList<Diner> associatedDiners) {
        this.associatedDiners = associatedDiners;
    }

    private ArrayList<Diner> associatedDiners;
    private static int numberOfDishes = 0;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }


    public ArrayList<Diner> getAssociatedDiners() {
        return associatedDiners;
    }


    public double getTaxPercentage() {
        return taxPercentage;
    }


    public Dishes(String name, double price, double taxPercentage, ArrayList<Diner> dinerList) {
        this.name = name;
        this.price = price;
        this.taxPercentage = taxPercentage;
        this.associatedDiners = dinerList;
        id = ++numberOfDishes;
    }

    public int getId() {
        return id;
    }




}
