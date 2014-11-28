/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JTE.ui;

/**
 *
 * @author Rahul
 */
public class Players {
    
    int nop=0;
    int computer=0;
    String playerName="";
    public Players(int number,int comp,String name)
    {
        nop=number;
        computer=comp;
        playerName=name;
    }
    public Players()
    {
        
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
    
}
