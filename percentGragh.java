import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
/**
 * Write a description of class percentGragh here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class percentGragh extends Actor
{
    private ArrayList<int[]> data;
    int[] aaa;
    private GreenfootImage drawBoard;
    private GreenfootImage finalBoard;
    private GreenfootImage statusSheet;
    private GreenfootImage whiteSheet;
    private Font f=new Font("Comic Sans MS",true,false,30);
    public percentGragh(ArrayList<int[]> data){
        //this.data=data;
        this.data=data;
        drawImage();
    }    
    private void drawImage(){
        drawBoard=new GreenfootImage(data.size(),data.get(data.size()-1)[5]);
        drawBoard.setColor(Color.GREEN);
        drawBoard.fillRect(0,0,data.size()+1,data.get(data.size()-1)[5]+1);
        drawBoard.setColor(Color.RED);
        int i=0;
        for(int[] a:data){
            drawBoard.fillRect(0+i,0,2,(int)(data.get(data.size()-1)[5]*((double)(a[1]+a[2]+a[3]+a[4])/a[5])));
            i++;
        }    
        drawBoard.setColor(Color.MAGENTA);
        i=0;
        for(int[] a:data){
            drawBoard.fillRect(0+i,0,2,(int)(data.get(data.size()-1)[5]*((double)(a[2]+a[3]+a[4])/a[5])));
            i++;
        }  
        drawBoard.setColor(Color.BLACK);
        i=0;
        for(int[] a:data){
            drawBoard.fillRect(0+i,0,2,(int)(data.get(data.size()-1)[5]*((double)(a[3]+a[4])/a[5])));
            i++;
        }  
        drawBoard.setColor(Color.YELLOW);
        i=0;
        for(int[] a:data){
            drawBoard.fillRect(0+i,0,2,(int)(data.get(data.size()-1)[5]*((double)(a[4])/a[5])));
            i++;
        }  
        drawBoard.scale(600,400);
        finalBoard=new GreenfootImage(800,500);
        whiteSheet=new GreenfootImage(800,500);
        whiteSheet.setColor(Color.WHITE);
        whiteSheet.fillRect(0,0,800+1,500+1);
        whiteSheet.setTransparency(150);
        finalBoard.drawImage(whiteSheet,0,0);
        finalBoard.setColor(Color.BLACK);
        finalBoard.setFont(f);
        finalBoard.drawImage(drawBoard,175,55);
        finalBoard.drawLine(175,30,175,455);
        finalBoard.drawLine(175,455,800,455);
        finalBoard.drawString("Frequency of each health status over Time",155,25);
        finalBoard.drawString("Time",350,485);
        finalBoard.drawString("Day "+data.size(),670,485);
        statusSheet=new GreenfootImage("Status Sheet.png");
        statusSheet.scale(150,175);
        
        finalBoard.drawImage(statusSheet,13,200);
        finalBoard.drawString("Frequency",13,100);
        this.setImage(finalBoard);
    }    
    
}
