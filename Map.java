import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
/**
 * Map class
 * 
 * The map that corresponded to different facilities.
 * 
 * @author ꧁༺Emily Chen༻꧂ ꧁༺Xiao Zhang༻꧂
 * @version (a version number or a date)
 */
public class Map extends Actor
{
    // Declare instance variable of Image.
    private GreenfootImage place;
    private GreenfootImage heading;
    private GreenfootImage backBoard;
    
    // Declare instance variables of stats.
    private int infectedPopulation=0;
    private int sizeX=300;
    private int sizeY=350;
    
    // Declare the size of font for text displayed.
    private Font f=new Font("OptimusPrinceps",true,false,16);
    
    // Declare the string that facility correspond to.
    private String facilityName;
    
    // Declare instance variable of SimulationWorld.
    private SimulationWorld SW;
    
    // Declare instance variables of object.
    private Close closeButtom;
    
     // Declare instance variables for human in map with ArrayList.
    private ArrayList<Human> listOfHuman;
    
    /**
     * Constructor of Map that create map for different facilities.
     * 
     * @param   listOfHuman   Human inside the facilities.
     * @param   facilityName    The name of the facility.
     */
    public Map(ArrayList<Human> listOfHuman, String facilityName){
        this.facilityName=facilityName;
        this.listOfHuman=listOfHuman;
        for(Human h:listOfHuman){
            if(!h.healthSituation()) infectedPopulation++;
        }    
        drawImage();
    }  
    
    /**
     * Return facility's name.
     */
    public String facilityName(){
        return facilityName;
    }    
    
    /**
     * Called by Greenfoot when an object of this class is added to 
     * the World.
     * 
     * @param w The World being added to.
     */
    public void addedToWorld(World w) 
    {
        SW=(SimulationWorld)w;
        // Add human and close buttom in map.
        addHuman();
        addCloseButtom();
    }    

    /**
     * Act - do whatever the Map wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        checkClose();
    }  

    /**
     * Draw the image for map.
     */
    private void drawImage() 
    {
        heading=new GreenfootImage(sizeX+1,sizeY-sizeX+1);
        heading.setColor(Color.GRAY);
        heading.fillRect(0,0,sizeX,sizeY-sizeX);
        heading.setColor(Color.BLACK);
        heading.drawRect(0,0,sizeX,sizeY-sizeX);
        heading.setFont(f);
        heading.setColor(Color.WHITE);
        heading.drawString("Infected: "+infectedPopulation,10,40);
        heading.drawString("Num Of People: "+listOfHuman.size(),10,20);
        //heading.drawString(infectedPopulation)
        place=new GreenfootImage(sizeX+1,sizeX+1);
        place.drawImage(new GreenfootImage("Map.png"),0,0);
        place.setColor(Color.BLACK);
        place.drawRect(0,0,place.getWidth()-1,place.getHeight()-1);
        backBoard=new GreenfootImage(sizeX+1,sizeY+1);
        backBoard.drawImage(heading,0,0);
        backBoard.drawImage(place,0,(sizeY-sizeX));
        setImage(backBoard);
    }   

    /**
     * Add human onto the map.
     */
    private void addHuman(){
        for(Human h:SW.listOfPeople(facilityName)){
            h.enteringStatusInFacility(returnBorder());
            SW.addObject(h,this.getX()+Greenfoot.getRandomNumber(sizeX-10)-sizeX/2+5,this.getY()+Greenfoot.getRandomNumber(sizeX-10)+5+sizeY/2-sizeX);
        }
    }    

    /**
     * Return the stats for border of map.
     */
    public int[] returnBorder(){
        int[] Border={this.getX()-sizeX/2+8, this.getX()+sizeX/2-8,this.getY()+sizeY/2-sizeX+8,this.getY()+sizeY/2-8};
        return Border;
    }    

    /**
     * Add close buttom onto the map.
     */
    private void addCloseButtom(){
        closeButtom=new Close();
        SW.addObject(closeButtom,this.getX()+sizeX/2-12,this.getY()-sizeY/2+12);
    }    

    /**
     * Check if map is being closed.
     */
    private void checkClose(){
        if(Greenfoot.mouseClicked(closeButtom)) {
            SW.removeObject(closeButtom);
            for(Human h:SW.listOfPeople(facilityName)){
                h.leavingStatusInFacility();
                SW.removeObject(h);
            }    
            SW.mapExistence(false);
            SW.removeObject(this);
        }
    }  

    /**
     * Update the image of map.
     * 
     * @param   enter   Whether human has entered into the facility.
     * @param   h       The human that is added into the map.
     */
    public void Update(boolean enter, Human h){
        if(enter){
            h.enteringStatusInFacility(returnBorder());
            SW.addObject(h,this.getX()+Greenfoot.getRandomNumber(sizeX-10)-sizeX/2+5,this.getY()+Greenfoot.getRandomNumber(sizeX-10)+5+sizeY/2-sizeX);
            reDrawImage();
        }    
        else{
            h.leavingStatusInFacility();
            SW.removeObject(h);    
            reDrawImage();
        }    
    }   
    
    /**
     * Set the number of people infected.
     * 
     * @param   The latest number of people being infected.
     */
    public void setInfectedPopulation(int numInfected) {
        infectedPopulation = numInfected;
    }

    /**
     * Redraw the image of map.
     */
    public void reDrawImage(){

        heading.clear();
        heading.setColor(Color.GRAY);
        heading.fillRect(0,0,sizeX,sizeY-sizeX);
        heading.setColor(Color.BLACK);
        heading.drawRect(0,0,sizeX,sizeY-sizeX);
        heading.setFont(f);
        heading.setColor(Color.WHITE);
        heading.drawString("Infected: "+infectedPopulation,10,40);
        heading.drawString("Num Of People: "+listOfHuman.size(),10,20);
        backBoard.clear();
        backBoard.drawImage(heading,0,0);
        backBoard.drawImage(place,0,(sizeY-sizeX));
        setImage(backBoard);

    }    

    /**
     * Return the name of the facility.
     */
    public String correspondingFacility(){
        return facilityName;
    }     
}
