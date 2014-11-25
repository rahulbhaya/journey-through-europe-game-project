package JTE.ui;

import application.Main;
import application.Main.JTEPropertyType;


import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import javax.swing.JEditorPane;
import javax.swing.text.Document;
import javax.swing.text.html.HTMLDocument;

import JTE.file.JTEFileLoader;
import JTE.game.JTEGameData;
import JTE.game.JTEGameStateManager;
import application.Main.JTEPropertyType;
import java.awt.Component;
import properties_manager.PropertiesManager;
import javafx.embed.swing.SwingNode;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import static java.util.Collections.list;

import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;

import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javax.swing.JScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.control.TextArea;
import javafx.scene.canvas.*;
import javafx.scene.control.RadioButton;
import javafx.scene.input.MouseEvent;
import javax.swing.JOptionPane;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.ParallelTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.event.EventType;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.util.Duration;


public class JTEUI extends Pane {

    /**
     * The JTEUIState represents the four screen states that are possible
     * for the JTE game application. Depending on which state is in current
     * use, different controls will be visible.
     */
   
    Cities cities;
    public  int quad=1;
    static String DATA_PATH = "./data/";
    AnimationTimer timer;
    BorderPane pane=new BorderPane();
    public Image loadImage(String imageName) {
		Image img = new Image(ImgPath + imageName);
		// System.out.print(imageName);
		return img;
	}
     
    public enum JTEUIState {

        SPLASH_SCREEN_STATE, PLAY_GAME_STATE, VIEW_STATS_STATE, VIEW_ABOUT_STATE,
        HANG1_STATE, HANG2_STATE, HANG3_STATE, HANG4_STATE, HANG5_STATE, HANG6_STATE,
    }
String number="0";
    // mainStage
    private Stage primaryStage;
    double xcoord1=0,ycoord1=0;
     double xcoord2=0,ycoord2=0;
      double xcoord3=0,ycoord3=0;
    // mainPane
    public BorderPane mainPane;
    private BorderPane hmPane;

    // SplashScreen
    private ImageView splashScreenImageView;
    private Pane splashScreenPane;
    private Label splashScreenImageLabel;
    private VBox commandSelectionPane;
    private ArrayList<Button> commandButtons;

    // NorthToolBar
    private HBox northToolbar;
    private Button gameButton;
    private Button statsButton;
    private Button aboutButton;
    private Button exitButton;
    private Button abtButton;
    private Button flightButton;
    private Button saveButton;
    
    public VBox sideBar;
    Image blackPiece;
    // GamePane
    private Label JTELabel;
    private Button newGameButton;
    private HBox letterButtonsPane;
    private HashMap<Character, Button> letterButtons;
    public BorderPane gamePanel = new BorderPane();

    //StatsPane
    private ScrollPane statsScrollPane;
    private JEditorPane statsPane;

    //aboutPane
    //private BorderPane aboutPanel;
    //private ScrollPane aboutScrollPane;
   // private JEditorPane aboutPane;
    private Button homeButton;
    private Pane workspace;
    private StackPane aboutPane;
    private StackPane abtPane;
    private StackPane histPane;
    

    private Button goButton;
     private Button histButton;
    private Button bckButton;
    public Button closeButton;
    
    // Padding
    private Insets marginlessInsets;

    private Button backButton;
    // Image path
    private String ImgPath = "file:images/";

    // mainPane weight && height
    private int paneWidth;
    private int paneHeigth;
   public int qa,qb,qc,qd;
    // THIS CLASS WILL HANDLE ALL ACTION EVENTS FOR THIS PROGRAM
    private JTEEventHandler eventHandler;
    private JTEErrorHandler errorHandler;
    private JTEDocumentManager docManager;
    
    public JTEFileLoader fl;
    public List<Cities> lis;
     public List<Cities> l;
     public List <JTECards> greenlist;
     public List <JTECards> redlist;
    public List <JTECards> yellowlist;
    
    JTEEventHandler eh;
    JTEGameStateManager gsm;
    
    GraphicsContext gc;
   
   ImageView blackPieceView;
    final Canvas canvas = new Canvas(603,770);

    public JTEUI() {
        gsm = new JTEGameStateManager(this);
        eventHandler = new JTEEventHandler(this);
        errorHandler = new JTEErrorHandler(primaryStage);
        docManager = new JTEDocumentManager(this);
        fl=new JTEFileLoader(this);
        
        System.out.println("1");
        initMainPane();
        initSplashScreen();
        initSideBar();
        lis=fl.retCities();
         //l=fl.retCities();
        fl.XMLParser();
         blackPiece= loadImage("piece_black.png");
         blackPieceView=new ImageView(blackPiece);
      // initaboutPane();
        
    }
    
    public void SetStage(Stage stage) {
        primaryStage = stage;
    }

    public BorderPane GetMainPane() {
        return this.mainPane;
    }

    public JTEGameStateManager getGSM() {
        return gsm;
    }

    public JTEDocumentManager getDocManager() {
        return docManager;
    }

