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
    final int brickWidth = 45;
    final int brickHeight = 20;
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
        case 3: levelFileName = "level3.csv";
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
    
    //remove any curently existing bricks
    while(bricks.size()>0){
      bricks.get(0).remove(gameWindow);
      bricks.remove(bricks.get(0));
    }
    
    // the following nested loops were tuned according to a set grid of bricks, 
    // please do not edit this, use the csv files to edit levels
    for(int y=brickHeight/2, j=0; j<=23; y+=(brickHeight+1), j++ ){
      for (int x=brickWidth/2, i=0; i<=10; x+=brickWidth, i++){
        if((levelInput[j][i].equals("0")) == false){
          bricks.add( new Brick( x, y, brickWidth, brickHeight, levelInput[j][i] ) );
        }
      }
    }
    
    for(int i = 0; i<bricks.size(); i++){
      bricks.get(i).addBrick(gameWindow);
    }
  }
  
}