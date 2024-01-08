package com.codegym.task.task34.task3410.model;

public abstract class CollisionObject extends GameObject {
    public CollisionObject(int x, int y) {
        super(x, y);
    }
    
    public boolean isCollision(GameObject gameObject, Direction direction) {
        if (direction == Direction.LEFT)
            return this.getX() - Model.BOARD_CELL_SIZE == gameObject.getX() &&
                    this.getY() == gameObject.getY();

        if (direction == Direction.RIGHT)
            return this.getX() + Model.BOARD_CELL_SIZE == gameObject.getX() &&
                    this.getY() == gameObject.getY();

        if (direction == Direction.UP)
            return this.getX() == gameObject.getX() &&
                    this.getY() - Model.BOARD_CELL_SIZE == gameObject.getY();

        if (direction == Direction.DOWN)
            return this.getX() == gameObject.getX() &&
                    this.getY() + Model.BOARD_CELL_SIZE == gameObject.getY();

        return false;
    }
}
