import java.lang.Math;
/**
 * Models a simple circle. 
 * This class represents a Ball object. When combined with the GameArena class,
 * instances of the Ball class can be displayed on the screen.
 */
public class Ball{
  private double[] position = new double[2];
  private double[] velocity = new double[2];
	private double size; // diameter
	private String colour = "WHITE";
  
  /**
  * Basic Constructor
  * @param x The starting X position of the ball
  * @param y The starting Y position of the ball
  * @param vx The starting X velocity of the ball
  * @param vy The starting Y velocity of the ball
  * @param diameter The diameter of the call, in pixels
  * @param col The colour of the ball, can take RGB values or common colours as strings
  */
	public Ball(double x, double y, double vx, double vy, double diameter, String col){
		position[0] = x;
		position[1] = y;
    velocity[0] = vx;
    velocity[1] = vy;
		size = diameter;
		colour = col;
	}	

  /**
  * Checks if a ball is colliding with a paddle
  * @return true if ball is colliding with paddle, false if not
  */
  public boolean colliding(Rectangle rec){
    
    double dx = Math.abs( this.position[0] - rec.getXPosition() );
    double dy = Math.abs( this.position[1] - rec.getYPosition() );
    
    // obvious too far away to collides dealt with first
    if(dx > rec.getWidth()/2 + this.size/2 ){
      return false;
    }
    if(dy > rec.getHeight()/2+size/2){
      return false;
    }
    
    // all checks from here will now not satisfy the above if statements
    // this means if the ball was a rectangle, it would collide at this point
    
    // obvious cases of touching
    if(dx <= rec.getWidth()/2){
      return true;
    }
    if(dy <= rec.getHeight()/2){
      return true;
    }
    
    // checks for collisions with the corner of the rectangle and the ball
    double cornerToCircleCenterDistSqr = Math.pow(dx-rec.getWidth()/2,2) +Math.pow(dy-rec.getHeight()/2,2);
    if(cornerToCircleCenterDistSqr <= Math.pow(this.size/2,2)){
      return true;
    }
    return false;
  }
  
  /**
  * Resolves collisions with this ball and a passed Paddle
  * by updating this ball's velocity vector
  * resolved x velocity is set to a function of how far from the center
  * of the paddle we collide
  * resolved y velocity is that of an elastic collision
  * @param paddle The paddle this ball is colliding with
  */
  public void resolvePaddleCollision(Paddle paddle){
    Rectangle rec = paddle.getRectangle();
    
    // direction is, ball --> rectangle
    double dySigned = rec.getYPosition() - this.position[1];
    double dxSigned = rec.getXPosition() - this.position[0] ;
    double maxVel = 8;
    
    if(velocity[1] == 0){
      velocity[1] = 8;
    }
    if( Math.signum(velocity[1]) == Math.signum( dySigned ) ){
      this.velocity[1] *= -1;
    
      double percentXDistance = ( dxSigned )/( rec.getWidth()/2 );
      this.velocity[0] = maxVel*percentXDistance*-1;
    }
  }
  
  /**
  * Resolves collision elastically with this ball and a passed Rectangle
  * by updating this ball's velocity vector
  * @param rec The Rectangle this ball is colliding with
  */
  public void resolveCollision(Rectangle rec){
    
    // direction is, ball --> rectangle
    double dySigned = rec.getYPosition() - this.position[1];
    double dxSigned = rec.getXPosition() - this.position[0] ;
    double maxVel = 10;
    
    // if ball above brick, and travelling towards brick
    if( Math.signum(velocity[1]) == Math.signum( dySigned ) 
    &&  Math.abs( dySigned ) >= rec.getHeight()/2
    ){
      this.velocity[1] *= -1;
    }
    if( Math.signum(velocity[0]) == Math.signum( dxSigned )
    &&  Math.abs( dxSigned ) >= rec.getWidth()/2
    ){
      this.velocity[0] *= -1;
    }
    
    
    
  }
  
  /**
  * Checks for collisions with game arena walla and if true, resolves them
  * @param windowWidth The width of the game arena
  * @param windowHeight The height of the game arena
  */
  public void resolveWallCollisions(GameArena ga){
    // collisions with right and left wall
    if(
    (  this.position[0] + this.size/2 >= ga.getArenaWidth()
    && this.velocity[0] > 0 )
    ||
    (  this.position[0] - this.size/2 <= 0
    && this.velocity[0] < 0 ))
    {
      this.velocity[0] *= -1;
    }
    // collisions with bottom and top wall
    // removed bottom wall collisions due to nature of the brick breaker
    if(/*
    (  this.position[1] + this.size/2 >= ga.getArenaHeight()
    && this.velocity[1] > 0 )
    || */
    (  this.position[1] - this.size/2 <= 0
    && this.velocity[1] < 0 ))
    {
      this.velocity[1] *= -1;
    }
  }
  
  /**
  * Adds the current velocity to the current position
  */
  public void updatePos(){
    this.position[0] += this.velocity[0];
    this.position[1] += this.velocity[1];
  }
  
  public void updatePosFraction(double fraction){
    this.position[0] += (this.velocity[0]/fraction);
    this.position[1] += (this.velocity[1]/fraction);
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
	 * Sets the size of this Ball to the given value.
     * @param size Teh new size of the Ball, in pixels.
	 */
	public void setSize(double size)
	{
		this.size = size;
	}

	/**
	 * Obtains the colour of this Ball.
	 * @return a textual description of the colour of this Ball.
	 */
	public String getColour(){
		return colour;
	}
  
  /**
	 * Changes the current colour of this Ball
	 * @param col the new colour of this Ball
  */
  public void setColour(String col){
    colour = col;
  }
}