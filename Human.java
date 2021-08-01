import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.Arrays;
/**
 * Human class
 * 
 * The people in the simulation, can be infected, died and recovred, and is divided
 * into 3 different categoires by age, different categories have different shape being represented.
 * 
 * @author ꧁༺Xiao Zhang༻꧂
 * @version 5.1
 */
public abstract class Human extends Actor
{
    // Declare instance variable for human's health status.
    
    /**
     * peopls's health status
     */
    protected String Health; //4 Levels: "Healthy", "Unconfirmed", "Confirmed","Died"
    
    /**
     * the lowest age of the young range group
     */
    protected int lower_Edge_Of_Young;
    /**
     * the highest age of the young range group
     */
    protected int higher_Edge_Of_Young;
    /**
     * the lowest age of the old range group
     */
    protected int lower_Edge_Of_Old;
    /**
     * the highest age of the old range group
     */
    protected int higher_Edge_Of_Old;
    /**
     * the side length of the person image
     */
    protected int side_Length= 10;
    /**
     * the speed people shake arround every act
     */
    protected int shakingSpeed=1;
    /**
     * the strength of the immune system, which will help people fight the virus directly
     */
    protected int Immune_System_Strength;
    /**
     * the age of the human, which will affect how well they recover
     */
    protected int age;
    /**
     * the x coordinate of the house the person belongs to
     */
    protected int houseX;
    /**
     * the y coordinate of the house the person belongs to
     */
    protected int houseY;
    
    /**
     * the station number they are at in their pathway
     */
    
    protected int stationInSchedule;
    
    /**
     * the quarantineDays people will have if they are infected, healthy is 0
     */
    protected int quarantineDays=0;
    
    /**
     * the started date of the person's quarantine
     */
    protected int quarantineStartedDays=0;
    
    /**
     * the basic demage virus will have on the human (the calculation below will based on this and make things randown)
     */
    protected int demageByVirus;
    /**
     * the total blood the human have, which is defaut 100
     */
    protected int totalBlood=100;
    /**
     * the speed for the human to move, depending on age
     */
    protected int movingSpeed;
    /**
     * the border human will have if they are in the facility
     */
    protected int[] border;
    
    // Declare the color for human corresponding to their health situaton.
    /**
     * the color of the human, according to its health status
     */
    protected Color Situation;
   
    // Declare instance variable of SimulationWorld.
    /**
     * the world people are in
     */
    protected SimulationWorld SW;
    
    // Declare boolean instance variables.
    /**
     * is the person inside a facility
     */
    protected boolean inFacility;
    /**
     * is the person inside the house
     */
    protected boolean inHouse;
    /**
     * can the person spread virus
     */
    protected boolean transmitionRate;
    
    // 2D array to declare human's pathway.
    /**
     * the Go pathway where human will use when the go to work
     */
    protected int[][] pathwayGo;
    /**
     * the back pathway where human will use when they return from work
     */
    protected int[][] pathwayBack;
    
    // Declare boolean instance variables.
    /**
     * is the human on their way to work
     */
    protected boolean goToWork;
    /**
     * are the people inside the facility and working right now
     */
    protected boolean working;
    /**
     * should people go out on that day?
     */
    protected boolean stayAtHome=false;
    
    // Declare instance variables of object.
    /**
     * the facility they are in right now if appicable
     */
    protected Facilities f;
    
    /**
     * The different types of human's schedule.
     * 
     * @param SW    The world that human belongs to.
     */
    protected abstract void scheduleDecision(SimulationWorld SW);

    /**
     * Called by Greenfoot when an object of this class is added to 
     * the World.
     * 
     * @param w The World being added to.
     */
    public void addedToWorld(World w){
        SW=(SimulationWorld) w;
        if(SW.dayTime().equals("Morning")){
            goToWork=true;
            stationInSchedule=0;
        }
        else if(SW.dayTime().equals("Evening")){
            goToWork=false;
            stationInSchedule=0;
            scheduleDecision(SW);
            if(Health.equals("Unconfirmed")&&quarantineStartedDays+quarantineDays<=SW.days()&&quarantineDays!=0){
                stayAtHome=true;
                SW.changeOfData(1);
                setHealthSituation("Confirmed");
            }    
        }
    }    

    /**
     * Return whether human needs to quarantine.
     */
    public boolean stayAtHomeForQuarantine(){
        return stayAtHome;
    }    

    /**
     * Act - do whatever the Human wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        if(!SW.pausePage()){
            // Define human's movement in facilities.
            if(inFacility) {
                movementInBuilding(border[0],border[1],border[2],border[3]);
                movingAround();
            }
            else{
                schedule();
                movingAround();
            }
        }
    } 

    /**
     * Return the pathway that human goes to facility.
     * @para input              the model of the correct pathway of the human, which modefy by the building number
     * @return result           The actural array of people's pathway
     */
    public static int[][] deepCopyPathway(int[][] input){
        int[][] result=new int[input.length][input[0].length];
        result[0]=Arrays.copyOf(input[0], input[0].length);
        result[1]=Arrays.copyOf(input[1], input[1].length);
        return result;
    }  

