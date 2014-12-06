///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package JTE.ui;
//
//import javafx.animation.ParallelTransition;
//import javafx.animation.ScaleTransition;
//import javafx.animation.TranslateTransition;
//import javafx.event.ActionEvent;
//import javafx.event.EventHandler;
//import javafx.scene.control.Button;
//import javafx.scene.image.Image;
//import javafx.scene.image.ImageView;
//import javafx.util.Duration;
//
//
///**
// *
// * @author Rahul
// */
//public class Animation {
//    
//
//    private JTEui ui;
//    TranslateTransition translateTransition;
//    
//    public JTEAnimation(JTEui ui)
//    {
//        this.ui = ui;
////        Xorigin = Xori;
////        Yorigin = Yori;
////        Xdestiny = Xdest;
////        Ydestiny = Ydest;
//    }
//    
//    public void moveFromTo(ImageView image, JTECities CurrentCity, int Xdest, int Ydest)
//    {
//        /*=========================================================================
//        Create here the logic for one movement left.
//        
//        =============================================================================*/
//        
//        
//        translateTransition = new TranslateTransition(Duration.millis(1000), image);
//        translateTransition.setFromX(CurrentCity.getXcoor() + 270);
//        translateTransition.setToX(Xdest + 270);// + 280);
//        translateTransition.setFromY(CurrentCity.getYcoor() - 30);
//        translateTransition.setToY(Ydest - 30);// - 10);
//        
//        System.out.println("origX: " + CurrentCity.getXcoor() + ", origY: " + CurrentCity.getYcoor()
//                            + ", destX: " + Xdest + ", destY: " + Ydest);
//        translateTransition.play();
//        
//    }
//    
//    public void moveCardPlayerToCenter (Button cardButton)
//    {
//        //cardButton.
//        
//        translateTransition = new TranslateTransition(Duration.millis(1000), cardButton);
//        translateTransition.setFromX(cardButton.getLayoutX());
//        translateTransition.setToX(550);// + 280);
//        translateTransition.setFromY(cardButton.getLayoutY());
//        translateTransition.setToY(300);// - 10);
//        
//        translateTransition.play();
//    }
//    
//    public void moveCitiesToVisitButtons (Button cardButton, int initialXcoor, int finalYcoor)
//    {
//        
////        mainPane.getChildren().add(cardButton);
//        
//        TranslateTransition translateTransition =
//            new TranslateTransition(Duration.millis(3000), cardButton);
//        translateTransition.setFromX(initialXcoor);
//        translateTransition.setToX(130);
//        translateTransition.setFromY(100);
//        translateTransition.setToY(finalYcoor);
//
//        ScaleTransition scaleTransition = 
//            new ScaleTransition(Duration.millis(300), cardButton);
//        scaleTransition.setToX(0.8f);
//        scaleTransition.setToY(0.8f);
//        scaleTransition.setCycleCount(1);
//        scaleTransition.setAutoReverse(true);
//
//        ParallelTransition parallelTransition   = new ParallelTransition ();
//        parallelTransition.getChildren().addAll(
//                translateTransition, scaleTransition);
//        parallelTransition.setCycleCount(1);
//        parallelTransition.setAutoReverse(true);
//        
//        parallelTransition.play();
//        
//        parallelTransition.setOnFinished(new EventHandler<ActionEvent>(){
//
//            @Override
//            public void handle(ActionEvent event) {
//                System.out.print(ui.playersList.get(ui.playersList.size() - 1).getCitiesToVisit().size() + " == " + JTEGameStateManager.TOTAL_CARDS);
//                System.out.println(": " + (ui.playersList.get(ui.playersList.size() - 1).getCitiesToVisit().size() == JTEGameStateManager.TOTAL_CARDS));
//                complete = (ui.playersList.get(ui.playersList.size() - 1).getCitiesToVisit().size() == JTEGameStateManager.TOTAL_CARDS);
//                if(complete){
//                    ui.continueProgramExecution();
//                }
//            }
//        });
//
//       
//    }
//    public static boolean complete = false;
//}
