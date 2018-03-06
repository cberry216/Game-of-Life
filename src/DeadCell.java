import java.util.HashMap;


/**
 * The {@link DeadCell} class is used to simulate a dead cell within the
 * {@link Map}. A {@link DeadCell} is not an empty cell; this is to allow
 * a way to interate over the {@link DeadCell}s without have to iterate
 * over the entire {@link Map}. A {@link DeadCell} keeps track of the
 * number of alive neighbors it has. Upon instantiation, {@link DeadCell}
 * has 1 alive neighbor since they are only created upon a
 * {@link LivingCell}'s creation. When a {@link LivingCell} adds the
 * {@link DeadCell}s to the {@link Map}, if there is already a dead cell
 * where a new {@link DeadCell} is being added, it will increment the
 * number of alive neighbors it has. After being added to the {@link Map}
 * it adds itself to the {@link Map}s deadCells {@link java.util.ArrayList}
 * in order to iterate over the {@link DeadCell}s without having to iterate
 * over the entire {@link Map}.
 *
 * @author Christopher Berry
 */
public class DeadCell extends Box {

    /**
     * The constructor for a {@link DeadCell} object. If replace is true, it will
     * not add itself to the given map. Replace is only used if you are converting
     * a {@link DeadCell} into a {@link LivingCell}.
     * @param positionX  x-position of the {@link DeadCell}
     * @param positionY  y-position of the {@link DeadCell}
     * @param map        the {@link Map} to add {@link DeadCell} to
     * @param replace    if true, won't add the {@link DeadCell} to the map
     */
    DeadCell(int positionX, int positionY, Map map, boolean replace) {
        super(positionX, positionY, map, replace);
        this.alive = false;
        if(!replace) {
            this.numberAliveNeighbors = 1;      // initially one since it is only added when a LivingCell is created
            this.addToMap();
        }
    }

    /**
     * Checks to see of the given {@link Object} is the same as the {@link DeadCell}
     * object calling equals. It first checks to make sure the objects are the same
     * type then checks to see if their positions are the same and if both are alive.
     * It does not check for the same map; this allows for debugging.
     * @param o  Object to equate
     * @return   boolean telling if both objects are equal.
     */
    @Override
    public boolean equals(Object o) {
        try {
            DeadCell dcell = (DeadCell)o;
            return (dcell.positionX == this.positionX &&
                    dcell.positionY == this.positionY &&
                    dcell.alive == this.alive);
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
     * Adds this {@link DeadCell} to the {@link Map} passed in the constructor.
     * It first checks to see if there is an equivalent x-po in the first HashMap
     * If there isn't, it adds the x-pos and a new HashMap to the HashMap, it then adds
     * the y-pos and the current {@link LivingCell}. If there is an x-pos in the
     * HashMap, it then gets the corresponding HashMap and checks to see if there
     * is a corresponding y-pos in the HashMap. If not, it will put the y-pos and
     * the current {@link DeadCell} in the second HashMap. If there is a
     * corresponding y-pos, since a {@link DeadCell} can never overwrite another
     * {@link DeadCell} or {@link LivingCell}, it simply increments the number of
     * alive neighbors the retrieved {@link Box} has.
     */
    @Override
    public void addToMap() {
        HashMap<Integer, HashMap<Integer, Box>> map = this.map.getMap();
        if(map.containsKey(this.positionX)) {
            if(map.get(this.positionX).containsKey(this.positionY)) {
                //System.out.println("Incrementing (" + this.positionX + ", " + this.positionY + ") by 1");
                map.get(this.positionX).get(this.positionY).incrementAliveNeighbors();
            }
            else {
                map.get(this.positionX).put(this.positionY, this);
                this.map.addToDeadCells(this);
            }
        }
        else {
            map.put(this.positionX, new HashMap<>());
            map.get(this.positionX).put(this.positionY, this);
            this.map.addToDeadCells(this);
        }
    }
}
