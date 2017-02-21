import java.util.*;
/**
* Driver class to represent a brick breaker type game.
* 
* @author Mark McElroy
* @author Chris Bartzis
**/
public class BrickBreakerWeek15{
  
  private static void updatePowerUpTimers(PowerUp[] powers, Ball ball, Paddle paddle){
    for(int i =0; i<powers.length; i++){
      if(powers[i].getActive()){
        powers[i].timerTick();
        if(powers[i].getTimer()<=0){
          powers[i].setActive(false);
          ball.setColour("yellow");
          paddle.setColour("Cyan");
          paddle.setWidth(200);
        }
      }
    }
  }
  
  private static void addPowers(PowerUp[] powers, Brick b, Ball ball, Paddle paddle){
    for(int i=0; i<powers.length; i++){
      if(b.getColour().equals(b.powers[i][1])){
        powers[i].setActive(true);
        powers[i].setTimer(10);
        
        switch(i){
          case 0:
            paddle.setColour(b.getColour());
            paddle.setWidth(140);
            break;
            
          case 1:
            ball.setColour(b.getColour());
            break;
            
          default:
            break;
        }
      }
    }
  }
  
  public static void main( String[] args ){
    
    int windowHeight = 700;
    int windowLength = 500;
    int frames = 10;
    
    PowerUp[] currentPowers = new PowerUp[Brick.powers.length];
    for(int i=0; i<currentPowers.length; i++){
      currentPowers[i] = new PowerUp(false,0);
    }
    
    GameArena gameWindow = new GameArena(windowLength,windowHeight);
    
    Paddle playerPaddle = new Paddle(windowLength/2, windowHeight-30, 200, "cyan");
    playerPaddle.addPaddle(gameWindow);
    
    Ball playerBall = new Ball(windowLength/4,windowHeight-35,1,0,10,"yellow");
    gameWindow.addBall(playerBall);
    
    List<Brick> bricks = new ArrayList<Brick>();
    BrickBreakerLevels levels = new BrickBreakerLevels(bricks);
    levels.load(1, gameWindow);
    
    for(int i=0;;i++){
      // ball-paddle collisions
      if( playerBall.colliding( playerPaddle.getRectangle() ) ){
        playerBall.resolvePaddleCollision(playerPaddle);
      }
      // ball-brick collisions
      for(int b = 0; b<bricks.size(); b++){
        if(playerBall.colliding( bricks.get(b).getRectangle() ) ){
          if(!(currentPowers[1].getActive())) // cyan power-up removes collissions
            playerBall.resolveCollision(bricks.get(b).getRectangle());
          addPowers(currentPowers,bricks.get(b),playerBall,playerPaddle);
          bricks.get(b).remove(gameWindow);
          bricks.remove(bricks.get(b));
        }
      }
      // ball-wall collisions
      playerBall.resolveWallCollisions(gameWindow);
      
      // executes every frame
      if(i%frames==0){
        // player controls
        if(gameWindow.rightPressed()){
          playerPaddle.move(7);
        }
        if(gameWindow.leftPressed()){
          playerPaddle.move(-7);
        }
        
        // executes roughly every second
        if(i%(frames*60)==0){
          updatePowerUpTimers(currentPowers, playerBall, playerPaddle);
        }
        
        gameWindow.pause();
      }
      playerBall.updatePosFraction(frames);
    }
  }
}