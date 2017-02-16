import java.util.*;
/**
* Driver class to represent a brick breaker type game.
* 
* @author Mark McElroy
* @author Chris Bartzis
**/
public class BrickBreakerWeek15{
  public static void main( String[] args ){
    
    int windowHeight = 700;
    int windowLength = 500;
    int frames = 10;
    
    GameArena gameWindow = new GameArena(windowLength,windowHeight);
    
    Paddle playerPaddle = new Paddle(windowLength/2, windowHeight-30, 70, "cyan");
    playerPaddle.addPaddle(gameWindow);
    
    Ball ball = new Ball(windowLength/4,windowHeight-35,1,0,10,"yellow");
    gameWindow.addBall(ball);
    
    List<Brick> bricks = new ArrayList<Brick>();
    // test brick
    //bricks.add( new Brick( windowLength/4,windowHeight/4,70,20,"white" ) );
    //bricks.get(0).addBrick(gameWindow);
   
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
          ball.resolveCollision(bricks.get(b).getRectangle());
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