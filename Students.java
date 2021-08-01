import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Students class
 * 
 * One type of human(students).
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Students extends Human
{
    // Declare instance variable of Image.
    private GreenfootImage Students;
    
    // Declare instance variable of Students' stat.
    private int stationInSchedule;
    
    /**
     * Constructor for Students that create students and set initial stats.
     * 
     * @param   age     Students' age.
     * @param   Health  Students' health situation.
     * @param   pathway Students' pathway in simulation.
     * @param   houseX  House's x-coordinate.
     * @param   houseY  House's y-coordinate.
     */
    public Students(int age, String Health,int[][] pathway,int houseX,int houseY){
        movingSpeed=13;
        stationInSchedule=0;
        goToWork=true;
        setAgeRange();
        setAge(age);
        setHealthSituation(Health);
        inFacility=false;
        
        this.pathwayGo=Human.deepCopyPathway(pathway);
        this.pathwayBack=backPathway(pathway, houseX,  houseY);
    }    


    /**
     * Draw the image for students.
     */
    public void drawImage(){
        Students=new GreenfootImage(side_Length+1, side_Length+1);
        int[] xPoints={0,side_Length,side_Length/2};
        int[] yPoints={side_Length,side_Length,0};
        Situation= ColorConversion(Health);
        Students.setColor(Situation);
        Students.fillPolygon(xPoints,yPoints,3);
        Students.setColor(Color.BLACK);
        Students.drawPolygon(xPoints,yPoints,3);
        this.setImage(Students);
    } 

    /**
     * Set the age for students.
     */
    protected void setAge(int age){
        this.age=age;
        if(age>=lower_Edge_Of_Young&&age<=higher_Edge_Of_Young){
            Immune_System_Strength=8;
        }    
        else if(age>=lower_Edge_Of_Old&&age<=higher_Edge_Of_Old){
            Immune_System_Strength=11;
        }    
    }  

    /**
     * Set the age range for students.
     */
    private void setAgeRange(){
        super.lower_Edge_Of_Young = 0;
        super.higher_Edge_Of_Young = 10;
        super.lower_Edge_Of_Old = 11;
        super.higher_Edge_Of_Old = 18;
    }      
    
    /**
     * Set students' schedule randomly.
     * 
     * @param SW    The world that students belong to.
     */
    protected void scheduleDecision(SimulationWorld SW){
        int random = Greenfoot.getRandomNumber(140+60+60+30);
        if(random<140) {
             f=SW.facility("school");
        }    
        else if(random<140+60) {
             f=SW.facility("supermarket");
        }   
        else if(random<140+60+60) {
             f=SW.facility("restaurant");
        }   
        else if(random<140+60+60+30) {
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
