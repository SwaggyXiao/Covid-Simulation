import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Wrong here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Wrong extends Buttom
{
    /**
     * Constructor of Wrong to create the image of wrong buttom.
     */
    public Wrong(){
        // Import image from local by calling constructor in superclass(Buttom).
        super("close.png");
    }    
    
    /**
     * Act - do whatever the Wrong wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        // Always check if mouse is over buttom.
        checkClicking();
    }    
    
    /**
     * Override the method initImages() since wrong buttom should have different
     * size than default image's size.
     */
    protected void initImages(){
        idleImage = new GreenfootImage (image);
        idleImage.scale(60,60);
        //the scale for the original icon
        mouseOverImage = new GreenfootImage(image);
        // Set the new look of close buttom if mouse is over close buttom.
        mouseOverImage.scale(70,70);
        //the scale is same as original icon for close buttom.
        setImage(idleImage);
        //the beginning image
    }   
}
