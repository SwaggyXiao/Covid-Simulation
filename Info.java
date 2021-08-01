import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Info here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Info extends Buttom
{
    /**
     * Constructor of Info to create the image of information buttom.
     */
    public Info(){
        super(null);
    }  
    
    /**
     * Act - do whatever the Info wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        // Always check if mouse is over buttom.
         checkClicking();
    }    
    
    /**
     * Override the method initImages() since info buttom should not be 
     * bigger anytime and should change image only when mouse is over.
     */
    protected void initImages(){
     
        idleImage = new GreenfootImage ("Info.mouseOn.png");
        idleImage.scale(100,100);
        //the scale for the original icon
        mouseOverImage = new GreenfootImage("Info.normal.png");
        mouseOverImage.scale(100,100);
        //the scale for the larger icon
        setImage(idleImage);
        //the beginning image
    }      
}
