package org.maxems_spagetti.map;

import org.maxems_spagetti.entities.Entity;
import org.maxems_spagetti.entities.creature.Fox;
import org.maxems_spagetti.entities.creature.Hare;
import org.maxems_spagetti.entities.stationary.Grass;
import org.maxems_spagetti.entities.stationary.Ground;
import org.maxems_spagetti.entities.stationary.Rock;
import org.maxems_spagetti.entities.stationary.Tree;

public class MapRenderer {

    private static final String FOX = "\uD83E\uDD8A";

    private static final String HARE = "\uD83D\uDC30";

    private static final String TREE = "\uD83C\uDF33";

    private static final String GRASS = "\uD83C\uDF40";

    private static final String GROUND = "\uD83D\uDFE4";

    private static final String ROCK = "\uD83D\uDF9B";

    public void render(Map map) {
        for (int i = 0; i < map.getHEIGHT(); i++) {
            for (int j = 0; j < map.getWIDTH(); j++) {
                Entity entity = map.getEntity(new Coordinates(j, i));
                if (entity instanceof Grass) {
                    System.out.print(GRASS);
                } else if (entity instanceof Fox) {
                    System.out.print(FOX);
                } else if (entity instanceof Tree) {
                    System.out.print(TREE);
                } else if (entity instanceof Hare) {
                    System.out.print(HARE);
                } else if (entity instanceof Ground) {
                    System.out.print(GROUND);
                } else if (entity instanceof Rock) {
                    System.out.print(ROCK);
                }
            }
            System.out.println();
        }
    }

}
