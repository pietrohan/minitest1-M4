package com.codegym.cms.model;


import javax.persistence.*;

@Entity
@Table(name = "expenses")
public class Expense {
    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    private Long id;
    private String name;
    private double price;
    private String description;
    private String listSpending;
    private String picture;

    public Expense() {
    }

    public Expense(Long id, String name, double price, String description, String listSpending, String picture) {

this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.listSpending = listSpending;
        this.picture = picture;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
