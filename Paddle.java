/**
 * Operates the players paddle. 
 * This class represents a Paddle
 */
public class Paddle{
	
	private int xPosition = 0; 
	private int yPosition = 0; 
	private int paddleWidth = 10; 
	private int paddleHeight = 10;
	private String colour = "White"; 
	private Rectangle paddleRectangle = new Rectangle(xPosition, yPosition, paddleWidth, paddleHeight, colour);
    
	/**
  * Basic Constructor, defaults height to 20 pixels
  * @param xPos The starting X position for the paddle
  * @param yPos The starting Y position for the paddle
  * @param col The colour of the paddle
  * @param width The width of the paddle
  */
	public Paddle(int xPos, int yPos, String col, int width){
		xPosition = xPos;
		yPosition = yPos;
		paddleWidth = width;
	  paddleHeight = 20;
		colour = col;
    
    updateSprite();
	}
	
  /**
  * Updates the sprite to correctly represent the variables of the Paddle.
  * only to be used within the paddle class
  */
	private void updateSprite(){
    paddleRectangle.setXPosition(xPosition);
    paddleRectangle.setYPosition(yPosition);
    paddleRectangle.setWidth(paddleWidth);
    paddleRectangle.setHeight(paddleHeight);
    paddleRectangle.setColour(colour);
  }
  
	/**
	 * Obtains the current height of the paddle.
	 * @return the height of the paddle as int.
	 */
	public int getHeight(){
		return paddleHeight;
	}
	
  /**
  * obtains the rectangle class used to represent the paddle
  * @return the rectangle used as the sprite
  */
  public Rectangle getRectangle(){
    return paddleRectangle;
  }
  
	/**
	 * Sets the height of the paddle
	 * @param newHeight the new height of the paddle
	 */
	public void setHeight(int newHeight){
		paddleHeight = newHeight;
    updateSprite();
	}
	
	/**
	 * Inits the paddle in the given game arena
	 * @param GameArena The game arena to add the paddle to
	 */
	public void addPaddle(GameArena gameWindow){
		gameWindow.addRectangle(paddleRectangle);
	}
  
	/**
	 * Moves the paddle
	 * @param xShift The x-shift of the paddle
	 */
	public void move(int shift){
		xPosition += shift;
    updateSprite();
	}
}