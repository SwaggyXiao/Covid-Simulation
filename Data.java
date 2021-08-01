import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Data here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Data extends Buttom
{
    public Data(){
        super("Data.png");
    }   
    public void act() 
    {
        checkClicking();
    }   
    protected void initImages(){
        idleImage = new GreenfootImage (image);
        idleImage.scale(140,160);
        //the scale for the original icon
        mouseOverImage = new GreenfootImage(image);
        mouseOverImage.scale(154,176);
        //the scale for the larger icon
        setImage(idleImage);
        //the beginning image
    }   
}
