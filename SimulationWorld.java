import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
/**
 * <b>Introduction:</b>
 * <p>
 * The coronavirus COVID-19 pandemic is the defining global health crisis of our time, 
 * our society is suffering under an almost intolerable burden of loss 
 * and many of us are thinking about how we could contribute to overcoming this unprecedented situation. 
 * Our COVID-19 simulation simulates the impact this pandemic has on humanity and society and how different factors can affect the spread of COVID-19. 
 * </p>
 * <b>How to Play:</b>
 * <p>
 * This simulation simulates people’s daily lives in a small city where there are homes(apartments) 
 * and a couple of facilities that people have access to. 
 * To start, our simulation has a control board where it allows the users to set two statistics, 
 * the awareness of this pandemic in the community and the percentage of the population that always wears a mask in the community. 
 * These two statistics can be adjusted anytime during the simulation by clicking the space button on the keyboard. 
 * This feature gives the user a chance to discover how these two factors can lessen the harm from COVID. For the human population, 
 * we defined four stages of the current health status of a person, 
 * healthy, unconfirmed, confirmed and died. 
 * Every time two people come close to each other, they have a chance of getting infected 
 * and that chance of infection also depends on if they are wearing a mask and their self-awareness of this virus. 
 * We split the human population into three groups, adults, seniors, and students. 
 * Each category of the population has a different immune system strength ranking from the highest is adults and 
 * the lowest is seniors to show that COVID does more harm to the senior population than adults and students. 
 * We have four different facilities in total, a restaurant, a school, a supermarket, and a train station. 
 * Each facility allows people to come in and come out, tracks how many people are currently in the building 
 * and how many are infected with covid, calculates the population density in the facility 
 * as well as the risk of getting infected when a new person enters at a specific time. 
 * The user can also click on any of the facility images and see its interior. 
 * The feature allows the user to see how many people are infected in the facility. 
 * Lastly, our simulation has a data display table at the top of the screen that displays how many days have gone by, 
 * the total number of infected populations, the current infection rate, and the current death rate. 
 * This table allows the user to keep track of how much the virus has spread in the city.
 * </p>
 * <b>Purpose:</b>
 * <p>
 * When we first decided to choose Covid-19 as our simulation topic, 
 * our intention was to educate and inform the user of how the current pandemic is affecting our daily lives. 
 * Throughout the creation process, we learned so much about the details about this virus; 
 * how it spreads, and how it affects disparate populations. We encourage our users to read our instructions 
 * and introductions thoroughly before starting to watch the simulation to get a better understanding of how exactly the simulation works to have a more in depth perception of this Covid 19 virus. 
 * We hope that after watching our simulation, our audience can be more aware of the seriousness of this virus and act responsibly to help stop the spread of Covid-19.
 * </p>
 * @author ꧁༺Emily Chen༻꧂ ꧁༺Leonard Jin༻꧂ ꧁༺Delun Sun༻꧂ ꧁༺Xiao Zhang༻꧂, assist by: ꧁༺Nathan Lo༻꧂ ꧁༺Star Xie༻꧂
 * @version 4.2
 */
public class SimulationWorld extends World
{
    
    // Declare instance variables of words.
    private String Awareness;
    private double awarenessIndex;
    private String Mask;
    private double maskIndex;

