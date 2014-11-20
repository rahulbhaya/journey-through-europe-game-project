package JTE.file;

import JTE.ui.Cities;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import application.Main.JTEPropertyType;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import properties_manager.PropertiesManager;
import JTE.ui.JTEUI;
import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.media.AudioClip;
import java.util.Stack;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

public class JTEFileLoader {

    // INITIALIZATION CONSTANTS
    // THESE CONSTANTS ARE FOR CUSTOMIZATION OF THE GRID
    private final int INIT_GRID_DIM = 10;
    private final int MIN_GRID_DIM = 1;
    private final int MAX_GRID_ROWS = 10;
    private final int MAX_GRID_COLUMNS = 20;
    static String DATA_PATH = "./data/";

    // TEXTUAL CONSTANTS
    private final String APP_TITLE = "JTE Level Editor";
    private final String OPEN_BUTTON_TEXT = "Open level binary file";
    private final String SAVE_AS_BUTTON_TEXT = "Save level binary file";
    private final String UPDATE_GRID_BUTTON_TEXT = "Update Grid";
    private final String COLUMNS_LABEL_TEXT = "Columns: ";
    private final String ROWS_LABEL_TEXT = "Rows: ";
    private final String SOK_FILE_EXTENSION = ".sok";
    private final String OPEN_FILE_ERROR_FEEDBACK_TEXT = "File not loaded: .sok files only";
    private final String SAVE_AS_ERROR_FEEDBACK_TEXT = "File not saved: must use .sok file extension";
    private final String FILE_LOADING_SUCCESS_TEXT = " loaded successfully";
    private final String FILE_READING_ERROR_TEXT = "Error reading from ";
    private final String FILE_WRITING_ERROR_TEXT = "Error writing to ";

    private final String SOKOBAN_DATA_DIR = "../JTE_draft/data/";

    // CONSTANTS FOR FORMATTING THE GRID
    private final Font GRID_FONT = new Font("monospaced", 36);

    // INSTANCE VARIABLES
    // HERE ARE THE UI COMPONENTS
    Stage primaryStage;
    private BorderPane gamePane;
    private GridPane westPane;
    private Button openButton;
    private Button saveAsButton;
    private Label columnsLabel;
    private TextField columnsTextField;
    private Label rowsLabel;
    private TextField rowsTextField;
    private Button updateGridButton;

    // GRID Renderer
    
   

    // AND HERE IS THE GRID WE'RE MAKING
    public int gridColumns;
    public int gridRows;
    public int grid[][];
    public int tempgrid[][];
    private JTEUI uiobj;

    public JTEFileLoader(JTEUI sik) {
        
        uiobj=sik;
    }
    public static String loadTextFile(  String textFile) throws IOException
   {
       // ADD THE PATH TO THE FILE
       PropertiesManager props = PropertiesManager.getPropertiesManager();
       textFile = props.getProperty(JTEPropertyType.DATA_PATH) + textFile;
       
       // WE'LL ADD ALL THE CONTENTS OF THE TEXT FILE TO THIS STRING
       String textToReturn = "";
      
       // OPEN A STREAM TO READ THE TEXT FILE
       FileReader fr = new FileReader(textFile);
       BufferedReader reader = new BufferedReader(fr);
           
       // READ THE FILE, ONE LINE OF TEXT AT A TIME
       String inputLine = reader.readLine();
       while (inputLine != null)
       {
           // APPEND EACH LINE TO THE STRING
           textToReturn += inputLine + "\n";
           
           // READ THE NEXT LINE
           inputLine = reader.readLine();        
       }
       
       // RETURN THE TEXT
       return textToReturn;
   } 
    public void initGUI() {
        
    }
    public List<Cities> retCities()
    {
   String root = DATA_PATH;
        File fileToOpen = new File(root+"cities.csv");
         try {
            if (fileToOpen != null) {
                
                FileReader fis = new FileReader(fileToOpen);
                BufferedReader bis = new BufferedReader(fis);

                // HERE IT IS, THE ONLY READY REQUEST WE NEED
               
                String temp="";
                 List<Cities> list = new ArrayList<Cities>();
                 
                // NOW WE NEED TO LOAD THE DATA FROM THE BYTE ARRAY
                 bis.readLine();
                while((temp=bis.readLine())!=null)
                {
                    
                    String [] st=temp.split("\t");
                    
                    if(st.length == 5) {
                        int newX = Integer.parseInt(st[3]);
                        int newY = Integer.parseInt(st[4]);

                        int p = (int)(((double)newX/2010.0) * 650);
                        int q = (int)(((double)newY/2569.0) * 500);

                        Cities c=new Cities(st[0],st[1],Integer.parseInt(st[2]),p,q);
                        list.add(c);
                    }
                    
                }
                return list;
              
              
            }
        } catch (Exception e) {
            System.out.println("in fileOpen file path: " + fileToOpen.getAbsolutePath());
            e.printStackTrace();
         
        }
         return null;
    }
    
} 
        
 
 
        

        

        
