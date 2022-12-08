package day7;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

// not particularly proud of this one :/

public class Main {

    private static String path = "C:\\Data\\Kiril\\AdventOfCode22\\src\\day6\\input.txt";
    private static Stack<String> navigation = new Stack<>();
    private static HashMap<String, List<String>> folderContents = new HashMap<>();
    private static HashMap<String, Integer> folderSize = new HashMap<>();


    public static String getParentFolder() {
        List<String> reversed = new ArrayList<>(navigation);
        return String.join("\\", reversed);
    }

    public static void processCommand(String line) {
        if (line.startsWith("$")) {
            if(line.startsWith("$ cd ..")) {
                navigation.pop();
                updateWithSizeOfFolders(getParentFolder());
            } else if(line.startsWith("$ cd")) {
                navigation.push(line.split("\\s+")[2]);
            }
        } else {
            String parentFolder = getParentFolder();
            if(folderContents.get(parentFolder) == null) {
                folderContents.put(parentFolder, new ArrayList<>());
            }

            folderContents.get(parentFolder).add(line);

            if(!line.startsWith("dir")) {
                if(folderSize.get(parentFolder) == null) {
                    folderSize.put(parentFolder, 0);
                }

                int size = Integer.parseInt(line.split("\\s+")[0]);
                folderSize.put(parentFolder, folderSize.get(parentFolder) + size);
            }

        }

    }

    public static void updateWithSizeOfFolders(String folder) {
        List<String> remaining = new ArrayList<>();

        if(!folderContents.containsKey(folder))
            return;

        folderContents.get(folder).forEach(c -> {
            if(c.startsWith("dir")) {
                if (folderSize.containsKey(folder + "\\" + c.split("\\s+")[1])) {
                    folderSize.put(folder,
                            folderSize.getOrDefault(folder, 0)
                                    + folderSize.get(folder + "\\" + c.split("\\s+")[1]));
                } else {
                    remaining.add(c);
                }
            }
        });

        folderContents.put(folder, remaining);
    }

    public static void parseInput() throws FileNotFoundException {
        new BufferedReader(new FileReader(path))
                .lines().forEach(Main::processCommand);

    }

    public static int getSolution() {
        return folderSize.entrySet().stream().filter(e -> e.getValue() < 100000).mapToInt(e -> e.getValue())
                .sum();
    }

    public static int getSolutionPartTwo() {
        int freeSpace = 70000000 - folderSize.get("/");
        int neededSpace = 30000000 - freeSpace;
        return folderSize.entrySet().stream().filter(e -> !e.getKey().equals("/") && e.getValue() >= neededSpace)
                .mapToInt(Map.Entry::getValue).min().getAsInt();
    }

    public static void main(String[] args) throws FileNotFoundException {
        parseInput();

        while(!navigation.isEmpty()) {
            navigation.pop();
            updateWithSizeOfFolders(getParentFolder());
        }

        System.out.println(getSolution());
        System.out.println(getSolutionPartTwo());
    }
}
