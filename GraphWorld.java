import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
import java.util.Arrays;
/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class GraphWorld extends World
{
    private ArrayList<int[]> data;
    private percentGragh PG;
    private SimulationWorld SW;
    private Restart restart;
    boolean aaa=false;
    private Resume resume;
    private GreenfootImage backgroundImage;
    private GreenfootSound GS;
    //private IndividualGraph IG;
    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    public GraphWorld(ArrayList<int[]> data,SimulationWorld SW)//healthy, unconfirmed, confirmed, died, recover
    {    
        super(1200, 800, 1); 
        this.data=data;
        this.SW=SW;
        PG=new percentGragh(data);
        addObject(PG,600,300);
        restart=new Restart();
        addObject(restart,700,650);
        resume=new Resume();
        addObject(resume,1000,650);
        backgroundImage=new GreenfootImage("Graph World.png");
        setBackground(backgroundImage);
        GS=new GreenfootSound("Simulation Music 2.mp3");
        //IG=new IndividualGraph(data);
        //addObject(IG,900,200);
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
    }
    public void act(){
        GS.playLoop();
        if(Greenfoot.mouseClicked(restart)){
            Greenfoot.setWorld(new OpeningPage());
            GS.stop();
        }
        else if(Greenfoot.mouseClicked(resume)) {
            Greenfoot.setWorld(SW);
            GS.stop();
        }
        
        /**
         * if(Greenfoot.mouseClicked(start)){
            BGM.stop();
            Greenfoot.setWorld(new SimulationWorld());
        }    
         */
    }    
    
}
