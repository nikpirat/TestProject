package com.example.TestProject.service.impl;

import com.example.TestProject.model.Ticket;
import com.example.TestProject.service.TicketService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toCollection;

/**
 * Service class for {@link com.example.TestProject.model.Ticket}
 *
 */

@Service
public class TicketServiceImpl implements TicketService {
    private final List<Ticket> tickets = Stream.of(new Ticket(1) ,new Ticket(2), new Ticket(3))
            .collect(toCollection(ArrayList::new));

    @Override
    public Ticket getById(long id) {
        return tickets
                .stream()
                .filter(target -> id==target.getId())
                .findAny()
                .orElseThrow(NullPointerException::new);
    }
}
