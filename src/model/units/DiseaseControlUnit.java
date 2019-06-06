package model.units;

import exceptions.CannotTreatException;
import exceptions.IncompatibleTargetException;
import model.events.WorldListener;
import model.people.Citizen;
import model.people.CitizenState;
import simulation.Address;
import simulation.Rescuable;

public class DiseaseControlUnit extends MedicalUnit {

	public DiseaseControlUnit(String unitID, Address location,
			int stepsPerCycle, WorldListener worldListener) {
		super(unitID, location, stepsPerCycle, worldListener);
	}

	@Override
	public void treat() {
		getTarget().getDisaster().setActive(false);
		Citizen target = (Citizen) getTarget();
		if (target.getHp() == 0) {
			jobsDone();
			return;
		} else if (target.getToxicity() > 0) {
			target.setToxicity(target.getToxicity() - getTreatmentAmount());
			if (target.getToxicity() == 0)
				target.setState(CitizenState.RESCUED);
		}

		else if (target.getToxicity() == 0)
			heal();

	}

	public void respond(Rescuable r)throws CannotTreatException, IncompatibleTargetException {
			InCompatible(this,r);
			if(this.canTreat(r)) {
			throw new CannotTreatException(this, r, this.getUnitID() +" Can't treat Already Safe " + r);
			}
		if (getTarget() != null && ((Citizen) getTarget()).getToxicity() > 0
				&& getState() == UnitState.TREATING)	
			reactivateDisaster();
		finishRespond(r);
			}
	public String toString() {
		return "Unit Id is :" + this.getUnitID() + "\n" +"Unit is DiseaseControlUnit " + "\n"+ "Unit location is: " + this.getLocation() + "\n"+ "Unit Target :" +this.getTarget()
		+"\n" + "Unit State " + this.getState();
		}	
}

//	public boolean canTreat(Rescuable r)throws CannotTreatException {
//		if(((Citizen) r).getToxicity() <=0){
//		return true;	
//		}
//		return false;
//		}
