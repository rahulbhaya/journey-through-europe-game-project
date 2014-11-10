package JTE.ui;

import application.Main;
import application.Main.JTEPropertyType;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

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
import javafx.event.EventType;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;


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


public class JTEUI extends Pane {

    /**
     * The JTEUIState represents the four screen states that are possible
     * for the JTE game application. Depending on which state is in current
     * use, different controls will be visible.
     */
    
    public enum JTEUIState {

        SPLASH_SCREEN_STATE, PLAY_GAME_STATE, VIEW_STATS_STATE, VIEW_ABOUT_STATE,
        HANG1_STATE, HANG2_STATE, HANG3_STATE, HANG4_STATE, HANG5_STATE, HANG6_STATE,
    }
String number="0";
    // mainStage
    private Stage primaryStage;

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

    private Button goButton;
    // Padding
    private Insets marginlessInsets;

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
    
    JTEEventHandler eh;
    JTEGameStateManager gsm;

    public JTEUI() {
        gsm = new JTEGameStateManager(this);
        eventHandler = new JTEEventHandler(this);
        errorHandler = new JTEErrorHandler(primaryStage);
        docManager = new JTEDocumentManager(this);
        fl=new JTEFileLoader(this);
        System.out.println("1");
        initMainPane();
        initSplashScreen();
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
        aboutPane.getChildren().addAll(ta1,ta2);
      
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
    public Image loadImage(String imageName) {
		Image img = new Image(ImgPath + imageName);
		// System.out.print(imageName);
		return img;
	}
        private void initGameScreen() {
		mainPane.getChildren().clear();
                GridPane grd = new GridPane();
                grd.setHgap(10);
                grd.setVgap(10);
                 grd.setPadding(new Insets(0, 10, 0, 10));
                 Label l=new Label("Player");
            Image centerImage = loadImage("gameimage.jpg");
            ImageView centerImageView = new ImageView(centerImage);
            centerImageView.setFitHeight(80.0);
            centerImageView.setFitWidth(80.0);
            grd.add(centerImageView,1,1);
                   mainPane.setCenter(grd);
                
                
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

        // MAKE AND INIT THE GAME BUTTON
        gameButton = initToolbarButton(northToolbar,
                JTEPropertyType.GAME_IMG_NAME);
        //setTooltip(gameButton, JTEPropertyType.GAME_TOOLTIP);
        gameButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                // TODO Auto-generated method stub
               mainPane.getChildren().clear();
               gamePanel.getChildren().clear();
               initSplashScreen();
               
                
            }
        });
        
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
      grid.setHgap(10);
    grid.setVgap(10);
    grid.setPadding(new Insets(0, 10, 0, 10));
    grid.setGridLinesVisible(true);
   // Image image1 = new Image("./images/flag_black.png");
   //ImageView iv1 = new ImageView();
    // iv1.setImage(image1);
    lab:
    {
    HBox h1=new HBox();
    HBox h2=new HBox();
    VBox v1=new VBox();
    RadioButton rb1=new RadioButton();
    rb1.setText("Player");
    rb1.setSelected(true);
    RadioButton rb2=new RadioButton();
    rb2.setText("Computer");
    Label l1=new Label("Name:");
    TextField tf1=new TextField();
    h1.getChildren().addAll(rb1,l1);
    h1.setSpacing(30);
    h2.getChildren().addAll(rb2,tf1);
    h2.setSpacing(20);
    v1.getChildren().addAll(h1,h2);
    grid.add(v1,1,1);
    v1.setSpacing(10);
    if(number.equals("1"))break lab;
    
    HBox h3=new HBox();
    HBox h4=new HBox();
    VBox v2=new VBox();
    RadioButton rb3=new RadioButton();
    rb3.setText("Player");
    rb3.setSelected(true);
    RadioButton rb4=new RadioButton();
    rb4.setText("Computer");
    Label l2=new Label("Name:");
    TextField tf2=new TextField();
    h3.getChildren().addAll(rb3,l2);
    h3.setSpacing(30);
    h4.getChildren().addAll(rb4,tf2);
    h4.setSpacing(20);
 
    v2.getChildren().addAll(h3,h4);
    grid.add(v2,2,1);
    v2.setSpacing(10);
    if(number.equals("2"))break lab;
    
    HBox h5=new HBox();
    HBox h6=new HBox();
    VBox v3=new VBox();
    RadioButton rb5=new RadioButton();
    rb5.setText("Player");
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
    v3.getChildren().addAll(h5,h6);
    grid.add(v3,3,1);
    v3.setSpacing(10);
    if(number.equals("3"))break lab;
    
    HBox h7=new HBox();
    HBox h8=new HBox();
    VBox v4=new VBox();
    RadioButton rb7=new RadioButton();
    rb7.setText("Player");
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
    v4.getChildren().addAll(h7,h8);
    grid.add(v4,1,2);
    v4.setSpacing(10);
    if(number.equals("4"))break lab;
    
    HBox h9=new HBox();
    HBox h10=new HBox();
    VBox v5=new VBox();
    RadioButton rb9=new RadioButton();
    rb9.setText("Player");
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
    v5.getChildren().addAll(h9,h10);
    grid.add(v5,2,2);
    v5.setSpacing(10);
   if(number.equals("5"))break lab;
    
    
    HBox h11=new HBox();
    HBox h12=new HBox();
    VBox v6=new VBox();
    RadioButton rb11=new RadioButton();
    rb11.setText("Player");
    rb11.setSelected(true);
    RadioButton rb12=new RadioButton();
    rb12.setText("Computer");
    Label l6=new Label("Name:");
    TextField tf6=new TextField();
    h11.getChildren().addAll(rb11,l6);
    h11.setSpacing(30);
    h12.getChildren().addAll(rb8,tf6);
    h12.setSpacing(20);
    //v1.getChildren().add
    v6.getChildren().addAll(h11,h12);
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
