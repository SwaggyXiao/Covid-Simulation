import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
/**
 * Home class
 * 
 * The place where human stay during night time.
 * 
 * @author ꧁༺Xiao Zhang༻꧂
 * @version 3.2
 */
public class Home extends Actor
{
    // Declare instance variable of Image.
    private GreenfootImage houseImage;
    
    // Declare instance variable of SimulationWorld.
    private SimulationWorld SW;
    
    // Declare instance variables for human in different status with ArrayList.
    private ArrayList<Human> human; 
    private ArrayList<Human> humanStay; 
    private ArrayList<Human> intersectingPeople=new ArrayList<Human>();
    
    // Declare instance variables of object.
    private Human h;
    private HomeData HD;
    
    // Declare 2D array to build human's pathway.
    private int[][] pathway;
    
    // Declare instance variables of stats in Home.
    private int houseNumber;
    private int[] populationStatus;//0-->healthy,1-->unconfirmed,2-->confirmed,3-->dead, 4-->reccovered
    private int maximunExitingPerTime=10;
    private int population;
     
    // Declare boolean instance variables.
    private boolean sendPeople=true;
    private boolean finishSentHuman=false;
    
    /**
     * Constructor of Home that create the home.
     * 
     * @param houseNumber   The number that memorize location for different homes.
     */
    public Home(int houseNumber){
        this.houseNumber=houseNumber;
        drawImage();
        human=new ArrayList<Human>();
        humanStay=new ArrayList<Human>();
        pathway=new PathwayOfHuman(houseNumber).pathway();
    }

    /**
     * Called by Greenfoot when an object of this class is added to 
     * the World.
     * 
     * @param w The World being added to.
     */
    public void addedToWorld(World w){
        SW=(SimulationWorld) w;
        population=SW.returnTotalPopulation()/8;
        populationStatus=new int[]{0/*Healthy**/, 0/*Unconfirmed**/,0/*Confirmed**/,0/*Dead**/,0/*Recover**/};
        
        // Add human with different types to the world.
        for(int i=0;i<population;i++){
            int humanAge= Greenfoot.getRandomNumber(101);
            if(humanAge<=18){
                h=new Students(humanAge,"Healthy",pathway, this.getX(),this.getY());
                human.add(h);
            }  
            else if(humanAge<=64){
                h=new Adults(humanAge,"Healthy",pathway,this.getX(),this.getY());
                human.add(h);
            } 
            else if(humanAge<=100){
                h=new Seniors(humanAge,"Healthy",pathway,this.getX(),this.getY());
                human.add(h);
            } 
            populationStatus[0]++;
        }    

    }    

    /**
     * Act - do whatever the Home wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        if(!SW.pausePage()){
            addHomeData();
            if(SW.dayTime().equals("Morning")){
                sendHumanOut();
            }
            else if(SW.dayTime().equals("Evening")){
                checkIntersectionWithHome();
            }
            else if (SW.dayAct()==1050){
                humanStay=new ArrayList<Human>();
                if(finishSentHuman) finishSentHuman=false;
                for(Human h:human) if(h.HealthSituation().equals("Confirmed"))h.fightigAgainstVirus();
                refresh();
            }   
        }
    }    

    /**
     * Draw the image of home.
     */
    private void drawImage(){
        houseImage=new GreenfootImage("Home.png");
        houseImage.scale(120,120);
        this.setImage(houseImage);
    }    

    /**
     * Check whether human is tneracting with home(coming back to home).
     */
    protected void checkIntersectionWithHome(){
        intersectingPeople= (ArrayList<Human>)getIntersectingObjects(Human.class);
        for(Human h:intersectingPeople){
            if(h!=null&&!h.inHouse&&!h.inFacility&&!h.goToWork){
                human.add(h);
                SW.removeObject(h);
            }  
        }
    }  

    /**
     * Check whether home is being clicked to add home's data on home.
     */
    private void addHomeData(){
        if(Greenfoot.mouseClicked(this)){
            HD=new HomeData(populationStatus);
            SW.addObject(HD,this.getX(),this.getY());
        }
    }    

    /**
     * Send human out of the home.
     */
    private void sendHumanOut(){
        if(human.size()!=0&&!finishSentHuman){
            for(int i=0;i<Greenfoot.getRandomNumber(maximunExitingPerTime);i++){
                int index=Greenfoot.getRandomNumber(human.size());
                Human hu=human.get(index);
                if(!hu.HealthSituation( ).equals("Died")&&!hu.HealthSituation( ).equals("Confirmed"))human.get(index).scheduleDecision(SW);
                // Different house's location should send human out with different locations as well.
                if(houseNumber==1||houseNumber==2||houseNumber==3||houseNumber==5){
                    if(!hu.stayAtHomeForQuarantine()) SW.addObject(hu,getX()+60,getY()-houseImage.getHeight()/2+Greenfoot.getRandomNumber(houseImage.getHeight()));
                    else humanStay.add(hu);
                    human.remove(hu);
                }
                else{
                    if(!hu.stayAtHomeForQuarantine()) SW.addObject(hu,getX()-60,getY()-houseImage.getHeight()/2+Greenfoot.getRandomNumber(houseImage.getHeight()));
                    else humanStay.add(hu);
                    human.remove(hu);
                }   
                if(human.size()==0){
                    if(humanStay.size()!=0){
                        //ArrayList<Human> temp=new ArrayList<Human>();
                        for(Human h:humanStay){
                            human.add(h);
                            //temp.add(h);
                        }   
                    }
                    finishSentHuman=true;
                    break;
                }
            }
        }
    }

    /**
     * Update the stat for human's status.
     */
    private void refresh(){
        populationStatus=new int[]{0,0,0,0,0};
        for (Human h:human){
            if(h.Health.equals("Healthy")) populationStatus[0]++;
            else if(h.Health.equals("Unconfirmed")) populationStatus[1]++;
            else if(h.Health.equals("Confirmed")) populationStatus[2]++;
            else if(h.Health.equals("Died")) populationStatus[3]++;
            else if(h.Health.equals("Recover")) populationStatus[4]++;
        }    
        if(HD!=null){
            HD.drawImage(populationStatus);
        }    
    }
}
