package norbertilies.tickets.Controllers;

import norbertilies.tickets.DAO.EventRepository;
import norbertilies.tickets.DAO.TicketRepository;
import norbertilies.tickets.Models.Event;
import norbertilies.tickets.Models.Ticket;
import org.jsondoc.core.annotation.Api;
import org.jsondoc.core.annotation.ApiMethod;
import org.jsondoc.core.annotation.ApiPathParam;
import org.jsondoc.core.pojo.ApiStage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/events")
@Api(
        name = "Event API",
        description = "A few methods to help with our ticket management.",
        stage= ApiStage.ALPHA)
public class EventController {

    private EventRepository eventRepository;
    private TicketRepository ticketRepository;

    @Autowired
    public EventController(EventRepository eventRepository, TicketRepository ticketRepository){
        this.eventRepository = eventRepository;
        this.ticketRepository = ticketRepository;
    }

    @RequestMapping(value = "/list_events", method = RequestMethod.GET)
    @ApiMethod(description = "Get all events from DB.")
    public List<Event> getAllEvents(){
        System.out.println("Listing all events..");
        return eventRepository.findAll();
    }

    @RequestMapping(value = "/buy_ticket/{id}", method = RequestMethod.POST)
    @ApiMethod(description = "Buy a ticket for an event, if there are any tickets left for sale.")
    public List<Event> buyTicket(@ApiPathParam(name = "Event ID") @PathVariable long id){
        Event currentEvent = eventRepository.findOne(id);
        if (currentEvent.getMaxCapacity()-currentEvent.getCurrentSold() > 0) {
            System.out.println("Buying ticket for event "+ id);
            Ticket newTicket = new Ticket(currentEvent);
            ticketRepository.save(newTicket);
            currentEvent.ticketSold(newTicket);
            eventRepository.save(currentEvent);
        }
        else{
            System.out.println("No available ticket for event "+id);
        }
        return eventRepository.findAll();
    }
}
