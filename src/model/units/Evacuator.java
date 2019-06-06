package model.units;

import exceptions.CannotTreatException;
import model.events.WorldListener;
import model.infrastructure.ResidentialBuilding;
import model.people.Citizen;
import simulation.Address;
import simulation.Rescuable;

public class Evacuator extends PoliceUnit {

	public Evacuator(String unitID, Address location, int stepsPerCycle,
			WorldListener worldListener, int maxCapacity) {
		super(unitID, location, stepsPerCycle, worldListener, maxCapacity);

	}

	@Override
	public void treat() {
		ResidentialBuilding target = (ResidentialBuilding) getTarget();
		if (target.getStructuralIntegrity() == 0
				|| target.getOccupants().size() == 0) {
			jobsDone();
			return;
		}

		for (int i = 0; getPassengers().size() != getMaxCapacity()
				&& i < target.getOccupants().size(); i++) {
			getPassengers().add(target.getOccupants().remove(i));
			i--;
		}

		setDistanceToBase(target.getLocation().getX()
				+ target.getLocation().getY());
	}
//	public boolean canTreat(Rescuable r){
//if(((ResidentialBuilding)r).getStructuralIntegrity() > 0){
//if(((ResidentialBuilding) r).getOccupants().size() <=0 ){
//System.out.println(((ResidentialBuilding) r).getOccupants().size());	
//return false;				
//}
//}
//if(((ResidentialBuilding)r).getStructuralIntegrity() == 0) 
//{
//return true;	
//}
//return false;
//}
public boolean canTreat(Rescuable r){
if(((ResidentialBuilding)r).getFoundationDamage() == 0) 
{
return true;	
}	
return false;
}	
public String toString() {
	return "Unit Id is :" + this.getUnitID() + "\n" +"Unit is Evacuator " + "\n"+ "Unit location is: " + this.getLocation() + "\n"+ "Unit Target :" +this.getTarget()
	+"\n" + "Unit State " + this.getState() + "the size of Passengers is : "+this.getPassengers().size();
	}
}