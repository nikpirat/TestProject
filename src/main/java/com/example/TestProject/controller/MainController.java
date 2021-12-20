package com.example.TestProject.controller;

import com.example.TestProject.model.Ticket;
import com.example.TestProject.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MainController {

    private final String INCORRECT_INPUT = "Invalid input, please use numbers...";

    private final String TICKET_FOUND = "Ticket Found";
    private final String TICKET_NOT_FOUND = "Ticket not Found";

    private final String TASK1_URL = "task1";

    private final TicketService ticketService;

    @Autowired
    public MainController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    /**
     * TASK 1 - FIND TICKET BY ID
     */
    @GetMapping("/task1")
    public String task1() {
        return "task1";
    }
    @PostMapping("/task1")
    public String task1(@ModelAttribute(name = "id") String number, Model model){

        if (!number.matches("[0-9]+")) return message(model, INCORRECT_INPUT, TASK1_URL);

        try {
            ticketService.getById(Long.parseLong(number));
            return message(model, TICKET_FOUND, TASK1_URL);
        } catch (NumberFormatException | NullPointerException e) {
            System.out.println(e);
            return message(model, TICKET_NOT_FOUND, TASK1_URL);
        }
    }

    /**
     * Returns message to user {FOUND/NOT FOUND/INCORRECT INPUT}
     */
    private String message(Model model, String message, String url) {
        model.addAttribute("message", message);
        return url;
    }
}
