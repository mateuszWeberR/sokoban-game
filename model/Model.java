package com.codegym.task.task34.task3410.model;

import com.codegym.task.task34.task3410.controller.EventListener;

import java.nio.file.Paths;
import java.util.Set;

public class Model {
    public static final int BOARD_CELL_SIZE = 20;
    private GameObjects gameObjects;
    private int currentLevel = 1;
    private static final String LEVELS = "C:/Users/Admin/IdeaProjects/CodeGymTasks/4.JavaCollections/src/com/codegym/task/task34/task3410/res/levels.txt";
    private LevelLoader levelLoader = new LevelLoader(Paths.get(LEVELS));
    private EventListener eventListener;

    public void setEventListener(EventListener eventListener) {
        this.eventListener = eventListener;
    }

    public GameObjects getGameObjects() {
        return gameObjects;
    }

    public void restartLevel(int level) {
        gameObjects = levelLoader.getLevel(level);
    }

    public void restart() {
        restartLevel(currentLevel);
    }

    public void startNextLevel() {
        restartLevel(++currentLevel);
    }

    public void move(Direction direction) {
        Player player = gameObjects.getPlayer();

        if (checkWallCollision(player, direction) || checkBoxCollisionAndMoveIfAvailable(direction))
            return;

        moveGameObjectToDirection(player, direction);
        checkCompletion();
    }

    public boolean checkWallCollision(CollisionObject gameObject, Direction direction) {
        for (Wall wall : gameObjects.getWalls()) {
            if (gameObject.isCollision(wall, direction))
                return true;
        }
        return false;
    }

    public boolean checkBoxCollisionAndMoveIfAvailable(Direction direction) {
        Player player = gameObjects.getPlayer();
        for (Box box : gameObjects.getBoxes()) {
            if (player.isCollision(box, direction)) {
                if (checkWallCollision(box, direction) || checkBoxToBoxCollision(box, direction)) {
                    return true;
                } else {
                    /* When a collision doesn't occur, the player pushes the box.
                     The player's location doesn't change here, because it does in
                     move(Direction) method. */
                    moveGameObjectToDirection(box, direction);
                    return false;
                }
            }
        }
        return false;
    }

    public boolean checkBoxToBoxCollision(CollisionObject thisBox, Direction direction) {
        for (Box box : gameObjects.getBoxes()) {
            if (thisBox.isCollision(box, direction))
                return true;
        }
        return false;
    }

    private void moveGameObjectToDirection(Movable gameObject, Direction direction) {
        switch (direction) {
            case UP:
                gameObject.move(0, -Model.BOARD_CELL_SIZE);
                break;
            case DOWN:
                gameObject.move(0, Model.BOARD_CELL_SIZE);
                break;
            case LEFT:
                gameObject.move(-Model.BOARD_CELL_SIZE, 0);
                break;
            case RIGHT:
                gameObject.move(Model.BOARD_CELL_SIZE, 0);
                break;
        }
    }

    public void checkCompletion() {
        Set<Box> boxes = gameObjects.getBoxes();
        Set<StorageLocation> storageLocations = gameObjects.getStorageLocations();
        int storageLocationCounter = 0;

        for (StorageLocation storageLocation : storageLocations) {
            for (Box box: boxes) {
                if (isBoxInStorage(box, storageLocation))
                    storageLocationCounter++;
            }
        }

        if (storageLocationCounter == storageLocations.size())
            eventListener.levelCompleted(currentLevel);
    }

    private boolean isBoxInStorage(Box box, StorageLocation storageLocation) {
        return box.getX() == storageLocation.getX()
                && box.getY() == storageLocation.getY();
    }
}
