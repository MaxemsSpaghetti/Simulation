package org.maxems_spagetti.entities.creature;

import org.maxems_spagetti.map.Coordinates;
import org.maxems_spagetti.map.Map;
import org.maxems_spagetti.entities.Entity;

import java.util.LinkedList;

public abstract class Creature extends Entity {


    int speed;

    int hp;

    public Creature(Coordinates coordinates, int speed, int hp) {
        super(coordinates);
        this.speed = speed;
        this.hp = hp;
    }

    public abstract void makeMove(LinkedList<Coordinates> path, Map map);

    public abstract boolean isTypeOfExtraction(Entity entity);
}
