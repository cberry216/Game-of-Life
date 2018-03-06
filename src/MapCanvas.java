import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * The {@link MapCanvas} is an extension of the {@link JPanel} class that
 * acts as a canvas to draw on. It first draws a grid and then will fill
 * each grid upon clicking.
 */
public class MapCanvas extends JPanel {

    private int width, height, numCols, numRows;
    int cellLength;
    ArrayList<Point> livingCells, deadCells;
    private GUIDriver gui;

    /**
     * The constructor for the {@link MapCanvas} class.
     * @param width       the width of the entire {@link MapCanvas} object
     * @param height      the height of the entire {@link MapCanvas} object
     * @param cellLength  the length, in pixels, of each grid cell
     * @param gui         the {@link GUIDriver} to contains the {@link MapCanvas}
     */
    MapCanvas(int width, int height, int cellLength, GUIDriver gui) {
        this.width = width;
        this.height = height;
        this.cellLength = cellLength;
        this.gui = gui;
        this.livingCells = new ArrayList<>();
        this.deadCells = new ArrayList<>();

        this.numCols = 0;
        this.numRows = 0;

        setPreferredSize(new Dimension(this.width, this.height));

        addMouseListener(new MapMouseListener(this));
    }

    /**
     * Resets the livingCells array effectively clearing the map.
     */
    public void resetMapCanvas() {
        this.numCols = 0;
        this.numRows = 0;
        this.livingCells.clear();
    }

    /**
     * Sets up the {@link MapCanvas}, adds all the {@link LivingCell}s from the
     * {@link MapCanvas} object to a new {@link GameDriver} object which allows
     * the {@link GUIDriver} to step through the game.
     */
    public void setupMap() {
        this.gui.setGame(new GameDriver());
        for(Point livingCell : this.livingCells) {
            this.gui.getGame().addLivingCell((int)(livingCell.getX() / this.cellLength), (int)(livingCell.getY() / this.cellLength));
        }
    }

    /**
     * Fill the board with a random amount of {@link LivingCell}s.
     */
    public void generateRandomGame() {
        this.livingCells.clear();
        for(int i = this.numCols / 10; i < this.numCols * 9 / 10; i++) {
            for(int j = this.numRows / 10; j < this.numRows * 9 / 10; j++) {
                if(Math.random() > 0.5) {
                    int xVal = i * this.cellLength;
                    int yVal = j * this.cellLength;
                    this.livingCells.add(new Point(xVal, yVal));
                }
            }
        }
        this.repaint();
    }

