package JTE.file;
import JTE.ui.JTECards;
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
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

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
  
    private JTEUI uiobj;
    
    public JTEFileLoader(JTEUI sik) {
        
        uiobj=sik;
    }
    public JTEFileLoader() {
        
        
    } 
    List<Cities> list = new ArrayList<Cities>();
   public void XMLParser()
    {
        try {
                DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                DocumentBuilder db = dbf.newDocumentBuilder();
                Document doc = db.parse(new File("./data/cities.xml"));
                Node root = doc.getElementsByTagName("routes").item(0);
                NodeList cardlist = root.getChildNodes();
                for (int i = 0; i < cardlist.getLength(); i++) 
                {
                    Node cardNode = cardlist.item(i);
                    if (cardNode.getNodeType() == Node.ELEMENT_NODE) 
                    {
                        NodeList cardAttrs = cardNode.getChildNodes();
                        for (int j=0;j<cardAttrs.getLength();j++) 
                        {
                            if (cardAttrs.item(j).getNodeType() == Node.ELEMENT_NODE) 
                            {
                                Node theNode = cardAttrs.item(j);
                                switch (theNode.getNodeName()) 
                                {
                                  case "name":
                                    for (Cities curCity:list)
                                    {
                                        if (curCity.getCityName().equals(theNode.getTextContent()))
                                        {System.out.println(theNode.getTextContent());
                                           Node landNeighbours;
                                           for(int p = 0; p < cardAttrs.getLength(); p++) {
                                               if(cardAttrs.item(p).getNodeType() == Node.ELEMENT_NODE) {
                                                   if(cardAttrs.item(p).getNodeName().equals("land")) {
                                                       landNeighbours = cardAttrs.item(p);
                                                        NodeList landList = landNeighbours.getChildNodes();
                                                        for (int k = 0; k < landList.getLength(); k++) 
                                                        {
                                                            if (landList.item(k).getNodeType() == Node.ELEMENT_NODE) 
                                                            {
                                                                System.out.println("Land neighbour: "
												+ landList.item(k)
														.getTextContent());
                                                                Cities landCity = searchCity(landList.item(k).getTextContent());
                                                                
                                                                curCity.addLandNeighbours(landCity);
                                                              
                                                            }
                                                        }
                                                   }
                                                   if(cardAttrs.item(p).getNodeName().equals("sea")) {
                                                       NodeList seaList = cardAttrs.item(p).getChildNodes();
                                                        for (int k = 0; k < seaList.getLength(); k++) 
                                                        {
                                                            if (seaList.item(k).getNodeType() == Node.ELEMENT_NODE) 
                                                            {
                                                                Cities seaCity = searchCity(seaList.item(k).getTextContent());
                                                                curCity.addSeaNeighbours(seaCity);
                                                                //System.out.println("Sea:"+seaCity.getCityName());
                                                            }
                                                        }

                                                   }
                                               }
                                           }
                                            
                                            
                                        }
                                    }
                                    
                                break;
                                }
                            }
                        }
                    }
                }

        } catch (ParserConfigurationException | SAXException | IOException e) {
                e.printStackTrace();
        }
    }
    public Cities searchCity(String cityname)
    {
        for(Cities curCity : list)
        {
            if (curCity.getCityName().equals(cityname))
            {
                return curCity;
            }
        }
        
        return null;
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
    
    public  List<JTECards> returnGreenCards()
    {
        String greenpath = "./images/Cards/green/"; 
        String imagePathGreen = "file:images/Cards/green/"; 
        File greenfile = new File(greenpath); 
        List<JTECards> greenList= new ArrayList<JTECards>();
        if (greenfile.isDirectory())
        {
            String[] cardName = greenfile.list(); 
            for (String card:cardName)
            {
                
                    Image i1=new Image(imagePathGreen + card);
                    System.out.println(imagePathGreen + card);
                    Image frontImage= new Image(imagePathGreen + card);                    
                    card = card.substring(0,card.length()- 4);
                    JTECards currentCard = new JTECards(frontImage);
                    greenList.add(currentCard);
                                 
            }
        }
        return greenList;
    }
     public  List<JTECards> returnRedCards()
    {        
        String redpath = "./images/Cards/red/"; 
        String imagePathRed = "file:images/Cards/red/";
        File redfile = new File(redpath);
        List<JTECards> redList= new ArrayList<JTECards>();
        
        if (redfile.isDirectory())
        {
            String[] cardName = redfile.list();
            
            for (String card : cardName)
            {
                System.out.println("hi r");
                //Image currentCardImage = new Image(greenpath + card);
                
                    System.out.println(imagePathRed + card);
                    Image FrontImage = new Image(imagePathRed+card);                    
                    card = card.substring(0,card.length() - 4);
                    JTECards currentCard = new JTECards(FrontImage);
                    redList.add(currentCard);
                   
            }
        }
         return redList;
    }
    
    
    public  List<JTECards> returnYellowCards()
    {
        String yellowpath = "./images/Cards/yellow/";
        String imagePathYellow = "file:images/Cards/yellow/";
        File yellowfile = new File(yellowpath);
        List<JTECards> yellowList= new ArrayList<JTECards>();
        
        if (yellowfile.isDirectory())
        {
            String[] cardName = yellowfile.list();
            
            for (String card : cardName)
            {
                //Image currentCardImage = new Image(greenCardspath + card);
             
                    System.out.println(imagePathYellow + card);
                    Image FrontImage = new Image(imagePathYellow + card);                    
                    card = card.substring(0, card.length()- 4);
                    JTECards currentCard = new JTECards(FrontImage);
                    yellowList.add(currentCard);
                                 
            }
        }
       
         return yellowList;
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
                 
                 
                // NOW WE NEED TO LOAD THE DATA FROM THE BYTE ARRAY
                 bis.readLine();
                while((temp=bis.readLine())!=null)
                {
                    
                    String [] st=temp.split("\t");
                   if(Integer.parseInt(st[2])==1)
                   {
                    if(st.length == 5) {
                        int newX = Integer.parseInt(st[3]);
                        int newY = Integer.parseInt(st[4]);
                        int p = (int)(((double)newX/2010.0) * 571);
                        int q = (int)(((double)newY/2569.0) * 700);
                        Cities c=new Cities(st[0],st[1],Integer.parseInt(st[2]),p,q);
                        list.add(c);
                    }
                   }
                   if(Integer.parseInt(st[2])==2)
                   {
                    if(st.length == 5) {
                        int newX = Integer.parseInt(st[3]);
                        int newY = Integer.parseInt(st[4]);

                        int p = (int)(((double)newX/1903.0) * 571);
                        int q = (int)(((double)newY/2585.0) * 700);

                       Cities c=new Cities(st[0],st[1],Integer.parseInt(st[2]),p,q);
                        list.add(c);
                    }
                   }
                   if(Integer.parseInt(st[2])==3)
                   {
                    if(st.length == 5) {
                        int newX = Integer.parseInt(st[3]);
                        int newY = Integer.parseInt(st[4]);

                        int p = (int)(((double)newX/1985.0) * 571);
                        int q = (int)(((double)newY/2583.0) * 700);

                       Cities c=new Cities(st[0],st[1],Integer.parseInt(st[2]),p,q);
                        list.add(c);
                    }
                   }
                   if(Integer.parseInt(st[2])==4)
                   {
                    if(st.length == 5) {
                        int newX = Integer.parseInt(st[3]);
                        int newY = Integer.parseInt(st[4]);

                        int p = (int)(((double)newX/1927.0) * 571);
                        int q = (int)(((double)newY/2561.0) * 700);

                       Cities c=new Cities(st[0],st[1],Integer.parseInt(st[2]),p,q);
                        list.add(c);
                    }
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
        
 
 
        

        

        
