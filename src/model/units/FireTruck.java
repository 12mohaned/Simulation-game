package model.units;

import exceptions.CannotTreatException;
import model.events.WorldListener;
import model.infrastructure.ResidentialBuilding;
import model.people.Citizen;
import simulation.Address;
import simulation.Rescuable;

public class FireTruck extends FireUnit {

	public FireTruck(String unitID, Address location, int stepsPerCycle,
			WorldListener worldListener) {
		super(unitID, location, stepsPerCycle, worldListener);
	}

	@Override
	public void treat() {
		getTarget().getDisaster().setActive(false);

		ResidentialBuilding target = (ResidentialBuilding) getTarget();
		if (target.getStructuralIntegrity() == 0) {
			jobsDone();
			return;
		} else if (target.getFireDamage() > 0)

			target.setFireDamage(target.getFireDamage() - 10);

		if (target.getFireDamage() == 0)

			jobsDone();

	}
	public boolean canTreat(Rescuable r) 
	{
	if(((ResidentialBuilding)r).getStructuralIntegrity() <100) {
	return true;	
	}
	if(((ResidentialBuilding) r).getFireDamage() <=0){
	return true;	
	}
	return false;
	}
	public String toString() {
		return "Unit Id is :" + this.getUnitID() + "\n" +"Unit is FireTruck " + "\n"+ "Unit location is: " + this.getLocation() + "\n"+ "Unit Target :" +this.getTarget()
		+"\n" + "Unit State " + this.getState();
		}
	
}
