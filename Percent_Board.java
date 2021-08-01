import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

import java.util.ArrayList;

/**
 * Write a description of class Percent_Board here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Percent_Board extends Actor
{
    private ArrayList<String> data=new ArrayList<String>();
    private int index_Number;
    private GreenfootImage Board;
    private Font f=new Font("Comic Sans MS",true,false,15);
    private Color c=Color.RED;
    private SimulationWorld SW;
/**
 * The constructor of the percent board
 * It will take in two parameter, where type tells the board what element it is discribing, and level tells what value is the current element.
 * @para Level                  the value of the element
 * @para Type                   the type of the element
 */
    public Percent_Board(String Level, String Type){ //Type: "Awareness", "Mask"// 
        if(Type.equals("Awareness")){
            data.add("High");
            data.add("Medium");
            data.add("Low");
            index_Number=data.indexOf(Level);
        }
        else if(Type.equals("Mask")){

            data.add("100%");
            data.add("80%");
            data.add("60%");
            data.add("40%");
            data.add("20%");
            data.add("0%");
            index_Number=data.indexOf(Level);
        }   
        drawImage();
    }    

    public void addedToWorld(World w){
        SW=(SimulationWorld)w;
    }   
/**
 * This method  allows other classes to updates the board, by the change (-1 or 1, which will change the index number) 
 * and the corresponding type of element
 * @para change                 -1 or 1 value, going up the array list or going down by 1
 * @para Type                   a string that discribes the type of element
 */
    public void Updates(int change, String Type) 
    {
        int new_Index_Number=index_Number+change;
        if(new_Index_Number>=0&&new_Index_Number<=data.size()-1) index_Number=new_Index_Number;
        if(Type.equals("Awareness")){
            SW.UpdatesAwareness(current_Value());
            SW.changeAwarenessIndex(index_Number);
        }
        else if(Type.equals("Mask")) {
            SW.UpdatesMask(current_Value());
            SW.changeMaskIndex(index_Number);
        }
        drawImage();
    }    

    /**
     * Return the value of the element (Awareness or Mask) and allows other class to access it
     * @return current_Value                the current value of the element
     */
    public String current_Value(){
        return (String)data.get(index_Number);
    }    
    /**
     * Draw the image of the board. 
     */
    public void drawImage(){
        Board=new GreenfootImage("Percent Board.png");
        Board.scale(150, 80);
        Board.setColor(c);
        GreenfootImage text=new GreenfootImage((String)data.get(index_Number), 40, c, null, Color.BLACK);
        text.setFont(f);
        Board.drawImage(text,Board.getWidth()/2-text.getWidth()/2,Board.getHeight()/2-text.getHeight()/2);
        this.setImage(Board);
    } 
}
