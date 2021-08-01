import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Up class
 * 
 * Use to make the stats (awareness and mask) higher.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Up extends Buttom
{
    /**
     * Constructor of Up to create the image of up buttom.
     */
    public Up(){
        // Import image from local by calling constructor in superclass(Buttom).
        super("Up.png");
    }    
    
    /**
     * Act - do whatever the Up wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        // Always check if mouse is over buttom.
        checkClicking();
    }    
}
