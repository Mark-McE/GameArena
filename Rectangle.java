/**
 * Models a simple, solid rectangle. 
 * This class represents a Rectabgle object. When combined with the GameArena class,
 * instances of the Rectangle class can be displayed on the screen.
 */
public class Rectangle{
	
	private double xPosition;
	private double yPosition;
	private double width;
	private double height;
	private String colour = "WHITE";
                                        // Permissable colours are 8 bit hexadecimal 
                                        // RGB values in the format #RRGGBB. e.g.
                                        // Pure red is FF0000
                                        // Pure red is 00FF00
                                        // Pure red is 0000FF

	/**
	 * Obtains the current position of this Rectangle.
	 * @return the X coordinate of this Rectangle within the GameArena.
	 */
	public double getXPosition(){
		return xPosition;
	}

	/**
	 * Obtains the current position of this Rectangle.
	 * @return the Y coordinate of this Rectangle within the GameArena.
	 */
	public double getYPosition(){
		return yPosition;
	}

	/**
	 * Moves the current position of this Rectangle to the given X co-ordinate
	 * @param x the new x co-ordinate of this Rectangle
	 */
	public void setXPosition(double x){
		this.xPosition = x;
	}

	/**
	 * Moves the current position of this Rectangle to the given Y co-ordinate
	 * @param y the new y co-ordinate of this Rectangle
	 */
	public void setYPosition(double y){
		this.yPosition = y;
	}

	/**
	 * Obtains the width of this Rectangle.
	 * @return the width of this Rectangle,in pixels.
	 */
	public double getWidth(){
		return width;
	}
  
  /**
     * Defines a new width for this Rectangle.
     * @param width The new width of this rectangle, in pixels.
	 */
	public void setWidth(double width){
		this.width = width;
	}

	/**
	 * Obtains the height of this Rectangle.
	 * @return the height of this Rectangle,in pixels.
	 */
	public double getHeight(){
		return height;
	}

  /**
     * Defines a new width for this Rectangle.
     * @param height The new height of this rectangle, in pixels.
	 */
	public void setHeight(double height){
		this.height = height;
	}

	/**
	 * Obtains the colour of this Rectangle.
	 * @return a textual description of the colour of this Rectangle.
	 */
	public String getColour(){
		return colour;
	}
	
	/**
	 * Changes the colour of this Rectangle
	 * @param input the new colour of this Rectangle
	 */
	public void setColour(String input){
		this.colour = input;
	}
  
  /**
  * Basic constructor
  * @param x The x coorinate for the center of the rectangle
  * @param y The y coorinate for the center of the rectangle
  * @param w The width of the rectangle
  * @param h The Height of the rectangle
  * @param col The colour of the rectangle. 
  * Can be hex values or common colour names supported by java
  */
	public Rectangle(double x, double y, double w, double h, String col){
		xPosition = x;
		yPosition = y;
		width = w;
		height = h;
		colour = col;
	}	
}
