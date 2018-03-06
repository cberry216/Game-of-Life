import java.util.ArrayList;

/**
 * The {@link GameDriver} class is used as an abstraction of the a running
 * game. It allows a user to add a living cell to the current game. The
 * most important method is the step method. The step method calculates
 * which cells need to be added to the game and which cells need to be
 * removed from the game. The remaining methods are deprecated due to the
 * {@link GUIDriver}.
 */
public class GameDriver {

    Map map;

    /**
     * The constructor for the game Driver. Creates a new {@link Map} object.
     */
    GameDriver() {
        this.map = new Map();
    }

    /**
     * Adds a {@link LivingCell} to {@link GameDriver}'s {@link Map} object by
     * calling the constructor for a new {@link LivingCell} object.
     * @param posX  x-positions of the new {@link LivingCell} to add
     * @param posY  y-positions of the new {@link LivingCell} to add
     */
    void addLivingCell(int posX, int posY) {
        new LivingCell(posX, posY, this.map, false);
    }

    /**
     * Calculates which cells will be added and which cells will be removed
     * from the game. It first retrieved the {@link Map}'s {@link LivingCell}s
     * and {@link DeadCell}s. There are two additional arrays to keep track of
     * which cells will removed and which will be added. For each {@link LivingCell}
     * it checks to see if the number of living neighbors is less than 2 or
     * greater than 3; if so, it adds the {@link LivingCell} to an array called
     * toRemove. Then for each {@link DeadCell}, it checks to see if the number
     * of living neighbors is 3, if so, it adds it the array called toAdd. Then
     * for each cell in toRemove and toAdd, it removes and adds a cell, respectively.
     */
    void step() {
        ArrayList<LivingCell> livingCells = this.map.getLivingCells();
        ArrayList<DeadCell> deadCells     = this.map.getDeadCells();

        ArrayList<LivingCell> toRemove = new ArrayList<>();
        ArrayList<DeadCell> toAdd = new ArrayList<>();

        for(LivingCell livingCell : livingCells) {
            if(livingCell.getNumberAliveNeighbors() < 2 ||
               livingCell.getNumberAliveNeighbors() > 3) {
                toRemove.add(livingCell);
            }
        }
        for(DeadCell deadCell : deadCells) {
            if(deadCell.getNumberAliveNeighbors() == 3) {
                toAdd.add(deadCell);
            }
        }

        for(LivingCell livingCell : toRemove) {
            this.map.livingCellDies(livingCell.positionX, livingCell.positionY);
        }
        for(DeadCell deadCell : toAdd) {
            this.map.livingCellBorn(deadCell.positionX, deadCell.positionY);
        }
    }

    /**
     * Starts an infinite loop of stepping
     * @deprecated  use {@link GUIDriver}
     */
    @Deprecated
    void start() {
        while(true) {
            try {
                Runtime.getRuntime().exec("clear");
                printMap();
                this.step();
                Thread.sleep(1);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Prints map to console.
     * @deprecated  use {@link GUIDriver}
     */
    @Deprecated
    private void printMap() {
        int minX = getMinXValue();
        int minY = getMinYValue();
        int maxX = getMaxXValue();
        int maxY = getMaxYValue();

        int absValMinX = Math.abs(minX);
        int absValMinY = Math.abs(minY);
        boolean[][] printMap = getMap();

        for(int i = 0; i < absValMinX + maxX + 1; i++) {
            for(int j = 0; j < absValMinY + maxY + 1; j++) {
                if(printMap[i][j])
                    System.out.print("|##");
                else
                    System.out.print("|  ");
            }
            System.out.println("|");
        }
        System.out.println("_________________________");
    }

    /**
     * Returns a 2D boolean array with true indicating a {@link LivingCell} and
     * false indicating a {@link DeadCell}
     * @return      2D boolean array
     * @deprecated  use {@link GUIDriver}
     */
    @Deprecated
    private boolean[][] getMap() {
        int minX = getMinXValue();
        int minY = getMinYValue();
        int maxX = getMaxXValue();
        int maxY = getMaxYValue();

        int absValMinX = Math.abs(minX);
        int absValMinY = Math.abs(minY);

        boolean[][] gameMap = new boolean[absValMinX + maxX + 1][absValMinY + maxY + 1];
        ArrayList<LivingCell> livingCells = this.map.getLivingCells();

        for(LivingCell livingCell : livingCells) {
            gameMap[livingCell.positionX + absValMinX][livingCell.positionY + absValMinY] = true;
        }

        return gameMap;
    }

    /**
     * Gets the minimum x-value of all cells
     * @return      the smallest x-value int
     * @deprecated  use {@link GUIDriver}
     */
    @Deprecated
    private int getMinXValue() {
        ArrayList<DeadCell> deadCells = this.map.getDeadCells();

        int minX = Integer.MAX_VALUE;

        for(DeadCell deadCell : deadCells) {
            if(deadCell.positionX < minX)
                minX = deadCell.positionX;
        }

        return minX;
    }

    /**
     * Gets the minimum y-value of all cells
     * @return      the smallest y-value int
     * @deprecated  use {@link GUIDriver}
     */
    @Deprecated
    private int getMinYValue() {
        ArrayList<DeadCell> deadCells = this.map.getDeadCells();

        int minY = Integer.MAX_VALUE;

        for(DeadCell deadCell : deadCells) {
            if(deadCell.positionY < minY)
                minY = deadCell.positionY;
        }

        return minY;
    }

    /**
     * Gets the maximum x-value of all cells
     * @return      the biggest x-value int
     * @deprecated  use {@link GUIDriver}
     */
    @Deprecated
    private int getMaxXValue() {
        ArrayList<DeadCell> deadCells = this.map.getDeadCells();

        int maxX = Integer.MIN_VALUE;

        for(DeadCell deadCell : deadCells) {
            if(deadCell.positionX > maxX)
                maxX = deadCell.positionX;
        }

        return maxX;
    }

    /**
     * Gets the maximum y-value of all cells
     * @return      the biggest y-value int
     * @deprecated  use {@link GUIDriver}
     */
    @Deprecated
    private int getMaxYValue() {
        ArrayList<DeadCell> deadCells = this.map.getDeadCells();

        int maxY = Integer.MIN_VALUE;

        for(DeadCell deadCell : deadCells) {
            if(deadCell.positionY > maxY)
                maxY = deadCell.positionY;
        }

        return maxY;
    }

}
