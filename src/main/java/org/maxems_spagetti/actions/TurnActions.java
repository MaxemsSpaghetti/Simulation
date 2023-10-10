package org.maxems_spagetti.actions;

import org.maxems_spagetti.entities.Entity;
import org.maxems_spagetti.entities.creature.Creature;
import org.maxems_spagetti.entities.creature.Herbivore;
import org.maxems_spagetti.entities.creature.Predator;
import org.maxems_spagetti.entities.stationary.Grass;
import org.maxems_spagetti.entities.stationary.Ground;
import org.maxems_spagetti.map.Coordinates;
import org.maxems_spagetti.map.EntityFactory;
import org.maxems_spagetti.map.Map;
import org.maxems_spagetti.map.Move;
import org.maxems_spagetti.pathFinder.BFSToEntity;

import java.util.List;
import java.util.Objects;
import java.util.Random;

public class TurnActions extends Actions {

    int countOfGrass = 6;

    int countOfHerbivores = 2;

    private final Map map;

    public TurnActions(Map map) {
        this.map = map;
    }

    public void turnActions() {
        for (int i = 0; i < map.getHeight(); i++) {
            for (int j = 0; j < map.getWidth(); j++) {
                Entity entity = map.getEntity(new Coordinates(j, i));
                if (entity instanceof Creature && !map.isMovedCreature(entity.coordinates)) {
                    moveCreature((Creature) entity, map);
                }
            }
        }
        map.clearMovedCreatures();
        if (countOfGrass < 3) {
            addEndingEntities(new Grass());
        } else if ( countOfHerbivores < 2) {
            addEndingEntities(new Herbivore());
        }
    }

    public void moveCreature(Creature creature, Map map) {
        BFSToEntity bfsToEntity = new BFSToEntity();
        Coordinates targetCoordinates = bfsToEntity.findNearestEntity(creature, map);

        if (targetCoordinates != null) {
            List<Coordinates> path =  bfsToEntity.BFS(creature, map);
            Coordinates nextPosition = path.get(1);
            Move move = new Move(creature.coordinates, nextPosition);
            if (creature instanceof Herbivore) {
                countOfGrass = creature.makeMove(move, map, countOfGrass);
            } else if (creature instanceof Predator) {
                countOfHerbivores = creature.makeMove(move, map, countOfHerbivores);
            }
        }
    }

    protected Coordinates createRandomPosition(Entity entity) {
        Random random = new Random();
        if (entity instanceof Herbivore) {
            int squareSize = 20;

            int x = random.nextInt(2) * (squareSize - 1);
            int y = random.nextInt(2) * (squareSize - 1);
            return new Coordinates(x, y);
        } else {
            int x = random.nextInt(map.getWidth());
            int y = random.nextInt(map.getHeight());
            return new Coordinates(x, y);
        }
    }

    public void addEndingEntities(Entity entity) {
        int i = entity instanceof Herbivore ? 2 : 5;
        while (i >= 0) {
            Coordinates coordinates = createRandomPosition(entity);
            if (map.getEntity(coordinates) instanceof Ground) {
                map.setEntity(coordinates,
                        Objects.requireNonNull(
                                EntityFactory.createEntity(
                                        coordinates, entity.getClass().getSimpleName())));
                i--;
            }
        }
    }
}
