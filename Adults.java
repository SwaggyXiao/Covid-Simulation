import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Adults class
 * 
 * One type of human(adults), can be added with infected status into the simulation.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Adults extends Human
{
    // Declare instance variable of Image.
    private GreenfootImage Adults;

    /**
     * Constructor for Adults that create adults and set initial stats.
     * 
     * @param   age     Adults' age.
     * @param   Health  Adults' health situation.
     * @param   pathway Adults' pathway in simulation.
     * @param   houseX  House's x-coordinate.
     * @param   houseY  House's y-coordinate.
     */
    public Adults(int age, String Health, int[][] pathway,int houseX,int houseY){
        movingSpeed=12;
        setAgeRange();
        setAge(age);
        setHealthSituation(Health);
        inFacility=false;
        this.pathwayGo=Human.deepCopyPathway(pathway);
        this.pathwayBack=backPathway(pathway, houseX,  houseY);
    }   

    /**
     * Constructor for Adults that is initially infected and set their intial stats.
     * 
     * @param   age     Adults' age.
     * @param   houseX  House's x-coordinate.
     * @param   houseY  House's y-coordinate.
     * @param   houseNumber     The number that correspond to house's location.
     */
    public Adults(int age,int houseX,int houseY,int houseNumber){
        movingSpeed=10;
        setAgeRange();
        setAge(age);
        setHealthSituation("Unconfirmed");
        quarantineDays=determineQuarantineDays();
        quarantineStartedDays=1;
        inFacility=false;
        this.pathwayGo=Human.deepCopyPathway(new PathwayOfHuman(houseNumber).pathway());
        this.pathwayBack=backPathway(new PathwayOfHuman(houseNumber).pathway(), houseX,  houseY);
        working(true);
    }   

      
    /**
     * Draw the image for adults.
     */
    public void drawImage(){
        Adults=new GreenfootImage(side_Length+1, side_Length+1);
        Situation= ColorConversion(Health);
        Adults.setColor(Situation);
        Adults.fillRect(0,0,side_Length,side_Length);
        Adults.setColor(Color.BLACK);
        Adults.drawRect(0,0,side_Length,side_Length);
        this.setImage(Adults);
    } 

    /**
     * Set the age for adults.
     */
    protected void setAge(int age){
        this.age=age;
        if(age>=lower_Edge_Of_Young&&age<=higher_Edge_Of_Young){
            Immune_System_Strength=13;
            
        }    
        else if(age>=lower_Edge_Of_Old&&age<=higher_Edge_Of_Old){
            Immune_System_Strength=10;
        }    

    }  

    /**
     * Set the age range for adults.
     */
    private void setAgeRange(){
        super.lower_Edge_Of_Young = 19;
        super.higher_Edge_Of_Young = 45;
        super.lower_Edge_Of_Old = 46;
        super.higher_Edge_Of_Old = 64;
    }    

    /**
     * Set adults' schedule randomly.
     * 
     * @param SW    The world that adults belong to.
     */
    protected void scheduleDecision(SimulationWorld SW){
        int random = Greenfoot.getRandomNumber(20+80+80+50);
        
        if(random<20) {
             f=SW.facility("school");
        }    
        else if(random<20+80) {
             f=SW.facility("supermarket");
        }   
        else if(random<20+80+80) {
             f=SW.facility("restaurant");
        }   
        else if(random<20+80+80+50) {
             f=SW.facility("trainStation");
        }
        if(f.getFacilityStatus()) stayAtHome=true;
        else{
            stayAtHome=false;
        pathwayGo[0][pathwayGo[0].length-1]=f.getX();
        pathwayGo[1][pathwayGo[1].length-1]=f.getY();
        }
    }    

    
}
