package com.example.TestProject.service;

import com.example.TestProject.model.Luggage;

/**
 * Service class for {@link com.example.TestProject.model.Luggage}
 *
 */

public interface LuggageService {
    Luggage getByIdAndDestinationId(long id, long destinationId);
}
