import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Buttom class 
 * 
 * Use to clikced by users.
 * 
 * @author ꧁༺Delun Sun༻꧂ ꧁༺Xiao Zhang༻꧂
 * @version 4.2
 */
public abstract class Buttom extends Actor
{
    // Declare instancce variable of mouse.
    protected MouseInfo m;
    
    // Declare instance variables of Images.
    protected GreenfootImage idleImage;
    protected GreenfootImage mouseOverImage;
    String image;
    
    // Declare boolean instance variables.
    protected boolean mouseOver;
    protected boolean clicked;
    protected GreenfootSound GS;
    // Declare instance variable of SimulationWorld. 
    
    /**
     * Constructor of Buttom that used to set image for its subclass.
     * @param image     The image for buttom's subclass.
     */
    public Buttom (String image){
        mouseOver = false;
        //see if mouse is on top of the buttom
        clicked = false;//see if it is been clicked
        this.image=image;//get which image it is
        initImages();//set the image
        GS=new GreenfootSound("Clicking.mp3");
        GS.setVolume(100);
    }
    
    
    /**
     * Check whether the mouse is over the image of buttoms.
     */
    protected void checkClicking(){
        m = Greenfoot.getMouseInfo();
       if(Greenfoot.mouseClicked(this)) GS.play();
        if (m != null){
            // MouseOver State
            if (!mouseOver && Greenfoot.mouseMoved(this)){
                mouseOver = true;
            }
            if (mouseOver && Greenfoot.mouseMoved(null) && !Greenfoot.mouseMoved(this)){
                mouseOver = false;
            }
            //assign the boolean mouseOver
            if (mouseOver){
                this.setImage(mouseOverImage);
                //the larger image when the mouse is on it
            } else {
                this.setImage(idleImage);
                //original small image if the mouse is not on it
            }

        }

    }    
    /**
     * Set the initial image of buttoms' size and size when mouse is over
     * the image.
     */
    protected void initImages(){
     
        idleImage = new GreenfootImage (image);
        idleImage.scale(70,70);
        //the scale for the original icon
        mouseOverImage = new GreenfootImage(image);
        mouseOverImage.scale(80,80);
        //the scale for the larger icon
        setImage(idleImage);
        //the beginning image
    }   
    
}
