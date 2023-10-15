package org.maxems_spagetti.entities.creature;

import org.maxems_spagetti.map.Coordinates;
import org.maxems_spagetti.map.Map;

import java.util.LinkedList;

public class Fox extends Predator {


    public Fox(Coordinates coordinates) {
        super(coordinates, 2, 100);
    }

    @Override
    public void makeMove(LinkedList<Coordinates> path, Map map) {
        super.makeMove(path, map);
    }
}