    private GreenfootSound SoundOne;
    //private GreenfootSound SoundTwo;
    // Declare instance variables of stats.
    private int Total_Population;
    private int Healthy_Population;
    private int Died_Population;
    private int Confirmed_Population;
    private int Unconfirmed_Population;
    private int Recover_Population;
    private int days=1;
    private double normalTransmitionRate=0.011/300.0;
    // Declare boolean instance variables.
    private boolean worldPause;
    private boolean isKeyOff;
    private boolean mapCreated=false;
    private final int totalAct=1200;
    // Declare instance variables of object.
    private ControlBoard CB;
    private BlackScreen BS;
    private School school;
    private Supermarket supermarket;
    private TrainStation trainStation;
    private Restaurant restaurant;
    private Map map;
    private Home home;
    private HeadLine HD;
    private ArrayList<int[]> data;
    private int[] dataArray;
    // Declare instance variables for human in different facilities with ArrayList.
    private ArrayList<Human> schoolPeople;
    private ArrayList<Human> restaurantPeople;
    private ArrayList<Human> supermarketPeople;
    private ArrayList<Human> trainStationPeople;
    private boolean[] facilitiesCloseStatus={false/*this one is for updating the status*/,
        false/*this one is for school*/,
        false/*this one is for supermarket*/,
        false/*this one is for restaurant*/,
        false/*this one is for train station*/,
    };
    // Declare 2D array to build home at different locations.
    private int[][] homeLocation;
    private int daytime;
    private Clock clock;
    private int dailyNewInfected=0;
    /**
     * Construct the main simulation world. Most animation, including human and facility interaction, is included here
     */
    public SimulationWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1200, 800, 1,false); 
        daytime=0;
        data=new ArrayList<int[]>();
        // Set boolean variables to their initial state.
        worldPause=false;
        isKeyOff=true;
        // Call some methods declared before to set the initial stats and the look.
        setPopulation();
        initialIndependentVariable();
        buildHomes();
        createHeadline();
        addFacilities();
        clock = new Clock(totalAct);
        addObject(clock,75,50);
        setPaintOrder(Restart.class,Data.class,Percent_Board.class,Up.class,Down.class,Statistics.class,ControlBoard.class,BlackScreen.class,Close.class,HomeData.class,Human.class);
        SoundOne=new GreenfootSound("Simulation Music 1.mp3");
        SoundOne.playLoop();
        //SoundTwo=new GreenfootSound("Simulation Music 2.mp3");
    }
    
    private void addFacilities(){
        addSchool();
        addSupermarket();
        addTrainStation();
        addRestaurant();
    }    

    /**
     * Act - do whatever the aa wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act(){
        checkWorldPause();
        if(!worldPause){
            if(!SoundOne.isPlaying()) SoundOne.playLoop();
            checkClickingFacilities();
            daytime++;
            if(daytime==totalAct) {
                daytime=0;
                days++;
                updateHeadline();
                setDataArray();
                dailyNewInfected=0;
                if(facilitiesCloseStatus[0]=true){
                    facilitiesCloseStatus[0]=false;
                    school.setFacilityStatus(facilitiesCloseStatus[1]);
                    supermarket.setFacilityStatus(facilitiesCloseStatus[2]);
                    restaurant.setFacilityStatus(facilitiesCloseStatus[3]);
                    trainStation.setFacilityStatus(facilitiesCloseStatus[4]);
                }   
                
            }
        }
        else {
            if(SoundOne.isPlaying())SoundOne.pause();
        }    
    }    
    /**
     * Return the basic/normal infection index, which is very important in the calculation of human infection
     * 
     * @return normalTransmitionRate                    A very small number that is specific designed for an infection rate about 0.67% (from www.worldometers.info)
     */
    public double normalTransimitioRate(){
        return normalTransmitionRate;
    }    
    /**
     * Return the current stages in a day
     * <p>
     * There are four stages, <b>"Night"</b>, <b>"Morning"</b>, <b>"Noon"</b>, <b>"Evening"</b>
     * 
     * @return dayTime                          The String that discribes the current day stages
     */
    public String dayTime(){
        String dayTime="";
        if(daytime<100) dayTime="Night";
        else if(daytime<450) dayTime="Morning";
        else if(daytime<650) dayTime="Noon";
        else if(daytime<1050) dayTime="Evening";
        else if(daytime<totalAct) dayTime="Night";
        return dayTime;
    }   
    /**
     * Return the act of the day at this point
     * <p>
     * The total acts per day will be 1200 daytime, where when it reach over 1200, it is reseted
     * @return daytime                  The act on that day
     */
    public int dayAct(){
        return daytime;
    }   
    /**
     * Return the map instance. 
     * <p>
     * It will return null if the map does not exist.
     * @return map          The map instance
     */
    public Map returnMap(){
        return map;
    }    
    /**
     * Return the value of infected rate.
     * @return Infected_Rate                    The string in the form XX.X%, keep to one decimal place
     */
    public String Infected_Rate(){
        if (Total_Population != 0) {
            //  Block of code to try
            return (1000 * getInfectedPopulation()/Total_Population)/10.0 + "%";
        } else {
            return "0.0%";
        }
    } 

    /**
     * Return the value of death rate.
     * @return Death_Rate                    The string in the form XX.X%, keep to one decimal place
     */
    public String Death_Rate(){
        if (getInfectedPopulation() != 0) {
            //  Block of code to try
            return (1000*Died_Population/getInfectedPopulation())/10.0 + "%";
        } else {
            //  Block of code to handle errors
            return "0.0%";
        }
    }    

    /**
     * Set the initial value of different type of people. 
     */
    private void setPopulation(){
        Healthy_Population=500;
        Died_Population=0;
        Confirmed_Population=0;
        Unconfirmed_Population=0;
        Recover_Population=0;
        updateTotalPopulation();
        setDataArray();
    }    

    private void setDataArray(){
                dataArray=new int[]{Healthy_Population,
                    Unconfirmed_Population,
                    Confirmed_Population,
                    Died_Population,
                    Recover_Population,
                    Total_Population,
                    dailyNewInfected,
                    getInfectedPopulation()
                };
                data.add(dataArray);
    }   
    /**
     * Set the words in instance variable Awareness as the word in parameter
     * @para Awareness                      the new word the awareness needs to be
     */
    public void UpdatesAwareness(String Awareness){
        this.Awareness=Awareness;
    }   
