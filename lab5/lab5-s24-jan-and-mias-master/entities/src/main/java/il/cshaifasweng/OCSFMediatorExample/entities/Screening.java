package il.cshaifasweng.OCSFMediatorExample.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;


@Entity
@Table(name = "screening")
public class Screening implements Serializable {

    private static Map<List<String>,List<Integer>> rooms = new HashMap<List<String>,List<Integer>>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movie_id", referencedColumnName = "auto_number_movie")
    Movie movie;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int auto_number_screening;


    @Temporal(TemporalType.TIMESTAMP)
    private Date date_time;

    private int room_number;
    @Column(columnDefinition = "VARCHAR(5000)")
    private String theater_map;
    private String branch;


    public Screening() {
    }

    public Screening(Date date_time, int room_number, String theater_map, String branch) {
        this.date_time = date_time;
        this.room_number = room_number;
        this.theater_map = theater_map;
        this.branch = branch;
        this.movie = new Movie();
    }

    // Getters and Setters

    public int getAuto_number_screening() {
        return auto_number_screening;
    }

    public Date getDate_time() {
        return date_time;
    }
    public void setDate_time(Date date_time) {
        this.date_time = date_time;
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

    public static void add_to_rooms(String Branch,int room_number,int row_size,int column_size) {
        ArrayList<String> keys = new ArrayList<>();
        keys.add(Branch);
        keys.add(String.valueOf(room_number));
        ArrayList<Integer>values = new ArrayList<Integer>();
        values.add(row_size);
        values.add(column_size);
        rooms.put(keys,values);
    }
    public static List<Integer> get_rows_and_columns(List<String> keys) {
        return rooms.get(keys);
    }

}
