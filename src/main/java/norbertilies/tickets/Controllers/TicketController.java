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
@RequestMapping(value = "/tickets")
@Api(
        name = "Ticket API",
        description = "A few methods to manage our tickets.",
        stage= ApiStage.ALPHA)
public class TicketController {

    private TicketRepository ticketRepository;
    private EventRepository eventRepository;

    @Autowired
    public TicketController(TicketRepository ticketRepository, EventRepository eventRepository) {
        this.ticketRepository = ticketRepository;
        this.eventRepository = eventRepository;
    }

    @RequestMapping(value = "/ticket_details/{id}", method = RequestMethod.GET)
    @ApiMethod(description = "Get details about a specific ticket from the DB.")
    public Ticket getTicketDetails(@ApiPathParam(name="Ticket ID") @PathVariable long id) {
        System.out.println("Ticket details for " + id);
        Ticket currentTicket = ticketRepository.findOne(id);
        if (currentTicket == null)
            System.out.println("Details: No ticket with ID " + id);
        return ticketRepository.findOne(id);
    }

    @RequestMapping(value = "/cancel_ticket/{id}", method = RequestMethod.POST)
    @ApiMethod(description = "Deletes a specific ticket from the DB.")
    public List<Ticket> cancelTicket(@ApiPathParam(name="Ticket ID") @PathVariable long id){
        System.out.println("Canceling ticket "+id);
        Ticket currentTicket = ticketRepository.findOne(id);
        if (currentTicket != null) {
            Event currentEvent = currentTicket.getEvent();
            currentEvent.ticketCanceled(currentTicket);
            ticketRepository.delete(currentTicket);
            eventRepository.save(currentEvent);
        }
        else
            System.out.println("Delete: No ticket with ID "+id);
        return ticketRepository.findAll();
    }

    @RequestMapping(value = "/list_tickets", method = RequestMethod.GET)
    @ApiMethod(description = "Get all the tickets that have been bought.")
    public List<Ticket> getAllTickets(){
        System.out.println("Listing all tickets..");
        return ticketRepository.findAll();
    }
}
