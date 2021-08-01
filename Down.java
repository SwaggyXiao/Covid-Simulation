import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Down class
 * 
 * Use to make the stats (awareness and mask) lower.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Down extends Buttom
{
    /**
     * Constructor of Down to create the image of down buttom.
     */
    public Down(){
        // Import image from local by calling constructor in superclass(Buttom).
        super("Down.png");
    }   
    
    /**
     * Act - do whatever the Down wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
       // Always check if mouse is over buttom.
       checkClicking();
    }    
}
