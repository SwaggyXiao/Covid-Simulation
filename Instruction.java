import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Instruction here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Instruction extends Buttom
{
    /**
     * Constructor of Instruction to create the image of instruction buttom.
     */
    public Instruction(){
        super(null);
    }   
    
    /**
     * Act - do whatever the Instruction wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        // Always check if mouse is over buttom.
         checkClicking();
    }    
    
    /**
     * Override the method initImages() since instruction buttom should not be 
     * bigger anytime and should change image only when mouse is over.
     */
    protected void initImages(){
     
        idleImage = new GreenfootImage ("Instructions.mouseOver.png");
        idleImage.scale(100,100);
        //the scale for the original icon
        mouseOverImage = new GreenfootImage("Instructions.normal.png");
        mouseOverImage.scale(100,100);
        //the scale for the larger icon
        setImage(idleImage);
        //the beginning image
    }      
}
