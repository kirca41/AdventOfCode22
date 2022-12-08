package day4;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;

public class PartTwo {
    private static String path = "C:\\Data\\Kiril\\AdventOfCode22\\src\\day4\\input.txt";
    private static int numPairs = 0;
    private static int totalPairs = 0;

    public static void parseLine(String line) {
        String[] pair = line.split(",");
        int start1 = Integer.parseInt(pair[0].split("-")[0]);
        int end1 = Integer.parseInt(pair[0].split("-")[1]);
        int start2 = Integer.parseInt(pair[1].split("-")[0]);
        int end2 = Integer.parseInt(pair[1].split("-")[1]);
        if((start1 < start2 && end1 < start2) || (start1 > start2 && start1 > end2)) {
            numPairs++;
        }
    }

    public static void parseInput() throws FileNotFoundException {
        new BufferedReader(new InputStreamReader(new FileInputStream(path)))
                .lines().forEach(l -> {
                    totalPairs++;
                    parseLine(l);
                });

    }

    public static void main(String[] args) throws FileNotFoundException {
        parseInput();

        System.out.println(totalPairs - numPairs);
    }
}
