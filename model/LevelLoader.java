package com.codegym.task.task34.task3410.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class LevelLoader {
    private Path levels;

    public LevelLoader(Path levels) {
        this.levels = levels;
    }

    public GameObjects getLevel(int level) {
        while (level > 60)
            level -= 60;

        GameObjects gameObjects = null;
        String maze = "Maze: " + level;

        try {
            BufferedReader reader = new BufferedReader(new FileReader(String.valueOf(levels)));
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.equals(maze)) {
                    List<String> currentMaze = readCurrentMaze(reader);
                    gameObjects = createGameObjectsToCurrentLevel(currentMaze);
                    break;
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return gameObjects;
    }

    private void printCurrentMaze(List<String> currentMaze) {
        for (String line : currentMaze) {
            System.out.println(line);
        }
    }

    private List<String> readCurrentMaze(BufferedReader reader) throws IOException {
        List<String> currentMaze = new LinkedList<>();

        // Ignore five first line with maze info and one empty line.
        for (int i = 0; i < 6; i++) {
            reader.readLine();
        }

        String line;
        while (!(line = reader.readLine()).equals("*************************************")) {
            currentMaze.add(line);
        }

        // Removing last empty line.
        currentMaze.remove(currentMaze.size() - 1);

        return currentMaze;
    }

    private GameObjects createGameObjectsToCurrentLevel(List<String> currentMaze) {
        Set<Wall> walls = new HashSet<>();
        Set<Box> boxes = new HashSet<>();
        Set<StorageLocation> storageLocations = new HashSet<>();
        Player player = null;

        for (int y = 0; y < currentMaze.size(); y++) {
            char[] lineInChar = currentMaze.get(y).toCharArray();
            for (int x = 0; x < lineInChar.length; x++) {
                if (lineInChar[x] == '@') {
                    int playerX = calculatePoint(x);
                    int playerY = calculatePoint(y);
                    player = new Player(playerX, playerY);
                } else {
                    addGameObject(lineInChar[x], x, y, boxes, storageLocations, walls);
                }
            }

        }
        return new GameObjects(walls, boxes, storageLocations, player);
    }

    private void addGameObject(char c, int x, int y,
                               Set<Box> boxes, Set<StorageLocation> storageLocations,
                               Set<Wall> walls) {

        x = calculatePoint(x);
        y = calculatePoint(y);

        switch(c) {
            case 'X':
                walls.add(new Wall(x, y));
                break;
            case '*':
                boxes.add(new Box(x, y));
                break;
            case '.':
               storageLocations.add(new StorageLocation(x, y));
               break;
            case '&':
                storageLocations.add(new StorageLocation(x, y));
                boxes.add(new Box(x, y));
                break;
        }
    }

    private int calculatePoint(int num) {
        return 10 + num * Model.BOARD_CELL_SIZE;
    }

    public static void main(String[] args) {
        LevelLoader levelLoader = new LevelLoader(Paths.get
                ("C:/Users/Admin/IdeaProjects/CodeGymTasks/4.JavaCollections/src/com/codegym/task/task34/task3410/res/levels.txt"));

//        levelLoader.getLevel(123);
    }
}






// Box box = new Box(200, 200);
//        Player player = new Player(100, 200);
//        StorageLocation storageLocation = new StorageLocation(300,300);
//        Wall wall = new Wall(60,60);
//        Wall wall1 = new Wall(80,60);
//        Wall wall2 = new Wall(100,60);
//
//        Set<Box> boxes = new HashSet<>();
//        Set<StorageLocation> locations = new HashSet<>();
//        Set<Wall> walls = new HashSet<>();
//
//        boxes.add(box);
//        locations.add(storageLocation);
//        walls.add(wall);
//        walls.add(wall1);
//        walls.add(wall2);
// GameObjects gameObjects = new GameObjects(walls, boxes, locations, player);
