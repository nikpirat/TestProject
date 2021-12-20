package com.example.TestProject.controller;

import com.example.TestProject.service.LuggageService;
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

    private final String LUGGAGE_FOUND = "Luggage Found";
    private final String LUGGAGE_NOT_FOUND = "Luggage not Found";

    private final String TASK1_URL = "task1";
    private final String TASK2_URL = "task2";

    private final TicketService ticketService;
    private final LuggageService luggageService;

    @Autowired
    public MainController(TicketService ticketService, LuggageService luggageService) {
        this.ticketService = ticketService;
        this.luggageService = luggageService;
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

    @GetMapping("/task2")
    public String task2(){
        return "task2";
    }

    @PostMapping("/task2")
    public String task2(@ModelAttribute(name = "id") String number, @ModelAttribute(name = "destinationId") String destinationIdNumber, Model model){

        if (!number.matches("[0-9]+")||!destinationIdNumber.matches("[0-9]+")) return message(model, INCORRECT_INPUT, TASK2_URL);

        try {
            luggageService.getByIdAndDestinationId(Long.parseLong(number),Long.parseLong(destinationIdNumber));
            return message(model, LUGGAGE_FOUND, TASK2_URL);
        } catch (NumberFormatException | NullPointerException e) {
            System.out.println(e);
            return message(model, LUGGAGE_NOT_FOUND, TASK2_URL);
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
