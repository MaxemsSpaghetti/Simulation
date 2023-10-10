package org.maxems_spagetti.entities.creature;

import org.maxems_spagetti.map.Coordinates;
import org.maxems_spagetti.map.Map;
import org.maxems_spagetti.map.Move;

public class Hare extends Herbivore {



    public Hare(Coordinates coordinates) {
        super(coordinates, 1, 100);
    }

    @Override
    public int makeMove(Move move, Map map, int countOfGrass) {
        super.makeMove(move, map, countOfGrass);
        return countOfGrass;
    }

    public boolean isFullHP() {
        return super.isFullHP();
    }


    public void setHP(int i) {
        super.setHP(i);
    }
}
