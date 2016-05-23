import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.KeyListener;
import javafx.scene.layout.Border;
import javax.swing.*;

public class GUI{
    public JPanel mainPanel;
    JPanel[][] panels;
    JFrame frame;
    JButton upButton, downButton, leftButton, rightButton;
    
    private KeyListener pc;
    
    public Grid grid;
    
    public void show (){
        frame.setVisible(true);
    }
    
    Menu main;
    
    public GUI (PlayerVehicle vehicle1, PlayerVehicle vehicle2, Menu menu, int score) {
        panels = new JPanel[12][12];
        
        main = menu;
        
        MapBuilder builder = new MapBuilder();
        grid = builder.build(panels, vehicle1, vehicle2, this, score);
        
        
        for(int row = 0; row < panels.length; row++){
            for(int col = 0; col < panels.length; col++){
                panels[row][col] = new JPanel();
                ImageComponent image = grid.get(row, col).image;
                panels[row][col].add(image);
                panels[row][col].setMinimumSize(new Dimension(64, 64));
                panels[row][col].setBackground(new Color(127, 127, 127));
            }
        }
        
        pc = new PlayerController(this);
        
        mainPanel = new JPanel();
        GridLayout layout = new GridLayout(12, 12);
        mainPanel.setLayout(layout);
        for(JPanel[] p : panels){
            for(JPanel panel : p){
                mainPanel.add(panel);
                
            }
        }
        
        mainPanel.addKeyListener(pc);
        mainPanel.setFocusable(true);
        mainPanel.setBackground(new Color (127, 127, 127));
        
        frame = new JFrame();
        frame.setSize(768, 768);
        frame.setResizable(false);
        frame.setTitle("Competitive Parking II: Round " + (score+1));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBackground(new Color (127, 127, 127));
        
        frame.add(mainPanel);
    }
    
    public void updatePanels (){
        if(mainPanel == null)
            return;
        
        mainPanel.removeAll();
        
        for(int row = 0; row < panels.length; row++){
            for(int col = 0; col < panels.length; col++){
                panels[row][col] = new JPanel();
                ImageComponent image = grid.get(row, col).image;
                panels[row][col].add(image);
                panels[row][col].setMinimumSize(new Dimension(64, 64));
                panels[row][col].setBackground(new Color(127, 127, 127));
            }
        }
        
        for(JPanel[] p : panels){
            for(JPanel panel : p){
                mainPanel.add(panel);
            }
        }
        
        mainPanel.invalidate();
        
        mainPanel.validate();
        
        mainPanel.repaint();
        
    }
    
    public void halt(int round, boolean won, int p1, int p2){
        grid = null;
        
        mainPanel = null;
        
        frame.invalidate();
        
        frame.setVisible(false);
        
        pc = null;
        
        frame = null;
        
        main.showMenu(round, won, p1, p2);
    }

    
}