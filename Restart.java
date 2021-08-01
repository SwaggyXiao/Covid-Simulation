import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Restart here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Restart extends Buttom
{
    public Restart(){
        super("Restart Not Click.png");
    }    
    public void act() 
    {
        checkClicking();
    }    
    /**
     * Override the method initImages() since close buttom should not be 
     * bigger anytime and should change image only when mouse is over.
     */
    protected void initImages(){
        idleImage = new GreenfootImage ("Restart Not Click.png");
        idleImage.scale(110,110);
        //the scale for the original icon
        mouseOverImage = new GreenfootImage("Restart Click.png");
        // Set the new look of close buttom if mouse is over close buttom.
        mouseOverImage.scale(110,110);
        //the scale is same as original icon for close buttom.
        setImage(idleImage);
        //the beginning image
    }   
}
