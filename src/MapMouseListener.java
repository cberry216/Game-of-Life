import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * The {@link MouseAdapter} the listens to the clicking of the
 * {@link MapCanvas}.
 */
public class MapMouseListener extends MouseAdapter {

    private MapCanvas mapCanvas;

    /**
     * The constructor for the {@link MapMouseListener} class
     * @param mapCanvas  the {@link MapCanvas} to listen to
     */
    MapMouseListener(MapCanvas mapCanvas) {
        super();
        this.mapCanvas = mapCanvas;
    }

    /**
     * Upon mouse click, add the clicked cell to the {@link MapCanvas}'s
     * livingCells array. If the user clicks on a cell that is already clicked,
     * it will remove the cell from the {@link MapCanvas}'s livingCells. It
     * then repaints the {@link MapCanvas}.
     * @param me
     */
    @Override
    public void mousePressed(MouseEvent me) {
        int xVal = (me.getX() / this.mapCanvas.cellLength) * this.mapCanvas.cellLength;
        int yVal = (me.getY() / this.mapCanvas.cellLength) * this.mapCanvas.cellLength;
        Point toAdd = new Point(xVal, yVal);
        if(this.mapCanvas.livingCells.contains(toAdd)) {
            this.mapCanvas.livingCells.remove(toAdd);
        }
        else {
            this.mapCanvas.livingCells.add(new Point(xVal, yVal));
        }
        this.mapCanvas.repaint();
    }
}
