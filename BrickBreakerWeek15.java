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
    
    Paddle playerPaddle = new Paddle(windowLength/2, windowHeight-50, "cyan", 100);
    
    playerPaddle.addPaddle(gameWindow);
    
    while(true){
      if(gameWindow.rightPresed()){
        playerPaddle.move(5);
      }
      if(gameWindow.leftPressed()){
        playerPaddle.move(-5);
      }
      pause();
    }
  }
  
}