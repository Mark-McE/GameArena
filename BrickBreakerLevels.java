import java.util.*;
/**
* Class to store all levels of brick breaker
*/
public class BrickBreakerLevels{
  private List<Brick> bricks;
  
  public BrickBreakerLevels(List<Brick> bs){
    bricks = bs;
  }
  
  private void level_1(GameArena ga){
    //**************************
    //******level layout********
    //**************************
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
    int brickWidth = 55;
    int brickHeight = 20;
    
    for(int j=ga.getArenaHeight()*2/7; j<=ga.getArenaHeight()*9/14; j+=(brickHeight+10) ){
      for (int i=brickWidth; i<ga.getArenaWidth()-brickWidth; i+=brickWidth){
        bricks.add( new Brick( i, j, brickWidth, brickHeight, "white" ) );
      }
    }
  }
  
  private void level_2(GameArena ga){
    //bricks.add( new Brick( /**/ ) );
  }
  
  public void load(int level, GameArena gameWindow){
    
    boolean validLevel;
    
    switch (level){
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