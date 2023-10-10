package org.maxems_spagetti.entities.creature;

import org.maxems_spagetti.entities.Entity;
import org.maxems_spagetti.map.Coordinates;
import org.maxems_spagetti.map.Map;
import org.maxems_spagetti.map.Move;
import org.maxems_spagetti.entities.stationary.Grass;

public class Herbivore extends Creature {



    public Herbivore(Coordinates coordinates, int speed, int hp) {
        super(coordinates, speed, hp);
    }

    public Herbivore() {
        super();
    }

    @Override
    public int makeMove(Move move, Map map, int countOfGrass) {
         if (map.getEntity(move.to) instanceof Grass) {
            map.replaceEntityToGround(move.to);
            countOfGrass = countOfGrass - 1;
        } else {
             Herbivore herbivore = (Herbivore) map.getEntity(move.from);
             map.removeEntity(move.from);
             map.setEntity(move.to, herbivore);
             map.replaceEntityToGround(move.from);
             map.setMovedCreature(move.to, herbivore);
         }
         return countOfGrass;
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
