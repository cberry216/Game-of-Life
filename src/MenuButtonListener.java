import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuButtonListener implements ActionListener {

    GUIDriver gui;
    byte menuItem;

    MenuButtonListener(GUIDriver gui, byte menuItem) {
        this.gui = gui;
        this.menuItem = menuItem;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch(this.menuItem){
            case (0):
                this.gui.getMap().generateRandomGame();
                break;
            case (1):
                this.gui.getMap().generatePulsar();
                break;
            case (2):
                this.gui.getMap().generateGlider();
                break;
        }
    }
}
