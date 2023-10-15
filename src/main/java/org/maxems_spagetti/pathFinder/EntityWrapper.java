package org.maxems_spagetti.pathFinder;

import org.maxems_spagetti.map.Coordinates;

import java.util.Objects;

public class EntityWrapper {

    private Coordinates coordinates;

    private EntityWrapper parent;

    private int costFromStartPosition;

    private int heuristicCost;

    private int sumCost;

    public EntityWrapper(Coordinates coordinates, EntityWrapper parent,
                         int costFromStartPosition, int heuristicCost) {
        this.coordinates = coordinates;
        this.parent = parent;
        this.costFromStartPosition = costFromStartPosition;
        this.heuristicCost = heuristicCost;
        this.sumCost = costFromStartPosition + heuristicCost;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public EntityWrapper getParent() {
        return parent;
    }

    public void setParent(EntityWrapper parent) {
        this.parent = parent;
    }

    public int getCostFromStartPosition() {
        return costFromStartPosition;
    }

    public void setCostFromStartPosition(int costFromStartPosition) {
        this.costFromStartPosition = costFromStartPosition;
    }

    public int getHeuristicCost() {
        return heuristicCost;
    }

    public void setHeuristicCost(int heuristicCost) {
        this.heuristicCost = heuristicCost;
    }

    public int getSumCost() {
        return sumCost;
    }

    public void setSumCost(int sumCost) {
        this.sumCost = sumCost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EntityWrapper that = (EntityWrapper) o;
        return costFromStartPosition == that.costFromStartPosition
                && heuristicCost == that.heuristicCost
                && sumCost == that.sumCost
                && Objects.equals(coordinates, that.coordinates)
                && Objects.equals(parent, that.parent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(coordinates, parent, costFromStartPosition, heuristicCost, sumCost);
    }
}
