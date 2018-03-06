/**
 * The {@link Box} class is an abstract class to simulate a single cell
 * within a the entire {@link Map}. A {@link Box} object has an x- and
 * y-position along with the {@link Map} that it exists in. The
 * {@link LivingCell} and {@link DeadCell} inherit from this class.
 *
 * @author Christopher Berry
 */
public abstract class Box {

    int positionX;
    int positionY;
    Map map;
    int numberAliveNeighbors;
    boolean alive;
    boolean replace;

    /**
     * The constructor for a {@link Box} class
     * @param positionX  the x-position of the {@link Box} to add
     * @param positionY  the y-position of the {@link Box} to add
     * @param map        the {@link Map} to add {@link Box} to
     * @param replace    if true, will act as a replacement and will not add it to the map
     */
    Box(int positionX, int positionY, Map map, boolean replace) {
        this.positionX = positionX;
        this.positionY = positionY;
        this.map       = map;
        this.replace   = replace;
    }

    /**
     * Gets the 8 neighbors of {@link Box} and returns them in array format
     * where the 1st index is the top-left neighbor, 2nd index is the top-
     * middle index, and so on.
     * @return  array containing the neighbors of the {@link Box}
     */
    public Box[] getNeighbors() {
        return new Box[]{this.map.getBox(this.positionX - 1, this.positionY - 1),
                           this.map.getBox(this.positionX, this.positionY - 1),
                           this.map.getBox(this.positionX + 1, this.positionY - 1),
                           this.map.getBox(this.positionX - 1, this.positionY),
                           this.map.getBox(this.positionX + 1, this.positionY),
                           this.map.getBox(this.positionX - 1, this.positionY + 1),
                           this.map.getBox(this.positionX, this.positionY + 1),
                           this.map.getBox(this.positionX + 1, this.positionY + 1)};
    }

    /**
     * Increments the number of alive neighbors a {@link Box} has by 1
     */
    public void incrementAliveNeighbors(){
        this.numberAliveNeighbors++;
    }

    /**
     * Decrements the number of alive neighbors a {@link Box} has by 1
     */
    public void decerementAliveNeighbors() {
        this.numberAliveNeighbors--;
    }

    /**
     * Returns the number of alive neighbors
     * @return  the number of alive neighbors
     */
    public int getNumberAliveNeighbors() {
        return this.numberAliveNeighbors;
    }

    /**
     * Sets the number of alive neighbors to the specified value
     * @param n  the value to change numberAliveNeighbors to
     */
    public void setNumberAliveNeighbors(int n) {
        this.numberAliveNeighbors = n;
    }

    /**
     * Checks to see whether this {@link Box} is alive.
     * @return  boolean, true if {@link Box} is alive, false otherwise
     */
    public abstract boolean isAlive();

    public abstract void addToMap();
}
