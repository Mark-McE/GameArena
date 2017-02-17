import java.util.*;
import java.lang.Math;
/**
* Class to store all levels of brick breaker
*/
public class BrickBreakerLevels{
  private List<Brick> bricks;
  
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
        if( (jCount==5) && (iCount==4 || iCount==5) ){
          bricks.add( new Brick( i, j, brickWidth, brickHeight, "Cyan" ) );
        }
        else{
          bricks.add( new Brick( i, j, brickWidth, brickHeight, "white" ) );
        }
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