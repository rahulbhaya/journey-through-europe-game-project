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
import JTE.ui.Player;
import java.io.FileNotFoundException;
import java.io.Writer;
import java.util.Scanner;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

public class JTEUI extends Pane {

    /**
     * The JTEUIState represents the four screen states that are possible for
     * the JTE game application. Depending on which state is in current use,
     * different controls will be visible.
     */
    Cities cities;
    public int quad = 1;
    static String DATA_PATH = "./data/";
    AnimationTimer timer;
    BorderPane pane = new BorderPane();

    public Image loadImage(String imageName) {
        Image img = new Image(ImgPath + imageName);
        // System.out.print(imageName);
        return img;
    }

    public enum JTEUIState {

        SPLASH_SCREEN_STATE, PLAY_GAME_STATE, VIEW_STATS_STATE, VIEW_ABOUT_STATE,
        HANG1_STATE, HANG2_STATE, HANG3_STATE, HANG4_STATE, HANG5_STATE, HANG6_STATE,
    }
    String noOfPlayers = "0";
    // mainStage
    private Stage primaryStage;
    double xcoord1 = 0, ycoord1 = 0;
    double xcoord2 = 0, ycoord2 = 0;
    double xcoord3 = 0, ycoord3 = 0;
    double xcoord4 = 0, ycoord4 = 0;
    double xcoord5 = 0, ycoord5 = 0;
    double xcoord6 = 0, ycoord6 = 0;
    int xhist1=0,yhist1=0;
     int xhist2=0,yhist2=0;
      int xhist3=0,yhist3=0;
       int xhist4=0,yhist4=0;
        int xhist5=0,yhist5=0;
         int xhist6=0,yhist6=0;
    

    int randomNum;
    // mainPane
    public BorderPane mainPane;
    private BorderPane hmPane;
    Label turnLabel = new Label("Player 1 turn");
    Label fixedMovesLabel= new Label("Moves Left:");
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

    Image blackPiece, bluePiece, greenPiece, redPiece, whitePiece, yellowPiece;
    // GamePane
    private Label JTELabel;
    private Button newGameButton;
    private HBox letterButtonsPane;
    private HashMap<Character, Button> letterButtons;
    public BorderPane gamePanel = new BorderPane();

    //StatsPane
    private ScrollPane statsScrollPane;
    private JEditorPane statsPane;
    private BorderPane canvasPane;

    //aboutPane
    //private BorderPane aboutPanel;
    //private ScrollPane aboutScrollPane;
    // private JEditorPane aboutPane;
    private Button homeButton;
    private Pane workspace;
    private StackPane aboutPane;
    private StackPane abtPane;
    public ScrollPane histPane;

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
    
    // THIS CLASS WILL HANDLE ALL ACTION EVENTS FOR THIS PROGRAM
    private JTEEventHandler eventHandler;
    private JTEErrorHandler errorHandler;
    private JTEDocumentManager docManager;

    public JTEFileLoader fl;
    public List<Cities> lis;
    public List<Cities> l;
    public List<JTECards> greenlist;
    public List<JTECards> redlist;
    public List<JTECards> yellowlist;
    public List<JTECards> listOfCities;

    JTEEventHandler eh;
    JTEGameStateManager gsm;
    VBox sideBar;

    Player player, player1, player2, player3, player4, player5, player6;
    int playerNo = 0;
    int turn = 0;
    int mouseClicked = 0;
    GraphicsContext gc;

    ImageView blackPieceView, bluePieceView, greenPieceView, redPieceView, whitePieceView, yellowPieceView;
     Canvas canvas = new Canvas(603, 770);
     Canvas canvas1 = new Canvas(603, 770);
     Canvas canvas2 = new Canvas(603, 770);
     Canvas canvas3 = new Canvas(603, 770);
     Canvas canvas4 = new Canvas(603, 770);
     Canvas canvas5 = new Canvas(603, 770);
     Canvas canvas6 = new Canvas(603, 770);
    TextField tf1 = new TextField();
    TextField tf2 = new TextField();
    TextField tf3 = new TextField();
    TextField tf4 = new TextField();
    TextField tf5 = new TextField();
    TextField tf6 = new TextField();
    Button b1 = new Button();
    Button b2 = new Button();
    Button b3 = new Button();
    Button b4 = new Button();
    public Button die = new Button();
    Random myRandomizer = new Random();
    JTECards randomGreen1;
    JTECards randomRed1;
    JTECards randomYellow1;
    JTECards randomGreen2;
    JTECards randomRed2;
    JTECards randomYellow2;
    JTECards randomGreen3;
    JTECards randomRed3;
    JTECards randomYellow3;
    JTECards randomGreen4;
    JTECards randomRed4;
    JTECards randomYellow4;
    JTECards randomGreen5;
    JTECards randomRed5;
    JTECards randomYellow5;
    JTECards randomGreen6;
    JTECards randomRed6;
    JTECards randomYellow6;
    Image img1;
    Random rand = new Random();
   int q;
    Image img2;
    Image img3;
    Image img4;
    int qp1,qp2,qp3,qp4,qp5,qp6;
    TextArea histArea;
    Image flightplanimg = loadImage("Flight_Plan.JPG");
    ImageView flightview = new ImageView(flightplanimg);
    AnchorPane flightplan;
    Cities c;
    List <String>airportCityClicked=new ArrayList<String>();

    public JTEUI() {
        gsm = new JTEGameStateManager(this);
        eventHandler = new JTEEventHandler(this);
        errorHandler = new JTEErrorHandler(primaryStage);
        docManager = new JTEDocumentManager(this);
        // player = new Player();
        player1 = new Player();
        player2 = new Player();
        player3 = new Player();
        player4 = new Player();
        player5 = new Player();
        player6 = new Player();
        // player1.moves=6;
        
        fl = new JTEFileLoader(this);
        greenlist = fl.returnGreenCards();
        redlist = fl.returnRedCards();
        yellowlist = fl.returnYellowCards();
        //  listOfCities = fl.retCities();
        randomGreen1 = greenlist.get(myRandomizer.nextInt(greenlist.size()));
        randomRed1 = redlist.get(myRandomizer.nextInt(redlist.size()));
        //placePlayer(randomRed);
        randomYellow1 = yellowlist.get(myRandomizer.nextInt(yellowlist.size()));
        //placePlayer(randomYellow)
        randomGreen2 = greenlist.get(myRandomizer.nextInt(greenlist.size()));
        randomRed2 = redlist.get(myRandomizer.nextInt(redlist.size()));
        randomYellow2 = yellowlist.get(myRandomizer.nextInt(yellowlist.size()));
        randomGreen3 = greenlist.get(myRandomizer.nextInt(greenlist.size()));
        randomRed3 = redlist.get(myRandomizer.nextInt(redlist.size()));
        randomYellow3 = yellowlist.get(myRandomizer.nextInt(yellowlist.size()));
        randomGreen4 = greenlist.get(myRandomizer.nextInt(greenlist.size()));
        randomRed4 = redlist.get(myRandomizer.nextInt(redlist.size()));
        randomYellow4 = yellowlist.get(myRandomizer.nextInt(yellowlist.size()));
        randomGreen5 = greenlist.get(myRandomizer.nextInt(greenlist.size()));
        randomRed5 = redlist.get(myRandomizer.nextInt(redlist.size()));
        randomYellow5 = yellowlist.get(myRandomizer.nextInt(yellowlist.size()));
        randomGreen6 = greenlist.get(myRandomizer.nextInt(greenlist.size()));
        randomRed6 = redlist.get(myRandomizer.nextInt(redlist.size()));
        randomYellow6 = yellowlist.get(myRandomizer.nextInt(yellowlist.size()));
        canvasPane = new BorderPane();
        System.out.println("1");
        initMainPane();
        initSplashScreen();
        initSideBar();
        lis = fl.retCities();
        //l=fl.retCities();
        fl.XMLParser();
        blackPiece = loadImage("piece_black.png");
        blackPieceView = new ImageView(blackPiece);
        bluePiece = loadImage("piece_blue.png");
        bluePieceView = new ImageView(bluePiece);
        greenPiece = loadImage("piece_green.png");
        greenPieceView = new ImageView(greenPiece);
        redPiece = loadImage("piece_red.png");
        redPieceView = new ImageView(redPiece);
        whitePiece = loadImage("piece_white.png");
        whitePieceView = new ImageView(whitePiece);
        yellowPiece = loadImage("piece_yellow.png");
        yellowPieceView = new ImageView(yellowPiece);
        img1 = loadImage("1.jpg");
        img2 = loadImage("2.jpg");
        img3 = loadImage("3.jpg");
        img4 = loadImage("4.jpg");
        histArea=new TextArea();
        setPlayerAtHome();
        // initaboutPane();
        player6.movesLeft=5;
        flightplan=new AnchorPane();
        loadAirports();
        for (int i = 0; i < lis.size(); i++) 
                    c = lis.get(i);
                    
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

    public void initSideBar() {
        sideBar = new VBox();
        sideBar.setAlignment(Pos.BASELINE_LEFT);
        sideBar.setPadding(marginlessInsets);
        sideBar.setSpacing(0.0);
    }

    public void travelAnimation(ImageView piece, int toX, int toY) {
        TranslateTransition translateTransition = new TranslateTransition(Duration.millis(1000), piece);

     
            if(quad==1)
            {
            // translateTransition.setFromX(xcoord1);
            translateTransition.setToX(toX + 107);
           // translateTransition.setFromY(ycoord1);
            translateTransition.setToY(toY - 40);
            translateTransition.play();
            canvasPane.getChildren().remove(piece);
            canvasPane.getChildren().add(piece);
           }
        if (quad == 2) {

            //translateTransition.setFromX(newx2);
            translateTransition.setToX(toX + 107);
            //translateTransition.setFromY(newy2);
            translateTransition.setToY(toY - 40);
            //translateTransition.setCycleCount(1);
            // translateTransition.setAutoReverse(true);
            translateTransition.play();
            canvasPane.getChildren().remove(piece);
            canvasPane.getChildren().add(piece);
        }
        if (quad == 3) {

            //translateTransition.setFromX(newx3);
            translateTransition.setToX(toX + 107);
            //translateTransition.setFromY(newy3);
            translateTransition.setToY(toY - 40);
            //translateTransition.setCycleCount(1);
            // translateTransition.setAutoReverse(true);
            translateTransition.play();
            canvasPane.getChildren().remove(piece);
            canvasPane.getChildren().add(piece);
        }
        if (quad == 4) {

            //translateTransition.setFromX(newx4);
            translateTransition.setToX(toX + 107);
            // translateTransition.setFromY(ycoord1);
            translateTransition.setToY(toY - 40);
//translateTransition.setCycleCount(1);
            // translateTransition.setAutoReverse(true);
            translateTransition.play();
            canvasPane.getChildren().remove(piece);
            canvasPane.getChildren().add(piece);
        }

        piece.setOnDragDetected(new EventHandler<MouseEvent>() {

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

    public void cardAnimation(JTECards card1, JTECards card2, JTECards card3, int cardNumber) {
        ImageView Front1 = new ImageView(card1.getFront());
        ImageView Front2 = new ImageView(card2.getFront());
        ImageView Front3 = new ImageView(card3.getFront());
        Front1.setFitHeight(250);
        Front1.setFitWidth(250);
        Front2.setFitHeight(250);
        Front2.setFitWidth(250);
        Front3.setFitHeight(250);
        Front3.setFitWidth(250);
        sideBar.getChildren().add(Front1);
        sideBar.getChildren().add(Front2);
        sideBar.getChildren().add(Front3);
        TranslateTransition translateTransition1
                = new TranslateTransition(Duration.millis(3000), Front1);
        translateTransition1.setFromX(500);
        translateTransition1.setToX(0);
        translateTransition1.setFromY(500);
        translateTransition1.setToY(0);
        translateTransition1.setCycleCount(1);
        //translateTransition.setAutoReverse(true);
        // translateTransition.setDelay(Duration.millis(500*cardNumber));

        ScaleTransition scaleTransition1
                = new ScaleTransition(Duration.millis(3000), Front1);
        scaleTransition1.setToX(0.85f);
        scaleTransition1.setToY(0.85f);
        scaleTransition1.setCycleCount(1);
        // scaleTransition.setAutoReverse(true);
        //scaleTransition.setDelay(Duration.millis(1000));

        ParallelTransition parallelTransition1 = new ParallelTransition();
        parallelTransition1.getChildren().addAll(
                translateTransition1, scaleTransition1);
        parallelTransition1.setCycleCount(1);
        // parallelTransition.setAutoReverse(true);
        parallelTransition1.play();

        TranslateTransition translateTransition2
                = new TranslateTransition(Duration.millis(3000), Front2);
        translateTransition2.setFromX(500);
        translateTransition2.setToX(0);
        translateTransition2.setFromY(500);
        translateTransition2.setToY(0);
        translateTransition2.setCycleCount(1);
        //translateTransition.setAutoReverse(true);
        // translateTransition.setDelay(Duration.millis(500*cardNumber));

        ScaleTransition scaleTransition2
                = new ScaleTransition(Duration.millis(3000), Front2);
        scaleTransition2.setToX(0.85f);
        scaleTransition2.setToY(0.85f);
        scaleTransition2.setCycleCount(1);
        // scaleTransition.setAutoReverse(true);
        //scaleTransition.setDelay(Duration.millis(1000));

        ParallelTransition parallelTransition2 = new ParallelTransition();
        parallelTransition2.getChildren().addAll(
                translateTransition2, scaleTransition2);
        parallelTransition2.setCycleCount(1);
        // parallelTransition.setAutoReverse(true);
        parallelTransition2.play();

        TranslateTransition translateTransition3
                = new TranslateTransition(Duration.millis(3000), Front3);
        translateTransition3.setFromX(500);
        translateTransition3.setToX(0);
        translateTransition3.setFromY(500);
        translateTransition3.setToY(0);
        translateTransition3.setCycleCount(1);
        //translateTransition.setAutoReverse(true);
        // translateTransition.setDelay(Duration.millis(500*cardNumber));

        ScaleTransition scaleTransition3
                = new ScaleTransition(Duration.millis(3000), Front3);
        scaleTransition3.setToX(0.85f);
        scaleTransition3.setToY(0.85f);
        scaleTransition3.setCycleCount(1);
        // scaleTransition.setAutoReverse(true);
        //scaleTransition.setDelay(Duration.millis(1000));

        ParallelTransition parallelTransition3 = new ParallelTransition();
        parallelTransition3.getChildren().addAll(
                translateTransition3, scaleTransition3);
        parallelTransition3.setCycleCount(1);
        // parallelTransition.setAutoReverse(true);
        parallelTransition3.play();
        //parallelTransition.setDelay(Duration.millis(3000));
//        transition.setOnFinished(new EventHandler<ActionEvent>(){
// 
//            @Override
//            public void handle(ActionEvent arg0) {
//                transitionForward.play();
//            }
//        });
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

        splashScreenPane.setMaxSize(700.0, 400.0);
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

    public void initHandlers() {
        mainPane.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {

            }
        });
    }

    public void doCommand(String command) {
        // initJTEUI();
        if (command.equals("Quit")) {
            eventHandler.respondToExitRequest(primaryStage);
        }
        if (command.equals("About")) {
            eventHandler.respondToSwitchScreenRequest(JTEUIState.VIEW_ABOUT_STATE);
        }
        if (command.equals("Play")) {
            eventHandler.respondToSwitchScreenRequest(JTEUIState.PLAY_GAME_STATE);
        }
        if (command.equals("Load")) {
            mainPane.getChildren().clear();
        }

    }
 

 public void loadAirports(){
        
                
               
                AnchorPane.setTopAnchor(flightview, 0.0);
                AnchorPane.setLeftAnchor(flightview,0.0);
                flightplan.getChildren().add(flightview);
                fl.loadAirportCities();
    }
    
    public void addFlightButtons(String city,String x,String y){
       
       Button b=new Button();
       b.setTooltip(new Tooltip(city));
       b.setMaxHeight(15);
       b.setMinHeight(15);
       b.setMaxWidth(15);
       b.setMinWidth(15);
       b.setStyle("-fx-border-radius:40");
       b.setStyle("-fx-background-radius:50");
       AnchorPane.setTopAnchor(b,Double.parseDouble(y)/2-7);
       AnchorPane.setLeftAnchor(b,Double.parseDouble(x)/2-6);
       flightplan.getChildren().add(b);
       b.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // TODO Auto-generated method stub
                airportCityClicked.add(city);
                
                System.out.println(airportCityClicked.get(0));
                if(canvas==canvas1)
                {
                    player1.movesLeft=player1.movesLeft-2;
                    fixedMovesLabel.setText("Moves Left:"+player1.movesLeft);
                    if(c.getCityName()==city)
                    {    
                    travelAnimation(blackPieceView,c.getX(),c.getY());
                    }
                }
                if(canvas==canvas2)
                {
                    player2.movesLeft=player2.movesLeft-2;
                    fixedMovesLabel.setText("Moves Left:"+player2.movesLeft);
                    
                }
                if(canvas==canvas3)
                {
                    player3.movesLeft=player3.movesLeft-2;
                    fixedMovesLabel.setText("Moves Left:"+player3.movesLeft);
                }
                
                if(canvas==canvas4)
                {
                    player4.movesLeft=player4.movesLeft-2;
                    fixedMovesLabel.setText("Moves Left:"+player4.movesLeft);
                }
                if(canvas==canvas5)
                {
                    player5.movesLeft=player5.movesLeft-2;
                    fixedMovesLabel.setText("Moves Left:"+player5.movesLeft);
                }
                
                if(canvas==canvas6)
                {
                    player6.movesLeft=player6.movesLeft-2;
                    fixedMovesLabel.setText("Moves Left:"+player6.movesLeft);
                }
                
                
            }
        });
       
    }
    public void initaboutPane() {
        aboutPane = new StackPane();
        TextArea ta1 = new TextArea();
        ta1.setText("Jouney Through Europe");
        ta1.setEditable(false);
        ta1.setStyle("-fx-background-color:transparent;");
        TextArea ta2 = new TextArea();
        ta2.setText("Journey through Europe is a family board game published by Ravensburger\n"
                + "The board is a map of Europe with various major cities marked, for example, Athens, Amsterdam and London. \n"
                + "The players are given a home city from which they will begin and are then dealt a number of cards with various other cities on them. \n"
                + "\n"
                + "They must plan a route between each of the cities in their hand of cards.\n"
                + "On each turn they throw a die and move between the cities, \n"
                + "The winner is the first player to visit each of their cities and then return to their home base.");

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

        aboutPane.getChildren().addAll(ta1, ta2, backButton);
        mainPane.setCenter(aboutPane);

    }

