import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
/**
 * TrainStation class
 * 
 * A facility that can accomodate people.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TrainStation extends Facilities
{
    private Adults unconfirmedPatient;// the patient who comes from outside

    /**
     * Constructor of TrainStation that set the initial stats and image
     * for train station.
     */
    public TrainStation(){
        drawImage();
        Area=300;
        infectionRate=1.7;
    }
    /**
     * This method is called by the Greenfoot system when this actor has been inserted into the world.
     * <p>
     * Take in the following Parameters to construct a more customized Health Bar
     * 
     * @param w                                 The World being added to.
     */
    public void addedToWorld(World w){
        SW=(SimulationWorld)w;
        facilityName="trainStation";
        addUnconfirmedPeople();
    }    
    /**
     * this method will overide and draw the image of the transtation, as well as add the close sign to the station if it's closed
     */
    protected void drawImage(){
        facilityImage = new GreenfootImage("Train Station.png");
        if(facilityClose) {
            facilityImage.drawImage(new GreenfootImage("Close Down.png"),256-232,200);
        }
        facilityImage.scale(150,150);
        setImage(facilityImage);
        
    }    
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
            else if(SW.dayAct()==1) if(Greenfoot.getRandomNumber(3)==0)addUnconfirmedPeople();
            checkingHumanInflation();
        }
    }  
    /**
     * add one unconfirmed person into the city
     */
    private void addUnconfirmedPeople(){
        int houseNumber=Greenfoot.getRandomNumber(8);//randomly enter one of the eight house
        unconfirmedPatient= new Adults(Greenfoot.getRandomNumber(46)+19,
                SW.homeLocation()[0][houseNumber],//x coordinate of the house
                SW.homeLocation()[1][houseNumber],//y coordinate of the house
                1+houseNumber);
       SW.listOfPeople(facilityName).add(unconfirmedPatient);
        infectedPopulation++;
        if(SW.mapExistence()&&SW.returnMap().correspondingFacility().equals(facilityName)){
            SW.returnMap().setInfectedPopulation(infectedPopulation);
            SW.returnMap().Update(true, unconfirmedPatient);
        }
        SW.changeOfData(4);
    }    
    
   
    
}
