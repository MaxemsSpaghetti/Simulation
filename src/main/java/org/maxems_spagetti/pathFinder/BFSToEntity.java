package org.maxems_spagetti.pathFinder;

import org.maxems_spagetti.entities.creature.Creature;
import org.maxems_spagetti.map.Coordinates;
import org.maxems_spagetti.map.Map;
import org.maxems_spagetti.entities.Entity;

import java.util.*;

public class BFSToEntity {

//    private final Deque<EntityWrapper> queueForSearch = new ArrayDeque<>();
//
//
//
//    public Entity BFS(Entity entity, Map map) {
//
//
//        EntityWrapper entityWrapper = new EntityWrapper(entity.coordinates, null);
//        queueForSearch.add(entityWrapper);
//
//        LinkedList<EntityWrapper> searchedEntities = new LinkedList<>();
//        searchedEntities.add(entityWrapper);
//
//        Entity extraction = getTypeOfExtraction(entity);
//
//        while(!queueForSearch.isEmpty()) {
//            EntityWrapper currentEntityWrapper = queueForSearch.pop();
//            if (!searchedEntities.contains(currentEntityWrapper)) {
//                Entity intermediateEntity = map.getEntity(currentEntityWrapper.coordinatesOfCurrentEntity);
//                if (intermediateEntity.getClass() == extraction.getClass()) {
//                    return map.getEntity(extractPath(currentEntityWrapper, entity));
//                }
//            }
//            addNearCells(map, currentEntityWrapper, extraction);
//            searchedEntities.add(currentEntityWrapper);
//        }
//        return null;
//    }
//

//
//    private void addNearCells(Map map, EntityWrapper currentEntityWrapper, Entity extraction) {
//        Coordinates currentCoordinates = currentEntityWrapper.coordinatesOfCurrentEntity;
//        int[][] directions = {
//                {1, 0}, {-1, 0}, {0, 1}, {0, -1}
//        };
//        for (int[] direction : directions) {
//            int newX = currentCoordinates.getX() + direction[0];
//            int newY = currentCoordinates.getY() + direction[1];
//
//            Coordinates newCoordinates = new Coordinates(newX, newY);
//            if (isValidCoordinates(map, newCoordinates)
//                    && !queueForSearch.contains(newCoordinates)) {
//                Entity nearEntity = map.getEntity(newCoordinates);
//                if (isMatchingEntity(nearEntity, extraction)) {
//                    queueForSearch.addLast(new EntityWrapper(newCoordinates, currentEntityWrapper));
//                }
//            }
//        }
//    }

//
//
//    private boolean isMatchingEntity(Entity entity1, Entity entity2) {
//        return entity1.getClass() == Ground.class || entity1.getClass() == entity2.getClass();
//    }
//
//
//
//    private Coordinates extractPath(EntityWrapper entityWrapper, Entity entity) {
//
//        LinkedList<Coordinates> path = new LinkedList<>();
//
//        while (entityWrapper.coordinatesOfParentEntity != null) {
//            path.addFirst(entityWrapper.coordinatesOfCurrentEntity);
//            entityWrapper = entityWrapper.coordinatesOfParentEntity;
//        }
//        if (entity instanceof Predator && path.size() > 2) {
//            return path.get(1);
//        }
//        return path.getFirst();
//    }

    public List<Coordinates> BFS(Creature creature, Map area) {
        Deque<Coordinates> positionQueue = new ArrayDeque<>();
        Set<Coordinates> visitedPositions = new HashSet<>();

        HashMap<Coordinates, Coordinates> parents = new HashMap<>();
        positionQueue.add(creature.coordinates);
        Coordinates targetPosition = null;
        boolean foundTarget = false;

        while (!positionQueue.isEmpty() && !foundTarget) {
            Coordinates currentPosition = positionQueue.remove();
            visitedPositions.add(currentPosition);
            List<Coordinates> neighborPositions = getNeighborPositions(currentPosition, area.getWidth(), area.getHeight());

            for (Coordinates neighborPosition : neighborPositions) {
                if (area.isValidCoordinates(neighborPosition) && !visitedPositions.contains(neighborPosition)) {
                    Entity entityAtLocation = area.getEntity(neighborPosition);
                    if (creature.isTypeOfExtraction(entityAtLocation)) {
                        targetPosition = neighborPosition;
                        parents.put(neighborPosition, currentPosition);
                        foundTarget = true;
                        break;
                    }
                    if (entityAtLocation == null) {
                        positionQueue.add(neighborPosition);
                        parents.put(neighborPosition, currentPosition);
                    }
                }
            }
        }

        List<Coordinates> path = new LinkedList<>();
        if (targetPosition != null) {
            path.add(targetPosition);
            Coordinates tempPosition = targetPosition;
            while (path.get(path.size() - 1) != creature.coordinates) {
                tempPosition = parents.get(tempPosition);
                path.add(tempPosition);
            }
            Collections.reverse(path);
        }

        return path;
    }

    public Coordinates findNearestEntity(Creature creature, Map area) {
        Coordinates currentPosition = creature.getCoordinates();
        Coordinates nearestEntity = null;
        double nearestDistance = Double.POSITIVE_INFINITY;

        for (Coordinates position : area.getEntities().keySet()) {
            Entity entity = area.getEntity(position);

            if (creature.isTypeOfExtraction(entity)) {
                double distance = calculateDistance(currentPosition, position);

                if (distance <= nearestDistance) {
                    nearestEntity = position;
                    nearestDistance = distance;
                }
            }
        }

        return nearestEntity;
    }

    private double calculateDistance(Coordinates position1, Coordinates position2) {
        int deltaX = position2.getX() - position1.getX();
        int deltaY = position2.getY() - position1.getY();
        return Math.sqrt(deltaX * deltaX + deltaY * deltaY);
    }

    private List<Coordinates> getNeighborPositions(Coordinates position, int mapWidth, int mapHeight) {
        int width;
        int height;
        List<Coordinates> neighborPositions = new ArrayList<>();

        if ((width = position.getX() - 1) >= 0) {
            neighborPositions.add(new Coordinates(width, position.getY()));
        }
        if ((width = position.getX() + 1) < mapWidth) {
            neighborPositions.add(new Coordinates(width, position.getY()));
        }
        if ((height = position.getY() - 1) >= 0) {
            neighborPositions.add(new Coordinates(position.getX(), height));
        }
        if ((height = position.getY() + 1) < mapHeight) {
            neighborPositions.add(new Coordinates(position.getX(), height));
        }

        return neighborPositions;
    }
}