    public void setPlayerAtHome() {
        String redPath1 = randomRed1.getFront().impl_getUrl();
        String greenPath1 = randomGreen1.getFront().impl_getUrl();
        String yellowPath1 = randomYellow1.getFront().impl_getUrl();
        String redPath2 = randomRed1.getFront().impl_getUrl();
        String greenPath2 = randomGreen1.getFront().impl_getUrl();
        String yellowPath2 = randomYellow1.getFront().impl_getUrl();
        String redPath3 = randomRed1.getFront().impl_getUrl();
        String greenPath3 = randomGreen1.getFront().impl_getUrl();
        String yellowPath3 = randomYellow1.getFront().impl_getUrl();
        String redPath4 = randomRed1.getFront().impl_getUrl();
        String greenPath4 = randomGreen1.getFront().impl_getUrl();
        String yellowPath4 = randomYellow1.getFront().impl_getUrl();
        String redPath5 = randomRed1.getFront().impl_getUrl();
        String greenPath5 = randomGreen1.getFront().impl_getUrl();
        String yellowPath5 = randomYellow1.getFront().impl_getUrl();
        String redPath6 = randomRed1.getFront().impl_getUrl();
        String greenPath6 = randomGreen1.getFront().impl_getUrl();
        String yellowPath6 = randomYellow1.getFront().impl_getUrl();
        System.out.println(redPath1);

        String redImageName1 = redPath1.substring(redPath1.lastIndexOf('/') + 1, redPath1.length() - 4);
        String greenImageName1 = greenPath1.substring(greenPath1.lastIndexOf('/') + 1, greenPath1.length() - 4);
        String yellowImageName1 = yellowPath1.substring(yellowPath1.lastIndexOf('/') + 1, yellowPath1.length() - 4);
        String redImageName2 = redPath2.substring(redPath2.lastIndexOf('/') + 1, redPath2.length() - 4);
        String greenImageName2 = greenPath2.substring(greenPath2.lastIndexOf('/') + 1, greenPath2.length() - 4);
        String yellowImageName2 = yellowPath2.substring(yellowPath2.lastIndexOf('/') + 1, yellowPath2.length() - 4);
        String redImageName3 = redPath3.substring(redPath3.lastIndexOf('/') + 1, redPath3.length() - 4);
        String greenImageName3 = greenPath3.substring(greenPath3.lastIndexOf('/') + 1, greenPath3.length() - 4);
        String yellowImageName3 = yellowPath3.substring(yellowPath3.lastIndexOf('/') + 1, yellowPath3.length() - 4);
        String redImageName4 = redPath4.substring(redPath4.lastIndexOf('/') + 1, redPath4.length() - 4);
        String greenImageName4 = greenPath4.substring(greenPath4.lastIndexOf('/') + 1, greenPath4.length() - 4);
        String yellowImageName4 = yellowPath4.substring(yellowPath4.lastIndexOf('/') + 1, yellowPath4.length() - 4);
        String redImageName5 = redPath5.substring(redPath5.lastIndexOf('/') + 1, redPath5.length() - 4);
        String greenImageName5 = greenPath5.substring(greenPath5.lastIndexOf('/') + 1, greenPath5.length() - 4);
        String yellowImageName5 = yellowPath5.substring(yellowPath5.lastIndexOf('/') + 1, yellowPath5.length() - 4);
        String redImageName6 = redPath6.substring(redPath6.lastIndexOf('/') + 1, redPath6.length() - 4);
        String greenImageName6 = greenPath6.substring(greenPath6.lastIndexOf('/') + 1, greenPath6.length() - 4);
        String yellowImageName6 = yellowPath6.substring(yellowPath6.lastIndexOf('/') + 1, yellowPath6.length() - 4);

        //  System.out.println(imageName);
        for (int i = 0; i < lis.size(); i++) {
            cities = lis.get(i);
            if (cities.getCityName().equals(redImageName1)) {
                xcoord1 = cities.getX();
                ycoord1 = cities.getY();
                qp1=cities.getQuadrant();
                
            }
            if (cities.getCityName().equals(greenImageName2)) {
                xcoord2 = cities.getX();
                ycoord2 = cities.getY();
                  qp2=cities.getQuadrant();
            }
            if (cities.getCityName().equals(yellowImageName3)) {
                xcoord3 = cities.getX();
                ycoord3 = cities.getY();
                  qp3=cities.getQuadrant();
            }
            if (cities.getCityName().equals(redImageName4)) {
                xcoord4 = cities.getX();
                ycoord4 = cities.getY();
                  qp4=cities.getQuadrant();
            }
            if (cities.getCityName().equals(greenImageName5)) {
                xcoord5 = cities.getX();
                ycoord5 = cities.getY();
                  qp5=cities.getQuadrant();
            }
            if (cities.getCityName().equals(yellowImageName6)) {
                xcoord6 = cities.getX();
                ycoord6 = cities.getY();
                  qp6=cities.getQuadrant();
            }
        }

        System.out.println(xcoord1 + "," + ycoord1);

    }

//    public void setPlayerCoordinates1(JTECards card) {
//        String path = card.getFront().impl_getUrl();
//        String imageName = path.substring(path.lastIndexOf('/') + 1, path.length() - 4);
//        System.out.println(imageName);
//        for (int i = 0; i < lis.size(); i++) {
//            cities = lis.get(i);
//            if (cities.getCityName().equals(imageName)) {
//
//                xcoord2 = cities.getX();
//                ycoord2 = cities.getY();
//                qb = cities.getQuadrant();
//            }
//
//        }
//
//        System.out.println(xcoord2 + "," + ycoord2);
//    }
//
//    public void setPlayerCoordinates2(JTECards card) {
//        String path = card.getFront().impl_getUrl();
//        String imageName = path.substring(path.lastIndexOf('/') + 1, path.length() - 4);
//        System.out.println(imageName);
//
//        for (int i = 0; i < lis.size(); i++) {
//            cities = lis.get(i);
//            if (cities.getCityName().equals(imageName)) {
//                xcoord3 = cities.getX();
//                ycoord3 = cities.getY();
//                qc = cities.getQuadrant();
//            }
//
//        }
//
//        System.out.println(xcoord3 + "," + ycoord3);
//    }
    public void drawHomeFlag1() {
        gc = canvas1.getGraphicsContext2D();
       Image blackflag = loadImage("flag_blackhq.png");
        gc.drawImage(blackflag, xcoord1 - 30.0, ycoord1 - 60.0);

    }
    public void drawHomeFlag2()
    {
        gc = canvas2.getGraphicsContext2D();
        Image blueflag = loadImage("flag_bluehq.png");
        gc.drawImage(blueflag, xcoord2 - 30.0, ycoord2 - 60.0);
        
    }
     public void drawHomeFlag3()
    {
        gc = canvas3.getGraphicsContext2D();
        Image greenflag = loadImage("flag_greenhq.png");
        gc.drawImage(greenflag, xcoord3 - 30.0, ycoord3 - 60.0);
        
    }
      public void drawHomeFlag4()
    {
        gc = canvas4.getGraphicsContext2D();
        Image redflag = loadImage("flag_redhq.png");
        gc.drawImage(redflag, xcoord4 - 30.0, ycoord4 - 60.0);
        
    }
       public void drawHomeFlag5()
    {
        gc = canvas5.getGraphicsContext2D();
        Image whiteflag = loadImage("flag_whitehq.png");
        gc.drawImage(whiteflag, xcoord5 - 30.0, ycoord5 - 60.0);
        
    }
          public void drawHomeFlag6()
    {
        gc = canvas6.getGraphicsContext2D();
        Image yellowflag = loadImage("flag_yellowhq.png");
        gc.drawImage(yellowflag, xcoord6 - 30.0, ycoord6 - 60.0);
        
    }

