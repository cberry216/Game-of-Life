import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * This class runs the GUI and all functions of the GUI. It extends the
 * {@link JFrame} class. The {@link GUIDriver} sets the size of the
 * application window to the size of the screen and adds all of the
 * corresponding components to the {@link GUIDriver}. It then adds all of
 * the listeners to the corresponding objects
 */
public class GUIDriver extends JFrame {

    Dimension screenSize;

    private MapCanvas map;
    private JButton start;
    private JButton stop;
    private JButton reset;
    private JLabel speedLabel;
    private JSlider speed;
    private JLabel cellSizeLabel;
    private JSpinner cellSize;

    private JMenuBar menuBar;
    private JMenuItem fileMenu;
    private JMenu gameMenu;
    private JMenuItem exitGame;
    private JMenuItem random;
    private JMenuItem pulsar;
    private JMenuItem glider;

    private GameDriver game;
    private int playSpeed;
    private boolean play;
    private boolean started;

    GUIDriver() {
        // Set the size of the JFrame to the size of the screen.
        this.screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setPreferredSize(this.screenSize);
        setSize(this.screenSize);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);

        // Add all components
        this.game = new GameDriver();
        this.map = new MapCanvas(this.screenSize.width - 220, this.screenSize.height - 220, 10, this);
        this.start = new JButton("Start");
        this.stop = new JButton("Stop");
        this.reset = new JButton("Reset");
        this.speedLabel = new JLabel("Speed", SwingConstants.CENTER);
        this.speed = new JSlider(1,10,1);
        this.cellSizeLabel = new JLabel("Cell Size", SwingConstants.CENTER);
        SpinnerNumberModel snm = new SpinnerNumberModel(10, 2, 100, 1);
        this.cellSize = new JSpinner(snm);

        this.menuBar = new JMenuBar();
        this.gameMenu = new JMenu("Game");
        this.fileMenu = new JMenu("File");
        this.exitGame = new JMenuItem("Exit");
        this.random = new JMenuItem("Random Game");
        this.pulsar = new JMenuItem("Pulsar");
        this.glider = new JMenuItem("Glider Gun");

        this.play = false;
        this.playSpeed = 1;

        // True if the game has already been started, reset upon Reseting
        this.started = false;

