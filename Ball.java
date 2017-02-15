/**
 * Models a simple circle. 
 * This class represents a Ball object. When combined with the GameArena class,
 * instances of the Ball class can be displayed on the screen.
 */
import java.lang.Math;

public class Ball{
  private double[] position = new double[2];
  private double[] velocity = new double[2];
	private double size; // diameter
	private String colour = "WHITE";
  
  /**
  * Basic Constructor
  * @param x The starting X position of the ball
  * @param y The starting Y position of the ball
  * @param diameter The diameter of the call, in pixels
  * @param col The colour of the ball, can take RGB values or common colours as strings
  */
	public Ball(double x, double y, double diameter, String col){
		position[0] = x;
		position[1] = y;
		size = diameter;
		colour = col;
	}	

  /**
  * Checks if a ball is colliding with a paddle
  * @return true if ball is colliding with paddle, false if not
  */
  public boolean colliding(Paddle paddle){
    
    double dx = Math.abs( this.position[0] - (double)paddle.getRectangle().getXPosition() );
    double dy = Math.abs( this.position[1] - (double)paddle.getRectangle().getYPosition() );
    
    if(dx > paddle.getRectangle().getWidth()/2 +size/2 ){
      return false;
    }
    if(dy > paddle.getRectangle().getHeight()/2+size/2){
      return false;
    }
    // TODO
    // ** NOT FINISHED ** 
    // TODO
    // ** re-model this is check if colliding with a rectangle, 
    // and use paddle.getRectangle() when calling this method **
    return true;
  }
  
	/**
	 * Obtains the current position of this Ball.
	 * @return the position vector of this Ball within the GameArena.
	 */
	public double[] getPosition(){
		return position;
	}
  
  // legacy code required for gameArena class
	public double getXPosition(){
		return position[0];
	}
	public double getYPosition(){
		return position[1];
	}

	/**
	 * Obtains the current velocity of this Ball.
	 * @return the velocity vector of this Ball within the GameArena.
	 */
	public double[] getVelocity(){
		return velocity;
	}

	/**
	 * Moves the current position of this Ball to the given co-ordinates
	 * @param pos the new position vector of this Ball
	 */
	public void setPosition(double[] pos){
		this.position[0] = pos[0];
		this.position[1] = pos[1];
	}

	/**
	 * Changes the current velocity of this Ball to the given vector
	 * @param vel the new velocity vector of this Ball
	 */
	public void setVelocity(double[] vel){
		this.velocity[0] = vel[0];
		this.velocity[1] = vel[1];
	}

	/**
	 * Obtains the size of this Ball.
	 * @return the diameter of this Ball,in pixels.
	 */
	public double getSize(){
		return size;
	}

	/**
	 * Obtains the colour of this Ball.
	 * @return a textual description of the colour of this Ball.
	 */
	public String getColour(){
		return colour;
	}
}