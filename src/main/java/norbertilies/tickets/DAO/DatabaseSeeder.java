package norbertilies.tickets.DAO;

import norbertilies.tickets.Models.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Component
public class DatabaseSeeder implements CommandLineRunner {

    private EventRepository eventRepository;

    @Autowired
    public DatabaseSeeder(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public void run(String... strings) throws Exception {

        List<Event> events = new ArrayList<>();

        SimpleDateFormat sdf = new SimpleDateFormat("MMM d, ''yy");

        try {
            events.add(new Event("It", 25, 120, sdf.parse("Sep 08, '17")));
            events.add(new Event("Annabelle: Creation", 40, 3, sdf.parse("Aug 09, '17")));
            events.add(new Event("Avatar 2", 11.5, 200, sdf.parse("Dec 18, '20")));
        } catch (ParseException e) {
            System.out.println("Invalid date input");
            e.printStackTrace();
        }
        finally{
            eventRepository.save(events);
        }
    }
}
