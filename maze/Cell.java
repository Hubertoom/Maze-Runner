package maze;

public class Cell {

    private static final String WALL = "\u2588\u2588";
    private static final String PATH = "  ";
    private final int x;
    private final int y;
    private boolean isPath;
    private boolean visited;

    public Cell(int x, int y) {
        this(x, y, false);
    }
    public Cell(int x, int y, boolean isPath) {
        this.x = x;
        this.y = y;
        this.isPath = isPath;
        this.visited = false;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isPath() {
        return isPath;
    }

    public void setPath() {
        this.isPath = true;
        this.visited = true;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public void setAsWall() {
        this.isPath = false;
        this.visited = true;
    }

    @Override
    public String toString() {
        return isPath() ? PATH : WALL;
    }
}