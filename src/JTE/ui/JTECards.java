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
   Image backimg;
   
   int counter=0;
    public JTECards(Image front,Image back)
    {
        frontimg=front;
        backimg=back;
    }
    public Image getFront()
    {
        return frontimg;
        
    }
    public Image getBack()
    {
        return backimg;
    }    
}
