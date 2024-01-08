package com.codegym.task.task34.task3410.model;

import java.util.HashSet;
import java.util.Set;

public class GameObjects {
    private Set<Wall> walls;
    private Set<Box> boxes;
    private Set<StorageLocation> storageLocations;
    private Player player;

    public GameObjects(Set<Wall> walls,
                       Set<Box> boxes,
                       Set<StorageLocation> storageLocations,
                       Player player) {
        this.walls = walls;
        this.boxes = boxes;
        this.storageLocations = storageLocations;
        this.player = player;
    }

    public Set<Wall> getWalls() {
        return walls;
    }

    public Set<Box> getBoxes() {
        return boxes;
    }

    public Set<StorageLocation> getStorageLocations() {
        return storageLocations;
    }

    public Player getPlayer() {
        return player;
    }

    public Set<GameObject> getAll() {
        Set<GameObject> allGameObjects = new HashSet<>();
        allGameObjects.addAll(walls);
        allGameObjects.addAll(boxes);
        allGameObjects.addAll(storageLocations);
        allGameObjects.add(player);
        return allGameObjects;
    }
}