        this.addListeners();
        this.initJFrame();
    }

    /**
     * Add the corresponding listeners to Start, Stop, Reset, and Apply buttons
     * and the Speed slider.
     */
    private void addListeners() {
        this.start.addActionListener(new MapButtonListener(this, "Start"));
        this.stop.addActionListener(new MapButtonListener(this, "Stop"));
        this.reset.addActionListener(new MapButtonListener(this, "Reset"));

        this.speed.addChangeListener(new SpeedChangeListener(this));

        this.cellSize.addChangeListener(new CellSizeChangeListener(this));

        this.exitGame.addActionListener(new MenuButtonListener(this, (byte)0));
        this.random.addActionListener(new MenuButtonListener(this, (byte)1));
        this.pulsar.addActionListener(new MenuButtonListener(this, (byte)2));
        this.glider.addActionListener(new MenuButtonListener(this, (byte)3));
    }

    /**
     * Adds each component to the {@link GUIDriver}. Sets the layout of the
     * {@link GUIDriver} to {@link GridBagLayout}. For each component added, a
     * {@link GridBagConstraints} is either created or reset to a new one. The
     * parameters is set inside each {@link GridBagConstraints} before being
     * added to the {@link GUIDriver}.
     */
    private void initJFrame() {
        setLayout(new GridBagLayout());

        setPreferredSize(this.screenSize);

        Dimension buttonSize = new Dimension(200, 130);

        this.fileMenu.add(this.exitGame);

        this.gameMenu.add(this.random);
        this.gameMenu.add(this.pulsar);
        this.gameMenu.add(this.glider);

        this.menuBar.add(this.fileMenu);
        this.menuBar.add(this.gameMenu);

        setJMenuBar(this.menuBar);

        this.start.setPreferredSize(buttonSize);
        this.stop.setPreferredSize(buttonSize);
        this.reset.setPreferredSize(buttonSize);

        this.speedLabel.setPreferredSize(new Dimension(200, 20));
        this.speed.setPreferredSize(new Dimension(200, 60));
        this.speed.setPaintTicks(true);
        this.speed.setPaintLabels(true);
        this.speed.setPaintTrack(true);
        this.speed.setSnapToTicks(true);
        this.speed.setMajorTickSpacing(1);

        this.cellSizeLabel.setPreferredSize(new Dimension(200, 20));
        this.cellSize.setPreferredSize(new Dimension(200, 20));


        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(3,3,3,3);
        add(this.start, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(3,3,3,3);
        add(this.stop, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(3,3,3,3);
        add(this.reset, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.ipady = 25;
        gbc.insets = new Insets(3,3,0,3);
        add(this.speedLabel, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.ipady = 25;
        gbc.insets = new Insets(0,3,3,3);
        add(this.speed, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(3,3,0,3);
        add(this.cellSizeLabel, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(0,3,3,3);
        add(this.cellSize, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 6;
        gbc.gridheight = 7;
        add(this.map, gbc);
    }

    /**
     * Takes one step in the simulation. It gets the {@link LivingCell}s,
     * clears the {@link MapCanvas}'s {@link LivingCell}s and changes it to the
     * {@link GameDriver}s {@link LivingCell}s. It repaints the {@link MapCanvas}
     * then waits for a period of time dependent on the speed value of the speed
     * slider.
     */
    private void stepSimulation() {
        this.game.step();
        ArrayList<LivingCell> stepLivingCells = this.game.map.getLivingCells();
        this.map.livingCells.clear();
        for (LivingCell livingCell : stepLivingCells) {
            this.map.livingCells.add(new Point(livingCell.positionX * this.map.cellLength, livingCell.positionY * this.map.cellLength));
        }
        this.map.repaint();
        try {
            Thread.sleep(500 / this.playSpeed);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Enters an infinite loop to contstantly step through the simulation while
     * the play member is true. The play member can only be changed by the Stop
     * button.
     */
    void startSimulation() {
        this.play = true;
        this.started = true;
        this.map.setupMap();
        this.playSpeed = getSpeedValue();
        while (this.play) {
            stepSimulation();
        }
    }

    /**
     * Instead of starting a new simulation, just continues where the previous
     * simulation left off.
     */
    void continueSimulation() {
        this.playSpeed = getSpeedValue();
        while(this.play) {
            stepSimulation();
        }
    }

    /**
     * Resets the {@link MapCanvas} and sets the game to a new {@link GameDriver}.
     * Also sets the started member to false, which states that a new simulation
     * has not been started yet before repainting the map.
     */
    public void resetGUI() {
        this.map.resetMapCanvas();
        this.game = new GameDriver();
        this.started = false;
        this.map.repaint();
        this.play = false;
    }

    // Getter

    /**
     * Gets the speed value of the speed slider
     * @return  the value of the speed slider
     */
    public int getSpeedValue() {
        return this.speed.getValue();
    }

    /**
     * Gets the cell size value from spinner
     * @return  size of the cell in pixels
     */
    public int getCellSize() {
        return (int)this.cellSize.getValue();
    }

    /**
     * Gets the {@link MapCanvas} associated with this {@link GUIDriver}
     * @return  the {@link MapCanvas} of this {@link GUIDriver}
     */
    public MapCanvas getMap() {
        return this.map;
    }

    /**
     * Gets the current {@link GUIDriver}'s {@link GameDriver}
     * @return  the current {@link GameDriver}
     */
    public GameDriver getGame() {
        return game;
    }

    /**
     * Gets the play value
     * @return  returns true if the game is currently playing
     */
    public boolean getPlay() {
        return this.play;
    }

    /**
     * Gets the started value
     * @return  returns true if the game has already started
     */
    public boolean getStarted() {
        return this.started;
    }

    // Setter

    /**
     * Sets the value of play
     * @param set  the boolean to set play to
     */
    public void setPlay(boolean set) {
        this.play = set;
    }

    /**
     * Sets the value of the speed member
     * @param speed  value to set speed to
     */
    public void setSpeed(int speed) {
        this.playSpeed = speed;
    }

    /**
     * Sets the current {@link GameDriver} to an existing {@link GameDriver}
     * @param game  the {@link GameDriver} to set the game member to
     */
    public void setGame(GameDriver game) {
        this.game = game;
    }
}
