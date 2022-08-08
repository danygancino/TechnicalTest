package com.dmgc.technicaltest.model;

/**
 * Storage the edge, this have origin, destiny and distance between two towns
 */
public class Route {
    private Town origin;
    private Town destiny;
    private int distance;

    public Route(Town origin, Town destiny, int distance) {
        this.origin = origin;
        this.destiny = destiny;
        this.distance = distance;
    }

    public Town getOrigin() {
        return origin;
    }

    public void setOrigin(Town origin) {
        this.origin = origin;
    }

    public Town getDestiny() {
        return destiny;
    }

    public void setDestiny(Town destiny) {
        this.destiny = destiny;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }
}
