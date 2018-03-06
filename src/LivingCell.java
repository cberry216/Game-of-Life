import java.util.HashMap;

/**
 * The {@link LivingCell} class simulates a living cell within the map.
 * When a {@link LivingCell} is instantiated it adds itself to the map. It
 * then adds itself the the {@link Map}'s living cells
 * {@link java.util.ArrayList}. After that, the {@link LivingCell} adds 8
 * {@link DeadCell}s around itself so that Each {@link LivingCell} is
 * surrounded by at least 8 {@link DeadCell}s. The {@link LivingCell} class
 * keeps track of the number of alive neighbors
 *
 * @author Christopher Berry
 */
public class LivingCell extends Box {

    /**
     * The constructor for a {@link LivingCell} object. If replace is true, it
     * will not add itself to the given map. Replace is only used if you are
     * converting a {@link LivingCell} to a {@link DeadCell}.
     * @param positionX  the x-position of the {@link LivingCell}
     * @param positionY  the y-position of the {@link LivingCell}
     * @param map        the {@link Map} to add {@link LivingCell} to
     * @param replace    if true, won't add the {@link LivingCell} to the map
     */
    LivingCell(int positionX, int positionY, Map map, boolean replace) {
        super(positionX, positionY, map, replace);
        this.alive = true;
        if(!replace) {
            this.addToMap();
            this.numberAliveNeighbors = 0;
            new DeadCell(this.positionX - 1, this.positionY - 1, this.map, false);
            new DeadCell(this.positionX, this.positionY - 1, this.map, false);
            new DeadCell(this.positionX + 1, this.positionY - 1, this.map, false);
            new DeadCell(this.positionX - 1, this.positionY, this.map, false);
            new DeadCell(this.positionX + 1, this.positionY, this.map, false);
            new DeadCell(this.positionX - 1, this.positionY + 1, this.map, false);
            new DeadCell(this.positionX, this.positionY + 1, this.map, false);
            new DeadCell(this.positionX + 1, this.positionY + 1, this.map, false);
            int checkNumberAliveNeighbors = 0;
            Box[] neighbors = this.getNeighbors();
            for(Box neighbor : neighbors) {
                if(neighbor.isAlive())
                    checkNumberAliveNeighbors++;
            }
            this.setNumberAliveNeighbors(checkNumberAliveNeighbors);
        }
    }

    /**
     * Returns the actual map of the {@link Map} object passed to constructor
     * @return  map of the {@link Map} object
     */
    private HashMap<Integer, HashMap<Integer, Box>> getMap() {
        return this.map.getMap();
    }

    /**
     * Checks to see of the given {@link Object} is the same as the {@link LivingCell}
     * object calling equals. It first checks to make sure the objects are the same
     * type then checks to see if their positions are the same and if both are alive.
     * It does not check for the same map; this allows for debugging.
     * @param o  Object to equate
     * @return   boolean telling if both objects are equal.
     */
    @Override
    public boolean equals(Object o) {
        try {
            LivingCell lcell = (LivingCell)o;
            return (lcell.positionX == this.positionX &&
                    lcell.positionY == this.positionY &&
                    lcell.alive == this.alive);
        }
        catch (IllegalArgumentException e) {
            return false;
        }
    }

    @Override
    public boolean isAlive() {
        return this.alive;
    }

    /**
     * Adds this {@link LivingCell} to the {@link Map} passed in the constructor.
     * It first checks to see if there is an equivalent x-pos in the first HashMap.
     * If there isn't it adds the x-pos and new a HashMap to the HashMap, then it adds
     * the y-pos and the current {@link LivingCell}. If there is an x-pos in the
     * HashMap, it then gets the corresponding HashMap and checks to see if there
     * is a corresponding y-pos in the HashMap. If not, it will put the y-pos and
     * the current {@link LivingCell} in the second HashMap. If there is a
     * corresponding y-pos, it checks to see if it is dead. Only {@link LivingCell}s
     * can overwrite {@link DeadCell}s so if the cell is dead, it copies it's
     * number of alive neighbors over to itself, removes the {@link DeadCell} from
     * the {@link Map}'s deadCells array. After all additions to the HashMap, the
     * {@link LivingCell} adds itselfs to the {@link Map}'s livingCells array.
     */
    @Override
    public void addToMap() {
        HashMap<Integer, HashMap<Integer, Box>> map = this.getMap();
        if(map.containsKey(this.positionX)) {
            if(map.get(this.positionX).containsKey(this.positionY)) {
                if(!map.get(this.positionX).get(this.positionY).isAlive()) {
                    DeadCell toRemove = (DeadCell)map.get(this.positionX).get(this.positionY);
                    this.map.removeFromDeadCells(toRemove);
                    this.map.addToLivingCells(this);
                    this.map.setBox(toRemove.positionX, toRemove.positionY, this);
                }
            }
            else {
                map.get(this.positionX).put(this.positionY, this);
                this.map.addToLivingCells(this);
            }
        }
        else {
            map.put(this.positionX, new HashMap<>());
            map.get(this.positionX).put(this.positionY, this);
            this.map.addToLivingCells(this);
        }
    }
}
