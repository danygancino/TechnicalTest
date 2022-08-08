package com.dmgc.technicaltest.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Internal Class to storage posible towns in routes *
 */
public class SubRoute implements Comparable<SubRoute> {

    int dest;
    double peso;
    int lastStopsCount;
    List<Town> towns = new ArrayList<>();

    public SubRoute(int dest, double peso) {
        this.dest = dest;
        this.peso = peso;
    }

    public SubRoute(int dest, double peso, int lastStopsCount, List<Town> towns) {
        this.dest = dest;
        this.peso = peso;
        this.lastStopsCount = lastStopsCount;
        this.towns.addAll(towns);
    }

    public void addTown(Town town) {
        towns.add(town);
    }

    public int compareTo(SubRoute otro) {
        if (this.peso < otro.peso) {
            return -1;
        } else if (this.peso > otro.peso) {
            return 1;
        } else {
            return 0;
        }
    }

    public int getDest() {
        return dest;
    }

    public void setDest(int dest) {
        this.dest = dest;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public int getLastStopsCount() {
        return lastStopsCount;
    }

    public void setLastStopsCount(int lastStopsCount) {
        this.lastStopsCount = lastStopsCount;
    }

    public List<Town> getTowns() {
        return towns;
    }

    public void setTowns(List<Town> towns) {
        this.towns = towns;
    }
}
