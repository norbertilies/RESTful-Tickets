package norbertilies.tickets.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ViewController {
    //event page
    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping("/tickets")
    public String tickets() {

        return "tickets";
    }
}
