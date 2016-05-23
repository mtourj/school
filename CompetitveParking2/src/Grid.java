
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

public class Grid {

    private GridBlock[][] grid;

    PlayerVehicle player1, player2;

    Timer timer;

    int round;

    ActionListener timerListener;

    GUI mainGUI;

    boolean newGameQueued;

    public Grid(PlayerVehicle v1, PlayerVehicle v2, GUI gui, int r) {
        newGameQueued = false;

        player1 = v1;
        player2 = v2;

        round = r;

        mainGUI = gui;

        timerListener = new TimerListener();

        timer = new Timer(player1.getDifficulty(), timerListener);
        timer.start();

        createNew();
    }

    private void createNew() {
        timer.stop();
        grid = new GridBlock[12][12];
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid.length; col++) {
                grid[row][col] = new GridBlock(true, false);
            }
        }

        int parkingRow = (int) (Math.random() * 9) + 1;
        int parkingCol = (int) (Math.random() * 5.5) * 2;

        set(player1.setLocation(new Location(11, 3)).getRow(), player1.getLocation().getCol(), player1);
        player1.setDirection(-1, 0, "sprites/playerVehicle_1UP.jpg");

        set(player2.setLocation(new Location(11, 7)).getRow(), player2.getLocation().getCol(), player2);
        player2.setDirection(-1, 0, "sprites/playerVehicle_2UP.jpg");

        for (int row = 1; row < 11; row++) {
            for (int col = 0; col < 12; col += 2) {

                if (row == parkingRow && col == parkingCol) {

                    this.set(row, col, new GridBlock(true, true));

                } else {

                    this.set(row, col, new GridBlock(false, false));

                }
            }
        }

        timer.start();

    }

    class TimerListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (timer.isRunning()) {
                Update();
            }
        }

    }

    public GridBlock get(int row, int col) {
        return grid[row][col];
    }

    public void set(int row, int col, GridBlock block) {
        grid[row][col] = block;
    }

    public void rotateP1(int row, int col) {
        String texture = "sprites/playerVehicle_1";
        if (row == -1) {
            texture += "UP.jpg";
        } else if (row == 1) {
            texture += "DN.jpg";
        } else if (col == 1) {
            texture += "RT.jpg";
        } else if (col == -1) {
            texture += "LT.jpg";
        }

        player1.setDirection(row, col, texture);
    }

    public void rotateP2(int row, int col) {
        String texture = "sprites/playerVehicle_2";
        if (row == -1) {
            texture += "UP.jpg";
        } else if (row == 1) {
            texture += "DN.jpg";
        } else if (col == 1) {
            texture += "RT.jpg";
        } else if (col == -1) {
            texture += "LT.jpg";
        }

        player2.setDirection(row, col, texture);
    }

    void Update() {

        set(player1.getLocation().getRow(), player1.getLocation().getCol(), new GridBlock(true, false));
        int col1 = player1.getColDirection() + player1.getLocation().getCol();
        int row1 = player1.getRowDirection() + player1.getLocation().getRow();

        set(player2.getLocation().getRow(), player2.getLocation().getCol(), new GridBlock(true, false));
        int col2 = player2.getColDirection() + player2.getLocation().getCol();
        int row2 = player2.getRowDirection() + player2.getLocation().getRow();

        if (player1.isActive()) {
            if ((col1 < 0 || row1 < 0) || (row1 > 11 || col1 > 11)) {
                player1.setActive(false);
                if (!player2.isActive()) {
                    if (newGameQueued) {
                        exit(true);
                        return;
                    } else {
                        exit(false);
                        return;
                    }
                }

                return;
            }

            if (!get(row1, col1).isSafeBlock()) {
                player1.setActive(false);
                if (!player2.isActive()) {
                    if (newGameQueued) {
                        exit(true);
                        return;
                    } else {
                        exit(false);
                        return;
                    }
                }

                return;
            }

            if (get(row1, col1).isParkBlock()) {
                player1.awardPoint();
                player1.setActive(false);
                if (!player2.isActive()) {
                    exit(true);
                    return;
                } else {
                    newGameQueued = true;
                }

                return;
            }

            player1.setLocation(new Location(row1, col1));
            set(player1.getLocation().getRow(), player1.getLocation().getCol(), player1);
        }

        if (player2.isActive()) {
            if ((col2 < 0 || row2 < 0) || (row2 > 11 || col2
                    > 11)) {
                player2.setActive(false);
                if (!player1.isActive()) {
                    if (newGameQueued) {
                        exit(true);
                        return;
                    } else {
                        exit(false);
                        return;
                    }
                }
                
                return;
            }
            if (!get(row2, col2).isSafeBlock()) {
                player2.setActive(false);
                if (!player1.isActive()) {
                    if (newGameQueued) {
                        exit(true);
                        return;
                    } else {
                        exit(false);
                        return;
                    }
                }
                
                return;
            }

            if (get(row2, col2).isParkBlock()) {
                player2.setActive(false);
                player2.awardPoint();
                if (!player1.isActive()) {
                    exit(true);
                    return;
                } else {
                    newGameQueued = true;
                }
                
                return;
            }

            player2.setLocation(new Location(row2, col2));
            set(player2.getLocation().getRow(), player2.getLocation().getCol(), player2);
        }

        mainGUI.updatePanels();

    }

    private void exit(boolean won) {

        timer.stop();
        if (won) {
            ++round;
        }
        this.mainGUI.halt(round, won, player1.getScore(), player2.getScore());

    }

}
