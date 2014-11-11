package JTE.ui;

import java.io.IOException;
import java.util.ArrayList;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import application.Main.JTEPropertyType;
import properties_manager.PropertiesManager;
import xml_utilities.InvalidXMLFileFormatException;
import JTE.file.JTEFileLoader;
import JTE.game.JTEGameStateManager;
import javax.swing.JEditorPane;

public class JTEEventHandler {

    private JTEUI ui;
//    JTEFileLoader fl=new JTEFileLoader();

    /**
     * Constructor that simply saves the ui for later.
     *
     * @param initUI
     */
    public JTEEventHandler(JTEUI initUI) {
        ui = initUI;
    }

    /**
     * This method responds to when the user wishes to switch between the Game,
     * Stats, and Help screens.
     *
     * @param uiState The ui state, or screen, that the user wishes to switch
     * to.
     */
    public void respondToSwitchScreenRequest(JTEUI.JTEUIState uiState) {

        ui.changeWorkspace(uiState);
    }
    /*This method responds to select levels as selected by the user
    *
    */
    public void respondToSelectCommandRequest(String comm)
    {
     
        ui.doCommand(comm);
    }
    /**
     * This method responds to when the user presses the new game method.
     */
    public void respondToNewGameRequest() {
        JTEGameStateManager gsm = ui.getGSM();
        gsm.startNewGame();
    }
     public void respondToGoHomeRequest()
    {
        //JEditorPane aboutPage = ui.getaboutPane();
        //ui.loadPage(aboutPage, JTEPropertyType.ABOUT_FILE_NAME);
    }
    /**
     * This method responds to when the user requests to exit the application.
     *
     * @param window The window that the user has requested to close.
     */
    public void respondToExitRequest(Stage primaryStage) {
        // ENGLIS IS THE DEFAULT
        String options[] = new String[]{"Yes", "No"};
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        options[0] = props.getProperty(JTEPropertyType.DEFAULT_YES_TEXT);
        options[1] = props.getProperty(JTEPropertyType.DEFAULT_NO_TEXT);
        String verifyExit = props.getProperty(JTEPropertyType.DEFAULT_EXIT_TEXT);

        // NOW WE'LL CHECK TO SEE IF LANGUAGE SPECIFIC VALUES HAVE BEEN SET
        if (props.getProperty(JTEPropertyType.YES_TEXT) != null) {
            options[0] = props.getProperty(JTEPropertyType.YES_TEXT);
            options[1] = props.getProperty(JTEPropertyType.NO_TEXT);
            verifyExit = props.getProperty(JTEPropertyType.EXIT_REQUEST_TEXT);
        }

        // FIRST MAKE SURE THE USER REALLY WANTS TO EXIT
        Stage dialogStage = new Stage();
        dialogStage.initModality(Modality.WINDOW_MODAL);
        dialogStage.initOwner(primaryStage);
        BorderPane exitPane = new BorderPane();
        HBox optionPane = new HBox();
        Button yesButton = new Button(options[0]);
        Button noButton = new Button(options[1]);
        optionPane.setSpacing(10.0);
        optionPane.getChildren().addAll(yesButton, noButton);
        Label exitLabel = new Label(verifyExit);
        exitPane.setCenter(exitLabel);
        exitPane.setBottom(optionPane);
        Scene scene = new Scene(exitPane, 50, 100);
        dialogStage.setScene(scene);
        dialogStage.show();
        // WHAT'S THE USER'S DECISION?
        yesButton.setOnAction(e -> {
            // YES, LET'S EXIT
            System.exit(0);
        });
        noButton.setOnAction(e -> {
            dialogStage.close();
        });

    }

}
