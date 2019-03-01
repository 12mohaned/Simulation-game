package model.disasters;
import simulation.Rescuable;
import simulation.Simulatable;
//Class Containing Disasters which can happens to Citizens or Buildings 
abstract public class Disaster implements Simulatable{
private int startCycle;
private Rescuable target;
private boolean active = false;
public int getStartCycle(){
return startCycle;	
}
public Rescuable getTarget(){
return target;	
}
public boolean isActive(){
return active;	
}
public void setActive(boolean active){
this.active = active;	
}
public Disaster(int startCycle , Rescuable target){
this.startCycle = startCycle;
this.target = target;
}
}