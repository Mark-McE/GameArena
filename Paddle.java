/**
 * Operates the players paddle. 
 * This class represents a Paddle
 */
public class Paddle(int xPosition, int yPosition, String colour, int paddleWidth) {
	
	private int paddleHeight = 30;
	
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