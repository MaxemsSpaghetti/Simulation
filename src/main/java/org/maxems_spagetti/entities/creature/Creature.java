package org.maxems_spagetti.entities.creature;

import org.maxems_spagetti.map.Coordinates;
import org.maxems_spagetti.map.Map;
import org.maxems_spagetti.map.Move;
import org.maxems_spagetti.entities.Entity;

public abstract class Creature extends Entity {


    int speed;

    int hp;

    public Creature(Coordinates coordinates, int speed, int hp) {
        super(coordinates);
        this.speed = speed;
        this.hp = hp;
    }

    public Creature() {
    }

    public abstract int makeMove(Move move, Map map, int count);

    public abstract boolean isTypeOfExtraction(Entity entity);
}
