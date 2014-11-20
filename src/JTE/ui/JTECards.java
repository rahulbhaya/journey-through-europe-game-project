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
   
    public JTECards(Image front,Image back)
    {
        frontimg=front;
        backimg=back;
    }
    public Image getFrontImg()
    {
        return frontimg;
        
    }
    public Image getBackImg()
    {
        return backimg;
    }
          
}
