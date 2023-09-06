package maze;

import java.util.*;

public class Maze {
    private final int ROWS;
    private final int COLS;
    private final Cell[][] GRID;

    public Maze(int rows, int cols) {
        this.ROWS = rows;
        this.COLS = cols;
        this.GRID = new Cell[rows][cols];
        create();
    }

    private void create() {
        initialiseGrid();

        Random random = new Random();
        Deque<Cell> uncheckedCells = new ArrayDeque<>();

        // randomly choose an entrance form left wall (not corner)
        int startIndex = random.nextInt(ROWS - 2) + 1;
        Cell currentPath = GRID[startIndex][0];
        uncheckedCells.offer(currentPath);

        while (!uncheckedCells.isEmpty()) {
            currentPath = uncheckedCells.pollLast();
            if (connectsPaths(currentPath)) {
                currentPath.setAsWall();
                continue;
            } else {
                currentPath.setPath();

                if (currentPath.getY() == COLS - 1) {
                    for (int i = 0; i < ROWS; i++) {
                        if (currentPath.getX() != i) {
                            GRID[i][COLS - 1].setAsWall();
                        }
                    }
                }
            }

            List<Cell> neighbors = cellNeighbors(currentPath);
            List<Cell> validNeighbors = new ArrayList<>();

            for (Cell neighbor : neighbors) {
                if (hasPathNear(neighbor, currentPath)) {
                    neighbor.setAsWall();
                } else if (!uncheckedCells.contains(neighbor)) {
                    uncheckedCells.offer(neighbor);
                    validNeighbors.add(neighbor);
                }
            }

            if (!validNeighbors.isEmpty()) {
                Cell randomCell = validNeighbors.get(random.nextInt(validNeighbors.size()));

                while (uncheckedCells.contains(randomCell)) {
                    uncheckedCells.remove(randomCell);
                }
                uncheckedCells.offer(randomCell);
            }
        }

    }

    private boolean hasPathNear(Cell cell, Cell excl) {
        if (excl == null) {
            return false;
        }

        List<Cell> neighbors = cellNeighbors(cell);
        neighbors.remove(excl);
        for (Cell neighbor : neighbors) {
            if (neighbor.isPath()) {
                return true;
            }
        }
        return false;
    }

    private List<Cell> cellNeighbors(Cell cell) {
        int r = cell.getX();
        int c = cell.getY();
        List<Cell> neighbors = new ArrayList<>();
        neighbors.add((getCellByPos(r - 1, c))); // upper
        neighbors.add(getCellByPos(r + 1, c));  // lower
        neighbors.add(getCellByPos(r, c - 1));  //left
        neighbors.add(getCellByPos(r, c + 1));  // right

        neighbors.removeIf(Objects::isNull);
        neighbors.removeIf(Cell::isVisited);

        return neighbors;
    }

    private boolean connectsPaths(Cell cell) {
        int r = cell.getX();
        int c = cell.getY();
        Cell upper = getCellByPos(r - 1, c);    // upper
        Cell lower = getCellByPos(r + 1, c);    // lower
        Cell left = getCellByPos(r, c - 1);    // left
        Cell right = getCellByPos(r, c + 1);    // right

        boolean horizontalConnection = left != null && right != null && left.isPath() && right.isPath();
        boolean verticalConnection = upper != null && lower != null && upper.isPath() && lower.isPath();

        return horizontalConnection || verticalConnection;
    }

    private Cell getCellByPos(int x, int y) {
        try {
            return GRID[x][y];
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }

    private void initialiseGrid() {
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                GRID[row][col] = new Cell(row, col);
                if (row == 0 || row == ROWS - 1 || col == 0 || row % 2 == 0 && col % 2 == 0) {
                    GRID[row][col].setAsWall();
                }
            }
        }
    }

    public void display() {
        System.out.println();
        for (Cell[] row : GRID) {
            for (Cell cell : row) {
                System.out.print(cell.toString());
            }
            System.out.println();
        }
        System.out.println();
    }

}