    /**
     * Return the color that human has corresponding to their health situation.
     * @return ColorConversion              the color coresponded to the health status of human
     */
    protected Color ColorConversion(String Health){
        if(Health.equals("Healthy")) return Color.GREEN;
        else if(Health.equals("Unconfirmed"))return Color.RED;
        else if(Health.equals("Confirmed"))return Color.MAGENTA;
        else if(Health.equals("Died"))return Color.BLACK;
        else if(Health.equals("Recover"))return Color.YELLOW;
        return Color.WHITE;
    }

    /**
     * Return human's health situation.
     * @return Health           the current health situation of the human
     */
    protected String HealthSituation( ){
        return Health;
    }

    /**
     * Set human's health situation.
     * 
     * @param Health    The new human's health situation.
     */
    protected void setHealthSituation(String Health){
        this.Health=Health;
        if(Health.equals("Healthy")||Health.equals("Recover")){
            transmitionRate=true;
        }    
        else{
            transmitionRate=false;
        }    
        drawImage();
    } 

    /**
     * Human's movement in simulation.
     */
    protected void movingAround(){
        setLocation(getX()+(Greenfoot.getRandomNumber(3)-1)*shakingSpeed,getY()+(Greenfoot.getRandomNumber(3)-1)*shakingSpeed);
    }    

    /**
     * Draw the image of human.
     */
    protected abstract void drawImage();

    /**
     * Set human's age.
     * 
     * @param age   The age of human.
     */
    protected abstract void setAge(int age);

    /**
     * Return whether human is being infected.
     * 
     * @param   Mask    Human's status on how much they put on their mask.
     * @param   AW      Human's awareness of covid.
     * @param   facility    The dangeroud of human's facility.
     * @param   normalTransmitionRate   The coivd's transmission rate.
     * @return determineInfection           if the person is infected
     */
    protected boolean determineInfection(double Mask, double AW, double facility, double normalTransmitionRate){
        double risk = Mask*AW*facility*normalTransmitionRate*1000000;
        int index=Greenfoot.getRandomNumber(1000000);
        return index<=risk;
    }    

    /**
     * Human's movement inside facilities.
     * 
     * @param   leftBoarder     Facility's left boarder.
     * @param   rightBoarder    Facility's right boarder.
     * @param   upBoarder       Facility's up boarder.
     * @param   downBoarder     Facility's down boarder.
     */
    protected void movementInBuilding(int leftBoarder,int rightBoarder,int upBoarder,int downBoarder){
        if(this.getX()<leftBoarder) this.setLocation(leftBoarder,getY());
        if(this.getX()>rightBoarder) this.setLocation(rightBoarder,getY());
        if(this.getY()<upBoarder) this.setLocation(getX(),upBoarder);
        if(this.getY()>downBoarder) this.setLocation(getX(),downBoarder);
    } 

    /**
     * The human's status when entering into facilities.
     * 
     * @param border    The border of facilities.
     */
    public void enteringStatusInFacility(int[] border){
        this.border=border;
        inFacility=true;
        side_Length=side_Length*3/2;
        drawImage();
        shakingSpeed=2;
    }

    /**
     * The human's status when leaving facilities.
     */
    public void leavingStatusInFacility(){
        inFacility=false;
        side_Length=side_Length*2/3;
        drawImage();
        shakingSpeed=1;
    }    

    /**
     * Set human's inHouse status when human entering the home.
     */
    public void enteringHome(){
        inHouse=true;
    }

    /**
     * Set human's inHouse status when human leaving the home.
     */
    public void leavingHome(){
        inHouse=false;
    }    

    /**
     * Return whether human are close enough to others.
     * @para X1             the x coordinate of the first object
     * @para Y1             the y coordinate of the first object
     * @para X2             the x coordinate of the second object
     * @para Y2             the y coordinate of the second object
     * @para distanceAllowed             the distance allowed to be approaching
     * @return closeEnough      tells people whether first obejct is close enough to the second object
     */
    protected boolean closeEnough(int X1, int Y1, int X2, int Y2, int distanceAllowed){
        return Math.hypot(Math.abs(X1-X2), Math.abs(Y1-Y2))<distanceAllowed;
    }   

    /**
     * Return the distance between two people.
     * @para X1             the x coordinate of the first object
     * @para Y1             the y coordinate of the first object
     * @para X2             the x coordinate of the second object
     * @para Y2             the y coordinate of the second object
     * @return getDistance  the distance between two object
     */
    protected double getDistance(int X1, int X2, int Y1, int Y2){
        return Math.hypot(Math.abs(X1-X2), Math.abs(Y1-Y2));
    }    

    /**
     * Return the current health situation for human.
     * @return transmitionRate              tells people whether the human is able to spread virus
     */
    public boolean healthSituation(){
        return transmitionRate;
    }   

