import java.util.*;
/**
* Driver class to represent a brick breaker type game.
* 
* @author Mark McElroy
* @author Chris Bartzis
**/
public class BrickBreak{
  
  public static final int defaultPaddleWidth = 100;
  public static final int windowHeight = 700;
  public static final int windowLength = 495;
  public static final String defaultPaddleColour = "White";
  public static final String defaultBallColour = "White";
  
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
            paddle.setColour(defaultPaddleColour);
            paddle.setWidth(defaultPaddleWidth);
            break;
            
          case 1:
            ball.setColour(defaultBallColour);
            break;
            
          case 2:
            ball.setColour(defaultBallColour);           
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
        if(powers[i].getActive() == false){
          powers[i].setActive(true);
          
          System.out.print("POWER UP: ");
          switch(i){
            case 0:
              System.out.println("large paddle!");
              paddle.setColour(b.getColour());
              paddle.setWidth(140);
              break;
              
            case 1:
              System.out.println("no collide!");
              ball.setColour(b.getColour());
              break;
              
            case 2:
              System.out.println("slow ball!");
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
    
    final int frames = 10; // number of game loop executions before .pause() is called
    int currentLevel = 0;
    int lastLevel = 3;
    int paddleMoveSpeed = 7;
    int score = 0;
    
    PowerUp[] currentPowers = new PowerUp[PowerUp.powers.length];
    for(int i=0; i<currentPowers.length; i++){
      currentPowers[i] = new PowerUp(false,0);
    }
    
    GameArena gameWindow = new GameArena(windowLength,windowHeight);
    
    Paddle playerPaddle = new Paddle(windowLength/2, windowHeight-30, defaultPaddleWidth, defaultPaddleColour);
    playerPaddle.addPaddle(gameWindow);
    
    List<Ball> balls = new ArrayList<Ball>(); // Intend on adding multiball power up later.
    balls.add(new Ball(windowLength/4,windowHeight-35,1,0,10,defaultBallColour));
    gameWindow.addBall(balls.get(0));
    
    List<Brick> bricks = new ArrayList<Brick>();
    BrickBreakerLevels levels = new BrickBreakerLevels(bricks);
    
    for(int i=0;;i++){
      // ball-paddle collisions
      if( balls.get(0).colliding( playerPaddle.getRectangle() ) ){
        balls.get(0).resolvePaddleCollision(playerPaddle);
      }
      // ball-brick collisions
      for(int b = 0; b<bricks.size(); b++){
        if(balls.get(0).colliding( bricks.get(b).getRectangle() ) ){
          if((currentPowers[1].getActive()) == false) // cyan power-up removes collissions
            balls.get(0).resolveCollision(bricks.get(b).getRectangle());
          addPowers(currentPowers,bricks.get(b),balls.get(0),playerPaddle);
          bricks.get(b).remove(gameWindow);
          bricks.remove(bricks.get(b));
          if(currentLevel != -1){
            score++;
          }
        }
      }
      // ball-wall collisions
      balls.get(0).resolveWallCollisions(gameWindow);
      
      // executes every time .pause() is called
      if(i%frames==0){
        // player controls
        if(gameWindow.rightPressed()){
          playerPaddle.move(paddleMoveSpeed);
        }
        if(gameWindow.leftPressed()){
          playerPaddle.move(-1*paddleMoveSpeed);
        }
        
        //check for win/new levels
        if(bricks.size() == 0){
          playerPaddle.setPosition(new int[]{windowLength/2, windowHeight-30} );
          balls.get(0).setPosition(new double[]{windowLength/4,windowHeight-35});
          balls.get(0).setVelocity(new double[]{1,0});
          // ticks down all power ups on new level
          for(int j=0;j<10;j++){
            updatePowerUpTimers(currentPowers, balls.get(0), playerPaddle);
          }
          if(currentLevel == -1){
            levels.load(currentLevel, gameWindow);
          }
          else{
            currentLevel++;
            if(currentLevel>lastLevel)
              currentLevel=0;
            levels.load(currentLevel, gameWindow);
          }
          System.out.println("Current Score: " +score);
        }
        // check for game over
        if(balls.get(0).getYPosition()>windowHeight){
          currentLevel = -1; // game over screen
          // ticks down all power ups on new level
          for(int j=0;j<10;j++){
            updatePowerUpTimers(currentPowers, balls.get(0), playerPaddle);
          }
          playerPaddle.setPosition(new int[]{windowLength/2, windowHeight-30} );
          balls.get(0).setPosition(new double[]{windowLength/4,windowHeight-35});
          balls.get(0).setVelocity(new double[]{1,0});
          levels.load(currentLevel, gameWindow);
          System.out.println("final Score: " +score);
        }
        
        // executes roughly every second
        if(i%(frames*60)==0){
          updatePowerUpTimers(currentPowers, balls.get(0), playerPaddle);
          i=0; // resets i to 0 to prevent int overflow
        }
        
        gameWindow.pause();
      }
      balls.get(0).updatePosFraction(frames);
    }
  }
}