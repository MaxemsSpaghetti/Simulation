package org.maxems_spagetti.map;

import org.maxems_spagetti.entities.Entity;
import org.maxems_spagetti.entities.creature.Fox;
import org.maxems_spagetti.entities.creature.Hare;
import org.maxems_spagetti.entities.stationary.Grass;
import org.maxems_spagetti.entities.stationary.Ground;
import org.maxems_spagetti.entities.stationary.Rock;
import org.maxems_spagetti.entities.stationary.Tree;

public class EntityFactory {

    public static Entity createEntity(Coordinates coordinates, String type) {

        return switch (type) {
            case "Fox" -> new Fox(coordinates);
            case "Hare" -> new Hare(coordinates);
            case "Tree" -> new Tree(coordinates);
            case "Grass" -> new Grass(coordinates);
            case "Ground" -> new Ground(coordinates);
            case "Rock" -> new Rock(coordinates);
            default -> throw new IllegalArgumentException("Wrong entity type");
        };
    }
}
