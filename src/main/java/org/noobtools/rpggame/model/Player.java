package org.noobtools.rpggame.model;

public class Player {
    private int x;
    private int y;
    private int health;

    public Player(int startX, int startY) {
        this.x = startX;
        this.y = startY;
        this.health = 10; // starthälsa
    }

    // Getters
    public int getX() { return x; }
    public int getY() { return y; }
    public int getHealth() { return health; }

    // Movement med väggkontroll
    public void moveUp(char[][] map) {
        if (y > 0 && map[y - 1][x] != '#') y--;
    }

    public void moveDown(char[][] map) {
        if (y < map.length - 1 && map[y + 1][x] != '#') y++;
    }

    public void moveLeft(char[][] map) {
        if (x > 0 && map[y][x - 1] != '#') x--;
    }

    public void moveRight(char[][] map) {
        if (x < map[0].length - 1 && map[y][x + 1] != '#') x++;
    }

    // Damage
    public void takeDamage(int amount) {
        health -= amount;
        if (health < 0) health = 0;
    }
}
