import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The {@link ActionListener} for buttons in the {@link GUIDriver}A
 */
public class MapButtonListener implements ActionListener {

    private GUIDriver gui;
    private String name;

    /**
     * The constructor for the {@link MapButtonListener} class
     * @param gui   the {@link GUIDriver} that contains the buttons to listen to
     * @param name  a specification of which button to listen to
     */
    MapButtonListener(GUIDriver gui, String name) {
        this.gui = gui;
        this.name = name;
    }

    /**
     * Each {@link MapButtonListener} listens to a different button. If the
     * button pressed is "Start", start the simulation. If the button
     * pressed if "Stop", stop the simulation. If the button press is "Reset"
     * reset the {@link GUIDriver}.
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (this.name) {
            case "Start":
                this.gui.setPlay(true);
                break;
            case "Stop":
                this.gui.setPlay(false);
                break;
            case "Reset":
                this.gui.resetGUI();
                break;
        }
    }
}
