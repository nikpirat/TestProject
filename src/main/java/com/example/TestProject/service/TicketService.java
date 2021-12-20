package com.example.TestProject.service;

import com.example.TestProject.model.Ticket;

/**
 * Service class for {@link com.example.TestProject.model.Ticket}
 *
 */

public interface TicketService {
    Ticket getById(long id);

    Ticket findCacheById(long id);
}
