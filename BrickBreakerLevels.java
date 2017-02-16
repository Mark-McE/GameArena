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
    bricks.add( new Brick( ga.getArenaWidth()/4, ga.getArenaHeight()/4, 70, 20, "white" ) );
  }
  
  private void level_2(GameArena ga){
    //bricks.add( new Brick( /**/ ) );
  }
  
  public boolean load(int level, GameArena gameWindow){
    
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
    }
    return validLevel;
  }
  
}