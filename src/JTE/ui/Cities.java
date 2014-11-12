package JTE.ui;

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
     public Cities(String cn,String col,int qt, int X ,int Y)
    {
        cityname=cn;
        color=col;
       quarter=qt;
        x=X;
        y=Y;
    }
     public int getX()
     {
         return x;
     }
     public int getY()
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
}
