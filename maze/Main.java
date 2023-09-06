package maze;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Please, enter the size of a maze");

            int[] input = Arrays.stream(scanner.nextLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            Maze maze = new Maze(input[0], input[1]);
            maze.display();
        } catch (Exception e) {
            System.out.println("Wrong input. Put 2 integer numbers");
        }
    }
}