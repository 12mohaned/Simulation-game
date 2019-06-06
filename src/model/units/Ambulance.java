package model.units;

import exceptions.CannotTreatException;
import exceptions.IncompatibleTargetException;
import model.events.WorldListener;
import model.people.Citizen;
import model.people.CitizenState;
import simulation.Address;
import simulation.Rescuable;

public class Ambulance extends MedicalUnit {

	public Ambulance(String unitID, Address location, int stepsPerCycle,
			WorldListener worldListener) {
		super(unitID, location, stepsPerCycle, worldListener);
	}

	@Override
	public void treat() {
		getTarget().getDisaster().setActive(false);

		Citizen target = (Citizen) getTarget();
		if (target.getHp() == 0) {
			jobsDone();
			return;
		} else if (target.getBloodLoss() > 0) {
			target.setBloodLoss(target.getBloodLoss() - getTreatmentAmount());
			if (target.getBloodLoss() == 0)
				target.setState(CitizenState.RESCUED);
		}

		else if (target.getBloodLoss() == 0)

			heal();

	}

	public void respond(Rescuable r) throws CannotTreatException, IncompatibleTargetException {
		InCompatible(this,r);
		if(this.canTreat(r)) {
		throw new CannotTreatException(this, r, this.getUnitID() +" Can't treat Already Safe " + r);	
		} 
		if (getTarget() != null && ((Citizen) getTarget()).getBloodLoss() > 0
				&& getState() == UnitState.TREATING)
			reactivateDisaster();
		finishRespond(r);
		
	}
	public String toString() {
	return "Unit Id is :"+ "\n" + this.getUnitID()+"\n" 
	+"Unit is Ambulance"+ "Unit location is:\n" +
	this.getLocation() + "Unit Target :\n" +
	this.getTarget()
	+ "Unit State\n" + this.getState();
	}
//public boolean canTreat(Rescuable r) {
//if(((Citizen) this.getTarget()).getBloodLoss() <=0){
//return true;	
//}
//return false;
//}
}
