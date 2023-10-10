package org.maxems_spagetti.entities.creature;

import org.maxems_spagetti.entities.Entity;
import org.maxems_spagetti.map.Coordinates;
import org.maxems_spagetti.map.Map;
import org.maxems_spagetti.map.Move;

public class Predator extends Creature {

    public Predator(Coordinates coordinates, int speed, int hp) {
        super(coordinates, speed, hp);
    }


    @Override
    public int makeMove(Move move, Map map, int countOfHerbivores) {
        Predator predator = (Predator) map.getEntity(move.from);
        map.removeEntity(move.from);
        if (map.getEntity(move.to) instanceof Herbivore
                && move.from.getX() - move.to.getX() == 1
                && move.from.getY() - move.to.getY() == 1) {
            bite(move, map, countOfHerbivores);
        }
        map.setEntity(move.to, predator);
        map.replaceEntityToGround(move.from);
        map.setMovedCreature(move.to, predator);
        return countOfHerbivores;
    }

    @Override
    public boolean isTypeOfExtraction(Entity entity) {
        return entity instanceof Hare;
    }

    private int bite(Move move, Map map, int countOfHerbivores) {
        Hare hare = (Hare) map.getEntity(move.to);
        if (hare.isFullHP()) {
            hare.setHP(50);
        } else {
            map.replaceEntityToGround(move.to);
            --countOfHerbivores;
        }
        return countOfHerbivores;
    }
}
