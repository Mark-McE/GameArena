import java.util.*;
/**
* Driver class to represent a brick breaker type game.
* 
* @author Mark McElroy
* @author Chris Bartzis
**/
public class BrickBreakerWeek15{
  
  private static void updatePowers(boolean[] powers, Brick b){
    for(int i=0; i<powers.length; i++){
      if(b.getColour().equals(b.powers[i][1])){
        powers[i]=true;
      }
    }
  }
  
  public static void main( String[] args ){
    
    int windowHeight = 700;
    int windowLength = 500;
    int frames = 10;
    
    boolean[] currentPowers = new boolean[Brick.powers.length];
    for(int i=0; i<currentPowers.length; i++){
      currentPowers[i] = false;
    }
    
    GameArena gameWindow = new GameArena(windowLength,windowHeight);
    
    Paddle playerPaddle = new Paddle(windowLength/2, windowHeight-30, 70, "cyan");
    playerPaddle.addPaddle(gameWindow);
    
    Ball ball = new Ball(windowLength/4,windowHeight-35,1,0,10,"yellow");
    gameWindow.addBall(ball);
    
    List<Brick> bricks = new ArrayList<Brick>();
    BrickBreakerLevels levels = new BrickBreakerLevels(bricks);
    levels.load(1, gameWindow);
    
    for(int i=0;;i++){
      // ball-paddle collisions
      if( ball.colliding( playerPaddle.getRectangle() ) ){
        ball.resolvePaddleCollision(playerPaddle);
      }
      // ball-brick collisions
      for(int b = 0; b<bricks.size(); b++){
        if(ball.colliding( bricks.get(b).getRectangle() ) ){
          if(!currentPowers[1])
            ball.resolveCollision(bricks.get(b).getRectangle());
          updatePowers(currentPowers,bricks.get(b));
          bricks.get(b).remove(gameWindow);
          bricks.remove(bricks.get(b));
        }
      }
      // ball-wall collisions
      ball.resolveWallCollisions(gameWindow);
      
      if(i%frames==0){
        // player controls
        if(gameWindow.rightPressed()){
          playerPaddle.move(7);
        }
        if(gameWindow.leftPressed()){
          playerPaddle.move(-7);
        }
        
        gameWindow.pause();
      }
      ball.updatePosFraction(frames);
    }
  }
}