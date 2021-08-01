import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Statistics class
 * 
 * Set all the stats, words and buttoms used in control board.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Statistics extends Actor
{
    // Declare instance variable of Image.
    private GreenfootImage BackgroundImage;
    
    // Declare instance variable of SimulationWorld.
    private SimulationWorld SW;
    
    // Declare instance variable for types of statistics.
    private String Type;
    
    // Declare instance variables of object.
    public Percent_Board PB;
    public Up UpButtom;
    public Down DownButtom;
    
    // Declare instance variable for data in statistics.
    private String data;
    
    /**
     * Constructor of Statistics that create image for statistics.
     * 
     * @param   Data    The data(stats) in statistics.
     * @param   Type    The type of statistics.
     */
    public Statistics(String Data, String Type){
        this.data=Data;
        this.Type=Type;
        drawImage();
    }     
    
    /**
     * Called by Greenfoot when an object of this class is added to 
     * the World.
     * 
     * @param w The World being added to.
     */
    public void addedToWorld (World w){
        SW = (SimulationWorld)w;
        // Add percent board, up buttom and down buttom in statistics.
        PB=new Percent_Board(data,Type);
        SW.addObject(PB, this.getX()+BackgroundImage.getWidth(),this.getY());
        UpButtom=new Up();
        SW.addObject(UpButtom, this.getX()+BackgroundImage.getWidth(),this.getY()-80);
        DownButtom=new Down();
        SW.addObject(DownButtom, this.getX()+BackgroundImage.getWidth(),this.getY()+80);
    }
    
    /**
     * Act - do whatever the Statistics wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        CheckClicking();
    }  
    
    /**
     * Draw the image of statistics.
     */
    protected void drawImage(){
        if(Type.equals("Awareness"))BackgroundImage=new GreenfootImage("Awareness Button.png");
        else if(Type.equals("Mask"))BackgroundImage=new GreenfootImage("Mask On Button.png");
        BackgroundImage.scale(200,80); 
        this.setImage(BackgroundImage);
    }  
    
    /**
     * Check whether statistics board is being clicked.
     */
    private void CheckClicking(){
        if(Greenfoot.mouseClicked(UpButtom)){
            PB.Updates(-1, Type);
        }   
        else if(Greenfoot.mouseClicked(DownButtom)){
            PB.Updates(1,Type);
        }    
    }  
}
