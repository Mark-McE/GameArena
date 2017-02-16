/**
 * Models the bricks to be hit in the brickbreaker game.
 */
public class Brick{
	
	private int xPosition = 0; 
	private int yPosition = 0; 
	private int brickWidth = 10; 
	private int brickHeight = 10;
	private String colour = "White"; 
	private Rectangle brickRectangle = new Rectangle(xPosition, yPosition, brickWidth, brickHeight, colour);
    
	/**
  * Basic Constructor
  * @param xPos The starting X position for the brick
  * @param yPos The starting Y position for the brick
  * @param width The width of the brick
  * @param height The height of the brick
  * @param col The colour of the brick
  */
	public Brick(int xPos, int yPos, int width, int height, String col){
		xPosition = xPos;
		yPosition = yPos;
		brickWidth = width;
	  brickHeight = height;
		colour = col;
    
    updateSprite();
	}
	
  /**
  * Updates the sprite to correctly represent the variables of the Brick.
  * only to be used within the brick class
  */
	private void updateSprite(){
    brickRectangle.setXPosition(xPosition);
    brickRectangle.setYPosition(yPosition);
    brickRectangle.setWidth(brickWidth);
    brickRectangle.setHeight(brickHeight);
    brickRectangle.setColour(colour);
  }
  
	/**
	 * Inits the brick in the given game arena
	 * @param GameArena The game arena to add the brick to
	 */
	public void addBrick(GameArena gameWindow){
		gameWindow.addRectangle(brickRectangle);
	}
  
  /**
  * Removes the brick from the game window
  * @param gameWindow The game arena to remove the brick from 
  */
  public void remove(GameArena gameWindow){
    gameWindow.removeRectangle(brickRectangle);
  }
  
	/**
	 * Moves the brick
	 * @param xShift The x-shift of the brick
	 */
	public void move(int shift){
		xPosition += shift;
    updateSprite();
	}

	/**
	 * Obtains the current height of the brick.
	 * @return the height of the brick as int.
	 */
	public int getHeight(){
		return brickHeight;
	}
	
  /**
  * obtains the rectangle class used to represent the brick
  * @return the rectangle used as the sprite
  */
  public Rectangle getRectangle(){
    return brickRectangle;
  }
  
	/**
	 * Sets the height of the brick
	 * @param newHeight the new height of the brick
	 */
	public void setHeight(int newHeight){
		brickHeight = newHeight;
    updateSprite();
	}
	
}