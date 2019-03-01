package model.disasters;
//Disaster which happens to Building
import model.infrastructure.ResidentialBuilding;
import simulation.Rescuable;
public class GasLeak extends Disaster{
public	GasLeak(int cycle, ResidentialBuilding target) {
	super(cycle,target);
}
}
