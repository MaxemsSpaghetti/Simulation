package org.maxems_spagetti.entities.creature;

import org.maxems_spagetti.entities.Entity;
import org.maxems_spagetti.map.Coordinates;
import org.maxems_spagetti.map.Map;
import org.maxems_spagetti.map.Move;

import java.util.LinkedList;

public class Predator extends Creature {

    public Predator(Coordinates coordinates, int speed, int hp) {
        super(coordinates, speed, hp);
    }


    @Override
    public void makeMove(LinkedList<Coordinates> path, Map map) {
        Move move;
        if (path.size() <= 3) {
            move = new Move(path.getFirst(), path.getLast());
        } else {
            move = new Move(path.getFirst(), path.get(2));
        }
        Predator predator = (Predator) map.getEntity(move.from);
            map.removeEntity(move.from);
            map.setEntity(move.to, predator);
            map.replaceEntityToGround(move.from);
            map.setMovedCreature(move.to, predator);
    }

    @Override
    public boolean isTypeOfExtraction(Entity entity) {
        return entity instanceof Hare;
    }
}
