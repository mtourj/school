public class GridBlock {
    
    private boolean safe, park;
    
    public ImageComponent image;
    
    String[] parkedCars = {"sprites/parkedCar0.jpg", "sprites/parkedCar1.jpg"};
    
    public GridBlock (boolean isSafe, boolean isPark){
        safe = isSafe;
        park = isPark;
        if(isSafe){
            if(isPark){
                setTexture("sprites/parkingSpot.jpg");
            } else {
                setTexture("sprites/empty.jpg");
            }
        } else {
            String parkedCar = parkedCars[(int)(Math.random() * parkedCars.length)];
            setTexture(parkedCar);
        }
    }
    
    public void setTexture (String tex){
        image = new ImageComponent(tex);
    }
    
    public boolean isSafeBlock (){
        return safe;
    }
    
    public boolean isParkBlock (){
        return park;
    }
}