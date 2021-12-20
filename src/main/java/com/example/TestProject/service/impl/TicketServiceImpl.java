package com.example.TestProject.service.impl;

import com.example.TestProject.model.Ticket;
import com.example.TestProject.service.TicketService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toCollection;

@Service
public class TicketServiceImpl implements TicketService {

    private CacheUtilService<Long,Ticket> cacheUtilService;



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

    /**
     * TASK 4 - CACHING DATA
     */
    @Override
    public Ticket findCacheById(long id) {
        if (cacheUtilService.get(id)==null) {
            cacheUtilService.put(id, new Ticket(id));
            return getById(id);
        }
        return cacheUtilService.get(id);
    }
}
