package org.maxems_spagetti.map;

import org.maxems_spagetti.entities.Entity;
import org.maxems_spagetti.entities.creature.Creature;
import org.maxems_spagetti.entities.stationary.Ground;

import java.util.HashMap;

public class Map {

    private final int width = 7;

    private final int height = 7;

    private HashMap<Coordinates, Creature> movedCreatures = new HashMap<>();

    HashMap<Coordinates, Entity> entities = new HashMap<>();



    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public void setEntity(Coordinates coordinates, Entity entity) {
        entity.coordinates = coordinates;
        entities.put(coordinates, entity);
    }

    public Entity getEntity(Coordinates coordinates) {
        return entities.get(coordinates);
    }

    public java.util.Map<Coordinates, Entity> getEntities() {
        return new HashMap<>(entities);
    }

    public void removeEntity(Coordinates coordinates) {
        entities.remove(coordinates);
    }

    public void replaceEntityToGround(Coordinates coordinates) {
        setEntity(coordinates, new Ground(coordinates));
    }

    public boolean isMovedCreature(Coordinates coordinates) {
        return movedCreatures.containsKey(coordinates);
    }

    public void setMovedCreature(Coordinates coordinates, Creature creature) {
        movedCreatures.put(coordinates, creature);

    }
    public void clearMovedCreatures(){
        movedCreatures.clear();
    }

    public boolean isValidCoordinates(Coordinates coordinates) {

        int x = coordinates.getX();
        int y = coordinates.getY();

        return x >= 0 && x < width && y >= 0 && y < height;
    }
}