    public void drawPiece1() {

        
        System.out.println("Printing in drawhomepiece1" + xcoord1 + "," + ycoord1);
       if(player1.turn==1)
        travelAnimation(blackPieceView, (int)(xcoord1 - 15), (int)(ycoord1 - 40));
       else 
            travelAnimation(blackPieceView, (int)(xhist1 - 10), (int)(yhist1 -10));


    }
    public void drawPiece2() {

        
        System.out.println("Printing in drawhomepiece2" + xcoord2 + "," + ycoord2);
         if(player2.turn==1)
        travelAnimation(bluePieceView, (int)(xcoord2 - 15), (int)(ycoord2 - 40));
       else 
            travelAnimation(bluePieceView, (int)(xhist2 - 15), (int)(yhist2 - 40));
       
    }
    public void drawPiece3() {

       
        System.out.println("Printing in drawhomepiece3" + xcoord3 + "," + ycoord3);
        
        travelAnimation(greenPieceView, (int)(xcoord3 - 15), (int)(ycoord3 - 40));
    }
    public void drawPiece4() {

       
        System.out.println("Printing in drawhomepiece4" + xcoord4 + "," + ycoord4);
       travelAnimation(redPieceView, (int)(xcoord4 - 15), (int)(ycoord4 - 40));
    }
    public void drawPiece5() {

       
        System.out.println("Printing in drawhomepiece5" + xcoord5 + "," + ycoord5);
         travelAnimation(whitePieceView, (int)(xcoord5 - 15), (int)(ycoord5 - 40));
    }
    public void drawPiece6() {    
        System.out.println("Printing in drawhomepiece6" + xcoord6 + "," + ycoord6);
       travelAnimation(yellowPieceView, (int)(xcoord6 - 15), (int)(ycoord6 - 40));
    }
   

//    public void repaint() {
//        gc = canvas1.getGraphicsContext2D();
//
//        if (q == 1) {
//            gc.drawImage(img1, 0, 0, 571, 700);
//        }
//        if (q == 2) {
//            gc.drawImage(img2, 0, 0, 571, 700);
//        }
//        if (q == 3) {
//            gc.drawImage(img3, 0, 0, 571, 700);
//        }
//        if (q == 4) {
//            gc.drawImage(img4, 0, 0, 571, 700);
//        }
//    }
//    public void playerController() {
////        //int count=0;
////        if (player.moves == 0) {
//////            turn++;
////            //playerNo = turn % (Integer.parseInt(number));
////            //count++;
////            if (playerNo < Integer.parseInt(noOfPlayers)) {
////                playerNo++;
////            } else {
////                playerNo = 1;
////            }
////            //toggleSideBar(playerNo);
////            
////
////            //turnLabel.setText("Player"+currentPlayer + "turn");
////            if (playerNo == 0) {
////                playerNo++;
////            }
////
////            if (playerNo == 1) {
////                toggleSideBar(playerNo);
////            }
////            if (playerNo == 2) {
////                toggleSideBar(playerNo);
////            }
////            if (playerNo == 3) {
////                toggleSideBar(playerNo);
////            }
////            if (playerNo == 4) {
////                toggleSideBar(playerNo);
////            }
////            if (playerNo == 5) {
////                toggleSideBar(playerNo);
////            }
////            if (playerNo == 6) {
////                toggleSideBar(playerNo);
////            }
////        }
//        if (player1.moves == 0) {
//            canvas = canvas2;
//            canvasHandler2();
//        }
//        if (player2.moves == 0) {
//            canvas = canvas3;
//            canvasHandler3();
//        }
//        if (player3.moves == 0) {
//            canvas = canvas4;
//            canvasHandler4();
//        }
//        if (player4.moves == 0) {
//            canvas = canvas4;
//            canvasHandler5();
//        }
//        if (player5.moves == 0) {
//            canvas = canvas5;
//            canvasHandler6();
//        }
//        if (player6.moves == 0) {
//            canvas = canvas6;
//            canvasHandler1();
//        }
//
//    }

  
//    public void toggleSideBar(int playerNo) {
////    player1.sideBar.setVisible(false);
////    player2.sideBar.setVisible(false);
////    player3.sideBar.setVisible(false);
////    player4.sideBar.setVisible(false);
////    player5.sideBar.setVisible(false);
////    player6.sideBar.setVisible(false);
//
//        switch (playerNo) {
//            case 1:
//                player = player1;
//                turnLabel.setText("Player" + playerNo + "turn");
//               sideBar.getChildren().clear();
//               cardAnimation(randomRed1, randomGreen1, randomYellow1, 1);
//                canvasController();
//                break;
//            case 2:
//                player = player2;
//                turnLabel.setText("Player" + playerNo + "turn");
//                enableDieButton();
//                sideBar.getChildren().clear();
//                
//                canvasController();
//                break;
//            case 3:
//                player = player3;
//                turnLabel.setText("Player" + playerNo + "turn");
//                enableDieButton();
//                sideBar.getChildren().clear();
//                
//                canvasController();
//                break;
//            case 4:
//                player = player4;
//                turnLabel.setText("Player" + playerNo + "turn");
//                enableDieButton();
//                sideBar.getChildren().clear();
//                
//                canvasController();
//                break;
//            case 5:
//                player = player5;
//                turnLabel.setText("Player" + playerNo + "turn");
//                enableDieButton();
//                sideBar.getChildren().clear();
//               
//                 canvasController();
//                break;
//            case 6:
//                player = player6;
//                turnLabel.setText("Player" + playerNo + "turn");
//                enableDieButton();
//                sideBar.getChildren().clear();
//                
//                  canvasController();
//                break;
//        }
//    }

    public void initPlayerControl() {

        initGameScreen();
        cardAnimation1();
         //playerController();
    }

    public void cardAnimation1() {
        sideBar.getChildren().clear();
        cardAnimation(randomRed1, randomGreen1, randomYellow1, 1);
    }

    public void cardAnimation2() {
        sideBar.getChildren().clear();
        cardAnimation(randomGreen2, randomYellow2, randomRed2, 1);
    }

    public void cardAnimation3() {
        sideBar.getChildren().clear();
        cardAnimation(randomYellow3, randomRed3, randomGreen3, 1);
    }

    public void cardAnimation4() {
        sideBar.getChildren().clear();
        cardAnimation(randomRed4, randomGreen4, randomYellow5, 1);
    }

    public void cardAnimation5() {
        sideBar.getChildren().clear();
        cardAnimation(randomGreen5, randomYellow5, randomRed5, 1);
    }

    public void cardAnimation6() {
        sideBar.getChildren().clear();
        cardAnimation(randomYellow6, randomRed6, randomGreen6, 1);
    }

    private void initGameScreen() {

        //toggleSideBar(1);
        mainPane.getChildren().clear();
        VBox vbox = new VBox();
        Image abt = loadImage("aboutjte.png");
        ImageView view = new ImageView(abt);
        abtButton = new Button(" ", view);
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
        Image hist = loadImage("history.png");
        ImageView view2 = new ImageView(hist);
        histButton = new Button(" ", view2);
        histButton.setStyle("-fx-focus-color: transparent;");
        histButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                // TODO Auto-generated method stub
                inithistPane();
               Stage stage = new Stage();
                stage.setScene(new Scene(new Group(histPane)));
                stage.show();

            }
        });
        Image flightbuttonimg = loadImage("flightplanimg.png");

        ImageView view3 = new ImageView(flightbuttonimg);
        flightButton = new Button(" ", view3);
        flightButton.setStyle("-fx-focus-color: transparent;");
        
        flightButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                // TODO Auto-generated method stub
                Stage stage = new Stage();
                stage.setScene(new Scene(new Group(flightplan)));
                stage.show();
            }
        });

        Image save = loadImage("save.png");
        ImageView view4 = new ImageView(save);
        saveButton = new Button(" ", view4);
        saveButton.setStyle("-fx-focus-color: transparent;");
        gc = canvas1.getGraphicsContext2D();

        gc.drawImage(img1, 0, 0, 571, 700);
        //  placePlayerAtHome(randomGreen);

        GridPane grd = new GridPane();
        Label ac = new Label("A-C");
        Label df = new Label("D-F");
        Label one = new Label("1-4");
        Label two = new Label("5-8");

        

        Image q1 = loadImage("1.jpg");
        ImageView q1view = new ImageView(q1);
        q1view.setFitHeight(40.0);
        q1view.setFitWidth(60.0);
        Image q2 = loadImage("2.jpg");
        ImageView q2view = new ImageView(q2);
        q2view.setFitHeight(40.0);
        q2view.setFitWidth(60.0);
        Image q3 = loadImage("3.jpg");
        ImageView q3view = new ImageView(q3);
        q3view.setFitHeight(40.0);
        q3view.setFitWidth(60.0);
        Image q4 = loadImage("4.jpg");
        ImageView q4view = new ImageView(q4);
        q4view.setFitHeight(40.0);
        q4view.setFitWidth(60.0);
        b1.setGraphic(q1view);
        b2.setGraphic(q2view);
        b3.setGraphic(q3view);
        b4.setGraphic(q4view);
        Image die_6 = loadImage("die_6.jpg");
        ImageView im = new ImageView(die_6);
        die.setGraphic(im);
