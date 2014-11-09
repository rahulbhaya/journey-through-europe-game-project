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
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;

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
    private BorderPane aboutPanel;
    private ScrollPane aboutScrollPane;
    private JEditorPane aboutPane;
    private Button homeButton;
    private Pane workspace;

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
        initMainPane();
        initSplashScreen();
        initaboutPane();
        
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

    public JEditorPane getHelpPane() {
        return aboutPane;
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

    /**
     * This method initializes the language-specific game controls, which
     * includes the three primary game screens.
     */
    public void initJTEUI() {
        // FIRST REMOVE THE SPLASH SCREEN
        mainPane.getChildren().clear();

        // GET THE UPDATED TITLE
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        String title = props.getProperty(JTEPropertyType.GAME_TITLE_TEXT);
        primaryStage.setTitle(title);

        // THEN ADD ALL THE STUFF WE MIGHT NOW USE
        initNorthToolbar();

        // OUR WORKSPACE WILL STORE EITHER THE GAME, STATS,
        // OR HELP UI AT ANY ONE TIME
        //initWorkspace();
        initGameScreen();
       // initStatsPane();
        initaboutPane();
        initHandlers();

        // WE'LL START OUT WITH THE GAME SCREEN
        changeWorkspace(JTEUIState.PLAY_GAME_STATE);

    }
  
   
    public void initHandlers(){
        mainPane.setOnKeyPressed(new EventHandler<KeyEvent>(){ 
            @Override
            public void handle(KeyEvent event) {
                     
            }
        });
    }
    
    public void doCommand(String command){
        
        initJTEUI();
       if(command.equals("Quit"))
            eventHandler.respondToExitRequest(primaryStage);
       if(command.equals("About"))
           eventHandler.respondToSwitchScreenRequest(JTEUIState.VIEW_ABOUT_STATE);
    }
    public JEditorPane getaboutPane() 
    { 
        return aboutPane; 
    }
    public void initaboutPane()
    {
        // WE'LL DISPLAY ALL about INFORMATION USING HTML
        aboutPane = new JEditorPane();
        aboutPane.setEditable(false);
        SwingNode swingNode = new SwingNode();
        swingNode.setContent(aboutPane);
        aboutScrollPane = new ScrollPane();
        aboutScrollPane.setContent(swingNode);
                        
        // NOW LOAD THE about HTML
        aboutPane.setContentType("text/html");
        
        // MAKE THE HELP BUTTON
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        String homeImgName = props.getProperty(JTEPropertyType.HOME_IMG_NAME);
        Image homeImg = loadImage(homeImgName);
        ImageView homeImgIcon = new ImageView(homeImg);
        homeButton = new Button();
       homeButton.setGraphic(homeImgIcon);
        //setTooltip(homeButton, JTEPropertyType.HOME_TOOLTIP);
        homeButton.setPadding(marginlessInsets);
        
        // WE'LL PUT THE HOME BUTTON IN A TOOLBAR IN THE NORTH OF THIS SCREEN,
        // UNDER THE NORTH TOOLBAR THAT'S SHARED BETWEEN THE THREE SCREENS
        Pane aboutToolbar = new Pane();
        aboutPanel = new BorderPane();
        //aboutPanel.setLayout(new BorderLayout());
        aboutPanel.setTop(aboutToolbar);
        
        aboutPanel.setCenter(aboutScrollPane);
        aboutToolbar.getChildren().add(homeButton);
        aboutToolbar.setStyle("-fx-background-color:white");
        
        // LOAD THE HELP PAGE
        loadPage(aboutPane, JTEPropertyType.ABOUT_FILE_NAME);
        
        // LET OUR HELP PAGE GO HOME VIA THE HOME BUTTON
        homeButton.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				eventHandler.respondToGoHomeRequest();
			}
        	
        });
        
        // LET OUR HELP SCREEN JOURNEY AROUND THE WEB VIA HYPERLINK
        //HelpHyperlinkListener hhl = new HelpHyperlinkListener(this);
        //aboutPane.addHyperlinkListener(hhl);         
        
        // ADD IT TO THE WORKSPACE
       // workspace.add(aboutPanel, JTEUIState.VIEW_HELP_STATE.toString());
       // workspace.getChildren().add(aboutPanel);
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

        // MAKE AND INIT THE STATS BUTTON
        statsButton = initToolbarButton(northToolbar,
                JTEPropertyType.STATS_IMG_NAME);
        //setTooltip(statsButton, JTEPropertyType.STATS_TOOLTIP);
        
        statsButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                // TODO Auto-generated method stub
                fl.gridRenderer.pressedundo();
                
            }

        });
        // MAKE AND INIT THE about BUTTON
        aboutButton = initToolbarButton(northToolbar,
                JTEPropertyType.HELP_IMG_NAME);
        //setTooltip(aboutButton, JTEPropertyType.HELP_TOOLTIP);
        aboutButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                // TODO Auto-generated method stub
               // eventHandler
                //        .respondToSwitchScreenRequest(JTEUIState.VIEW_HELP_STATE);
            }

        });

        // MAKE AND INIT THE EXIT BUTTON
        exitButton = initToolbarButton(northToolbar,
                JTEPropertyType.EXIT_IMG_NAME);
        //setTooltip(exitButton, JTEPropertyType.EXIT_TOOLTIP);
        exitButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                // TODO Auto-generated method stub
                eventHandler.respondToExitRequest(primaryStage);
            }

        });

        // AND NOW PUT THE NORTH TOOLBAR IN THE FRAME
        mainPane.setTop(northToolbar);
        //mainPane.getChildren().add(northToolbar);
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
                
                workspace.getChildren().remove(aboutPanel);
              // workspace.getChildren().remove(statsScrollPane);
                workspace.getChildren().add(gamePanel);
                workspace.getChildren().add(aboutPanel);
                gamePanel.setVisible(false);
                break;
            case PLAY_GAME_STATE:
                mainPane.setCenter(gamePanel);
                break;
            case VIEW_STATS_STATE:
                mainPane.setCenter(statsScrollPane);
                break;
            default:
        }

    }


}
