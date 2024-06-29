package il.cshaifasweng.OCSFMediatorExample.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "movies")
public class Movie implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int auto_number_movie;

    private String movie_name;
    private String main_actors;
    private String category;
    private String description_;
    private String time_;
    private int year_;

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Screening> screenings = new ArrayList<Screening>();;

    public Movie(int auto_number_movie, String movie_name, String main_actors, String category, String description_, String time_, int year_) {
        this.auto_number_movie = auto_number_movie;
        this.movie_name = movie_name;
        this.main_actors = main_actors;
        this.category = category;
        this.description_ = description_;
        this.time_ = time_;
        this.year_ = year_;

    }

    public Movie() {
    }

    // Getters and Setters

    public int getAuto_number_movie() {
        return auto_number_movie;
    }

    public String getMovie_name() {
        return movie_name;
    }

    public void setMovie_name(String movie_name) {
        this.movie_name = movie_name;
    }

    public String getMain_actors() {
        return main_actors;
    }

    public void setMain_actors(String main_actors) {
        this.main_actors = main_actors;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription_() {
        return description_;
    }

    public void setDescription_(String description_) {
        this.description_ = description_;
    }

    public String getTime_() {
        return time_;
    }

    public void setTime_(String time_) {
        this.time_ = time_;
    }

    public int getYear_() {
        return year_;
    }

    public void setYear_(int year_) {
        this.year_ = year_;
    }
}
