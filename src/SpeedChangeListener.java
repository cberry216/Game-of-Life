import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * A {@link ChangeListener} that listens to the changing of the speed slider
 * in the given {@link GUIDriver}
 */
public class SpeedChangeListener implements ChangeListener {

    private GUIDriver gui;

    /**
     * The constructor for the {@link SpeedChangeListener}
     * @param gui  the {@link GUIDriver} that contains the slider to listen to
     */
    SpeedChangeListener(GUIDriver gui) {
        this.gui = gui;
    }

    /**
     * Upon state change, change the value of the play speed
     * @param e  Not used.
     */
    @Override
    public void stateChanged(ChangeEvent e) {
        gui.setSpeed(gui.getSpeedValue());
    }
}
