package com.example;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "humans_table")
public class Human {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String first_name;
    private String last_name;
    private String Password;
    private String Email;

    @OneToMany(mappedBy = "Human", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Car> Cars;

    @ManyToMany
    @JoinTable(
            name = "human_garage",
            joinColumns = @JoinColumn(name = "human_id"),
            inverseJoinColumns = @JoinColumn(name = "garage_id")
    )
    private List<Carage> garages;

    public  Human(){}

    public Human(String first_name,String last_name,String Password,String Email)
    {
        super();
        this.first_name = first_name;
        this.last_name = last_name;
        this.Password = Password;
        this.Email = Email;
        this.Cars = new ArrayList<Car>();
        this.garages = new ArrayList<Carage>();
    }

    public String getFirstName(){
        return first_name;
    }
    public void setFirstName(String first_name) {this.first_name = first_name;}

    public String getLastName() {return last_name;}
    public void setLastName(String last_name) {this.last_name = last_name;}

    public String getPassword() {return Password;}
    public void setPassword(String Password) {this.Password = Password;}

    public String getEmail() {return Email;}
    public void setEmail(String Email) {this.Email = Email;}

    public int getId() {return id;}

    public List<Car> getCars() {return Cars;}
    public void setCars(List<Car> Cars) {this.Cars = Cars;}

    public List<Carage> getGarages() {return garages;}
    public void setGarages(List<Carage> garages) {this.garages = garages;}

}
