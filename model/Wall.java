package com.codegym.task.task34.task3410.model;

import java.awt.*;

public class Wall extends CollisionObject {
    public Wall(int x, int y) {
        super(x, y);
    }

    @Override
    public void draw(Graphics graphics) {
        int dx = getX() - (getWidth() / 2);
        int dy = getY() - (getHeight() / 2);
        graphics.setColor(new Color(165, 42, 42));
        graphics.drawRect(dx, dy, getWidth(), getHeight());
    }
}
