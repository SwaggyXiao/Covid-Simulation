import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Next here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Next extends Buttom
{
    private int pageNumber=0;
    /**
     * Constructor of Next to create the image of next buttom.
     */
    public Next(){
        // Import image from local by calling constructor in superclass(Buttom).
        super("Next.png");
    }    

    /**
     * Act - do whatever the Next wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        // Always check if mouse is over buttom.
        checkClicking();
    }    

    /**
     * Override the method initImages() since next buttom should have different
     * size than default image's size.
     */
    protected void initImages(){

        idleImage = new GreenfootImage (image);
        if(pageNumber==0)idleImage.scale(150,50);
        else if(pageNumber==1)idleImage.scale(200,50);
        //the scale for the original icon
        mouseOverImage = new GreenfootImage(image);
        if(pageNumber==0)mouseOverImage.scale(165,55);
        else if(pageNumber==1)mouseOverImage.scale(210,55);
        //the scale for the larger icon
        setImage(idleImage);
        //the beginning image
    }   
    public void setImage(String file){
        if(pageNumber==0)pageNumber++;
        else if(pageNumber==1)pageNumber--;
        image=file;
        initImages();
    }    
}
