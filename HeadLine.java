import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * HeadLine class
 * 
 * The headline of simulation that will display number of days passed, number of 
 * people infected, infection rate and death rate.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class HeadLine extends Actor
{
    // Declare instance variable of Image.
    private GreenfootImage bar;
    
    // Declare and set the background colour and font.
    private Color backgroundColor=new Color(71,67,100);
    private Font f=new Font("Comic Sans MS",true,false,30);
    
    // Declare instance variables of stats in headline.
    private int infected, days;
    private String infectedRate,deathRate;
    
    /**
     * Constructor of HeadLine that set initial stats and image for headline.
     * 
     * @param days      Number of days passed.
     * @param infected  Number of people infected.
     * @param infectedRate  The infection rate.
     * @param deathRate     The death rate.
     */
    public HeadLine(int days,int infected, String infectedRate, String deathRate ){
        this.infected=infected;
        this.infectedRate=infectedRate;
        this.deathRate=deathRate;
        this.days=days;
        bar=new GreenfootImage(1200,100);
        bar.setColor(backgroundColor);
        bar.fillRect(0,0,1200,100);
        bar.setColor(Color.WHITE);
        bar.setFont(f);
        bar.drawString("Days: "+days,150,60);
        bar.drawString("infected: "+infected+" ppl",300,60);
        bar.drawString("infectedRate: "+infectedRate,580,60);
        bar.drawString("deathRate: "+deathRate,900,60);
        this.setImage(bar);
        
    }    
    
    /**
     * Update the latest stats and image of headline.
     * 
     * @param days      Number of days passed.
     * @param infected  Number of people infected.
     * @param infectedRate  The infection rate.
     * @param deathRate     The death rate.
     */
    public void Update(int days,int infected, String infectedRate, String deathRate ){
        this.infected=infected;
        this.infectedRate=infectedRate;
        this.deathRate=deathRate;
        this.days=days;
        bar=new GreenfootImage(1200,100);
        bar.setColor(backgroundColor);
        bar.fillRect(0,0,1200,100);
        bar.setColor(Color.WHITE);
        bar.setFont(f);
        bar.drawString("Days: "+days,150,60);
        bar.drawString("infected: "+infected+" ppl",300,60);
        bar.drawString("infectedRate: "+infectedRate,580,60);
        bar.drawString("deathRate: "+deathRate,900,60);
        this.setImage(bar);
        
        
    }   
}