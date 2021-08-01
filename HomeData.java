import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * HomeData class
 * 
 * Display the data for different homes.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class HomeData extends Actor
{
    // Declare instance variables of stats in HomeData.
    private int sizeX=190;
    private int sizeY=180;
    private int population;
    private int[] populationStatus;//0-->healthy,1-->unconfirmed,2-->confirmed,3-->dead, 4-->reccovered
    
    // Declare instance variable of Image.
    private GreenfootImage homeDataBackground;
    
    // Declare instance variables of object.
    private Close close;
    
    // Declare instance variable of SimulationWorld.
    private SimulationWorld SW;
    
    // Declare the font for text displayed in home's data.
    private Font f=new Font("OptimusPrinceps",true,false,20);
    
    /**
     * Constructor of HomeData that creates image for home data.
     * 
     * @param   populationStatus    The status of human in home.
     */
    public HomeData(int[] populationStatus){
        this.populationStatus=populationStatus;
        drawImage(populationStatus);
    }    
    
    /**
     * Called by Greenfoot when an object of this class is added to 
     * the World.
     * 
     * @param w The World being added to.
     */
    public void addedToWorld(World w){
        SW=(SimulationWorld)w;
        // Add close buttom to homedata.
        close=new Close();
        SW.addObject(close, this.getX()+sizeX/2-12,this.getY()-sizeY/2+12);
    }    
    
    /**
     * Act - do whatever the HomeData wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        // Always check if close buttom is being clicked
        checkClose();
    }    
    
    /**
     * Check whether close buttom is being clicked.
     */
    private void checkClose(){
        if(Greenfoot.mouseClicked(close)){
            // If being clicked, remove homedata and close buttom.
            SW.removeObject(close);
            SW.removeObject(this);
        }    
    }   
    
    /**
     * Draw the image for HomeData.
     * 
     * @param   populationStatus    The status of human in home.
     */
    public void drawImage(int[] populationStatus){
        homeDataBackground=new GreenfootImage("HomeBackground.png");
        homeDataBackground.scale(sizeX,sizeY);
        homeDataBackground.setFont(f);
        homeDataBackground.setColor(Color.WHITE);
        homeDataBackground.drawString(("Healthy: "+populationStatus[0]),15,40);
        homeDataBackground.drawString(("Unconfirmed: "+populationStatus[1]),15,70);
        homeDataBackground.drawString(("Confirmed: "+populationStatus[2]),15,100);
        homeDataBackground.drawString(("Dead: "+populationStatus[3]),15,130);
        homeDataBackground.drawString(("Recover: "+populationStatus[4]),15,160);
        setImage(homeDataBackground);
        
    }    
}
