package day10;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.List;

public class PartTwo {

    private static String path = "C:\\Data\\Kiril\\AdventOfCode22\\src\\day10\\input.txt";
    private static int cycle = 1;
    private static int X = 1;
    private static HashSet<Integer> checkpoints = new HashSet<>();
    private static int sum = 0;
    private static char[][] crt = new char[6][40];

    public static void init() {
        checkpoints.addAll(List.of(20, 60, 100, 140, 180, 220));
    }

    private static boolean overlappingWithSprite(int j) {
        return j == X || j == X - 1 || j == X + 1;
    }

    private static void draw() {
        int i = (cycle - 1) / 40;
        int j = (cycle - 1) % 40;
        crt[i][j] =  overlappingWithSprite(j) ? '#' : '.';
    }

    private static void noop() {
        draw();
        if (checkpoints.contains(cycle))
            sum += cycle * X;

        cycle += 1;
    }

    private static void addx(int value) {
        draw();
        if (checkpoints.contains(cycle))
            sum += cycle * X;

        cycle += 1;

        draw();
        if (checkpoints.contains(cycle))
            sum += cycle * X;

        cycle += 1;
        X += value;
    }

    public static void parseInput() throws FileNotFoundException {
        new BufferedReader(new InputStreamReader(new FileInputStream(path)))
                .lines().forEach(l -> {
                    if (l.startsWith("noop"))
                        noop();
                    else
                        addx(Integer.parseInt(l.split("\\s+")[1]));
                });
    }

    public static void printCRT() {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 40; j++) {
                System.out.print(crt[i][j]);
            }
            System.out.println("");
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        init();
        parseInput();

        printCRT();
    }
}
