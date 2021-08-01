import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * BlackScreen Class
 * 
 * Use to create the image of black screen, it will be created
 * when the simulation is paused.
 * 
 * @author (your name)
 * @version (a version number or a date)
 */
public class BlackScreen extends Actor
{
    // Declare instance variable of black screen's image.
    private GreenfootImage blackScreen;
    
    /**
     * Constructor of BlackScreen that set the image of black screen.
     */
    public BlackScreen(){
        // Import the black screen from local.
        blackScreen=new GreenfootImage("Black Screen.png");
        blackScreen.scale(1200,800);
        blackScreen.setTransparency(100);
        this.setImage(blackScreen);
    }   
}
