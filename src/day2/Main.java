package day2;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;

public class Main {

    private static int totalScore = 0;
    private static int totalScorePartTwo = 0;
    private static HashMap<String, Integer> stringToScore = new HashMap<>();
    private static HashMap<String, String> roundToPlay = new HashMap<>();
    private static HashMap<String, Integer> roundToPoints = new HashMap<>();
    private static HashMap<String, Integer> roundToPointsPartTwo = new HashMap<>();
    private static String path = "C:\\Data\\Kiril\\AdventOfCode22\\src\\day2\\input.txt";

    // A for Rock, B for Paper, and C for Scissors
    // X for Rock, Y for Paper, and Z for Scissors

    public static void init() {
        stringToScore.put("X", 1);
        stringToScore.put("Y", 2);
        stringToScore.put("Z", 3);

        roundToPoints.put("A X", 3);
        roundToPoints.put("A Y", 6);
        roundToPoints.put("A Z", 0);
        roundToPoints.put("B X", 0);
        roundToPoints.put("B Y", 3);
        roundToPoints.put("B Z", 6);
        roundToPoints.put("C X", 6);
        roundToPoints.put("C Y", 0);
        roundToPoints.put("C Z", 3);

        // X means you need to lose, Y means you need to end the round in a draw, and Z means you need to win
        roundToPlay.put("A X", "Z");
        roundToPlay.put("A Y", "X");
        roundToPlay.put("A Z", "Y");
        roundToPlay.put("B X", "X");
        roundToPlay.put("B Y", "Y");
        roundToPlay.put("B Z", "Z");
        roundToPlay.put("C X", "Y");
        roundToPlay.put("C Y", "Z");
        roundToPlay.put("C Z", "X");

        roundToPointsPartTwo.put("X", 0);
        roundToPointsPartTwo.put("Y", 3);
        roundToPointsPartTwo.put("Z", 6);
    }

    private static void calculateScore(String line) {
        String me = line.split("\\s+")[1];

        totalScore += stringToScore.get(me);

        totalScore += roundToPoints.get(line);

    }

    // part two
    // X means you need to lose, Y means you need to end the round in a draw, and Z means you need to win

    private static void calculateScorePartTwo(String line) {
        String outcome = line.split("\\s+")[1];
        String me = roundToPlay.get(line);

        totalScorePartTwo += stringToScore.get(me);

        totalScorePartTwo += roundToPointsPartTwo.get(outcome);
    }

    public static void parseInput() throws FileNotFoundException {
        new BufferedReader(new FileReader(path))
                .lines().forEach(l -> {
                    calculateScore(l);
                    calculateScorePartTwo(l);
                });

    }

    public static void main(String[] args) throws FileNotFoundException {
        init();
        parseInput();
        System.out.println(totalScore);
        System.out.println(totalScorePartTwo);
    }
}