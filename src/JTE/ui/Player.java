/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JTE.ui;
 import java.util.ArrayList;
import java.util.List;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;

/**
 *
 * @author Rahul
 */
public class Player {
    
    int nop=0;
    int computer=0;
    String playerName="";
    String originCity="";
    String curCity="";
    int moves=0;
    int turn=1;
    List <String>citiesToVisit=new ArrayList<String>();
    List <String>citiesVisited=new ArrayList<String>();
    
    VBox sideBar;
    public Player(int number,int comp,String name)
    {
        
        nop=number;
        computer=comp;
        playerName=name;
        sideBar = new VBox();
        sideBar.setAlignment(Pos.BASELINE_LEFT);
       // sideBar.setPadding(marginlessInsets);
        sideBar.setSpacing(0.0);
    }
    public Player()
    {
        sideBar = new VBox();
        sideBar.setAlignment(Pos.BASELINE_LEFT);
        //sideBar.setPadding(marginlessInsets);
        sideBar.setSpacing(0.0);
    } 
    public void setNumber(int num)
    {
        nop=num;
    }
    public void isComputer(int comp)
    {
        computer=comp;
    }
    public void setName(String name)
    {
        playerName=name;
    }
    public void setPlayerType(int type)
    {
        computer=type;
    }
    public int getNumber()
    {
        return nop;
    }
    public String getName()
    {
        return playerName;
    }
    public int getPlayerType()
    {
        return computer;
    }
     public String getOriginCity()
    {
        return originCity;
    }
      public String getCurrentCity()
    {
        return curCity;
    }
      
    
}
