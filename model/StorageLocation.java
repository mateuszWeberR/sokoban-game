package com.codegym.task.task34.task3410.model;

import java.awt.*;

public class StorageLocation extends GameObject {
    public StorageLocation(int x, int y) {
        super(x, y);
        setHeight(2);
        setWidth(2);
    }

    @Override
    public void draw(Graphics graphics) {
        int dx = getX() - getWidth() / 2;
        int dy = getY() - getHeight() / 2;
        graphics.setColor(Color.RED);
        graphics.drawOval(dx, dy, getWidth(), getHeight());
    }
}