    public JTEErrorHandler getErrorHandler() {
        return errorHandler;
    }
    public void cardButton(JTECards card)
        {
//            int clickCounter = 0;
            ImageView imgView = new ImageView(card.getFront());
            imgView.setFitHeight(150);//280
            imgView.setFitWidth(100);//230
            Button leftButton = new Button();
            leftButton.setGraphic(imgView);
            ImageView Back = new ImageView(card.getBack());
            leftButton.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event) 
                {
                    
                    if(card.counter == 0)
                    {
                        Back.setFitHeight(196);//280
                        Back.setFitWidth(161);//230
                        leftButton.setGraphic(Back);
                        card.counter++;
//                        System.out.println("card image back: " + Back.getImage());
                    }
                    else
                    {
                        imgView.setFitHeight(196);//280
                        imgView.setFitWidth(161);//230
                        leftButton.setGraphic(imgView);
                        card.counter --;
                    }
                    
                }

        });
          
            sideBar.getChildren().add(leftButton);
            
        }
 public void initSideBar()
    {
        sideBar = new VBox();
        sideBar.setAlignment(Pos.BASELINE_LEFT);
        sideBar.setPadding(marginlessInsets);
        sideBar.setSpacing(0.0);
    } 
public void travelAnimation(ImageView piece,int toX,int toY)
 {
     //GraphicsContext gc=canvas.getGraphicsContext2D();
     
     
     mainPane.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
           @Override
           public void handle(MouseEvent e) {
               int x=(int)e.getX();
               int y=(int)e.getY();
   
   TranslateTransition translateTransition =new TranslateTransition(Duration.millis(1000),piece);
        if(quad==1)
        {
            double newx=toX*3.5201401050;
            double newy=toY*3.67;
       translateTransition.setFromX(newx);
       translateTransition.setToX(x-30.0);
       translateTransition.setFromY(newy);
       translateTransition.setToY(y-60.0);
//translateTransition.setCycleCount(1);
    // translateTransition.setAutoReverse(true);
        translateTransition.play();
        
        mainPane.getChildren().add(piece);
        
        }
        if(quad==2)
        {
            
          double newx=toX*3.3327495621;
          double newy=toY*3.67;
          translateTransition.setFromX(newx);
       translateTransition.setToX(x-30.0);
       translateTransition.setFromY(newy);
       translateTransition.setToY(y-60.0);
//translateTransition.setCycleCount(1);
    // translateTransition.setAutoReverse(true);
        translateTransition.play();
           
        mainPane.getChildren().add(piece);
    
        }
        if(quad==3)
        {
             double newx=toX*3.4763572679;
            double newy=toY*3.69; 
        
       translateTransition.setFromX(newx);
       translateTransition.setToX(x-30.0);
       translateTransition.setFromY(newy);
       translateTransition.setToY(y-60.0);
//translateTransition.setCycleCount(1);
    // translateTransition.setAutoReverse(true);
        translateTransition.play();
        
        mainPane.getChildren().add(piece);
        
        }
        if(quad==4)
        {
             double newx=toX*3.3747810858;
            double newy=toY*3.6585714285; 
       
       translateTransition.setFromX(newx);
       translateTransition.setToX(x-30.0);
       translateTransition.setFromY(newy);
       translateTransition.setToY(y-60.0);
//translateTransition.setCycleCount(1);
    // translateTransition.setAutoReverse(true);
        translateTransition.play();
   
        mainPane.getChildren().add(piece);
         } 
           }
           });
     piece.setOnDragDetected(new EventHandler <MouseEvent>() {

           @Override
           public void handle(MouseEvent event) {

               /* allow any transfer mode */
               Dragboard db = piece.startDragAndDrop(TransferMode.MOVE);
                
               /* put a image on dragboard */
               ClipboardContent content = new ClipboardContent();
                
               Image sourceImage = piece.getImage();
               content.putImage(sourceImage);
               db.setContent(content);
               
                
               event.consume();
           }
       });
 }
 
  
   public void cardAnimation(JTECards card)
     {
        ImageView Front = new ImageView(card.getFront());
        Front.setFitHeight(200);
        Front.setFitWidth(200);
        ImageView Back = new ImageView(card.getBack());    
        
        // Creates button 
        Button cardButton = new Button(); 
        cardButton.setGraphic(Front);    
        cardButton.setOnAction((ActionEvent event) -> {
            if(card.counter == 0)
            {
                
                Back.setFitHeight(200);
                Back.setFitWidth(200);
                cardButton.setGraphic(Back);
                card.counter ++;
            }
            else
            {
                cardButton.setGraphic(Front);
                card.counter --;
            }
        }); 
        sideBar.getChildren().add(cardButton); 
        TranslateTransition translateTransition =
            new TranslateTransition(Duration.millis(5000),cardButton);
        translateTransition.setFromX(500);
        translateTransition.setToX(0);
        translateTransition.setFromY(500);
        translateTransition.setToY(0);
        translateTransition.setCycleCount(1);
        translateTransition.setAutoReverse(true);
        
        
        ScaleTransition scaleTransition = 
            new ScaleTransition(Duration.millis(10000), cardButton);
        scaleTransition.setToX(0.8f);
        scaleTransition.setToY(0.8f);
        scaleTransition.setCycleCount(1);
        scaleTransition.setAutoReverse(true);
        
        ParallelTransition parallelTransition   = new ParallelTransition ();
        parallelTransition.getChildren().addAll(
                translateTransition, scaleTransition);
        parallelTransition.setCycleCount(1);
        parallelTransition.setAutoReverse(true);
        parallelTransition.play();
     }     
      
    public void initMainPane() {
        marginlessInsets = new Insets(5, 5, 5, 5);
        mainPane = new BorderPane();

        PropertiesManager props = PropertiesManager.getPropertiesManager();
        paneWidth = Integer.parseInt(props
                .getProperty(JTEPropertyType.WINDOW_WIDTH));
        paneHeigth = Integer.parseInt(props
                .getProperty(JTEPropertyType.WINDOW_HEIGHT));
        mainPane.resize(paneWidth, paneHeigth);
        mainPane.setPadding(marginlessInsets);
        
    }

    public void initSplashScreen() {

        // INIT THE SPLASH SCREEN CONTROLS
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        String splashScreenImagePath = props
                .getProperty(JTEPropertyType.SPLASH_SCREEN_IMAGE_NAME);
        props.addProperty(JTEPropertyType.INSETS, "5");
        String str = props.getProperty(JTEPropertyType.INSETS);

        splashScreenPane = new Pane();
        
        splashScreenPane.setMaxSize(700.0,400.0);
        Image splashScreenImage = loadImage(splashScreenImagePath);
        splashScreenImageView = new ImageView(splashScreenImage);

        splashScreenImageLabel = new Label();
        splashScreenImageLabel.setGraphic(splashScreenImageView);
        // move the label position to fix the pane
        splashScreenImageLabel.setLayoutX(-45);
        splashScreenPane.getChildren().add(splashScreenImageLabel);
        
        // GET THE LIST OF LEVEL OPTIONS
        ArrayList<String> commands = props
                .getPropertyOptionsList(JTEPropertyType.BUTTON_OPTIONS);
        ArrayList<String> commandImages = props
                .getPropertyOptionsList(JTEPropertyType.BUTTON_IMAGE_NAMES);
        //ArrayList<String> commandFiles = props
        //        .getPropertyOptionsList(JTEPropertyType.LEVEL_FILES);

        commandSelectionPane = new VBox();
        commandSelectionPane.setSpacing(10.0);
        commandSelectionPane.setAlignment(Pos.CENTER);
        
        // add key listener
        commandButtons = new ArrayList<Button>();
        
        
        for (int i = 0; i < commands.size(); i++) {

            // GET THE LIST OF LEVEL OPTIONS
            String command = commands.get(i);
         
            String commandImageName = commandImages.get(i);
            Image commandImage = loadImage(commandImageName);
            ImageView commandImageView = new ImageView(commandImage);
            commandImageView.setFitHeight(80.0);
            commandImageView.setFitWidth(80.0);
            // AND BUILD THE BUTTON
            Button commandButton = new Button();
            commandButton.setGraphic(commandImageView);
            commandButton.setStyle("-fx-focus-color: transparent;");
            // CONNECT THE BUTTON TO THE EVENT HANDLER
            commandButton.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event) {
                    // TODO
                    eventHandler.respondToSelectCommandRequest(command);
                }
            });
            // TODO
            commandSelectionPane.getChildren().add(commandButton);
            // TODO: enable only the first command
           commandButton.setDisable(false);
        }

        mainPane.setTop(splashScreenPane);
        mainPane.setBottom(commandSelectionPane);
    }

   
    public void initJTEUI() {
        // FIRST REMOVE THE SPLASH SCREEN
        mainPane.getChildren().clear();
        //makeGridPane();
        // GET THE UPDATED TITLE
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        String title = props.getProperty(JTEPropertyType.GAME_TITLE_TEXT);
        primaryStage.setTitle(title);

        // THEN ADD ALL THE STUFF WE MIGHT NOW USE
        initNorthToolbar();

        // OUR WORKSPACE WILL STORE EITHER THE GAME, STATS,
        // OR HELP UI AT ANY ONE TIME
        //initWorkspace();
        //
       // initStatsPane();
        //initaboutPane();
        //initHandlers();
        
        // WE'LL START OUT WITH THE GAME SCREEN
        //changeWorkspace(JTEUIState.PLAY_GAME_STATE);

    }
  
  
    public void initHandlers(){
        mainPane.setOnKeyPressed(new EventHandler<KeyEvent>(){ 
            @Override
            public void handle(KeyEvent event) {
                     
            }
        });
    }
    
    public void doCommand(String command){
        
       // initJTEUI();
       if(command.equals("Quit"))
            eventHandler.respondToExitRequest(primaryStage);
       if(command.equals("About"))
           eventHandler.respondToSwitchScreenRequest(JTEUIState.VIEW_ABOUT_STATE);
       if(command.equals("Play"))
           eventHandler.respondToSwitchScreenRequest(JTEUIState.PLAY_GAME_STATE);
       if(command.equals("Load"))
           mainPane.getChildren().clear();
           
    }
   
    public void initaboutPane()
    {
        aboutPane=new StackPane();
        TextArea ta1=new TextArea();
        ta1.setText("Jouney Through Europe");
        ta1.setEditable(false);
          ta1.setStyle("-fx-background-color:transparent;");
          TextArea ta2= new TextArea();
          ta2.setText("Journey through Europe is a family board game published by Ravensburger\n" +
"The board is a map of Europe with various major cities marked, for example, Athens, Amsterdam and London. \n" +
"The players are given a home city from which they will begin and are then dealt a number of cards with various other cities on them. \n" +
"\n" +
"They must plan a route between each of the cities in their hand of cards.\n" +
"On each turn they throw a die and move between the cities, \n" +
"The winner is the first player to visit each of their cities and then return to their home base.\n" +
"HangMan was firstly mentioned in 1894. See Wikipedia for more.");
          ta2.setEditable(false);
        
      backButton = new Button("Back");
      backButton.setStyle("-fx-focus-color: transparent;");
        //setTooltip(gameButton, JTEPropertyType.GAME_TOOLTIP);
        backButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                // TODO Auto-generated method stub
               mainPane.getChildren().clear();
               gamePanel.getChildren().clear();
               initSplashScreen();
               
                
            }
        });
         
        aboutPane.getChildren().addAll(ta1,ta2,backButton);
        mainPane.setCenter(aboutPane);
        
    }
    public void loadPage(JEditorPane jep, JTEPropertyType fileProperty) {
		// GET THE FILE NAME
		PropertiesManager props = PropertiesManager.getPropertiesManager();
		String fileName = props.getProperty(fileProperty);
		try {
			// LOAD THE HTML INTO THE EDITOR PANE
			String fileHTML = JTEFileLoader.loadTextFile(fileName);
			jep.setText(fileHTML);
		} catch (IOException ioe) {
                    ioe.printStackTrace();
			errorHandler.processError(JTEPropertyType.INVALID_URL_ERROR_TEXT);
		}
	}
   public void setPlayerAtHome(JTECards card)
   {
       String path=card.getFront().impl_getUrl();
       String imageName = path.substring(path.lastIndexOf('/')+1,path.length()-4 );
       System.out.println(imageName);
     
         for(int i=0;i<lis.size();i++)
         {
                   cities = lis.get(i);
            if(cities.getCityName().equals(imageName))
            {
               
                xcoord1=cities.getX();
                ycoord1=cities.getY();
                qa=cities.getQuadrant();
            }
            
         }
        
         System.out.println(xcoord1+","+ycoord1);
      
   }
   public void setPlayerCoordinates1(JTECards card)
   {
       String path=card.getFront().impl_getUrl();
       String imageName = path.substring(path.lastIndexOf('/')+1,path.length()-4 );
       System.out.println(imageName);
         for(int i=0;i<lis.size();i++)
         {
                   cities = lis.get(i);
            if(cities.getCityName().equals(imageName))
            {
               
                xcoord2=cities.getX();
                ycoord2=cities.getY();
                qb=cities.getQuadrant();
            }
            
         }
        
         System.out.println(xcoord2+","+ycoord2);
   }
   public void setPlayerCoordinates2(JTECards card)
   {
       String path=card.getFront().impl_getUrl();
       String imageName = path.substring(path.lastIndexOf('/')+1,path.length()-4 );
       System.out.println(imageName);
     
         for(int i=0;i<lis.size();i++)
         {
                   cities = lis.get(i);
            if(cities.getCityName().equals(imageName))
            {
                xcoord3=cities.getX();
                ycoord3=cities.getY();
                qc=cities.getQuadrant();
            }
            
         }
        
         System.out.println(xcoord3+","+ycoord3);
   }
   public void drawHomeFlags()
   {
        gc=canvas.getGraphicsContext2D();
       Image blackflag = loadImage("flag_blackhq.png");
       Image blueflag = loadImage("flag_bluehq.png");
       Image greenflag = loadImage("flag_greenhq.png");
          gc.drawImage(blackflag,xcoord1-30.0,ycoord1-60.0);
   }
