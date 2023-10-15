package org.maxems_spagetti.entities.creature;

import org.maxems_spagetti.entities.Entity;
import org.maxems_spagetti.map.Coordinates;
import org.maxems_spagetti.map.Map;
import org.maxems_spagetti.entities.stationary.Grass;
import org.maxems_spagetti.map.Move;

import java.util.LinkedList;

public class Herbivore extends Creature {



    public Herbivore(Coordinates coordinates, int speed, int hp) {
        super(coordinates, speed, hp);
    }


    @Override
    public void makeMove(LinkedList<Coordinates> path, Map map) {
        Move move = new Move(path.getFirst(), path.get(1));
        Herbivore herbivore = (Herbivore) map.getEntity(move.from);
        if (isTypeOfExtraction(map.getEntity(move.to))) {
            map.replaceEntityToGround(move.to);
        } else {
            map.removeEntity(move.from);
            map.setEntity(move.to, herbivore);
            map.replaceEntityToGround(move.from);
            map.setMovedCreature(move.to, herbivore);
         }
    }

    @Override
    public boolean isTypeOfExtraction(Entity entity) {
        return entity instanceof Grass;
    }

    public boolean isFullHP() {
        return hp == 100;
    }

    public void setHP(int i) {
        hp = i;
    }
}
