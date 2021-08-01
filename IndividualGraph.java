import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
/**
 * Write a description of class IndividualGraph here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class IndividualGraph extends Actor
{
    /**
     * Under constructed page!!!!!
     */
    private ArrayList<int[]> data;
    int[] aaa;
    private GreenfootImage drawBoard;
    private GreenfootImage circleBoard;
    public IndividualGraph(ArrayList<int[]> data){
        //this.data=data;
        this.data=data;
        drawImage();
    }    

    private void drawImage(){
        if(data.get(data.size()-1)[7]!=0){
            drawBoard=new GreenfootImage(data.size()*10,data.get(data.size()-1)[7]*10);
            int i=0;
            for(int[] a:data){
                if(a[6]!=0){
                    circleBoard=new GreenfootImage(10*a[6],10*a[6]);
                    circleBoard.setColor(Color.BLUE);
                    circleBoard.fillOval(0,0, 10*a[6],10*a[6]);
                    circleBoard.setTransparency(150);
                    drawBoard.drawImage(circleBoard,0+10*i,10*(int)(data.get(data.size()-1)[5]*(1.0-((double)a[7]/data.get(data.size()-1)[7]))));
                    i++;
                }
            }    
        }
        else drawBoard=new GreenfootImage(10,10);
        drawBoard.scale(400,300);
        this.setImage(drawBoard);
    }

}
