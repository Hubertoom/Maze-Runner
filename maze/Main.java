package maze;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int[][] maze = new int[][]{
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                {1, 0, 0, 0, 0, 0, 0, 0, 1, 1},
                {0, 0, 1, 1, 1, 0, 1, 1, 0, 0},
                {1, 1, 0, 0, 0, 0, 1, 1, 0, 1},
                {1, 1, 0, 1, 1, 0, 0, 0, 0, 1},
                {1, 0, 0, 0, 0, 0, 1, 0, 0, 1},
                {1, 0, 1, 0, 1, 0, 1, 0, 1, 1},
                {1, 0, 1, 0, 1, 0, 0, 0, 1, 1},
                {1, 0, 0, 1, 0, 0, 1, 0, 0, 1},
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
        };

        displayMaze(maze);
    }

    private static void displayMaze(int[][] maze) {
        Arrays.stream(maze)
                .forEach(array -> {
                    System.out.println();
                    Arrays.stream(array)
                            .forEach(cell -> System.out.print(
                                    cell == 1 ? "\u2588\u2588" : "  "));
                });
    }

}