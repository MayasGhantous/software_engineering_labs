package il.cshaifasweng.OCSFMediatorExample.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.sql.Time;

@Entity
@Table(name = "screening")
public class Screening implements Serializable {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movie_id", referencedColumnName = "auto_number_movie")
    Movie movie;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int auto_number_screening;
    private Time time_;
    private Date date_;
    private int room_number;
    private String theater_map;
    private String branch;


    public Screening() {
    }

    public Screening(Time time, Date date, int room_number, String theater_map, String branch) {
        this.time_ = time;
        this.date_ = date;
        this.room_number = room_number;
        this.theater_map = theater_map;
        this.branch = branch;
        this.movie = new Movie();
    }

    // Getters and Setters

    public int getAuto_number_screening() {
        return auto_number_screening;
    }

    public Time getTime() {
        return time_;
    }
    public void setTime(Time time_) {
        this.time_ = time_;
    }
    public Date getDate() {
        return date_;
    }
    public void setDate(Date date_) {
        this.date_ = date_;
    }
    public int getRoom_number() {
        return room_number;
    }
    public void setRoom_number(int room_number) {
        this.room_number = room_number;
    }

    public Movie getMovie() {return this.movie;}
    public void setMovie(Movie movie) {this.movie = movie;}

    public String getTheater_map() {return theater_map;}
    public void setTheater_map(String theater_map) {this.theater_map = theater_map;}
    public String getBranch() {return branch;}
    public void setBranch(String branch) {this.branch = branch;}

}
