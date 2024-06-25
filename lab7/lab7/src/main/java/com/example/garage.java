package com.example;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "garages_table")
public class Garage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String location;
    private String Phone_Number;


    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "HumansGrage",
            joinColumns = @JoinColumn(name = "Garadge_Id"),
            inverseJoinColumns = @JoinColumn(name = "id")
    )
    private List<Human> Humans;

    public Garage() {}
    public Garage(String location, String Phone_Number) {
        super();
        this.location = location;
        this.Phone_Number = Phone_Number;

    }

    public int getGarage_id() {return id;}



    public String getLocation() {return location;}
    public void setLocation(String location) {this.location = location;}


    public String getPhone_Number() {return Phone_Number;}
    public void setPhone_Number(String Phone_Number) {this.Phone_Number = Phone_Number;}


    public List<Human> getHumans() {return Humans;}
    public void setHumans(List<Human> Humans) {this.Humans = Humans;}


}
