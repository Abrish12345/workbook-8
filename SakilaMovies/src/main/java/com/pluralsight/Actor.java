package com.pluralsight;

public class Actor {
    private int actorId;
    private String firstName;
    private String lastName;

    public Actor(int actorId, String firstName, String lastName) {
        this.actorId = actorId;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public int getActorId() {
        return actorId;
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public String toString() {
        return String.format("ID: %d - %s %s", actorId, firstName, lastName);
    }
}

