/**
 * PathWayOfHuman class
 * 
 * Creates the pathway of human for human in different location of homes.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PathwayOfHuman  
{
    // Declare instance variables of stats.
    private int[][] turnings;//from TopLeft to BottomLeft to BottomRight to TopRight
    private int houseNumber;
    
    /**
     * Constructor of PathwayOfHuman that create the pathway for human
     * 
     * @param   houseNumber     The number corresponding to house's location.
     */
    public PathwayOfHuman(int houseNumber)
    {
        this.houseNumber=houseNumber;
        //7 points
        turnings= new int[][]{{100+(1200-200)*1/6,100+(1200-200)*1/6,100+(1200-200)*1/6,600,100+(1200-200)*5/6,100+(1200-200)*5/6,100+(1200-200)*5/6,600},
        {(700-200)*0/2+200,(700-200)*1/2+200,(700-200)*2/2+200,(700-200)*2/2+200,(700-200)*2/2+200,(700-200)*1/2+200,(700-200)*0/2+200,450}};
    }
    
    /**
     * Return the pathway of human.
     */
    public int[][] pathway(){
        // Human in different home have different pathways.
        if(houseNumber==1){
        return new int[][]{{turnings[0][2],turnings[0][3],turnings[0][7],0},{turnings[1][2],turnings[1][3],turnings[1][7],0}};
    }
    else if(houseNumber==3||houseNumber==4){
        return new int[][]{{turnings[0][0],turnings[0][1],turnings[0][2],turnings[0][3],turnings[0][7],0},{turnings[1][0],turnings[1][1],turnings[1][2],turnings[1][3],turnings[1][7],0}};
    }  
    else if(houseNumber==2){
        return new int[][]{{turnings[0][1],turnings[0][2],turnings[0][3],turnings[0][7],0},{turnings[1][1],turnings[1][2],turnings[1][3],turnings[1][7],0}};
    }   
    else if(houseNumber==5||houseNumber==6){
        return new int[][]{{turnings[0][6],turnings[0][5],turnings[0][4],turnings[0][3],turnings[0][7],0},{turnings[1][6],turnings[1][5],turnings[1][4],turnings[1][3],turnings[1][7],0}};
    } 
    else if (houseNumber==7){
        return new int[][]{{turnings[0][5],turnings[0][4],turnings[0][3],turnings[0][7],0},{turnings[1][5],turnings[1][4],turnings[1][3],turnings[1][7],0}};
    } 
    else if(houseNumber==8){
        return new int[][]{{turnings[0][4],turnings[0][3],turnings[0][7],0},{turnings[1][4],turnings[1][3],turnings[1][7],0}};
    } 
    else return new int[][]{{},{}};
}
}
