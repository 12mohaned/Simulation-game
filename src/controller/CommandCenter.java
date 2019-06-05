package controller;

import java.awt.Dimension;
import model.disasters.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

import exceptions.SimulationException;
import exceptions.UnitException;
import model.events.SOSListener;
import model.infrastructure.ResidentialBuilding;
import model.people.Citizen;
import model.units.Ambulance;
import model.units.DiseaseControlUnit;
import model.units.Evacuator;
import model.units.FireTruck;
import model.units.GasControlUnit;
import model.units.Unit;
import model.units.UnitState;
import simulation.Rescuable;
import simulation.Simulator;
import view.GameOver;
import view.SimulationView;
//Controller in MVC Pattern
public class CommandCenter implements SOSListener,ActionListener{

	private Simulator engine;
	private ArrayList<ResidentialBuilding> visibleBuildings;
	private ArrayList<Citizen> visibleCitizens;
	private SimulationView View ; 
	private JButton[][]world;
	private ArrayList<JButton>Buildings;
	private int Counter;
	private JComboBox combo;
	private Unit unit;
	private Rescuable rescuable;
	@SuppressWarnings("unused")
	private ArrayList<Unit> emergencyUnits;
	private ArrayList<JButton>units;
	private ArrayList<JButton> Citizens;
	private GameOver GO;
	private JScrollPane s;
	public CommandCenter() throws Exception {
		engine = new Simulator(this);
		units = new ArrayList<JButton>();
		visibleBuildings = new ArrayList<ResidentialBuilding>();
		visibleCitizens = new ArrayList<Citizen>();
		/* emergencyUnits variable in line 35 assign itself to the Units loaded from the CSV in 
		 * Simulator method getEmergencyUnits which return units loaded from csv /*
		 */
		Buildings = new ArrayList<JButton>();
		Citizens  = new ArrayList<JButton>();
		emergencyUnits = engine.getEmergencyUnits();
		s = new JScrollPane();
		combo = new JComboBox();
		combo.setPreferredSize(new Dimension(40,40));
		combo.addActionListener(this);
		world= new JButton[10][10];
		View = new SimulationView(this);	
		this.setUnits();
		this.setMap();
		View.getAddUnit().addActionListener(this);
		View.getAddUnit().setIcon(new ImageIcon("/Users/mohanedmashaly/gamesim/Addbutton.png"));
		units.add(View.getAddUnit());
		View.setUnit(units);
	}
	/*this method create ArrayList of Button every button contains the units which is unloaded from emergencyunits r*/
	/* the method which starts the Simulation*/
	public void setUnits() {
	for(int i =0; i <emergencyUnits.size();i++){
		JButton b = new JButton();
		b.setPreferredSize(new Dimension(120,120));	
		Unit u = emergencyUnits.get(i);
		if(u instanceof Ambulance){
		b.setIcon(new ImageIcon("/Users/mohanedmashaly/eclipse-workspace/SimGame/thumbnail-01.jpg"));
		}
		if(u instanceof Evacuator) {
		b.setIcon(new ImageIcon("/Users/mohanedmashaly/Quiz2/Unknown.jpg"));
		}
		if(u instanceof GasControlUnit) {
		b.setIcon(new ImageIcon("/Users/mohanedmashaly/Quiz2/GasCar.jpg"));
		}
		if(u instanceof DiseaseControlUnit) {	
		b.setIcon(new ImageIcon("/Users/mohanedmashaly/Quiz2/DiseasesControl.png"));	
		}
		if(u instanceof FireTruck) {
		b.setIcon(new ImageIcon("/Users/mohanedmashaly/Quiz2/FireTruck.jpg"));		
		}
		units.add(b);	
		b.setText(u.toString());
		b.addActionListener(this);
		}
		View.setUnit(units);
	}
	public void setMap(){
		for(int i =0 ;i < 10;i++){
			for(int j =0; j < 10;j++) {
		JButton b = new JButton();
			b.setPreferredSize(new Dimension(80,120));
			b.addActionListener(this);
			world[i][j]=b;
			}	
			}
	View.setMap(world);	
	}
	@Override
	public void receiveSOSCall(Rescuable r){
		if (r instanceof ResidentialBuilding) {	
			if (!visibleBuildings.contains(r)) {
				visibleBuildings.add((ResidentialBuilding) r);
			}
		} 
		else {	
			if (!visibleCitizens.contains(r)) {
				visibleCitizens.add((Citizen) r);
			}
		}
	}
public void setIcon(){
for(int i=0; i < 10;i++){
for(int j =0; j < 10;j++){
for(int b = 0; b < visibleBuildings.size();b++){
if (i==visibleBuildings.get(b).getLocation().getX()&& j==visibleBuildings.get(b).getLocation().getY() )
{
world[i][j].setIcon(new ImageIcon("/Users/mohanedmashaly/gamesim/l.jpg"));
world[i][j].setText(visibleBuildings.get(b).toString());
setDisasterIcon(visibleBuildings.get(b),world[i][j]);
Buildings.add(world[i][j]);
}
}
for(int c =0; c < visibleCitizens.size();c++) {
if (i==visibleCitizens.get(c).getLocation().getX()&& j==visibleCitizens.get(c).getLocation().getY())
{	
world[i][j].setIcon(new ImageIcon("/Users/mohanedmashaly/gamesim/images.jpg"));
world[i][j].setText(visibleCitizens.get(c).toString());
setDisasterIcon(visibleCitizens.get(c),world[i][j]);
Citizens.add(world[i][j]);
}
}
}
}
}
public void setDisasterIcon(Rescuable building,JButton BuildingButton){
if(building.getDisaster() instanceof Fire){
BuildingButton.setRolloverEnabled(true);
BuildingButton.setRolloverIcon(new ImageIcon("/Users/mohanedmashaly/gamesim/LL.jpg"));
}
if(building.getDisaster() instanceof Injury){
BuildingButton.setRolloverEnabled(true);
BuildingButton.setRolloverIcon(new ImageIcon("/Users/mohanedmashaly/gamesim/images.png"));
} 
if(building.getDisaster() instanceof Infection){
BuildingButton.setRolloverEnabled(true);
BuildingButton.setRolloverIcon(new ImageIcon("/Users/mohanedmashaly/gamesim/images.png"));
}
if(building.getDisaster() instanceof GasLeak){
BuildingButton.setRolloverEnabled(true);
BuildingButton.setRolloverIcon(new ImageIcon("/Users/mohanedmashaly/gamesim/Gas.jpg"));
}
if(building.getDisaster() instanceof Collapse){
BuildingButton.setRolloverEnabled(true);
BuildingButton.setRolloverIcon(new ImageIcon("/Users/mohanedmashaly/gamesim/Collapse.png"));
}
}
//public void AssignRescuable(ActionEvent e){
//for(int i=0; i < 10;i++) {
//for(int j=0; j < 10;j++){
//if(e.getSource() == world[i][j]) {
//for(int Building =0; Building < visibleBuildings.size();Building++){
//if(visibleBuildings.get(Building).getLocation().getX() == i && visibleBuildings.get(Building).getLocation().getY() == j) {
//rescuable = visibleBuildings.get(Building);		
//}
//}
//}
//for(int Citizen =0; Citizen< visibleCitizens.size();Citizen++){
//if(visibleBuildings.get(Citizen).getLocation().getX() == i && visibleBuildings.get(Citizen).getLocation().getY() == j) {	
//rescuable = visibleCitizens.get(Citizen);	
//}
//}
//}
//}	
//}	
public void AssignRescuable(JButton b){
int i = 0;
int j =0;
for(int k =0; k < 10;k++) {
for(int l =0; l < 10;l++) {
if(world[k][l] == b) {
i = k; 
j = l;
}	
}	
}
for(int c =0; c < visibleCitizens.size();c++){
if(visibleCitizens.get(c).getLocation().getX() == i && visibleCitizens.get(c).getLocation().getY() == j) {
rescuable = visibleCitizens.get(c);	
}	
}
for(int building = 0; building < visibleBuildings.size();building++){
if(visibleBuildings.get(building).getLocation().getX() == i && visibleBuildings.get(building).getLocation().getY() == j) {
rescuable = visibleBuildings.get(building);		
}	
}
}
public void AddNewUnit (){
for(int i=0 ; i < emergencyUnits.size();i++) {
if(!emergencyUnits.get(i).toString().equals(units.get(i).getText())) {	
JButton unit = new JButton();
unit.setPreferredSize(new Dimension(80,120));
Unit u = emergencyUnits.get(i);
if(u instanceof Ambulance){
unit.setIcon(new ImageIcon("/Users/mohanedmashaly/eclipse-workspace/SimGame/thumbnail-01.jpg"));
}
if(u instanceof Evacuator) {
unit.setIcon(new ImageIcon("/Users/mohanedmashaly/Quiz2/Unknown.jpg"));
}
if(u instanceof GasControlUnit) {
unit.setIcon(new ImageIcon("/Users/mohanedmashaly/Quiz2/GasCar.jpg"));
}
if(u instanceof DiseaseControlUnit) {	
unit.setIcon(new ImageIcon("/Users/mohanedmashaly/Quiz2/DiseasesControl.png"));	
}
if(u instanceof FireTruck) {
unit.setIcon(new ImageIcon("/Users/mohanedmashaly/Quiz2/FireTruck.jpg"));		
}
units.add(unit);	
unit.setText(u.toString());
unit.addActionListener(this);
}	
}
View.setUnit(units);
}
@Override
public void actionPerformed(ActionEvent e)
{
JButton b =(JButton) e.getSource();
if(View.getAddUnit() == e.getSource()) {
AddNewUnit();
}
else{
AssignRescuable(b);
int Unit_index = units.indexOf(b);
if(Unit_index != -1) 
{	
unit = emergencyUnits.get(Unit_index);
units.get(emergencyUnits.indexOf(unit)).setText(unit.toString());	
}	
if(e.getSource() == View.getEndCurrentCycle()) {
try {
if(!engine.checkGameOver()) 
{
engine.nextCycle();
setIcon();
Counter++;
View.AddCurrentCycle(Counter,engine.calculateCasualties());
JOptionPane.showMessageDialog(null, Log());
}
else {
GO = new GameOver();
GO.SetCasualities(engine.calculateCasualties());
System.exit(0);
}
} 
catch (SimulationException e1) {
System.out.println(e1.getMessage());
}	
}
if(e.getSource() == View.getRespond())
{
try
{
if(unit != null && rescuable != null) {
unit.respond(rescuable);
}
}
catch (UnitException e1) {
System.out.println(e1.getMessage());
}
}
}
View.AddToPanel(b.getText());
}
public static void main(String[] args) throws Exception { 
new CommandCenter();
}
public String Log() {
String s = " ";
for(int i =0; i < visibleBuildings.size();i++) {
if(visibleBuildings.get(i).getDisaster() != null){
s += visibleBuildings.toString();
s+="\n";
}	
}
for(int i =0; i < visibleCitizens.size();i++) {
if(visibleCitizens.get(i).getDisaster() != null){
s += visibleCitizens.toString();
s+="\n";
}	
}
return s;
}
}
