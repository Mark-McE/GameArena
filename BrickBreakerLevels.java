import java.util.*;
import java.lang.Math;
import java.io.FileReader;
import java.io.BufferedReader;
/**
* Stores all levels of the brick breaker game.
* With methods to load the levels into an arrayList of Bricks, and add them to the GameArena
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
  
  private void level_1(GameArena ga){
    //******level layout********
    //***********8x9************
    //**■■■■■■■■■■■■■■■■■■■■■■**
    //**■■■■■■■■■■■■■■■■■■■■■■**
    //**■■■■■■■■■■■■■■■■■■■■■■**
    //**■■■■■■■■■■■■■■■■■■■■■■**
    //**■■■■■■■■■■■■■■■■■■■■■■**
    //**■■■■■■■■■■■■■■■■■■■■■■**
    //**■■■■■■■■■■■■■■■■■■■■■■**
    //**************************
    //**************************
    //**************************
    //**************************
    //************o*************
    //**********----************
    int brickWidth = 55;
    int brickHeight = 20;
    
    for(int j=ga.getArenaHeight()/7,jCount=0; j<=ga.getArenaHeight()/2; j+=(brickHeight+10), jCount++ ){
      for (int i=brickWidth, iCount=0; i<ga.getArenaWidth()-brickWidth; i+=brickWidth, iCount++){
        if( (jCount==8) && (iCount==3 || iCount==4) ){
          bricks.add( new Brick( i, j, brickWidth, brickHeight, "Red" ) );
        }
        else{
          bricks.add( new Brick( i, j, brickWidth, brickHeight, "white" ) );
        }
      }
    }
  }
  
  private void level_2(GameArena ga){
    
    String[][] levelInput = new String[24][10];
    String line = "";
    int brickWidth = 45;
    int brickHeight = 20;
    
    try{
      BufferedReader levelFile = new BufferedReader(new FileReader("level1.csv"));
      int k = 0;
      System.out.println("Loading file....");
      while((line = levelFile.readLine()) != null){
        levelInput[k] = line.split(",");
        k++;
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
    
  }
  
  private void level_win(GameArena ga){
    //******level layout********
    //**********15x12***********
    //**■■■■■■■■■■■■■■■■■■■■■■**
    //**■■■■■■■■■■■■■■■■■■■■■■**
    //**■■■■■■■■■■■■■■■■■■■■■■**
    //**■■■■■■■■■■■■■■■■■■■■■■**
    //**■■■■■■■■■■■■■■■■■■■■■■**
    //**■■■■■■■■■■■■■■■■■■■■■■**
    //**■■■■■■■■■■■■■■■■■■■■■■**
    //**■■■■■■■■■■■■■■■■■■■■■■**
    //**■■■■■■■■■■■■■■■■■■■■■■**
    //**************************
    //**************************
    //************o*************
    //**********----************
    
    int brickWidth = 29;
    int brickHeight = 20;
    
    for(int j=ga.getArenaHeight()/7,jCount=0; j<=ga.getArenaHeight()/2; j+=(brickHeight), jCount++ ){
      for (int i=brickWidth, iCount=0; i<ga.getArenaWidth()-brickWidth; i+=brickWidth, iCount++){
        if( (jCount==11) && (iCount==5 || iCount==10 || iCount==15) )
        {
          bricks.add( new Brick( i, j, brickWidth, brickHeight, "Red" ) );
        }
        else{
          bricks.add( new Brick( i, j, brickWidth, brickHeight, "white" ) );
        }
      }
    }
  }
  
  private void level_lose(GameArena ga){
    //bricks.add( new Brick( /**/ ) );
  }
  
  /**
  * Loads levels onto the GameArena
  * @param level The level to load
  * @param gameWindow The GameArena to load the level onto.
  */
  public void load(int level, GameArena gameWindow){
    
    boolean validLevel;
    
    switch (level){
      case -1: level_lose(gameWindow); validLevel = true;
        break;
      case 0: level_win(gameWindow); validLevel = true;
        break;
      case 1: level_1(gameWindow); validLevel = true;
        break;
      case 2: level_2(gameWindow); validLevel = true;
        break;
      default: validLevel = false;
    }
    if(validLevel){
      for(int i = 0; i<bricks.size(); i++){
        bricks.get(i).addBrick(gameWindow);
      }
    }else{
      System.out.print("Invalid level loaded");
      System.exit(0);
    };
  }
  
}