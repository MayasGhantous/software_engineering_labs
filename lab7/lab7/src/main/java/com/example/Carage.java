package com.example;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "Carage_table")
public class Carage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String location;
    private String Phone_Number;


    @ManyToMany
    @JoinTable(
            name = "Carage_car",
            joinColumns = @JoinColumn(name = "Carage_id"),
            inverseJoinColumns = @JoinColumn(name = "car_id")
    )
    private List<Car> cars;
    @ManyToMany(mappedBy = "garages")
    private List<Human> humans;

    public Carage() {}
    public Carage(String location, String Phone_Number) {
        super();
        this.location = location;
        this.Phone_Number = Phone_Number;
        this.cars = new ArrayList<Car>();
        this.humans=new ArrayList<Human>();

    }

    public int getGarage_id() {return id;}



    public String getLocation() {return location;}
    public void setLocation(String location) {this.location = location;}


    public String getPhone_Number() {return Phone_Number;}
    public void setPhone_Number(String Phone_Number) {this.Phone_Number = Phone_Number;}


    public List<Human> getHumans() {return humans;}
    public void setHumans(List<Human> Humans) {this.humans = Humans;}

    public List<Car> getCars() {return cars;}
    public void setCars(List<Car> cars) {this.cars = cars;}


}
