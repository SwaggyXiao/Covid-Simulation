import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * InformationPage class
 * 
 * Display the background information of Covid.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class InformationPage extends Actor
{
    // Declare instance variable of Image.
    private GreenfootImage information;
    
    // Declare instance variables of object.
    private OpeningPage OP;
    private Wrong close;
    
    /**
     * Constructor for InformationPage that set the image for information page.
     */
    public InformationPage(){
        information=new GreenfootImage("Information.png");
        setImage(information);
    }  
    
    /**
     * Called by Greenfoot when an object of this class is added to 
     * the World.
     * 
     * @param w The World being added to.
     */
    public void addedToWorld(World w){
        OP=(OpeningPage) w;
        // Add wrong buttom to information page.
        close=new Wrong();
        OP.addObject(close,1040,80);
    }    
    /**
     * Act - do whatever the InfomationPage wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        if(Greenfoot.mouseClicked(close)){
            // If being clicked, remove information page and wrong buttom.
            OP.removeObject(close);
            OP.removeObject(this);
        }     
    }    
}
