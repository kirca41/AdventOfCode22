package day9;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.stream.IntStream;

public class PartTwo {

    private static String path = "C:\\Data\\Kiril\\AdventOfCode22\\src\\day9\\input.txt";
    private static LinkedList<Position> head = new LinkedList<>();
    private static HashSet<Position> visitedPositions = new HashSet<>();

    private static boolean areAdjacent(Position position1, Position position2) {
        if (Math.abs(position1.getX() - position2.getX()) <= 1 && Math.abs(position1.getY() - position2.getY()) <= 1)
            return true;

        return false;
    }

    public static void moveRight() {
        head.get(0).moveRight();
        adjust();
    }

    private static void adjust() {
        for(int i = 1; i < head.size(); i++) {
            Position h = head.get(i - 1);
            Position t = head.get(i);
            if(!areAdjacent(t, h)) {
                int dx = h.getX() - t.getX();
                int dy = h.getY() - t.getY();
                if (Math.abs(dx) >= 2 && Math.abs(dy) >= 2) {
                    t.setX(t.getX() < h.getX() ? h.getX() - 1 : h.getX() + 1);
                    t.setY(t.getY() < h.getY() ? h.getY() - 1 : h.getY() + 1);
                } else if (Math.abs(dx) >= 2) {
                    t.setX(t.getX() < h.getX() ? h.getX() - 1 : h.getX() + 1);
                    t.setY(h.getY());
                } else if (Math.abs(dy) >= 2) {
                    t.setX(h.getX());
                    t.setY(t.getY() < h.getY() ? h.getY() - 1 : h.getY() + 1);
                }
            }
        }

        visitedPositions.add(new Position(head.getLast().getX(), head.getLast().getY()));
    }

    public static void moveLeft() {
        head.get(0).moveLeft();
        adjust();
    }

    public static void moveDown() {
        head.get(0).moveDown();
        adjust();
    }

    public static void moveUp() {
        head.get(0).moveUp();
        adjust();
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
        IntStream.range(0, 10).forEach(i -> head.add(i, new Position()));
        new BufferedReader(new InputStreamReader(new FileInputStream(path)))
                .lines().forEach(PartTwo::move);
    }

    public static void main(String[] args) throws FileNotFoundException {
        parseInput();

        System.out.println(visitedPositions.size());
    }
}
