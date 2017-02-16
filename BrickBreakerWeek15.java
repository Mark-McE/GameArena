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
    GameArena gameWindow = new GameArena(windowLength,windowHeight);
    
    Paddle playerPaddle = new Paddle(windowLength/2, windowHeight-30, "cyan", 70);
    playerPaddle.addPaddle(gameWindow);
    
    Ball ball = new Ball(windowLength/2,windowHeight/2,0,5,10,"yellow");
    gameWindow.addBall(ball);
    
    while(true){
      if(gameWindow.rightPressed()){
        playerPaddle.move(5);
      }
      if(gameWindow.leftPressed()){
        playerPaddle.move(-5);
      }
      if( ball.colliding( playerPaddle.getRectangle() ) ){
        ball.resolvePaddleCollision(playerPaddle);
      }
      
      // if(ball.colliding(brick.getRectangle())
      //   ball.resolveCollision(brick.getRectangle());
      // brick.remove();
      
      ball.resolveWallCollisions(windowLength,windowHeight);
      ball.updatePos();
      gameWindow.pause();
    }
  }
}