package org.maxems_spagetti.pathFinder;

import org.maxems_spagetti.entities.Entity;
import org.maxems_spagetti.entities.creature.Creature;
import org.maxems_spagetti.entities.creature.Hare;
import org.maxems_spagetti.entities.creature.Predator;
import org.maxems_spagetti.entities.stationary.Grass;
import org.maxems_spagetti.entities.stationary.Ground;
import org.maxems_spagetti.map.Coordinates;
import org.maxems_spagetti.map.Map;

import java.util.*;

public class AStarPathfinder {

    private Map map;
    private final Set<Coordinates> checkedCells = new HashSet<>();
    private final PriorityQueue<EntityWrapper> cellsToCheck = new PriorityQueue<>
            (Comparator.comparingInt(EntityWrapper::getSumCost));

    public LinkedList<Coordinates> findPath(Entity entity, Map map) {

        this.map = map;

        Coordinates targetCoordinates = getNearestExtraction((Creature) entity);

        EntityWrapper startNode = new EntityWrapper(entity.coordinates, null,
                0, heuristicCost(entity.coordinates, targetCoordinates));
        cellsToCheck.add(startNode);

        while (!cellsToCheck.isEmpty()) {
            EntityWrapper currentEntityWrapper = cellsToCheck.poll();

            if (currentEntityWrapper.getCoordinates().equals(targetCoordinates)) {
                return reconstructPath(startNode, currentEntityWrapper);
            }

            checkedCells.add(currentEntityWrapper.getCoordinates());

            for (Coordinates neighborCoordinates : getNeighborCoordinates(currentEntityWrapper.getCoordinates())) {
                if (checkedCells.contains(neighborCoordinates)) {
                    continue;
                }

                Entity neighborEntity = map.getEntity(neighborCoordinates);
                if (canEntityMoveTo(entity, neighborEntity)) {
                    int currentPathCost = currentEntityWrapper.getCostFromStartPosition() + 1;
                    EntityWrapper neighborNode = new EntityWrapper(neighborCoordinates, currentEntityWrapper,
                            currentPathCost, heuristicCost(neighborCoordinates, targetCoordinates));

                    if (!cellsToCheck.contains(neighborNode) || currentPathCost < neighborNode.getCostFromStartPosition()) {
                        neighborNode.setParent(currentEntityWrapper);
                        neighborNode.setCostFromStartPosition(currentPathCost);
                        neighborNode.setSumCost(neighborNode.getCostFromStartPosition() + neighborNode.getHeuristicCost());
                        cellsToCheck.add(neighborNode);
                    }
                }
            }
        }
        return new LinkedList<>();
    }

    private Coordinates getNearestExtraction(Creature creature) {
        Coordinates nearestExtraction = null;
        int minDistance = Integer.MAX_VALUE;
        for (Coordinates coordinates : map.getEntities().keySet()) {
            Entity entity = map.getEntity(coordinates);
            if (creature.isTypeOfExtraction(entity)) {
                int distance = heuristicCost(creature.coordinates, coordinates);
                if (distance < minDistance) {
                    nearestExtraction = coordinates;
                    minDistance = distance;
                }
            }
        }
        return nearestExtraction;
    }

    private int heuristicCost(Coordinates from, Coordinates to) {
        return Math.abs(from.getX() - to.getX()) + Math.abs(from.getY() - to.getY());
    }

    private boolean isValidCoordinates(Coordinates coordinates) {
        return coordinates.getX() >= 0 && coordinates.getX() < map.getWidth()
                && coordinates.getY() >= 0 && coordinates.getY() < map.getHeight();
    }

    private LinkedList<Coordinates> reconstructPath(EntityWrapper startEntityWrapper, EntityWrapper currentEntityWrapper) {
        LinkedList<Coordinates> path = new LinkedList<>();
         do {
            path.addFirst(currentEntityWrapper.getCoordinates());
            currentEntityWrapper = currentEntityWrapper.getParent();
        } while (currentEntityWrapper.getParent() != null);
        path.addFirst(startEntityWrapper.getCoordinates());
        return path;

    }

    private Set<Coordinates> getNeighborCoordinates(Coordinates coordinates) {
        Set<Coordinates> neighbors = new HashSet<>();
        int x = coordinates.getX();
        int y = coordinates.getY();

        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (Math.abs(i) == Math.abs(j)) {
                    continue;
                }
                Coordinates neighborCoordinates = new Coordinates(x + i, y + j);
                if (isValidCoordinates(neighborCoordinates)) {
                    neighbors.add(neighborCoordinates);
                }
            }
        }
        return neighbors;
    }

    private boolean canEntityMoveTo(Entity entity, Entity neighborEntity) {
        if (entity instanceof Predator) {
            return neighborEntity instanceof Hare || neighborEntity instanceof Ground;
        }
        return neighborEntity instanceof Grass || neighborEntity instanceof Ground;
    }
}