//        setPlayerAtHome(randomGreen);
//        setPlayerCoordinates1(randomRed);
//        setPlayerCoordinates2(randomYellow);

        //JLabel MyImage = new JLabel(new ImageIcon("image"+randomNum+".png"));
       

        grd.add(ac, 1, 0);
        grd.add(df, 2, 0);
        grd.add(one, 0, 1);
        grd.add(two, 0, 2);

        // Buttons are thrown into the grd.
        grd.add(b1, 1, 1);
        grd.add(b2, 2, 1);
        grd.add(b3, 1, 2);
        grd.add(b4, 2, 2);

        Button endTurn = new Button("End Turn");
        endTurn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //setDice();
                //turn++;
                // playerNo = turn % (Integer.parseInt(number));
                //turnLabel.setText("Player " + (playerNo + 1) + " turn");
            }

        });

        vbox.getChildren().addAll(turnLabel, grd, fixedMovesLabel,die, flightButton, histButton, abtButton, saveButton);
        vbox.setSpacing(10);
        canvasHandler1();
        pane.setLeft(sideBar);
        pane.setRight(vbox);
        //pane.setLeft(player.sideBar);
        mainPane.setCenter(pane);

    }

   public void canvasHandler1() {
       canvas=canvas1;
        if(player1.turn!=1)
        {
           xhist1=player1.citiesVisitedXCoord.get(player1.citiesVisitedXCoord.size()-1);
           yhist1=player1.citiesVisitedYCoord.get(player1.citiesVisitedYCoord.size()-1);
           die.setDisable(false);
           travelAnimation(blackPieceView,xhist1,yhist1);
        }
        gc=canvas1.getGraphicsContext2D();
         die.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                randomNum = rand.nextInt(6) + 1;
                Image dieimage = loadImage("die_" + randomNum + ".jpg");
                ImageView dieimageview = new ImageView(dieimage);
                die.setGraphic(dieimageview);
                die.setDisable(true);
                player1.movesLeft = randomNum;
                System.out.println("In die handler player 1: "+player1.movesLeft);
                fixedMovesLabel.setText("Moves Left:"+player1.movesLeft);
            }
        });
        b1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // TODO Auto-generated method stub
                gc.drawImage(img1, 0, 0, 571, 700);
                canvasPane.getChildren().clear();
                canvasPane.setCenter(canvas1);
                quad = 1;
                    if(qp1==1)
                    {
                        //if()
                    drawHomeFlag1();
                    drawPiece1();
                    }
                
               
            }
        });
        b2.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                // TODO Auto-generated method stub
                gc.drawImage(img2, 0, 0, 571, 700);
                quad = 2;
                canvasPane.getChildren().clear();
                canvasPane.setCenter(canvas1);
                 if(qp1==2)
                    {
                    drawHomeFlag1();
                    drawPiece1();
                    }
                  
            }
               
        });
        b3.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                // TODO Auto-generated method stub
                gc.drawImage(img3, 0, 0, 571, 700);
                canvasPane.getChildren().clear();
                canvasPane.setCenter(canvas1);
                quad = 3;
                 if(qp1==3)
                    {
                    drawHomeFlag1();
                    drawPiece1();
                    }
                
            }
        });
        b4.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                // TODO Auto-generated method stub
                canvasPane.getChildren().clear();
                canvasPane.setCenter(canvas1);
                gc.drawImage(img4, 0, 0, 571, 700);
                quad = 4;
                
                  if(qp1==4)
                    {
                    drawHomeFlag1();
                    drawPiece1();
                    }
                
                

            }
        });
        gc.setStroke(Color.RED);
        gc.setLineWidth(2);
        canvas1.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                int x = (int) e.getX();
                int y = (int) e.getY();
                mouseClicked++;
                Cities ct;
                System.out.println(x + " " + y);
                for (int i = 0; i < lis.size(); i++) {
                    ct = lis.get(i);
                    if(player1.movesLeft==0&&(Integer.parseInt(noOfPlayers)>1))
                    {
//                        if(Integer.parseInt(noOfPlayers)<2)
//                        {
//                             turnLabel.setText("Player 2 Turn");
//                            canvasHandler2();
//                            cardAnimation2();
//                            break;
//                        }
                         
                        die.setDisable(false);
                        player1.turn++;
                        canvasHandler2();
                        cardAnimation2();
                         turnLabel.setText("Player 2 Turn");
                        break;
                    }
                    if (player1.movesLeft > 0&&(Integer.parseInt(noOfPlayers)>1)) {
                        if (x > (ct.getX() - 10) && x < (ct.getX() + 10)) {
                            if (y > (ct.getY() - 10) && y < (ct.getY() + 10)) {
                                
                            
                                List<Cities> tempLand = ct.fetchLandNeighbour();
                                List<Cities> tempSea = ct.fetchSeaNeighbour();
                                if (ct.getQuadrant() == 1 && quad == 1) {
                                    player1.movesLeft--;
                                    histArea.appendText("\nPlayer 1 moved to "+ct.getCityName());
//                                    if(ct.getCityName()==airportCityClicked.get(0))
//                                    {
//                                        player1.movesLeft=player1.movesLeft-2;
//                                        travelAnimation(blackPieceView,ct.getX(),ct.getY());
//                                    }
                                    fixedMovesLabel.setText("Moves Left:"+player1.movesLeft);
                                    
                                    q = 1;
                                    for (Cities Neighbors : tempLand) {
                                        System.out.println(Neighbors.getX() + "," + Neighbors.getY() + "," + ct.getX() + "," + ct.getY());
                                        // if(Neighbors.getCityName()!=)
                                       // b1.fire();
                                        gc.strokeLine(ct.getX(), ct.getY(), Neighbors.getX(), Neighbors.getY());
                                        travelAnimation(blackPieceView, ct.getX(), ct.getY());
                                         player1.citiesVisited.add(ct.getCityName());
                                        player1.citiesVisitedXCoord.add(ct.getX());
                                            player1.citiesVisitedYCoord.add(ct.getY());
                                           
                                        System.out.println("In templand quad 1"+player1.movesLeft);
                                    }

                                    for (Cities Neighbors : tempSea) {
                                        System.out.println(Neighbors.getX() + "," + Neighbors.getY() + "," + ct.getX() + "," + ct.getY());
                                        gc.strokeLine(ct.getX(), ct.getY(), Neighbors.getX(), Neighbors.getY());
                                        travelAnimation(blackPieceView, ct.getX(), ct.getY());
                                         player1.citiesVisited.add(ct.getCityName());
                                        player1.citiesVisitedXCoord.add(ct.getX());
                                            player1.citiesVisitedYCoord.add(ct.getY());
                                       
                                         System.out.println("In tempsea quad 1"+player1.movesLeft);

                                    }
                                    String data1 = ct.getCityName() + "," + (ct.getX()) + "," + (ct.getY());
                                    System.out.println(ct.getCityName());
                                  
                                    //JOptionPane.showMessageDialog(null,data1);
                                    continue;
                                }
                                if (ct.getQuadrant() == 2 && quad == 2) {
                                   player1.movesLeft--;
                                   histArea.appendText("\nPlayer 1 moved to "+ct.getCityName());
//                                   if(ct.getCityName()==airportCityClicked.get(0))
//                                    {
//                                        player1.movesLeft=player1.movesLeft-2;
//                                        travelAnimation(blackPieceView,ct.getX(),ct.getY());
//                                    }
                                   fixedMovesLabel.setText("Moves Left:"+player1.movesLeft);
                                   
                                    q = 2;
                                    for (Cities Neighbors : tempLand) {
                                        System.out.println(Neighbors.getX());
                                        gc.strokeLine(ct.getX(), ct.getY(), Neighbors.getX(), Neighbors.getY());

                                        travelAnimation(blackPieceView, ct.getX(), ct.getY());
                                        
                                         player1.citiesVisited.add(ct.getCityName());
                                        player1.citiesVisitedXCoord.add(ct.getX());
                                            player1.citiesVisitedYCoord.add(ct.getY());
                                           
                                            
                                                  
                                        //b2.fire();
                                       System.out.println("In templand quad 2 p1"+player1.movesLeft);

                                    }
                                    for (Cities Neighbors : tempSea) {
                                        System.out.println(Neighbors.getX() + "," + Neighbors.getY() + "," + ct.getX() + "," + ct.getY());
                                        // if(Neighbors.getCityName()!=)
                                        gc.strokeLine(ct.getX(), ct.getY(), Neighbors.getX(), Neighbors.getY());
                                        travelAnimation(blackPieceView, ct.getX(), ct.getY());
                                         player1.citiesVisited.add(ct.getCityName());
                                        player1.citiesVisitedXCoord.add(ct.getX());
                                            player1.citiesVisitedYCoord.add(ct.getY());
                                             
                                        //player1.movesLeft--;
                                        System.out.println("In tempsea quad2 player 1"+player1.movesLeft);
                                        // diceController();
                                    }
                                    String data2 = ct.getCityName() + "," + (ct.getX()) + "," + (ct.getY());
                                    System.out.println(ct.getCityName());
                                    //JOptionPane.showMessageDialog(null,data2);
                                    continue;
                                }
                                if (ct.getQuadrant() == 3 && quad == 3) {
                                    player1.movesLeft--;
                                     histArea.appendText("\nPlayer 1 moved to"+ct.getCityName());
//                                     if(ct.getCityName()==airportCityClicked.get(0))
//                                    {
//                                        player1.movesLeft=player1.movesLeft-2;
//                                        travelAnimation(blackPieceView,ct.getX(),ct.getY());
//                                    }
                                    fixedMovesLabel.setText("Moves Left:"+player1.movesLeft);
                                    
                                    q = 0;
                                    for (Cities Neighbors : tempLand) {
                                        System.out.println(Neighbors.getX());
                                        gc.strokeLine(ct.getX(), ct.getY(), Neighbors.getX(), Neighbors.getY());

                                        travelAnimation(blackPieceView, ct.getX(), ct.getY());
                                        
                                          player1.citiesVisited.add(ct.getCityName());
                                        player1.citiesVisitedXCoord.add(ct.getX());
                                            player1.citiesVisitedYCoord.add(ct.getY());
                                            
                                      //  b3.fire();
                                        System.out.println("In templand quad 3 p1 "+player1.movesLeft);

                                    }
                                    for (Cities Neighbors : tempSea) {

                                        System.out.println(Neighbors.getX() + "," + Neighbors.getY() + "," + ct.getX() + "," + ct.getY());
                                        // if(Neighbors.getCityName()!=)
                                        gc.strokeLine(ct.getX(), ct.getY(), Neighbors.getX(), Neighbors.getY());
                                        travelAnimation(blackPieceView, ct.getX(), ct.getY());
                                          player1.citiesVisited.add(ct.getCityName());
                                        player1.citiesVisitedXCoord.add(ct.getX());
                                            player1.citiesVisitedYCoord.add(ct.getY());
                                            
                                         System.out.println("In tempsea quad 3 p1 "+player1.movesLeft);
                                        //player1.movesLeft--;
                                        //System.out.println(player1.movesLeft);

                                    }
                                    String data3 = ct.getCityName() + "," + (ct.getX()) + "," + (ct.getY());
                                    System.out.println(ct.getCityName());
                                    // JOptionPane.showMessageDialog(null,data3);
                                    continue;
                                }
                                if (ct.getQuadrant() == 4 && quad == 4) {
                                      player1.movesLeft--;
                                      histArea.appendText("\nPlayer 1 moved to"+ct.getCityName());
                                      fixedMovesLabel.setText("Moves Left:"+player1.movesLeft);
//                                      if(ct.getCityName()==airportCityClicked.get(0))
//                                    {
//                                        player1.movesLeft=player1.movesLeft-2;
//                                        travelAnimation(blackPieceView,ct.getX(),ct.getY());
//                                    }
                                    q = 0;
                                    
                                    for (Cities Neighbors : tempLand) {
                                        System.out.println(Neighbors.getX());
                                        gc.strokeLine(ct.getX(), ct.getY(), Neighbors.getX(), Neighbors.getY());

                                        travelAnimation(blackPieceView, ct.getX(), ct.getY());
                                      
                                          player1.citiesVisited.add(ct.getCityName());
                                        player1.citiesVisitedXCoord.add(ct.getX());
                                            player1.citiesVisitedYCoord.add(ct.getY());
                                             
                                         System.out.println("In templand quad 4 p1 "+player1.movesLeft);
                                        
                                        // b4.fire();
                                    }
                                    for (Cities Neighbors : tempSea) {
                                        System.out.println(Neighbors.getX() + "," + Neighbors.getY() + "," + ct.getX() + "," + ct.getY());
                                        // if(Neighbors.getCityName()!=)
                                        gc.strokeLine(ct.getX(), ct.getY(), Neighbors.getX(), Neighbors.getY());
                                        travelAnimation(blackPieceView, ct.getX(), ct.getY());
                                          player1.citiesVisited.add(ct.getCityName());
                                        player1.citiesVisitedXCoord.add(ct.getX());
                                            player1.citiesVisitedYCoord.add(ct.getY());
                                     
                                    System.out.println("In templand quad 4 p1 "+player1.movesLeft);

                                    }
                                    String data4 = ct.getCityName() + "," + (ct.getX()) + "," + (ct.getY());
                                    System.out.println(ct.getCityName());
                                    //JOptionPane.showMessageDialog(null,data4);
                                   
                                }
                            }
                        }
                    }

                }

            }
        });
        canvasPane.setCenter(canvas1);
        pane.setCenter(canvasPane);
    }

    

    public void canvasHandler2() {
        canvas=canvas2;
        if(player2.turn!=1)
        {
           xhist2=player2.citiesVisitedXCoord.get(player2.citiesVisitedXCoord.size()-1);
       yhist2=player2.citiesVisitedYCoord.get(player2.citiesVisitedYCoord.size()-1);
        travelAnimation(bluePieceView,xhist2,yhist2);
        }
        
        gc=canvas2.getGraphicsContext2D();
        gc.drawImage(img1, 0, 0, 571, 700);
         die.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                randomNum = rand.nextInt(6) + 1;
                Image dieimage = loadImage("die_" + randomNum + ".jpg");
                ImageView dieimageview = new ImageView(dieimage);
                die.setGraphic(dieimageview);
                die.setDisable(true);
                player2.movesLeft = randomNum;
                fixedMovesLabel.setText("Moves Left:"+player2.movesLeft);
                System.out.println("in canvas handler 2"+player2.movesLeft);

            }
        });
        
        b1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // TODO Auto-generated method stub
                gc.drawImage(img1, 0, 0, 571, 700);
                canvasPane.getChildren().clear();
                canvasPane.setCenter(canvas2);
                quad = 1;
                 if(qp2==1)
                    {
                    drawHomeFlag2();
                    drawPiece2();
                    }
                
            }
        });
        b2.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                // TODO Auto-generated method stub
                gc.drawImage(img2, 0, 0, 571, 700);
                quad = 2;
                canvasPane.getChildren().clear();
                canvasPane.setCenter(canvas2);
               
                    if(qp2==2)
                    {
                    drawHomeFlag2();
                    drawPiece2();
                    }
                
            }
        });
        b3.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                // TODO Auto-generated method stub
                gc.drawImage(img3, 0, 0, 571, 700);
                canvasPane.getChildren().clear();
                canvasPane.setCenter(canvas2);
                quad = 3;
               
                 if(qp2==3)
                    {
                    drawHomeFlag2();
                    drawPiece2();
                    }

            }
        });
        b4.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                // TODO Auto-generated method stub
                canvasPane.getChildren().clear();
                canvasPane.setCenter(canvas2);
                gc.drawImage(img4, 0, 0, 571, 700);
                quad = 4;
                
                if(qp2==4)
                    {
                    drawHomeFlag2();
                    drawPiece2();
                    }
               

            }
        });
        gc.setStroke(Color.RED);
        gc.setLineWidth(2);
        canvas2.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                int x = (int) e.getX();
                int y = (int) e.getY();
                mouseClicked++;
                Cities ct;
                System.out.println(x + " " + y);
                for (int i = 0; i < lis.size(); i++) {
                    ct = lis.get(i);
                    if(player2.movesLeft==0&&(Integer.parseInt(noOfPlayers)>1))
                    {
                        player2.turn++;
                        if(Integer.parseInt(noOfPlayers)<3)
                        {
                            player1.turn++;
                             turnLabel.setText("Player 1 Turn");
                            canvasHandler1();
                            break;
                        }
                        
                        cardAnimation3();
                        die.setDisable(false);
                        canvasHandler3();
                         turnLabel.setText("Player 3 Turn");
                        break;
                    }
                    if (player2.movesLeft > 0&&(Integer.parseInt(noOfPlayers)>1)) {
                        if (x > (ct.getX() - 10) && x < (ct.getX() + 10)) {
                            if (y > (ct.getY() - 10) && y < (ct.getY() + 10)) {
                                List<Cities> tempLand = ct.fetchLandNeighbour();
                                List<Cities> tempSea = ct.fetchSeaNeighbour();
                                if (ct.getQuadrant() == 1 && quad == 1) {
                                    player2.movesLeft--;
                                     histArea.appendText("\nPlayer 2 moved to "+ct.getCityName());
                                    fixedMovesLabel.setText("Moves Left:"+player2.movesLeft);
                                    q = 1;
                                    for (Cities Neighbors : tempLand) {
                                        System.out.println(Neighbors.getX() + "," + Neighbors.getY() + "," + ct.getX() + "," + ct.getY());
                                        // if(Neighbors.getCityName()!=)
                                       // b1.fire();
                                        gc.strokeLine(ct.getX(), ct.getY(), Neighbors.getX(), Neighbors.getY());
                                        
                                        travelAnimation(bluePieceView, ct.getX(), ct.getY());
                                         
                                         player2.citiesVisited.add(ct.getCityName());
                                        player2.citiesVisitedXCoord.add(ct.getX());
                                            player2.citiesVisitedYCoord.add(ct.getY());
                                            
                                        System.out.println(player2.movesLeft);
                                    }

                                    for (Cities Neighbors : tempSea) {
                                        System.out.println(Neighbors.getX() + "," + Neighbors.getY() + "," + ct.getX() + "," + ct.getY());
                                        gc.strokeLine(ct.getX(), ct.getY(), Neighbors.getX(), Neighbors.getY());
                                        travelAnimation(bluePieceView, ct.getX(), ct.getY());
                                         player2.citiesVisited.add(ct.getCityName());
                                        player2.citiesVisitedXCoord.add(ct.getX());
                                            player2.citiesVisitedYCoord.add(ct.getY());
                                            
                                         System.out.println(player2.movesLeft);

                                    }
                                    String data1 = ct.getCityName() + "," + (ct.getX()) + "," + (ct.getY());
                                    System.out.println(ct.getCityName());
                                  
                                    //JOptionPane.showMessageDialog(null,data1);
                                    continue;
                                }
                                if (ct.getQuadrant() == 2 && quad == 2) {
                                    player2.movesLeft--;
                                    fixedMovesLabel.setText("Moves Left:"+player2.movesLeft);
                                    histArea.appendText("\nPlayer 2 moved to "+ct.getCityName());
                                    q = 2;
                                    for (Cities Neighbors : tempLand) {
                                        System.out.println(Neighbors.getX());
                                        gc.strokeLine(ct.getX(), ct.getY(), Neighbors.getX(), Neighbors.getY());

                                        travelAnimation(bluePieceView, ct.getX(), ct.getY());
                                        
                                         player2.citiesVisited.add(ct.getCityName());
                                        player2.citiesVisitedXCoord.add(ct.getX());
                                            player2.citiesVisitedYCoord.add(ct.getY());
                                            
                                        //b2.fire();
                                        System.out.println(player2.movesLeft);

                                    }
                                    for (Cities Neighbors : tempSea) {
                                        System.out.println(Neighbors.getX() + "," + Neighbors.getY() + "," + ct.getX() + "," + ct.getY());
                                        // if(Neighbors.getCityName()!=)
                                        gc.strokeLine(ct.getX(), ct.getY(), Neighbors.getX(), Neighbors.getY());
                                        travelAnimation(bluePieceView, ct.getX(), ct.getY());
                                         player2.citiesVisited.add(ct.getCityName());
                                        player2.citiesVisitedXCoord.add(ct.getX());
                                            player2.citiesVisitedYCoord.add(ct.getY());
                                             
                                        //player2.movesLeft--;
                                        System.out.println(player2.movesLeft);
                                        // diceController();
                                    }
                                    String data2 = ct.getCityName() + "," + (ct.getX()) + "," + (ct.getY());
                                    System.out.println(ct.getCityName());
                                    //JOptionPane.showMessageDialog(null,data2);
                                    continue;
                                }
                                if (ct.getQuadrant() == 3 && quad == 3) {
                                    player2.movesLeft--;
                                    fixedMovesLabel.setText("Moves Left:"+player2.movesLeft);
                                    histArea.appendText("\nPlayer 2 moved to"+ct.getCityName());
                                    q = 0;
                                    for (Cities Neighbors : tempLand) {
                                        System.out.println(Neighbors.getX());
                                        gc.strokeLine(ct.getX(), ct.getY(), Neighbors.getX(), Neighbors.getY());

                                        travelAnimation(bluePieceView, ct.getX(), ct.getY());
                                        
                                          player2.citiesVisited.add(ct.getCityName());
                                        player2.citiesVisitedXCoord.add(ct.getX());
                                            player2.citiesVisitedYCoord.add(ct.getY());
                                             
                                      //  b3.fire();
                                        System.out.println(player2.movesLeft);

                                    }
                                    for (Cities Neighbors : tempSea) {

                                        System.out.println(Neighbors.getX() + "," + Neighbors.getY() + "," + ct.getX() + "," + ct.getY());
                                        // if(Neighbors.getCityName()!=)
                                        gc.strokeLine(ct.getX(), ct.getY(), Neighbors.getX(), Neighbors.getY());
                                        travelAnimation(bluePieceView, ct.getX(), ct.getY());
                                          player2.citiesVisited.add(ct.getCityName());
                                        player2.citiesVisitedXCoord.add(ct.getX());
                                            player2.citiesVisitedYCoord.add(ct.getY());
                                         
                                        //player2.movesLeft--;
                                        System.out.println(player2.movesLeft);

                                    }
                                    String data3 = ct.getCityName() + "," + (ct.getX()) + "," + (ct.getY());
                                    System.out.println(ct.getCityName());
                                    // JOptionPane.showMessageDialog(null,data3);
                                    continue;
                                }
                                if (ct.getQuadrant() == 4 && quad == 4) {
                                      player2.movesLeft--;
                                      fixedMovesLabel.setText("Moves Left:"+player2.movesLeft);
                                       histArea.appendText("\nPlayer 2 moved to"+ct.getCityName());
                                    q = 0;
                                    for (Cities Neighbors : tempLand) {
                                        System.out.println(Neighbors.getX());
                                        gc.strokeLine(ct.getX(), ct.getY(), Neighbors.getX(), Neighbors.getY());

                                        travelAnimation(bluePieceView, ct.getX(), ct.getY());
                                      
                                          player2.citiesVisited.add(ct.getCityName());
                                        player2.citiesVisitedXCoord.add(ct.getX());
                                            player2.citiesVisitedYCoord.add(ct.getY());
                                             
                                            
                                        System.out.println(player2.movesLeft);
                                        // b4.fire();
                                    }
                                    for (Cities Neighbors : tempSea) {
                                        System.out.println(Neighbors.getX() + "," + Neighbors.getY() + "," + ct.getX() + "," + ct.getY());
                                        // if(Neighbors.getCityName()!=)
                                        gc.strokeLine(ct.getX(), ct.getY(), Neighbors.getX(), Neighbors.getY());
                                        travelAnimation(bluePieceView, ct.getX(), ct.getY());
                                          player2.citiesVisited.add(ct.getCityName());
                                        player2.citiesVisitedXCoord.add(ct.getX());
                                            player2.citiesVisitedYCoord.add(ct.getY());
                                              
                                       //player2.movesLeft--;
                                        System.out.println(player2.movesLeft);

                                    }
                                    String data4 = ct.getCityName() + "," + (ct.getX()) + "," + (ct.getY());
                                    System.out.println(ct.getCityName());
                                    //JOptionPane.showMessageDialog(null,data4);
                                   
                                }
                            }
                        }
                    }

                }

            }
        });
        canvasPane.setCenter(canvas2);
        pane.setCenter(canvasPane);
    }

    public void canvasHandler3() {
        canvas=canvas3;
        if(player3.turn!=1)
        {
           xhist3=player3.citiesVisitedXCoord.get(player3.citiesVisitedXCoord.size()-1);
       yhist3=player3.citiesVisitedYCoord.get(player3.citiesVisitedYCoord.size()-1);
        travelAnimation(greenPieceView,xhist3,yhist3);
        
        }
         die.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                randomNum = rand.nextInt(6) + 1;
                Image dieimage = loadImage("die_" + randomNum + ".jpg");
                ImageView dieimageview = new ImageView(dieimage);
                die.setGraphic(dieimageview);
                die.setDisable(true);
                player3.movesLeft = randomNum;
                System.out.println(player3.movesLeft);
                fixedMovesLabel.setText("Moves Left:"+player3.movesLeft);

            }
        });
        gc=canvas3.getGraphicsContext2D();
        gc.drawImage(img1, 0, 0, 571, 700);
        b1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // TODO Auto-generated method stub
                gc.drawImage(img1, 0, 0, 571, 700);
                canvasPane.getChildren().clear();
                canvasPane.setCenter(canvas3);
                quad = 1;
                
                    if(qp3==1)
                    {
                    drawHomeFlag3();
                    drawPiece3();
                    }
                
            }
        });
        b2.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                // TODO Auto-generated method stub
                gc.drawImage(img2, 0, 0, 571, 700);
                quad = 2;
                canvasPane.getChildren().clear();
                canvasPane.setCenter(canvas3);
                
                     if(qp3==2)
                    {
                    drawHomeFlag3();
                    drawPiece3();
                    }
                
            }
        });
        b3.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                // TODO Auto-generated method stub
                gc.drawImage(img3, 0, 0, 571, 700);
                canvasPane.getChildren().clear();
                canvasPane.setCenter(canvas3);
                quad = 3;
                 if(qp3==3)
                    {
                    drawHomeFlag3();
                    drawPiece3();
                    }
                

            }
        });
        b4.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                // TODO Auto-generated method stub
                canvasPane.getChildren().clear();
                canvasPane.setCenter(canvas3);
                gc.drawImage(img4, 0, 0, 571, 700);
                quad = 4;
                
                if(qp3==4)
                    {
                    drawHomeFlag3();
                    drawPiece3();
                    }

            }
        });
        gc.setStroke(Color.RED);
        gc.setLineWidth(4);
        canvas3.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                int x = (int) e.getX();
                int y = (int) e.getY();
                mouseClicked++;
                Cities ct;
                System.out.println(x + " " + y);
                for (int i = 0; i < lis.size(); i++) {
                    ct = lis.get(i);
                    if(player3.movesLeft==0&&(Integer.parseInt(noOfPlayers)>2))
                    {
                        
                        player3.turn++;
                        if(Integer.parseInt(noOfPlayers)<4)
                        {
                            player1.turn++;
                            canvasHandler1();
                             turnLabel.setText("Player 1 Turn");
                            break;
                        }
                        die.setDisable(false);
                        cardAnimation4();
                          turnLabel.setText("Player 4 Turn");       
                        canvasHandler4();
                        break;
                    }
                    if (player3.movesLeft > 0&&(Integer.parseInt(noOfPlayers)>2)) {
                        if (x > (ct.getX() - 10) && x < (ct.getX() + 10)) {
                            if (y > (ct.getY() - 10) && y < (ct.getY() + 10)) {
                                List<Cities> tempLand = ct.fetchLandNeighbour();
                                List<Cities> tempSea = ct.fetchSeaNeighbour();
                                if (ct.getQuadrant() == 1 && quad == 1) {
                                    player3.movesLeft--;
                                    histArea.appendText("\nPlayer 3 moved to"+ct.getCityName());
                                    fixedMovesLabel.setText("Moves Left:"+player3.movesLeft);
                                    q = 1;
                                    for (Cities Neighbors : tempLand) {
                                        System.out.println(Neighbors.getX() + "," + Neighbors.getY() + "," + ct.getX() + "," + ct.getY());
                                        // if(Neighbors.getCityName()!=)
                                        //b1.fire();
                                        gc.strokeLine(ct.getX(), ct.getY(), Neighbors.getX(), Neighbors.getY());
                                        travelAnimation(greenPieceView, ct.getX(), ct.getY());
                                        player3.citiesVisited.add(ct.getCityName());
                                        player3.citiesVisitedXCoord.add(ct.getX());
                                          
                                            player3.citiesVisitedYCoord.add(ct.getY());
                                        System.out.println(player3.movesLeft);
                                    }

                                    for (Cities Neighbors : tempSea) {
                                        System.out.println(Neighbors.getX() + "," + Neighbors.getY() + "," + ct.getX() + "," + ct.getY());
                                        gc.strokeLine(ct.getX(), ct.getY(), Neighbors.getX(), Neighbors.getY());
                                        travelAnimation(greenPieceView, ct.getX(), ct.getY());
                                        player3.citiesVisited.add(ct.getCityName());
                                        player3.citiesVisitedXCoord.add(ct.getX());
                                            player3.citiesVisitedYCoord.add(ct.getY());
                                             
                                        //player3.moves--;
                                        // System.out.println(player3.moves);

                                    }
                                    String data1 = ct.getCityName() + "," + (ct.getX()) + "," + (ct.getY());
                                    System.out.println(ct.getCityName());
                                    player3.citiesVisited.add(ct.getCityName());

                                    //JOptionPane.showMessageDialog(null,data1);
                                    continue;
                                }
                                if (ct.getQuadrant() == 2 && quad == 2) {
                                    player3.movesLeft--;
                                    histArea.appendText("\nPlayer 3 moved to"+ct.getCityName());
                                     fixedMovesLabel.setText("Moves Left:"+player3.movesLeft);
                                    q = 2;
                                    for (Cities Neighbors : tempLand) {
                                        System.out.println(Neighbors.getX());
                                        gc.strokeLine(ct.getX(), ct.getY(), Neighbors.getX(), Neighbors.getY());
                                        player3.citiesVisited.add(ct.getCityName());
                                        player3.citiesVisitedXCoord.add(ct.getX());
                                            player3.citiesVisitedYCoord.add(ct.getY());
                                        travelAnimation(greenPieceView, ct.getX(), ct.getY());
                                          
                                       // b2.fire();
                                        System.out.println(player3.movesLeft);

                                    }
                                    for (Cities Neighbors : tempSea) {
                                        System.out.println(Neighbors.getX() + "," + Neighbors.getY() + "," + ct.getX() + "," + ct.getY());
                                        // if(Neighbors.getCityName()!=)
                                        gc.strokeLine(ct.getX(), ct.getY(), Neighbors.getX(), Neighbors.getY());
                                        player3.citiesVisited.add(ct.getCityName());
                                        player3.citiesVisitedXCoord.add(ct.getX());
                                        player3.citiesVisitedYCoord.add(ct.getY());
                                        travelAnimation(greenPieceView, ct.getX(), ct.getY());
                                         
                                        //player3.moves--;
                                        //System.out.println(player3.moves);
                                        // diceController();
                                    }
                                    String data2 = ct.getCityName() + "," + (ct.getX()) + "," + (ct.getY());
                                    System.out.println(ct.getCityName());
                                    //JOptionPane.showMessageDialog(null,data2);
                                    continue;
                                }
                                if (ct.getQuadrant() == 3 && quad == 3) {
                                    player3.movesLeft--;
                                     fixedMovesLabel.setText("Moves Left:"+player3.movesLeft);
                                     histArea.appendText("\nPlayer 3 moved to"+ct.getCityName());
                                    q = 0;
                                    for (Cities Neighbors : tempLand) {
                                        System.out.println(Neighbors.getX());
                                        gc.strokeLine(ct.getX(), ct.getY(), Neighbors.getX(), Neighbors.getY());

                                        travelAnimation(greenPieceView, ct.getX(), ct.getY());
                                        player3.citiesVisited.add(ct.getCityName());
                                        player3.citiesVisitedXCoord.add(ct.getX());
                                            player3.citiesVisitedYCoord.add(ct.getY());
                                              
                                       // b3.fire();
                                        System.out.println(player3.movesLeft);

                                    }
                                    for (Cities Neighbors : tempSea) {

                                        System.out.println(Neighbors.getX() + "," + Neighbors.getY() + "," + ct.getX() + "," + ct.getY());
                                        // if(Neighbors.getCityName()!=)
                                        gc.strokeLine(ct.getX(), ct.getY(), Neighbors.getX(), Neighbors.getY());
                                        travelAnimation(greenPieceView, ct.getX(), ct.getY());
                                        player3.citiesVisited.add(ct.getCityName());
                                        player3.citiesVisitedXCoord.add(ct.getX());
                                        player3.citiesVisitedYCoord.add(ct.getY());
                                         
                                        //player3.moves--;
                                        System.out.println(player3.movesLeft);

                                    }
                                    String data3 = ct.getCityName() + "," + (ct.getX()) + "," + (ct.getY());
                                    System.out.println(ct.getCityName());
                                    // JOptionPane.showMessageDialog(null,data3);
                                    continue;
                                }
                                if (ct.getQuadrant() == 4 && quad == 4) {
                                    player3.movesLeft--;
                                    histArea.appendText("\nPlayer 3 moved to"+ct.getCityName());
                                     fixedMovesLabel.setText("Moves Left:"+player3.movesLeft);
                                    q = 0;
                                    for (Cities Neighbors : tempLand) {
                                        System.out.println(Neighbors.getX());
                                        gc.strokeLine(ct.getX(), ct.getY(), Neighbors.getX(), Neighbors.getY());

                                        travelAnimation(greenPieceView, ct.getX(), ct.getY());
                                        player3.citiesVisited.add(ct.getCityName());
                                        player3.citiesVisitedXCoord.add(ct.getX());
                                         player3.citiesVisitedYCoord.add(ct.getY());
                                           
                                        System.out.println(player3.movesLeft);
                                        // b4.fire();
                                    }
                                    for (Cities Neighbors : tempSea) {
                                        System.out.println(Neighbors.getX() + "," + Neighbors.getY() + "," + ct.getX() + "," + ct.getY());
                                        // if(Neighbors.getCityName()!=)
                                        gc.strokeLine(ct.getX(), ct.getY(), Neighbors.getX(), Neighbors.getY());
                                        travelAnimation(greenPieceView, ct.getX(), ct.getY());
                                        player3.citiesVisited.add(ct.getCityName());
                                        player3.citiesVisitedXCoord.add(ct.getX());
                                            player3.citiesVisitedYCoord.add(ct.getY());
                                        //player3.moves--;
                                        System.out.println(player3.movesLeft);

                                    }
                                    String data4 = ct.getCityName() + "," + (ct.getX()) + "," + (ct.getY());
                                    System.out.println(ct.getCityName());
                                    //JOptionPane.showMessageDialog(null,data4);
                                   
                                }
                            }
                        }
                    }

                }

            }
        });
        canvasPane.setCenter(canvas3);
        pane.setCenter(canvasPane);
    }

    public void canvasHandler4() {
        canvas=canvas4;
         if(player4.turn!=1)
        {
           xhist4=player4.citiesVisitedXCoord.get(player4.citiesVisitedXCoord.size()-1);
       yhist4=player4.citiesVisitedYCoord.get(player4.citiesVisitedYCoord.size()-1);
        travelAnimation(bluePieceView,xhist4,yhist4);
        }
         die.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                randomNum = rand.nextInt(6) + 1;
                Image dieimage = loadImage("die_" + randomNum + ".jpg");
                ImageView dieimageview = new ImageView(dieimage);
                die.setGraphic(dieimageview);
                die.setDisable(true);
                player4.movesLeft = randomNum;
                System.out.println(player4.movesLeft);
                fixedMovesLabel.setText("Moves Left:"+player4.movesLeft);

            }
        });
        gc=canvas4.getGraphicsContext2D();
        gc.drawImage(img1, 0, 0, 571, 700);
        b1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // TODO Auto-generated method stub
                gc.drawImage(img1, 0, 0, 571, 700);
                canvasPane.getChildren().clear();
                canvasPane.setCenter(canvas4);
                quad = 1;
                if(qp4==1)
                    {
                    drawHomeFlag4();
                    drawPiece4();
                    }
                
                
            }
        });
        b2.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                // TODO Auto-generated method stub
                gc.drawImage(img2, 0, 0, 571, 700);
                quad = 2;
                canvasPane.getChildren().clear();
                canvasPane.setCenter(canvas4);
               if(qp4==2)
                    {
                    drawHomeFlag4();
                    drawPiece4();
                    }
               
            }
        });
        b3.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                // TODO Auto-generated method stub
                gc.drawImage(img3, 0, 0, 571, 700);
                canvasPane.getChildren().clear();
                canvasPane.setCenter(canvas4);
                quad = 3;
              if(qp4==3)
                    {
                    drawHomeFlag4();
                    drawPiece4();
                    }
               

            }
        });
        b4.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                // TODO Auto-generated method stub
                canvasPane.getChildren().clear();
                canvasPane.setCenter(canvas4);
                gc.drawImage(img4, 0, 0, 571, 700);
                quad = 4;
                
               if(qp4==4)
                    {
                    drawHomeFlag4();
                    drawPiece4();
                    }
                
                

            }
        });
        gc.setStroke(Color.RED);
        gc.setLineWidth(4);
        canvas4.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                int x = (int) e.getX();
                int y = (int) e.getY();
                mouseClicked++;
                Cities ct;
                System.out.println(x + " " + y);
                for (int i = 0; i < lis.size(); i++) {
                    ct = lis.get(i);
                   if(player4.movesLeft==0&&(Integer.parseInt(noOfPlayers)>3))
                    {
                        player4.turn++;
                         if(Integer.parseInt(noOfPlayers)<5)
                        {
                            player1.turn++;
                            canvasHandler1();
                             turnLabel.setText("Player 1 Turn");
                            break;
                        }
                       
                        cardAnimation5();
                        die.setDisable(false);
                        canvasHandler5();
                         turnLabel.setText("Player 5 Turn");
                        break;
                    }
                    if (player4.movesLeft > 0&&(Integer.parseInt(noOfPlayers)>3)) {
                        if (x > (ct.getX() - 10) && x < (ct.getX() + 10)) {
                            if (y > (ct.getY() - 10) && y < (ct.getY() + 10)) {
                                List<Cities> tempLand = ct.fetchLandNeighbour();
                                List<Cities> tempSea = ct.fetchSeaNeighbour();
                                if (ct.getQuadrant() == 1 && quad == 1) {
                                    player4.movesLeft--;
                                    histArea.appendText("\nPlayer 4 moved to"+ct.getCityName());
                                     fixedMovesLabel.setText("Moves Left:"+player4.movesLeft);
                                    q = 1;
                                    for (Cities Neighbors : tempLand) {
                                        System.out.println(Neighbors.getX() + "," + Neighbors.getY() + "," + ct.getX() + "," + ct.getY());
                                        // if(Neighbors.getCityName()!=)
                                       // b1.fire();
                                        gc.strokeLine(ct.getX(), ct.getY(), Neighbors.getX(), Neighbors.getY());
                                        travelAnimation(redPieceView, ct.getX(), ct.getY());
                                        player4.citiesVisited.add(ct.getCityName());
                                        player4.citiesVisitedXCoord.add(ct.getX());
                                            player4.citiesVisitedYCoord.add(ct.getY());
                                              

                                        System.out.println(player4.movesLeft);
                                    }

                                    for (Cities Neighbors : tempSea) {
                                        System.out.println(Neighbors.getX() + "," + Neighbors.getY() + "," + ct.getX() + "," + ct.getY());
                                        gc.strokeLine(ct.getX(), ct.getY(), Neighbors.getX(), Neighbors.getY());
                                        travelAnimation(redPieceView, ct.getX(), ct.getY());
                                        player4.citiesVisited.add(ct.getCityName());
                                        player4.citiesVisitedXCoord.add(ct.getX());
                                            player4.citiesVisitedYCoord.add(ct.getY());
                                            
                                        //player4.moves--;
                                        // System.out.println(player4.moves);

                                    }
                                    String data1 = ct.getCityName() + "," + (ct.getX()) + "," + (ct.getY());
                                    System.out.println(ct.getCityName());
                                    player4.citiesVisited.add(ct.getCityName());

                                    //JOptionPane.showMessageDialog(null,data1);
                                    continue;
                                }
                                if (ct.getQuadrant() == 2 && quad == 2) {
                                    player4.movesLeft--;
                                    histArea.appendText("\nPlayer 4 moved to"+ct.getCityName());
                                     fixedMovesLabel.setText("Moves Left:"+player4.movesLeft);
                                    q = 2;
                                    for (Cities Neighbors : tempLand) {
                                        System.out.println(Neighbors.getX());
                                        gc.strokeLine(ct.getX(), ct.getY(), Neighbors.getX(), Neighbors.getY());

                                        travelAnimation(redPieceView, ct.getX(), ct.getY());
                                        player4.citiesVisited.add(ct.getCityName());
                                        player4.citiesVisitedXCoord.add(ct.getX());
                                        player4.citiesVisitedYCoord.add(ct.getY());
                                        
                                       // b2.fire();
                                        System.out.println(player4.movesLeft);

                                    }
                                    for (Cities Neighbors : tempSea) {
                                        System.out.println(Neighbors.getX() + "," + Neighbors.getY() + "," + ct.getX() + "," + ct.getY());
                                        // if(Neighbors.getCityName()!=)
                                        gc.strokeLine(ct.getX(), ct.getY(), Neighbors.getX(), Neighbors.getY());
                                        travelAnimation(redPieceView, ct.getX(), ct.getY());
                                        player4.citiesVisited.add(ct.getCityName());
                                        player4.citiesVisitedXCoord.add(ct.getX());
                                            player4.citiesVisitedYCoord.add(ct.getY());
                                            
                                        //player4.moves--;
                                        //System.out.println(player4.moves);
                                        // diceController();
                                    }
                                    String data2 = ct.getCityName() + "," + (ct.getX()) + "," + (ct.getY());
                                    System.out.println(ct.getCityName());
                                    //JOptionPane.showMessageDialog(null,data2);
                                    continue;
                                }
                                if (ct.getQuadrant() == 3 && quad == 3) {
                                    player4.movesLeft--;
                                     fixedMovesLabel.setText("Moves Left:"+player4.movesLeft);
                                      histArea.appendText("\nPlayer 4 moved to"+ct.getCityName());
                                    q = 0;
                                    for (Cities Neighbors : tempLand) {
                                        System.out.println(Neighbors.getX());
                                        gc.strokeLine(ct.getX(), ct.getY(), Neighbors.getX(), Neighbors.getY());

                                        travelAnimation(redPieceView, ct.getX(), ct.getY());
                                        player4.citiesVisited.add(ct.getCityName());
                                        player4.citiesVisitedXCoord.add(ct.getX());
                                            player4.citiesVisitedYCoord.add(ct.getY());
                                           
                                        //b3.fire();
                                        System.out.println(player4.movesLeft);

                                    }
                                    for (Cities Neighbors : tempSea) {

                                        System.out.println(Neighbors.getX() + "," + Neighbors.getY() + "," + ct.getX() + "," + ct.getY());
                                        // if(Neighbors.getCityName()!=)
                                        gc.strokeLine(ct.getX(), ct.getY(), Neighbors.getX(), Neighbors.getY());
                                        travelAnimation(redPieceView, ct.getX(), ct.getY());
                                        player4.citiesVisited.add(ct.getCityName());
                                        player4.citiesVisitedXCoord.add(ct.getX());
                                            player4.citiesVisitedYCoord.add(ct.getY());
                                            
                                        //player4.moves--;
                                        System.out.println(player4.movesLeft);

                                    }
                                    String data3 = ct.getCityName() + "," + (ct.getX()) + "," + (ct.getY());
                                    System.out.println(ct.getCityName());
                                    // JOptionPane.showMessageDialog(null,data3);
                                    continue;
                                }
                                if (ct.getQuadrant() == 4 && quad == 4) {
                                    player4.movesLeft--;
                                     fixedMovesLabel.setText("Moves Left:"+player4.movesLeft);
                                     histArea.appendText("\nPlayer 4 moved to"+ct.getCityName());
                                    q = 0;
                                    for (Cities Neighbors : tempLand) {
                                        System.out.println(Neighbors.getX());
                                        gc.strokeLine(ct.getX(), ct.getY(), Neighbors.getX(), Neighbors.getY());

                                        travelAnimation(redPieceView, ct.getX(), ct.getY());
                                        player4.citiesVisited.add(ct.getCityName());
                                        player4.citiesVisitedXCoord.add(ct.getX());
                                        player4.citiesVisitedYCoord.add(ct.getY());
                                        
                                        System.out.println(player4.movesLeft);
                                        // b4.fire();
                                    }
                                    for (Cities Neighbors : tempSea) {
                                        System.out.println(Neighbors.getX() + "," + Neighbors.getY() + "," + ct.getX() + "," + ct.getY());
                                        // if(Neighbors.getCityName()!=)
                                        gc.strokeLine(ct.getX(), ct.getY(), Neighbors.getX(), Neighbors.getY());
                                        travelAnimation(redPieceView, ct.getX(), ct.getY());
                                        player4.citiesVisited.add(ct.getCityName());
                                        player4.citiesVisitedXCoord.add(ct.getX());
                                            player4.citiesVisitedYCoord.add(ct.getY());
                                            
                                        //player4.moves--;
                                        System.out.println(player4.movesLeft);

                                    }
                                    String data4 = ct.getCityName() + "," + (ct.getX()) + "," + (ct.getY());
                                    System.out.println(ct.getCityName());
                                    //JOptionPane.showMessageDialog(null,data4);
                                    
                                }
                            }
                        }
                    }

                }

            }
        });
        canvasPane.setCenter(canvas4);
        pane.setCenter(canvasPane);
    }

    public void canvasHandler5() {
        canvas=canvas5;
         if(player5.turn!=1)
        {
           xhist5=player5.citiesVisitedXCoord.get(player5.citiesVisitedXCoord.size()-1);
       yhist5=player5.citiesVisitedYCoord.get(player5.citiesVisitedYCoord.size()-1);
        travelAnimation(whitePieceView,xhist5,yhist5);
        }
         die.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                randomNum = rand.nextInt(6) + 1;
                Image dieimage = loadImage("die_" + randomNum + ".jpg");
                ImageView dieimageview = new ImageView(dieimage);
                die.setGraphic(dieimageview);
                die.setDisable(true);
                player5.movesLeft = randomNum;
                System.out.println(player5.movesLeft);
                fixedMovesLabel.setText("Moves Left:"+player5.movesLeft);
            }
        });
        gc=canvas5.getGraphicsContext2D();
        gc.drawImage(img1, 0, 0, 571, 700);
        b1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // TODO Auto-generated method stub
                gc.drawImage(img1, 0, 0, 571, 700);
                canvasPane.getChildren().clear();
                canvasPane.setCenter(canvas5);
                quad = 1;
               if(qp5==1)
                    {
                    drawHomeFlag5();
                    drawPiece5();
                    }
                
                
            }
        });
        b2.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                // TODO Auto-generated method stub
                gc.drawImage(img2, 0, 0, 571, 700);
                quad = 2;
                canvasPane.getChildren().clear();
                canvasPane.setCenter(canvas5);
               if(qp5==2)
                    {
                    drawHomeFlag5();
                    drawPiece5();
                    }
                
               
            }
        });
        b3.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                // TODO Auto-generated method stub
                gc.drawImage(img3, 0, 0, 571, 700);
                canvasPane.getChildren().clear();
                canvasPane.setCenter(canvas5);
                quad = 3;
               if(qp5==3)
                    {
                    drawHomeFlag5();
                    drawPiece5();
                    }
               

            }
        });
        b4.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                // TODO Auto-generated method stub
                canvasPane.getChildren().clear();
                canvasPane.setCenter(canvas5);
                gc.drawImage(img4, 0, 0, 571, 700);
                quad = 4;
                if(qp5==4)
                    {
                    drawHomeFlag5();
                    drawPiece5();
                    }
            }
        });
        gc.setStroke(Color.RED);
        gc.setLineWidth(4);
        canvas5.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                int x = (int) e.getX();
                int y = (int) e.getY();
                mouseClicked++;
                Cities ct;
                System.out.println(x + " " + y);
                for (int i = 0; i < lis.size(); i++) {
                    ct = lis.get(i);
                   if(player5.movesLeft==0&&(Integer.parseInt(noOfPlayers)>4))
                    {
                        player5.turn++;
                        if(Integer.parseInt(noOfPlayers)<6)
                        {
                            player1.turn++;
                            canvasHandler1();
                             turnLabel.setText("Player 1 Turn");
                            break;
                        }
                        cardAnimation6();
                        die.setDisable(false);
                        canvasHandler6();
                         turnLabel.setText("Player 6 Turn");
                        break;
                    }
                    if (player5.movesLeft > 0&&(Integer.parseInt(noOfPlayers)>4)) {
                        if (x > (ct.getX() - 10) && x < (ct.getX() + 10)) {
                            if (y > (ct.getY() - 10) && y < (ct.getY() + 10)) {
                                List<Cities> tempLand = ct.fetchLandNeighbour();
                                List<Cities> tempSea = ct.fetchSeaNeighbour();
                                if (ct.getQuadrant() == 1 && quad == 1) {
                                    player5.movesLeft--;
                                     fixedMovesLabel.setText("Moves Left:"+player5.movesLeft);
                                     histArea.appendText("\nPlayer 5 moved to"+ct.getCityName());
                                    q = 1;
                                    for (Cities Neighbors : tempLand) {
                                        System.out.println(Neighbors.getX() + "," + Neighbors.getY() + "," + ct.getX() + "," + ct.getY());
                                        // if(Neighbors.getCityName()!=)
                                       // b1.fire();
                                        gc.strokeLine(ct.getX(), ct.getY(), Neighbors.getX(), Neighbors.getY());
                                        travelAnimation(whitePieceView, ct.getX(), ct.getY());
                                        player5.citiesVisited.add(ct.getCityName());
                                        player5.citiesVisitedXCoord.add(ct.getX());
                                            player5.citiesVisitedYCoord.add(ct.getY());
                                            
                                        System.out.println(player5.movesLeft);
                                    }

                                    for (Cities Neighbors : tempSea) {
                                        System.out.println(Neighbors.getX() + "," + Neighbors.getY() + "," + ct.getX() + "," + ct.getY());
                                        gc.strokeLine(ct.getX(), ct.getY(), Neighbors.getX(), Neighbors.getY());
                                        travelAnimation(whitePieceView, ct.getX(), ct.getY());
                                        player5.citiesVisited.add(ct.getCityName());
                                        player5.citiesVisitedXCoord.add(ct.getX());
                                         player5.citiesVisitedYCoord.add(ct.getY());
                                         
                                        //player5.moves--;
                                        // System.out.println(player5.moves);

                                    }
                                    String data1 = ct.getCityName() + "," + (ct.getX()) + "," + (ct.getY());
                                    System.out.println(ct.getCityName());
                                    //player5.citiesVisited.add(ct.getCityName());

                                    //JOptionPane.showMessageDialog(null,data1);
                                    continue;
                                }
                                if (ct.getQuadrant() == 2 && quad == 2) {
                                    player5.movesLeft--;
                                     fixedMovesLabel.setText("Moves Left:"+player5.movesLeft);
                                     histArea.appendText("\nPlayer 5 moved to"+ct.getCityName());
                                    q = 2;
                                    for (Cities Neighbors : tempLand) {
                                        System.out.println(Neighbors.getX());
                                        gc.strokeLine(ct.getX(), ct.getY(), Neighbors.getX(), Neighbors.getY());
                                        player5.citiesVisited.add(ct.getCityName());
                                        player5.citiesVisitedXCoord.add(ct.getX());
                                          player5.citiesVisitedYCoord.add(ct.getY());
                                        travelAnimation(whitePieceView, ct.getX(), ct.getY());
                                        
                                       // b2.fire();
                                        System.out.println(player5.movesLeft);

                                    }
                                    for (Cities Neighbors : tempSea) {
                                        System.out.println(Neighbors.getX() + "," + Neighbors.getY() + "," + ct.getX() + "," + ct.getY());
                                        // if(Neighbors.getCityName()!=)
                                        gc.strokeLine(ct.getX(), ct.getY(), Neighbors.getX(), Neighbors.getY());
                                        travelAnimation(whitePieceView, ct.getX(), ct.getY());
                                        player5.citiesVisited.add(ct.getCityName());
                                        player5.citiesVisitedXCoord.add(ct.getX());
                                         player5.citiesVisitedYCoord.add(ct.getY());
                                         
                                        //player5.moves--;
                                        //System.out.println(player5.moves);
                                        // diceController();
                                    }
                                    String data2 = ct.getCityName() + "," + (ct.getX()) + "," + (ct.getY());
                                    System.out.println(ct.getCityName());
                                    //JOptionPane.showMessageDialog(null,data2);
                                    continue;
                                }
                                if (ct.getQuadrant() == 3 && quad == 3) {
                                    player5.movesLeft--;
                                     histArea.appendText("\nPlayer 5 moved to"+ct.getCityName());
                                     fixedMovesLabel.setText("Moves Left:"+player5.movesLeft);
                                    q = 0;
                                    for (Cities Neighbors : tempLand) {
                                        System.out.println(Neighbors.getX());
                                        gc.strokeLine(ct.getX(), ct.getY(), Neighbors.getX(), Neighbors.getY());
                                        player5.citiesVisited.add(ct.getCityName());
                                        player5.citiesVisitedXCoord.add(ct.getX());
                                            player5.citiesVisitedYCoord.add(ct.getY());
                                           
                                        travelAnimation(whitePieceView, ct.getX(), ct.getY());

                                      //  b3.fire();
                                        System.out.println(player5.movesLeft);

                                    }
                                    for (Cities Neighbors : tempSea) {

                                        System.out.println(Neighbors.getX() + "," + Neighbors.getY() + "," + ct.getX() + "," + ct.getY());
                                        // if(Neighbors.getCityName()!=)
                                        gc.strokeLine(ct.getX(), ct.getY(), Neighbors.getX(), Neighbors.getY());
                                        travelAnimation(whitePieceView, ct.getX(), ct.getY());
                                        player5.citiesVisited.add(ct.getCityName());
                                        player5.citiesVisitedXCoord.add(ct.getX());
                                            player5.citiesVisitedYCoord.add(ct.getY());
                                           
                                        //player5.moves--;
                                        System.out.println(player5.movesLeft);

                                    }
                                    String data3 = ct.getCityName() + "," + (ct.getX()) + "," + (ct.getY());
                                    System.out.println(ct.getCityName());
                                    // JOptionPane.showMessageDialog(null,data3);
                                    continue;
                                }
                                if (ct.getQuadrant() == 4 && quad == 4) {
                                    player5.movesLeft--;
                                    fixedMovesLabel.setText("Moves Left:"+player5.movesLeft);
                                     histArea.appendText("\nPlayer 5 moved to"+ct.getCityName());
                                    q = 0;
                                    for (Cities Neighbors : tempLand) {
                                        System.out.println(Neighbors.getX());
                                        gc.strokeLine(ct.getX(), ct.getY(), Neighbors.getX(), Neighbors.getY());
                                        travelAnimation(whitePieceView, ct.getX(), ct.getY());
                                        player5.citiesVisited.add(ct.getCityName());
                                        player5.citiesVisitedXCoord.add(ct.getX());
                                            player5.citiesVisitedYCoord.add(ct.getY());
                                        System.out.println(player5.movesLeft);
                                        // b4.fire();
                                    }
                                    for (Cities Neighbors : tempSea) {
                                        System.out.println(Neighbors.getX() + "," + Neighbors.getY() + "," + ct.getX() + "," + ct.getY());
                                        // if(Neighbors.getCityName()!=)
                                        gc.strokeLine(ct.getX(), ct.getY(), Neighbors.getX(), Neighbors.getY());
                                        travelAnimation(whitePieceView, ct.getX(), ct.getY());
                                        player5.citiesVisited.add(ct.getCityName());
                                        player5.citiesVisitedXCoord.add(ct.getX());
                                            player5.citiesVisitedYCoord.add(ct.getY());
                                           
                                        
                                        //player5.moves--;
                                        System.out.println(player5.movesLeft);

                                    }
                                    String data4 = ct.getCityName() + "," + (ct.getX()) + "," + (ct.getY());
                                    System.out.println(ct.getCityName());
                                    //JOptionPane.showMessageDialog(null,data4);
                                    continue;
                                }
                            }
                        }
                    }

                }

            }
        });
        canvasPane.setCenter(canvas5);
        pane.setCenter(canvasPane);
    }
    public void drawHomeFlags(){}
