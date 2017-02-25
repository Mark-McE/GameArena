import java.util.*;
/**
* Driver class to represent a brick breaker type game.
* 
* @author Mark McElroy
* @author Chris Bartzis
**/
public class BrickBreakerWeek15{
  
  public static final int defaultPaddleWidth = 100;
  public static final int windowHeight = 700;
  public static final int windowLength = 495;
  
  /**
  * Adds one second to the timer varaible for all active powerUp classes.
  * Will set PowerUp.active to false if timer is reduced to 0.
  */
  private static void updatePowerUpTimers(PowerUp[] powers, Ball ball, Paddle paddle){
    for(int i =0; i<powers.length; i++){
      if(powers[i].getActive()){
        powers[i].timerTick();
        if(powers[i].getTimer()<=0){
          powers[i].setActive(false);
          
          switch(i){
          case 0:
            paddle.setColour("Cyan");
            paddle.setWidth(defaultPaddleWidth);
            break;
            
          case 1:
            ball.setColour("Yellow");
            break;
            
          case 2:
            ball.setColour("Yellow");           
            ball.setVelocity( new double []{ball.getVelocity()[0] , ball.getVelocity()[1]*2} );
            break;
            
          default:
            break;
          }
        }
      }
    }
  }
  
  /**
  * Activates power ups if the currently hit brick contains a power up.
  */
  private static void addPowers(PowerUp[] powers, Brick b, Ball ball, Paddle paddle){
    for(int i=0; i<powers.length; i++){
      if(b.getColour().equals(PowerUp.powers[i])){
        powers[i].setTimer(10);
        if(!powers[i].getActive()){
          powers[i].setActive(true);
          
          switch(i){
            case 0:
              paddle.setColour(b.getColour());
              paddle.setWidth(140);
              break;
              
            case 1:
              ball.setColour(b.getColour());
              break;
              
            case 2:
              ball.setColour(b.getColour());
              ball.setVelocity( new double[]{ball.getVelocity()[0],ball.getVelocity()[1]/2} );
              break;
              
            default:
              break;
          }
        }
      }
    }
  }
  
  public static void main( String[] args ){
    
    int frames = 10;
    int currentLevel = 0;
    int lastLevel = 2;
    
    PowerUp[] currentPowers = new PowerUp[PowerUp.powers.length];
    for(int i=0; i<currentPowers.length; i++){
      currentPowers[i] = new PowerUp(false,0);
    }
    
    GameArena gameWindow = new GameArena(windowLength,windowHeight);
    
    Paddle playerPaddle = new Paddle(windowLength/2, windowHeight-30, defaultPaddleWidth, "cyan");
    playerPaddle.addPaddle(gameWindow);
    
    Ball playerBall = new Ball(windowLength/4,windowHeight-35,1,0,10,"yellow");
    gameWindow.addBall(playerBall);
    
    List<Brick> bricks = new ArrayList<Brick>();
    BrickBreakerLevels levels = new BrickBreakerLevels(bricks);
    
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
          i=0;
        }
        
        //check for win/new levels
        if(bricks.size() == 0){
          currentLevel++;
          if(currentLevel>lastLevel)
            currentLevel=0;
          
          for(int wait=0; wait<(50*0.2); wait++)
            gameWindow.pause();
          
          levels.load(currentLevel, gameWindow);
        }
        
        gameWindow.pause();
      }
      playerBall.updatePosFraction(frames);
    }
  }
}