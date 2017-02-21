
/**
* Class to store information about power ups in the brick breaker game
*/
public class PowerUp{
  
  private boolean active; // tells if power up is currently active
  private int timer; // time for power up measured in seconds
  
  /**
  * basic constructor
  * @param isActive Boolean stating if the power up is active at the moment
  * @param timeToLive How long the power ups lasts for, in seconds.
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
  
  public boolean getActive(){
    return active;
  }
  
  public void setActive(boolean state){
    active = state;
  }
  
  public int getTimer(){
    return timer;
  }
  
  public void setTimer(int t){
    timer = t;
  }
}