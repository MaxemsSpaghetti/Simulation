package org.maxems_spagetti.entities.creature;

import org.maxems_spagetti.map.Coordinates;
import org.maxems_spagetti.map.Map;

import java.util.LinkedList;

public class Hare extends Herbivore {



    public Hare(Coordinates coordinates) {
        super(coordinates, 1, 100);
    }

    @Override
    public void makeMove(LinkedList<Coordinates> path, Map map) {
        super.makeMove(path, map);
    }

    public boolean isFullHP() {
        return super.isFullHP();
    }


    public void setHP(int i) {
        super.setHP(i);
    }
}
