package org.maxems_spagetti.entities.creature;

import org.maxems_spagetti.map.Coordinates;
import org.maxems_spagetti.map.Map;
import org.maxems_spagetti.map.Move;

public class Fox extends Predator {


    public Fox(Coordinates coordinates) {
        super(coordinates, 2, 100);
    }

    @Override
    public int makeMove(Move move, Map map, int countOfHerbivores) {
        super.makeMove(move, map, countOfHerbivores);
        return countOfHerbivores;
    }
}