//   public void drawFlag1()
//   {
//        gc=canvas.getGraphicsContext2D();
//        Image blackflag1 = loadImage("flag_blackhq.png");
//        Image blueflag1 = loadImage("flag_bluehq.png");
//       Image greenflag1 = loadImage("flag_greenhq.png");
//       gc.drawImage(blackflag1,xcoord2-30.0,ycoord2-60.0);   
//   }
//    public void drawFlag2()
//   {
//        gc=canvas.getGraphicsContext2D();
//        Image blackflag2 = loadImage("flag_blackhq.png");
//        Image blueflag2 = loadImage("flag_bluehq.png");
//       Image greenflag2 = loadImage("flag_greenhq.png");
//       gc.drawImage(blackflag2,xcoord3-30.0,ycoord3-60.0);   
//   }
    public void drawPiece()
   {
        gc=canvas.getGraphicsContext2D();
        
       Image bluepiece = loadImage("piece_blue.png");
       Image greenpiece = loadImage("piece_green.png");
       gc.drawImage(blackPiece,xcoord1-15.0,ycoord1-40.0);   
   }
        private void initGameScreen() {
            
            Random myRandomizer = new Random();
            greenlist = fl.returnGreenCards();
            redlist = fl.returnRedCards();
            yellowlist = fl.returnYellowCards(); 
            JTECards randomGreen = greenlist.get(myRandomizer.nextInt(greenlist.size()));
            JTECards randomRed = redlist.get(myRandomizer.nextInt(greenlist.size()));
            //placePlayer(randomRed);
            JTECards randomYellow = yellowlist.get(myRandomizer.nextInt(greenlist.size()));
            //placePlayer(randomYellow);
           cardAnimation(randomGreen);
            cardAnimation(randomRed);
             cardAnimation(randomYellow);
             
		mainPane.getChildren().clear();
                VBox vbox1=new VBox();
                VBox vbox2=new VBox();
                VBox vbox3=new VBox();
                Image abt= loadImage("aboutjte.png");
                ImageView view=new ImageView(abt);
                abtButton = new Button(" ",view);
                abtButton.setStyle("-fx-focus-color: transparent;");
        //setTooltip(gameButton, JTEPropertyType.GAME_TOOLTIP);
        abtButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                // TODO Auto-generated method stub
               mainPane.getChildren().clear();
               initabtPane();
                
            }
        });
        Image hist= loadImage("history.png");
                ImageView view2=new ImageView(hist);
                histButton = new Button(" ",view2);
                histButton.setStyle("-fx-focus-color: transparent;");
        histButton.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                // TODO Auto-generated method stub
               mainPane.getChildren().clear();
                   inithistPane();
                
            }
        });
        Image flightplan= loadImage("flightplanimg.png");
                ImageView view3=new ImageView(flightplan);
                flightButton = new Button(" ",view3);
                flightButton.setStyle("-fx-focus-color: transparent;");
                
        Image save= loadImage("save.png");
                ImageView view4=new ImageView(save);
                saveButton = new Button(" ",view4);
                saveButton.setStyle("-fx-focus-color: transparent;");
        
                gc = canvas.getGraphicsContext2D();
                 Label l1=new Label("Player 1");
             Label l2= new Label("Player 1 Turn");
             Label l3= new Label("Rolled 6");
             Label l4= new Label("Select City");
             
           Image img1 = loadImage("1.jpg");
            gc.drawImage(img1,0,0,571,700);
          //  placePlayerAtHome(randomGreen);
            Image img2 = loadImage("2.jpg");
            Image img3 = loadImage("3.jpg");
            Image img4 = loadImage("4.jpg");
         
            
            GridPane grd= new GridPane();
            Label ac= new Label("A-C");
            Label df=new Label ("D-F");
             Label one= new Label("1-4");
            Label two=new Label ("5-8");
            Button b1= new Button();
            Button b2= new Button();
            Button b3= new Button();
            Button b4= new Button();
            Button die= new Button();
            Random rand = new Random();
                 
            Image q1=loadImage("1.jpg");
            ImageView q1view = new ImageView(q1);
            q1view.setFitHeight(40.0);
            q1view.setFitWidth(60.0);
             Image q2=loadImage("2.jpg");
            ImageView q2view = new ImageView(q2);
             q2view.setFitHeight(40.0);
            q2view.setFitWidth(60.0);
            Image q3=loadImage("3.jpg");
            ImageView q3view = new ImageView(q3);
            q3view.setFitHeight(40.0);
            q3view.setFitWidth(60.0);
            Image q4=loadImage("4.jpg");
            ImageView q4view = new ImageView(q4);
            q4view.setFitHeight(40.0);
            q4view.setFitWidth(60.0);
            b1.setGraphic(q1view);
            b2.setGraphic(q2view);
            b3.setGraphic(q3view);
            b4.setGraphic(q4view);
            Image die_6=loadImage("die_6.jpg");
            ImageView im= new ImageView(die_6);
            die.setGraphic(im);
              setPlayerAtHome(randomGreen);
              setPlayerCoordinates1(randomRed);
              setPlayerCoordinates2(randomYellow);
            b1.setOnAction(new EventHandler<ActionEvent>() { 
            @Override
            public void handle(ActionEvent event) {
                // TODO Auto-generated method stub
              gc.drawImage(img1,0,0,571,700);
              quad=1;
              if(qa==1)
              {
               drawHomeFlags();
               drawPiece();
              }
              if(qb==1)
              {
                  //drawFlag1();
              }
              if(qc==1)
              {
                 // drawFlag2();
              }
            }
        });
               b2.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                // TODO Auto-generated method stub
              gc.drawImage(img2,0,0,571,700);
              quad=2;
             if(qa==2)
              {
               drawHomeFlags();
                drawPiece();
              }
              if(qb==2)
              {
                 // drawFlag1();
              }
              if(qc==2)
              {
                  //drawFlag2();
              }
            }
        });
                  b3.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                // TODO Auto-generated method stub
              gc.drawImage(img3,0,0,571,700);
              quad=3;
              if(qa==3)
              {
               drawHomeFlags();
                drawPiece();
              }
              if(qb==3)
              {
                  //drawFlag1();
              }
              if(qc==3)
              {
                 // drawFlag2();
              }
              
            }
        });
               b4.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                // TODO Auto-generated method stub
              gc.drawImage(img4,0,0,571,700);
                quad=4;
                if(qa==4)
              {
               drawHomeFlags();
                drawPiece();
              }
              if(qb==4)
              {
                  //drawFlag1();
              }
              if(qc==4)
              {
                  //drawFlag2();
              }
           
            }
        });
                //JLabel MyImage = new JLabel(new ImageIcon("image"+randomNum+".png"));
                die.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                int randomNum= rand.nextInt(6)+1;
            Image dieimage=loadImage("die_"+randomNum+".jpg");
            ImageView dieimageview = new ImageView(dieimage);
               die.setGraphic(dieimageview);
                
            }
        });
                
        grd.add(ac, 1, 0);
        grd.add(df, 2, 0);
        grd.add(one, 0, 1);
        grd.add(two, 0, 2);
        
        // Buttons are thrown into the grd.
        grd.add(b1, 1, 1);
        grd.add(b2, 2, 1);
        grd.add(b3, 1, 2);
        grd.add(b4, 2, 2);
             //Image select = loadImage("gameplay_selector.jpg");
            //ImageView sel = new ImageView(select);
            
          //  vbox1.getChildren().add(sideBar);
           
            vbox3.getChildren().addAll(l2,l3,l4,grd,die,flightButton,histButton,abtButton,saveButton);
            vbox3.setSpacing(10);
             gc.setStroke(Color.RED);
              gc.setLineWidth(5);
              
             canvas.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
           @Override
           public void handle(MouseEvent e) {
               int x=(int)e.getX();
               int y=(int)e.getY();
               Cities ct;
                  System.out.println(x + " " + y);
               for(int i=0;i<lis.size();i++)
               {
                   ct = lis.get(i);
                   
                   if(x>(ct.getX()-10)&&x<(ct.getX()+10))
                   {
                       if(y>(ct.getY()-10)&&y<(ct.getY()+10))
                       {
                            List <Cities> tempLand= ct.fetchLandNeighbour();
                            List <Cities> tempSea = ct.fetchSeaNeighbour();
                            
                           if(ct.getQuadrant()==1&&quad==1)
                           {
                            for(Cities Neighbors:tempLand)
                           {
                               System.out.println(Neighbors.getX()+","+Neighbors.getY()+","+ct.getX()+","+ct.getY());
                              // if(Neighbors.getCityName()!=)
                                gc.strokeLine(ct.getX(),ct.getY(), Neighbors.getX(), Neighbors.getY());
                                travelAnimation(blackPieceView,ct.getX(),ct.getY());
                            }
                             for(Cities Neighbors:tempSea)
                           {
                               System.out.println(Neighbors.getX()+","+Neighbors.getY()+","+ct.getX()+","+ct.getY());
                              // if(Neighbors.getCityName()!=)
                               // gc.strokeLine(ct.getX(),ct.getY(), Neighbors.getX(), Neighbors.getY());
                               // travelAnimation(ct.getX(),ct.getY(),Neighbors.getX(),Neighbors.getY());
                            }
                           String data1=ct.getCityName()+","+(ct.getX())+","+(ct.getY());
                           System.out.println(ct.getCityName());
                           //JOptionPane.showMessageDialog(null,data1);
                           continue;
                           }
                           if(ct.getQuadrant()==2&&quad==2)
                           {
                             for(Cities Neighbors : tempLand)
                           {
                               System.out.println(Neighbors.getX());
                                gc.strokeLine(ct.getX(),ct.getY(), Neighbors.getX(), Neighbors.getY());
                                travelAnimation(blackPieceView,ct.getX(),ct.getY());
                            }
                             for(Cities Neighbors:tempSea)
                           {
                               System.out.println(Neighbors.getX()+","+Neighbors.getY()+","+ct.getX()+","+ct.getY());
                              // if(Neighbors.getCityName()!=)
                               // gc.strokeLine(ct.getX(),ct.getY(), Neighbors.getX(), Neighbors.getY());
                                //travelAnimation(ct.getX(),ct.getY(),Neighbors.getX(),Neighbors.getY());
                            }
                           String data2=ct.getCityName()+","+(ct.getX())+","+(ct.getY());
                           System.out.println(ct.getCityName());
                           //JOptionPane.showMessageDialog(null,data2);
                           continue;
                           }
                           if(ct.getQuadrant()==3&&quad==3)
                           {
                                for(Cities Neighbors : tempLand)
                           {
                               System.out.println(Neighbors.getX());
                                gc.strokeLine(ct.getX(),ct.getY(), Neighbors.getX(), Neighbors.getY());
                                travelAnimation(blackPieceView,ct.getX(),ct.getY());
                            }
                                for(Cities Neighbors:tempSea)
                           {
                               System.out.println(Neighbors.getX()+","+Neighbors.getY()+","+ct.getX()+","+ct.getY());
                              // if(Neighbors.getCityName()!=)
                               // gc.strokeLine(ct.getX(),ct.getY(), Neighbors.getX(), Neighbors.getY());
                               // travelAnimation(ct.getX(),ct.getY(),Neighbors.getX(),Neighbors.getY());
                            }
                           String data3=ct.getCityName()+","+(ct.getX())+","+(ct.getY());
                           System.out.println(ct.getCityName());
                          // JOptionPane.showMessageDialog(null,data3);
                           continue;
                           }
                           if(ct.getQuadrant()==4&&quad==4)
                           {
                                for(Cities Neighbors : tempLand)
                           {
                               System.out.println(Neighbors.getX());
                                gc.strokeLine(ct.getX(),ct.getY(), Neighbors.getX(), Neighbors.getY());
                                travelAnimation(blackPieceView,ct.getX(),ct.getY());
                            }
                                for(Cities Neighbors:tempSea)
                           {
                               System.out.println(Neighbors.getX()+","+Neighbors.getY()+","+ct.getX()+","+ct.getY());
                              // if(Neighbors.getCityName()!=)
                               // gc.strokeLine(ct.getX(),ct.getY(), Neighbors.getX(), Neighbors.getY());
                               // travelAnimation(ct.getX(),ct.getY(),Neighbors.getX(),Neighbors.getY());
                            }
                           String data4=ct.getCityName()+","+(ct.getX())+","+(ct.getY());
                           System.out.println(ct.getCityName());
                          //JOptionPane.showMessageDialog(null,data4);
                           continue;
                           }
                       }
                       
                   }
                       
               }
               
           }
       });
            
             pane.setLeft(sideBar);
              pane.setCenter(canvas);
              pane.setRight(vbox3);
           mainPane.setCenter(pane);    
    }
        public void inithistPane()
        {
            histPane=new StackPane();
            closeButton=new Button("Close");
       closeButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // TODO Auto-generated method stub
               mainPane.getChildren().clear();
               mainPane.setCenter(pane);
                
            }
        });
        histPane.getChildren().add(closeButton);
        mainPane.setCenter(histPane);
            
        }
    public void initabtPane()
    {
        abtPane=new StackPane();
        TextArea ta3=new TextArea();
        ta3.setText("Jouney Through Europe");
        ta3.setEditable(false);
          ta3.setStyle("-fx-background-color:transparent;");
          TextArea ta4= new TextArea();
          ta4.setText("Journey through Europe is a family board game published by Ravensburger\n" +
"The board is a map of Europe with various major cities marked, for example, Athens, Amsterdam and London. \n" +
"The players are given a home city from which they will begin and are then dealt a number of cards with various other cities on them. \n" +
"\n" +
"They must plan a route between each of the cities in their hand of cards.\n" +
"On each turn they throw a die and move between the cities, \n" +
"The winner is the first player to visit each of their cities and then return to their home base.\n" +
"HangMan was firstly mentioned in 1894. See Wikipedia for more.");
          ta4.setEditable(false);
        
      bckButton = new Button("Back");
      
        //setTooltip(gameButton, JTEPropertyType.GAME_TOOLTIP);
        bckButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                // TODO Auto-generated method stub
               mainPane.getChildren().clear();
               mainPane.setCenter(pane);
                
            }
        });
         
        abtPane.getChildren().addAll(ta3,ta4,bckButton);
        mainPane.setCenter(abtPane);
        
    }
    /**
     * This function initializes all the controls that go in the north toolbar.
     */
    private void initNorthToolbar() {
        // MAKE THE NORTH TOOLBAR, WHICH WILL HAVE FOUR BUTTONS
       
        northToolbar = new HBox();
        northToolbar.setStyle("-fx-background-color:lightgray");
        northToolbar.setAlignment(Pos.CENTER);
        northToolbar.setPadding(marginlessInsets);
        northToolbar.setSpacing(10.0);
        northToolbar.setStyle("-fx-background-color: #D1B48C;");
        
        
        Label lb=new Label("Number of Players");
        northToolbar.getChildren().add(lb);
        ComboBox cb=new ComboBox();
        cb.getItems().addAll("1", "2", "3","4","5","6");
        northToolbar.getChildren().add(cb);
        cb.setOnAction((event) -> {
        Object playerno = cb.getSelectionModel().getSelectedItem();
       number= playerno.toString();
            System.out.println("you selected:  "+number);
            GridPane grid = new GridPane();
      grid.setHgap(20);
    grid.setVgap(20);
    grid.setPadding(new Insets(0, 10, 0, 10));
    grid.setGridLinesVisible(true);
    grid.setStyle("-fx-background-color: #D1B48C;");
    
  Image image1 =  loadImage("flag_black.png");
   ImageView iv1 = new ImageView();
   iv1.setImage(image1);
   
    Image image2 =loadImage("flag_blue.png");
   ImageView iv2 = new ImageView();
   iv2.setImage(image2);
   
   Image image3 =loadImage("flag_green.png");
   ImageView iv3 = new ImageView();
   iv3.setImage(image3);
   
   Image image4 =loadImage("flag_red.png");
   ImageView iv4 = new ImageView();
   iv4.setImage(image4);
   
   Image image5 =loadImage("flag_white.png");
   ImageView iv5 = new ImageView();
   iv5.setImage(image5);
   
   Image image6 =loadImage("flag_yellow.png");
   ImageView iv6 = new ImageView();
   iv6.setImage(image6);
   
    lab:
    {
    HBox h1=new HBox();
    HBox h2=new HBox();
    VBox v1=new VBox();
    //HBox h15=new HBox();
   // h15.getChildren().add(iv1);
    RadioButton rb1=new RadioButton();
    rb1.setText("Player   ");
    rb1.setSelected(true);
    RadioButton rb2=new RadioButton();
    rb2.setText("Computer");
    Label l1=new Label("Name:");
    TextField tf1=new TextField();
    h1.getChildren().addAll(rb1,l1);
    h1.setSpacing(30);
    h2.getChildren().addAll(rb2,tf1);
    h2.setSpacing(20);
    
  
    v1.getChildren().addAll(h1,h2,iv1);
    grid.add(v1,1,1);
    v1.setSpacing(10);
    if(number.equals("1"))break lab;
    
    HBox h3=new HBox();
    HBox h4=new HBox();
    VBox v2=new VBox();
    RadioButton rb3=new RadioButton();
    rb3.setText("Player   ");
    rb3.setSelected(true);
    RadioButton rb4=new RadioButton();
    rb4.setText("Computer");
    Label l2=new Label("Name:");
    TextField tf2=new TextField();
    h3.getChildren().addAll(rb3,l2);
    h3.setSpacing(30);
    h4.getChildren().addAll(rb4,tf2);
    h4.setSpacing(20);
 
    v2.getChildren().addAll(h3,h4,iv2);
    grid.add(v2,2,1);
    v2.setSpacing(10);
    if(number.equals("2"))break lab;
    
    HBox h5=new HBox();
    HBox h6=new HBox();
    VBox v3=new VBox();
    RadioButton rb5=new RadioButton();
    rb5.setText("Player   ");
    rb5.setSelected(true);
    RadioButton rb6=new RadioButton();
    rb6.setText("Computer");
    Label l3=new Label("Name:");
    TextField tf3=new TextField();
    h5.getChildren().addAll(rb5,l3);
    h5.setSpacing(30);
    h6.getChildren().addAll(rb6,tf3);
    h6.setSpacing(20);
    //v1.getChildren().add
    v3.getChildren().addAll(h5,h6,iv3);
    grid.add(v3,3,1);
    v3.setSpacing(10);
    if(number.equals("3"))break lab;
    
    HBox h7=new HBox();
    HBox h8=new HBox();
    VBox v4=new VBox();
    RadioButton rb7=new RadioButton();
    rb7.setText("Player   ");
    rb7.setSelected(true);
    RadioButton rb8=new RadioButton();
    rb8.setText("Computer");
    Label l4=new Label("Name:");
    TextField tf4=new TextField();
    h7.getChildren().addAll(rb7,l4);
    h7.setSpacing(30);
    h8.getChildren().addAll(rb8,tf4);
    h8.setSpacing(20);
    //v1.getChildren().add
    v4.getChildren().addAll(h7,h8,iv4);
    grid.add(v4,1,2);
    v4.setSpacing(10);
    if(number.equals("4"))break lab;
    
    HBox h9=new HBox();
    HBox h10=new HBox();
    VBox v5=new VBox();
    RadioButton rb9=new RadioButton();
    rb9.setText("Player   ");
    rb9.setSelected(true);
    RadioButton rb10=new RadioButton();
    rb10.setText("Computer");
    Label l5=new Label("Name:");
    TextField tf5=new TextField();
    h9.getChildren().addAll(rb9,l5);
    h9.setSpacing(30);
    h10.getChildren().addAll(rb10,tf5);
    h10.setSpacing(20);
    //v1.getChildren().add
    v5.getChildren().addAll(h9,h10,iv5);
    grid.add(v5,2,2);
    v5.setSpacing(10);
   if(number.equals("5"))break lab;
    
    
    HBox h11=new HBox();
    HBox h12=new HBox();
    VBox v6=new VBox();
    RadioButton rb11=new RadioButton();
    rb11.setText("Player   ");
    rb11.setSelected(true);
    RadioButton rb12=new RadioButton();
    rb12.setText("Computer");
    Label l6=new Label("Name:");
    TextField tf6=new TextField();
    h11.getChildren().addAll(rb11,l6);
    h11.setSpacing(30);
    h12.getChildren().addAll(rb12,tf6);
    h12.setSpacing(20);
    //v1.getChildren().add
    v6.getChildren().addAll(h11,h12,iv6);
    grid.add(v6,3,2);
    v6.setSpacing(10);
    if(number.equals("6"))break lab;
    
    }
    mainPane.setCenter(grid);
            //mainPane.setCenter(gamePanel);
            
    
});
      goButton = initToolbarButton(northToolbar,
                JTEPropertyType.GO_IMG_NAME);
        
        goButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                // TODO Auto-generated method stub
                initGameScreen();
                
                
            }

        });
        

        // AND NOW PUT THE NORTH TOOLBAR IN THE FRAME
        mainPane.setTop(northToolbar);
        //mainPane.getChildren().add(northToolbar);
       
    }
