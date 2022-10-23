package com.codegym.cms.model;


import javax.persistence.*;

@Entity
@Table(name = "expenses")
public class Expense {
    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    private long id;
    private String name;
    private double price;
    private String description;
    private String listSpending;
    private String picture;

    public Expense() {
    }

    public Expense( String name, double price, String description, String listSpending, String picture) {


        this.name = name;
        this.price = price;
        this.description = description;
        this.listSpending = listSpending;
        this.picture = picture;
    }

//    @Override
//    public String toString() {
//        return "Expense{" +
//                "id=" + id +
//                ", name='" + name + '\'' +
//                ", price=" + price +
//                ", description='" + description + '\'' +
//                ", listSpending='" + listSpending + '\'' +
//                ", picture='" + picture + '\'' +
//                '}';
//    }
    @Override
    public String toString() {
        return String.format("Expense[id=%d, name='%s', price='%s',description='%s,listSpending='%s,picture='%s]", id, name, price,description,listSpending,picture);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getListSpending() {
        return listSpending;
    }

    public void setListSpending(String listSpending) {
        this.listSpending = listSpending;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

}
