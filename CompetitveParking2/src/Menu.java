/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author 17kulkarninikhil
 */
public class Menu {

    /**
     * Creates new form Menu
     */
    
    GUI gui;
    
    private int speed; 
    
    private int roundsPlayed;
    
    private int p1s, p2s;
    
    static MenuWindow window;
    
    public Menu() {
        
        p1s = p2s = 0;
        
        roundsPlayed = 0;
        
        speed = 150;
        
        window = MenuWindow.instance;
    }

    Menu(MenuWindow aThis) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void setDifficulty (int diff){
        speed = diff;
    }
    
    public void showMenu(int rounds, boolean won, int p1, int p2){
        
        roundsPlayed = rounds;
        
        p1s = p1;
        p2s = p2;
        
        setUpScore();
        
        gui = null;
        if(won)
            setUpGame(roundsPlayed);
        else
            window.setVisible(true);
        
    }

    private void setUpScore (){
       //jLabel1.setText("Rounds played: " + roundsPlayed + ", Player 1: " + p1s + "pts" + ",\n Player 2: " + p2s + "pts");
    }
    
    public void setUpGame()
    {
        p1s = p2s = 0;
        setUpGame(0);
    }
    
    private void setUpGame(int rounds){
        
        PlayerVehicle a1 = new PlayerVehicle(speed,11, 3, p1s);
        PlayerVehicle a2 = new PlayerVehicle(speed, 11, 7, p2s);
        
        gui = new GUI(a1, a2, this, rounds);
        
        gui.show();
    }
    
    
  
}
