package bern.project.travellog.model;

import javax.persistence.*;

@Entity
@Table(name = "travels")
public class Travel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // AUTO
    @Column(name = "travel_id")
    private long id;

    @Column(name = "travel_name")
    private String name;

    @Column(name = "travel_itinerary")
    private String itinerary;

    @Column(name = "user_id")
    private long userId;

    public Travel() {

    }

    public Travel(String name, String itinerary, long userId) {
        this.name = name;
        this.itinerary = itinerary;
        this.userId = userId;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getItinerary() {
        return itinerary;
    }

    public void setItinerary(String itinerary) {
        this.itinerary = itinerary;
    }

    public long getUserId() {
        return userId;
    }
}