import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Seniors class
 * 
 * One type of human(seniors).
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Seniors extends Human
{
    // Declare instance variable of Image.
    private GreenfootImage Seniors;
    
    /**
     * Constructor for Seniors that create seniors and set initial stats.
     * 
     * @param   age     Seniors' age.
     * @param   Health  Seniors' health situation.
     * @param   pathway Seniors' pathway in simulation.
     * @param   houseX  House's x-coordinate.
     * @param   houseY  House's y-coordinate.
     */
    public Seniors(int age, String Health,int[][] pathway,int houseX,int houseY){
        movingSpeed=11;
        setAgeRange();
        setAge(age);
        setHealthSituation(Health);
        inFacility=false;
        this.pathwayGo=Human.deepCopyPathway(pathway);
        this.pathwayBack=backPathway(pathway, houseX,  houseY);
    }    


    /**
     * Set the age for seniors.
     */
    protected void setAge(int age){
        super.age=age;
        if(age>=lower_Edge_Of_Young&&age<=higher_Edge_Of_Young){
            Immune_System_Strength=5;
        }    
        else if(age>=lower_Edge_Of_Old&&age<=higher_Edge_Of_Old){
            Immune_System_Strength=3;
        }    
    }  
    
    /**
     * Draw the image for seniors.
     */
    public void drawImage(){
        Seniors=new GreenfootImage(side_Length+1, side_Length+1);
        Color Situation= ColorConversion(Health);
        Seniors.setColor(Situation);
        Seniors.fillOval(0,0,side_Length,side_Length);
        Seniors.setColor(Color.BLACK);
        Seniors.drawOval(0,0,side_Length,side_Length);
        this.setImage(Seniors);
    } 
    
    /**
     * Set the age range for seniors.
     */
    private void setAgeRange(){
        super.lower_Edge_Of_Young = 65;
        super.higher_Edge_Of_Young = 72;
        super.lower_Edge_Of_Old = 73;
        super.higher_Edge_Of_Old = 100;
    }    
    
    /**
     * Set seniors' schedule randomly.
     * 
     * @param   SW  The world that seniors belong to.
     */
    protected void scheduleDecision(SimulationWorld SW){
        int random = Greenfoot.getRandomNumber(10+50+80+30);
        
        if(random<10) {
             f=SW.facility("school");
        }    
        else if(random<10+50) {
             f=SW.facility("supermarket");
        }   
        else if(random<10+50+80) {
             f=SW.facility("restaurant");
        }   
        else if(random<10+50+80+30) {
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
