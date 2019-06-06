package model.units;

import exceptions.CannotTreatException;
import exceptions.IncompatibleTargetException;
import exceptions.UnitException;
import model.disasters.Disaster;
import model.events.SOSResponder;
import model.events.WorldListener;
import model.infrastructure.ResidentialBuilding;
import model.people.Citizen;
import simulation.Address;
import simulation.Rescuable;
import simulation.Simulatable;

public abstract class Unit implements Simulatable, SOSResponder {
	private String unitID;
	private UnitState state;
	private Address location;
	private Rescuable target;
	private int distanceToTarget;
	private int stepsPerCycle;
	private WorldListener worldListener;

	public Unit(String unitID, Address location, int stepsPerCycle,
			WorldListener worldListener) {
		this.unitID = unitID;
		this.location = location;
		this.stepsPerCycle = stepsPerCycle;
		this.state = UnitState.IDLE;
		this.worldListener = worldListener;
	}

	public void setWorldListener(WorldListener listener) {
		this.worldListener = listener;
	}

	public WorldListener getWorldListener() {
		return worldListener;
	}

	public UnitState getState() {
		return state;
	}

	public void setState(UnitState state) {
		this.state = state;
	}

	public Address getLocation() {
		return location;
	}

	public void setLocation(Address location) {
		this.location = location;
	}

	public String getUnitID() {
		return unitID;
	}

	public Rescuable getTarget() {
		return target;
	}

	public int getStepsPerCycle() {
		return stepsPerCycle;
	}

	public void setDistanceToTarget(int distanceToTarget) {
		this.distanceToTarget = distanceToTarget;
	}

	@Override
	public void respond(Rescuable r)throws UnitException {
	
			InCompatible(this,r);
			if(this.canTreat(r)) {
			throw new CannotTreatException(this, r, this.unitID +" Can't treat Already Safe " + r);	
			} 
			if (target != null && state == UnitState.TREATING) 
				reactivateDisaster();
		finishRespond(r);
			}
		
	public void reactivateDisaster() {
		Disaster curr = target.getDisaster();
		curr.setActive(true);
	}

	public void finishRespond(Rescuable r) {

		target = r;
		state = UnitState.RESPONDING;
		Address t = r.getLocation();
		distanceToTarget = Math.abs(t.getX() - location.getX())
				+ Math.abs(t.getY() - location.getY());

	}

	public abstract void treat();

	public void cycleStep(){
		if (state == UnitState.IDLE)
			return;
		if (distanceToTarget > 0) {
			distanceToTarget = distanceToTarget - stepsPerCycle;
			if (distanceToTarget <= 0) {
				distanceToTarget = 0;
				Address t = target.getLocation();
				worldListener.assignAddress(this, t.getX(), t.getY());
			}
		} else {
			state = UnitState.TREATING;
			treat();
		}
	}

	public void jobsDone() {
		target = null;
		state = UnitState.IDLE;

	}
	public void InCompatible(Unit u,Rescuable r) throws IncompatibleTargetException {
	if(u instanceof Ambulance || u instanceof DiseaseControlUnit){
	if(r instanceof ResidentialBuilding) {	
	throw new IncompatibleTargetException(u, u.target, u.getUnitID()+" Can't Handle Buildings");	}
	}
	else {	
	if(r instanceof Citizen) {
	throw new IncompatibleTargetException(u, u.target, u.getUnitID()+" Can't Handle Citizens");
	}	
	}
	}
	public boolean canTreat(Rescuable r) {		
	if(r instanceof Citizen) {
	if(this instanceof Ambulance) {
	if(((Citizen) r).getBloodLoss() <=0){
	return true;	
	}
	}
	else {
	if(this instanceof DiseaseControlUnit) {	
	if(((Citizen) r).getToxicity() <=0){
	return true;	
	}
	}
	}
	}
	else{
	if(((ResidentialBuilding) r).getFoundationDamage() <= 0 &&((ResidentialBuilding) r).getFireDamage() <=0
	&&((ResidentialBuilding) r).getGasLevel()<=0) {	
	if(this instanceof Evacuator)
	{
	if(((ResidentialBuilding) r).getGasLevel()>=0){
	return true;	
	}
	if(((ResidentialBuilding) r).getFireDamage()>=0){
	return true;	
	}
	}
	}
	}
	return false;
}
//	public String Printname() {
//	String s ="";
//	if(this instanceof Ambulance) {
//	s = "Ambulance";	
//	}
//	if(this instanceof Evacuator) {
//		s = "Evacuator";	
//		}
//	if(this instanceof GasControlUnit) {
//		s = "GasControlUnit";	
//		}
//	if(this instanceof DiseaseControlUnit) {
//	s = "DiseasesControlUnit";		
//	}
//	if(this instanceof FireTruck) {
//	s = "FireTruck";	
//	}
//	return s;
//	}
}