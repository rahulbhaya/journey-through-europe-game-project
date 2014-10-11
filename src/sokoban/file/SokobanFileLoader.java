package sokoban.file;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import application.Main.SokobanPropertyType;
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
import sokoban.ui.SokobanUI;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.media.AudioClip;

import javafx.stage.Stage;
import javax.swing.JOptionPane;

public class SokobanFileLoader {

    // INITIALIZATION CONSTANTS
    // THESE CONSTANTS ARE FOR CUSTOMIZATION OF THE GRID
    private final int INIT_GRID_DIM = 10;
    private final int MIN_GRID_DIM = 1;
    private final int MAX_GRID_ROWS = 10;
    private final int MAX_GRID_COLUMNS = 20;
    static String DATA_PATH = "./data/";

    // TEXTUAL CONSTANTS
    private final String APP_TITLE = "Sokoban Level Editor";
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

    private final String SOKOBAN_DATA_DIR = "../Sokoban_draft/data/";

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
    public GridRenderer gridRenderer;
    private GraphicsContext gc;

    // AND HERE IS THE GRID WE'RE MAKING
    public int gridColumns;
    public int gridRows;
    public int grid[][];
    public int tempgrid[][];
    
    public SokobanFileLoader(){
        gridRenderer=new GridRenderer();
    }

    

    public void initGUI() {
        // THE PARENT PANE IS A BORDERPANE
//        gamePane = new BorderPane();
        // THIS GUY RENDERS OUR GRID
        gridRenderer = new GridRenderer();
        // PUT EVERYTHING IN THE FRAME
//        gamePane.setRight(gridRenderer);
//        Scene scene = new Scene(gamePane);
//        primaryStage.setTitle(APP_TITLE);
//        primaryStage.setScene(scene);
//        primaryStage.show();
    }

    public void openfile(String level) {

        String root = DATA_PATH;
        File fileToOpen = new File(root + level + ".sok");

        try {
            if (fileToOpen != null) {
               // LET'S USE A FAST LOADING TECHNIQUE. WE'LL LOAD ALL OF THE
                // BYTES AT ONCE INTO A BYTE ARRAY, AND THEN PICK THAT APART.
                // THIS IS FAST BECAUSE IT ONLY HAS TO DO FILE READING ONCE

                byte[] bytes = new byte[Long.valueOf(fileToOpen.length()).intValue()];
                ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
                FileInputStream fis = new FileInputStream(fileToOpen);
                BufferedInputStream bis = new BufferedInputStream(fis);

                // HERE IT IS, THE ONLY READY REQUEST WE NEED
                bis.read(bytes);
                bis.close();

                // NOW WE NEED TO LOAD THE DATA FROM THE BYTE ARRAY
                DataInputStream dis = new DataInputStream(bais);

               // NOTE THAT WE NEED TO LOAD THE DATA IN THE SAME
                // ORDER AND FORMAT AS WE SAVED IT
                // FIRST READ THE GRID DIMENSIONS
                int initGridColumns = dis.readInt();
                int initGridRows = dis.readInt();
                int[][] newGrid = new int[initGridColumns][initGridRows];
                int[][] newtempgrid = new int[initGridColumns][initGridRows];

                // AND NOW ALL THE CELL VALUES
                for (int i = 0; i < initGridColumns; i++) {
                    for (int j = 0; j < initGridRows; j++) {
                        newGrid[i][j] = dis.readInt();
                    }
                }

                for (int i = 0; i < initGridColumns; i++) {
                    for (int j = 0; j < initGridRows; j++) {
                        if (newGrid[i][j] == 3) {
                            newtempgrid[i][j] = 3;
                        }
                    }
                }
                tempgrid = newtempgrid;
                grid = newGrid;
                gridColumns = initGridColumns;
                gridRows = initGridRows;
                //gridRenderer.repaint();
            }
        } catch (Exception e) {
            System.out.println("in fileOpen file path: " + fileToOpen.getAbsolutePath());
            e.printStackTrace();
        }

    }

    public class GridRenderer extends Canvas {

        private GraphicsContext gc;

        // PIXEL DIMENSIONS OF EACH CELL
        int cellWidth;
        int cellHeight;

        // images
        Image wallImage = new Image("file:images/wall.png");
        Image boxImage = new Image("file:images/box.png");
        Image placeImage = new Image("file:images/place.png");
        Image sokobanImage = new Image("file:images/Sokoban.png");