public void makeGridPane()
{
    
    //return grid;
}
    /**
     * This method helps to initialize buttons for a simple toolbar.
     *
     * @param toolbar The toolbar for which to add the button.
     *
     * @param prop The property for the button we are building. This will
     * dictate which image to use for the button.
     *
     * @return A constructed button initialized and added to the toolbar.
     */
    private Button initToolbarButton(HBox toolbar, JTEPropertyType prop) {
        // GET THE NAME OF THE IMAGE, WE DO THIS BECAUSE THE
        // IMAGES WILL BE NAMED DIFFERENT THINGS FOR DIFFERENT LANGUAGES
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        String imageName = props.getProperty(prop);

        // LOAD THE IMAGE
        Image image = loadImage(imageName);
        ImageView imageIcon = new ImageView(image);

        // MAKE THE BUTTON
        Button button = new Button();
        button.setGraphic(imageIcon);
        button.setPadding(marginlessInsets);

        // PUT IT IN THE TOOLBAR
        toolbar.getChildren().add(button);

        // AND SEND BACK THE BUTTON
        return button;
    }

    /**
     * The workspace is a panel that will show different screens depending on
     * the user's requests.
     */
    private void initWorkspace() {
        // THE WORKSPACE WILL GO IN THE CENTER OF THE WINDOW, UNDER THE NORTH
        // TOOLBAR
        workspace = new Pane();
        mainPane.setCenter(workspace);
        //mainPane.getChildren().add(workspace);
        System.out.println("in the initWorkspace");
    }
JEditorPane gamePane;
        
        SwingNode gameSwingNode;
	

    

    /**
     * This function selects the UI screen to display based on the uiScreen
     * argument. Note that we have 3 such screens: game, stats, and about.
     *
     * @param uiScreen The screen to be switched to.
     */
    public void changeWorkspace(JTEUIState uiScreen) {
        switch (uiScreen) {
            case VIEW_ABOUT_STATE:
                System.out.println("3");
               // workspace.getChildren().remove(aboutPanel);
              // workspace.getChildren().remove(statsScrollPane);
                //workspace.getChildren().add(gamePanel);
               // workspace.getChildren().add(aboutPanel);
                //gamePanel.setVisible(false);
                mainPane.getChildren().clear();
                initaboutPane();
                //mainPane.setCenter(aboutPane);
                break;
            case PLAY_GAME_STATE:
                System.out.println("2");
                //mainPane.setCenter(gamePanel);
               // makeGridPane();
               initJTEUI();
                break;
            case VIEW_STATS_STATE:
                mainPane.setCenter(statsScrollPane);
                break;
            default:
        }

    }


}
