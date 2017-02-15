/**
 * Operates the players paddle. 
 * This class represents a Paddle
 */
public class Paddle{
	
	private int xPosition; 
	private int yPosition; 
	private int paddleWidth; 
	private String colour; 
	private int paddleHeight;
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
		colour = col;
	  paddleHeight = 20;
	}
	
	
	/**
	 * Obtains the current height of the paddle.
	 * @return the height of the paddle as int.
	 */
	public int getHeight(){
		return paddleHeight;
	}
	
	/**
	 * Sets the height of the paddle
	 * @param newHeight the new height of the paddle
	 */
	public void setHeight(int newHeight){
		paddleHeight = newHeight;
	}
	
	/**
	 * Inits the paddle in the given game arena
	 * @param GameArena The game arena to add the paddle to
	 */
	public void addPaddle(GameArena gameWindow){
		Rectangle paddleRectangle = new Rectangle(xPosition, yPosition, paddleWidth, paddleHeight, colour);
		gameWindow.addRectangle(paddleRectangle);
	}
	
	/**
	 * Moves the paddle
	 * @param xShift The x-shift of the paddle
	 */
	public void move(int shift){
		xPosition = xPosition + shift;
	}
	
	
}