        //Defalt const
        public GridRenderer() {
            this.setWidth(500);
            this.setHeight(500);
            repaint();
        }
        public void win()
        {
            int count=0;
            for(int p=0;p<gridColumns;p++)
            {
                for(int q=0;q<gridRows;q++)
                {
                    if(grid[p][q]==3)
                    count++;
                }
                
            }
            if(count==0){JOptionPane.showMessageDialog(null, "Congrats,You won the Puzzle!", DATA_PATH, cellHeight);}
            
        }
        public void popLoseMessage()
        {
            JOptionPane.showMessageDialog(null, "SorryYou lost it", DATA_PATH, cellHeight);
            sui.mainPane.getChildren().clear();
            sui.gamePanel.getChildren().clear();
            sui.initSplashScreen();
        }
        public void lose()
        {
            for(int i=0;i<gridColumns;i++)
            {for(int j=0;j<gridRows;j++)
                {
                if(grid[i][j]==2&&tempgrid[i][j]!=3)
                {
                    if(grid[i][j-1]==1&&grid[i+1][j]==1)
                    {
                        popLoseMessage();
                    }
                    else if(grid[i+1][j]==1&&grid[i][j+1]==1)
                    {
                        popLoseMessage();
                    }
                    else if(grid[i][j+1]==1&&grid[i-1][j]==1)
                    {
                        popLoseMessage();
                    }
                    else if(grid[i-1][j]==1&&grid[i][j-1]==1)
                    {
                        popLoseMessage();
                    }
                }
                    
                
                }
                
            }
                
        }
        AudioClip hit = new AudioClip("http://www.soundjay.com/button/button-09.wav");

        public void goUp() { 
            System.out.println("Up Pressed");
            hit.play();
            l1:
            {
                for (int r1 = 0; r1 < grid.length; r1++) {
                    for (int c1 = 0; c1 < grid[r1].length; c1++) {
                        if (grid[r1][c1] == 4) {
                            if (grid[r1][c1 - 1] == 0) {
                                //if(grid[r1][c1-2]==0){grid[r1][c1-1]=4;grid[r1][c1]=0;break l1;}
                                grid[r1][c1 - 1] = 4;
                                grid[r1][c1] = 0;
                                 if (tempgrid[r1][c1] == 3) {
                                        grid[r1][c1] = 3;
                                    }
                                break l1;
                            } else if (grid[r1][c1 - 1] == 2) {
                                if (grid[r1][c1 - 2] == 0 || grid[r1][c1 - 2] == 3) {
                                    grid[r1][c1 - 2] = 2;
                                    grid[r1][c1 - 1] = 4;
                                    grid[r1][c1] = 0;
                                    if (tempgrid[r1][c1] == 3) {
                                        grid[r1][c1] = 3;
                                    }
                                    break l1;
                                }

                            } else if (grid[r1][c1 - 1] == 3) {
                                grid[r1][c1 - 1] = 4;
                                grid[r1][c1] = 0;
                                if (tempgrid[r1][c1] == 3) {
                                    grid[r1][c1] = 3;
                                }
                                break l1;
                            } else if (grid[r1][c1] == 1) {
                                break l1;
                            }
                        }
                    }
                }

            }
            win();
            lose();
            repaint();
        }

        public void goDown() {
            System.out.println("Down Pressed");
            hit.play();
            l2:
            {
                for (int r2 = 0; r2 < grid.length; r2++) {
                    for (int c2 = 0; c2 < grid[r2].length; c2++) {
                        if (grid[r2][c2] == 4) {
                            if (grid[r2][c2 + 1] == 0) {
                                //if(grid[r2][c2-2]==0){grid[r2][c2-1]=4;grid[r2][c2]=0;break l1;}
                                grid[r2][c2 + 1] = 4;
                                grid[r2][c2] = 0;
                                 if (tempgrid[r2][c2] == 3) {
                                        grid[r2][c2] = 3;
                                    }
                                break l2;
                            } else if (grid[r2][c2 + 1] == 2) {
                                if (grid[r2][c2 + 2] == 0 || grid[r2][c2 + 2] == 3) {
                                    grid[r2][c2 + 2] = 2;
                                    grid[r2][c2 +1] = 4;
                                    grid[r2][c2] = 0;
                                    if (tempgrid[r2][c2] == 3) {
                                        grid[r2][c2] = 3;
                                    }
                                    break l2;
                                }

                            } else if (grid[r2][c2 + 1] == 3) {
                                grid[r2][c2 + 1] = 4;
                                grid[r2][c2] = 0;
                                if (tempgrid[r2][c2] == 3) {
                                    grid[r2][c2] = 3;
                                }
                                break l2;
                            } else if (grid[r2][c2] == 1) {
                                break l2;
                            }
                        }
                    }
                }

            }
            win();
            lose();
            repaint();
            
        }
        public void goLeft() {
            System.out.println("Left Pressed");
            hit.play();
            l3:
            {
                for (int r3 = 0; r3 < grid.length; r3++) {
                    for (int c3 = 0; c3 < grid[r3].length; c3++) {
                        if (grid[r3][c3] == 4) {
                            if (grid[r3 - 1][c3] == 0) {
                                //if(grid[r3-2][c3]==0){grid[r3-1][c3]=4;grid[r3][c3]=0; break l3;}
                                grid[r3 - 1][c3] = 4;
                                grid[r3][c3] = 0;
                                 if (tempgrid[r3][c3] == 3) {
                                        grid[r3][c3] = 3;
                                    }
                                break l3;
                            } else if (grid[r3 - 1][c3] == 2) {
                                if (grid[r3 - 2][c3] == 0 || grid[r3 - 2][c3] == 3) {
                                    grid[r3 - 2][c3] = 2;
                                    grid[r3 - 1][c3] = 4;
                                    grid[r3][c3] = 0;
                                    if (tempgrid[r3][c3] == 3) {
                                        grid[r3][c3] = 3;
                                    }
                                    break l3;
                                }
                            } else if (grid[r3 - 1][c3] == 3) {

                                grid[r3 - 1][c3] = 4;
                                grid[r3][c3] = 0;
                                if (tempgrid[r3][c3] == 3) {
                                    grid[r3][c3] = 3;
                                }
                                break l3;
                            } else if (grid[r3][c3] == 1) {
                                break l3;
                            }

                        }
                    }

                }

            }
            
            win();
            lose();
            repaint();
        }