/**
     * Return the whole set of data
     * @return data                      all the data about the simulation
     */
    public ArrayList<int[]> data(){
        return data;
    }    
    /**
     * Return the value of total population in the simulation
     * @return Total_Population                         The totle population of the town
     */
    public int returnTotalPopulation(){
        return Total_Population;
    }   

    /**
     * Set the words in instance variable Mask as the word in parameter
     * @para Mask                      The new word the mask needs to be
     */
    public void UpdatesMask(String Mask){
        this.Mask=Mask;
    }   
    /**
     * Returns the location of the 8 homes, with x and y value
     * <p>
     * homeLocation[2][8], 2 for 2 values of x and y, 8 for 8 type of houses
     * 
     * return homeLocation                  The array that contains the location of all eight homes
     */
    public int[][] homeLocation(){
        return homeLocation;
    } 

    /**
     * Return the words in instance variable mask.
     * @return Mask                     The current value of the mask
     */
    public String ReturnMask(){
        return Mask;
    }   

    /**
     * Return the words in instance variable awareness.
     * @return Awareness                     The current value of the awareness
     */
    public String ReturnAwareness(){
        return Awareness;
    }   

    /**
     * Check whether the simulation is being paused.
     * <p>
     * the page will be paused when the user press the space bar
     */
    private void checkWorldPause(){
        if(Greenfoot.isKeyDown("space")&&isKeyOff&&!worldPause){
            // If space is being pressed while the simulation is running, call the method 
            // construct_a_pause_page() to create the pause page.
            worldPause=true;
            isKeyOff=false;
            construct_a_pause_page();

        }  
        else if(Greenfoot.isKeyDown("space")&&isKeyOff&&worldPause){
            // If space is being pressed while the simulation is paused, call the method
            // remove_a_pause_page() to remove the pause page.
            worldPause=false;
            isKeyOff=false;
            remove_a_pause_page();
        }    
        else if(!Greenfoot.isKeyDown("space"))isKeyOff=true;
    }   

    /**
     * Create the pause page. 
     */
    private void construct_a_pause_page(){
        BS=new BlackScreen();
        addObject(BS,getWidth()/2,getHeight()/2);
        CB=new ControlBoard();
        addObject(CB,getWidth()/2,getHeight()/2);
    }  

    /**
     * Remove the pause page.
     */
    private void remove_a_pause_page(){
        removeObject(BS);
        removeObject(CB.awareness.UpButtom);
        removeObject(CB.dataButton);
        removeObject(CB.awareness.DownButtom);
        removeObject(CB.awareness.PB);
        removeObject(CB.awareness);
        removeObject(CB.mask.UpButtom);
        removeObject(CB.mask.DownButtom);
        removeObject(CB.mask.PB);
        removeObject(CB.mask);
        removeObject(CB);
    }  
