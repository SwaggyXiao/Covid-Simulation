import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
/**
 * Close class
 * 
 * Use to close the inside look of facilities.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Close extends Buttom
{
    private int sizeNumber=15;
    public Close(){
        // Import image from local by calling constructor in superclass(Buttom)..
        super("CloseButtom A.png");
    }   
    
    /**
     * Act - do whatever the Close wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
       // Always check if mouse is over buttom.
       checkClicking();
    }      
    /**
     * Override the method initImages() since close buttom should not be 
     * bigger anytime and should change image only when mouse is over.
     */
    protected void initImages(){
        idleImage = new GreenfootImage ("CloseButtom A.png");
        idleImage.scale(15,15);
        //the scale for the original icon
        mouseOverImage = new GreenfootImage("CloseButtom B.png");
        // Set the new look of close buttom if mouse is over close buttom.
        mouseOverImage.scale(15,15);
        //the scale is same as original icon for close buttom.
        setImage(idleImage);
        //the beginning image
    }   
}