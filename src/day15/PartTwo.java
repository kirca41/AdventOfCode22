package day15;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// for each position in the search space
// check every sensor and compute if its distance to that sensor is <= than the
// manhattan distance to that sensor's closest beacon.

public class PartTwo {

    private static String path = "C:\\Data\\Kiril\\AdventOfCode22\\src\\day15\\input.txt";
    private static HashMap<Position, Position> sensorToBeacon = new HashMap<>();
    private static HashMap<Integer, HashSet<Position>> invalidPositionsByRow = new HashMap<>();

    public static Position findDistressBeacon() {
        for (int i = 0; i < 4000000; i++) {
            for (int j = 0; j < 4000000; j++) {
                Position p = new Position(i, j);
                boolean flag = false;
                int dx = 0;
                int dy = 0;
                for (Map.Entry<Position,Position> entry :sensorToBeacon.entrySet()) {
                    int manhattan1 = Math.abs(p.getX() - entry.getKey().getX()) + Math.abs(p.getY() - entry.getKey().getY());
                    int manhattan2 = Math.abs(entry.getKey().getX() - entry.getValue().getX()) + Math.abs(entry.getKey().getY() - entry.getValue().getY());

                    if (manhattan1 <= manhattan2) {
                        if (p.getX() == entry.getKey().getX() && manhattan1 > dx)
                            dx = manhattan1;

                        if (p.getY() == entry.getKey().getY() && manhattan1 > dy) {
                            dy = manhattan1;
                        }

                        flag = true;
                        break;
                    }
                }

                if (!flag)
                    return p;

                i += dx;
                j += dy;
            }
        }

        return null;
    }

    public static void printAreaEndpoints() {
        sensorToBeacon.entrySet().forEach(me -> {
            int dx = Math.abs(me.getKey().getX() - me.getValue().getX());
            int dy = Math.abs(me.getKey().getY() - me.getValue().getY());
            Position sensor = me.getKey();
            int manhattan = dx + dy;
            System.out.printf("%d,%d,%d,%d,%d,%d,%d,%d\n",
                    sensor.getX() - manhattan, sensor.getY(),
                    sensor.getX(), sensor.getY() + manhattan,
                    sensor.getX() + manhattan, sensor.getY(),
                    sensor.getX(), sensor.getY() - manhattan);
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
//        Position beacon = findDistressBeacon();
//        System.out.printf("%d %d", beacon.getX(), beacon.getY());
        printAreaEndpoints();
    }
}
