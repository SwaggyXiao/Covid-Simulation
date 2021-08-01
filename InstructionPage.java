import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * InstructionPage class
 * 
 * Display the instruction of simulation.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class InstructionPage extends Actor
{
    // Declare instance variable of Image.
    private GreenfootImage instructions;
    
    // Declare instance variables of object.
    private Next next;
    private OpeningPage OP;
    private Wrong close;
    
    // Declare instance variables of stats.
    private int pageNumber=1;
    
    /**
     * Constructor for InstuctionPage that set the image for instuction page.
     */
    public InstructionPage(){
        instructions=new GreenfootImage("Instruction 1.png");
        setImage(instructions);
    }    
    
    /**
     * Called by Greenfoot when an object of this class is added to 
     * the World.
     * 
     * @param w The World being added to.
     */
    public void addedToWorld(World w){
        OP=(OpeningPage) w;
        // Add next buttom and wrong buttom to instruction page.
        next=new Next();
        OP.addObject(next,900,140);
        close=new Wrong();
        OP.addObject(close,1050,120);
    }    
    /**
     * Act - do whatever the InstructionPage wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act(){
        if(Greenfoot.mouseClicked(next)&&pageNumber==1){
            pageNumber++;
            next.setImage("previous.png");
            updateImage(0);
        }   
        else if(Greenfoot.mouseClicked(next)&&pageNumber==2){
            pageNumber--;
            next.setImage("Next.png");
            updateImage(1);
        }     
        if(Greenfoot.mouseClicked(close)){
            OP.removeObject(close);
            OP.removeObject(next);
            OP.removeObject(this);
        }     
    }    
    
    /**
     * Update the image of instruction page.
     */
    private void updateImage(int i) 
    {
        if(i==0){instructions=new GreenfootImage("Instruction 2.png");
        setImage(instructions);
    }
    else if(i==1){instructions=new GreenfootImage("Instruction 1.png");
        setImage(instructions);
    }
    }    
}
