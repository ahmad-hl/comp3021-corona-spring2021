package pa1;


/**
 * An immutable class that represents a cell in the grid map
 */
// TODO
public class Cell {

    private final int x, y;

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Cell) {
            Cell c = (Cell) obj;
            return c.getX() == getX() && c.getY() == getY();
        }

        return false;
    }
}
