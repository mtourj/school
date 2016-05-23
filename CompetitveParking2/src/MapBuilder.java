import javax.swing.JPanel;

public class MapBuilder {
    
    Grid grid;
    
    public Grid build (JPanel[][] panels, PlayerVehicle v1, PlayerVehicle v2, GUI gui, int score){
        grid = new Grid (v1, v2, gui, score);
        
        return grid;
    }
    
}