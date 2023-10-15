package org.maxems_spagetti.actions;

import org.maxems_spagetti.entities.Entity;
import org.maxems_spagetti.entities.creature.Creature;
import org.maxems_spagetti.entities.stationary.Ground;
import org.maxems_spagetti.map.Coordinates;
import org.maxems_spagetti.map.EntityFactory;
import org.maxems_spagetti.map.Map;
import org.maxems_spagetti.pathFinder.AStarPathfinder;


import java.util.*;

public class TurnActions extends Actions {

    private final Map map;

    private int countOfTurns;

    public TurnActions(Map map) {
        super(map);
        this.map = map;
    }

    public void turnActions() {
        countOfTurns++;

        for (Entity entity : map.getEntities().values()) {
            if (entity instanceof Creature creature && !map.isMovedCreature(entity.coordinates)) {
                AStarPathfinder pathFinder = new AStarPathfinder();
                LinkedList<Coordinates> path = pathFinder.findPath(entity, map);
                if (!path.isEmpty()) {
                    creature.makeMove(path, map);
                }
            }
        }
        map.clearMovedCreatures();
        addExtraEntities();

        System.out.println("Turn: " + countOfTurns);
    }

    @Override
    public Coordinates createRandomPosition() {
        return super.createRandomPosition();
    }

    public void addExtraEntities() {
        int i = 2;
        while (i >= 0) {
            Coordinates coordinates = createRandomPosition();
            if (map.getEntity(coordinates) instanceof Ground) {
                map.setEntity(coordinates,
                        Objects.requireNonNull(
                                EntityFactory.createEntity(
                                        coordinates, i != 0 ? "Hare" : "Grass")));
                i--;
            }
        }
    }
}
