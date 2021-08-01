import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The clock is a separate individual simulation of the sun and the moon rising and setting.
 * This animation will happen on the top left side of the simulation world screen.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Clock extends Actor
{
    // Declare instance variables of Images.
    private GreenfootImage sun;
    private GreenfootImage moon;
    
    // Declare boolean instance variables.
    private boolean isSun;
    
    // Declare instance variable of SimulationWorld.
    private SimulationWorld SW;
    
    // Declare instance variables of stats in Clock.
    private int xCoordinate;
    private int yCoordinate;
    private int x;
    private int y;
    private int totalActs;
    private int Xradius=50;
    private int Yradius=30;
    private double angle;
    /**
     * Startes the animation by creating an image of the sun and the moon, both with sizes 30 by 30 pixels.
     * This animation will have the sun and the moon in circular motion representing the days that have gone by
     * in the simulation.
     * 
     * @param actsPerDay        Length of a day in the simulation counted in number of acts
     */
    public Clock(int actsPerDay)
    {
        totalActs = actsPerDay;
        sun = new GreenfootImage("sun.png");
        sun.scale(30,30);
        moon = new GreenfootImage("moon.png");
        moon.scale(30,30);
        setImage(sun);
    }
    /**
     * Called when actor inserted into the world, and it gets the x coordinate and the y coordinate it has 
     * when getting added to the world.
     * 
     * @param w     The world that this actor was added to.
     */
    public void addedToWorld(World w){
        SW=(SimulationWorld) w;
        xCoordinate = getX();
        yCoordinate = getY();
    }    
    
    /**
     * Act - do whatever the Clock wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        if(!SW.pausePage()){
            angle=Math.toRadians((double)SW.dayAct()/totalActs*360);
            // Set the location of the sun or moon corresponding to the time.
            setLocation(xCoordinate+(int)(Xradius*Math.sin(angle-Math.PI/2)),yCoordinate-(int)(Yradius*Math.cos(angle-Math.PI/2)));
            if(SW.dayAct()==1) this.setImage(sun);
            else if(SW.dayAct()==totalActs/2+1) this.setImage(moon);
        }     
    }    
}
