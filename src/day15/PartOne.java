package day15;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// BORINGLY SLOW SOLUTION

public class PartOne {

    private static String path = "C:\\Data\\Kiril\\AdventOfCode22\\src\\day15\\input.txt";
    private static HashMap<Position, Position> sensorToBeacon = new HashMap<>();
    private static HashMap<Integer, HashSet<Position>> invalidPositionsByRow = new HashMap<>();

    private static boolean hasBeacon(Position position) {
        return sensorToBeacon.values().stream().filter(b -> b.equals(position)).count() > 0;
    }

    public static void findInvalidPositions() {
        sensorToBeacon.entrySet().forEach(me -> {
            int dx = Math.abs(me.getKey().getX() - me.getValue().getX());
            int dy = Math.abs(me.getKey().getY() - me.getValue().getY());
            int manhattan = dx + dy;
            for (int i = 0; i <= manhattan; i++) {
                if (me.getKey().getY() + manhattan - i != 2000000 && me.getKey().getY() - manhattan + i != 2000000)
                    continue;
                if (!invalidPositionsByRow.containsKey(me.getKey().getY() + manhattan - i))
                    invalidPositionsByRow.put(me.getKey().getY() + manhattan - i, new HashSet<>());

                if (!invalidPositionsByRow.containsKey(me.getKey().getY() - manhattan + i))
                    invalidPositionsByRow.put(me.getKey().getY() - manhattan + i, new HashSet<>());

                for (int j = 0; j <= i; j++) {
                    Position p = new Position(me.getKey().getX() - j, me.getKey().getY() + manhattan - i);
                    if (!hasBeacon(p))
                        invalidPositionsByRow.get(me.getKey().getY() + manhattan - i).add(p);
                    p = new Position(me.getKey().getX() + j, me.getKey().getY() + manhattan - i);
                    if (!hasBeacon(p))
                        invalidPositionsByRow.get(me.getKey().getY() + manhattan - i).add(p);
                    p = new Position(me.getKey().getX() - j, me.getKey().getY() - manhattan + i);
                    if (!hasBeacon(p))
                        invalidPositionsByRow.get(me.getKey().getY() - manhattan + i).add(p);
                    p = new Position(me.getKey().getX() + j, me.getKey().getY() - manhattan + i);
                    if (!hasBeacon(p))
                        invalidPositionsByRow.get(me.getKey().getY() - manhattan + i).add(p);
                }
            }
        });
    }

    public static void parseInput() throws FileNotFoundException {
        new BufferedReader(new InputStreamReader(new FileInputStream(path)))
                .lines()
                .forEach(l -> {
                    Pattern pattern = Pattern.compile("[-]?\\d+");
                    Matcher matcher = pattern.matcher(l);
                    while(matcher.find()) {
                        int sx = Integer.parseInt(matcher.group());
                        matcher.find();
                        int sy = Integer.parseInt(matcher.group());
                        matcher.find();
                        int bx = Integer.parseInt(matcher.group());
                        matcher.find();
                        int by = Integer.parseInt(matcher.group());
                        sensorToBeacon.put(new Position(sx, sy), new Position(bx, by));
                    }
                });

    }

    public static void main(String[] args) throws FileNotFoundException {
        parseInput();
        findInvalidPositions();
        System.out.println(invalidPositionsByRow.get(2000000).size());
    }
}
