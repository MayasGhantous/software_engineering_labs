package com.example;

import javax.persistence.*;


@Entity
@Table(name = "pictures_table")
public class Picture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String picture_name;

    @OneToOne(mappedBy = "picture")
    private Car Car;

    public Picture(){}
    public Picture(String picture_name) {
        super();
        this.picture_name = picture_name;
    }
    public int getPicture_id() {return this.id;}

    public void setCar(Car Car1) {this.Car = Car1;}
    public Car getCar() {return this.Car;}

    public void setPicture_name(String picture_name) {this.picture_name = picture_name;}
    public String getPicture_name() {return this.picture_name;}
}