public void canvasHandler6() {
    canvas=canvas6;
     if(player6.turn!=1)
        {
           xhist6=player6.citiesVisitedXCoord.get(player6.citiesVisitedXCoord.size()-1);
       yhist6=player6.citiesVisitedYCoord.get(player6.citiesVisitedYCoord.size()-1);
        travelAnimation(yellowPieceView,xhist6,yhist6);
        }
         die.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                randomNum = rand.nextInt(6) + 1;
                Image dieimage = loadImage("die_" + randomNum + ".jpg");
                ImageView dieimageview = new ImageView(dieimage);
                die.setGraphic(dieimageview);
                die.setDisable(true);
                player6.movesLeft = randomNum;
                System.out.println(player6.movesLeft);
                fixedMovesLabel.setText("Moves Left:"+player6.movesLeft);
            }
        });
        gc=canvas6.getGraphicsContext2D();
        gc.drawImage(img1, 0, 0, 571, 700);
        b1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // TODO Auto-generated method stub
                gc.drawImage(img1, 0, 0, 571, 700);
                canvasPane.getChildren().clear();
                canvasPane.setCenter(canvas6);
                quad = 1;
               if(qp6==1)
                    {
                    drawHomeFlag6();
                    drawPiece6();
                    }
                
                
            }
        });
        b2.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                // TODO Auto-generated method stub
                gc.drawImage(img2, 0, 0, 571, 700);
                quad = 2;
                canvasPane.getChildren().clear();
                canvasPane.setCenter(canvas6);
                 if(qp6==2)
                    {
                    drawHomeFlag6();
                    drawPiece6();
                    }
               
            }
        });
        b3.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                // TODO Auto-generated method stub
                gc.drawImage(img3, 0, 0, 571, 700);
                canvasPane.getChildren().clear();
                canvasPane.setCenter(canvas6);
                quad = 3;
                if(qp6==3)
                    {
                    drawHomeFlag6();
                    drawPiece6();
                    }
                
               

            }
        });
        b4.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                // TODO Auto-generated method stub
                canvasPane.getChildren().clear();
                canvasPane.setCenter(canvas6);
                gc.drawImage(img4, 0, 0, 571, 700);
                quad = 4;
               if(qp6==4)
                    {
                    drawHomeFlag6();
                    drawPiece6();
                    }
                
                

            }
        });
        gc.setStroke(Color.RED);
        gc.setLineWidth(4);
        canvas6.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                int x = (int) e.getX();
                int y = (int) e.getY();
                mouseClicked++;
                Cities ct;
                System.out.println(x + " " + y);
                for (int i = 0; i < lis.size(); i++) {
                    ct = lis.get(i);
                   if(player6.movesLeft==0&&(Integer.parseInt(noOfPlayers)>5))
                    {
                        player6.turn++;
                        player1.turn++;
                        cardAnimation1();
                         turnLabel.setText("Player 1 Turn");
                        die.setDisable(false);
                        canvasHandler1();
                        break;
                    }
                    if (player6.movesLeft > 0&&(Integer.parseInt(noOfPlayers)>5)) {
                        if (x > (ct.getX() - 10) && x < (ct.getX() + 10)) {
                            if (y > (ct.getY() - 10) && y < (ct.getY() + 10)) {
                                List<Cities> tempLand = ct.fetchLandNeighbour();
                                List<Cities> tempSea = ct.fetchSeaNeighbour();
                                if (ct.getQuadrant() == 1 && quad == 1) {
                                    player6.movesLeft--;
                                    fixedMovesLabel.setText("Moves Left:"+player6.movesLeft);
                                    histArea.appendText("\nPlayer 6 moved to"+ct.getCityName());
                                    q = 1;
                                    for (Cities Neighbors : tempLand) {
                                        System.out.println(Neighbors.getX() + "," + Neighbors.getY() + "," + ct.getX() + "," + ct.getY());
                                        // if(Neighbors.getCityName()!=)
                                       // b1.fire();
                                        gc.strokeLine(ct.getX(), ct.getY(), Neighbors.getX(), Neighbors.getY());
                                        travelAnimation(yellowPieceView, ct.getX(), ct.getY());
                                        player6.citiesVisited.add(ct.getCityName());
                                        player6.citiesVisitedXCoord.add(ct.getX());
                                            player6.citiesVisitedYCoord.add(ct.getY());
                                            
                                        System.out.println(player6.movesLeft);
                                    }

                                    for (Cities Neighbors : tempSea) {
                                        System.out.println(Neighbors.getX() + "," + Neighbors.getY() + "," + ct.getX() + "," + ct.getY());
                                        gc.strokeLine(ct.getX(), ct.getY(), Neighbors.getX(), Neighbors.getY());
                                        travelAnimation(yellowPieceView, ct.getX(), ct.getY());
                                        player6.citiesVisited.add(ct.getCityName());
                                        player6.citiesVisitedXCoord.add(ct.getX());
                                            player6.citiesVisitedYCoord.add(ct.getY());
                                            
                                        //player6.moves--;
                                        // System.out.println(player6.moves);

                                    }
                                    String data1 = ct.getCityName() + "," + (ct.getX()) + "," + (ct.getY());
                                    System.out.println(ct.getCityName());
                                    //player6.citiesVisited.add(ct.getCityName());

                                    //JOptionPane.showMessageDialog(null,data1);
                                    continue;
                                }
                                if (ct.getQuadrant() == 2 && quad == 2) {
                                    player6.movesLeft--;
                                    fixedMovesLabel.setText("Moves Left:"+player6.movesLeft);
                                    histArea.appendText("\nPlayer 6 moved to"+ct.getCityName());
                                    q = 2;
                                    for (Cities Neighbors : tempLand) {
                                        System.out.println(Neighbors.getX());
                                        gc.strokeLine(ct.getX(), ct.getY(), Neighbors.getX(), Neighbors.getY());

                                        travelAnimation(yellowPieceView, ct.getX(), ct.getY());
                                        player6.citiesVisited.add(ct.getCityName());
                                        player6.citiesVisitedXCoord.add(ct.getX());
                                            player6.citiesVisitedYCoord.add(ct.getY());
                                            
                                       // b2.fire();
                                        System.out.println(player6.movesLeft);

                                    }
                                    for (Cities Neighbors : tempSea) {
                                        System.out.println(Neighbors.getX() + "," + Neighbors.getY() + "," + ct.getX() + "," + ct.getY());
                                        // if(Neighbors.getCityName()!=)
                                        gc.strokeLine(ct.getX(), ct.getY(), Neighbors.getX(), Neighbors.getY());
                                        travelAnimation(yellowPieceView, ct.getX(), ct.getY());
                                        player6.citiesVisited.add(ct.getCityName());
                                        player6.citiesVisitedXCoord.add(ct.getX());
                                            player6.citiesVisitedYCoord.add(ct.getY());
                                            
                                        //player6.moves--;
                                        //System.out.println(player6.moves);
                                        // diceController();
                                    }
                                    String data2 = ct.getCityName() + "," + (ct.getX()) + "," + (ct.getY());
                                    System.out.println(ct.getCityName());
                                    //JOptionPane.showMessageDialog(null,data2);
                                    continue;
                                }
                                if (ct.getQuadrant() == 3 && quad == 3) {
                                    player6.movesLeft--;
                                    fixedMovesLabel.setText("Moves Left:"+player6.movesLeft);
                                    histArea.appendText("\nPlayer 6 moved to"+ct.getCityName());
                                    q = 0;
                                    for (Cities Neighbors : tempLand) {
                                        System.out.println(Neighbors.getX());
                                        gc.strokeLine(ct.getX(), ct.getY(), Neighbors.getX(), Neighbors.getY());
                                        player6.citiesVisited.add(ct.getCityName());
                                        player6.citiesVisitedXCoord.add(ct.getX());
                                            player6.citiesVisitedYCoord.add(ct.getY());
                                           
                                        travelAnimation(yellowPieceView, ct.getX(), ct.getY());

                                      //  b3.fire();
                                        System.out.println(player6.movesLeft);

                                    }
                                    for (Cities Neighbors : tempSea) {

                                        System.out.println(Neighbors.getX() + "," + Neighbors.getY() + "," + ct.getX() + "," + ct.getY());
                                        // if(Neighbors.getCityName()!=)
                                        gc.strokeLine(ct.getX(), ct.getY(), Neighbors.getX(), Neighbors.getY());
                                        travelAnimation(yellowPieceView, ct.getX(), ct.getY());
                                        player6.citiesVisited.add(ct.getCityName());
                                        player6.citiesVisitedXCoord.add(ct.getX());
                                            player6.citiesVisitedYCoord.add(ct.getY());
                                           
                                        //player6.moves--;
                                        System.out.println(player6.movesLeft);

                                    }
                                    String data3 = ct.getCityName() + "," + (ct.getX()) + "," + (ct.getY());
                                    System.out.println(ct.getCityName());
                                    // JOptionPane.showMessageDialog(null,data3);
                                    continue;
                                }
                                if (ct.getQuadrant() == 4 && quad == 4) {
                                    player6.movesLeft--;
                                    fixedMovesLabel.setText("Moves Left:"+player6.movesLeft);
                                    histArea.appendText("\nPlayer 6 moved to"+ct.getCityName());
                                    q = 0;
                                    for (Cities Neighbors : tempLand) {
                                        System.out.println(Neighbors.getX());
                                        gc.strokeLine(ct.getX(), ct.getY(), Neighbors.getX(), Neighbors.getY());

                                        travelAnimation(yellowPieceView, ct.getX(), ct.getY());
                                        player6.citiesVisited.add(ct.getCityName());
                                        player6.citiesVisitedXCoord.add(ct.getX());
                                            player6.citiesVisitedYCoord.add(ct.getY());
                                            
                                        System.out.println(player6.movesLeft);
                                        // b4.fire();
                                    }
                                    for (Cities Neighbors : tempSea) {
                                        System.out.println(Neighbors.getX() + "," + Neighbors.getY() + "," + ct.getX() + "," + ct.getY());
                                        // if(Neighbors.getCityName()!=)
                                        gc.strokeLine(ct.getX(), ct.getY(), Neighbors.getX(), Neighbors.getY());
                                        travelAnimation(yellowPieceView, ct.getX(), ct.getY());
                                        player6.citiesVisited.add(ct.getCityName());
                                        player6.citiesVisitedXCoord.add(ct.getX());
                                            player6.citiesVisitedYCoord.add(ct.getY());
                                            
                                        //player6.moves--;
                                        System.out.println(player6.movesLeft);

                                    }
                                    String data4 = ct.getCityName() + "," + (ct.getX()) + "," + (ct.getY());
                                    System.out.println(ct.getCityName());
                                    //JOptionPane.showMessageDialog(null,data4);
                                    
                                }
                            }
                        }
                    }

                }

            }
        });
        canvasPane.setCenter(canvas6);
        pane.setCenter(canvasPane);
    }
    
