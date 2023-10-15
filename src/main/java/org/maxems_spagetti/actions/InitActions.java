package org.maxems_spagetti.actions;

import org.maxems_spagetti.map.Coordinates;
import org.maxems_spagetti.map.EntityFactory;
import org.maxems_spagetti.map.Map;

import java.util.HashMap;

public class InitActions extends Actions {

    private final Map map;

    private final HashMap<String, Integer> amountOfEntities = new HashMap<>();

    public InitActions(Map map) {
        super(map);
        this.map = map;
    }

    public Map fillMap() {
        createAmountOfEntities();
        for (String key : amountOfEntities.keySet()) {
            for (int i = 0; i < amountOfEntities.get(key); i++) {
                Coordinates coordinates = createRandomPosition();
                if (map.getEntity(coordinates) == null) {
                    map.setEntity(coordinates,
                            EntityFactory.createEntity(coordinates, key));
                } else {
                    i--;
                }
            }
        }
        return map;
    }

    private void createAmountOfEntities() {
        amountOfEntities.put("Fox", 10);
        amountOfEntities.put("Hare", 10);
        amountOfEntities.put("Tree", 30);
        amountOfEntities.put("Grass", 30);
        amountOfEntities.put("Rock", 30);
        amountOfEntities.put("Ground", 290);
    }
    @Override
    protected Coordinates createRandomPosition() {
        return super.createRandomPosition();
    }
}
