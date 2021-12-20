package com.example.TestProject.service.impl;

import com.example.TestProject.model.Luggage;
import com.example.TestProject.service.LuggageService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toCollection;
@Service
public class LuggageServiceImpl implements LuggageService {
    private final List<Luggage> luggages = Stream.of(new Luggage(1,1), new Luggage(2,2), new Luggage(3,3))
            .collect(toCollection(ArrayList::new));
    @Override
    public Luggage getByIdAndDestinationId(long id, long destinationId) {
        return luggages
                .stream()
                .filter(luggage -> id==luggage.getId()&&destinationId==luggage.getDestinationId())
                .findAny()
                .orElseThrow(NullPointerException::new);
    }
}
