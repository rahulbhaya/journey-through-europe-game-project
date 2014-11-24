package JTE.ui;
import JTE.file.JTEFileLoader;
import java.util.ArrayList;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Rahul
 */

public class Cities {
   
    String cityname;
    String color;
    int quarter;
    int x;
    int y;
    List<Cities> landNeighbours;
    List<Cities> seaNeighbours;
    
    JTEFileLoader fileload;
     public Cities(String cn,String col,int qt, int X ,int Y)
    {
        cityname=cn;
        color=col;
       quarter=qt;
        x=X;
        y=Y;
        fileload=new JTEFileLoader();
        landNeighbours = new ArrayList<Cities>();
        seaNeighbours = new ArrayList<Cities>();
    }
     
     public int getX()
     {
         return x;
     }
     public int getY()
     {
         return y;
     }
    
     public int getY(String ctname)
     {
         return y;
     }
     public String getCityName()
     {
         return cityname;
     }
     public int getQuadrant()
     {
         return quarter;
     }
     public void addSeaNeighbours(Cities neighbour)
    {
        seaNeighbours.add(neighbour);
    }
    
    public Cities getSeaNeighbours(int i)
    {
        return seaNeighbours.get(i);
    }
    
    public void addLandNeighbours(Cities neighbour)
    {
        landNeighbours.add(neighbour);
    }
    
   
     public Cities fetchLandNeighbours(int i)
    {
        return landNeighbours.get(i);
    }
    
    public List<Cities> fetchSeaNeighbour()
    {
        return seaNeighbours;
        
    }
    public List<Cities> fetchLandNeighbour()
    {
        return landNeighbours;
        
    }
}
