package norbertilies.tickets.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    private String name;
    private double ticketPrice;
    private int currentSold;
    private int maxCapacity;
    @Temporal(TemporalType.DATE)
    private Date eventDate;

    @OneToMany(mappedBy = "event")
    @JsonIgnore
    private List<Ticket> listOfTickets = new ArrayList<>();

    public Event(){}

    public Event(String name, double ticketPrice, int maxCapacity, Date eventDate) {
        this.name = name;
        this.ticketPrice = ticketPrice;
        this.currentSold = 0;
        this.maxCapacity = maxCapacity;
        this.eventDate = eventDate;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getTicketPrice() {
        return ticketPrice;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public Date getEventDate() {
        return eventDate;
    }

    public int getCurrentSold() {
        return currentSold;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTicketPrice(double ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public void setCurrentSold(int currentSold) {
        this.currentSold = currentSold;
    }

    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }

    public void ticketSold(Ticket ticket){
        this.currentSold++;
        this.listOfTickets.add(ticket);
    }

    public void ticketCanceled(Ticket ticket){
        this.currentSold--;
        this.listOfTickets.remove(ticket);
    }

    public List<Ticket> getListOfTickets() {
        return listOfTickets;
    }

    public void setListOfTickets(List<Ticket> listOfTickets) {
        this.listOfTickets = listOfTickets;
    }
}
