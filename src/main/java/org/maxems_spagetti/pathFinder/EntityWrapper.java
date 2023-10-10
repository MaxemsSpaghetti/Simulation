package org.maxems_spagetti.pathFinder;

import org.maxems_spagetti.map.Coordinates;

public class EntityWrapper {

    public Coordinates coordinatesOfCurrentEntity;

    public EntityWrapper coordinatesOfParentEntity;

    public EntityWrapper(Coordinates coordinatesOfCurrentEntity, EntityWrapper coordinatesOfParentEntity) {
        this.coordinatesOfCurrentEntity = coordinatesOfCurrentEntity;
        this.coordinatesOfParentEntity = coordinatesOfParentEntity;
    }
}
