package day9;

import java.util.Objects;

public class Position {

    private int x;
    private int y;

    public Position() {
        this.x = 0;
        this.y = 0;
    }

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void moveUp() {
        this.y += 1;
    }

    public void moveDown() {
        this.y -= 1;
    }

    public void moveLeft() {
        this.x -= 1;
    }

    public void moveRight() {
        this.x += 1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return x == position.x && y == position.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}

