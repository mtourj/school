public class PlayerVehicle extends GridBlock
{
    private final int speed; //difficulty
    private int rowDirection;
    private int colDirection;
    private Location l;
    private boolean active;
    private int score;
    
    public boolean isActive (){
        return active;
    }
    
    public void setActive (boolean act){
        active = act;
    }
    
    public int getScore (){
        return score;
    }
    
    public void awardPoint (){
        score++;
    }
    
    public PlayerVehicle(int speed, int row, int col, int s)
    {
        super(false, false);
        active = true;
        score = s;
        super.setTexture("sprites/playerVehicle1_UP.jpg");
        this.speed = speed;
        rowDirection = -1;
        colDirection = 0;
        l = new Location(row,col);
    }
    
    public int getDifficulty()
    {
        return speed; 
    }
    
    public void setDirection(int rowDir, int colDir, String tex){
        super.setTexture(tex);
        rowDirection = rowDir;
        colDirection = colDir;
    }
    
    public int getRowDirection (){
        return rowDirection;
    }
    
    public int getColDirection (){
        return colDirection;
    }
    
    public Location getLocation()
    {
        return l; 
    }
    
    public Location setLocation(Location loc)
    {
        l = loc;
        return loc;
    }
    
}