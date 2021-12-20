package com.example.TestProject.model;

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
    public String toString() {
        return "Luggage{" +
                "id=" + id +
                ", destinationId=" + destinationId +
                '}';
    }
}
