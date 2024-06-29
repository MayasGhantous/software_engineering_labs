package il.cshaifasweng.OCSFMediatorExample.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "screening")
public class Screening implements Serializable {

    @ManyToOne
    @JoinColumn(name = "movie_id", referencedColumnName = "auto_number_movie")
    Movie movie;
    @Id
    private int auto_number_movie;
    private String time_;
    private String date_;
    private int room_number;


    public Screening() {
    }

    public Screening(int auto_number_movie, String time, String date, int room_number) {
        this.auto_number_movie = auto_number_movie;
        this.time_ = time;
        this.date_ = date;
        this.room_number = room_number;
        this.movie = new Movie();
    }

    // Getters and Setters

    public int getAuto_number_movie() {
        return auto_number_movie;
    }

    public String getTime() {
        return time_;
    }
    public void setTime(String time_) {
        this.time_ = time_;
    }
    public String getDate() {
        return date_;
    }
    public void setDate(String date_) {
        this.date_ = date_;
    }
    public int getRoom_number() {
        return room_number;
    }
    public void setRoom_number(int room_number) {
        this.room_number = room_number;
    }

}
