
/**
* Class to store information about power ups in the brick breaker game
*/
public class PowerUp{
  
  private boolean active; // tells if power up is currently active
  private int timer; // time for power up measured in seconds
  
  /**
  * basic constructor
  * @param isActive Boolean stating if the power up is active at the moment
  * @param timeToLive How long this power up lasts for, in seconds.
  */
  public PowerUp(boolean isActive, int timeToLive){
    boolean active = isActive;
    int timer = timeToLive;
  }
  
  /**
  * lowers the timer by one second.
  */
  public void timerTick(){
    this.timer--;
  }
  
  /**
  * Obtains the active variable
  */
  public boolean getActive(){
    return active;
  }
  
  /**
  * Sets the activiely of this power up
  * @param state The boolean value of activity
  */
  public void setActive(boolean state){
    active = state;
  }
  
  /**
  * Obtains the remaining time of this power up
  */
  public int getTimer(){
    return timer;
  }
  
  /**
  * Sets the timer for this power up
  * @param t The time for this timer to tick down from
  */
  public void setTimer(int t){
    timer = t;
  }
}