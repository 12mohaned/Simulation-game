package model.disasters;
//Disaster which happens to Building
import model.infrastructure.ResidentialBuilding;
import simulation.Rescuable;
public class Collapse extends Disaster
{
public Collapse(int cycle ,ResidentialBuilding target){
super(cycle,target);
}

}
