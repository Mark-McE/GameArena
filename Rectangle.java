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
	 * sets the width of this Rectangle.
   * @param w the new width
	 */
	public void setWidth(double w){
    width = w;
	}

	/**
	 * Obtains the height of this Rectangle.
	 * @return the height of this Rectangle,in pixels.
	 */
	public double getHeight(){
		return height;
	}

	/**
	 * sets the height of this Rectangle.
   * @param h the new height
	 */
	public void setHeight(double h){
    height = h;
	}

	/**
	 * Obtains the colour of this Rectangle.
	 * @return a textual description of the colour of this Rectangle.
	 */
	public String getColour(){
		return colour;
	}

	/**
	 * sets the colour of this Rectangle.
   * @param col the new colour
	 */
	public void setColour(String col){
		colour = col;
	}

	public Rectangle(double x, double y, double w, double h, String col){
		xPosition = x;
		yPosition = y;
		width = w;
		height = h;
		colour = col;
	}	
}
