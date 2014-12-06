/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JTE.ui;

import javafx.scene.image.Image;

/**
 *
 * @author Rahul
 */
public class JTECards {
    
   Image frontimg;
   
   
   int counter=0;
    public JTECards(Image front)
    {
        frontimg=front;
        
    }
    public Image getFront()
    {
        return frontimg;
        
    }
        
}
