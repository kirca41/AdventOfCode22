package day4;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;

public class PartOne {

    private static String path = "C:\\Data\\Kiril\\AdventOfCode22\\src\\day4\\input.txt";
    private static int numPairs = 0;

    public static void parseLine(String line) {
        String[] pair = line.split(",");
        int start1 = Integer.parseInt(pair[0].split("-")[0]);
        int end1 = Integer.parseInt(pair[0].split("-")[1]);
        int start2 = Integer.parseInt(pair[1].split("-")[0]);
        int end2 = Integer.parseInt(pair[1].split("-")[1]);

        if((start1 <= start2 && end1 >= end2) || (start2 <= start1 && end2 >= end1))
            numPairs++;
    }

    public static void parseInput() throws FileNotFoundException {
        new BufferedReader(new InputStreamReader(new FileInputStream(path)))
                .lines().forEach(PartOne::parseLine);

    }

    public static void main(String[] args) throws FileNotFoundException {
        parseInput();

        System.out.println(numPairs);
    }
}
