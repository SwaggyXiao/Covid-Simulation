import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
/**
 * Restaurant class
 * 
 * A facility that can accomodate people.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Restaurant extends Facilities
{
    
    /**
     * Constructor of Restaurant that set the initial stats and image
     * for restaurant. 
     */
    public Restaurant(){
        drawImage();
        Area=680;
        infectionRate=2.3;
    }
    /**
     * this method will overide and draw the image of the transtation, as well as add the close sign to the station if it's closed
     */
     protected void drawImage(){
        facilityImage = new GreenfootImage("Restaurant.png");
        if(facilityClose) {
            facilityImage.drawImage(new GreenfootImage("Close Down.png"),256-232,200);
        }
        facilityImage.scale(150,150);
        setImage(facilityImage);
        
    }    
}
