import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Here is the simulation opening page
 * <p>
 * by click <b>start</b>, <b>book</b>, <b>Info</b>, you can either start the game, open the game instructions,or open the information about this game
 * 
 * @author ꧁༺Leonard Jin༻꧂ ꧁༺Xiao Zhang༻꧂
 * @version 4.2
 */
public class OpeningPage extends World
{
    private GreenfootImage background;
    private GameStart start;
    private Info info;
    private Instruction instruction;
    private InstructionPage IP;
    private InformationPage InfoP;
    private GreenfootSound BGM;
    /**
     * The constructor of the Opening World
     */
    public OpeningPage()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1200, 800, 1);
        background = new GreenfootImage("backImage.jpg");
        background.scale(1200,800);
        setBackground(background);
        Logo l=new Logo();
        addObject(l, 600,340);
         start = new GameStart();
        addObject(start, 350,600);
         info = new Info();
        addObject(info, 850, 600);
        instruction=new Instruction();
        addObject(instruction, 600, 600);
        BGM=new GreenfootSound ("Beginning.mp3");
    }
    public void act(){
        BGM.playLoop();
        if(Greenfoot.mouseClicked(start)){
            BGM.stop();
            Greenfoot.setWorld(new SimulationWorld());
        }    
        else if(Greenfoot.mouseClicked(info)){
            InfoP=new InformationPage();
            addObject(InfoP,600,400);
        }    
        else if(Greenfoot.mouseClicked(instruction)){
            IP=new InstructionPage();
            addObject(IP,600,400);
        }    
    }    
}
