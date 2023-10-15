package org.maxems_spagetti.actions;

import org.maxems_spagetti.map.Coordinates;
import org.maxems_spagetti.map.Map;

import java.util.Random;

public abstract class Actions {

    private final Map map;

    protected Actions(Map map) {
        this.map = map;
    }

    protected Coordinates createRandomPosition() {
        Random random = new Random();
        int x = random.nextInt(map.getWIDTH());
        int y = random.nextInt(map.getHEIGHT());
        return new Coordinates(x, y);
    }

}
