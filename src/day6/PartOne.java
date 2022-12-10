package day6;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class PartOne {

    private static String path = "C:\\Data\\Kiril\\AdventOfCode22\\src\\day6\\input.txt";

    public static int processInput() throws FileNotFoundException {
        Scanner scanner = new Scanner(new InputStreamReader(new FileInputStream(path)));
        String line = scanner.next();
        char first = line.charAt(0);
        char second = line.charAt(1);
        char third = line.charAt(2);
        for (int i = 3; i <= line.length() - 4; i++) {
            char fourth = line.charAt(i);
            if (first != second && first != third && first != fourth && second != third && second != fourth && third != fourth)
                return i + 1;

            first = second;
            second = third;
            third = fourth;
        }

        return 0;
    }


    public static void main(String[] args) throws FileNotFoundException {
        System.out.println(processInput());
    }
}
