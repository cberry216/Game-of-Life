/**
 * The main class which runs the application.
 */
public class GameOfLife {

    public static void main(String[] args) {
        GUIDriver gui = new GUIDriver();
        gui.setVisible(true);
        while(true) {
            if(gui.getPlay()) {
                if(!gui.getStarted()) {
                    gui.startSimulation();
                }
                else
                    gui.getMap().setupMap();
                gui.continueSimulation();
            }
            try {
                Thread.sleep(100);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
