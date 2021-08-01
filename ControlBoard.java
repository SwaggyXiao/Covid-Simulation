
import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * ControlBoard class
 * 
 * Use to create a control board that is displayed when the simulation is paused
 * and is able to change stats.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ControlBoard extends Actor
{
    // Declare instance variables of Images.
    private GreenfootImage controlBoard;
    
    // Declare instance variables of object.
    public Statistics awareness;
    public Statistics mask;
    
    // Declare instance variable of SimulationWorld.
    private SimulationWorld SW;
    public Data dataButton;
    /**
     * Constructor of ControlBoard that set the image of ControlBoard's background. 
     */
    public ControlBoard(){
        // Import the backgroud of control board from local.
        controlBoard=new GreenfootImage("Control Board.png");
        // Set the size of controlBoard.
        controlBoard.scale(900,495);
        this.setImage(controlBoard);
        
    }    
    
    /**
     * Called by Greenfoot when an object of this class is added to 
     * the World.
     * 
     * @param w The World being added to.
     */
    public void addedToWorld (World w){
        SW = (SimulationWorld)w;
        // Call method addButtoms() to add "Awareness" and "Mask On" to 
        // control board.
        addButtoms();
    }
    public void act(){
        if(Greenfoot.mouseClicked(dataButton)) Greenfoot.setWorld(new GraphWorld(SW.data(),SW));

    }    
    /**
     * Add "Awareness" and "Mask On" to the control board. 
     */
    private void addButtoms(){
        awareness=new Statistics(SW.ReturnAwareness(),"Awareness");
        mask=new Statistics(SW.ReturnMask(),"Mask");
        dataButton=new Data();
        SW.addObject(awareness,this.getX()-200,this.getY()-120);
        SW.addObject(mask,this.getX()-200,this.getY()+120);
        SW.addObject(dataButton,900,400);
    }    
}
