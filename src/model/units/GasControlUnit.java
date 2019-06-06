package model.units;

import exceptions.CannotTreatException;
import model.events.WorldListener;
import model.infrastructure.ResidentialBuilding;
import model.people.Citizen;
import simulation.Address;
import simulation.Rescuable;

public class GasControlUnit extends FireUnit {

	public GasControlUnit(String unitID, Address location, int stepsPerCycle,
			WorldListener worldListener) {
		super(unitID, location, stepsPerCycle, worldListener);
	}

	public void treat() {
		getTarget().getDisaster().setActive(false);

		ResidentialBuilding target = (ResidentialBuilding) getTarget();
		if (target.getStructuralIntegrity() == 0) {
			jobsDone();
			return;
		} else if (target.getGasLevel() > 0) 
			target.setGasLevel(target.getGasLevel() - 10);

		if (target.getGasLevel() == 0)
			jobsDone();

	}
	public boolean canTreat(Rescuable r) {
		if(((ResidentialBuilding)r).getStructuralIntegrity() <100) {
		return true;	
		}
		if(((ResidentialBuilding)r).getGasLevel() <=0){
		return true;	
		}
		return false;
		}
	public String toString() {
		return "Unit Id is :" + this.getUnitID() + "\n" +"Unit is GasControlUnit " + "\n"+ "Unit location is: " + this.getLocation() + "\n"+ "Unit Target :" +this.getTarget()
		+"\n" + "Unit State " + this.getState();
		}
}
