package day9;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Objects;

public class PartOne {

    private static String path = "C:\\Data\\Kiril\\AdventOfCode22\\src\\day9\\input.txt";
    private static Position head = new Position();
    private static Position tail = new Position();
    private static HashSet<Position> visitedPositions = new HashSet<>();

//            ..H...
//            ....T.
//            ......

    public static void moveRight() {
        head.moveRight();
        if (head.getX() - tail.getX() > 1 && head.getY() != tail.getY()) {
            tail.moveRight();
            if (head.getY() > tail.getY()) {
                tail.moveUp();
            } else {
                tail.moveDown();
            }
        } else if(head.getX() - tail.getX() > 1) {
            tail.moveRight();
        }

        visitedPositions.add(new Position(tail.getX(), tail.getY()));
    }

    public static void moveLeft() {
        head.moveLeft();
        if (tail.getX() - head.getX() > 1 && head.getY() != tail.getY()) {
            tail.moveLeft();
            if (head.getY() > tail.getY()) {
                tail.moveUp();
            } else {
                tail.moveDown();
            }
        } else if(tail.getX() - head.getX() > 1) {
            tail.moveLeft();
        }

        visitedPositions.add(new Position(tail.getX(), tail.getY()));
    }

    public static void moveDown() {
        head.moveDown();
        if (tail.getY() - head.getY() > 1 && head.getX() != tail.getX()) {
            tail.moveDown();
            if (head.getX() > tail.getX()) {
                tail.moveRight();
            } else {
                tail.moveLeft();
            }
        } else if(tail.getY() - head.getY() > 1) {
            tail.moveDown();
        }

        visitedPositions.add(new Position(tail.getX(), tail.getY()));
    }

    public static void moveUp() {
        head.moveUp();
        if (head.getY() - tail.getY() > 1 && head.getX() != tail.getX()) {
            tail.moveUp();
            if (head.getX() > tail.getX()) {
                tail.moveRight();
            } else {
                tail.moveLeft();
            }
        } else if(head.getY() - tail.getY() > 1) {
            tail.moveUp();
        }

        visitedPositions.add(new Position(tail.getX(), tail.getY()));
    }

    public static void move(String line) {
        String[] parts = line.split(" ");
        int numberOfPositions = Integer.parseInt(parts[1]);
        if (parts[0].equals("U")) {
            while(numberOfPositions != 0) {
                moveUp();
                numberOfPositions--;
            }
        } else if (parts[0].equals("D")) {
            while(numberOfPositions != 0) {
                moveDown();
                numberOfPositions--;
            }
        } else if (parts[0].equals("L")) {
            while(numberOfPositions != 0) {
                moveLeft();
                numberOfPositions--;
            }
        } else {
            while(numberOfPositions != 0) {
                moveRight();
                numberOfPositions--;
            }
        }
    }

    public static void parseInput() throws FileNotFoundException {
        new BufferedReader(new InputStreamReader(new FileInputStream(path)))
                .lines().forEach(PartOne::move);
    }

    public static void main(String[] args) throws FileNotFoundException {
        parseInput();

        System.out.println(visitedPositions.size());
    }
}

class Position {

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