        public void goRight() {
            System.out.println("Right Pressed");
            hit.play();
            l4:
            {
                for (int r4 = 0; r4 < grid.length; r4++) {
                    for (int c4 = 0; c4 < grid[r4].length; c4++) {
                        if (grid[r4][c4] == 4) {
                            if (grid[r4 + 1][c4] == 0) {
                                /*if(grid[r4+2][c4]==0)
                                 {
                                 grid[r4+1][c4]=4;
                                 grid[r4][c4]=0;
                                 break l4;
                                 }
                                 else if(grid[r4+2][c4]==1)
                                 {
                                 grid[r4+1][c4]=4;
                                 grid[r4][c4]=0;
                                 break;
                                 }*/
                                grid[r4 + 1][c4] = 4;
                                grid[r4][c4] = 0;
                                if (tempgrid[r4][c4] == 3) {
                                    grid[r4][c4] = 3;
                                }

                                break l4;
                            } else if (grid[r4 + 1][c4] == 2) {
                                if (grid[r4 + 2][c4] == 0 || grid[r4 + 2][c4] == 3) {
                                    grid[r4 + 2][c4] = 2;
                                    grid[r4 + 1][c4] = 4;
                                    grid[r4][c4] = 0;
                                    if (tempgrid[r4][c4] == 3) {
                                        grid[r4][c4] = 3;
                                    }
                                    break l4;
                                }
                            } else if (grid[r4 + 1][c4] == 3) {
                                grid[r4 + 1][c4] = 4;
                                grid[r4][c4] = 0;
                                if (tempgrid[r4][c4] == 3) {
                                    grid[r4][c4] = 3;
                                }
                                break l4;
                            } else if (grid[r4][c4] == 1) {
                                break l4;
                            }
                        }
                    }
                }
            }
            win();
            lose();
            repaint();
        }

        public void repaint() {
            gc = this.getGraphicsContext2D();
            gc.clearRect(0, 0, this.getWidth(), this.getHeight());

            // CALCULATE THE GRID CELL DIMENSIONS
            double w = this.getWidth() / gridColumns;
            double h = this.getHeight() / gridRows;

            gc = this.getGraphicsContext2D();

            // NOW RENDER EACH CELL
            int x = 0, y = 0;
            for (int i = 0; i < gridColumns; i++) {
                y = 0;
                for (int j = 0; j < gridRows; j++) {
                    // DRAW THE CELL
                    gc.setFill(Color.LIGHTBLUE);
                    gc.strokeRoundRect(x, y, w, h, 10, 10);

                    switch (grid[i][j]) {
                        case 0:
                            gc.strokeRoundRect(x, y, w, h, 10, 10);
                            break;
                        case 1:
                            gc.drawImage(wallImage, x, y, w, h);
                            break;
                        case 2:
                            gc.drawImage(boxImage, x, y, w, h);
                            break;
                        case 3:
                            gc.drawImage(placeImage, x, y, w, h);
                            break;
                        case 4:
                            gc.drawImage(sokobanImage, x, y, w, h);
                            break;
                    }

                    // THEN RENDER THE TEXT
                    String numToDraw = "" + grid[i][j];
                    double xInc = (w / 2) - (10 / 2);
                    double yInc = (h / 2) + (10 / 4);
                    x += xInc;
                    y += yInc;
//                    gc.setFill(Color.RED);
//                    gc.fillText(numToDraw, x, y);
                    x -= xInc;
                    y -= yInc;

                    // ON TO THE NEXT ROW
                    y += h;
                }
                // ON TO THE NEXT COLUMN
                x += w;
            }
        }

    }
}