/**
 * This method will update the total number of populations when unconfrimed cases are brought into the town
 */
    public void updateTotalPopulation() {
        Total_Population=Recover_Population+Healthy_Population+Died_Population+Confirmed_Population+Unconfirmed_Population;
    }
    /**
     * This method changes the number of people in each category according to the needs in the parameter, 
     * as well as change the heading
     * <p>
     * imput 0 is for turing a healthy person to unconfirmed
     * <p>
     * imput 1 is for turing a unconfirmed person to confirmed
     * <p>
     * imput 2 is for turing a confirmed person to died
     * <p>
     * imput 3 is for turing a confirmed person to recover
     * <p>
     * imput 4 is for bringing one more unconfirmed person into the twon through the trainstation
     * 
     *@para a                           The instruction number
     */
    public void changeOfData(int a){
        if(a==0){
            Healthy_Population--;
            Unconfirmed_Population++;
            dailyNewInfected++;
        }
        else if(a==1){
            Unconfirmed_Population--;
            Confirmed_Population++;
        }
        else if(a==2){
            Confirmed_Population--;
            Died_Population++;
        }
        else if(a==3){
            Confirmed_Population--;
            Recover_Population++;
        }
        else if(a==4){//specifically for adding unconfirmed from trainstation
            Unconfirmed_Population++;
            updateTotalPopulation();
        }    
        updateHeadline();
    }    

    /**
     * Set the initial words for awareness and mask.
     */
    private void initialIndependentVariable(){
        Awareness="Low";
        Mask="0%";
        changeMaskIndex(4);
        changeAwarenessIndex(2);
    }    

    /**
     * Return the state of the simulation(whether the game is running).
     * @return pausePage                        The variable that repersent the world is pause
     */
    public boolean pausePage(){
        return worldPause;
    }   

    /**
     * Add school to the simulation.
     */
    private void addSchool(){
        schoolPeople=new ArrayList<Human>();
        school=new School();
        addObject(school,100+(getWidth()-200)*1/3,380);
    }    

    /**
     * Add supermarket to the simulation.
     */
    private void addSupermarket(){
        // Use the ArrayList declared before to store human in supermarket.
        supermarketPeople=new ArrayList<Human>();
        supermarket=new Supermarket();
        addObject(supermarket,100+(getWidth()-200)*2/3,380);
    }    

    /**
     * Add train station to the simulation.
     */
    private void addTrainStation(){
        // Use the ArrayList declared before to store human in train station.

        trainStationPeople=new ArrayList<Human>();
        trainStation=new TrainStation();
        addObject(trainStation,100+(getWidth()-200)*2/3,540);
    }    

    /**
     * Add restaurant to the simulation.
     */
    private void addRestaurant(){
        // Use the ArrayList declared before to store human in restaurant.
        restaurantPeople=new ArrayList<Human>();
        restaurant=new Restaurant();
        addObject(restaurant,100+(getWidth()-200)*1/3,540);
    }    

    /**
     * Check whether facilities are being clicked.
     */
    private void checkClickingFacilities(){
        if(!mapCreated&&Greenfoot.mouseClicked(school)){
            // If school is being clikced, display the human in school.
            map=new Map(schoolPeople,"school");
            addObject(map,school.getX(),school.getY());
            mapCreated=true;
        }   
        else if(!mapCreated&&Greenfoot.mouseClicked(supermarket)){
            // If supermarket is being clikced, display the human in supermarket.
            map=new Map(supermarketPeople,"supermarket");
            addObject(map,supermarket.getX(),supermarket.getY());
            mapCreated=true;
        }   
        else if(!mapCreated&&Greenfoot.mouseClicked(trainStation)){
            // If train station is being clikced, display the human in train station.
            map=new Map(trainStationPeople,"trainStation");
            addObject(map,trainStation.getX(),trainStation.getY());
            mapCreated=true;

        }   
        else if(!mapCreated&&Greenfoot.mouseClicked(restaurant)){
            map=new Map(restaurantPeople,"restaurant");
            addObject(map,restaurant.getX(),restaurant.getY());
            mapCreated=true;
        }   
    } 

    /**
     * Set the state of map(whether map is created). This will help turn on or off the map
     * 
     * @para status                     The new status the map should be
     */
    public void mapExistence(boolean status){
        mapCreated=status;  
    }   

    /**
     * Return the state of whether map is created.
     * 
     * @para mapExistence                     The status of the map
     */
    public boolean mapExistence(){
        return mapCreated ;  
    }

    /**
     * Add house to the simulation.
     */
    private void buildHomes(){
        homeLocation=new int[][] {
            {100+(getWidth()-200)*0/3,
             100+(getWidth()-200)*0/3,
             100+(getWidth()-200)*0/3,
             100+(getWidth()-200)*1/3 ,
             100+(getWidth()-200)*2/3,
             100+(getWidth()-200)*3/3,
             100+(getWidth()-200)*3/3,
             100+(getWidth()-200)*3/3},
            {(getHeight()-300)*2/2+200,
             (getHeight()-300)*1/2+200,
             (getHeight()-300)*0/2+200,
             (getHeight()-300)*0/2+200,
             (getHeight()-300)*0/2+200,
             (getHeight()-300)*0/2+200,
             (getHeight()-300)*1/2+200,
             (getHeight()-300)*2/2+200}};
        for(int i=0;i<8;i++){
            home=new Home(i+1);
            addObject(home,homeLocation[0][i],homeLocation[1][i]);
        }    
    }    
    /**
     * return the people who is or was infected by covid, including recover people
     */
    private int getInfectedPopulation() {
        return Died_Population+Confirmed_Population+Unconfirmed_Population+Recover_Population;
    }
    
    /**
     * Add the headline(display the stats and words)to the simulation.
     */
    private void createHeadline(){
        HD=new HeadLine(days,getInfectedPopulation(),Infected_Rate(),Death_Rate());
        addObject(HD,getWidth()/2,50);
    }     
