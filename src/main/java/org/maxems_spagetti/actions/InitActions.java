package org.maxems_spagetti.actions;

import org.maxems_spagetti.map.Coordinates;
import org.maxems_spagetti.map.EntityFactory;
import org.maxems_spagetti.map.Map;

import java.util.HashMap;
import java.util.Objects;
import java.util.Random;

public class InitActions extends Actions {

    private final Map map;

    private final HashMap<String, Integer> amountOfEntities = new HashMap<>();

    public InitActions(Map map) {
        this.map = map;
    }

    public Map fillMap() {
        createAmountOfEntities();
        for (String key : amountOfEntities.keySet() ) {
            for (int i = 0; i < amountOfEntities.get(key); i++) {
                Coordinates coordinates = createRandomPosition();
                if (map.getEntity(coordinates) == null) {
                    map.setEntity(coordinates,
                            Objects.requireNonNull(EntityFactory.createEntity(coordinates, key)));
                } else {
                    i--;
                }
            }
        }
        return map;
    }

    private void createAmountOfEntities() {
        amountOfEntities.put("Fox", 2);
        amountOfEntities.put("Hare", 2);
        amountOfEntities.put("Tree", 4);
        amountOfEntities.put("Grass", 6);
        amountOfEntities.put("Rock", 4);
        amountOfEntities.put("Ground", 31);

//        amountOfEntities.put("Fox", 4);
//        amountOfEntities.put("Hare", 4);
//        amountOfEntities.put("Tree", 8);
//        amountOfEntities.put("Grass", 10);
//        amountOfEntities.put("Rock", 8);
//        amountOfEntities.put("Ground", 66);

//        amountOfEntities.put("Fox", 10);
//        amountOfEntities.put("Hare", 10);
//        amountOfEntities.put("Tree", 30);
//        amountOfEntities.put("Grass", 30);
//        amountOfEntities.put("Rock", 30);
//        amountOfEntities.put("Ground", 290);
    }

    protected Coordinates createRandomPosition() {
        Random random = new Random();
        int x = random.nextInt(map.getWidth());
        int y = random.nextInt(map.getHeight());
        return new Coordinates(x, y);
    }
}
