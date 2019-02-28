package model.disasters;

import model.infrastructure.ResidentialBuilding;
import simulation.Rescuable;

public class Fire extends Disaster {

	public Fire(int cycle, ResidentialBuilding target) {
		super(cycle, target);
	}

}
