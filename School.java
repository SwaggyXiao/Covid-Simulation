import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
/**
 * School class
 * 
 * A facility that can accomodate people.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class School extends Facilities
{
    
    /**
     * Constructor of School that set the initial stats and image
     * for school.
     */
    public School(){
        drawImage();
        Area=500;
        infectionRate=0.8;
    }
    /**
     * this method will overide and draw the image of the transtation, as well as add the close sign to the station if it's closed
     */
    protected void drawImage(){
        facilityImage = new GreenfootImage("School.png");
        if(facilityClose) {
            facilityImage.drawImage(new GreenfootImage("Close Down.png"),256-232,200);
        }
        facilityImage.scale(150,150);
        setImage(facilityImage);
        
    }    
}