    /**
     * Fill the board with a Pulsar pattern
     */
    public void generatePulsar() {
        this.livingCells.clear();

        System.out.println(this.numCols);
        System.out.println(this.numRows);

        int startX = this.numCols / 2 - (this.numCols / 4);
        int startY = this.numRows / 2 - (this.numRows / 4);

        this.livingCells.add(new Point((startX + 1) * this.cellLength, (startY + 3) * this.cellLength));
        this.livingCells.add(new Point((startX + 1) * this.cellLength, (startY + 4) * this.cellLength));
        this.livingCells.add(new Point((startX + 1) * this.cellLength, (startY + 5) * this.cellLength));
        this.livingCells.add(new Point((startX + 1) * this.cellLength, (startY + 9) * this.cellLength));
        this.livingCells.add(new Point((startX + 1) * this.cellLength, (startY + 10) * this.cellLength));
        this.livingCells.add(new Point((startX + 1) * this.cellLength, (startY + 11) * this.cellLength));

        this.livingCells.add(new Point((startX + 3) * this.cellLength, (startY + 1) * this.cellLength));
        this.livingCells.add(new Point((startX + 3) * this.cellLength, (startY + 6) * this.cellLength));
        this.livingCells.add(new Point((startX + 3) * this.cellLength, (startY + 8) * this.cellLength));
        this.livingCells.add(new Point((startX + 3) * this.cellLength, (startY + 13) * this.cellLength));

        this.livingCells.add(new Point((startX + 4) * this.cellLength, (startY + 1) * this.cellLength));
        this.livingCells.add(new Point((startX + 4) * this.cellLength, (startY + 6) * this.cellLength));
        this.livingCells.add(new Point((startX + 4) * this.cellLength, (startY + 8) * this.cellLength));
        this.livingCells.add(new Point((startX + 4) * this.cellLength, (startY + 13) * this.cellLength));

        this.livingCells.add(new Point((startX + 5) * this.cellLength, (startY + 1) * this.cellLength));
        this.livingCells.add(new Point((startX + 5) * this.cellLength, (startY + 6) * this.cellLength));
        this.livingCells.add(new Point((startX + 5) * this.cellLength, (startY + 8) * this.cellLength));
        this.livingCells.add(new Point((startX + 5) * this.cellLength, (startY + 13) * this.cellLength));

        this.livingCells.add(new Point((startX + 6) * this.cellLength, (startY + 3) * this.cellLength));
        this.livingCells.add(new Point((startX + 6) * this.cellLength, (startY + 4) * this.cellLength));
        this.livingCells.add(new Point((startX + 6) * this.cellLength, (startY + 5) * this.cellLength));
        this.livingCells.add(new Point((startX + 6) * this.cellLength, (startY + 9) * this.cellLength));
        this.livingCells.add(new Point((startX + 6) * this.cellLength, (startY + 10) * this.cellLength));
        this.livingCells.add(new Point((startX + 6) * this.cellLength, (startY + 11) * this.cellLength));

        this.livingCells.add(new Point((startX + 8) * this.cellLength, (startY + 3) * this.cellLength));
        this.livingCells.add(new Point((startX + 8) * this.cellLength, (startY + 4) * this.cellLength));
        this.livingCells.add(new Point((startX + 8) * this.cellLength, (startY + 5) * this.cellLength));
        this.livingCells.add(new Point((startX + 8) * this.cellLength, (startY + 9) * this.cellLength));
        this.livingCells.add(new Point((startX + 8) * this.cellLength, (startY + 10) * this.cellLength));
        this.livingCells.add(new Point((startX + 8) * this.cellLength, (startY + 11) * this.cellLength));

        this.livingCells.add(new Point((startX + 9) * this.cellLength, (startY + 1) * this.cellLength));
        this.livingCells.add(new Point((startX + 9) * this.cellLength, (startY + 6) * this.cellLength));
        this.livingCells.add(new Point((startX + 9) * this.cellLength, (startY + 8) * this.cellLength));
        this.livingCells.add(new Point((startX + 9) * this.cellLength, (startY + 13) * this.cellLength));

        this.livingCells.add(new Point((startX + 10) * this.cellLength, (startY + 1) * this.cellLength));
        this.livingCells.add(new Point((startX + 10) * this.cellLength, (startY + 6) * this.cellLength));
        this.livingCells.add(new Point((startX + 10) * this.cellLength, (startY + 8) * this.cellLength));
        this.livingCells.add(new Point((startX + 10) * this.cellLength, (startY + 13) * this.cellLength));

        this.livingCells.add(new Point((startX + 11) * this.cellLength, (startY + 1) * this.cellLength));
        this.livingCells.add(new Point((startX + 11) * this.cellLength, (startY + 6) * this.cellLength));
        this.livingCells.add(new Point((startX + 11) * this.cellLength, (startY + 8) * this.cellLength));
        this.livingCells.add(new Point((startX + 11) * this.cellLength, (startY + 13) * this.cellLength));

        this.livingCells.add(new Point((startX + 13) * this.cellLength, (startY + 3) * this.cellLength));
        this.livingCells.add(new Point((startX + 13) * this.cellLength, (startY + 4) * this.cellLength));
        this.livingCells.add(new Point((startX + 13) * this.cellLength, (startY + 5) * this.cellLength));
        this.livingCells.add(new Point((startX + 13) * this.cellLength, (startY + 9) * this.cellLength));
        this.livingCells.add(new Point((startX + 13) * this.cellLength, (startY + 10) * this.cellLength));
        this.livingCells.add(new Point((startX + 13) * this.cellLength, (startY + 11) * this.cellLength));

        this.repaint();
    }

