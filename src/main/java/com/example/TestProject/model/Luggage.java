package com.example.TestProject.model;

import java.util.Objects;

public class Luggage {
    private long id;
    private long destinationId;

    public Luggage(long id, long destinationId) {
        this.id = id;
        this.destinationId = destinationId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getDestinationId() {
        return destinationId;
    }

    public void setDestinationId(long destinationId) {
        this.destinationId = destinationId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Luggage luggage = (Luggage) o;
        return id == luggage.id &&
                destinationId == luggage.destinationId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, destinationId);
    }

    @Override
    public String toString() {
        return "Luggage{" +
                "id=" + id +
                ", destinationId=" + destinationId +
                '}';
    }
}