    /**
     * Human's schedule when they are outside of facilities.
     */
    protected void schedule() 
    {
        if(goToWork){
            if(!closeEnough(this.getX(),this.getY(),pathwayGo[0][stationInSchedule],pathwayGo[1][stationInSchedule],5)){
                int distanceBetweenX=pathwayGo[0][stationInSchedule]-getX();
                int distanceBetweenY=pathwayGo[1][stationInSchedule]-getY();
                setLocation((int)(getX()+distanceBetweenX*movingSpeed/Math.hypot(distanceBetweenX,distanceBetweenY)),(int)(getY()+distanceBetweenY*movingSpeed/Math.hypot(distanceBetweenX,distanceBetweenY)));
            }    
            else if(stationInSchedule!=pathwayGo[0].length-1)stationInSchedule++;
        }  
        else{
            if(!closeEnough(this.getX(),this.getY(),pathwayBack[0][stationInSchedule],pathwayBack[1][stationInSchedule],5)){
                int distanceBetweenX=pathwayBack[0][stationInSchedule]-getX();
                int distanceBetweenY=pathwayBack[1][stationInSchedule]-getY();
                setLocation((int)(getX()+distanceBetweenX*movingSpeed/Math.hypot(distanceBetweenX,distanceBetweenY)),(int)(getY()+distanceBetweenY*movingSpeed/Math.hypot(distanceBetweenX,distanceBetweenY)));
            }    
            else if(stationInSchedule!=pathwayGo[0].length-1)stationInSchedule++;
        }      
        // Add your action code here.
    }    

    /**
     * Human's pathway when they go back to home.
     * @para pathwayGo          the pathway of the human going to work on that day (no house included)
     * @para houseX             the x coordinate of the house people belongs to
     * @para houseY             the y coordinate of the house people belongs to
     * @return backPathway      the pathway the person will return on that day (house included)
     */
    protected static int[][] backPathway(int[][] pathwayGo, int houseX, int houseY){
        int[][] backPath=new int[pathwayGo.length][pathwayGo[0].length];
        for(int i=0;i<backPath.length;i++){
            for(int j=0;j<backPath[0].length-1;j++){
                backPath[i][j]=pathwayGo[i][pathwayGo[i].length-2-j];
            } 
        }    
        backPath[0][backPath[0].length-1]=houseX;
        backPath[1][backPath[1].length-1]=houseY;
        return backPath;
    }    

    /**
     * Set whether human is working.
     * 
     * @param work  Whether human is working.
     */
    public void working(boolean work){
        working=work;
    }    
/**
 * Determine if the person is infected.
 * <p>
 * If the person is infected, then set the health status into Unconfirmed
 * @para risk           the risk that human may be infected, associate with the facility
 */
    public void determineInflation(double risk){
        if(determineInfection(SW.maskIndex()/*Mask*/,SW.awarenessIndex()/*AW*/,risk/*Facility*/,SW.normalTransimitioRate())) {
            setHealthSituation("Unconfirmed");
            SW.changeOfData(0);
            quarantineDays=determineQuarantineDays();
            quarantineStartedDays=SW.days();
        }
    }    

    /**
     * Return the number of days that human need to quarantine.
     * @return determineQuarantineDays          the number of unconfirmed days people will be pass
     */
    protected int determineQuarantineDays(){
        int random=Greenfoot.getRandomNumber(85);
        if(random<=3) return 2;
        else if(random<=3+4) return 3;
        else if(random<=3+4+5) return 4;
        else if(random<=3+4+5+6) return 5;
        else if(random<=3+4+5+6+8) return 6;
        else if(random<=3+4+5+6+8+10) return 7;
        else if(random<=3+4+5+6+8+10+12) return 8;
        else if(random<=3+4+5+6+8+10+12+10) return 9;
        else if(random<=3+4+5+6+8+10+12+10+8) return 10;
        else if(random<=3+4+5+6+8+10+12+10+8+6) return 11;
        else if(random<=3+4+5+6+8+10+12+10+8+6+5) return 12;
        else if(random<=3+4+5+6+8+10+12+10+8+6+5+4) return 13;
        else if(random<=3+4+5+6+8+10+12+10+8+6+5+4+3) return 14;
        else return 0;
    }    
    
    /**
     * Human who are infected and try to fight against virus.
     */
    protected void fightigAgainstVirus(){
        int random = Greenfoot.getRandomNumber(100);
        demageByVirus=23;
        if(random<=8) demageByVirus=demageByVirus-6;
        else if(random<=22) demageByVirus=demageByVirus-4;
        else if(random<=34) demageByVirus=demageByVirus-2;
        else if(random<=61) demageByVirus=demageByVirus;
        else if(random<=81) demageByVirus=demageByVirus+2;
        else if(random<=99) demageByVirus=demageByVirus+5;
        totalBlood-=demageByVirus;
        totalBlood+=Immune_System_Strength;
        // If no blood left for human, then human is died.
        if(totalBlood<0) {
            setHealthSituation("Died");
            SW.changeOfData(2);
        }    
        // Human will be recovered if they have enough blood and good strength again. 
        else if(totalBlood>=30&&Immune_System_Strength>=23){
            setHealthSituation("Recover");
            SW.changeOfData(3);
            stayAtHome=false;
        }    
        if(Greenfoot.getRandomNumber(100)<50)Immune_System_Strength+=2;
        else Immune_System_Strength+=3;
    }    
}
