package il.cshaifasweng.OCSFMediatorExample.entities;

import javax.persistence.*;

@Entity
@Table(name = "screening")
public class Screening {
    @Id
    @ManyToOne
    @JoinColumn(name = "auto_number_movie")
    Movie movie;
    private int auto_number_movie;
    private String time;
    private String date;
    private int room_number;

    public Screening() {
    }

    public Screening(int auto_number_movie, String time, String date, int room_number) {
        this.auto_number_movie = auto_number_movie;
        this.time = time;
        this.date = date;
        this.room_number = room_number;
    }

    // Getters and Setters

    public int getAuto_number_movie() {
        return auto_number_movie;
    }

    public void setAuto_number_movie(int auto_number_movie) {
        this.auto_number_movie = auto_number_movie;
    }
    public String getTime() {
        return time;
    }
    public void setTime(String time) {
        this.time = time;
    }
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public int getRoom_number() {
        return room_number;
    }
    public void setRoom_number(int room_number) {
        this.room_number = room_number;
    }

}