    /**
     * Fill the board with a Glider pattern
     */
    public void generateGlider() {
        this.livingCells.clear();
        int startX = this.numCols / 2 - (this.numCols / 4);
        int startY = this.numRows / 2 - (this.numRows / 4);

        this.livingCells.add(new Point((startX + 1) * this.cellLength, (startY + 5) * this.cellLength));
        this.livingCells.add(new Point((startX + 1) * this.cellLength, (startY + 6) * this.cellLength));
        this.livingCells.add(new Point((startX + 2) * this.cellLength, (startY + 5) * this.cellLength));
        this.livingCells.add(new Point((startX + 2) * this.cellLength, (startY + 6) * this.cellLength));

        this.livingCells.add(new Point((startX + 11) * this.cellLength, (startY + 5) * this.cellLength));
        this.livingCells.add(new Point((startX + 11) * this.cellLength, (startY + 6) * this.cellLength));
        this.livingCells.add(new Point((startX + 11) * this.cellLength, (startY + 7) * this.cellLength));
        this.livingCells.add(new Point((startX + 12) * this.cellLength, (startY + 4) * this.cellLength));
        this.livingCells.add(new Point((startX + 12) * this.cellLength, (startY + 8) * this.cellLength));
        this.livingCells.add(new Point((startX + 13) * this.cellLength, (startY + 3) * this.cellLength));
        this.livingCells.add(new Point((startX + 13) * this.cellLength, (startY + 9) * this.cellLength));
        this.livingCells.add(new Point((startX + 14) * this.cellLength, (startY + 3) * this.cellLength));
        this.livingCells.add(new Point((startX + 14) * this.cellLength, (startY + 9) * this.cellLength));

        this.livingCells.add(new Point((startX + 15) * this.cellLength, (startY + 6) * this.cellLength));

        this.livingCells.add(new Point((startX + 16) * this.cellLength, (startY + 4) * this.cellLength));
        this.livingCells.add(new Point((startX + 16) * this.cellLength, (startY + 8) * this.cellLength));
        this.livingCells.add(new Point((startX + 17) * this.cellLength, (startY + 5) * this.cellLength));
        this.livingCells.add(new Point((startX + 17) * this.cellLength, (startY + 6) * this.cellLength));
        this.livingCells.add(new Point((startX + 17) * this.cellLength, (startY + 7) * this.cellLength));
        this.livingCells.add(new Point((startX + 18) * this.cellLength, (startY + 6) * this.cellLength));

        this.livingCells.add(new Point((startX + 21) * this.cellLength, (startY + 3) * this.cellLength));
        this.livingCells.add(new Point((startX + 21) * this.cellLength, (startY + 4) * this.cellLength));
        this.livingCells.add(new Point((startX + 21) * this.cellLength, (startY + 5) * this.cellLength));
        this.livingCells.add(new Point((startX + 22) * this.cellLength, (startY + 3) * this.cellLength));
        this.livingCells.add(new Point((startX + 22) * this.cellLength, (startY + 4) * this.cellLength));
        this.livingCells.add(new Point((startX + 22) * this.cellLength, (startY + 5) * this.cellLength));

        this.livingCells.add(new Point((startX + 23) * this.cellLength, (startY + 2) * this.cellLength));
        this.livingCells.add(new Point((startX + 23) * this.cellLength, (startY + 6) * this.cellLength));

        this.livingCells.add(new Point((startX + 25) * this.cellLength, (startY + 1) * this.cellLength));
        this.livingCells.add(new Point((startX + 25) * this.cellLength, (startY + 2) * this.cellLength));
        this.livingCells.add(new Point((startX + 25) * this.cellLength, (startY + 6) * this.cellLength));
        this.livingCells.add(new Point((startX + 25) * this.cellLength, (startY + 7) * this.cellLength));

        this.livingCells.add(new Point((startX + 35) * this.cellLength, (startY + 3) * this.cellLength));
        this.livingCells.add(new Point((startX + 35) * this.cellLength, (startY + 4) * this.cellLength));
        this.livingCells.add(new Point((startX + 36) * this.cellLength, (startY + 3) * this.cellLength));
        this.livingCells.add(new Point((startX + 36) * this.cellLength, (startY + 4) * this.cellLength));


        this.repaint();
    }

    // Setters

    /**
     * Sets the cell length of the {@link MapCanvas} to the given size as long
     * as the given length is greater than 1
     * @param cellLength  the size in pixels to set the cellLength
     */
    public void setCellLength(int cellLength) {
        if (cellLength >= 2)
            this.cellLength = cellLength;
    }

    @Override
    public void paint(Graphics g) {
        this.numCols = 0;
        this.numRows = 0;

        g.setColor(Color.WHITE);
        g.fillRect(0,0,this.gui.screenSize.width, this.gui.screenSize.height);

        g.setColor(Color.BLACK);

        // Draw columns
        int currentDrawWidth = 0;
        while(currentDrawWidth < this.width) {
            g.drawLine(currentDrawWidth, 0, currentDrawWidth, this.height);
            this.numCols++;
            currentDrawWidth += this.cellLength;
        }

        // Draw rows
        int currentDrawHeight = 0;
        while(currentDrawHeight < this.height) {
            g.drawLine(0, currentDrawHeight, this.width, currentDrawHeight);
            this.numRows++;
            currentDrawHeight += this.cellLength;
        }

        // Draw living cells
        for(Point livingCell : this.livingCells) {
            g.setColor(Color.BLACK);
            g.fillRect((int) livingCell.getX(), (int) livingCell.getY(), this.cellLength, this.cellLength);
            if (this.cellLength >= 7) {
                g.setColor(Color.WHITE);
                g.drawRect((int) livingCell.getX() + 1, (int) livingCell.getY() + 1, this.cellLength - 2, this.cellLength - 2);
            }
        }
    }
}
