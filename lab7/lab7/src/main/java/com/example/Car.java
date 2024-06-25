package com.example;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cars_table")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String licensePlate;

    private double price;

    @Column(name = "manufacturing_year")
    private int year;

    @ManyToOne
    @JoinColumn(name = "human_id", referencedColumnName = "id")
    private Human Human;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "picture_id", referencedColumnName = "id")
    private Picture picture;

    @ManyToMany(mappedBy = "cars")
    private List<Carage> garages;

    public Car() { }
    public Car(String licensePlate, double price, int year) {
        super();
        this.licensePlate = licensePlate;
        this.price = price;
        this.year = year;
        this.garages = new ArrayList<Carage>();

    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getYear() {
        return year;
    }
    public void setYear(int year) {
        this.year = year;
    }

    public int getId() {
        return id;
    }

    public Human getHuman() {return Human;}
    public void setHuman(Human Human) {this.Human = Human;}

    public Picture getPicture() {return this.picture;}
    public void setPicture(Picture picture) {this.picture = picture;}

    public List<Carage> getGarages() {return garages;}
    public void setGarages(List<Carage> garages) {this.garages=garages;}
}