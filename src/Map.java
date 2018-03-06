import java.util.ArrayList;
import java.util.HashMap;

/**
 * The {@link Map} class is an abstraction of 2D space by using a
 * {@link HashMap} with Keys being X-positions and the Values being
 * {@link HashMap}s, where the Keys are Y-positions and the Values are
 * {@link Box}s. This way by accessing the X-position then the Y-position,
 * you can access the {@link Box} located at that position. The {@link Map}
 * also keeps track of the {@link LivingCell}s and the {@link DeadCell}s in
 * {@link ArrayList}s. This allows iterations of only the
 * {@link LivingCell}s and the {@link DeadCell}s instead of iterating over
 * the entire {@link Map}
 *
 * @author Christopher Berry
 */
public class Map {

    private HashMap<Integer, HashMap<Integer, Box>> map;
    private ArrayList<Box> livingCells;
    private ArrayList<Box> deadCells;


    /**
     * The constructor for a Map object
     */
    Map() {
        this.map = new HashMap<>();
        this.livingCells = new ArrayList<>();
        this.deadCells = new ArrayList<>();
    }

    /**
     * Replaces the {@link LivingCell} specified with a {@link DeadCell}. The
     * number of alive neighbors of the {@link DeadCell} is set to the number
     * of alive neighbors of the {@link LivingCell}. The neighbors of the
     * replaced cell are then all decremented by 1.
     * @param positionX  x-position of the cell to replace
     * @param positionY  y-position of the cell to replace
     */
    public void livingCellDies(int positionX, int positionY) {
        // Instantiate DeadCell to replace LivingCell
        DeadCell replaceDeadCell = new DeadCell(positionX, positionY, this, true);
        // Get LivingCell to replace
        LivingCell cellToReplace = (LivingCell) getBox(positionX, positionY);
        // Set the number of alive neighbors of the DeadCell to the number of alive neighbors of the LivingCell
        replaceDeadCell.setNumberAliveNeighbors(this.getBox(positionX, positionY).getNumberAliveNeighbors());
        // Replace the LivingCell with the DeadCell
        this.map.get(positionX).replace(positionY, replaceDeadCell);
        // Remove the LivingCell from livingCells array
        this.removeFromLivingCells(cellToReplace);
        // Add the DeadCell to the deadCells array
        this.addToDeadCells(replaceDeadCell);
        // For each Box around the replaced cell, decrease the number of alive neighbors by 1
        Box[] neighbors = this.getBox(positionX, positionY).getNeighbors();
        for(Box neighbor : neighbors) {
            neighbor.decerementAliveNeighbors();
        }
    }

    /**
     * Replaces the given{@link DeadCell} or empty space with a {@link LivingCell}
     * using the {@link LivingCell}'s native addToMap() method.
     * @param positionX  x-position of the cell to replace
     * @param positionY  y-position of the cell to replace
     */
    public void livingCellBorn(int positionX, int positionY) {
        new LivingCell(positionX, positionY, this, false);
    }

    /**
     * Adds a {@link LivingCell} to the livingCells ArrayList
     * @param livingCell  the {@link LivingCell} being added to the {@link ArrayList}
     */
    public void addToLivingCells(LivingCell livingCell) {
        this.livingCells.add(livingCell);
    }

    /**
     * Removes the {@link LivingCell} specified from the  livingCells {@link ArrayList}
     * @param livingCell  the {@link LivingCell} to remove from livingCells
     */
    private void removeFromLivingCells(LivingCell livingCell) {
        this.livingCells.remove(livingCell);
    }

    /**
     * Adds a {@link DeadCell} to the deadCells ArrayList
     * @param deadCell  the {@link DeadCell} being added to the ArrayList
     */
    public void addToDeadCells(DeadCell deadCell) {
        this.deadCells.add(deadCell);
    }

    /**
     * Removes the {@link DeadCell} specified from the  deadCells {@link ArrayList}
     * @param deadCell  the {@link DeadCell} to remove from deadCells
     */
    public void removeFromDeadCells(DeadCell deadCell) {
        this.deadCells.remove(deadCell);
    }

    // Getters
    /**
     * This function returns the {@link Box} located at the given x-position
     * and the y-position
     * @param xPos  the x-position on the {@link Map} where the Box is located
     * @param yPos  the y-position on the {@link Map} where the Box is located
     * @return      {@link Box} located at (x-pos, y-pos)
     */
    public Box getBox(int xPos, int yPos) {
        return this.map.get(xPos).get(yPos);
    }

    /**
     * Gets the {@link HashMap} that abstracts the 2D space
     * @return  the {@link HashMap} that abstracts 2D space
     */
    public HashMap<Integer, HashMap<Integer, Box>> getMap() {
        return this.map;
    }

    /**
     * Returns the {@link ArrayList} that contains all {@link LivingCell}s
     * @return {@link ArrayList} that contains all the {@link LivingCell}s
     */
    public ArrayList<LivingCell> getLivingCells() {
        ArrayList<LivingCell> livingCells = new ArrayList<>();
        for(Box box : this.livingCells) {
            livingCells.add((LivingCell) box);
        }
        return livingCells;
    }

    /**
     * Returns the {@link ArrayList} that contains all {@link DeadCell}s
     * @return {@link ArrayList} that contains all the {@link DeadCell}s
     */
    public ArrayList<DeadCell> getDeadCells() {
        ArrayList<DeadCell> deadCells = new ArrayList<>();
        for(Box box : this.deadCells) {
            deadCells.add((DeadCell) box);
        }
        return deadCells;
    }

    // Setters
    public void setBox(int posX, int posY, Box box) {
        this.map.get(posX).replace(posY, box);
    }
}