//    public void displayCards()
//    {
//        pane.setLeft(sideBar);
//        
//    }

    public void inithistPane() {
        
        histPane = new ScrollPane();
       
        histArea.setEditable(false);
        histPane.setContent(histArea);

    }

    public void initabtPane() {
        abtPane = new StackPane();
        TextArea ta3 = new TextArea();
        ta3.setText("Jouney Through Europe");
        ta3.setEditable(false);
        ta3.setStyle("-fx-background-color:transparent;");
        TextArea ta4 = new TextArea();
        ta4.setText("Journey through Europe is a family board game published by Ravensburger\n"
                + "The board is a map of Europe with various major cities marked, for example, Athens, Amsterdam and London. \n"
                + "The players are given a home city from which they will begin and are then dealt a number of cards with various other cities on them. \n"
                + "\n"
                + "They must plan a route between each of the cities in their hand of cards.\n"
                + "On each turn they throw a die and move between the cities, \n"
                + "The winner is the first player to visit each of their cities and then return to their home base.\n");
                
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

        abtPane.getChildren().addAll(ta3, ta4, bckButton);
        mainPane.setCenter(abtPane);

    }

    /**
     * This function initializes all the controls that go in the north toolbar.
     */
    private void initNorthToolbar() {

        northToolbar = new HBox();
        northToolbar.setStyle("-fx-background-color:lightgray");
        northToolbar.setAlignment(Pos.CENTER);
        northToolbar.setPadding(marginlessInsets);
        northToolbar.setSpacing(10.0);
        northToolbar.setStyle("-fx-background-color: #D1B48C;");
        Label lb = new Label("Number of Players");
        northToolbar.getChildren().add(lb);
        ComboBox cb = new ComboBox();
        cb.getItems().addAll("1", "2", "3", "4", "5", "6");
        northToolbar.getChildren().add(cb);
        cb.setOnAction((event) -> {
            Object playerno = cb.getSelectionModel().getSelectedItem();
            noOfPlayers = playerno.toString();

            player1.setNumber(Integer.parseInt(noOfPlayers));
            player2.setNumber(Integer.parseInt(noOfPlayers));
            player3.setNumber(Integer.parseInt(noOfPlayers));
            player4.setNumber(Integer.parseInt(noOfPlayers));
            player5.setNumber(Integer.parseInt(noOfPlayers));
            player6.setNumber(Integer.parseInt(noOfPlayers));
            System.out.println("you selected:  " + noOfPlayers);
            GridPane grid = new GridPane();
            grid.setHgap(20);
            grid.setVgap(20);
            grid.setPadding(new Insets(0, 10, 0, 10));
            grid.setGridLinesVisible(true);
            grid.setStyle("-fx-background-color: #D1B48C;");

            Image image1 = loadImage("flag_black.png");
            ImageView iv1 = new ImageView();
            iv1.setImage(image1);

            Image image2 = loadImage("flag_blue.png");
            ImageView iv2 = new ImageView();
            iv2.setImage(image2);

            Image image3 = loadImage("flag_green.png");
            ImageView iv3 = new ImageView();
            iv3.setImage(image3);

            Image image4 = loadImage("flag_red.png");
            ImageView iv4 = new ImageView();
            iv4.setImage(image4);

            Image image5 = loadImage("flag_white.png");
            ImageView iv5 = new ImageView();
            iv5.setImage(image5);

            Image image6 = loadImage("flag_yellow.png");
            ImageView iv6 = new ImageView();
            iv6.setImage(image6);

            lab:
            {
                HBox h1 = new HBox();
                HBox h2 = new HBox();
                VBox v1 = new VBox();
                //HBox h15=new HBox();
                // h15.getChildren().add(iv1);
                final ToggleGroup group1 = new ToggleGroup();
                RadioButton rb1 = new RadioButton();
                rb1.setToggleGroup(group1);
                rb1.setText("Player   ");
                rb1.setSelected(true);
                RadioButton rb2 = new RadioButton();
                rb2.setToggleGroup(group1);
                rb2.setText("Computer");
                if (rb1.isSelected() == true) {
                    player1.setPlayerType(1);
                } else {
                    player1.setPlayerType(0);
                }
                
                Label l1 = new Label("Name:");

                h1.getChildren().addAll(rb1, l1);
                h1.setSpacing(30);
                h2.getChildren().addAll(rb2, tf1);
                h2.setSpacing(20);

                v1.getChildren().addAll(h1, h2, iv1);
                grid.add(v1, 1, 1);
                v1.setSpacing(10);
                if (noOfPlayers.equals("1")) {
                    break lab;
                }

                HBox h3 = new HBox();
                HBox h4 = new HBox();
                VBox v2 = new VBox();
                final ToggleGroup group2 = new ToggleGroup();
                RadioButton rb3 = new RadioButton();
                rb3.setToggleGroup(group2);
                rb3.setText("Player   ");
                rb3.setSelected(true);
                RadioButton rb4 = new RadioButton();
                rb4.setToggleGroup(group2);
                rb4.setText("Computer");
                if (rb3.isSelected() == true) {
                    player2.setPlayerType(1);
                } else {
                    player2.setPlayerType(0);
                }

                Label l2 = new Label("Name:");

                h3.getChildren().addAll(rb3, l2);
                h3.setSpacing(30);
                h4.getChildren().addAll(rb4, tf2);
                h4.setSpacing(20);

                v2.getChildren().addAll(h3, h4, iv2);
                grid.add(v2, 2, 1);
                v2.setSpacing(10);
                if (noOfPlayers.equals("2")) {
                    break lab;
                }

                HBox h5 = new HBox();
                HBox h6 = new HBox();
                VBox v3 = new VBox();
                
                final ToggleGroup group3 = new ToggleGroup();
                RadioButton rb5 = new RadioButton();
                rb5.setToggleGroup(group3);
                rb5.setText("Player   ");
                rb5.setSelected(true);
                RadioButton rb6 = new RadioButton();
                rb6.setToggleGroup(group3);
                rb6.setText("Computer");
                Label l3 = new Label("Name:");

                h5.getChildren().addAll(rb5, l3);
                h5.setSpacing(30);
                h6.getChildren().addAll(rb6, tf3);
                h6.setSpacing(20);
                if (rb5.isSelected() == true) {
                    player3.setPlayerType(1);
                } else {
                    player3.setPlayerType(0);
                }
                //v1.getChildren().add
                v3.getChildren().addAll(h5, h6, iv3);
                grid.add(v3, 3, 1);
                v3.setSpacing(10);
                if (noOfPlayers.equals("3")) {
                    break lab;
                }

                HBox h7 = new HBox();
                HBox h8 = new HBox();
                VBox v4 = new VBox();
                final ToggleGroup group4 = new ToggleGroup();
                RadioButton rb7 = new RadioButton();
                rb7.setToggleGroup(group4);
                rb7.setText("Player   ");
                rb7.setSelected(true);
                RadioButton rb8 = new RadioButton();
                rb8.setToggleGroup(group4);
                rb8.setText("Computer");
                Label l4 = new Label("Name:");

                if (rb7.isSelected() == true) {
                    player4.setPlayerType(1);
                } else {
                    player4.setPlayerType(0);
                }
                h7.getChildren().addAll(rb7, l4);
                h7.setSpacing(30);
                h8.getChildren().addAll(rb8, tf4);
                h8.setSpacing(20);
                //v1.getChildren().add
                v4.getChildren().addAll(h7, h8, iv4);
                grid.add(v4, 1, 2);
                v4.setSpacing(10);
                if (noOfPlayers.equals("4")) {
                    break lab;
                }
                final ToggleGroup group5 = new ToggleGroup();
                HBox h9 = new HBox();
                HBox h10 = new HBox();
                VBox v5 = new VBox();
                RadioButton rb9 = new RadioButton();
                rb9.setText("Player   ");
                rb9.setSelected(true);
                rb9.setToggleGroup(group5);
                RadioButton rb10 = new RadioButton();
                rb10.setToggleGroup(group5);
                rb10.setText("Computer");
                Label l5 = new Label("Name:");

                h9.getChildren().addAll(rb9, l5);
                h9.setSpacing(30);
                h10.getChildren().addAll(rb10, tf5);
                h10.setSpacing(20);
                if (rb9.isSelected() == true) {
                    player5.setPlayerType(1);
                } else {
                    player5.setPlayerType(0);
                }
                //v1.getChildren().add
                v5.getChildren().addAll(h9, h10, iv5);
                grid.add(v5, 2, 2);
                v5.setSpacing(10);
                if (noOfPlayers.equals("5")) {
                    break lab;
                }
                final ToggleGroup group6 = new ToggleGroup();
                HBox h11 = new HBox();
                HBox h12 = new HBox();
                VBox v6 = new VBox();
                RadioButton rb11 = new RadioButton();
                rb11.setText("Player   ");
                rb11.setSelected(true);
                rb11.setToggleGroup(group6);
                RadioButton rb12 = new RadioButton();
                rb12.setToggleGroup(group6);
                rb12.setText("Computer");
                Label l6 = new Label("Name:");

                h11.getChildren().addAll(rb11, l6);
                h11.setSpacing(30);
                h12.getChildren().addAll(rb12, tf6);
                h12.setSpacing(20);
                //v1.getChildren().add
                if (rb11.isSelected() == true) {
                    player6.setPlayerType(1);
                } else {
                    player6.setPlayerType(0);
                }
                v6.getChildren().addAll(h11, h12, iv6);
                grid.add(v6, 3, 2);
                v6.setSpacing(10);
                if (noOfPlayers.equals("6")) {
                    break lab;
                }

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

                initPlayerControl();
                player1.setName(tf1.getText());
                System.out.println(player1.getName() + player1.getPlayerType());
                player2.setName(tf2.getText());
                System.out.println(player2.getName() + player2.getPlayerType());
                player3.setName(tf3.getText());
                System.out.println(player3.getName() + player3.getPlayerType());
                player4.setName(tf4.getText());
                System.out.println(player4.getName() + player4.getPlayerType());
                player5.setName(tf5.getText());
                System.out.println(player5.getName() + player5.getPlayerType());
                player6.setName(tf6.getText());
                System.out.println(player6.getName() + player6.getPlayerType());
            }

        });

        // AND NOW PUT THE NORTH TOOLBAR IN THE FRAME
        mainPane.setTop(northToolbar);
        //mainPane.getChildren().add(northToolbar);

    }

    public void makeGridPane() {

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
