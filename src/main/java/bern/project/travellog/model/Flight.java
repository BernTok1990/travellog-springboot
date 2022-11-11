package bern.project.travellog.model;

import java.util.Calendar;

import javax.persistence.*;

@Entity
@Table(name = "flights")
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // AUTO
    @Column(name = "flight_id")
    private long id;

    @Column(name = "flight_date")
    @Temporal(TemporalType.DATE)
    private Calendar date;

    @Column(name = "flight_source")
    private String source;

    @Column(name = "flight_destination")
    private String destination;

    @Column(name = "travel_id")
    private long travelId;

    public Flight() {

    }

    public Flight(Calendar date, String source, String destination, long travelId) {
        this.date = date;
        this.source = source;
        this.destination = destination;
        this.travelId = travelId;
    }

    public long getId() {
        return id;
    }

    public Calendar getDate() {
        return date;
    }

    public String getSource() {
        return source;
    }

    public String getDestination() {
        return destination;
    }

    public long getTravelId() {
        return travelId;
    }
}