/**
 * Update the headline
 */
    private void updateHeadline(){
        HD.Update(days,getInfectedPopulation(),Infected_Rate(),Death_Rate());
    }     
/**
 * Return the total number of days pass
 * @return days                         total days pass
 */
    public int days(){
        return days;
    }    
/**
 * Helps the facility to get the list of people inside
 * @para place                      the name of the specify facility
 * @return listOfPeople             the list that contains all the people's address in that paticular facility
 */
    public ArrayList<Human> listOfPeople(String place){
        if(place.equals("school")) return schoolPeople;
        else if(place.equals("restaurant")) return restaurantPeople;
        else if(place.equals("supermarket")) return supermarketPeople;
        else if(place.equals("trainStation")) return trainStationPeople;
        else return null;
    }    
/**
 * Helps other class to get the specific facility instance
 * @para place                      the name of the specify facility
 * @return facility             the facility indecated in the parameter
 */
    public Facilities facility(String place){
        if(place.equals("school")) return school;
        else if(place.equals("restaurant")) return restaurant;
        else if(place.equals("supermarket")) return supermarket;
        else if(place.equals("trainStation")) return trainStation;
        else return null;
    }    
    /**
     * The index depending on the variabe mask, which takes a huge impact on the infection rate
     * @return maskIndex                the index of mask 
     */
    public double maskIndex(){
        return maskIndex;
    }   
/**
     * The index depending on the variabe awareness, which takes a huge impact on the infection rate
     * @return awarenessIndex                the index of awareness
     */
    public double awarenessIndex(){
        return awarenessIndex;
    }   
/**
     * Set the mask index to a specific value, which indecates by the parameter
     * @para Level                the slot which helps to set the mask index

     */
    public void changeMaskIndex(int Level){
        if(Level==0) this.maskIndex=0.48;
        else if(Level==1) this.maskIndex=0.65;
        else if(Level==2) this.maskIndex=0.80;
        else if(Level==3) this.maskIndex=0.92;
        else if(Level==4) this.maskIndex=1.0;
        else if(Level==5) this.maskIndex=1.1;
    }   
/**
     * Set the awareness index to a specific value, which indecates by the parameter.
     * <p>
     * This will also help to close down the trainStation when peopl's awareness changes
     * @para Level                the slot which helps to set the mask index

     */
    public void changeAwarenessIndex(int Level){
        if(Level==2) {
            this.awarenessIndex=0.55;
            facilitiesCloseStatus[0]=true;
            facilitiesCloseStatus[4]=false;
        }
        else if(Level==1) {
            this.awarenessIndex=1.0;
            facilitiesCloseStatus[0]=true;
            facilitiesCloseStatus[4]=true;
            facilitiesCloseStatus[3]=false;
            facilitiesCloseStatus[1]=false;
        }
        else if(Level==0) {
            this.awarenessIndex=1.15;
            facilitiesCloseStatus[0]=true;
            facilitiesCloseStatus[3]=true;
            facilitiesCloseStatus[1]=true;
            facilitiesCloseStatus[4]=true;
        }
    }   
}
