import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
/**
 * Facilities class
 * 
 * Use to get human inside of it and easier to determine infection inside facilities.
 * 
 * @author ꧁༺Leonard Jin༻꧂ ꧁༺Xiao Zhang༻꧂
 * @version 3.4
 */
public abstract class Facilities extends Actor
{
    /**
     * A specific infection index that is differ by different type of 
     */
    protected double infectionRate;
    /**
     * The world that the facility is in
     */
    protected SimulationWorld SW;
    /**
     * The number of people infected in the building
     * <p>
     * This will indirectly impact the infection rate within the building 
     */
    protected int infectedPopulation=0;
    /**
     * The name of the facility instance
     */
    protected String facilityName;
    /**
     * The maximum number of people that can exit the building per act will be three
     */
    protected int maximunExitingPerTime=3;
    /**
     * The image of the specific facility
     */
    protected GreenfootImage facilityImage;
    /**
     * The list of people that is stored inside the facility
     */
    protected ArrayList<Human> intersectingPeople=new ArrayList<Human>();
    /**
     * An estimation of the area of the facility. 
     * This will be involved when determining the infection rate inside this building
     */
    protected int Area;
    /**
     * Describes if the facility is closed
     */
    protected boolean facilityClose=false;
    /**
     * This method is called by the Greenfoot system when this actor has been inserted into the world.
     * <p>
     * Take in the following Parameters to construct a more customized Health Bar
     * 
     * @param w                                 The World being added to.
     */
    protected void addedToWorld(World w){
        SW=(SimulationWorld)w;
        if(this instanceof School)facilityName="school";
        else if(this instanceof Restaurant)facilityName="restaurant";
        else if(this instanceof Supermarket) facilityName="supermarket";
    }
    /**
     * The act method is called by the greenfoot framework to give actors a chance to perform some action.
     */
    public void act() 
    {
        if(!SW.pausePage()&&!facilityClose){
            if(SW.dayTime().equals("Morning")){
                // Always update the restaurant's map that show the people inside.
                checkIntersectionWithFacility();
            }

            else if(SW.dayTime().equals("Evening")){
                // Always update the restaurant's map that show the people inside.
                sendHumanOut();
            }
            checkingHumanInflation();
        }
    }  
    

    /**
     * Return the risk index that human will be infected.
     * @return risk             a risk index that will directly determine whether a person is infected through a calculation in Human class
     */
    protected double Risk(){
        double risk = 10*infectedPopulation*Math.sqrt((double)SW.listOfPeople(facilityName).size())/Area;
        return risk;
    }

    /**
     * a setter for the status of the facility, such as close or open
     * <p>
     * Take in the following Parameters to construct a more customized Health Bar
     * 
     * @param newStatus                                 The new status that is require to be set
     */
     
    public void setFacilityStatus(boolean newStatus){
        facilityClose=newStatus;
        drawImage();
    }   
    /**
     * The method is called by the instances of Facilities to draw the image of each special facility
     */
    protected void drawImage(){
        facilityImage.scale(150,150);
        setImage(facilityImage);
    }    
    /**
     * a getter for the status of the facility, such as close or open
     * 
     * @return facilityClose                    Return is the facility is closed
     */
    public boolean getFacilityStatus(){
        return facilityClose;
    }   
    /**
     * This method is called by Facilities to check if human is intersecting with this facility. 
     * <p>
     * If the person touches the facility, the person will be removed from the world
     * and added to the list of people inside that facility
     */
    protected void checkIntersectionWithFacility(){
        intersectingPeople= (ArrayList<Human>)getIntersectingObjects(Human.class);
        for(Human h:intersectingPeople){
            if(h!=null&&!h.inFacility){
                // If human is in facility, add human into ArrayList of facility
                // and remove human in the world.
                h.working(true);
                SW.listOfPeople(facilityName).add(h);
                if(!h.healthSituation()) {
                    infectedPopulation++;
                    if(SW.mapExistence()&&SW.returnMap().correspondingFacility().equals(facilityName)){
                        SW.returnMap().setInfectedPopulation(infectedPopulation);
                    }
                }
                SW.removeObject(h);
                // Update the inside facility's people (map of facilities).
                if(SW.mapExistence()&&SW.returnMap().correspondingFacility().equals(facilityName)){
                    SW.returnMap().Update(true,h);
                }
            }  
        }
        intersectingPeople.clear();
    }    

/**
     * This method is called by Facilities to send people out when it's afternoon and people need to go home.
     * <p>
     * The facility will send a random number of people out per act, 
     * until all the people in the list left.
     * 
     *                            
     */
    protected void sendHumanOut(){
        if(SW.listOfPeople(facilityName).size()!=0){
            for(int i=0;i<Greenfoot.getRandomNumber(maximunExitingPerTime);i++){
                int index=Greenfoot.getRandomNumber(SW.listOfPeople(facilityName).size());
                Human h=SW.listOfPeople(facilityName).get(index);
                h.working(false);
                if(!h.healthSituation()) { 
                    infectedPopulation--;
                    if(SW.mapExistence()&&SW.returnMap().correspondingFacility().equals(facilityName)){
                        SW.returnMap().setInfectedPopulation(infectedPopulation);
                    }
                }
                if(SW.mapExistence()&&SW.returnMap().correspondingFacility().equals(facilityName)){
                    SW.returnMap().Update(false,h);
                }
                if(facilityName.equals("school")||facilityName.equals("restaurant")){
                    SW.addObject(h,getX()+60,getY()-facilityImage.getHeight()/2+Greenfoot.getRandomNumber(facilityImage.getHeight()));
                    SW.listOfPeople(facilityName).remove(h);
                }
                else{
                    SW.addObject(h,getX()-60,getY()-facilityImage.getHeight()/2+Greenfoot.getRandomNumber(facilityImage.getHeight()));
                    SW.listOfPeople(facilityName).remove(h);
                }   
                if(SW.mapExistence()&&SW.returnMap().correspondingFacility().equals(facilityName)){
                    SW.returnMap().reDrawImage();
                }
                if(SW.listOfPeople(facilityName).size()==0) break;
            }
        }
    }    

    /**
     * Called by facility when people are inside the building.
     * <p>
     * This will run by every person every act, with a calculated probability, to be infected
     */
    protected void checkingHumanInflation(){
        if(SW.listOfPeople(facilityName).size()!=0){
            for(Human h:SW.listOfPeople(facilityName)){
                if(h.HealthSituation().equals("Healthy")){
                    h.determineInflation(Risk());
                    if(!h.healthSituation()){
                        infectedPopulation++;
                        if(SW.mapExistence() && SW.returnMap().correspondingFacility().equals(facilityName)) {
                            SW.returnMap().setInfectedPopulation(infectedPopulation);
                            SW.returnMap().reDrawImage();
                        }
                    }    
                }
            }    
        }    
    }    
}   
