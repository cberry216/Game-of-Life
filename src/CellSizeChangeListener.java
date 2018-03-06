import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class CellSizeChangeListener implements ChangeListener {

    GUIDriver gui;

    CellSizeChangeListener(GUIDriver gui) {
        this.gui = gui;
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        this.gui.getMap().setCellLength(this.gui.getCellSize());
        this.gui.getMap().repaint();
    }
}
