import java.util.*;
import java.lang.Math;
import java.io.FileReader;
import java.io.BufferedReader;
/**
* Handles all externally stored levels of the brick breaker game.
* Loads the levels into an arrayList of Bricks, and adds Bricks to the GameArena
*/
public class BrickBreakerLevels{
  
  private List<Brick> bricks;
  
  /**
  * Basic constructor
  * @param bs The arrayList of bricks which the game is using
  */
  public BrickBreakerLevels(List<Brick> bs){
    bricks = bs;
  }

  /**
  * Loads levels onto the GameArena
  * @param level The level to load
  * @param gameWindow The GameArena to load the level onto.
  */ 
  public void load(int level, GameArena gameWindow){
    
    String levelFileName = "";
    String[][] levelInput = new String[24][10];
    String line = "";
    int brickWidth = 45;
    int brickHeight = 20;
    try{
      switch (level){
        case -1: levelFileName = "levelLose.csv";
          break;
        case 0: levelFileName = "levelWin.csv";
          break;
        case 1: levelFileName = "level1.csv";
          break;
        case 2: levelFileName = "level2.csv";
          break;
        default: 
          System.out.println("Invalid level loaded"); System.exit(0);
      }
      BufferedReader levelFile = new BufferedReader(new FileReader(levelFileName));
      System.out.println("Loading file....");
      for( int k=0; (line = levelFile.readLine()) != null; k++ ){
        levelInput[k] = line.split(",");
      }
      levelFile.close();
      System.out.println("File loaded");
    }catch(java.io.IOException ex){
      System.out.println("Failed to load file!");
    }
    
    for(int j=brickHeight/2,jCount=0; jCount<=23; j+=(brickHeight+1), jCount++ ){
      for (int i=brickWidth/2, iCount=0; iCount<=10; i+=brickWidth, iCount++){
        if(!(levelInput[jCount][iCount].equals("0"))){
          bricks.add( new Brick( i, j, brickWidth, brickHeight, levelInput[jCount][iCount] ) );
        }
      }
    }
    
    for(int i = 0; i<bricks.size(); i++){
      bricks.get(i).addBrick(gameWindow);
    }
  }
  
}